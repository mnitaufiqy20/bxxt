package com.chd.bx.bxd

import java.text.SimpleDateFormat
import jbpm.WorkflowFactory
import com.chd.bx.expenseAccount.LoanAppReceiptsService
import com.chd.bx.login.UserLogin
import com.chd.bx.security.User
import processes.TaskStore
import org.jbpm.api.task.Task
import org.jbpm.api.history.HistoryTask
import org.jbpm.api.history.HistoryTaskQuery
import com.chd.bx.examAppHistory.ExamAppHistory
import org.jbpm.pvm.internal.history.model.HistoryTaskInstanceImpl
import org.jbpm.api.cmd.Command
import org.jbpm.api.cmd.Environment
import org.hibernate.Session
import processes.ExmAppTask
import com.chd.bx.expenseAccount.LoanAppReceipts
import org.jbpm.api.ProcessDefinition
import com.chd.bx.security.UserRole
import com.chd.bx.security.Role
import com.chd.bx.security.Menu
import com.chd.bx.security.RoleMenu

/**
 *   用户登录service
 *   @author 孟敏
 *   @time 2013年1月6日
 */
class BxReceiptController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def bxReceipt   = new BxReceipt();
    def bxReceiptService
    def bxOtherService
    def bxLoanService
    def bxTravelService
    def bxWorkService
    def bxZhaoDaiService
    def processEngine;
    def springSecurityService
    WorkflowFactory  workflowFactory = new WorkflowFactory()
    def loanAppReceiptsService
    /**
     *  初始 进入查询报销单界面
     * @return
     */
    def index() {
        List<BxReceipt> bxdList = new ArrayList<BxReceipt>()
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        def user2 = UserLogin.findByLoginName(currentUserName)
        def userRoleList = UserRole.findAllByUser(user)
        for (UserRole userRole:userRoleList){
            def role = new Role()
            role = userRole.role
            if (role.description.equals("PT")) {
                bxdList = BxReceipt.findAllByBxEmpIdNumber(user2.idNumber)
            } else if (role.description.equals("KJ")) {
                bxdList = bxReceiptService.bxdQuery("已审核")
                break;
            } else if (role.description.equals("CN")) {
                bxdList = bxReceiptService.bxdQuery("已过账")
                break;
            }
        }
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
        if(str.indexOf("E")!=-1){
            c = "E"
        }
//        bxdList = bxReceiptService.bxdQueryAll()
        render(view: '../bxReceipt/bxReceiptQuery',model: [bxdList:bxdList,funcCode:funcCode,a:a,b:b,c:c])
    }
    /**
     *   查询报销单据
     * @param params
     * @return
     */
    def queryReceipt(params){
        List<BxReceipt> bxdList = new ArrayList<BxReceipt>()
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        def user2 = UserLogin.findByLoginName(currentUserName)
        def idNum = user2.idNumber
        def index = 0
        def userRoleList = UserRole.findAllByUser(user)
        for (UserRole userRole:userRoleList){
            def role = new Role()
            role = userRole.role
            if (role.description.equals("PT")) {
                index = 1
                break;
            } else if (role.description.equals("KJ")) {
                index = 2
                break;
            } else if (role.description.equals("CN")) {
                index = 3
                break;
            }
        }
        bxdList=bxReceiptService.queryReceipt(params,idNum,index)
//        bxdList=bxReceiptService.queryReceipt(params)
        String rNo = params['rNo']
        String startDate = params['startDate']
        String endDate = params['endDate']
        String status = params['status']
        render(view: '../bxReceipt/bxReceiptQuery',model: [bxdList:bxdList,rNo:rNo,startDate:startDate,endDate:endDate,status:status])
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
                    && !role1.description.equals("CN") && str.equals("B")) {
                def roleMenu = RoleMenu.findByRoleIdAndMenu(role1.id,menu)
                if (roleMenu!=null && roleMenu.roleRight!=""){
                    strRoleRight += roleMenu.roleRight
                }
            }
        }
        return strRoleRight
    }
    /**
     * 进入新增报销单页面
     * @return
     */
    def bxdDetail(){
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)
        bxReceipt = new BxReceipt();
        bxReceipt.bxNo=" "
        bxReceipt.approvalTime=sysDateFormat()
        bxReceipt.bxEmpIdNumber = user.idNumber
        bxReceipt.bxEmpName = user.userName
        bxReceipt.bxEmpNo = Integer.parseInt(user.empNo)
        bxReceipt.bxEmpPhoneNumber = user.telephone
        bxReceipt.companyName = user.companyNo
        bxReceipt.bxEmpPosition = user.empPosition
        bxReceipt.managerName = user.userName
        bxReceipt.applicationDate = sysDateFormat()
        bxReceipt.needMoneyDate = sysDateFormat()
        bxReceipt.costCenter = "1"
        bxReceipt.budgetCenter = "1"
        bxReceipt.paymentMode = "银行转账"
        bxReceipt.billsCurr = "CNY"
        List<BxOther> listOther = new ArrayList<BxOther>()
        List<BxLoan>  listLoan = new ArrayList<BxLoan>();
        List<BxTravel> listTravel = new ArrayList<BxTravel>();
        List<BxZhaoDai> listZhaoDai = new ArrayList<BxZhaoDai>();
        List<BxWork> listWork = new ArrayList<BxWork>();
        BxTravel bxTravel = new BxTravel();
        bxTravel.clTravelDaysCount=0
        bxTravel.clTravelDetails = ""
        def idNum = user.idNumber
        def  loan_list = loanAppReceiptsService.loanAppReceiptsByIdNum(idNum)
        def str = ""
        def strId = ""
        for(LoanAppReceipts loanAppReceipts:loan_list){
           str += (loanAppReceipts.loanAppReceiptsId+","+loanAppReceipts.loanBegDate+","+loanAppReceipts.billsCurr+","
            +loanAppReceipts.loanMoney+","+loanAppReceipts.loanBalance+","+loanAppReceipts.loanAlreadyRefund+","
            +loanAppReceipts.loanRemark+";")
            strId += (loanAppReceipts.loanAppReceiptsId+";")
        }
        def funcCode = params["funcCode"]
        render(view: '../bxReceipt/bxReceiptDetail',model: [user:user,bxReceipt:bxReceipt,funcCode:funcCode,str:str,strId:strId,bxTravel:bxTravel,listOther:listOther,
                listLoan:listLoan,listTravel:listTravel,listZhaoDai:listZhaoDai,listWork:listWork,loan_list:loan_list])
    }
    /**
     *   进入修改界面
     * @param params
     * @return
     */
    def bxdEdit(params){
        String bxdNo=params['bxNo']
        bxReceipt = getBxdById(params);
        List<BxOther> listOther = new ArrayList<BxOther>()
        listOther=bxOtherService.otherQueryByBxdNo(bxdNo)
        if (listOther==null){
            listOther = new ArrayList<BxOther>()
        }
        List<BxLoan> listLoan = new ArrayList<BxLoan>()
        listLoan = bxLoanService.loanQueryByBxdNo(bxdNo)
        if (listLoan==null){
            listLoan = new ArrayList<BxLoan>()
        }
        //差旅信息
        List<BxTravel> listTravel = new ArrayList<BxTravel>()
        listTravel = bxTravelService.travelQueryByBxdNo(bxdNo)
        if (listTravel==null){
            listTravel = new ArrayList<BxTravel>()
        }
        BxTravel bxTravel = new BxTravel();
        bxTravel=bxTravelService.travelGetOneByBxdNo(bxdNo)
        if (bxTravel==null){
            bxTravel = new BxTravel();
            bxTravel.clTravelDaysCount=0
            bxTravel.clTravelDetails = ""
        }
        //办公信息
        List<BxWork> listWork = new ArrayList<BxWork>()
        listWork = bxWorkService.workQueryByBxdNo(bxdNo)
        if (listWork==null){
            listWork = new ArrayList<BxWork>()
        }
        //招待信息
        List<BxZhaoDai> listZhaoDai = new ArrayList<BxZhaoDai>()
        listZhaoDai = bxZhaoDaiService.zhaoDaiQueryByBxdNo(bxdNo)
        if (listZhaoDai==null){
            listZhaoDai = new ArrayList<BxZhaoDai>()
        }
        def funcCode = params["funcCode"]
        render(view: '../bxReceipt/bxReceiptUpdate',model: [bxReceipt:bxReceipt,funcCode:funcCode,bxTravel:bxTravel,listOther:listOther,
                listLoan:listLoan,listTravel:listTravel,listZhaoDai:listZhaoDai,listWork:listWork])
    }
    /**
     * 根据报销单号获取报销单信息
     * @param params
     * @return
     */
    def getBxdById(params){
        List<BxReceipt> list = new ArrayList<BxReceipt>();
        list=bxReceiptService.getBxdById(params["bxNo"])
        bxReceipt = new BxReceipt();
        if(list!=null && list.size()>0){
            bxReceipt = list.get(0)
        }
    }
    /**
     * 添加保存
     */
    def bxdSave(params){
        String type="add"
        String  bxdNo="0";
        bxReceipt.bxdStatus="已保存"
        bxReceipt=bxRep(bxReceipt,params)
        bxdNo=getBxdNo()
        bxReceipt.setBxNo(bxdNo)
        //保存报销单信息
        bxReceiptService.bxdSave(bxReceipt)
        //保存其他报销信息
        saveOther(params,bxdNo,type)
        List<BxOther> listOther = new ArrayList<BxOther>()
        listOther=bxOtherService.otherQueryByBxdNo(bxdNo)
        //保存借款信息
        saveLoan(params,bxdNo,type)
        List<BxLoan> listLoan = new ArrayList<BxLoan>()
        listLoan = bxLoanService.loanQueryByBxdNo(bxdNo)
        //保存差旅信息
        saveTravel(params,bxdNo,type)
        List<BxTravel> listTravel = new ArrayList<BxTravel>()
        listTravel = bxTravelService.travelQueryByBxdNo(bxdNo)
        BxTravel bxTravel = new BxTravel();
        bxTravel.clTravelDaysCount=Integer.parseInt( params['clTravelDaysCount'])
        bxTravel.clTravelDetails = params['clTravelDetails']
        //保存办公信息
        saveWork(params,bxdNo,type)
        List<BxWork> listWork = new ArrayList<BxWork>()
        listWork = bxWorkService.workQueryByBxdNo(bxdNo)
        //保存招待信息
        saveZhaoDai(params,bxdNo,type)
        List<BxZhaoDai> listZhaoDai = new ArrayList<BxZhaoDai>()
        listZhaoDai = bxZhaoDaiService.zhaoDaiQueryByBxdNo(bxdNo)
        startFlow(bxdNo);
        def funcCode = params["funcCode"]
        render(view: '/bxReceipt/bxReceiptUpdate',model: [bxReceipt:bxReceipt,funcCode:funcCode,listOther:listOther,listLoan:listLoan,
                listTravel:listTravel,listWork:listWork,listZhaoDai:listZhaoDai,bxTravel:bxTravel])
    }
    /**
     *   修改保存
     * @return
     */
    def bxdUpdate(params){
        String type="update"
        print("Start update ......")
        String  bxdNo="0"
        bxdNo = params['bxNo'];
        bxReceipt=bxReceiptService.getBxdById(bxdNo).get(0)
        bxReceipt=bxRep(bxReceipt,params)
        bxReceipt.setBxNo(bxdNo)

        //保存报销单信息
        bxReceiptService.bxdSave(bxReceipt)
        //保存其他报销信息
        saveOther(params,bxdNo,type)
        List<BxOther> listOther = new ArrayList<BxOther>()
        listOther=bxOtherService.otherQueryByBxdNo(bxdNo)
        //保存借款信息
        saveLoan(params,bxdNo,type)
        List<BxLoan> listLoan = new ArrayList<BxLoan>()
        listLoan = bxLoanService.loanQueryByBxdNo(bxdNo)
        //保存差旅信息
        saveTravel(params,bxdNo,type)
        List<BxTravel> listTravel = new ArrayList<BxTravel>()
        listTravel = bxTravelService.travelQueryByBxdNo(bxdNo)
        BxTravel bxTravel = new BxTravel();
        bxTravel.clTravelDaysCount=Integer.parseInt( params['clTravelDaysCount'])
        bxTravel.clTravelDetails = params['clTravelDetails']
        //保存办公信息
        saveWork(params,bxdNo,type)
        List<BxWork> listWork = new ArrayList<BxWork>()
        listWork = bxWorkService.workQueryByBxdNo(bxdNo)
        //保存招待信息
        saveZhaoDai(params,bxdNo,type)
        List<BxZhaoDai> listZhaoDai = new ArrayList<BxZhaoDai>()
        listZhaoDai = bxZhaoDaiService.zhaoDaiQueryByBxdNo(bxdNo)
        def funcCode = params["funcCode"]
        render(view: '/bxReceipt/bxReceiptUpdate',model: [bxReceipt:bxReceipt,funcCode:funcCode,listOther:listOther,listLoan:listLoan,listTravel:listTravel,
                listWork:listWork,listZhaoDai:listZhaoDai,bxTravel:bxTravel])
    }

    def bxdLookUp(params){
        String type="lookUp"
        print("Start lookUp ......")
        String  bxdNo="0"
        bxdNo = params['bxNo'];
        bxReceipt=bxReceiptService.getBxdById(bxdNo).get(0)

        List<BxOther> listOther = new ArrayList<BxOther>()
        listOther=bxOtherService.otherQueryByBxdNo(bxdNo)
        //保存借款信息
        List<BxLoan> listLoan = new ArrayList<BxLoan>()
        listLoan = bxLoanService.loanQueryByBxdNo(bxdNo)
        //保存差旅信息
        List<BxTravel> listTravel = new ArrayList<BxTravel>()
        listTravel = bxTravelService.travelQueryByBxdNo(bxdNo)
        BxTravel bxTravel = new BxTravel();
//        bxTravel.clTravelDaysCount=Integer.parseInt( params['clTravelDaysCount'])
//        bxTravel.clTravelDetails = params['clTravelDetails']
        //保存办公信息
        List<BxWork> listWork = new ArrayList<BxWork>()
        listWork = bxWorkService.workQueryByBxdNo(bxdNo)
        //保存招待信息
        List<BxZhaoDai> listZhaoDai = new ArrayList<BxZhaoDai>()
        listZhaoDai = bxZhaoDaiService.zhaoDaiQueryByBxdNo(bxdNo)
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        def userRoleList = UserRole.findAllByUser(user)
        def role = new Role()
        for (UserRole userRole:userRoleList){
            def role1 = new Role()
            role1 = userRole.role
            def str = role1.description.substring(0,1)
            if (!role1.description.equals("PT") && !role1.description.equals("KJ")
                    && !role1.description.equals("CN") && str.equals("B")) {
                role = role1
                break;
            }
        }
        def userL = UserLogin.findByIdNumber(bxReceipt.bxEmpIdNumber)
        def user2 = User.findByUsername(userL.loginName)
        def userRoleList2 = UserRole.findAllByUser(user2)
        def role2 = new Role()
        for (UserRole userRole:userRoleList2){
            def role1 = new Role()
            role1 = userRole.role
            def str = role1.description.substring(0,1)
            if (!role1.description.equals("PT") && !role1.description.equals("KJ")
                    && !role1.description.equals("CN") && str.equals("B")) {
                role2 = role1
                break;
            }
        }
        def exmApp = loanAppReceiptsService.getProcessApprove(role2.authority,userL.companyNo,"FYBX")
        def funcCode = params["funcCode"]
        def str = getLimitsStr(currentUserName,funcCode)
        def d = ""
        def e = ""
        if (str.indexOf("P")!=-1){
            d = "P"
        }
        if(str.indexOf("M")!=-1){
            e = "M"
        }
        render(view: '/bxReceipt/bxReceiptCommit',model: [bxReceipt:bxReceipt,funcCode:funcCode,d: d,e: e,user: user,role:role,exmApp:exmApp,listOther:listOther,
                listLoan:listLoan,listTravel:listTravel,listWork:listWork,listZhaoDai:listZhaoDai,bxTravel:bxTravel])
    }

    /**
     * 提交单据
     * @return
     */
    def bxdCommit(){
        def action = params["act"]
        def bxdNo
        if (action.equals("add")){
            String type="add"
            bxdNo = getBxdNo()
            bxReceipt = new BxReceipt()
            bxReceipt.bxNo = bxdNo   //单据名称首字母J(1位)+公司代码（4位）+年份月分（4位）+3位流水号
            //保存其他报销信息
            saveOther(params,bxdNo,type)
            //保存借款信息
            saveLoan(params,bxdNo,type)
            //保存差旅信息
            saveTravel(params,bxdNo,type)
//            BxTravel bxTravel = new BxTravel();
//            bxTravel.clTravelDaysCount=Integer.parseInt( params['clTravelDaysCount'])
//            bxTravel.clTravelDetails = params['clTravelDetails']
            //保存办公信息
            saveWork(params,bxdNo,type)
            //保存招待信息
            saveZhaoDai(params,bxdNo,type)
            startFlow(bxdNo)
        }else if (params["act"].equals("update")){
            bxReceipt=bxReceiptService.getBxdById(params["bxNo"]).get(0)
            bxdNo = bxReceipt.bxNo
            String type="lookUp"
            //保存其他报销信息
            saveOther(params,bxdNo,type)
            //保存借款信息
            saveLoan(params,bxdNo,type)
            //保存差旅信息
            saveTravel(params,bxdNo,type)
            BxTravel bxTravel = new BxTravel();
            bxTravel.clTravelDaysCount=Integer.parseInt( params['clTravelDaysCount'])
//            bxTravel.clTravelDetails = params['clTravelDetails']
            //保存办公信息
            saveWork(params,bxdNo,type)
            //保存招待信息
            saveZhaoDai(params,bxdNo,type)
        }
        bxReceipt=bxRep(bxReceipt,params)
        bxReceipt.bxdStatus = "已提交"
        bxReceiptService.bxdSave(bxReceipt)

        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        List<Task> list = workflowFactory.getTaskByUserId(processEngine,user.id.toString());
        if (list!=null&&list.size()>1){
            paiXu(list);
        }
        Task task = list.get(0)
        def executionId = task.getExecutionId()
        def nextUserId = task.assignee
        def nextUser = UserLogin.findByUserId(User.findById(nextUserId).userId)
        workflowFactory.approveTask(processEngine,list.get(0).getId(),"approve");

        List<ExmAppTask> exmAppTaskList = loanAppReceiptsService.getTaskByExecutionId(executionId)
        def exmAppTask = list.get(0)
//        def nextUserId = exmAppTask.assignId
//        def nextUser = UserLogin.findByUserId(nextUserId)

        //发送邮件给下一个办理人
//        sendEmail(nextUser.getUserName(),params["loanAppReceiptsId"],nextUser.empEmail,1);//用户需要邮箱
//        String currentUserName = springSecurityService.getPrincipal().username;
//        def user = User.findByUsername(currentUserName)
        def user2 = UserLogin.findByLoginName(currentUserName)
        def userRoleList = UserRole.findAllByUser(user)
        def role = new Role()
        for (UserRole userRole:userRoleList){
            def role1 = new Role()
            role1 = userRole.role
            def str = role1.description.substring(0,1)
            if (!role1.description.equals("PT") && !role1.description.equals("KJ")
                    && !role1.description.equals("CN") && str.equals("B")) {
                role = role1
                break;
            }
        }
        List<BxOther> listOther = new ArrayList<BxOther>()
        listOther=bxOtherService.otherQueryByBxdNo(bxdNo)
        //保存借款信息
        List<BxLoan> listLoan = new ArrayList<BxLoan>()
        listLoan = bxLoanService.loanQueryByBxdNo(bxdNo)
        //保存差旅信息
        List<BxTravel> listTravel = new ArrayList<BxTravel>()
        listTravel = bxTravelService.travelQueryByBxdNo(bxdNo)
        BxTravel bxTravel = new BxTravel();
//        bxTravel.clTravelDaysCount=Integer.parseInt( params['clTravelDaysCount'])
//        bxTravel.clTravelDetails = params['clTravelDetails']
        //保存办公信息
        List<BxWork> listWork = new ArrayList<BxWork>()
        listWork = bxWorkService.workQueryByBxdNo(bxdNo)
        //保存招待信息
        List<BxZhaoDai> listZhaoDai = new ArrayList<BxZhaoDai>()
        listZhaoDai = bxZhaoDaiService.zhaoDaiQueryByBxdNo(bxdNo)
        def userL = UserLogin.findByIdNumber(bxReceipt.bxEmpIdNumber)
        def user3 = User.findByUsername(userL.loginName)
        def userRoleList2 = UserRole.findAllByUser(user3)
        def role2 = new Role()
        for (UserRole userRole:userRoleList2){
            def role1 = new Role()
            role1 = userRole.role
            def str = role1.description.substring(0,1)
            if (!role1.description.equals("PT") && !role1.description.equals("KJ")
                    && !role1.description.equals("CN") && str.equals("B")) {
                role2 = role1
                break;
            }
        }
        def exmApp = loanAppReceiptsService.getProcessApprove(role2.authority,userL.companyNo,"FYBX")
        def funcCode = params["funcCode"]
        def str = getLimitsStr(currentUserName,funcCode)
        def d = ""
        def e = ""
        if (str.indexOf("P")!=-1){
            d = "P"
        }
        if(str.indexOf("M")!=-1){
            e = "M"
        }
        render(view: '/bxReceipt/bxReceiptCommit',model: [bxReceipt:bxReceipt,funcCode:funcCode,d: d,e: e,user: user,role:role,exmApp:exmApp,listOther:listOther,
                listLoan:listLoan,listTravel:listTravel,listWork:listWork,listZhaoDai:listZhaoDai,bxTravel:bxTravel])
    }

    /**
     * 启动流程
     */
    def startFlow(String bxdNo){
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        def user2 = UserLogin.findByLoginName(currentUserName)
        def userRoleList = UserRole.findAllByUser(user)
        def role = new Role()
        for (UserRole userRole:userRoleList){
            def role1 = new Role()
            role1 = userRole.role
            def str = role1.description.substring(0,1)
            if (!role1.description.equals("PT") && !role1.description.equals("KJ")
                    && !role1.description.equals("CN") && str.equals("B")) {
                role = role1
                break;
            }
        }
//        def exmApp = loanAppReceiptsService.getProcessApprove(user.role.authority,user2.companyNo,"FYBX")
        def exmApp = loanAppReceiptsService.getProcessApprove(role.authority,user2.companyNo,"FYBX")
        String ty = "";
        if (exmApp.firstName!=null && exmApp.secondName==null && exmApp.thirdName==null && exmApp.fourthName==null && exmApp.fifthName==null){
            ty = "A";
        }else if (exmApp.firstName!=null && exmApp.secondName!=null && exmApp.thirdName==null && exmApp.fourthName==null && exmApp.fifthName==null){
            ty = "B";
        }else if (exmApp.firstName!=null && exmApp.secondName!=null && exmApp.thirdName!=null && exmApp.fourthName==null && exmApp.fifthName==null){
            ty = "C";
        }else if (exmApp.firstName!=null && exmApp.secondName!=null && exmApp.thirdName!=null && exmApp.fourthName!=null && exmApp.fifthName==null){
            ty = "D";
        }else if (exmApp.firstName!=null && exmApp.secondName!=null && exmApp.thirdName!=null && exmApp.fourthName!=null && exmApp.fifthName!=null){
            ty = "E";
        }
        def map = new HashMap<String, Object>()
        map.put("loanId",bxdNo)
        map.put("type",ty)
        map.put("userId",User.findByUserId(user.userId).id)
        if (exmApp.getFirstName()==null){
            map.put("first","")
        }else{
            map.put("first",User.findByUsername(exmApp.getFirstName()).id)
        }
        if (exmApp.getSecondName()==null){
            map.put("second","")
        }else{
            map.put("second",User.findByUsername(exmApp.getSecondName()).id)
        }
        if (exmApp.getThirdName()==null){
            map.put("third","")
        }else{
            map.put("third",User.findByUsername(exmApp.getThirdName()).id)
        }
        if (exmApp.getFourthName()==null){
            map.put("fourth","")
        }else{
            map.put("fourth",User.findByUsername(exmApp.getFourthName()).id)
        }
        if (exmApp.getFifthName()==null){
            map.put("fifth","")
        }else{
            map.put("fifth",User.findByUsername(exmApp.getFifthName()).id)
        }
        workflowFactory.startWorkflow(processEngine,"BxRec",map)
    }

    /**
     * 查找用户任务并且通过任务ID倒序
     * @param list
     *      需要转换的List
     */
    void paiXu(List<Task> list){
        Collections.sort(list,new Comparator<Task>(){
            public int compare(Task task1, Task task2) {
                int t1 = Integer.parseInt(task1.getId());
                int t2 = Integer.parseInt(task2.getId());
                return t2-t1;
            }
        });
    }


    //在新增时获得单号
    def getBxdNo(){
        String bxdNo = "B";
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)

        def comNo = user.companyNo  //公司代码（四位）

        //年月 （四位）
        Date date = new Date()
        SimpleDateFormat matter=new SimpleDateFormat("yyyyMM")
        String dates = matter.format(date);
        println("dates:"+dates)
        def comAndDate = comNo + dates.substring(2,6)
        bxdNo += comAndDate

        //流水号（三位）
        def serialNumberList = new ArrayList<String>()
        List<BxReceipt> bx_list = new ArrayList<BxReceipt>()
        bx_list = BxReceipt.findAllByCompanyName(comNo)
        def n1 = 0
        def n2 = 0
        if(bx_list!=null&&bx_list.size()>0){
            for (int i=0;i<bx_list.size();i++){
                def str = bx_list.get(i).getBxNo()
                def s = str.substring(1,9)
                if (comAndDate.equals(s)){
                    if (serialNumberList.size()==0){
                        n1 = Integer.parseInt(str.substring(9,str.length()))
                        serialNumberList.add(str.substring(9,str.length()))
                    }else{
                        n2 = Integer.parseInt(str.substring(9,str.length()))
                        if (n2 > n1){
                            serialNumberList.clear()
                            serialNumberList.add(str.substring(9,str.length()))
                            n1 = n2
                        }
                    }
                }
            }
        }

        if (serialNumberList.size()==0){
            bxdNo += "001"
        }else{
            int t =  Integer.parseInt(serialNumberList.get(0))+1
            int a = t/10
            def serialNumber = ""
            if (t<100){
                if (a>0){
                    serialNumber = "0"+t
                }else{
                    serialNumber = "00"+t
                }
                bxdNo += serialNumber
            }else{
                bxdNo += t
            }
        }
        return bxdNo
    }
    def deleteOther(String bxdNo){
        List<BxOther> listOther = new ArrayList<BxOther>()
        listOther= bxOtherService.otherQueryByBxdNo(bxdNo)
        if (listOther!=null){
            int listSize=listOther.size();
            if (listSize>0){
                for (int i=0;i<listSize;i++){
                    BxOther bo = new BxOther()
                    bo = listOther.get(i)
                    bo.delete()
                    print("other  delete ......")
                }
            }
        }
    }
    def deleteLoan(String bxdNo){
        List<BxLoan> listLoan = new ArrayList<BxLoan>()
        listLoan= bxLoanService.loanQueryByBxdNo(bxdNo)
        if (listLoan!=null){
            int listSize=listLoan.size();
            if (listSize>0){
                for (int i=0;i<listSize;i++){
                    BxLoan bl = new BxLoan()
                    bl = listLoan.get(i)
                    bl.delete()
                    print("BxLoan  delete ......")
                }
            }
        }
    }
    def deleteTravel(String bxdNo){
        List<BxTravel> listTravel = new ArrayList<BxTravel>()
        listTravel= bxTravelService.travelQueryByBxdNo(bxdNo)
        if (listTravel!=null){
            int listSize=listTravel.size();
            if (listSize>0){
                for (int i=0;i<listSize;i++){
                    BxTravel bt = new BxTravel()
                    bt = listTravel.get(i)
                    bt.delete()
                    print("BxTravel  delete ......")
                }
            }
        }
    }
    def deleteWork(String bxdNo){
        List<BxWork> listWork = new ArrayList<BxWork>()
        listWork= bxWorkService.workQueryByBxdNo(bxdNo)
        if (listWork!=null){
            int listSize=listWork.size();
            if (listSize>0){
                for (int i=0;i<listSize;i++){
                    BxWork bw = new BxWork()
                    bw = listWork.get(i)
                    bw.delete()
                    print("BxWork  delete ......")
                }
            }
        }
    }
    def deleteZhaoDai(String bxdNo){
        List<BxZhaoDai> listZhaoDai = new ArrayList<BxZhaoDai>()
        listZhaoDai= bxZhaoDaiService.zhaoDaiQueryByBxdNo(bxdNo)
        if (listZhaoDai!=null){
            int listSize=listZhaoDai.size();
            if (listSize>0){
                for (int i=0;i<listSize;i++){
                    BxZhaoDai bzd = new BxZhaoDai()
                    bzd = listZhaoDai.get(i)
                    bzd.delete()
                    print("BxZhaoDai  delete ......")
                }
            }
        }
    }
    def saveOther(params,String bxdNo,String type){
        int index= Integer.parseInt(params['otherIndex'])
        if (type.equals("add")){
            if (index>1){
                for(int i=1;i<index;i++){
                    BxOther bxOther = new BxOther();
                    bxOther=bxOtherRep(bxOther,params,i,bxdNo,type)
                    if (bxOther.oNo>0){
                        bxOtherService.otherSave(bxOther)
                    }
                }
            }
        }else if (type.equals("update")){
            deleteOther(bxdNo)
            def oNoList=params['oNoList']
            int listCount=0
            if (oNoList!=null){
                listCount=oNoList.size();
                if (listCount>0){
                    for(int i=0;i<listCount;i++){
                        BxOther bxOther = new BxOther();
                        int a=i
                        if (listCount==1){
                            a = -1;
                        }
                        bxOther=bxOtherRep(bxOther,params,a,bxdNo,type)
                        if (bxOther.oNo>0){
                            bxOtherService.otherSave(bxOther)
                        }
                    }
                }
            }
            if (index>1){
                for(int i=1;i<index;i++){
                    BxOther bxOther = new BxOther();
                    bxOther=bxOtherRep(bxOther,params,i,bxdNo,"add")
                    if (bxOther.oNo>0){
                        bxOtherService.otherSave(bxOther)
                    }

                }
            }

        }


    }
    def saveLoan(params,String bxdNo,String type){
        int index= Integer.parseInt(params['loanIndex'])
        if (type.equals("add")){
            if (index>1){
                for(int i=1;i<index;i++){
                    BxLoan bxLoan = new BxLoan();
                    bxLoan=bxLoanRep(bxLoan,params,i,bxdNo,type)
                    if (bxLoan.lNo>0){
                        bxLoanService.loanSave(bxLoan)
                    }

                }
            }
        }else if (type.equals("update")){
            deleteLoan(bxdNo)
            def lNoList=params['lNoList']
            int listCount=0
            if (lNoList!=null){
                listCount=lNoList.size();
                if (listCount>0){
                    for(int i=0;i<listCount;i++){
                        BxLoan bxLoan = new BxLoan();
                        int a=i
                        if (listCount==1){
                            a = -1;
                        }
                        bxLoan=bxLoanRep(bxLoan,params,a,bxdNo,type)
                        if (bxLoan.lNo>0){
                            bxLoanService.loanSave(bxLoan)
                        }
                    }
                }
            }
            if (index>1){
                for(int i=1;i<index;i++){
                    BxLoan bxLoan = new BxLoan();
                    bxLoan=bxLoanRep(bxLoan,params,i,bxdNo,"add")
                    if (bxLoan.lNo>0){
                        bxLoanService.loanSave(bxLoan)
                    }

                }
            }

        }
    }
    def saveTravel(params,String bxdNo,String type){
        int index= Integer.parseInt(params['clIndex'])
        if (type.equals("add")){
            if (index>1){
                for(int i=1;i<index;i++){
                    BxTravel bxTravel = new BxTravel();
                    bxTravel=bxTravelRep(bxTravel,params,i,bxdNo,type)
                    if (bxTravel.cNo>0){
                        bxTravelService.travelSave(bxTravel)
                    }

                }
            }
        }else if (type.equals("update")){
            deleteTravel(bxdNo)
            def cNoList=params['cNoList']
            int listCount=0
            if (cNoList!=null){
                listCount=cNoList.size();
                if (listCount>0){
                    for(int i=0;i<listCount;i++){
                        BxTravel bxTravel = new BxTravel();
                        int a=i
                        if (listCount==1){
                            a = -1;
                        }
                        bxTravel=bxTravelRep(bxTravel,params,a,bxdNo,type)
                        if (bxTravel.cNo>0){
                            bxTravelService.travelSave(bxTravel)
                        }
                    }
                }
            }
            if (index>1){
                for(int i=1;i<index;i++){
                    BxTravel bxTravel = new BxTravel();
                    bxTravel=bxTravelRep(bxTravel,params,i,bxdNo,"add")
                    if (bxTravel.cNo>0){
                        bxTravelService.travelSave(bxTravel)
                    }

                }
            }
        }
    }
    def saveWork(params,String bxdNo,String type){
        int index= Integer.parseInt(params['bgIndex'])
        if (type.equals("add")){
            if (index>1){
                for(int i=1;i<index;i++){
                    BxWork bxWork = new BxWork();
                    bxWork=bxWorkRep(bxWork,params,i,bxdNo,type)
                    if (bxWork.bNo>0){
                        bxWorkService.workSave(bxWork)
                    }

                }
            }
        }else if (type.equals("update")){
            deleteWork(bxdNo)
            def bNoList=params['bNoList']
            int listCount=0
            if (bNoList!=null){
                listCount=bNoList.size();
                if (listCount>0){
                    for(int i=0;i<listCount;i++){
                        BxWork bxWork = new BxWork();
                        int a=i
                        if (listCount==1){
                            a = -1;
                        }
                        bxWork=bxWorkRep(bxWork,params,a,bxdNo,type)
                        if (bxWork.bNo>0){
                            bxWorkService.workSave(bxWork)
                        }
                    }
                }
            }
            if (index>1){
                for(int i=1;i<index;i++){
                    BxWork bxWork = new BxWork();
                    bxWork=bxWorkRep(bxWork,params,i,bxdNo,"add")
                    if (bxWork.bNo>0){
                        bxWorkService.workSave(bxWork)
                    }

                }
            }
        }
    }
    def saveZhaoDai(params,String bxdNo,String type){
        int index= Integer.parseInt(params['zdIndex'])
        if (type.equals("add")){
            if (index>1){
                for(int i=1;i<index;i++){
                    BxZhaoDai bxZhaoDai = new BxZhaoDai();
                    bxZhaoDai=bxZhaoDaiRep(bxZhaoDai,params,i,bxdNo,type)
                    if (bxZhaoDai.zNo>0){
                        bxZhaoDaiService.zhaoDaiSave(bxZhaoDai)
                    }

                }
            }
        }else if (type.equals("update")){
            deleteZhaoDai(bxdNo)
            def zNoList=params['zNoList']
            int listCount=0
            if (zNoList!=null){
                listCount=zNoList.size();
                if (listCount>0){
                    for(int i=0;i<listCount;i++){
                        BxZhaoDai bxZhaoDai = new BxZhaoDai();
                        int a=i
                        if (listCount==1){
                            a = -1;
                        }
                        bxZhaoDai=bxZhaoDaiRep(bxZhaoDai,params,a,bxdNo,type)
                        if (bxZhaoDai.zNo>0){
                            bxZhaoDaiService.zhaoDaiSave(bxZhaoDai)
                        }
                    }
                }
            }
            if (index>1){
                for(int i=1;i<index;i++){
                    BxZhaoDai bxZhaoDai = new BxZhaoDai();
                    bxZhaoDai=bxZhaoDaiRep(bxZhaoDai,params,i,bxdNo,"add")
                    if (bxZhaoDai.zNo>0){
                        bxZhaoDaiService.zhaoDaiSave(bxZhaoDai)
                    }

                }
            }
        }
    }
    /**
     * 赋值给对象
     */
    def bxOtherRep(BxOther bo,params,int i,String bxdNo,String type){
        print("other赋值.........")
        if (type.equals("add")){
            bo.bxdNo = bxdNo
            bo.oNo = Integer.parseInt(params['oNo'+i])
            bo.otherId = UUID.randomUUID().toString();
            bo.otherDate = params['otherDate'+i]
            bo.otherOriginalSum = Double.parseDouble(params['otherOriginalSum'+i])
            bo.otherBxRmbSum = Double.parseDouble(params['otherBxRmbSum'+i])
            bo.otherBillsExplain = params['otherBillsExplain'+i]
            bo.otherRemark = params['otherRemark'+i]
        }else if (type.equals("update")){
            if (i==-1){
                bo.bxdNo = bxdNo
                bo.oNo = Integer.parseInt(params['oNoList'])
                bo.otherId = UUID.randomUUID().toString();
                bo.otherDate = params['otherDateList']
                bo.otherOriginalSum = Double.parseDouble(params['otherOriginalSumList'])
                bo.otherBxRmbSum = Double.parseDouble(params['otherBxRmbSumList'])
                bo.otherBillsExplain = params['otherBillsExplainList']
                bo.otherRemark = params['otherRemarkList']
            }else{
                bo.bxdNo = bxdNo
                bo.oNo = Integer.parseInt(params['oNoList'].getAt(i))
                bo.otherId = UUID.randomUUID().toString();
                bo.otherDate = params['otherDateList'].getAt(i)
                bo.otherOriginalSum = Double.parseDouble(params['otherOriginalSumList'].getAt(i))
                bo.otherBxRmbSum = Double.parseDouble(params['otherBxRmbSumList'].getAt(i))
                bo.otherBillsExplain = params['otherBillsExplainList'].getAt(i)
                bo.otherRemark = params['otherRemarkList'].getAt(i)
            }

        }

        return bo;
    }
    def bxLoanRep(BxLoan bl,params,int i,String bxdNo,String type){
        if (type.equals("add")){
            bl.bxdNo = bxdNo
            bl.lNo = Integer.parseInt(params['lNo'+i])
            bl.loanNo = params['loanNo'+i]
            bl.loanDate = params['loanDate'+i]
            bl.loanBalance = Double.parseDouble(params['loanBalance'+i])
            bl.loanBillsCurr = params['loanBillsCurr'+i]
            bl.loanOriginalSum = Double.parseDouble(params['loanOriginalSum'+i])
            bl.loanTheRepayment = Double.parseDouble(params['loanTheRepayment'+i])
            bl.loanRemark = params['loanRemark'+i]
        }else if (type.equals("update")){
            if (i==-1){
                bl.bxdNo = bxdNo
                bl.lNo = Integer.parseInt(params['lNoList'])
                bl.loanNo = params['loanNoList']
                bl.loanDate = params['loanDateList']
                bl.loanBalance = Double.parseDouble(params['loanBalanceList'])
                bl.loanBillsCurr = params['loanBillsCurrList']
                bl.loanOriginalSum = Double.parseDouble(params['loanOriginalSumList'])
                bl.loanTheRepayment = Double.parseDouble(params['loanTheRepaymentList'])
                bl.loanRemark = params['loanRemarkList']
            }else{
                bl.bxdNo = bxdNo
                bl.lNo = Integer.parseInt(params['lNoList'].getAt(i))
                bl.loanNo = params['loanNoList'].getAt(i)
                bl.loanDate = params['loanDateList'].getAt(i)
                bl.loanBalance = Double.parseDouble(params['loanBalanceList'].getAt(i))
                bl.loanBillsCurr = params['loanBillsCurrList'].getAt(i)
                bl.loanOriginalSum = Double.parseDouble(params['loanOriginalSumList'].getAt(i))
                bl.loanTheRepayment = Double.parseDouble(params['loanTheRepaymentList'].getAt(i))
                bl.loanRemark = params['loanRemarkList'].getAt(i)
            }
        }
        return bl;
    }
    def bxZhaoDaiRep(BxZhaoDai bzd,params,int i,String bxdNo,String type){
        if (type.equals("update")){
            if (i==-1){
                bzd.bxdNo = bxdNo
                bzd.zNo = Integer.parseInt(params['zNoList'])
                bzd.zdNo = UUID.randomUUID().toString();
                bzd.zdDate = params['zdDateList']
                bzd.zdExpenseType = params['zdExpenseTypeList']
                bzd.zdOriginalSum = Double.parseDouble(params['zdOriginalSumList'])
                bzd.zdBxRmbSum =Double.parseDouble( params['zdBxRmbSumList'] )
                bzd.zdBillsExplain = params['zdBillsExplainList']
                bzd.zdRemark = params['zdRemarkList']
            }else{
                bzd.bxdNo = bxdNo
                bzd.zNo = Integer.parseInt(params['zNoList'].getAt(i))
                bzd.zdNo = UUID.randomUUID().toString();
                bzd.zdDate = params['zdDateList'].getAt(i)
                bzd.zdExpenseType = params['zdExpenseTypeList'].getAt(i)
                bzd.zdOriginalSum = Double.parseDouble(params['zdOriginalSumList'].getAt(i))
                bzd.zdBxRmbSum =Double.parseDouble( params['zdBxRmbSumList'].getAt(i) )
                bzd.zdBillsExplain = params['zdBillsExplainList'].getAt(i)
                bzd.zdRemark = params['zdRemarkList'].getAt(i)
            }
        }else if (type.equals("add")){
            bzd.bxdNo = bxdNo
            bzd.zNo = Integer.parseInt(params['zNo'+i])
            bzd.zdNo = UUID.randomUUID().toString();
            bzd.zdDate = params['zdDate'+i]
            bzd.zdExpenseType = params['zdExpenseType'+i]
            bzd.zdOriginalSum = Double.parseDouble(params['zdOriginalSum'+i])
            bzd.zdBxRmbSum =Double.parseDouble( params['zdBxRmbSum'+i]   )
            bzd.zdBillsExplain = params['zdBillsExplain'+i]
            bzd.zdRemark = params['zdRemark'+i]
        }
        return bzd;
    }
    def bxWorkRep(BxWork bw,params,int i,String bxdNo,String type){
        if (type.equals("add")){
            bw.bxdNo = bxdNo
            bw.bgNo =  UUID.randomUUID().toString();
            bw.bNo = Integer.parseInt(params['bNo'+i])
            bw.bgDate = params['bgDate'+i]
            bw.bgOriginalSum = Double.parseDouble(params['bgOriginalSum'+i] )
            bw.bgBxRmbSum = Double.parseDouble(params['bgBxRmbSum'+i] )
            bw.bgBillsExplain = params['bgBillsExplain'+i]
            bw.bgRemark = params['bgRemark'+i]
            bw.bgExpenseType = params['bgExpenseType'+i]
        }else if (type.equals("update")){
            if (i==-1){
                bw.bxdNo = bxdNo
                bw.bgNo =  UUID.randomUUID().toString();
                bw.bNo = Integer.parseInt(params['bNoList'])
                bw.bgDate = params['bgDateList']
                bw.bgOriginalSum = Double.parseDouble(params['bgOriginalSumList'] )
                bw.bgBxRmbSum = Double.parseDouble(params['bgBxRmbSumList'])
                bw.bgBillsExplain = params['bgBillsExplainList']
                bw.bgRemark = params['bgRemarkList']
                bw.bgExpenseType = params['bgExpenseTypeList']
            }else{
                bw.bxdNo = bxdNo
                bw.bgNo =  UUID.randomUUID().toString();
                bw.bNo = Integer.parseInt(params['bNoList'].getAt(i))
                bw.bgDate = params['bgDateList'].getAt(i)
                bw.bgOriginalSum = Double.parseDouble(params['bgOriginalSumList'].getAt(i) )
                bw.bgBxRmbSum = Double.parseDouble(params['bgBxRmbSumList'].getAt(i) )
                bw.bgBillsExplain = params['bgBillsExplainList'].getAt(i)
                bw.bgRemark = params['bgRemarkList'].getAt(i)
                bw.bgExpenseType = params['bgExpenseTypeList'].getAt(i)
            }
        }
        return bw;
    }
    def bxTravelRep(BxTravel bt,params,int i,String bxdNo,String type){
        if (type.equals("add")){
            bt.bxdNo = bxdNo
            bt.clNo =  UUID.randomUUID().toString();
            bt.cNo = Integer.parseInt(params['cNo'+i])
            bt.clStartDate = params['clStartDate'+i]
            bt.clEndDate = params['clEndDate'+i]
            bt.clExpenseType = params['clExpenseType'+i]
            bt.clTransport = params['clTransport'+i]
            bt.clTravelDays = Integer.parseInt(params['clTravelDays'+i])
            bt.clOriginalSum = Double.parseDouble(params['clOriginalSum'+i] )
            bt.clBxRmbSum =Double.parseDouble( params['clBxRmbSum'+i] )
            bt.clSeAddress = params['clSeAddress'+i]
            bt.clTravelDaysCount = Integer.parseInt(params['clTravelDaysCount'])
            bt.clTravelDetails = params['clTravelDetails']
            bt.clRemark = params['clRemark'+i]
        }else if (type.equals("update")){
            if (i==-1){
                bt.bxdNo = bxdNo
                bt.clNo =  UUID.randomUUID().toString();
                bt.cNo = Integer.parseInt(params['cNoList'])
                bt.clStartDate = params['clStartDateList']
                bt.clEndDate = params['clEndDateList']
                bt.clExpenseType = params['clExpenseTypeList']
                bt.clTransport = params['clTransportList']
                bt.clTravelDays = Integer.parseInt(params['clTravelDaysList'])
                bt.clOriginalSum = Double.parseDouble(params['clOriginalSumList'] )
                bt.clBxRmbSum =Double.parseDouble( params['clBxRmbSumList'] )
                bt.clSeAddress = params['clSeAddressList']
                bt.clTravelDaysCount = Integer.parseInt(params['clTravelDaysCount'])
                bt.clTravelDetails = params['clTravelDetails']
                bt.clRemark = params['clRemarkList']
            }else{
                bt.bxdNo = bxdNo
                bt.clNo =  UUID.randomUUID().toString();
                bt.cNo = Integer.parseInt(params['cNoList'].getAt(i))
                bt.clStartDate = params['clStartDateList'].getAt(i)
                bt.clEndDate = params['clEndDateList'].getAt(i)
                bt.clExpenseType = params['clExpenseTypeList'].getAt(i)
                bt.clTransport = params['clTransportList'].getAt(i)
                bt.clTravelDays = Integer.parseInt(params['clTravelDaysList'].getAt(i))
                bt.clOriginalSum = Double.parseDouble(params['clOriginalSumList'].getAt(i) )
                bt.clBxRmbSum =Double.parseDouble( params['clBxRmbSumList'].getAt(i) )
                bt.clSeAddress = params['clSeAddressList'].getAt(i)
                bt.clTravelDaysCount = Integer.parseInt(params['clTravelDaysCount'])
                bt.clTravelDetails = params['clTravelDetails']
                bt.clRemark = params['clRemarkList'].getAt(i)
            }
        }
        return bt;
    }

    def examineBxReceipts(){
        Date date = new Date()
//        SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd")
        String nowDate = matter.format(date);
        String currentUserName = springSecurityService.getPrincipal().username;
//        def userL = UserLogin.findByLoginName(currentUserName)
        def user = User.findByUsername(currentUserName)
        def userRoleList = UserRole.findAllByUser(user)
        def role = new Role()
        for (UserRole userRole:userRoleList){
            def role1 = new Role()
            role1 = userRole.role
            def str = role1.description.substring(0,1)
            if (!role1.description.equals("PT") && !role1.description.equals("KJ")
                    && !role1.description.equals("CN") && str.equals("B")) {
                role = role1
                break;
            }
        }
        def taskId = params["taskId"]
        def bxReceiptsId = params["bxReceiptsId"]
        bxReceipt=bxReceiptService.getBxdById(bxReceiptsId).get(0)

        //其他报销信息
        List<BxOther> listOther = new ArrayList<BxOther>()
        listOther=bxOtherService.otherQueryByBxdNo(bxReceiptsId)
        //保存借款信息
        List<BxLoan> listLoan = new ArrayList<BxLoan>()
        listLoan = bxLoanService.loanQueryByBxdNo(bxReceiptsId)
        //保存差旅信息
        List<BxTravel> listTravel = new ArrayList<BxTravel>()
        listTravel = bxTravelService.travelQueryByBxdNo(bxReceiptsId)
//        BxTravel bxTravel = new BxTravel();
//        bxTravel.clTravelDaysCount=Integer.parseInt( params['clTravelDaysCount'])
//        bxTravel.clTravelDetails = params['clTravelDetails']
        //保存办公信息
        List<BxWork> listWork = new ArrayList<BxWork>()
        listWork = bxWorkService.workQueryByBxdNo(bxReceiptsId)
        //保存招待信息
        List<BxZhaoDai> listZhaoDai = new ArrayList<BxZhaoDai>()
        listZhaoDai = bxZhaoDaiService.zhaoDaiQueryByBxdNo(bxReceiptsId)
        def historyLists = handle(taskId,bxReceiptsId)
        def funcCode = params["funcCode"]
        render(view: '/bxReceipt/bxHandle',model: [nowDate:nowDate,user:user,role:role,taskId:taskId,bxReceipt:bxReceipt,funcCode: funcCode,
                listOther:listOther,listLoan:listLoan,listTravel:listTravel,listWork:listWork,listZhaoDai:listZhaoDai,historyLists:historyLists])
//        render(view: '/bxReceipt/bxHandle')
    }

    def examineSave(){
        def examAppHistory = new ExamAppHistory()
        examAppHistory.taskId = params["taskId"]
        examAppHistory.receiptsId = params["bxNo"]
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)
        examAppHistory.examAppName = user.userName
        examAppHistory.examAppNamePosition = user.empPosition
        Date date = new Date()
