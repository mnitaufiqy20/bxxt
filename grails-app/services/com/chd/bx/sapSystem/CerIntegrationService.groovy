package com.chd.bx.sapSystem

import jbpm.SpringUtil
import java.sql.Statement
import java.sql.ResultSet
import com.chd.bx.security.User
import sapSystem.CerIntegration

class CerIntegrationService {

    def cerIntegrationQuery(params) {
        def strSql="SELECT * FROM TI_CER_INTEGRATION"
        def conn = null;
        try {
            org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy ds = SpringUtil.getBean("dataSource");
            conn = ds.getConnection()
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(strSql);
            List<CerIntegration> list = new ArrayList<CerIntegration>()
            while(rs.next()){
                def cerIntegration = new CerIntegration()
                cerIntegration.companyCode= rs.getString("COMPANY_CODE")
                cerIntegration.accYear= rs.getString("ACC_YEAR")
                cerIntegration.cerNo= rs.getString("CER_NO")
                cerIntegration.recNo= rs.getString("REC_NO")
                cerIntegration.mesType= rs.getString("MES_TYPE")
                cerIntegration.mesDes= rs.getString("MES_DES")
//                cerIntegration.empName= rs.getString("EMP_NAME")
                cerIntegration.empName= "zhangsan"
                list.add(cerIntegration)
            }
            if (list != null && list.size() > 0) {
                return list
            }else{
                return null
            }
            stmt.close()
        } catch (Exception e1) {
            e1.printStackTrace()
            return null
        }
    }
}
