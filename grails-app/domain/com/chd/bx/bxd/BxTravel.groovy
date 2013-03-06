package com.chd.bx.bxd

class BxTravel {
    String bxdNo                       //报销单号
    String clNo                        //差旅单号
    int cNo                         //差旅单--序号
    String clTravelDetails        //差旅--出差事由，明细
    int clTravelDays            //差旅--出差天数
    int clTravelDaysCount            //差旅--出差总天数
    String clStartDate              //差旅--出发日期
    String clEndDate                //差旅--到达日期
    String clSeAddress            //差旅--起止地点
    String clExpenseType          //差旅--费用类型
    String clTransport            //差旅--交通工具
    double clOriginalSum            //差旅--原币金额
    double clBxRmbSum               //差旅--报销人民币金额
    String clRemark                 //差旅--备注
    static constraints = {
        bxdNo nullable: false,maxSize: 40
        clNo nullable: false,maxSize: 40
        cNo nullable:false
        clTravelDaysCount nullable:false
        clTravelDetails nullable: true,maxSize: 50
        clTravelDays nullable:true
        clStartDate nullable: true ,maxSize: 30
        clEndDate nullable: true    ,maxSize: 30
        clSeAddress nullable: true,maxSize: 20
        clExpenseType nullable: true,maxSize: 20
        clTransport nullable: true,maxSize: 20
        clOriginalSum nullable: true
        clBxRmbSum nullable:true
        clRemark nullable: true,maxSize: 100
    }
    static mapping = {
        table 'TT_BX_TRAVEL'
        id generator:'sequence', params:[sequence:'SEQ_TT_BX_TRAVEL_ID']
    }
}
