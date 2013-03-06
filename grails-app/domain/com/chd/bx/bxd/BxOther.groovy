package com.chd.bx.bxd

class BxOther {
    //其他费用
    String otherId                           //其他消费单Id
    int oNo                                   //其他消费单序号---用于排序
    String bxdNo                             //报销单no
    String otherDate                        //其他--日期
    double otherOriginalSum             //其他--原币金额
    double otherBxRmbSum                //其他--报销人民币金额
    String otherBillsExplain             //其他--票据说明
    String otherRemark                    //其他--备注
    static constraints = {
        oNo nullable:false
        bxdNo nullable: false,maxSize: 30
        otherId nullable: false,maxSize: 40
        otherDate nullable: false  ,maxSize: 30
        otherOriginalSum nullable:false
        otherBxRmbSum nullable:false
        otherBillsExplain nullable: true,maxSize: 50
        otherRemark nullable: true,maxSize: 100
    }
    static mapping = {
        table 'TT_BX_OTHER'
        id generator:'sequence', params:[sequence:'SEQ_TT_BX_OTHER_ID']
    }
}
