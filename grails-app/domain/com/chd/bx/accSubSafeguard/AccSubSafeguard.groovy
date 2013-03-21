package com.chd.bx.accSubSafeguard

class AccSubSafeguard {
    String appType        //申请单类型
    String costKind       //费用种类
    String costType       //费用类型
    String appSub          //会计科目
    String encoding    //序号
    static constraints = {
        appType nullable: false, maxSize: 15
        costKind nullable: true, maxSize: 15
        costType nullable: true, maxSize: 15
        appSub nullable: false, maxSize: 15
        encoding nullable: false, maxSize: 2
    }
    static mapping = {
        table 'TT_ACC_SUB_SAFEG'
        id generator:'sequence', params:[sequence:'SEQ_TT_ACC_SUB_SAFEG_ID']
    }
}
