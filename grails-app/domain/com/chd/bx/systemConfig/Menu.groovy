package com.chd.bx.systemConfig

class Menu {
//    static hasMany = [roleMenu:RoleManagement]
    String createBy
     Date createDate
    String functionCode
    String functionName
    String functionRight
    String functionUrl
    String menuCode
    String status
    String updateBy
    Date updateDate
    static constraints = {
        createBy            nullable: false ,maxSize: 225
        createDate              nullable: false
        functionCode         nullable: false ,maxSize: 10
        functionName        nullable: false ,maxSize: 50
        functionRight       nullable: false ,maxSize: 20
        functionUrl         nullable: false ,maxSize: 100
        menuCode            nullable: false ,maxSize: 10
        status              nullable: false ,maxSize: 2
        updateBy           nullable: true ,maxSize: 255
        updateDate         nullable: true
    }
    static mapping = {
        table 'TS_FUNCTION'
        id generator:'sequence', params:[sequence:'SEQ_TS_FUNCTION_ID']
    }
}