//        SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd")
        String nowDate = matter.format(date);
        examAppHistory.examAppTime = nowDate
        examAppHistory.examAppIdea = params["examAppIdea"]
        examAppHistory.approveRemark = params["approveRemark"]
        bxReceiptService.examineSave(examAppHistory)


        def taskId = params["taskId"]
        Task task = processEngine.getTaskService().getTask(taskId);
        def executionId = task.getExecutionId()

        workflowFactory.approveTask(processEngine,taskId,params["examAppIdea"]);

        List<ExmAppTask> list = loanAppReceiptsService.getTaskByExecutionId(executionId)
        bxReceipt = new BxReceipt()
        bxReceipt =bxReceiptService.getBxdById(params["bxNo"]).get(0)
        def bxUser = UserLogin.findByEmpNo(bxReceipt.bxEmpNo.toString())
        if (list==null){
            bxReceipt.bxdStatus = "已审核"
            bxReceiptService.bxdSave(bxReceipt)
//            sendEmail(bxUser.getUserName(),params["bxNo"],bxUser.empEmail,0);//用户需要邮箱
        }else{
            def type = params["type"]
            if (type.equals("1")){
                bxReceipt.bxdStatus = "已保存"
                bxReceiptService.bxdSave(bxReceipt)
            }else{
                bxReceipt.bxdStatus = "已提交"
                bxReceiptService.bxdSave(bxReceipt)
            }
            def exmAppTask = list.get(0)
            def nextUserId = exmAppTask.assignId
            def nextUser = UserLogin.findByUserId(nextUserId)
            if (params["examAppIdea"].equals("approve")) {
//                sendEmail(nextUser.getUserName(),params["bxNo"],nextUser.empEmail,1);//用户需要邮箱  通过
            }else{
//                sendEmail(bxUser.getUserName(),params["bxNo"],bxUser.empEmail,2);//用户需要邮箱  不通过
            }
        }
        //发送邮件给下一个办理人
