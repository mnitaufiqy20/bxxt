package com.chd.bx.security

class User {

    transient springSecurityService

    String username             //登录用户名
    String password             //登录口令
    String name                   //用户姓名
    String empNo                //员工编号
    String telephone           // 电话
    String idNumber            // 身份证号
    String companyNo           //公司代码
    String departmentNo       //部门代码
    String empPosition        //员工职位
    String userId              //用户Id
    Role role                  //用户角色

    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static constraints = {
        username blank: false, unique: true
        password blank: false
        name  nullable: false
        empNo  nullable: false
        telephone  nullable: false
        idNumber   nullable: false
        companyNo  nullable: false
        departmentNo  nullable: false
        userId nullable: false
        empPosition   nullable: false
    }

    static mapping = {
        table 'S_USER'
        password column: 'password'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }
}
