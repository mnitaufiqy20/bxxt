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
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)
        List<ExmApp> listFYBX = new ArrayList<ExmApp>()
        List<ExmApp> listLOAN = new ArrayList<ExmApp>()
        listFYBX = ExmApp.findAllByReceiptsTypeAndCompanyNo("FYBX",user.companyNo)
        listLOAN = ExmApp.findAllByReceiptsTypeAndCompanyNo("LOAN",user.companyNo)
        render(view: '/flowConfig/flowConfigIndex2',model: [user:user,listFYBX:listFYBX,listLOAN:listLOAN])
    }
    def getExmAppList(String type){
        List<ExmApp> list = new ArrayList<ExmApp>()
        list = ExmApp.findAllByReceiptsType(type)
        List<ExmApp> exmAppList = new ArrayList<ExmApp>()
        if (list!=null && list.size()>0) {
            for (ExmApp exmApp:list) {
                def e = new ExmApp()
                e = exmApp
                if (exmApp.firstName!="0"){
                    e.firstName = User.findById(Long.parseLong(exmApp.firstName)).username
                }else{
                    e.firstName = ""
                }
                if (exmApp.secondName!="0"){
                    e.secondName = User.findById(Long.parseLong(exmApp.secondName)).username
                }else{
                    e.secondName = ""
                }
                if (exmApp.thirdName!="0"){
                    e.thirdName = User.findById(Long.parseLong(exmApp.thirdName)).username
                }else{
                    e.thirdName = ""
                }
                if (exmApp.fourthName!="0"){
                    e.fourthName = User.findById(Long.parseLong(exmApp.fourthName)).username
                }else{
                    e.fourthName = ""
                }
                if (exmApp.fifthName!="0"){
                    e.fifthName = User.findById(Long.parseLong(exmApp.fifthName)).username
                }else{
                    e.fifthName = ""
                }
                e.postAcc = User.findById(Long.parseLong(exmApp.postAcc)).username
                e.payTeller = User.findById(Long.parseLong(exmApp.payTeller)).username
                exmAppList.add(e)
            }
        }
        return exmAppList
    }

    /**
     * 点击添加的时候，得到没有配置流程人员的角色
     * @return
     */
    def flowConfigAdd(){
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        List<Role> list = new ArrayList<Role>()
        list = flowConfigService.appRoleQuery()
        def loanAppName = flowConfigService.getAppName("借","审")
        def bxAppName =  flowConfigService.getAppName("报","审")
        def loanAccName = flowConfigService.getAppName("借","会计")
        def bxAccName =  flowConfigService.getAppName("报","会计")
        def loanCasName = flowConfigService.getAppName("借","出纳")
        def bxCasName =  flowConfigService.getAppName("报","出纳")
        print("loanAppName:"+loanAppName)
        print("bxAppName:"+bxAppName)
        print("loanAccName:"+loanAccName)
        print("bxAccName:"+bxAccName)
        print("loanCasName:"+loanCasName)
        print("bxCasName:"+bxCasName)
        render(view: '/flowConfig/flowConfigAdd',model: [user:user,list:list,loanAppName:loanAppName,
                bxAppName:bxAppName,loanAccName:loanAccName,bxAccName:bxAccName,loanCasName:loanCasName,bxCasName:bxCasName])
    }
    def flowConfigSave(){
        def companyNo = params["companyNo"]
        def empRole = params["empRole"]
        def r = ExmApp.findByEmpRoleAndCompanyNo(empRole,companyNo)
        def exmApp = new ExmApp()
        exmApp.companyNo = companyNo
        exmApp.empRole = empRole
        def str = empRole.toString().substring(0,1)
        if (str.equals("借")) {
            exmApp.receiptsType = "LOAN"
        } else if (str.equals("报")) {
            exmApp.receiptsType = "FYBX"
        }
        def first = params["firstName"]
        def second = params["secondName"]
        def third = params["thirdName"]
        def fourth = params["fourthName"]
        def fifth = params["fifthName"]
        def postAcc = params["postAcc"]
        def payTeller = params["payTeller"]

        if (!first.equals("") && User.findByUsername(first)!=null) {
            exmApp.firstName = User.findByUsername(first).username
        }else{
            exmApp.firstName = ""
        }
        if (!second.equals("") && User.findByUsername(second)!=null) {
            exmApp.secondName = User.findByUsername(second).username
        }else{
            exmApp.secondName = ""
        }
        if (!third.equals("") && User.findByUsername(third)!=null) {
            exmApp.thirdName = User.findByUsername(third).username
        }else{
            exmApp.thirdName = ""
        }
        if (!fourth.equals("") && User.findByUsername(fourth)!=null) {
            exmApp.fourthName = User.findByUsername(fourth).username
        }else{
            exmApp.fourthName = ""
        }
        if (!fifth.equals("") && User.findByUsername(fifth)!=null) {
            exmApp.fifthName = User.findByUsername(fifth).username
        }else{
            exmApp.fifthName = ""
        }
        if (!postAcc.equals("") && User.findByUsername(postAcc)!=null) {
            exmApp.postAcc = User.findByUsername(postAcc).username
        }else{
            exmApp.postAcc = ""
        }
        if (!payTeller.equals("") && User.findByUsername(payTeller)!=null) {
            exmApp.payTeller = User.findByUsername(payTeller).username
        }else{
            exmApp.payTeller = ""
        }
        flowConfigService.exmAppSave(exmApp)
        flowConfig()
    }

    def flowConfigE(){
        def id = params["id"]
        def exmApp = ExmApp.findById(Long.parseLong(id))
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        def loanAppName = flowConfigService.getAppName("借","审")
        def bxAppName =  flowConfigService.getAppName("报","审")
        def loanAccName = flowConfigService.getAppName("借","会计")
        def bxAccName =  flowConfigService.getAppName("报","会计")
        def loanCasName = flowConfigService.getAppName("借","出纳")
        def bxCasName =  flowConfigService.getAppName("报","出纳")
        render(view: '/flowConfig/flowConfigUpdate',model:[exmApp:exmApp,user: user,loanAppName:loanAppName,
                bxAppName:bxAppName,loanAccName:loanAccName,bxAccName:bxAccName,loanCasName:loanCasName,bxCasName:bxCasName])
    }

    def flowConfigUpdate(){
        def id = params["id"]
        def exmApp = new ExmApp()
        exmApp = ExmApp.findById(id)
        exmApp.firstName = params["firstName"]
        exmApp.secondName = params["secondName"]
        exmApp.thirdName = params["thirdName"]
        exmApp.fourthName = params["fourthName"]
        exmApp.fifthName = params["fifthName"]
        exmApp.postAcc = params["postAcc"]
        exmApp.payTeller = params["payTeller"]
        flowConfigService.exmAppSave(exmApp)
        flowConfig()
    }

    def flowConfigD(){
        def id = params["id"]
        def exmApp = new ExmApp()
        exmApp = ExmApp.findById(id)
        flowConfigService.exmAppDelete(exmApp)
        flowConfig()
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
