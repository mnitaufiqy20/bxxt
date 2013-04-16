package com.chd.bx.sapSystem

import sapSystem.BxCerIntegrationHeadOutput
import sapSystem.BxCerIntegrationLineOutput
import com.capgemini.eis.adapter.framework.requester.IServiceRequester
import com.capgemini.eis.adapter.framework.requester.impl.BaseServiceRequester
import com.capgemini.eis.adapter.framework.message.IMsgObject
import com.capgemini.eis.adapter.framework.message.impl.MsgObject
import com.capgemini.eis.adapter.framework.message.impl.GroupRecord
import com.capgemini.eis.adapter.framework.message.implcom.MsgConstants

class BxCerIntegrationService {

    /**
     * 	费用报销单凭证集成
     * @param bxCerIntegrationHeadOutput SAP接口传入抬头栏
     * @param BxCerIntegrationLineOutput 接口传入行项目
     * @return
     */
    def serviceMethod(BxCerIntegrationHeadOutput bxCerIntegrationHeadOutput, List<BxCerIntegrationLineOutput> bxCerIntegrationLineOutputList) {
        //调用状态信息
        String msg = new String()
        List<BxCerIntegrationImport> bxCerIntegrationImportList = new ArrayList<BxCerIntegrationImport>()
        //获取服务调用者实例
        IServiceRequester Requester = BaseServiceRequester.getInstance();
        //定义请求报文对象
        IMsgObject reqMo = new MsgObject();
        //对请求报文对象进行设置
        reqMo.setBusinessCode("S0010000000016");
        //查询参数
        GroupRecord groupRecord = new GroupRecord()
        groupRecord.setFieldValue("BUKRS", bxCerIntegrationHeadOutput.companyCode);
        groupRecord.setFieldValue("PJBB", bxCerIntegrationHeadOutput.pjbb);
        groupRecord.setFieldValue("BXDH", bxCerIntegrationHeadOutput.bxdh);
        groupRecord.setFieldValue("SQRQ", bxCerIntegrationHeadOutput.sqrq);

        groupRecord.setName("T_DATA")
        List<GroupRecord> groupRecordList = new ArrayList<GroupRecord>()
        groupRecordList.add(groupRecord)

        for(int i =0;i<bxCerIntegrationLineOutputList.size();i++){
            groupRecord = new GroupRecord()
            groupRecord.setFieldValue("BXDH", bxCerIntegrationLineOutputList.get(i).bxdh);
            groupRecord.setFieldValue("FYKM", bxCerIntegrationLineOutputList.get(i).fykm);
            groupRecord.setFieldValue("YBJE", bxCerIntegrationLineOutputList.get(i).ybje);
            groupRecord.setFieldValue("BZ", bxCerIntegrationLineOutputList.get(i).bz);
            groupRecord.setFieldValue("KOSTL", bxCerIntegrationLineOutputList.get(i).kostl);
            groupRecord.setFieldValue("XYKM", bxCerIntegrationLineOutputList.get(i).xykm);
            groupRecord.setFieldValue("SJBX", bxCerIntegrationLineOutputList.get(i).sjbx);
            groupRecord.setFieldValue("JKDH", bxCerIntegrationLineOutputList.get(i).jkdh);
            groupRecord.setFieldValue("JKKM", bxCerIntegrationLineOutputList.get(i).jkkm);
            groupRecord.setFieldValue("BCJE", bxCerIntegrationLineOutputList.get(i).bcje);
            groupRecord.setFieldValue("YGBH", bxCerIntegrationLineOutputList.get(i).ygbh);

            groupRecord.setName("T_DETAIL")
            groupRecordList.add(groupRecord)
        }
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
            BxCerIntegrationImport bxCerIntegrationImport;
            for (GroupRecord gr in grs) {
                bxCerIntegrationImport = new BxCerIntegrationImport()
                bxCerIntegrationImport.setCompanyCode(gr.getFieldValue("BUKRS"))
                bxCerIntegrationImport.setBxAccYear(gr.getFieldValue("GJAHR"))
                bxCerIntegrationImport.setBxCerNo(gr.getFieldValue("BELNR"))
                bxCerIntegrationImport.setBxNo(gr.getFieldValue("BXDH"))
                bxCerIntegrationImport.setBxMesType(gr.getFieldValue("TYPE"))
                bxCerIntegrationImport.setBxMesDes(gr.getFieldValue("MESSAGE"))

                try {
                    if (bxCerIntegrationImport.save(flush: true)) {
                        println("success")
                    } else {
                        println("error")
                        msg = "请求查询失败，错误信息：[未知错误]";
                    }
                } catch (Exception e) {
                    e.printStackTrace()
                    msg = "请求查询失败，错误信息：[未知错误]";
                }
                bxCerIntegrationImportList.add(bxCerIntegrationImport)
            }
            //接下来可以对fybxdOutCommandList进行处理或return
        } else {
            //未知错误
            msg = "请求查询失败，错误信息：[未知错误]";
        }
        return msg;
    }
}
