package com.chd.bx.sapSystem

class AccSubjectImportController {
    def accSubjectImportService = new AccSubjectImportService()
    def accSubjectImportList = new ArrayList<AccSubjectImport>()
    def index() {
        redirect(action: "list", params: params)
    }
    def index2() {
        render(view: '/accSubjectImport/accSubjectImport')
    }
    def accSubjectImport(params){
        def companyCode = params["companyCode"]
        def subjectNo = params["subjectNo"]
        accSubjectImportList = accSubjectImportService.accSubjectImport(companyCode,subjectNo)
        render(view: '/accSubjectImport/accSubjectImport',model: [accSubjectImportList: accSubjectImportList])
    }
}
