package com.chd.bx.empInformation

import empInformation.EmpInformation
import com.chd.bx.login.UserLogin

class EmpInformationController {
    def empInformationService = new EmpInformationService()
    def empInformation = new UserLogin()
    def emp_list = new ArrayList<EmpInformation>()
    def index() {
        redirect(action: "list", params: params)
    }

    def empInformation() {
        def userId = getEmpInformation()
        empInformation = new UserLogin()
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
        def empInformation2 = new UserLogin();
        empInformation2 = empInformationService.empInformationQuery(userId)
        if (params["type"]=="1"){
            def telephone = params["telephone"]
            empInformation2.telephone = telephone
        }else if (params["type"]=="2"){
            def pw = params["passwordNew"]
            empInformation2.userPwd = pw
        }
        empInformationService.saveEmpInformation(empInformation2)
        empInformation()
    }
    def editPwEmpInformation(){
        def userId = getEmpInformation()
        empInformation = new UserLogin();
        empInformation = empInformationService.empInformationQuery(userId)
        render(view: '/empInformation/empInformationUpdatePassword',model: [empInformation: empInformation])
    }
    def  getEmpInformation(){
        def user = (UserLogin)session.getAttribute("user")
        def userId = user.id;
        return userId
    }
}
