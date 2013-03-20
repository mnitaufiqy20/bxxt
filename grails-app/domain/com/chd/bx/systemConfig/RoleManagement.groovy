package com.chd.bx.systemConfig

class RoleManagement {
    String functionCode
    String roleCode
    String roleRight
    String createBy
    String createDate
    String updateBy
    String updateDate
//    Role role
    static constraints = {
          functionCode    nullable:false         ,  maxSize: 10
          roleCode        nullable:false       ,  maxSize: 10
          roleRight       nullable:false       ,  maxSize: 10
          createBy        nullable:true      ,  maxSize: 30
          createDate     nullable:true       ,  maxSize: 30
          updateBy        nullable:true  ,  maxSize: 30
          updateDate     nullable:true      ,  maxSize: 30
    }
    static mapping = {
        table 'TS_ROLES_RIGHT'
        id generator:'sequence', params:[sequence:'SEQ_TS_ROLES_RIGHT_ID']

    }
}
