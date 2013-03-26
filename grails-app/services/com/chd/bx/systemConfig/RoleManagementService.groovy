package com.chd.bx.systemConfig

import com.chd.bx.security.Role
import com.chd.bx.security.RoleMenu
import jbpm.SpringUtil
import systemConfig.RoleRight

import java.sql.ResultSet
import java.sql.Statement

/**
 * Created with IntelliJ IDEA.
 * User: MengMin
 * Date: 13-3-13
 * Time: 下午1:51
 * To change this template use File | Settings | File Templates.
 */
class RoleManagementService {


    /**
     *    查询角色列表
     * @param receiptType
     * @param roleCode
     * @param roleName
     * @return list
     */
    def roleMenuList(String receiptType,String roleCode,String roleName){
//        String sql = "from RoleManagement where functionCode = 'FN020003'"
//        List<RoleManagement> list =    RoleManagement.findAll(sql)
//        print(list.size())
        def conn = null;
        String a = receiptType.charAt(0)
        def strSql = "SELECT SM.MENU_CODE,SM.MENU_NAME,SR.ROLE_CODE,SR.AUTHORITY FROM S_MENU SM,S_ROLE SR,S_ROLE_MENU SRM \n" +
                " WHERE SM.ID = SRM.MENU_ID \n" +
                " AND SR.ID = SRM.ROLE_ID \n" +
                " AND SM.MENU_NAME = '"+receiptType+"'" +
                " AND  SR.AUTHORITY LIKE '"+a+"%'"

        if(roleCode != null && roleCode!=""){
            strSql += " AND SR.ROLE_CODE = '"+roleCode+"'"
        }else if (roleName !=null && roleName !=""){
            strSql +=" AND SR.AUTHORITY = '"+roleName+"'"
        }
        try {
            org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy ds = SpringUtil.getBean("dataSource");
            conn = ds.getConnection()
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(strSql);
            List list = new ArrayList()
            while(rs.next()){
                def roleRight = new RoleRight()
                roleRight.setFunctionCode(rs.getString("MENU_CODE"))
                roleRight.setFunctionName(rs.getString("MENU_NAME"))
                roleRight.setRoleCode(rs.getString("ROLE_CODE"))
                roleRight.setRoleName(rs.getString("AUTHORITY"))
                list.add(roleRight)
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
    /**
     * 角色保存
     * @param role
     */
    def roleSave(Role role) {
        try {
            if (role.save(flush: true)) {
                println("role save success.....")
            } else {
                println("role save error......")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
    def queryByRoleCode(String roleCode){
         return Role.findByRoleCode(roleCode)
    }
    /**
     * 查询role根据role表id删除userrole对应表数据
     * @param role
     */
    def queryRoleMenuByRoleCode(long roleId){
        List<RoleMenu> list=RoleMenu.findAllByRoleId(roleId)
        for(int i=0;i<list.size();i++){
            RoleMenu roleMenu = new RoleMenu()
            roleMenu = list.get(i)
            roleMenu.delete()

        }

    }
}
