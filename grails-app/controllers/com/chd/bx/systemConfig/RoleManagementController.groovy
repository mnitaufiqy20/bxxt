package com.chd.bx.systemConfig

import com.chd.bx.security.Role
import com.chd.bx.security.UserRole
import systemConfig.RoleRight

/**
 * Created with IntelliJ IDEA.
 * User: MengMin
 * Date: 13-3-13
 * Time: 下午1:51
 * To change this template use File | Settings | File Templates.
 */
class RoleManagementController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def roleManagementService
    /**
     *   初始化进入角色列表界面
     * @param params
     * @return
     */
    def index() {
          //所有子菜单
//        List<RoleRight> listMenu = roleManagementService.getMenu()
        String receiptType = params["menu"]
        if (receiptType ==null ||receiptType ==""){
            receiptType = "费用报销单"
        }
        String roleCode = params["roleCode"]
        String roleName = params["roleName"]
        List<RoleRight> list =roleManagementService.roleMenuList(receiptType,roleCode,roleName)

        render(view: '../roleManagement/roleList',model: [list:list,roleCode:roleCode,roleName:roleName])
    }

    def addRoleRight(){
        render(view: '../roleManagement/addRoleRight')
    }
    def deleteRole(){
        String needDel=params["ckString"]  ;
        String[] str =needDel.split(",")
        for (int i;i<str.size();i++){
            Role role = new Role();
            role = roleManagementService.queryByRoleCode(str[i])
            roleManagementService.queryRoleMenuByRoleCode(role.id)

            UserRole.removeAll(role)
            role.delete()
        }
        index()
    }
    /**
     * 新增角色
     *  @param params
     */
    def addRole(){
        Role role = new Role()
        role.roleCode = params["roleCode"]
        role.authority = params["roleName"]
        role.description =  params["roleName"]
        roleManagementService.roleSave(role)
        render(view: '../roleManagement/roleList',model: [list:null,roleCode:"",roleName:""])
    }

}
