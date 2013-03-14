package com.chd.bx.sapSystem

import com.chd.bx.login.UserLogin

class CostCenterImportController {
    def costCenterImportService = new CostCenterImportService()
    def costCenterImportList = new ArrayList<CostCenterImport>()
    def springSecurityService
    def index() {
        redirect(action: "list", params: params)
    }

    def index2() {
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)
        render(view: '/costCenterImport/costCenterImport',model: [user:user])
    }

    def costCenterImport(params) {
        def companyCode = params["companyCode"]
        costCenterImportList = costCenterImportService.costCenterImport(companyCode)
        render(view: '/costCenterImport/costCenterImport',model: [costCenterImportList: costCenterImportList])
    }

}
