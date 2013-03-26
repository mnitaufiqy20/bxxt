package com.chd.bx.flowManage

import com.chd.bx.security.Role
import com.chd.bx.security.UserRole
import com.chd.bx.flow.ExmApp
import systemConfig.FlowCon
import com.chd.bx.login.UserLogin
import com.chd.bx.security.User

class FlowConfigController {
    def flowConfigService
    def springSecurityService
    def index() {
        redirect(action: "list", params: params)
    }

    def flowConfigIndex(){
        render(view: '/flowConfig/flowConfigIndex')
    }
    def flowConfig(){
//        def userRoleList1=UserRole.findAllByRole(Role.findByAuthority("公司领导"))
//        def userRoleList2=UserRole.findAllByRole(Role.findByAuthority("公司分管领导"))
//        def userRoleList3=UserRole.findAllByRole(Role.findByAuthority("公司责任部门领导"))
//        def userRoleList4=UserRole.findAllByRole(Role.findByAuthority("部门领导"))
//        def userRoleList5=UserRole.findAllByRole(Role.findByAuthority("分部领导"))
//        def userRoleList6=UserRole.findAllByRole(Role.findByAuthority("过账会计"))
//        def userRoleList7=UserRole.findAllByRole(Role.findByAuthority("出纳"))
//        def userRoleList8=UserRole.findAllByRole(Role.findByAuthority("公司领导"))
//        render(view: '/flowConfig/flowConfig',model:[userRoleList1:userRoleList1,userRoleList2:userRoleList2,
//                userRoleList3:userRoleList3,userRoleList4:userRoleList4,userRoleList5:userRoleList5,
//                userRoleList6:userRoleList6,userRoleList7:userRoleList7,userRoleList8:userRoleList8] )

        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)
        List<FlowCon> list = new ArrayList<FlowCon>()
        List<FlowCon> list2 = new ArrayList<FlowCon>()
        list = initExmApp(user,"FYBX")
        list2 = initExmApp(user,"LOAN")
        render(view: '/flowConfig/flowConfigIndex',model: [user:user,list:list,list2:list2])

    }
    def flowConfigSave(){
        def companyNo = params["companyNo"]
        def exmAppList = ExmApp.findAllByCompanyNo(companyNo)
        if (exmAppList==null || exmAppList.size()==0){
            exmAppSave(params)
        }else{
            exmAppUpdate(params);
        }
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)
        List<FlowCon> list = new ArrayList<FlowCon>()
        List<FlowCon> list2 = new ArrayList<FlowCon>()
        list = initExmApp(user,"FYBX")
        list2 = initExmApp(user,"LOAN")
