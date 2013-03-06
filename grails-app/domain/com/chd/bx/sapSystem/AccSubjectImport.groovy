package com.chd.bx.sapSystem

class AccSubjectImport {
    String companyCode;//公司代码
    String subjectNo    ;//科目编号
    String subjectDes;//科目描述
    static constraints = {
        companyCode nullable: false, maxSize: 20
        subjectNo nullable: false, maxSize: 20
        subjectDes nullable: false, maxSize: 100
    }
    static mapping = {
        table 'TI_ACC_SUBJECT_IMPORT'
        id generator:'sequence', params:[sequence:'SEQ_TI_ACC_SUBJECT_IMPORT_ID']
    }
}