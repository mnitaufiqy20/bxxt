package com.chd.bx.sapSystem

class BxCerIntegrationImport {
    String companyCode;//公司代码
    String bxAccYear;//会计年度
    String bxCerNo;//凭证编号
    String bxNo;//报销单号
    String bxMesType;//消息类型
    String bxMesDes;//消息描述
    static constraints = {
        companyCode nullable: false, maxSize: 4
        bxAccYear nullable: false, maxSize: 4
        bxCerNo nullable: false, maxSize: 12
        bxNo nullable: false, maxSize: 12
        bxMesType nullable: false, maxSize: 1
        bxMesDes nullable: false, maxSize: 12
    }
    static mapping = {
        table 'TI_BX_CER_INTEGRATION'
        id generator:'sequence', params:[sequence:'SEQ_TI_BX_CER_INTEGRATION_ID']
    }
}
