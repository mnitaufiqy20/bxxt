package com.chd.bx.systemConfig

import com.chd.bx.security.Role
import com.chd.bx.security.User
import com.chd.bx.security.UserRole
import jbpm.SpringUtil
import java.sql.Statement
import java.sql.ResultSet

class UserRoleService {

    def getAllRoleName(){
        return Role.findAll()
    }
    def getUserRoleByRoleId(int roleId){
        return UserRole.findAllByRole(Role.findById(roleId))
    }
    def deleteUserRole(int userId,int roleId){
         UserRole userRole = new UserRole()
        userRole = UserRole.findByRoleAndUser(Role.findById(roleId),User.findById(userId))
        userRole.delete()
    }
    def getUser(String userId){
        def strSql=""
        if(userId==null||userId==""){
             strSql = "SELECT ID,EMP_NO,NAME FROM S_USER"
        } else{
             strSql = "SELECT ID,EMP_NO,NAME FROM S_USER  WHERE ID not in ("+userId+")"
        }

        def conn = null;
        try {
            org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy ds = SpringUtil.getBean("dataSource");
            conn = ds.getConnection()
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(strSql);
            List list = new ArrayList()
            while(rs.next()){
                def user = new User()
                user.name= rs.getString("NAME")
                user.id = Integer.parseInt(rs.getString("ID"))
                user.empNo = rs.getString("EMP_NO")
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
    def userRoleSave(UserRole userRole) {
        try {
            if (userRole.save(flush: true)) {
                println("userRole save success.....")
            } else {
                println("userRole save error......")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
