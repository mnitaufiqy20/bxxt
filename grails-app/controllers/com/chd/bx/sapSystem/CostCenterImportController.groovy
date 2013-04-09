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
        costCenterImportList = CostCenterImport.findAllByCompanyCode(user.companyNo)
        render(view: '/costCenterImport/costCenterImport',model: [user:user,costCenterImportList:costCenterImportList])
    }

    def costCenterImport(params) {
        def companyCode = params["companyCode"]
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)
        def list = CostCenterImport.findAllByCompanyCode(companyCode)
        if (list!=null && list.size()>0){
            costCenterImportService.delete(companyCode)
        }
        costCenterImportList = costCenterImportService.costCenterImport(companyCode)
        render(view: '/costCenterImport/costCenterImport',model: [user:user,costCenterImportList: costCenterImportList])
    }

}
