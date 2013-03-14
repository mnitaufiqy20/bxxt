package com.chd.bx.sapSystem

import com.chd.bx.login.UserLogin

class AccSubjectImportController {
    def accSubjectImportService = new AccSubjectImportService()
    def accSubjectImportList = new ArrayList<AccSubjectImport>()
    def springSecurityService
    def index() {
        redirect(action: "list", params: params)
    }
    def index2() {
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)
        render(view: '/accSubjectImport/accSubjectImport',model: [user:user])
    }
    def accSubjectImport(params){
        def companyCode = params["companyCode"]
        def subjectNo = params["subjectNo"]
        accSubjectImportList = accSubjectImportService.accSubjectImport(companyCode,subjectNo)
        render(view: '/accSubjectImport/accSubjectImport',model: [accSubjectImportList: accSubjectImportList])
    }
}
