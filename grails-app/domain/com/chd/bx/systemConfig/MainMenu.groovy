package com.chd.bx.systemConfig

class MainMenu {
    String createBy
    Date createDate
    String menuCode
    String menuDesc
    String status
    String updateBy
    Date updateDate
    static constraints = {
        createBy   nullable: false,maxSize: 20
        createDate           nullable: false
        menuCode             nullable: false,maxSize: 15
        menuDesc            nullable: false,maxSize: 50
        status               nullable: false,maxSize: 2
        updateBy            nullable: true,maxSize: 20
    }
    static mapping = {
        table 'TS_MENU'
        id generator:'sequence', params:[sequence:'SEQ_TS_MENU_ID']
    }
}
