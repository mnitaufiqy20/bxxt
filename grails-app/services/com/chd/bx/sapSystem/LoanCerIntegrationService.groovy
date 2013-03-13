package com.chd.bx.sapSystem

import com.capgemini.eis.adapter.framework.requester.IServiceRequester
import com.capgemini.eis.adapter.framework.requester.impl.BaseServiceRequester
import com.capgemini.eis.adapter.framework.message.IMsgObject
import com.capgemini.eis.adapter.framework.message.impl.MsgObject
import com.capgemini.eis.adapter.framework.message.impl.GroupRecord
import com.capgemini.eis.adapter.framework.message.implcom.MsgConstants

class LoanCerIntegrationService {

    /**
     * 借款单凭证集成
     * @param jkdpzInCommand 调用参数
     * @return
     */
//    def serviceMethod(JkdpzInCommand jkdpzInCommand){
//        //调用状态信息
//        String msg = new String()
//        List<JkdpzOutCommand> jkdpzOutCommandList = new ArrayList<JkdpzOutCommand>()
//        //获取服务调用者实例
//        IServiceRequester Requester = BaseServiceRequester.getInstance();
//        //定义请求报文对象
//        IMsgObject reqMo = new MsgObject();
//        //对请求报文对象进行设置
//        reqMo.setBusinessCode("S0010000000015");
//        //查询参数
//        GroupRecord groupRecord = new GroupRecord()
//        groupRecord.setFieldValue("BUKRS", jkdpzInCommand.companyCode);
//        groupRecord.setFieldValue("JKRQ", jkdpzInCommand.jkrq);
//        groupRecord.setFieldValue("XKRQ", jkdpzInCommand.xkrq);
//        groupRecord.setFieldValue("JKDH", jkdpzInCommand.jkdh);
//        groupRecord.setFieldValue("JKYE", jkdpzInCommand.jkye);
//        groupRecord.setFieldValue("YGBH", jkdpzInCommand.ygbh);
//        groupRecord.setFieldValue("FKFS", jkdpzInCommand.fkfs);
//        groupRecord.setFieldValue("YT", jkdpzInCommand.yt);
//        groupRecord.setFieldValue("XYKM", jkdpzInCommand.xykm);
//        groupRecord.setFieldValue("JKKM", jkdpzInCommand.jkkm);
//
//        groupRecord.setName("T_DATA")
//        List<GroupRecord> groupRecordList = new ArrayList<GroupRecord>()
//        groupRecordList.add(groupRecord)
//        reqMo.setReqGroupRecord(groupRecordList)
//
//        //发送请求
//        IMsgObject resMo = Requester.execute(reqMo);
//
//        //判断是否请求成功
//        if (MsgConstants.STATUS_FAIL.equals(resMo.getServResStatus())) {
//            //失败
//            msg = "请求查询失败，错误编号：[" + resMo.getServResCode() + "]，错误信息：[" + resMo.getServResDesc() + "]";
//        } else if (MsgConstants.STATUS_COMPLETE.equals(resMo.getServResStatus())) {
//            //成功
//            msg = "请求查询成功";
//            List<GroupRecord> grs = resMo.getResGroupRecord("T_MESSAGE")
//            JkdpzOutCommand jkdpzOutCommand;
//            for(GroupRecord gr in grs){
//                jkdpzOutCommand = new JkdpzOutCommand()
//                jkdpzOutCommand.setCompanyCode(gr.getFieldValue("BUKRS"))
//                jkdpzOutCommand.setGjahr(gr.getFieldValue("GJAHR"))
//                jkdpzOutCommand.setBelnr(gr.getFieldValue("BELNR"))
//                jkdpzOutCommand.setJkdh(gr.getFieldValue("JKDH"))
//                jkdpzOutCommand.setType(gr.getFieldValue("TYPE"))
//                jkdpzOutCommand.setMessage(gr.getFieldValue("MESSAGE"))
//                jkdpzOutCommandList.add(jkdpzOutCommand)
//            }
//
//            //接下来可以对jkdpzOutCommandList进行处理或return
//
//
//        } else {
//            //未知错误
//            msg = "请求查询失败，错误信息：[未知错误]";
//        }
//        return msg;
//    }
}
