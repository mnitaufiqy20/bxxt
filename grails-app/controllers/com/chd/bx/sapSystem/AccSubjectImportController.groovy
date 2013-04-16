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
        accSubjectImportList = new ArrayList<AccSubjectImport>()
        render(view: '/accSubjectImport/accSubjectImport',model: [user:user,accSubjectImportList: accSubjectImportList])
    }
    def accSubjectImport(params){
        def companyCode = params["companyCode"]
        def subjectNo = params["subjectNo"]
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)
        def list = AccSubjectImport.findAllByCompanyCodeAndSubject(companyCode,subjectNo);
        if (list!=null && list.size()>0){
            accSubjectImportService.delete(companyCode,subjectNo)
        }
        accSubjectImportList = accSubjectImportService.accSubjectImport(companyCode,subjectNo)
        render(view: '/accSubjectImport/accSubjectImport',model: [user:user,accSubjectImportList: accSubjectImportList])
    }
}
