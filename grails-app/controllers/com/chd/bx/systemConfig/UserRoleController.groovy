package com.chd.bx.systemConfig

import com.chd.bx.security.Role
import com.chd.bx.security.User
import com.chd.bx.security.UserRole
import systemConfig.UserRoleList
import com.chd.bx.security.Menu
import com.chd.bx.security.RoleMenu

class UserRoleController {
    def userRoleService
    def springSecurityService
    def index() {
        def role = "0"
        def user = "0"
        def listAll = new ArrayList<UserRoleList>()
        def list = userRoleService.getAllRoleName()
        def roleList = Role.findAll();
        def userList = User.findAll();
        for(int i=0;i<list.size();i++){
            def userRoleList = new UserRoleList()
            int roleId = list.get(i).id
            userRoleList.setRoleId(roleId)
            userRoleList.setRoleName(list.get(i).authority)
            def listUser = userRoleService.getUserRoleByRoleId(roleId)
            String userId=""
            String userName=""
            if (listUser!=null){
                if (listUser.size()>5){
                    for (int h=0;h<5;h++){
                        def userRole=listUser.get(h)
                        int uId=userRole.user.id
                        String uName=userRole.user.username
                        if (h==4){
                            userId = userId+uId
                            userName = userName+uName+",......"
                        }else{
                            userId = userId+uId+","
                            userName = userName+uName+","
                        }
                    }
                }else{
                    for (int h=0;h<listUser.size();h++){
                        def userRole=listUser.get(h)
                        int uId=userRole.user.id
                        String uName=userRole.user.username
                        if (listUser.size()-1==h){
                            userId = userId+uId
                            userName = userName+uName
                        }else{
                            userId = userId+uId+","
                            userName = userName+uName+","
                        }
                    }
                }
                userRoleList.setUserId(userId)
                userRoleList.setUserName(userName)
            }
            listAll.add(userRoleList)
        }

        String currentUserName = springSecurityService.getPrincipal().username;
        def funcCode = params["funcCode"]
        def str = getLimitsStr(currentUserName,funcCode)
        def a = ""
        if (str.indexOf("V")!=-1){
            a = "V"
        }
        render(view:'../userRole/userRole',model: [list:listAll,roleList:roleList,funcCode:funcCode,a:a,userList:userList,roleId:role,userId:user])
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

    def queryUserRole(){
        def userRoleLists
        def role
        def user
        def roleId = request.getParameter("role")
        def userId = request.getParameter("user")

        if (roleId=="0" && userId=="0"){
            userRoleLists = UserRole.findAll()
        }else if (roleId=="0" && userId!="0"){
            user = User.findById(userId)
            userRoleLists = UserRole.findAllByUser(user)
        }else if (roleId!="0" && userId=="0"){
            role = Role.findById(roleId)
            userRoleLists = UserRole.findAllByRole(role)
        } else{
            role = Role.findById(roleId)
            user = User.findById(userId)
            userRoleLists = UserRole.findAllByUserAndRole(user,role)
        }

        def roleList = Role.findAll();
        def userList = User.findAll();

        def list = new ArrayList<UserRoleList>()
        if (userRoleLists!=null || userRoleLists!=""){
            for (int i=0;i<userRoleLists.size();i++){
                 def userRoleList = new UserRoleList()
                userRoleList.roleName = userRoleLists.get(i).role.authority
                userRoleList.roleId = userRoleLists.get(i).role.id
                userRoleList.userName = userRoleLists.get(i).user.username
                userRoleList.userId = userRoleLists.get(i).user.userId
                list.add(userRoleList)
            }
        }
        String currentUserName = springSecurityService.getPrincipal().username;
        def funcCode = params["funcCode"]
        def str = getLimitsStr(currentUserName,funcCode)
        def a = ""
        if (str.indexOf("V")!=-1){
            a = "V"
        }
        render(view:'../userRole/userRole',model: [list:list,roleList:roleList,funcCode:funcCode,a:a,userList:userList,roleId:roleId,userId:userId])
    }
    /**
     * 查看详细--该角色下的所有用户
     * @return
     */
    def edit(){
        def roleName=request.getParameter("roleName")
        def roleId=request.getParameter("roleId")
        def list = userRoleService.getUserRoleByRoleId(Integer.parseInt(roleId))
        String currentUserName = springSecurityService.getPrincipal().username;
        def funcCode = params["funcCode"]
        def str = getLimitsStr(currentUserName,funcCode)
        def a = ""
        def b = ""
        if (str.indexOf("N")!=-1){
            a = "N"
        }
        if (str.indexOf("D")!=-1){
            b = "D"
        }
        render(view: '../userRole/editUserRole',model: [roleName:roleName,list: list,roleId:roleId,funcCode:funcCode,a:a,b:b])
    }
    def deleteUserRole(){
        def roleName=request.getParameter("roleName")
        def userId=request.getParameter("userId")
        def roleId=request.getParameter("roleId")
        userRoleService.deleteUserRole(Integer.parseInt(userId),Integer.parseInt(roleId))
        def list = userRoleService.getUserRoleByRoleId(Integer.parseInt(roleId))
        String currentUserName = springSecurityService.getPrincipal().username;
        def funcCode = params["funcCode"]
        def str = getLimitsStr(currentUserName,funcCode)
        def a = ""
        def b = ""
        if (str.indexOf("N")!=-1){
            a = "N"
        }
        if (str.indexOf("D")!=-1){
            b = "D"
        }
        render(view: '../userRole/editUserRole',model: [roleName:roleName,list: list,roleId:roleId,funcCode:funcCode,a:a,b:b])
    }
    def getUser(){
        String rId=params["roleId"]
        String roleName=params["roleName"]
        int roleId=Integer.parseInt(rId)
        def listUser = userRoleService.getUserRoleByRoleId(roleId)
        String userId=""
        String userName=""
        if (listUser!=null){
            for (int h=0;h<listUser.size();h++){
                def userRole=listUser.get(h)
                int uId=userRole.user.id
                String uName=userRole.user.username
                if (listUser.size()-1==h){
                    userId = userId+uId
                    userName = userName+uName
                }else{
                    userId = userId+uId+","
                    userName = userName+uName+","
                }
            }
        }
        List<User> userList = userRoleService.getUser(userId)

        String currentUserName = springSecurityService.getPrincipal().username;
        def funcCode = params["funcCode"]
        def str = getLimitsStr(currentUserName,funcCode)
        def a = ""
        if (str.indexOf("N")!=-1){
            a = "N"
        }
        render(view: '../userRole/addUser',model: [userList:userList,roleName:roleName,roleId:roleId,funcCode:funcCode,a: a])
    }
    def saveUserRole(){
        String roleName = params["roleName"]
        String userStr=params["ckString"]
        String rId = params["roleId"]
        String[] str =userStr.split(",")
        for (int i;i<str.size();i++){
            UserRole userRole = new UserRole()
            def role = Role.findById(Integer.parseInt(rId))
            def user = User.findById(Integer.parseInt(str[i]))
            userRole = UserRole.findByRoleAndUser(role,user)
            if (userRole==null){
                userRole = new UserRole()
                userRole.role =role
                userRole.user = user
            }
            userRoleService.userRoleSave(userRole)
        }
        def list = userRoleService.getUserRoleByRoleId(Integer.parseInt(rId))
        render(view: '../userRole/editUserRole',model: [roleName:roleName,list: list,roleId:rId])
    }
}
