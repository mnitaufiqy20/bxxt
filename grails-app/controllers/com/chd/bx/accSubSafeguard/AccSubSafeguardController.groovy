package com.chd.bx.accSubSafeguard

class AccSubSafeguardController {
    def accSubSafeguardService = new AccSubSafeguardService()
    def accSubSafeguardList
    def index() {
        redirect(action: "list", params: params)
    }
    def accSubSafeguard(){
        accSubSafeguardList = new ArrayList<AccSubSafeguard>()
        accSubSafeguardList = AccSubSafeguard.findAll()
        render(view: '/accSubSafeguard/accSubSafeguard',model: [accSubSafeguardList:accSubSafeguardList])
//        render(view: '/accSubSafeguard/accSubSafeguard')
    }
    def accSubSafeguardSave(params){
        accSubSafeguardList = new ArrayList<AccSubSafeguard>()
        accSubSafeguardList = AccSubSafeguard.findAll()
        for (int i=0;i<accSubSafeguardList.size();i++){
            def accSubSafeguard = new AccSubSafeguard()
            accSubSafeguard = accSubSafeguardList.get(i)
            accSubSafeguard.appSub = params["appSub"][i]
            accSubSafeguardService.accSubSafeguardSave(accSubSafeguard)
        }
        accSubSafeguardList = AccSubSafeguard.findAll()
        render(view: '/accSubSafeguard/accSubSafeguard',model: [accSubSafeguardList:accSubSafeguardList])
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
