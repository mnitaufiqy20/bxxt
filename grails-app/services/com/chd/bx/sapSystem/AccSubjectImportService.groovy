package com.chd.bx.sapSystem

import com.capgemini.eis.adapter.framework.message.impl.MsgObject
import com.capgemini.eis.adapter.framework.requester.IServiceRequester
import com.capgemini.eis.adapter.framework.requester.impl.BaseServiceRequester
import com.capgemini.eis.adapter.framework.message.IMsgObject
import com.capgemini.eis.adapter.framework.message.implcom.MsgConstants
import com.capgemini.eis.adapter.framework.message.impl.GroupRecord

class AccSubjectImportService {
    /**
     * 会计科目接口
     * @param companyCode   公司代码
     * @param kmdr       单一标识符（科目导入）
     * @return
     */
    def accSubjectImport(String companyCode,String subjectNo) {
        //调用状态信息
        String msg = new String()
        List<AccSubjectImport> accSubjectImportList = new ArrayList<AccSubjectImport>()
        //获取服务调用者实例
        IServiceRequester Requester = BaseServiceRequester.getInstance();
        //定义请求报文对象
        IMsgObject reqMo = new MsgObject();
        //对请求报文对象进行设置
        reqMo.setBusinessCode("S0010000000018");
        //查询参数
        reqMo.setReqValue("I_BUKRS", companyCode);
        reqMo.setReqValue("I_DMKR", subjectNo);

        //发送请求
        IMsgObject resMo = Requester.execute(reqMo);

        //判断是否请求成功
        if (MsgConstants.STATUS_FAIL.equals(resMo.getServResStatus())) {
            //失败
            msg = "请求查询失败，错误编号：[" + resMo.getServResCode() + "]，错误信息：[" + resMo.getServResDesc() + "]";
        } else if (MsgConstants.STATUS_COMPLETE.equals(resMo.getServResStatus())) {
            //成功
            msg = "请求查询成功";
            List<GroupRecord> grs = resMo.getResGroupRecord("T_SKAT")
            AccSubjectImport accSubjectImport;
            for(GroupRecord gr in grs){
                accSubjectImport = new AccSubjectImport()
                accSubjectImport.setCompanyCode(gr.getFieldValue("BUKRS"))
                accSubjectImport.setSubjectNo(gr.getFieldValue("SAKNR"))
                accSubjectImport.setSubjectDes(gr.getFieldValue("TXT20_ML"))
                try {
                    if (accSubjectImport.save(flush: true)) {
                        println("success")
                    } else {
                        println("error")
                        msg = "请求查询失败，错误信息：[未知错误]";
                    }
                } catch (Exception e) {
                    e.printStackTrace()
                    msg = "请求查询失败，错误信息：[未知错误]";
                }
                accSubjectImportList.add(accSubjectImport)
            }
            //接下来可以对kjkmOutCommandList进行处理或return
        } else {
            //未知错误
            msg = "请求查询失败，错误信息：[未知错误]";
        }
        return accSubjectImportList;
    }
}