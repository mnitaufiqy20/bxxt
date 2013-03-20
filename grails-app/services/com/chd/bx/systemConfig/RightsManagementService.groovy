package com.chd.bx.systemConfig

import jbpm.SpringUtil
import systemConfig.RightManage

import java.sql.ResultSet
import java.sql.Statement
import com.chd.bx.login.UserLogin

class RightsManagementService {

    def getAllRoleName(){
        return Role.findAll()
    }
    /**
     *   得到所有子菜单
     * @return   list
     */
    def getMenu(){
        def strSql = "SELECT TF2.FUNCTION_CODE,TF2.FUNCTION_NAME,TM.MENU_DESC FROM TS_FUNCTION TF2,TS_MENU TM WHERE TF2.MENU_CODE = TM.MENU_CODE"
        def conn = null;
        try {
            org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy ds = SpringUtil.getBean("dataSource");
            conn = ds.getConnection()
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(strSql);
            List list = new ArrayList()
            while(rs.next()){
                def rightManage = new RightManage()
                rightManage.setFunctionCode( rs.getString("FUNCTION_CODE"))
                rightManage.setFunctionName(rs.getString("FUNCTION_NAME"))
                rightManage.setMenuDesc(rs.getString("MENU_DESC"))
                list.add(rightManage)
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
    def getUserByRoleId(String roleId){
        def strSql = "\n" +
                "SELECT TUL.USER_NAME FROM TM_USER_LOGIN TUL,TT_USER_ROLE TUR WHERE TUL.USER_ID = TUR.USERID AND TUR.ROLE_CODE = '"+roleId+"'"
        def conn = null;
        try {
            org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy ds = SpringUtil.getBean("dataSource");
            conn = ds.getConnection()
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(strSql);
            List list = new ArrayList()
            while(rs.next()){
                def userLogin = new UserLogin()
                userLogin.setUserName( rs.getString("USER_NAME"))
                list.add(userLogin)
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
    def findByFunctionCodeAndRoleCode(String functionCode ,String roleCode){
        return RoleManagement.findByFunctionCodeAndRoleCode(functionCode,roleCode)
    }
    def roleManagementSave(RoleManagement roleManagement) {
        try {
            if (roleManagement.save(flush: true)) {
                println("roleManagement save success.....")
            } else {
                println("roleManagement save error......")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
