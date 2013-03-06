package com.chd.bx.flow
/***
 * 在办箱业务逻辑处理
 */
class Processes {
     String transId    //单号
     String deptCode  //部门编码
     String userId    //提交人
     String  amount   //金额
     String  status   //状态
     String  type     //单据类型  【费用报销单、员工借款单】
     Date  lastUpdateTime //最后修改时间


    static constraints = {
        transId   unique: true
        deptCode  nullable: false, minSize: 25
        userId   nullable: false, minSize: 25
        amount   nullable: false, minSize: 15
        status   nullable: false, minSize: 10
        type     nullable: false,  minSize: 1
        lastUpdateTime nullable:  false
    }
    static mapping = {
        table 'TA_PROCESSES'
        id generator:'sequence', params:[sequence:'SEQ_TA_PROCESSES_ID']
        version false
    }
}