//        def appHistVar = new AppHistVar()
//        appHistVar = loanAppReceiptsService.getNowAppHistVar(user.userId,executionId)
//        def varName = appHistVar.varName
//         if (varName.equals("userId")){
//
//         }else if (varName.equals("first")){
//
//         }else if (varName.equals("second")){
//
//         }else if (varName.equals("third")){
//
//         }else if (varName.equals("fourth")){
//
//         }else if (varName.equals("fifth")){
//
//         }
        returnProcessList()
    }
    /**
     * 任务办理
     * @param taskId
     *      任务ID
     * @return
     */
    def store
    def historyList
    def handle(String taskId,String wfNo){
        store = new TaskStore();
        //通过任务ID查找任务
        Task task = processEngine.getTaskService().getTask(taskId);
        store.setTaskId(taskId);
        store.setExecutionId(task.getExecutionId());
        store.setTaskName(task.getName());
        store.setWfNo(wfNo);
        // 获得当前流程实例中的已办信息
        historyList = findHistoryInstanceDetail(task.getExecutionId());
        return historyList
    }
    /**
     * 查询某流程实例下的已办任务详细信息
     * @param executionId
     *      实例ID
     * @return
     */
    List<TaskStore> findHistoryInstanceDetail(String executionId){
        List<TaskStore> storeList = new ArrayList<TaskStore>();
        //获取已办任务列表
        List<HistoryTask> htList = processEngine.getHistoryService().createHistoryTaskQuery().state(HistoryTask.STATE_COMPLETED).orderAsc(HistoryTaskQuery.PROPERTY_ENDTIME).list();
        // 若该流程中含有子任务
        List<HistoryTask> htList2 = new ArrayList<HistoryTask>();
        for(HistoryTask ht : htList){
            if(ht.getExecutionId().contains(executionId)){
                htList2.add(ht);
            }
        }
        for(Iterator<HistoryTask> iter = htList2.iterator();iter.hasNext();){
            HistoryTask historyTask = iter.next();
            TaskStore taskStore = new TaskStore();
            //获取任务名称
            taskStore.setTaskName(getHistoryTaskInstanceByTaskId(historyTask.getId()).getActivityName());
            if(null!=historyTask.getAssignee()){
                //获取参与者（受托人）的名称
//                taskStore.setAssignee(historyTask.getAssignee());
                User userL = User.findById(historyTask.getAssignee())
                UserLogin userLogin = UserLogin.findByLoginName(userL.username)
                taskStore.setAssignee(userLogin.loginName);
                def userRoleList = UserRole.findAllByUser(userL)
                def role = new Role()
                for (UserRole userRole:userRoleList){
                    def role1 = new Role()
                    role1 = userRole.role
                    def str = role1.description.substring(0,1)
                    if (!role1.description.equals("PT") && !role1.description.equals("KJ")
                            && !role1.description.equals("CN") && str.equals("B")) {
                        role = role1
                        break;
                    }
                }
                taskStore.setExamAppNamePosition(role.authority)
            }
            def date = historyTask.getEndTime()
            def time = ""
            if (null == date ) {
                time = ""
            }else{
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                time = simpleDateFormat.format(date);
            }
            taskStore.setEndTime(time);
            if (historyTask.getOutcome()=='approve'){
                taskStore.setResult("同意");
            }else if (historyTask.getOutcome()=='reject'){
                taskStore.setResult("不同意");
            }
            ExamAppHistory tl = ExamAppHistory.findByTaskId(historyTask.getId());
            if(tl==null){
                taskStore.setRemark("");
            }else{
                taskStore.setRemark(tl.getApproveRemark());
            }
            storeList.add(taskStore);
        }
        return storeList;
    }

    /**
     * 根据任务ID获取历史任务对象
     * @param taskId
     *      任务ID
     * @return
     */
    HistoryTaskInstanceImpl getHistoryTaskInstanceByTaskId(final String taskId){
        return processEngine.execute(new Command<HistoryTaskInstanceImpl>(){
            private static final long serialVersionUID = 1L;
            public HistoryTaskInstanceImpl execute(Environment environment)
            throws Exception {
                Session session = environment.get(Session.class);
                StringBuilder hql = new StringBuilder();
                hql.append("select hti from ").append(HistoryTaskInstanceImpl.class.getName());
                hql.append(" as hti ");
                hql.append("where hti.historyTask.dbid = :taskDbid");
                return (HistoryTaskInstanceImpl) session.createQuery(hql.toString())
                        .setLong("taskDbid", Long.valueOf(taskId)).uniqueResult();
            }
        });
    }

    /**
     * 获得代办列表的数据
     * @return
     */
    def returnProcessList() {
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        def userId = user.id.toString()
//        List<Processes> loan_list = new ArrayList<Processes>();
        List<TaskStore> list = new ArrayList<TaskStore>();
        // 获取当前用户任务列表
        List<Task> taskList = workflowFactory.getTaskByUserId(processEngine,userId);
        if (taskList!=null&&taskList.size()>0){
            for (Task task:taskList){
                //封装表格数据
                ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery()
                        .processDefinitionId( processEngine.getExecutionService().findExecutionById(task.getExecutionId()).getProcessDefinitionId()).uniqueResult();
                TaskStore taskStore = new TaskStore();
                Object loanId = processEngine.getTaskService().getVariable(task.getId(),"loanId");
                //办理链接
//                StringBuffer sbf = new StringBuffer();
//                sbf.append("<a href=\"#\" onclick=handle(\"");
//                sbf.append(task.getId()                                         );
//                sbf.append("\",\""                                                    );
//                sbf.append(wfNo.toString()                                   );
//                sbf.append( "\") >"  );
//                sbf.append("办理</a>"                  );
                taskStore.setWfNo(loanId.toString());
                def s = loanId.toString().substring(0,1)
                def name = ""
                if (s.equals("J")){
                    name = LoanAppReceipts.findByLoanAppReceiptsId(loanId.toString()).loanEmpName;
                }else{
                    name = BxReceipt.findByBxNo(loanId.toString()).bxEmpName;
                }
                taskStore.setUserName(name);
                taskStore.setTaskId(task.getId());
                taskStore.setTaskName(task.getName());
                taskStore.setProcessName(processDefinition.getName());
                def date = task.getCreateTime()
                def dateStr
                if (null == date ){
                    dateStr = ""
                }else{
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    dateStr = simpleDateFormat.format(date);
                }

                taskStore.setCreateTime(dateStr);
//                taskStore.setOperate(sbf.toString())
                list.add(taskStore);
            }
        }
        List<TaskStore> loanList = new ArrayList<TaskStore>();
        List<TaskStore> bxList = new ArrayList<TaskStore>();
        for (TaskStore taskStore:list){
            String str = taskStore.wfNo.substring(0,1)
            if (str.equals("J")){
                loanList.add(taskStore)
            }else if (str.equals("B")){
                bxList.add(taskStore)
            }
        }
        def funcCode = params["funcCode"]
        def strJ = getLimitsStrJ(currentUserName,funcCode)
        def strB = getLimitsStrB(currentUserName,funcCode)
        def a = ""
        def b = ""
        if (strJ.indexOf("C")!=-1){
            a = "C"
        }
        if(strB.indexOf("C")!=-1){
            b = "C"
        }
//        loan_list = processesService.getProcessList();
        render(view: '/processes/processesList', model: [list: list,loanList:loanList,bxList:bxList,userName:user.name,funcCode:funcCode,a:a,b:b])
    }

    /**
     * 得到当前用户作为借款申请中角色的所有权限的字符串
     */
    def getLimitsStrJ(String currentUserName,String funcCode){
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
                    && !role1.description.equals("CN") && str.equals("J")) {
                def roleMenu = RoleMenu.findByRoleIdAndMenu(role1.id,menu)
                if (roleMenu!=null && roleMenu.roleRight!=""){
                    strRoleRight += roleMenu.roleRight
                }
            }
        }
        return strRoleRight
    }

    /**
     * 得到当前用户作为报销申请中角色的所有权限的字符串
     */
    def getLimitsStrB(String currentUserName,String funcCode){
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
                    && !role1.description.equals("CN") && str.equals("B")) {
                def roleMenu = RoleMenu.findByRoleIdAndMenu(role1.id,menu)
                if (roleMenu!=null && roleMenu.roleRight!=""){
                    strRoleRight += roleMenu.roleRight
                }
            }
        }
        return strRoleRight
    }



    /**
     * 赋值给对象
     */
    def bxRep(BxReceipt bxd,params){
        String bxEmpId=params['bxEmpNo']
        bxd.bxEmpNo = Integer.parseInt(bxEmpId)
        bxd.costCenter = params['costCenter']
        bxd.bxEmpIdNumber = params["bxEmpIdNumber"]
        bxd.companyName = params['companyName']
        bxd.bxEmpName = params['bxEmpName']
        bxd.bxEmpPhoneNumber = params['bxEmpPhoneNumber']
        bxd.bxEmpPosition = params['bxEmpPosition']
        bxd.budgetCenter = params['budgetCenter']
        bxd.needMoneyDate =params['needMoneyDate']
        bxd.paymentMode = params['paymentMode']
        bxd.applicationDate = params['applicationDate']
        bxd.managerName = params['managerName']
        bxd.billsCurr = params['billsCurr']

//        bxd.clTravelDetails = params['clTravelDetails']
//        bxd.clTravelDays = Integer.parseInt(params['clTravelDays'])
//        bxd.clStartDate = params['clStartDate']
//        bxd.clEndDate = params['clEndDate']
//        bxd.clSeAddress = params['clSeAddress']
//        bxd.clExpenseType = params['clExpenseType']
//        bxd.clTransport = params['clTransport']
//        bxd.clOriginalSum = Double.parseDouble(params['clOriginalSum'])
//        bxd.clBxRmbSum = Double.parseDouble(params['clBxRmbSum'])
//        bxd.clRemark = params['clRemark']

//        bxd.zdDate = params['zdDate']
//        bxd.zdExpenseType = params['zdExpenseType']
//        bxd.zdOriginalSum = Double.parseDouble(params['zdOriginalSum'] )
//        bxd.zdBxRmbSum =Double.parseDouble( params['zdBxRmbSum'])
//        bxd.zdBillsExplain = params['zdBillsExplain']
//        bxd.zdRemark = params['zdRemark']

//        bxd.bgDate = params['bgDate']
//        bxd.bgExpenseType = params['bgExpenseType']
//        bxd.bgOriginalSum = Double.parseDouble(params['bgOriginalSum'] )
//        bxd.bgBxRmbSum = Double.parseDouble(params['bgBxRmbSum']     )
//        bxd.bgBillsExplain = params['bgBillsExplain']
//        bxd.bgRemark = params['bgRemark']

//        bxd.otherDate = params['otherDate']
//        bxd.otherOriginalSum =Double.parseDouble( params['otherOriginalSum']   )
//        bxd.otherBxRmbSum =Double.parseDouble( params['otherBxRmbSum']    )
//        bxd.otherBillsExplain = params['otherBillsExplain']
//        bxd.otherRemark = params['otherRemark']

        bxd.bxCostCounts =Double.parseDouble( params['bxCostCounts']   )

//        bxd.loanDate =params['loanDate']
//        bxd.loanNo = params['loanNo']
//        bxd.loanBillsCurr = params['loanBillsCurr']
//        bxd.loanOriginalSum =Double.parseDouble( params['loanOriginalSum'] )
//        bxd.loanBalance =Double.parseDouble( params['loanBalance']   )
//        bxd.loanTheRepayment =Double.parseDouble( params['loanTheRepayment'] )
//        bxd.loanRemark = params['loanRemark']

        bxd.payCounts = Double.parseDouble(params['payCounts'] )
        bxd.billsCounts = Integer.parseInt(params['billsCounts'] )
        bxd.expenseCategory = params['expenseCategory']
        bxd.budgetYear = Integer.parseInt(params['budgetYear']  )
        bxd.budgetMonth = Integer.parseInt(params['budgetMonth']  )
        bxd.budgetQuarter = Integer.parseInt(params['budgetQuarter'] )
        bxd.balanceBudgetYear =Double.parseDouble( params['balanceBudgetYear']        )
        bxd.balanceBudgetMonth = Double.parseDouble(params['balanceBudgetMonth']     )
        bxd.balanceBudgetQuarter =Double.parseDouble( params['balanceBudgetQuarter'])
        bxd.budgetYearPro = Double.parseDouble(params['budgetYearPro']        )
        bxd.budgetMonthPro =Double.parseDouble( params['budgetMonthPro']     )
        bxd.budgetQuarterPro = Double.parseDouble(params['budgetQuarterPro'])
        bxd.approvalTime = params['approvalTime']
        bxd.approvalOpinions = params['approvalOpinions']
        bxd.approverPosition = params['approverPosition']
        bxd.approver = params['approver']
        bxd.approvalStatus = params['approvalStatus']
        return  bxd;
    }
    def sysDateFormat(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sysDate = sdf.format(date);
        return  sysDate;
    }

}
