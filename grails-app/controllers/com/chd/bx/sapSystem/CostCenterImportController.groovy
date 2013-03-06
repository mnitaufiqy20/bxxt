package com.chd.bx.sapSystem

class CostCenterImportController {
    def costCenterImportService = new CostCenterImportService()
    def costCenterImportList = new ArrayList<CostCenterImport>()
    def index() {
        redirect(action: "list", params: params)
    }

    def index2() {
        render(view: '/costCenterImport/costCenterImport')
    }

    def costCenterImport(params) {
        def companyCode = params["companyCode"]
        costCenterImportList = costCenterImportService.costCenterImport(companyCode)
        render(view: '/costCenterImport/costCenterImport',model: [costCenterImportList: costCenterImportList])
    }

}
