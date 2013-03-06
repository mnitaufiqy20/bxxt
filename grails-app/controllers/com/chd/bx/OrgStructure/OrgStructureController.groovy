package com.chd.bx.OrgStructure

import com.chd.bx.orgStructure.OrgStructure

class OrgStructureController {
    def orgStructureService
    def index() {
        String companyCode="C1122"
        String companyName=""
        List<OrgStructure> list = orgStructureService.queryDeptByCompanyCode(companyCode)
        if (list!=null){
            companyCode=list.get(0).companyCode
            companyName =  list.get(0).companyName
        }
        render(view: "/orgStructure/orgStructureList" ,model: [list:list,companyCode:companyCode,companyName:companyName])

    }

}
