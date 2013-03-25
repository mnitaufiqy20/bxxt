package com.chd.bx.flow

class ExmApp {
    String receiptsType;     //流程类型
    String companyNo;         //公司编码
    String empRole;           //角色
    String firstName;         //第一审批人
    String secondName;        //第二审批人
    String thirdName;          //第三审批人
    String fourthName;         //第四审批人
    String fifthName;           //第五审批人
    String postAcc               //过账会计
    String payTeller             //付款出纳

    static constraints = {
        receiptsType  nullable: false, maxSize: 25
        companyNo  nullable: false, maxSize: 25
        empRole  nullable: false, maxSize: 25
        firstName  nullable: false, maxSize: 25
        secondName  nullable: true, maxSize: 25
        thirdName  nullable: true, maxSize: 25
        fourthName  nullable: true, maxSize: 25
        fifthName  nullable: true, maxSize: 25
        postAcc  nullable: true, maxSize: 25
        payTeller  nullable: true, maxSize: 25
    }
    static mapping = {
        table 'TT_EXA_APP'
        id generator:'sequence', params:[sequence:'SEQ_TT_EXA_APP_ID']
        version false
    }
}
