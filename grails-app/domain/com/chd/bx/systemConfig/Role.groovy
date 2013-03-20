package com.chd.bx.systemConfig

class Role {
    String createBy
    String createDate
    String roleCode
    String roleName
    String status
    String updateBy
    String updateDate
    static constraints = {
        createBy nullable: true ,maxSize: 20
        createDate   nullable: true  ,  maxSize: 30
        roleCode    nullable: false ,maxSize: 10
        roleName      nullable: false ,maxSize: 50
        status       nullable: false ,maxSize: 2
        updateBy    nullable: true ,maxSize: 20
        updateDate  nullable: true   ,maxSize: 30
    }
    static mapping = {
        table 'TS_ROLES'
        id generator:'sequence', params:[sequence:'SEQ_TS_ROLES_ID']
    }
}
