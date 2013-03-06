package com.chd.bx.bxd
/**
 *   用户登录service
 *   @author 孟敏
 *   @time 2013年1月6日
 */
class BxReceipt {
    String bxNo                 //报销单据号
    int bxEmpNo              //报销员工编号
    String costCenter           //成本中心
    String companyName          //公司名称
    String bxEmpName            //报销人姓名
    String bxEmpPhoneNumber        //报销员工电话
    String bxEmpPosition        //报销员工职位
    String budgetCenter         //预算中心
    String needMoneyDate          //需款日期
    String paymentMode          //付款方式
    String applicationDate        //申请日期
    String managerName          // 经办人姓名
    String billsCurr            //票据币别
    //差旅
//    String clTravelDetails        //差旅--出差事由，明细
//    int clTravelDays            //差旅--出差天数
//    String clStartDate              //差旅--出发日期
//    String clEndDate                //差旅--到达日期
//    String clSeAddress            //差旅--起止地点
//    String clExpenseType          //差旅--费用类型
//    String clTransport            //差旅--交通工具
//    double clOriginalSum            //差旅--原币金额
//    double clBxRmbSum               //差旅--报销人民币金额
//    String clRemark                 //差旅--备注
    //招待
//    String zdDate                         //招待--日期
//    String zdExpenseType               //招待--费用类型
//    double zdOriginalSum              //招待--原币金额
//    double zdBxRmbSum                 //招待--报销人民币金额
//    String zdBillsExplain              //招待--票据说明
//    String zdRemark                     //招待--备注
    // 办公
//    String bgDate                                  //办公--日期
//    String bgExpenseType                     //办公--费用类型
//    double bgOriginalSum                    //办公--原币金额
//    double bgBxRmbSum                       //办公--报销人民币金额
//    String bgBillsExplain                   //办公--票据说明
//    String bgRemark                         //办公--备注
    //其他费用
//    List<BxOther> otherList
//    String otherDate                        //其他--日期
//    double otherOriginalSum             //其他--原币金额
//    double otherBxRmbSum                //其他--报销人民币金额
//    String otherBillsExplain             //其他--票据说明
//    String otherRemark                    //其他--备注
    //
    double bxCostCounts                      //报销金额合计
    //借款
//    String loanDate                           //借款--日期
//    String loanNo                           //借款--单号
//    String loanBillsCurr                   //借款--单据币别
//    double loanOriginalSum                //借款--原币金额
//    double loanBalance                     //借款--余额
//    double loanTheRepayment               //借款--本次还款金额
//    String loanRemark                       //借款--备注

    double payCounts                       //实付金额合计
    int billsCounts                        //单据总数
    String expenseCategory                 //费用种类
    int budgetYear                           // 预算年度
    int budgetMonth                          // 预算月份
    int budgetQuarter                        //预算季度
    double balanceBudgetYear                // 年度预算余额
    double balanceBudgetMonth               //月度预算余额
    double balanceBudgetQuarter            //季度预算余额
    double   budgetYearPro                  //年度预算完成比例
    double   budgetMonthPro                //月度预算完成比例
    double   budgetQuarterPro               //季度预算完成比例
    String approvalTime                     //审批时间
    String approvalOpinions                //审批意见
    String approverPosition                //审批人职位
    String approver                          //审批人
    String approvalStatus                    //审批状态
    String bxdStatus                           //报销单状态

    static constraints = {
        bxNo unique: true,maxSize: 12
        bxEmpNo nullable: false
        costCenter nullable: false,maxSize: 20
        companyName nullable: false,maxSize: 45
        bxEmpName nullable: false,maxSize: 22
        bxEmpPhoneNumber nullable:false,maxSize:22
        bxEmpPosition nullable: false,maxSize: 35
        budgetCenter nullable: false,maxSize: 30
        needMoneyDate nullable: false ,maxSize: 30
        paymentMode nullable: false,maxSize: 30
        applicationDate nullable: false ,maxSize: 30
        managerName nullable: false,maxSize: 22
        billsCurr nullable: false,maxSize: 20
        bxdStatus  nullable: false,maxSize: 20
//        clTravelDetails nullable: true,maxSize: 50
//        clTravelDays nullable:true
//        clStartDate nullable: true ,maxSize: 30
//        clEndDate nullable: true    ,maxSize: 30
//        clSeAddress nullable: true,maxSize: 20
//        clExpenseType nullable: true,maxSize: 20
//        clTransport nullable: true,maxSize: 20
//        clOriginalSum nullable: true
//        clBxRmbSum nullable:true
//        clRemark nullable: true,maxSize: 100
//        zdDate nullable: true,maxSize: 30
//        zdExpenseType nullable: true,maxSize: 20
//        zdOriginalSum nullable:true
//        zdBxRmbSum nullable:true
//        zdBillsExplain nullable: true,maxSize: 50
//        zdRemark nullable: true,maxSize: 100
//        bgDate nullable: true ,maxSize: 30
//        bgExpenseType nullable: true,maxSize: 20
//        bgOriginalSum nullable:true
//        bgBxRmbSum nullable:true
//        bgBillsExplain nullable: true,maxSize: 50
//        bgRemark nullable: true,maxSize: 100
//        otherDate nullable: true  ,maxSize: 30
//        otherOriginalSum nullable:true
//        otherBxRmbSum nullable:true
//        otherBillsExplain nullable: true,maxSize: 50
//        otherRemark nullable: true,maxSize: 100
        bxCostCounts nullable:true
//        loanDate nullable: true,maxSize: 30
//        loanNo nullable: true,maxSize:  12
//        loanBillsCurr nullable: true,maxSize: 20
//        loanOriginalSum nullable:true
//        loanBalance  nullable:true
//        loanTheRepayment nullable:true
//        loanRemark nullable: true,maxSize: 100
        payCounts nullable:false
        billsCounts nullable:false
        expenseCategory nullable: false,maxSize: 20
        budgetYear  nullable: false
        budgetMonth nullable: false
        budgetQuarter nullable: false
        balanceBudgetYear nullable: false
        balanceBudgetMonth nullable: false
        balanceBudgetQuarter nullable: false
        budgetYearPro nullable: false
        budgetMonthPro nullable: false
        budgetQuarterPro nullable: false
        approvalTime nullable: true,maxSize: 30
        approvalOpinions nullable: true,maxSize: 10
        approverPosition  nullable: true,maxSize: 30
        approver nullable: true,maxSize: 10
        approvalStatus nullable: true,maxSize: 20
    }
    static mapping = {
        table 'TT_BX_RECEIPT'
        id generator:'sequence', params:[sequence:'SEQ_TT_BX_RECEIPT_ID']
    }
}
