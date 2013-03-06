package com.chd.bx.bxd

class BxZhaoDai {
    String zdNo                              //招待单号
    String bxdNo                             //报销单号
    int zNo                                  //序号
    String zdDate                         //招待--日期
    String zdExpenseType               //招待--费用类型
    double zdOriginalSum              //招待--原币金额
    double zdBxRmbSum                 //招待--报销人民币金额
    String zdBillsExplain              //招待--票据说明
    String zdRemark                     //招待--备注
    static constraints = {
        zdNo nullable: false,maxSize: 40
        bxdNo nullable: false,maxSize: 40
        zNo nullable:false
        zdDate nullable: true,maxSize: 30
        zdExpenseType nullable: true,maxSize: 20
        zdOriginalSum nullable:true
        zdBxRmbSum nullable:true
        zdBillsExplain nullable: true,maxSize: 50
        zdRemark nullable: true,maxSize: 100
    }
    static mapping = {
        table 'TT_BX_ZHAO_DAI'
        id generator:'sequence', params:[sequence:'SEQ_TT_BX_ZHAO_DAI_ID']
    }
}
