package com.chd.bx.empInformation

import empInformation.EmpInformation
import com.chd.bx.login.UserLogin

class EmpInformationController {
    def empInformationService = new EmpInformationService()
    def empInformation = new UserLogin()
    def emp_list = new ArrayList<EmpInformation>()
    def springSecurityService
    def index() {
        redirect(action: "list", params: params)
    }

    def empInformation() {
        def userId = getEmpInformation()
        empInformation = new UserLogin()
        emp_list = new ArrayList<EmpInformation>()
        empInformation = empInformationService.empInformationQuery(userId)
        emp_list  = empInformationService.empInformationQuery2(userId)
//        print(emp_list.size())
//        render(view: '/empInformation/empInformation',model: [emp_list: emp_list])
        render(view: '/empInformation/empInformation',model: [empInformation: empInformation,emp_list:emp_list])
    }

    def editEmpInformation(){
        def userId = getEmpInformation()
        empInformation = new UserLogin();
        empInformation = empInformationService.empInformationQuery(userId)
        render(view: '/empInformation/empInformationUpdate',model: [empInformation: empInformation])
    }

    def saveEmpInformation(params){
        def userId = params["empId"]
        def type = params["type"]
        def empInformation2 = new UserLogin();
//        empInformation2 = empInformationService.empInformationQuery(userId)
        empInformation2 = UserLogin.findById(userId)
        if (type.equals("1")){
            def telephone = params["telephone"]
            empInformation2.telephone = telephone
        }else if (type.equals("2")){
            def pw = params["passwordNew"]
            empInformation2.userPwd = pw
        }
        empInformationService.saveEmpInformation(empInformation2)
//        empInformation()
        emp_list  = empInformationService.empInformationQuery2(userId)
        render(view: '/empInformation/empInformation',model: [empInformation: empInformation2,emp_list:emp_list])
    }
    def editPwEmpInformation(){
        def userId = getEmpInformation()
        empInformation = new UserLogin();
        empInformation = empInformationService.empInformationQuery(userId)
        render(view: '/empInformation/empInformationUpdatePassword',model: [empInformation: empInformation])
    }
    def  getEmpInformation(){
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)
        def userId = user.id;
        return userId
    }
}
