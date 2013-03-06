package com.chd.bx.login
/**
 *   用户登录domain
 *   @author 孟敏
 *   @time 2013年1月6日
 */
class UserLogin {
    String userId
    String userName
    String loginName
    String userPwd
    String empNo                 //员工编号
    String telephone            // 电话
    String idNumber             // 身份证号
    String companyNo            //公司代码
    String departmentNo        //部门代码
    String empPosition         //员工职位

    static constraints = {
        userId  unique: true
        userName  nullable: false, minSize: 2
        loginName  nullable: false, minSize: 2
        userPwd   nullable: false, minSize: 6
        empNo  nullable: false, minSize: 20
        telephone  nullable: false, minSize: 11
        idNumber   nullable: false, minSize: 18
        companyNo  nullable: false, minSize: 15
        departmentNo  nullable: false, minSize: 25
        empPosition   nullable: false, minSize: 25
    }
    static mapping = {
        table 'TM_USER_LOGIN'
        id generator:'sequence', params:[sequence:'SEQ_TM_USER_LOGIN_ID']
    }
}
