package com.chd.bx.sapSystem

import com.capgemini.eis.adapter.framework.requester.IServiceRequester
import com.capgemini.eis.adapter.framework.message.IMsgObject
import com.capgemini.eis.adapter.framework.message.impl.MsgObject
import com.capgemini.eis.adapter.framework.message.impl.GroupRecord
import com.capgemini.eis.adapter.framework.requester.impl.BaseServiceRequester
import com.capgemini.eis.adapter.framework.message.implcom.MsgConstants
import jbpm.SpringUtil
import java.sql.Statement;

class CostCenterImportService {

    def costCenterImport(def companyCode) {
        //调用状态信息
        String msg = new String()
        List<CostCenterImport> costCenterImportList = new ArrayList<CostCenterImport>()
        //获取服务调用者实例
        IServiceRequester Requester = BaseServiceRequester.getInstance();
        //定义请求报文对象
        IMsgObject reqMo = new MsgObject();
        //对请求报文对象进行设置
        reqMo.setBusinessCode("S0010000000017");
        //查询参数
        reqMo.setReqValue("I_BUKRS", companyCode);

        //发送请求
        IMsgObject resMo = Requester.execute(reqMo);

        //判断是否请求成功
        if (MsgConstants.STATUS_FAIL.equals(resMo.getServResStatus())) {
            //失败
            msg = "请求查询失败，错误编号：[" + resMo.getServResCode() + "]，错误信息：[" + resMo.getServResDesc() + "]";
        } else if (MsgConstants.STATUS_COMPLETE.equals(resMo.getServResStatus())) {
            //成功
            msg = "请求查询成功";
            List<GroupRecord> grs = resMo.getResGroupRecord("T_CSKS")
            def costCenterImport;
            for(GroupRecord gr in grs){
                def cI =  CostCenterImport.findByCompanyCodeAndCostCenterNo(gr.getFieldValue("BUKRS"),gr.getFieldValue("KOSTL"))
                if(cI==null){
                    costCenterImport  =new CostCenterImport()
                    costCenterImport.setCompanyCode(gr.getFieldValue("BUKRS"))
                    costCenterImport.setCostCenterNo(gr.getFieldValue("KOSTL"))
                    costCenterImport.setCostCenterDes(gr.getFieldValue("KTEXT"))
                } else{
                    costCenterImport  = cI
                    costCenterImport.setCompanyCode(gr.getFieldValue("BUKRS"))
                    costCenterImport.setCostCenterNo(gr.getFieldValue("KOSTL"))
                    costCenterImport.setCostCenterDes(gr.getFieldValue("KTEXT"))
                }

                try {
                    if (costCenterImport.save(flush: true)) {
                        println("success")
                    } else {
                        println("error")
                        msg = "请求查询失败，错误信息：[未知错误]";
                    }
                } catch (Exception e) {
                    e.printStackTrace()
                    msg = "请求查询失败，错误信息：[未知错误]";
                }
                costCenterImportList.add(costCenterImport)
            }
            //接下来可以对cbzxOutCommandList进行处理或return
        } else {
            //未知错误
            msg = "请求查询失败，错误信息：[未知错误]";
        }
        return costCenterImportList;
    }

    def delete(String companyCode){
        def strSql="DELETE FROM TI_COST_CENTER_IMPORT WHERE COMPANY_CODE='"+companyCode+"'"
        def conn = null;
        try {
            org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy ds = SpringUtil.getBean("dataSource");
            conn = ds.getConnection()
            Statement stmt = conn.createStatement();
            stmt.execute(strSql);
            stmt.close()
        } catch (Exception e1) {
            e1.printStackTrace()
        }
    }
}
