package com.chd.bx.systemConfig

import com.chd.bx.security.Menu
import com.chd.bx.security.Role
import com.chd.bx.security.RoleMenu
import com.chd.bx.security.User
import systemConfig.RightManage

class RightsManagementController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def rightsManagementService
    /**
     * 初始化进入权限配置页面
     * @return
     */
    def index() {
        //得到所有子菜单
        List<RightManage> menuList=rightsManagementService.getMenu()
//        def list = getAllRights(menuList)
        List<RightManage> reList = new ArrayList<RightManage>()
        for (int c=0;c<menuList.size();c++){
            def rightManage=menuList.get(c)
            rightManage = getAllRights(rightManage,0)
            if (rightManage.functionName.equals("科目导入") || rightManage.functionName.equals("SAP系统")){
                continue;
            }
            reList.add(rightManage)
        }
        String roleCode = ""
        String userGroup=""
        //得到角色列表ALL
        def roleList=rightsManagementService.getAllRoleName()
        render(view: '/roleManagement/rightsManagement',model:[roleList:roleList,list:reList,roleCode:roleCode,userGroup:userGroup])
    }
    def getAllRights(RightManage rightManage,int roleCode){
             def functionRts=rightsManagementService.findByFunctionCodeAndRoleCode(rightManage.functionCode,roleCode)
             if (functionRts!=null){
                 if (functionRts.roleRight!=null&&functionRts.roleRight!=""){
                     String rr=functionRts.roleRight
                     int index = rr.length();
                     String[] str = new String[index];
                     for (int i = 0; i <index; i++) {
                         str[i] = rr.substring(i, i + 1);
                         if (str[i]=="V"){  //查看
                             rightManage.setRightLook(true)
                         }else if (str[i]=="N"){                  //新增
                             rightManage.setRightAdd(true)
                         }else if (str[i]=="E"){                  //修改
                             rightManage.setRightUpdate(true)
                         }else if (str[i]=="D"){                  //删除
                             rightManage.setRightDelete(true)
                         }else if (str[i]=="C"){                  //审核
                             rightManage.setRightCheck(true)
                         }else if (str[i]=="S"){                  //授权范围
                             rightManage.setRightArea(true)
                         }
                     }
                     rightManage.setRoleRight(functionRts.roleRight)
                 }
             }
        return rightManage
    }
    def getEdit(){
        String roleCode= params["roleName"]
        //得到所有子菜单
        List<RightManage> menuList=rightsManagementService.getMenu()
//        def list = getAllRights(menuList)
        int roleId = Role.findByRoleCode(roleCode).id
        List<RightManage> reList = new ArrayList<RightManage>()
        for (int c=0;c<menuList.size();c++){
            def rightManage=menuList.get(c)
            rightManage = getAllRights(rightManage,roleId)
            if (rightManage.functionName.equals("科目导入") || rightManage.functionName.equals("SAP系统")){
                continue;
            }
            reList.add(rightManage)
        }
        //得到角色列表ALL
        def roleList=rightsManagementService.getAllRoleName()
        String userGroup=getUser(roleId);

        render(view: '../roleManagement/rightsManagement',model:[roleList:roleList,list:reList,roleCode:roleCode,userGroup:userGroup])
    }
    /**
     * 根据所选的角色code获取角色下面所有用户姓名
     */
    def getUser(int roleId){
//        String roleId= request.getParameter("roleId")
        List<User> list=rightsManagementService.getUserByRoleId(roleId)
        String userStr=""
        if (list!=null){
            if (list.size()>0){
                for (int i=0;i<list.size();i++){
                    if (list.size()-i ==1){
                        userStr = userStr+list.get(i).getUsername();
                    }else{
                        userStr = userStr+list.get(i).getUsername()+",";
                    }
                }
            }
        }
        return userStr
//        request.setCharacterEncoding("GBK");
//        response.setContentType("text/type");
//        response.setCharacterEncoding("GBK")
//        PrintWriter out=response.getWriter();
//        out.print(userStr) ;
//        out.flush();
    }
    /**
     * 修改角色权限
     */
    def updateRights(){
        String roleCode=params["roleName"]
        int roleId = Role.findByRoleCode(roleCode).id
        def functionCodeList=params["functionCode"]
        String lookString = params["lookString"]
        String [] look = lookString.split(",")
        String addString = params["addString"]
        String [] add = addString.split(",")
        String updateString = params["updateString"]
        String [] update = updateString.split(",")
        String deleteString = params["deleteString"]
        String [] delete = deleteString.split(",")
        String checkString = params["checkString"]
        String [] check = checkString.split(",")
        String areaString = params["areaString"]
        String [] area = areaString.split(",")
        for(int i=0;i<functionCodeList.size();i++){
            String roleRight =""
            if (look[i]=="true"){
                roleRight=roleRight+"V"
            }
            if (add[i]=="true"){
                roleRight=roleRight+"N"
            }
            if (update[i]=="true"){
                roleRight=roleRight+"E"
            }
            if (delete[i]=="true"){
                roleRight=roleRight+"D"
            }
            if (check[i]=="true"){
                roleRight=roleRight+"C"
            }
            if (area[i]=="true"){
                roleRight=roleRight+"S"
            }
            RoleMenu roleMenu = new RoleMenu();
            roleMenu = rightsManagementService.findByFunctionCodeAndRoleCode(functionCodeList.getAt(i),roleId)
            if (roleMenu!=null){
                roleMenu.roleRight=roleRight
                rightsManagementService.roleManagementSave(roleMenu)
            }else{
                if (roleRight!=null||roleRight!=""){
                    roleMenu = new RoleMenu();
                    roleMenu.menu = Menu.findById(Integer.parseInt(functionCodeList.getAt(i)))
                    roleMenu.roleId=roleId
                    roleMenu.roleRight=roleRight
                    rightsManagementService.roleManagementSave(roleMenu)
                }
            }
        }
        render(view: '../workbench/index')
    }

}
