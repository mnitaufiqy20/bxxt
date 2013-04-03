package com.chd.bx.sapSystem

import com.chd.bx.login.UserLogin
import sapSystem.CerIntegration

class CerIntegrationController {
    def springSecurityService
    def cerIntegrationService
    def cerIntegrationList
    def index() {
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)
        cerIntegrationList = new ArrayList<CerIntegration>()
        render(view: '/cerIntegration/cerIntegration',model: [user:user,cerIntegrationList:cerIntegrationList])
    }
    def cerIntegrationQuery(){
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)
        cerIntegrationList = new ArrayList<CerIntegration>()
        cerIntegrationList = cerIntegrationService.cerIntegrationQuery(params)
        render(view: '/cerIntegration/cerIntegration',model: [user:user,cerIntegrationList:cerIntegrationList])
    }
}
