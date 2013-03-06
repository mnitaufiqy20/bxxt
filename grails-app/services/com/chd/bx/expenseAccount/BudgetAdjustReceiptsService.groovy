package com.chd.bx.expenseAccount

class BudgetAdjustReceiptsService {

    def budgetAdjustReceiptsQuery(){
        String strSql = "from BudgetReportReceipts where budgetCostType='差旅费' and budgetYear='2013' order by id asc "
        List<BudgetReportReceipts> list = BudgetReportReceipts.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list.get(0)
        }
    }
    def budgetAdjustReceiptsTQuery(){
        String strSql = "from BudgetReportReceiptsTemp where budgetCostType='差旅费' and budgetYear='2013' order by id asc "
        List<BudgetReportReceiptsTemp> list = BudgetReportReceiptsTemp.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list.get(0)
        }
    }
    def budgetAdjustReceiptsQueryByType(String budgetCostType,String budgetYear){
        String strSql = "from BudgetReportReceipts where budgetCostType='"+budgetCostType+"' and budgetYear='"+budgetYear+"' order by id asc "
        List<BudgetReportReceipts> list = BudgetReportReceipts.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list.get(0)
        }
    }
    def budgetAdjustReceiptsTQueryByType(String budgetCostType,String budgetYear){
        String strSql = "from BudgetReportReceiptsTemp where budgetCostType='"+budgetCostType+"' and budgetYear='"+budgetYear+"' order by id asc "
        List<BudgetReportReceiptsTemp> list = BudgetReportReceiptsTemp.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list.get(0)
        }
    }
    def budgetAdjustReceiptsQueryById(id,type){
        String strSql = "from BudgetReportReceipts where budgetReportReceiptsId='"+id+"' and budgetCostType='"+type+"'"
        List<BudgetReportReceipts> list = BudgetReportReceipts.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list.get(0)
        }
    }

    def budgetAdjustReceiptsTQueryById(id,type){
        String strSql = "from BudgetReportReceiptsTemp where budgetReportReceiptsId='"+id+"' and budgetCostType='"+type+"'"
        List<BudgetReportReceiptsTemp> list = BudgetReportReceiptsTemp.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list.get(0)
        }
    }

    def budgetReportReceiptsSave(BudgetReportReceipts budgetReportReceipts) {
        try {
            if (budgetReportReceipts.save(flush: true)) {
                println("success")
            } else {
                println("error")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
    def budgetReportReceiptsTSave(BudgetReportReceiptsTemp budgetReportReceiptsTemp) {
        try {
            if (budgetReportReceiptsTemp.save(flush: true)) {
                println("success")
            } else {
                println("error")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
