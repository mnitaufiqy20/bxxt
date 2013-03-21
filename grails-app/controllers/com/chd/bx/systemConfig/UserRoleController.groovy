package com.chd.bx.systemConfig

import com.chd.bx.security.Role
import com.chd.bx.security.User
import com.chd.bx.security.UserRole
import systemConfig.UserRoleList

class UserRoleController {
    def userRoleService

    def index() {
        def listAll = new ArrayList<UserRoleList>()
        def list = userRoleService.getAllRoleName()
        for(int i=0;i<list.size();i++){
            def userRoleList = new UserRoleList()
            int roleId = list.get(i).id
            userRoleList.setRoleId(roleId)
            userRoleList.setRoleName(list.get(i).authority)
            def listUser = userRoleService.getUserRoleByRoleId(roleId)
            String userId=""
            String userName=""
            if (listUser!=null){
                for (int h=0;h<listUser.size();h++){
                    def userRole=listUser.get(h)
                    int uId=userRole.user.id
                    String uName=userRole.user.name
                    if (listUser.size()-1==h){
                        userId = userId+uId
                        userName = userName+uName
                    }else{
                        userId = userId+uId+","
                        userName = userName+uName+","
                    }

                }
                userRoleList.setUserId(userId)
                userRoleList.setUserName(userName)
            }
            listAll.add(userRoleList)
        }
        render(view:'../userRole/userRole',model: [list:listAll])
    }
    /**
     * 查看详细--该角色下的所有用户
     * @return
     */
    def edit(){
        def roleName=request.getParameter("roleName")
        def roleId=request.getParameter("roleId")
        def list = userRoleService.getUserRoleByRoleId(Integer.parseInt(roleId))
        render(view: '../userRole/editUserRole',model: [roleName:roleName,list: list,roleId:roleId])
    }
    def deleteUserRole(){
        def roleName=request.getParameter("roleName")
        def userId=request.getParameter("userId")
        def roleId=request.getParameter("roleId")
        userRoleService.deleteUserRole(Integer.parseInt(userId),Integer.parseInt(roleId))
        def list = userRoleService.getUserRoleByRoleId(Integer.parseInt(roleId))
        render(view: '../userRole/editUserRole',model: [roleName:roleName,list: list,roleId:roleId])
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
                String uName=userRole.user.name
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
        render(view: '../userRole/addUser',model: [userList:userList,roleName:roleName,roleId:roleId])
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
