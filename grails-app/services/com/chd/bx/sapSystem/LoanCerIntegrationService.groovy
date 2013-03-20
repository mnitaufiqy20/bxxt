package com.chd.bx.sapSystem

import com.capgemini.eis.adapter.framework.requester.IServiceRequester
import com.capgemini.eis.adapter.framework.requester.impl.BaseServiceRequester
import com.capgemini.eis.adapter.framework.message.IMsgObject
import com.capgemini.eis.adapter.framework.message.impl.MsgObject
import com.capgemini.eis.adapter.framework.message.impl.GroupRecord
import com.capgemini.eis.adapter.framework.message.implcom.MsgConstants
import sapSystem.LoanCerIntegrationOutput
import com.chd.bx.expenseAccount.LoanAppReceipts
import com.chd.bx.accSubSafeguard.AccSubSafeguard

class LoanCerIntegrationService {

    /**
     * 借款单凭证集成
     * @param jkdpzInCommand 调用参数
     * @return
     */
    def serviceMethod(LoanCerIntegrationOutput loanCerIntegrationOutput){
        //调用状态信息
        String msg = new String()
        List<LoanCerIntegrationImport> loanCerIntegrationList = new ArrayList<LoanCerIntegrationImport>()
        //获取服务调用者实例
        IServiceRequester Requester = BaseServiceRequester.getInstance();
        //定义请求报文对象
        IMsgObject reqMo = new MsgObject();
        //对请求报文对象进行设置
        reqMo.setBusinessCode("S0010000000015");
        //查询参数
        GroupRecord groupRecord = new GroupRecord()
        groupRecord.setFieldValue("BUKRS", loanCerIntegrationOutput.companyCode);
        groupRecord.setFieldValue("JKRQ", loanCerIntegrationOutput.jkrq);
        groupRecord.setFieldValue("XKRQ", loanCerIntegrationOutput.xkrq);
        groupRecord.setFieldValue("JKDH", loanCerIntegrationOutput.jkdh);
        groupRecord.setFieldValue("JKYE", loanCerIntegrationOutput.jkye);
        groupRecord.setFieldValue("YGBH", loanCerIntegrationOutput.ygbh);
        groupRecord.setFieldValue("FKFS", loanCerIntegrationOutput.fkfs);
        groupRecord.setFieldValue("YT", loanCerIntegrationOutput.yt);
        groupRecord.setFieldValue("XYKM", loanCerIntegrationOutput.xykm);
        groupRecord.setFieldValue("JKKM", loanCerIntegrationOutput.jkkm);

        groupRecord.setName("T_DATA")
        List<GroupRecord> groupRecordList = new ArrayList<GroupRecord>()
        groupRecordList.add(groupRecord)
        reqMo.setReqGroupRecord(groupRecordList)

        //发送请求
        IMsgObject resMo = Requester.execute(reqMo);

        //判断是否请求成功
        if (MsgConstants.STATUS_FAIL.equals(resMo.getServResStatus())) {
            //失败
            msg = "请求查询失败，错误编号：[" + resMo.getServResCode() + "]，错误信息：[" + resMo.getServResDesc() + "]";
        } else if (MsgConstants.STATUS_COMPLETE.equals(resMo.getServResStatus())) {
            //成功
            msg = "请求查询成功";
            List<GroupRecord> grs = resMo.getResGroupRecord("T_MESSAGE")
            LoanCerIntegrationImport loanCerIntegrationImport;
            for(GroupRecord gr in grs){
                loanCerIntegrationImport = new LoanCerIntegrationImport()
                loanCerIntegrationImport.setCompanyCode(gr.getFieldValue("BUKRS"))
                loanCerIntegrationImport.setLoanAccYear(gr.getFieldValue("GJAHR"))
                loanCerIntegrationImport.setLoanCerNo(gr.getFieldValue("BELNR"))
                loanCerIntegrationImport.setLoanNo(gr.getFieldValue("JKDH"))
                loanCerIntegrationImport.setLoanMesType(gr.getFieldValue("TYPE"))
                loanCerIntegrationImport.setLoanMesDes(gr.getFieldValue("MESSAGE"))
                loanCerIntegrationList.add(loanCerIntegrationImport)
            }

            //接下来可以对jkdpzOutCommandList进行处理或return


        } else {
            //未知错误
            msg = "请求查询失败，错误信息：[未知错误]";
        }
        return msg;
    }

    def getAccSubSafeguardLoan(def type){
        String strSql = "from AccSubSafeguard where appType='"+type+"' order by id asc "
        List<AccSubSafeguard> list = AccSubSafeguard.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list
        }
    }
}
