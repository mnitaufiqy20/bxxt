package com.chd.bx.login
/**
 *   用户登录controller
 *   @author 孟敏
 *   @time 2013年1月6日
 */
class UserLoginController {


//    def userLoginService = new UserLoginService();
    /**
     *    用户登录
     * @return
     */
    def login() {
       def user= UserLogin.findByLoginNameAndUserPwd(params["loginName"],params["userPwd"])
        if (user){
            println("登录成功" )
            session.setAttribute("user",user)
//            session.setAttribute("userPwd",params["userPwd"])
            render(view: '/welcome');
        }else{
            println("登录失败")
            flash.message="用户名或密码有误"
            render(view: '/userLogin/login')
        }

    }
    def clearSession(){
        session.invalidate()
        render(view: '/userLogin/login')
    }
}
