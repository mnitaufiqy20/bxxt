package com.chd.bx.systemConfig

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
        def strSql = "select TRR.ID,TF.FUNCTION_NAME,TR.ROLE_CODE,TR.ROLE_NAME from TS_FUNCTION TF,TS_ROLES TR,TS_ROLES_RIGHT TRR \n" +
                "  WHERE TRR.FUNCTION_CODE = TF.FUNCTION_CODE AND TRR.ROLE_CODE = TR.ROLE_CODE AND TF.FUNCTION_NAME ='"+receiptType+"'"
        def conn = null;
        if(roleCode != null && roleCode!=""){
            strSql += " AND TR.ROLE_CODE = '"+roleCode+"'"
        }else if (roleName !=null && roleName !=""){
            strSql +=" AND TR.ROLE_NAME = '"+roleName+"'"
        }
        try {
            org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy ds = SpringUtil.getBean("dataSource");
            conn = ds.getConnection()
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(strSql);
            List list = new ArrayList()
            while(rs.next()){
                def roleRight = new RoleRight()
                roleRight.setId(Integer.parseInt(rs.getString("ID")))
                roleRight.setFunctionName(rs.getString("FUNCTION_NAME"))
                roleRight.setRoleCode(rs.getString("ROLE_CODE"))
                roleRight.setRoleName(rs.getString("ROLE_NAME"))
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
}
