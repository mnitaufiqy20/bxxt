package com.chd.bx.flowManage

import com.chd.bx.flow.ExmApp
import jbpm.SpringUtil
import java.sql.Statement
import java.sql.ResultSet
import com.chd.bx.security.Role

class FlowConfigService {
    def appRoleQuery(){
        def strSql = "SELECT ID,AUTHORITY, DESCRIPTION, ROLE_CODE FROM S_ROLE \n" +
                " WHERE AUTHORITY NOT IN (SELECT EMP_ROLE FROM TT_EXA_APP ) \n" +
                " AND AUTHORITY LIKE '%申请人%'\n" +
                " ORDER BY ROLE_CODE"
        def conn = null;
        try {
            org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy ds = SpringUtil.getBean("dataSource");
            conn = ds.getConnection()
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(strSql);
            List<Role> list = new ArrayList<Role>()
            while(rs.next()){
                def role = new Role()
                role.id = Integer.parseInt(rs.getString("ID"))
                role.authority = rs.getString("AUTHORITY")
                role.description = rs.getString("DESCRIPTION")
                role.roleCode = rs.getString("ROLE_CODE")
                list.add(role)
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

    def getAppName(String type,String str){
        def strSql = "SELECT USERNAME FROM S_USER \n" +
                " WHERE ID IN (\n" +
                " SELECT USER_ID FROM S_USER_ROLE WHERE ROLE_ID IN (\n" +
                " SELECT ID FROM S_ROLE WHERE AUTHORITY LIKE '%"+type+"%"+str+"%'))\n" +
                " ORDER BY ID"
        def conn = null
        try {
            org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy ds = SpringUtil.getBean("dataSource");
            conn = ds.getConnection()
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(strSql);
           def strName = ""
            while(rs.next()){
                strName += (rs.getString("USERNAME")+",")
//                strName += name+","
            }
            if (strName != "" && strName.length() > 0) {
                return strName
            }else{
                return ""
            }
            stmt.close()
        } catch (Exception e1) {
            e1.printStackTrace()
            return ""
        }
    }

    def exmAppSave(ExmApp exmApp) {
        try {
            if (exmApp.save(flush: true)) {
                println("success")
            } else {
                println("error")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
    def exmAppDelete(ExmApp exmApp){
       exmApp.delete()
    }
}
