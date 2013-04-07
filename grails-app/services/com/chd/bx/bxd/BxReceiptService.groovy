package com.chd.bx.bxd

import com.chd.bx.examAppHistory.ExamAppHistory

/**
 *   报销单service
 *   @author 孟敏
 *   @time 2013年1月6日
 */
class BxReceiptService {

    def serviceMethod() {

    }

    def bxdSave(BxReceipt bxReceipt) {
        try {
            if (bxReceipt.save(flush: true)) {
                println("success.....")
            } else {
                println("error......")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
    def bxdQueryAll() {
        String strSql = "from BxReceipt order by id asc "
        List<BxReceipt> list = BxReceipt.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list
        }
    }
    def getBxdById(String bxdNo){
        def strSql = "from BxReceipt where bxNo='"+bxdNo +"'"
        List<BxReceipt> list = BxReceipt.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list
        }
    }
    def queryReceipt(params){
         def strSql = " from BxReceipt where 1=1"
        if(params['rNo']!=null&&params['rNo'].length()>0){
           strSql+=" and bxNo like '%"+params['rNo']+"%'"
        }
        if(params['status']!=null&&params['status']!=""&&!params['status'].equals("-1")){
            strSql+=" and bxdStatus like '%"+params['status']+"%'"
        }
        if(params["startDate"]!=null&&params["startDate"]!="" && params["endDate"]!=null && params["endDate"]!=""){
            strSql += " and applicationDate >= '"+params["startDate"] +"' and applicationDate <= '"+params["endDate"] +"'"
        }
        if(params["startDate"]!=null && params["endDate"]==null){
            strSql += " and applicationDate > '"+params["startDate"] +"'"
        }
        if(params["startDate"]==null && params["endDate"]!=null){
            strSql += " and applicationDate < '"+params["endDate"] +"'"
        }
        strSql += " order by id asc "
        print(strSql)
        List<BxReceipt> list = BxReceipt.findAll(strSql)
        if(list!=null&&list.size()>0){
            return  list;
        }
    }

    def examineSave(ExamAppHistory examAppHistory) {
        try {
            if (examAppHistory.save(flush: true)) {
                println("success")
            } else {
                println("error")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
