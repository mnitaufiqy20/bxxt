package com.chd.bx.sapSystem

class CostSubjectImport {
    String companyCode;//公司代码
    String subjectNo    ;//科目编号
    String subjectDes;//科目描述
    static constraints = {
        companyCode nullable: false, maxSize: 4
        subjectNo nullable: false, maxSize: 10
        subjectDes nullable: false, maxSize: 35
    }
    static mapping = {
        table 'TI_COST_SUBJECT_IMPORT'
        id generator:'sequence', params:[sequence:'SEQ_TI_COST_SUBJECT_IMPORT_ID']
    }
}
