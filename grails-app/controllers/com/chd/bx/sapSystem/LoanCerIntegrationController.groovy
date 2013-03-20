package com.chd.bx.sapSystem

import com.chd.bx.accSubSafeguard.AccSubSafeguard

class LoanCerIntegrationController {
    def loanCerIntegrationService = new LoanCerIntegrationService()

    def index() {
        redirect(action: "list", params: params)
    }

    def loanCerIntegration(params){
        def type = params["type"]
        def accSubSafeguard = new AccSubSafeguard()
        if (type.equase("loan")){
            accSubSafeguard =  loanCerIntegrationService.getAccSubSafeguardLoan(type)
        }
        render(view: '/loanCerIntegration/loanCerIntegration')
    }
}
