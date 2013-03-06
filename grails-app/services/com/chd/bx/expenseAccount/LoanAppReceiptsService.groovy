package com.chd.bx.expenseAccount

import com.chd.bx.login.UserLogin

class LoanAppReceiptsService {

    def loanAppReceiptsQuery() {
        println("LoanAppReceiptsService loanReceiptsQuery is loading....")
        String strSql = "from LoanAppReceipts order by id asc "
        List<LoanAppReceipts> list = LoanAppReceipts.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list
        }
    }

    def loanAppReceiptsGJQuery(params){
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date startDate = null;
//        Date endDate = null;
//        if (params["startDate"] != null && params["startDate"].length() > 0) {
//            startDate = new Date(System.currentTimeMillis(format.parse(params["startDate"]).time));
//        }
//        if (params["endDate"] != null && params["endDate"].length() > 0) {
//            endDate = format.parse(params["endDate"]);
//        }
        String strSql = "from LoanAppReceipts where 1=1"
        if(params["loanAppReceiptsId"]!=null && params["loanAppReceiptsId"].length() > 0){
            strSql += " and loanAppReceiptsId like '%"+params["loanAppReceiptsId"] +"%'"
        }
        if(params["loanStatus"]!=null && params["loanStatus"]!="" && !params["loanStatus"].equals("-1")){
            strSql += " and loanStatus='"+params["loanStatus"] +"'"
        }
        if(params["startDate"]!=null && params["startDate"]!="" && params["endDate"]!=null && params["endDate"]!=""){
            strSql += " and loanBegDate >= '"+params["startDate"] +"' and loanBegDate <= '"+params["endDate"] +"'"
        }
        if(params["startDate"]!=null && params["startDate"]!="" && (params["endDate"]==null || params["endDate"]=="")){
            strSql += " and loanBegDate > '"+params["startDate"] +"'"
        }
        if((params["startDate"]==null || params["startDate"]=="") && params["endDate"]!=null && params["endDate"]!=""){
            strSql += " and loanBegDate < '"+params["endDate"] +"'"
        }

            strSql += " order by id asc "
        println(strSql)
        List<LoanAppReceipts> list = LoanAppReceipts.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list

        }
    }

    def loanAppReceiptsAddChangeEmpNo(def empNo){
        def strSql = "from UserLogin where empNo='"+empNo +"'"
        List<UserLogin> list = UserLogin.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list.get(0)
        }
    }

    def loanAppReceiptsSave(LoanAppReceipts loanAppReceipts) {
        println("LoanAppReceiptsService loanAppReceiptsSave is loading...."+loanAppReceipts)
        println("SELECT CONCAT(year(now()), LPAD(month(now()),2,'0'))")
        try {
            if (loanAppReceipts.save(flush: true)) {
                println("success")
            } else {
                println("error")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    def getLoanAppReceiptsById(String loanAppReceiptsId){
        def strSql = "from LoanAppReceipts where loanAppReceiptsId='"+loanAppReceiptsId +"'"
        List<LoanAppReceipts> list = LoanAppReceipts.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list
        }
    }
}
