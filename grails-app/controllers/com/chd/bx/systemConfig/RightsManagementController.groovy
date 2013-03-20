package com.chd.bx.systemConfig

import com.chd.bx.login.UserLogin
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
            rightManage = getAllRights(rightManage,params["roleCode"])
            reList.add(rightManage)
        }
        String roleCode = ""
        String userGroup=""
        //得到角色列表ALL
        def roleList=rightsManagementService.getAllRoleName()
        render(view: '../roleManagement/rightsManagement',model:[roleList:roleList,list:reList,roleCode:roleCode,userGroup:userGroup])
    }
    def getAllRights(RightManage rightManage,String roleCode){
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
        List<RightManage> reList = new ArrayList<RightManage>()
        for (int c=0;c<menuList.size();c++){
            def rightManage=menuList.get(c)
            rightManage = getAllRights(rightManage,roleCode)
            reList.add(rightManage)
        }
        //得到角色列表ALL
        def roleList=rightsManagementService.getAllRoleName()
        String userGroup=getUser(roleCode);

        render(view: '../roleManagement/rightsManagement',model:[roleList:roleList,list:reList,roleCode:roleCode,userGroup:userGroup])
    }
    /**
     * 根据所选的角色code获取角色下面所有用户姓名
     */
    def getUser(String roleId){
//        String roleId= request.getParameter("roleId")
        List<UserLogin> list=rightsManagementService.getUserByRoleId(roleId)
        String userStr=""
        if (list!=null){
            if (list.size()>0){
                for (int i=0;i<list.size();i++){
                    if (list.size()-i ==1){
                        userStr = userStr+list.get(i).getUserName();
                    }else{
                        userStr = userStr+list.get(i).getUserName()+",";
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
        def functionCodeList=params["functionCode"]
        String lookString = params["lookString"]
        String [] look = lookString.split(",")
        String addString = params["addString"]
        String [] add = addString.split(",")
        String updateString = params["updateString"]
        String [] update = updateString.split(",")
        String deleteString = params["deleteString"]
        String [] delete = deleteString.split(",")
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
            if (area[i]=="true"){
                roleRight=roleRight+"S"
            }
            RoleManagement roleManagement = new RoleManagement();
            roleManagement = rightsManagementService.findByFunctionCodeAndRoleCode(functionCodeList.getAt(i),roleCode)
            if (roleManagement!=null){
                roleManagement.roleRight=roleRight
                rightsManagementService.roleManagementSave(roleManagement)
            }else{
                roleManagement = new RoleManagement();
                roleManagement.functionCode=functionCodeList.getAt(i)
                roleManagement.roleCode=roleCode
                roleManagement.roleRight=roleRight
                rightsManagementService.roleManagementSave(roleManagement)
            }
        }
    }

}
