package com.chd.bx.orgStructure

class OrgStructure {
    String companyCode
    String companyName
    String deptCode
    String deptName
    String orgKey
    String orgName  //组织名称
    String mark
    String sendDate
    String sendPerson
    static constraints = {
        orgKey unique: true,maxSize: 50 ,nullable: false
        orgName  nullable:false
        companyCode  nullable: false,maxSize:50
        companyName nullable: false,maxSize: 50
        deptCode nullable: false,maxSize: 50
        deptName nullable: false,maxSize: 50
        mark nullable: false,maxSize: 50
        sendDate nullable: false,maxSize: 50
        sendPerson nullable: false,maxSize: 50
    }
    static mapping = {
        table 'TT_ORG_STRUCTURE'
        id generator:'sequence', params:[sequence:'SEQ_TT_ORG_STRUCTURE_ID']
    }
}
