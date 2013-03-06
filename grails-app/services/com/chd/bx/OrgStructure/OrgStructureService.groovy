package com.chd.bx.OrgStructure

import com.chd.bx.orgStructure.OrgStructure

class OrgStructureService {

    def serviceMethod() {

    }
    def queryDeptByCompanyCode(String companyCode){
        String strSql=" from OrgStructure where companyCode = '"+companyCode+"' order by id asc"
        List<OrgStructure> list = OrgStructure.findAll(strSql);
        if (list != null && list.size() > 0) {
            return list
        }
    }
}
