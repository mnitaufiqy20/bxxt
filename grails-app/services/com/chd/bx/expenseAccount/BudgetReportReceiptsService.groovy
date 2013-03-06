package com.chd.bx.expenseAccount

class BudgetReportReceiptsService {
    def budgetReportReceiptsQuery(){
        String strSql = "from BudgetReportReceipts order by id asc "
        List<BudgetReportReceipts> list = BudgetReportReceipts.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list
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
    def budgetDetailQuery(String year , String company,String dept,String  type){
        String strSql = "from BudgetReportReceipts where budget_year = '"+year+"' and budget_company_no = '"+company+"' and budget_department_no='"+dept+"' and budget_cost_type = '"+type+"'  "
        List<BudgetReportReceipts> list = BudgetReportReceipts.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list.get(0)
        }
    }
}
