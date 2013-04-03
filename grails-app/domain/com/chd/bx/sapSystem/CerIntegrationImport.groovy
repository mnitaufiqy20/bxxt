package com.chd.bx.sapSystem

import javax.persistence.Transient

class CerIntegrationImport {
    String companyCode;//公司代码
    String accYear;//会计年度
    String cerNo;//凭证编号
    String recNo;//借款单号
    String mesType;//消息类型
    String mesDes;//消息描述

    static constraints = {
        companyCode nullable: false, maxSize: 4
        accYear nullable: false, maxSize: 4
        cerNo nullable: false, maxSize: 12
        recNo nullable: false, maxSize: 12
        mesType nullable: false, maxSize: 1
        mesDes nullable: false, maxSize: 12
    }
    static mapping = {
        table 'TI_CER_INTEGRATION'
        id generator:'sequence', params:[sequence:'SEQ_TI_CER_INTEGRATION_ID']
    }
}
