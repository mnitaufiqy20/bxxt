package com.chd.bx.expenseAccount

class BudgetAdjustReceiptsController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def budgetAdjustReceiptsService = new BudgetAdjustReceiptsService()
    def budgetReportReceiptsService = new BudgetReportReceiptsService()
    def budgetReportReceipts = new BudgetReportReceipts()
    def budgetReportReceiptsT = new BudgetReportReceiptsTemp()
    def budget_list = new ArrayList<BudgetReportReceipts>()
    def str = ""
    def index() {
        redirect(action: "list", params: params)
    }

    def budgetAdjustReceiptsQuery(params) {
        println("LoanAppReceiptsController budgetAdjustReceiptsQuery is loading....")
//        render(view: '/expenseAccount/test')
        str = getYear()
        budgetReportReceipts = budgetAdjustReceiptsService.budgetAdjustReceiptsQuery()
        budgetReportReceiptsT = null
//        budgetReportReceiptsT = budgetAdjustReceiptsService.budgetAdjustReceiptsTQuery()
        render(view: '/expenseAccount/budgetAdjustReceipts',model: [budgetReportReceipts: budgetReportReceipts,budgetReportReceiptsT: budgetReportReceiptsT,budgetYear:str])
    }

    def budgetAdjustReceiptsChangeType(params){
        def budgetCostType = params["budgetCostType"]
        println("budgetCostType:"+budgetCostType)
        def budgetYear = params["budgetYear"]
        str = getYear()
        budgetReportReceipts = budgetAdjustReceiptsService.budgetAdjustReceiptsQueryByType(budgetCostType,budgetYear)
        budgetReportReceiptsT = null
        render(view: '/expenseAccount/budgetAdjustReceipts',model: [budgetReportReceipts: budgetReportReceipts,budgetReportReceiptsT: budgetReportReceiptsT,budgetYear:str])
//        budgetReportReceiptsT = budgetAdjustReceiptsService.budgetAdjustReceiptsTQueryByType(budgetCostType,budgetYear)
//        if (budgetReportReceipts!=null){
//            render(view: '/expenseAccount/budgetAdjustReceipts',model: [budgetReportReceipts: budgetReportReceipts,budgetReportReceiptsT: budgetReportReceiptsT,budgetYear:str])
//        }
    }

    def budgetAdjustReceiptsSave(params){
        def id = params["budgetReportReceiptsId"]
        def type = params["budgetCostType"]
        str = getYear()
        budgetReportReceipts = new BudgetReportReceipts()
        budgetReportReceipts = budgetAdjustReceiptsService.budgetAdjustReceiptsQueryById(id,type)
//        budgetReportReceipts = budgetAdjustRec(budgetReportReceipts,params)
//        budgetAdjustReceiptsService.budgetReportReceiptsSave(budgetReportReceipts)
        budgetReportReceiptsT = new BudgetReportReceiptsTemp()
        budgetReportReceiptsT = budgetAdjustReceiptsService.budgetAdjustReceiptsTQueryById(id,type)
        budgetReportReceiptsT = budgetAdjustRecT(budgetReportReceiptsT,params)
        budgetAdjustReceiptsService.budgetReportReceiptsTSave(budgetReportReceiptsT)
        render(view: '/expenseAccount/budgetAdjustReceipts',model: [budgetReportReceipts: budgetReportReceipts,budgetReportReceiptsT: budgetReportReceiptsT,budgetYear:str])
    }

    def getYear(){
        budget_list = new ArrayList<BudgetReportReceipts>()
        budget_list =  budgetReportReceiptsService.budgetReportReceiptsQuery()
        str = ""
        if (budget_list != null && budget_list.size()>0){
            List<String> listS = new ArrayList<String>();
            listS.add(budget_list.get(0).budgetYear);
            str += budget_list.get(0).budgetYear+";"
            for(int i=0;i<budget_list.size()-1;i++){
                boolean flag = true;
                for(def j=0;j<listS.size();j++){
                    if(budget_list.get(i+1).budgetYear.equals(listS.get(j))){
                        flag = false;
                        j=-1;
                        break;
                    }
                }
                if(flag){
                    listS.add(budget_list.get(i+1).budgetYear);
                    str += budget_list.get(i+1).budgetYear+";"
                }
            }
        }
        return str
    }

    def budgetAdjustRec(BudgetReportReceipts budRep,params){
//        budRep.budgetReportEmpNo = params["budgetReportEmpNo"]
//        budRep.budgetReportEmpName = params["budgetReportEmpName"]
//        budRep.budgetReportEmpPosition = params["budgetReportEmpPosition"]
//        budRep.budgetReportDate = params["budgetReportDate"]
//        budRep.budgetCompanyNo = params["budgetCompanyNo"]
//        budRep.budgetDepartmentNo = params["budgetDepartmentNo"]
        budRep.budgetCostCenter = params["budgetCostCenter"]
        budRep.budgetCenter = params["budgetCenter"]
//        budRep.budgetYear = params["budgetYear"]
        budRep.budgetTypeTotal = params["corSum"]

        budRep.budgetFirstQuarter = params["corQuarter1"]
        budRep.budgetSecondQuarter = params["corQuarter2"]
        budRep.budgetThirdQuarter = params["corQuarter3"]
        budRep.budgetFourthQuarter = params["corQuarter4"]

        budRep.budgetJanMoney = params["corMonth1"]
        budRep.budgetFebMoney = params["corMonth2"]
        budRep.budgetMarMoney = params["corMonth3"]
        budRep.budgetAprilMoney = params["corMonth4"]
        budRep.budgetMayMoney = params["corMonth5"]
        budRep.budgetJuneMoney = params["corMonth6"]
        budRep.budgetJulyMoney = params["corMonth7"]
        budRep.budgetAugustMoney = params["corMonth8"]
        budRep.budgetSepMoney = params["corMonth9"]
        budRep.budgetOctMoney = params["corMonth10"]
        budRep.budgetNovMoney = params["corMonth11"]
        budRep.budgetDecMoney = params["corMonth12"]
        return budRep
    }
    def budgetAdjustRecT(BudgetReportReceiptsTemp budRep,params){
//        budRep.budgetReportEmpNo = params["budgetReportEmpNo"]
//        budRep.budgetReportEmpName = params["budgetReportEmpName"]
//        budRep.budgetReportEmpPosition = params["budgetReportEmpPosition"]
//        budRep.budgetReportDate = params["budgetReportDate"]
//        budRep.budgetCompanyNo = params["budgetCompanyNo"]
//        budRep.budgetDepartmentNo = params["budgetDepartmentNo"]
        budRep.budgetCostCenter = params["budgetCostCenter"]
        budRep.budgetCenter = params["budgetCenter"]
//        budRep.budgetYear = params["budgetYear"]
        budRep.budgetTypeTotal = params["corSum"]

        budRep.budgetFirstQuarter = params["corQuarter1"]
        budRep.budgetSecondQuarter = params["corQuarter2"]
        budRep.budgetThirdQuarter = params["corQuarter3"]
        budRep.budgetFourthQuarter = params["corQuarter4"]

        budRep.budgetJanMoney = params["corMonth1"]
        budRep.budgetFebMoney = params["corMonth2"]
        budRep.budgetMarMoney = params["corMonth3"]
        budRep.budgetAprilMoney = params["corMonth4"]
        budRep.budgetMayMoney = params["corMonth5"]
        budRep.budgetJuneMoney = params["corMonth6"]
        budRep.budgetJulyMoney = params["corMonth7"]
        budRep.budgetAugustMoney = params["corMonth8"]
        budRep.budgetSepMoney = params["corMonth9"]
        budRep.budgetOctMoney = params["corMonth10"]
        budRep.budgetNovMoney = params["corMonth11"]
        budRep.budgetDecMoney = params["corMonth12"]
        return budRep
    }
}
