package com.chd.bx.expenseAccount

import com.chd.bx.login.UserLogin
import com.chd.bx.examAppHistory.ExamAppHistory
import com.chd.bx.flow.ExmApp
import org.jbpm.api.task.Task
import java.sql.DriverManager
import java.sql.Statement
import java.sql.ResultSet
import empInformation.EmpInformation
import processes.ExmAppTask

class LoanAppReceiptsService {

    def loanAppReceiptsQuery(def empNo) {
        println("LoanAppReceiptsService loanReceiptsQuery is loading....")
        String strSql = "from LoanAppReceipts where loanEmpNo= '"+empNo+"' order by id asc "
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
            return list.get(0)
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

    def getProcessApprove(String role){
        def strSql = "from ExmApp where empRole='"+role +"' and receiptsType = 'LOAN'"
        List<ExmApp> list = ExmApp.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list.get(0)
        }
    }

    def getTaskByExecutionId(String executionId){
        def strSql = "SELECT DBID_, EXECUTION_ID_, TASKDEFNAME_ \n" +
                "\tFROM PEIHAO.JBPM4_TASK WHERE EXECUTION_ID_='"+executionId+"'"
        try {
            def conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.190:1521:ORCL","peihao","peihao")
//            def pre = conn.prepareStatement(strSql)
//            def rs = pre.executeQuery()
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(strSql);
            List<ExmAppTask> list = new ArrayList<ExmAppTask>()
            while(rs.next()){
                def exmAppTask = new ExmAppTask()
                exmAppTask.setDbId(rs.getString("DBID_"))
                exmAppTask.setExecutionId(rs.getString("EXECUTION_ID_"))
                exmAppTask.setTaskDefName(rs.getString("TASKDEFNAME_"))
                list.add(exmAppTask)
            }
            if (list != null && list.size() > 0) {
                return list
            }else{
                return null
            }
        } catch (Exception e1) {
            e1.printStackTrace()
        }
    }


}
