package com.chd.bx.expenseAccount

import java.text.SimpleDateFormat
import com.chd.bx.login.UserLogin

class LoanAppReceiptsController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def loanAppReceiptsService = new LoanAppReceiptsService()
    def loanAppReceipts = new LoanAppReceipts()
    def loan_list = new ArrayList<LoanAppReceipts>();

    def index() {
        redirect(action: "list", params: params)
    }

    def loanAppReceiptsQuery() {
        println("LoanAppReceiptsController loanAppReceiptsQuery is loading....")
        loan_list = new ArrayList<LoanAppReceipts>();
        loan_list = loanAppReceiptsService.loanAppReceiptsQuery()
        render(view: '/expenseAccount/loanAppReceiptsList', model: [loan_list: loan_list])
    }

    def  loanAppReceiptsGJQuery(params){
        loan_list = new ArrayList<LoanAppReceipts>();
        loan_list = loanAppReceiptsService.loanAppReceiptsGJQuery(params)
        render(view: '/expenseAccount/loanAppReceiptsList', model: [loan_list: loan_list,startDate:params["startDate"],
                endDate:params["endDate"],loanAppReceiptsId:params["loanAppReceiptsId"],loanStatus:params["loanStatus"]])
    }

    def loanAppReceiptsAdd(params) {
        println("LoanAppReceiptsController loanReceiptsAdd is loading....")
        Date date = new Date()
        SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd")
        String nowDate = matter.format(date);
//        def empNo = params["loanEmpNo"]
//        def emp = new UserLogin()
        def user = (UserLogin)session.getAttribute("user")
//        if (empNo!=null && empNo!=-1) {
//            emp = loanAppReceiptsService.loanAppReceiptsAddChangeEmpNo(empNo)
//            render(view: '/expenseAccount/loanAppReceiptsAdd', model: [nowDate: nowDate,emp:emp])
//        }else{
//            render(view: '/expenseAccount/loanAppReceiptsAdd', model: [nowDate: nowDate])
//        }
        render(view: '/expenseAccount/loanAppReceiptsAdd', model: [nowDate: nowDate,user:user])
    }

    //添加保存
    def loanAppReceiptsSave(params) {
        println("LoanAppReceiptsController loanAppReceiptsSave is loading....")
        def loanId = getLoanId()
        loanAppReceipts = new LoanAppReceipts()
        loanAppReceipts.loanAppReceiptsId = loanId   //单据名称首字母J(1位)+公司代码（4位）+年份月分（4位）+3位流水号
        loanAppReceipts = loanAppRec(loanAppReceipts,params)
        loanAppReceipts.loanStatus = "已保存"
        loanAppReceiptsService.loanAppReceiptsSave(loanAppReceipts)

        render(view: '/expenseAccount/loanAppReceiptsUpdate',model: [loanAppReceipts: loanAppReceipts])
    }

    //提交
    def commitLoanAppReceipts(params){
        def action = params["act"]
        if (action.equals("add")){
            def loanId = getLoanId()
            loanAppReceipts = new LoanAppReceipts()
            loanAppReceipts.loanAppReceiptsId = loanId   //单据名称首字母J(1位)+公司代码（4位）+年份月分（4位）+3位流水号
        }else if (params["act"].equals("update")){
            loanAppReceipts = getLoanAppReceiptsById(params)
        }
        loanAppReceipts = loanAppRec(loanAppReceipts,params)
        loanAppReceipts.loanStatus = "已提交"

        loanAppReceiptsService.loanAppReceiptsSave(loanAppReceipts)
        render(view: '/expenseAccount/loanAppReceiptsCommit',model: [loanAppReceipts: loanAppReceipts])
    }

    def editLoanAppReceipts(params){
        loanAppReceipts = getLoanAppReceiptsById(params)
        if (loanAppReceipts.loanStatus.equals("已保存")){
            render(view: '/expenseAccount/loanAppReceiptsUpdate', model: [loanAppReceipts: loanAppReceipts])
        }else{
            render(view: '/expenseAccount/loanAppReceiptsCommit', model: [loanAppReceipts: loanAppReceipts])
        }
    }

    def lookUpLoanAppReceipts(params){
        loanAppReceipts = getLoanAppReceiptsById(params)
        render(view: '/expenseAccount/loanAppReceiptsCommit', model: [loanAppReceipts: loanAppReceipts])
    }

    //修改保存
    def loanAppReceiptsUpdate(params) {
        println("LoanAppReceiptsController loanAppReceiptsUpdate is loading....")
        loanAppReceipts = getLoanAppReceiptsById(params)
        loanAppReceipts = loanAppRec(loanAppReceipts,params)
        loanAppReceipts.loanStatus = "已保存"
        loanAppReceiptsService.loanAppReceiptsSave(loanAppReceipts)
        render(view: '/expenseAccount/loanAppReceiptsUpdate', model: [loanAppReceipts: loanAppReceipts])
    }

    //在新增时获得单号
    def getLoanId(){
        String loanId = "J";
        def comNo = "0001"  //公司代码（四位）

        //年月 （四位）
        Date date = new Date()
        SimpleDateFormat matter=new SimpleDateFormat("yyyyMM")
        String dates = matter.format(date);
        println("dates:"+dates)
        def comAndDate = comNo + dates.substring(2,6)
        loanId += comAndDate

        //流水号（三位）
        def serialNumberList = new ArrayList<String>()
        loan_list = new ArrayList<LoanAppReceipts>()
        loan_list = loanAppReceiptsService.loanAppReceiptsQuery()
        def n1 = 0
        def n2 = 0
        if(loan_list !=null && loan_list.size()>0){
            for (int i=0;i<loan_list.size();i++){
                def str = loan_list.get(i).getLoanAppReceiptsId()
                def s = str.substring(1,9)
                if (comAndDate.equals(s)){
                    if (serialNumberList.size()==0){
                        n1 = Integer.parseInt(str.substring(9,str.length()))
                        serialNumberList.add(str.substring(9,str.length()))
                    }else{
                        n2 = Integer.parseInt(str.substring(9,str.length()))
                        if (n2 > n1){
                            serialNumberList.clear()
                            serialNumberList.add(str.substring(9,str.length()))
                            n1 = n2
                        }
                    }
                }
            }
        }

        if (serialNumberList.size()==0){
            loanId += "001"
        }else{
            int t =  Integer.parseInt(serialNumberList.get(0))+1
            int a = t/10
            def serialNumber = ""
            if (t<100){
                if (a>0){
                    serialNumber = "0"+t
                }else{
                    serialNumber = "00"+t
                }
                loanId += serialNumber
            }else{
                loanId += t
            }
        }
        return loanId
    }

    //页面获取值之后赋给一个对象
    def loanAppRec(LoanAppReceipts loanApp,params){
        loanApp.loanEmpNo = params["loanEmpNo"]
        loanApp.loanCostCenter = params["loanCostCenter"]
        loanApp.loanCompanyNo = params["loanCompanyNo"]
//        loanApp.loanDepartmentNo = params["loanDepartmentNo"]
        loanApp.loanDepartmentNo = "02"
        loanApp.loanEmpName = params["loanEmpName"]
        loanApp.loanEmpPhone = params["loanEmpPhone"]
        loanApp.loanEmpPosition = params["loanEmpPosition"]
        loanApp.loanEmpIdNumber = params["loanEmpIdNumber"]
        loanApp.loanBudgetCenter = params["loanBudgetCenter"]
        loanApp.loanParagraphDate = params["loanParagraphDate"]
//        loanApp.loanParagraphDate = new Date(System.currentTimeMillis())
        loanApp.loanPaymentType = params["loanPaymentType"]
        loanApp.loanMoney = Double.parseDouble(params["loanMoney"])
        loanApp.loanAlreadyRefund = Double.parseDouble(params["loanAlreadyRefund"])
        loanApp.loanBalance = Double.parseDouble(params["loanBalance"])
//        loanApp.loanBalance = Double.parseDouble(params["loanMoney"]) - Double.parseDouble(params["loanAlreadyRefund"])
        loanApp.loanBegDate = params["loanBegDate"]
//        loanApp.loanBegDate = new Date(System.currentTimeMillis())
        loanApp.loanOperatorName = params["loanOperatorName"]
        loanApp.loanPurpose = params["loanPurpose"]
        loanApp.loanRemark = params["loanRemark"]
        return loanApp
    }

    //通过  loanAppReceiptsId 得到对应的对象
    def getLoanAppReceiptsById(params){
        List<LoanAppReceipts> list= loanAppReceiptsService.getLoanAppReceiptsById(params["loanAppReceiptsId"])
        loanAppReceipts = new LoanAppReceipts()
        if(list!=null && list.size()>0){
            loanAppReceipts = list.get(0)
        }
    }

}
