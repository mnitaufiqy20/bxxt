package com.chd.bx.login;
/**
 *   用户登录service
 *   @author 孟敏
 *   @time 2013年1月6日
 */
class UserLoginService {

    def serviceMethod() {

    }
    def login(String loginName, String userPwd) {
        String strSql = "select * FROM user_login where login_name='"+loginName+"' and user_pwd='"+userPwd+"'"
        List<UserLogin> listCol = UserLogin.find(strSql)
        println("____________________________________________________"+listCol.size())
        if (listCol != null && listCol.size() > 0) {
            println("登录成功" )
            return true
        } else {
            println("登录失败")
            return false
        }
    }
}
