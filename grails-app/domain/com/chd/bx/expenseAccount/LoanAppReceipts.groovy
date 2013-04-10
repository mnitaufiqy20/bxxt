package com.chd.bx.expenseAccount

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-8
 * Time: 上午10:20
 * To change this template use File | Settings | File Templates.
 */
class LoanAppReceipts {
    String loanAppReceiptsId            //单号                                是
    String loanEmpNo                     //借款员工编号                        是
    String loanCostCenter               //成本中心                            是
    String loanCompanyNo                //公司代码                            是
    String loanDepartmentNo            //部门代码                            是
    String loanEmpName                  //借款员工姓名                        是
    String loanEmpPhone                 //借款员工电话                        是
    String loanEmpPosition             //借款员工职位                        是
    String loanEmpIdNumber             // 身份证号                           是
    String loanBudgetCenter            //预算中心                            是
    String loanParagraphDate              //需款日期                            是
    String loanPaymentType             //付款方式                            是
    double loanMoney                    //借款金额                            是
    double loanAlreadyRefund           //已还金额                            是
    double loanBalance                  //借款余额                            是
    String loanBegDate                    //申请日期                            是
    String loanOperatorName            //经办人姓名                          是
    String loanPurpose                  //用途                                是
    String loanStatus                   //状态                                是
    String loanRemark                   //备注                                否

    static constraints = {
        loanAppReceiptsId nullable: false, maxSize: 15, unique: true
        loanEmpNo nullable: false, maxSize: 20
        loanCostCenter nullable: false, maxSize: 20
        loanCompanyNo nullable: false, maxSize: 15
        loanDepartmentNo nullable: false, maxSize: 50
        loanEmpName nullable: false, maxSize: 12
        loanEmpPhone nullable: false, maxSize: 22
        loanEmpPosition nullable: false, maxSize: 25
        loanEmpIdNumber nullable: false, maxSize: 18
        loanBudgetCenter nullable: false, maxSize: 20
        loanParagraphDate nullable: false, maxSize: 20
        loanPaymentType nullable: false, maxSize: 20
        loanMoney nullable: false, maxSize: 13
        loanAlreadyRefund nullable: false, maxSize: 13
        loanBalance nullable: false, maxSize: 13
        loanBegDate nullable: false, maxSize: 20
        loanOperatorName nullable: false, maxSize: 12
        loanPurpose nullable: false, maxSize: 100
        loanStatus nullable: false, maxSize: 20
        loanRemark nullable: true, maxSize: 100
    }
    static mapping = {
        table 'TT_LOAN_APP_RECEIPTS'
        id generator:'sequence', params:[sequence:'SEQ_TT_LOAN_APP_RECEIPTS_ID']
    }
}
