package com.chd.bx.systemConfig

import com.chd.bx.security.Menu
import com.chd.bx.security.Role
import com.chd.bx.security.RoleMenu
import com.chd.bx.security.User
import jbpm.SpringUtil
import systemConfig.RightManage

import java.sql.ResultSet
import java.sql.Statement

class RightsManagementService {

    def getAllRoleName(){
        return Role.findAll()
    }
    /**
     *   得到所有子菜单
     * @return   list
     */
    def getMenu(){
        def strSql = "SELECT SM.ID ,SM.MENU_NAME,SMC.CATEGORY_NAME FROM S_MENU SM,S_MENU_CATEGORY SMC WHERE SM.MENU_CATEGORY_ID = SMC.ID ORDER BY SMC.ID,SM.MENU_CATEGORY_ID"
        def conn = null;
        try {
            org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy ds = SpringUtil.getBean("dataSource");
            conn = ds.getConnection()
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(strSql);
            List list = new ArrayList()
            while(rs.next()){
                def rightManage = new RightManage()
                rightManage.setFunctionCode(rs.getString("ID"))
                rightManage.setFunctionName(rs.getString("MENU_NAME"))
                rightManage.setMenuDesc(rs.getString("CATEGORY_NAME"))
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
    def getUserByRoleId(int roleId){
        def strSql = "\n" +
                "SELECT SU.USERNAME FROM S_USER SU,S_USER_ROLE SUR WHERE SU.ID=SUR.USER_ID AND   SUR.ROLE_ID="+roleId
        def conn = null;
        try {
            org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy ds = SpringUtil.getBean("dataSource");
            conn = ds.getConnection()
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(strSql);
            List list = new ArrayList()
            while(rs.next()){
                def user = new User()
                user.setUsername( rs.getString("USERNAME"))
                list.add(user)
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
    def findByFunctionCodeAndRoleCode(String functionCode ,int roleCode){
        def menu=Menu.findById(Integer.parseInt(functionCode))
        return RoleMenu.findByRoleIdAndMenu(roleCode,menu)
    }
    def roleManagementSave(RoleMenu roleMenu) {
        try {
            if (roleMenu.save(flush: true)) {
                println("roleMenu save success.....")
            } else {
                println("roleMenu save error......")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
