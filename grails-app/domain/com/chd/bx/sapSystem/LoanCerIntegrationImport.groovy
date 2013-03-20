package com.chd.bx.sapSystem

class LoanCerIntegrationImport {
    String companyCode;//公司代码
    String loanAccYear;//会计年度
    String loanCerNo;//凭证编号
    String loanNo;//借款单号
    String loanMesType;//消息类型
    String loanMesDes;//消息描述
    static constraints = {
        companyCode nullable: false, maxSize: 4
        loanAccYear nullable: false, maxSize: 4
        loanCerNo nullable: false, maxSize: 12
        loanNo nullable: false, maxSize: 12
        loanMesType nullable: false, maxSize: 1
        loanMesDes nullable: false, maxSize: 12
    }
    static mapping = {
        table 'TI_LOAN_CER_INTEGRATION'
        id generator:'sequence', params:[sequence:'SEQ_TI_LOAN_CER_INTEGRATION_ID']
    }
}
