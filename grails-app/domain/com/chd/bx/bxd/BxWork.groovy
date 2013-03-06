package com.chd.bx.bxd

class BxWork {
    String bxdNo                                        //报销单号
    int bNo                                           //序号
    String bgNo                                     // 办公单号
    String bgDate                                  //办公--日期
    String bgExpenseType                     //办公--费用类型
    double bgOriginalSum                    //办公--原币金额
    double bgBxRmbSum                       //办公--报销人民币金额
    String bgBillsExplain                   //办公--票据说明
    String bgRemark                         //办公--备注
    static constraints = {
        bxdNo nullable: false,maxSize: 40
        bNo nullable:false
        bgNo nullable: false,maxSize: 40
        bgDate nullable: true ,maxSize: 30
        bgExpenseType nullable: true,maxSize: 20
        bgOriginalSum nullable:true
        bgBxRmbSum nullable:true
        bgBillsExplain nullable: true,maxSize: 50
        bgRemark nullable: true,maxSize: 100
    }
    static mapping = {
        table 'TT_BX_WORK'
        id generator:'sequence', params:[sequence:'SEQ_TT_BX_WORK_ID']
    }
}
