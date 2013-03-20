package com.chd.bx.OrgStructure

import com.chd.bx.orgStructure.OrgStructure
import com.chd.bx.login.UserLogin

class OrgStructureController {
    def orgStructureService
    def springSecurityService

    def index() {
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)
        def companyCode = user.companyNo
        def orgCode =""
        def orgName=""
        List<OrgStructure> list = orgStructureService.queryDeptByCompanyCode(companyCode)
        if (list!=null){
            orgCode =list.get(0).getOrgKey()
            orgName=list.get(0).getOrgName()
        }
        render(view: "/orgStructure/orgStructureList" ,model: [list:list,orgCode:orgCode,orgName:orgName])

    }

}
