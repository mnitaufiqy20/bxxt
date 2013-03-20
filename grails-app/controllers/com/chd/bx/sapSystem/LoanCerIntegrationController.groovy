package com.chd.bx.sapSystem

class LoanCerIntegrationController {
    def loanCerIntegrationService = new LoanCerIntegrationService()

    def index() {
        redirect(action: "list", params: params)
    }

    def loanCerIntegration(){
        render(view: '/loanCerIntegration/loanCerIntegration')
    }
}
