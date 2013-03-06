package com.chd.bx.bxd

class BxLoan {
    int lNo                                      //用于排序
    String bxdNo                               //报销单据No
    String loanDate                           //借款--日期
    String loanNo                           //借款--单号
    String loanBillsCurr                   //借款--单据币别
    double loanOriginalSum                //借款--原币金额
    double loanBalance                     //借款--余额
    double loanTheRepayment               //借款--本次还款金额
    String loanRemark                       //借款--备注
    static constraints = {
        lNo nullable:false
        bxdNo nullable: false,maxSize: 40
        loanDate nullable: true,maxSize: 30
        loanNo nullable: true,maxSize:  12
        loanBillsCurr nullable: true,maxSize: 20
        loanOriginalSum nullable:true
        loanBalance  nullable:true
        loanTheRepayment nullable:true
        loanRemark nullable: true,maxSize: 100
    }
    static mapping = {
        table 'TT_BX_LOAN'
        id generator:'sequence', params:[sequence:'SEQ_TT_BX_LOAN_ID']
    }
}
