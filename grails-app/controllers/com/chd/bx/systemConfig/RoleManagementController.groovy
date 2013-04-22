package com.chd.bx.systemConfig

import com.chd.bx.security.Role
import com.chd.bx.security.UserRole
import systemConfig.RoleRight
import com.chd.bx.security.Menu
import com.chd.bx.security.User
import com.chd.bx.security.RoleMenu

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
    def springSecurityService
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

        String currentUserName = springSecurityService.getPrincipal().username;
        def funcCode = params["funcCode"]
        def str = getLimitsStr(currentUserName,funcCode)
        def a = ""
        def b = ""
        def c = ""
        if (str.indexOf("V")!=-1){
            a = "V"
        }
        if(str.indexOf("N")!=-1){
            b = "N"
        }
        if(str.indexOf("D")!=-1){
            c = "D"
        }
        render(view: '../roleManagement/roleList',model: [list:list,receiptType:receiptType,funcCode:funcCode,roleCode:roleCode,roleName:roleName,a:a,b:b,c:c])
    }

    /**
     * 得到当前用户所有权限的字符串
     */
    def getLimitsStr(String currentUserName,String funcCode){
        def strRoleRight = ""
        def menu = Menu.findByMenuCode(funcCode)
        def u = User.findByUsername(currentUserName)
        def userRoleList = UserRole.findAllByUser(u)
        def role = new Role()
        for (UserRole userRole:userRoleList){
            def role1 = new Role()
            role1 = userRole.role
            def str = role1.description.substring(0,1)
            if (!role1.description.equals("PT") && !role1.description.equals("KJ")
                    && !role1.description.equals("CN") && (str.equals("J") || str.equals("B"))) {
                def roleMenu = RoleMenu.findByRoleIdAndMenu(role1.id,menu)
                if (roleMenu!=null && roleMenu.roleRight!=""){
                    strRoleRight += roleMenu.roleRight
                }
            }
        }
        return strRoleRight
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