//        def roleList = Role.findAll();
        render(view: '/flowConfig/flowConfigIndex',model: [user:user,list:list,list2:list2])
    }

    def exmAppSave(params){
        def firstName = params["firstName"]
        for (int i=0;i<firstName.size();i++){
            String first =firstName[i]
            String sedName = params["secondName"][i]
            String thirdName = params["thirdName"][i]
            String fourthName = params["fourthName"][i]
            String fifthName = params["fifthName"][i]
            String postAcc = params["postAcc"][i]
            String payTeller = params["payTeller"][i]
            ExmApp exmApp = new ExmApp()
            exmApp.receiptsType = params["typeBX"][i]
            exmApp.companyNo = params["companyNo"]
            exmApp.empRole = params["empRole"][i]
            exmApp.firstName = first
            exmApp.secondName = sedName
            exmApp.thirdName = thirdName
            exmApp.fourthName = fourthName
            exmApp.fifthName = fifthName
            exmApp.postAcc = postAcc
            exmApp.payTeller = payTeller
            flowConfigService.exmAppSave(exmApp)
        }
    }

    def exmAppUpdate(params){
        def firstName = params["firstName"]
        for (int i=0;i<firstName.size();i++){
            String first =firstName[i]
            String sedName = params["secondName"][i]
            String thirdName = params["thirdName"][i]
            String fourthName = params["fourthName"][i]
            String fifthName = params["fifthName"][i]
            String postAcc = params["postAcc"][i]
            String payTeller = params["payTeller"][i]
            String type = params["typeBX"][i]
            String companyNo = params["companyNo"]
            String empRole = params["empRole"][i]
            ExmApp exmApp = new ExmApp()
            exmApp = ExmApp.findByCompanyNoAndReceiptsTypeAndEmpRole(companyNo,type,empRole);
            exmApp.receiptsType = type
            exmApp.companyNo = companyNo
            exmApp.empRole = empRole
            exmApp.firstName = first
            exmApp.secondName = sedName
            exmApp.thirdName = thirdName
            exmApp.fourthName = fourthName
            exmApp.fifthName = fifthName
            exmApp.postAcc = postAcc
            exmApp.payTeller = payTeller
            flowConfigService.exmAppSave(exmApp)
        }
    }
    def initExmApp(UserLogin userLogin,String type){
        List listRoleName = new ArrayList()

        List<FlowCon> list = new ArrayList<FlowCon>()
        List<UserRole> listStr = new ArrayList<UserRole>()
        UserRole userRole2 = new UserRole()
        User user1 = new User();
        user1.userId = 0;
        user1.name = "无"
        userRole2.user = user1;
        listStr.add(userRole2);
        def roleList;
        def userRoleList1;
        def userRoleList2
        if (type.equals("FYBX")){
            listRoleName.add(0,"报销单－审批人－公司领导")
            listRoleName.add(1,"报销单－审批人－公司分管领导")
            listRoleName.add(2,"报销单－审批人－公司分管部门领导")
            listRoleName.add(3,"报销单－审批人－部门领导")
            roleList = Role.findAllByDescription("BXSQ");
//            listRoleName.add(4,"分部领导")
//            listRoleName.add(5,"员工")
            userRoleList1 = UserRole.findAllByRole(Role.findByDescription("BXKJ"))
            userRoleList2 = UserRole.findAllByRole(Role.findByDescription("BXCN"))
        }else{
            listRoleName.add(0,"借款单－审批人－公司领导")
            listRoleName.add(1,"借款单－审批人－公司分管领导")
            listRoleName.add(2,"借款单－审批人－公司分管部门领导")
            listRoleName.add(3,"借款单－审批人－部门领导")
            roleList = Role.findAllByDescription("JKSQ");
//            listRoleName.add(4,"分部领导")
//            listRoleName.add(5,"员工")
            userRoleList1 = UserRole.findAllByRole(Role.findByDescription("JKKJ"))
            userRoleList2 = UserRole.findAllByRole(Role.findByDescription("JKCN"))
        }

        for (int i=0;i<listRoleName.size();i++){
            FlowCon flowCon = new FlowCon()
            if (roleList==null || roleList.size()==0){
                flowCon.empRoleList = null
            }else{
                flowCon.empRoleList = roleList
            }
            flowCon.empRole = listRoleName.get(i)
            ExmApp exmApp = new ExmApp()
            exmApp = ExmApp.findByCompanyNoAndReceiptsTypeAndEmpRole(userLogin.companyNo,type,listRoleName.get(i))
            if (exmApp==null){
                flowCon.firId = ""
                flowCon.secId = ""
                flowCon.thiId = ""
                flowCon.fouId = ""
                flowCon.fifId = ""
                flowCon.posId = ""
                flowCon.payId = ""
            }else{
                flowCon.firId = exmApp.firstName
                flowCon.secId = exmApp.secondName
                flowCon.thiId = exmApp.thirdName
                flowCon.fouId = exmApp.fourthName
                flowCon.fifId = exmApp.fifthName
                flowCon.posId = exmApp.postAcc
                flowCon.payId = exmApp.payTeller
            }
            if (i==3){
                flowCon.firstName = UserRole.findAllByRole(Role.findByAuthority(listRoleName.get(i)))
            }else{
                flowCon.firstName = UserRole.findAllByRole(Role.findByAuthority(listRoleName.get(i)))
            }
            if (i==3){
                flowCon.secondName =  UserRole.findAllByRole(Role.findByAuthority(listRoleName.get(i-1)))
            }else{
                if (i==0){
                    flowCon.secondName =  listStr;
                }else{
                    flowCon.secondName =  UserRole.findAllByRole(Role.findByAuthority(listRoleName.get(i-1)))
                }
            }
            if (i==3){
                flowCon.thirdName =  UserRole.findAllByRole(Role.findByAuthority(listRoleName.get(i-2)))
            } else{
                if (i ==0 || i == 1){
                    flowCon.thirdName =  listStr;
                }else{
                    flowCon.thirdName =  UserRole.findAllByRole(Role.findByAuthority(listRoleName.get(i-2)))
                }
            }
            if (i==3){
                flowCon.fourthName =  UserRole.findAllByRole(Role.findByAuthority(listRoleName.get(i-3)))
            } else{
                if (i ==0 || i == 1 || i == 2){
                    flowCon.fourthName =  listStr;
                }else{
                    flowCon.fourthName =  UserRole.findAllByRole(Role.findByAuthority(listRoleName.get(i-2)))
                }
            }
//            if (i ==5){
//                flowCon.fifthName =  UserRole.findAllByRole(Role.findByAuthority(listRoleName.get(i-5)))
//            } else{
//                if (i ==0 || i == 1 || i == 2 || i == 3){
//                    flowCon.fifthName =  listStr;
//                }else{
//                    flowCon.fifthName =  UserRole.findAllByRole(Role.findByAuthority(listRoleName.get(i-4)))
//                }
//            }
            flowCon.postAcc = userRoleList1
            flowCon.payTeller = userRoleList2
            list.add(flowCon)
        }
        return list
    }
}
