package com.chd.bx.expenseAccount

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-21
 * Time: 上午10:20
 * To change this template use File | Settings | File Templates.
 */
class BudgetReportReceipts {
    String budgetReportReceiptsId            //单号                                是
    String budgetReportEmpNo                 //申报员工编号                        是
    String budgetReportEmpName                  //申报员工姓名                        是
    String budgetReportEmpPosition             //申报员工职位                        是
    String budgetReportDate                  //申请日期                        是
    String budgetCompanyNo                //公司代码                            是
    String budgetDepartmentNo            //部门代码                            是
    String budgetCostCenter               //成本中心                            是
    String budgetCenter                    //预算中心                            是
    String budgetYear                      //预算年份                            是
    String budgetCostType                 //费用种类                            是
    String budgetTypeTotal                //类型合计
    String budgetFirstQuarter            //一季度
    String budgetSecondQuarter            //二季度
    String budgetThirdQuarter            //三季度
    String budgetFourthQuarter            //四季度
    String budgetJanMoney                   //一月
    String budgetFebMoney                       //二月
    String budgetMarMoney                       //三月
    String budgetAprilMoney                    //四月
    String budgetMayMoney                       //五月
    String budgetJuneMoney                       //六月
    String budgetJulyMoney                       //七月
    String budgetAugustMoney                     //八月
    String budgetSepMoney                       //九月
    String budgetOctMoney                       //十月
    String budgetNovMoney                       //十一月
    String budgetDecMoney                       //十二月

//    Double budgetJanMoney                   //一月
//    Double budgetFebMoney                       //二月
//    Double budgetMarMoney                       //三月
//    Double budgetAprilMoney                    //四月
//    Double budgetMayMoney                       //五月
//    Double budgetJuneMoney                       //六月
//    Double budgetJulyMoney                       //七月
//    Double budgetAugustMoney                     //八月
//    Double budgetSepMoney                       //九月
//    Double budgetOctMoney                       //十月
//    Double budgetNovMoney                       //十一月
//    Double budgetDecMoney                       //十二月

    static constraints = {
        budgetReportReceiptsId nullable: false, maxSize: 12
        budgetReportEmpNo nullable: false, maxSize: 20
        budgetReportEmpName nullable: false, maxSize: 12
        budgetReportEmpPosition nullable: false, maxSize: 25
        budgetCompanyNo nullable: false, maxSize: 15
        budgetDepartmentNo nullable: false, maxSize: 25
        budgetYear nullable: false, maxSize: 8
        budgetCostCenter nullable: false, maxSize: 10
        budgetCenter nullable: false, maxSize: 10
        budgetReportDate nullable: false, maxSize: 20
        budgetCostType nullable: false, maxSize: 20
        budgetTypeTotal nullable: false, maxSize: 20
        budgetFirstQuarter nullable: false, maxSize: 20
        budgetSecondQuarter nullable: false, maxSize: 20
        budgetThirdQuarter nullable: false, maxSize: 20
        budgetFourthQuarter nullable: false, maxSize: 20
        budgetJanMoney nullable: false, maxSize: 13
        budgetFebMoney nullable: false, maxSize: 13
        budgetMarMoney nullable: false, maxSize: 13
        budgetAprilMoney nullable: false, maxSize: 13
        budgetMayMoney nullable: false, maxSize: 13
        budgetJuneMoney nullable: false, maxSize: 13
        budgetJulyMoney nullable: false, maxSize: 13
        budgetAugustMoney nullable: false, maxSize: 13
        budgetSepMoney nullable: false, maxSize: 13
        budgetOctMoney nullable: false, maxSize: 13
        budgetNovMoney nullable: false, maxSize: 13
        budgetDecMoney nullable: false, maxSize: 13
    }
    static mapping = {
        table 'TT_BUD_REP_RECE'
        id generator:'sequence', params:[sequence:'SEQ_TT_BUD_REP_RECE_ID']
    }
}
