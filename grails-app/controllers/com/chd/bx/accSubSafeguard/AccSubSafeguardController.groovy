package com.chd.bx.accSubSafeguard

class AccSubSafeguardController {
    def accSubSafeguardService = new AccSubSafeguardService()

    def index() {
        redirect(action: "list", params: params)
    }
    def accSubSafeguard(){
        render(view: '/accSubSafeguard/accSubSafeguard')
    }
    def accSubSafeguardSave(params){
        def accSubSafeguard1 = new AccSubSafeguard()
        accSubSafeguard1 = setAccSubSafeguardLoan(params,accSubSafeguard1)
        accSubSafeguardService.accSubSafeguardSave(accSubSafeguard1)
        render(view: '/accSubSafeguard/accSubSafeguard',model: [accSubSafeguard1:accSubSafeguard1])
    }
    def setAccSubSafeguardLoan(params,AccSubSafeguard accSubSafeguard){
        accSubSafeguard.appType = "LOAN"
        accSubSafeguard.costKind = ""
        accSubSafeguard.costType = ""
        accSubSafeguard.appSub = params["loanAppSub"]
        accSubSafeguard.encoding = "1"
        return accSubSafeguard
    }
}
