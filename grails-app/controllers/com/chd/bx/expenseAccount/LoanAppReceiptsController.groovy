package com.chd.bx.expenseAccount

import java.text.SimpleDateFormat
import com.chd.bx.login.UserLogin
import jbpm.WorkflowFactory
import org.jbpm.api.task.Task
import com.chd.bx.examAppHistory.ExamAppHistory
import org.jbpm.api.history.HistoryTask
import org.jbpm.api.history.HistoryTaskQuery
import processes.TaskStore
import org.jbpm.pvm.internal.history.model.HistoryTaskInstanceImpl
import org.jbpm.api.cmd.Command
import org.jbpm.api.cmd.Environment
import org.hibernate.Session
import org.jbpm.api.ProcessDefinition
import processes.ExmAppTask
import email.SendMail
import processes.AppHistVar
import com.chd.bx.security.User
import com.chd.bx.security.RoleMenu
import com.chd.bx.security.UserRole
import com.chd.bx.security.Menu
import com.chd.bx.security.Role
import com.chd.bx.bxd.BxReceipt

class LoanAppReceiptsController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def loanAppReceiptsService = new LoanAppReceiptsService()
    def loanAppReceipts = new LoanAppReceipts()
    def loan_list = new ArrayList<LoanAppReceipts>()
    def processEngine;
    def springSecurityService
    WorkflowFactory  workflowFactory = new WorkflowFactory()

    def index() {
        redirect(action: "list", params: params)
    }

    /**
     * 查询
     * @return
     */
    def loanAppReceiptsQuery() {
//        workflowFactory.applyWorkflow(processEngine,"E:/Groovy/loanAppRec.zip")
        println("LoanAppReceiptsController loanAppReceiptsQuery is loading....")
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        def user2 = UserLogin.findByLoginName(currentUserName)
        def empNo = user.empNo
        def menuId = params["menuId"]
        loan_list = new ArrayList<LoanAppReceipts>();

//        def str = getLimitsStr(currentUserName)
//        def a = ""
//        def b = ""
//        def c = ""
//        if (str.indexOf("V")!=-1){
//          a = "V"
//        }
//        if(str.indexOf("N")!=-1){
//          b = "N"
//        }
//        if(str.indexOf("E")!=-1){
//            c = "E"
//        }
        def userRoleList = UserRole.findAllByUser(user)
        for (UserRole userRole:userRoleList){
            def role = new Role()
            role = userRole.role
            if (role.description.equals("PT")) {
                loan_list = LoanAppReceipts.findAllByLoanEmpIdNumber(user2.idNumber)
            } else if (role.description.equals("KJ")) {
                loan_list = loanAppReceiptsService.loanAppReceiptsQuery("已审核")
                break;
            } else if (role.description.equals("CN")) {
                loan_list = loanAppReceiptsService.loanAppReceiptsQuery("已过账")
                break;
            }
        }
//        loan_list = loanAppReceiptsService.loanAppReceiptsQuery()
//        render(view: '/loanAppReceipts/loanAppReceiptsList', model: [loan_list: loan_list,menuId:menuId,a:a,b:b,c:c])
        render(view: '/loanAppReceipts/loanAppReceiptsList', model: [loan_list: loan_list,menuId:menuId])
    }

    /**
     * 得到当前用户所有权限的字符串
     */
    def getLimitsStr(String currentUserName){
        def menu = Menu.findById(params["menuId"])
        def u = User.findByUsername(currentUserName)
        def userRoleList = UserRole.findAllByUser(u)
        def str = ""
        for (UserRole userRole:userRoleList){
            def roleMenu = RoleMenu.findByRoleIdAndMenu(userRole.roleId,menu)
            if (roleMenu!=null && roleMenu.roleRight!=""){
                str += roleMenu.roleRight
            }
        }
        return str
    }
    /**
     * 高级查询
     * @param params
     * @return
     */
    def  loanAppReceiptsGJQuery(params){
        loan_list = new ArrayList<LoanAppReceipts>();
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
        loan_list = loanAppReceiptsService.loanAppReceiptsGJQuery(params,idNum,index)
//        loan_list = loanAppReceiptsService.loanAppReceiptsGJQuery(params)
        render(view: '/loanAppReceipts/loanAppReceiptsList', model: [loan_list: loan_list,startDate:params["startDate"],
                endDate:params["endDate"],loanAppReceiptsId:params["loanAppReceiptsId"],loanStatus:params["loanStatus"]])
    }

    /**
     * 添加
     * @param params
     * @return
     */
    def loanAppReceiptsAdd(params) {
        println("LoanAppReceiptsController loanReceiptsAdd is loading....")
        Date date = new Date()
        SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd")
        String nowDate = matter.format(date);
//        def empNo = params["loanEmpNo"]
//        def emp = new UserLogin()
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)
//        if (empNo!=null && empNo!=-1) {
//            emp = loanAppReceiptsService.loanAppReceiptsAddChangeEmpNo(empNo)
//            render(view: '/expenseAccount/loanAppReceiptsAdd', model: [nowDate: nowDate,emp:emp])
//        }else{
//            render(view: '/expenseAccount/loanAppReceiptsAdd', model: [nowDate: nowDate])
//        }
        def menuId = params["menuId"]
        render(view: '/loanAppReceipts/loanAppReceiptsAdd', model: [nowDate: nowDate,user:user,menuId: menuId])
    }

    /**
     *  保存
     * @param params
     * @return
     */
    def loanAppReceiptsSave(params) {
        println("LoanAppReceiptsController loanAppReceiptsSave is loading....")
        def loanId = getLoanId()
        def loanAppReceipts = new LoanAppReceipts();
//        loanAppReceipts.id=0;
        loanAppReceipts.loanAppReceiptsId = loanId   //单据名称首字母J(1位)+公司代码（4位）+年份月分（4位）+3位流水号
        loanAppReceipts = loanAppRec(loanAppReceipts,params)
        loanAppReceipts.loanStatus = "已保存"
        loanAppReceiptsService.loanAppReceiptsSave(loanAppReceipts);
        def menuId = params["menuId"]
        startFlow(loanId);
        render(view: '/loanAppReceipts/loanAppReceiptsUpdate',model: [loanAppReceipts: loanAppReceipts,menuId: menuId])
    }

    /**
     * 提交申请
     * @param params
     * @return
     */
    def commitLoanAppReceipts(params){
        def action = params["act"]
        def loanAppReceipts = new LoanAppReceipts()
        if (action.equals("add")){
            def loanId = getLoanId()
            loanAppReceipts.loanAppReceiptsId = loanId   //单据名称首字母J(1位)+公司代码（4位）+年份月分（4位）+3位流水号
            startFlow(loanId);
        }else if (params["act"].equals("update")){
            loanAppReceipts = loanAppReceiptsService.getLoanAppReceiptsById(params["loanAppReceiptsId"])
        }
        loanAppReceipts = loanAppRec(loanAppReceipts,params)
        loanAppReceipts.loanStatus = "已提交"
        loanAppReceiptsService.loanAppReceiptsSave(loanAppReceipts)

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
        def menuId = params["menuId"]
        render(view: '/loanAppReceipts/loanAppReceiptsCommit',model: [loanAppReceipts: loanAppReceipts,menuId: menuId])
    }

    /**
     * 启动流程
     */
    def startFlow(String loanId){
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        def user2 = UserLogin.findByLoginName(currentUserName)
        def userRoleList = UserRole.findAllByUser(user)
        def role1 = new Role()
        for (UserRole userRole:userRoleList){
            def role = new Role()
            role = userRole.role
            if (!role.description.equals("PT") && !role.description.equals("KJ") && !role.description.equals("CN")) {
                role1=role
                break;
            }
        }
        def exmApp = loanAppReceiptsService.getProcessApprove(role1.authority,user2.companyNo,"LOAN")
        if(exmApp!=null) {
            String type = "";
            if (exmApp.firstName!=null && exmApp.secondName==null && exmApp.thirdName==null && exmApp.fourthName==null && exmApp.fifthName==null){
                type = "A";
            }else if (exmApp.firstName!=null && exmApp.secondName!=null && exmApp.thirdName==null && exmApp.fourthName==null && exmApp.fifthName==null){
                type = "B";
            }else if (exmApp.firstName!=null && exmApp.secondName!=null && exmApp.thirdName!=null && exmApp.fourthName==null && exmApp.fifthName==null){
                type = "C";
            }else if (exmApp.firstName!=null && exmApp.secondName!=null && exmApp.thirdName!=null && exmApp.fourthName!=null && exmApp.fifthName==null){
                type = "D";
            }else if (exmApp.firstName!=null && exmApp.secondName!=null && exmApp.thirdName!=null && exmApp.fourthName!=null && exmApp.fifthName!=null){
                type = "E";
            }
            def map = new HashMap<String, Object>()
            map.put("loanId",loanId)
            map.put("type",type)
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
            workflowFactory.startWorkflow(processEngine,"LoanAppRec",map)
        }
    }

    /**
     * 修改
     * @param params
     * @return
     */
    def editLoanAppReceipts(params){
        loanAppReceipts = loanAppReceiptsService.getLoanAppReceiptsById(params["loanAppReceiptsId"])
        def menuId = params["menuId"]
        if (loanAppReceipts.loanStatus.equals("已保存")){
            render(view: '/loanAppReceipts/loanAppReceiptsUpdate', model: [loanAppReceipts: loanAppReceipts,menuId: menuId])
        }else{
            render(view: '/loanAppReceipts/loanAppReceiptsCommit', model: [loanAppReceipts: loanAppReceipts,menuId: menuId])
        }
    }

    /**
     * 查看
     * @param params
     * @return
     */
    def lookUpLoanAppReceipts(params){
        def loanAppReceipts = new LoanAppReceipts()
        loanAppReceipts = loanAppReceiptsService.getLoanAppReceiptsById(params["loanAppReceiptsId"])
//        def user = (UserLogin)session.getAttribute("user")
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        def userRoleList = UserRole.findAllByUser(user)
        def role = new Role()
        for (UserRole userRole:userRoleList){
            def role1 = new Role()
            role1 = userRole.role
            if (!role1.description.equals("PT") && !role1.description.equals("KJ") && !role1.description.equals("CN")) {
                role=role1
                break;
            }
        }
        def menuId = params["menuId"]
        render(view: '/loanAppReceipts/loanAppReceiptsCommit', model: [loanAppReceipts: loanAppReceipts,user: user,role:role,menuId: menuId])
    }

    /**
     * 修改后保存
     * @param params
     * @return
     */
    def loanAppReceiptsUpdate(params) {
        println("LoanAppReceiptsController loanAppReceiptsUpdate is loading....")
        loanAppReceipts = loanAppReceiptsService.getLoanAppReceiptsById(params["loanAppReceiptsId"])
        loanAppReceipts = loanAppRec(loanAppReceipts,params)
        loanAppReceipts.loanStatus = "已保存"
        loanAppReceiptsService.loanAppReceiptsSave(loanAppReceipts)
        def menuId = params["menuId"]
        render(view: '/loanAppReceipts/loanAppReceiptsUpdate', model: [loanAppReceipts: loanAppReceipts,menuId: menuId])
    }

    /**
     * 待办箱点击办理
     * @param params
     * @return
     */
    def examineLoanAppReceipts(params){
        Date date = new Date()
//        SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd")
        String nowDate = matter.format(date);
        String currentUserName = springSecurityService.getPrincipal().username;
        def userL = UserLogin.findByLoginName(currentUserName)
        def user = User.findByUsername(currentUserName)
        def userRoleList = UserRole.findAllByUser(user)
        def role = new Role()
        for (UserRole userRole:userRoleList){
            def role1 = new Role()
            role1 = userRole.role
            if (!role1.description.equals("PT") && !role1.description.equals("KJ") && !role1.description.equals("CN")) {
                role=role1
                break;
            }
        }
        def taskId = params["taskId"]
        def loanAppReceiptsId = params["loanAppReceiptsId"]
        loanAppReceipts = loanAppReceiptsService.getLoanAppReceiptsById(loanAppReceiptsId)
        def historyLists = handle(taskId,loanAppReceiptsId)
//        String type = loanAppReceiptsId.toString().substring(0,1);
        def menuId = params["menuId"]
//        if("J".equals(type)){
            render(view: '/loanAppReceipts/loanAppReceiptsExamine', model: [nowDate:nowDate,user:user,loanAppReceipts: loanAppReceipts,
                    taskId:taskId,historyLists:historyLists,menuId: menuId,role:role])
//        }else if ("B".equals(type)){
//            render(view: '/bxReceipt/bxHandle', model: [nowDate:nowDate,user:user,loanAppReceipts: loanAppReceipts,taskId:taskId,historyLists:historyLists,menuId: menuId])
//        }
//        render(view: '/loanAppReceipts/loan')
    }

    /**
     * 办理后保存
     * @param params
     * @return
     */
    def examineSave(params){
        def examAppHistory = new ExamAppHistory()
        examAppHistory.taskId = params["taskId"]
        examAppHistory.receiptsId = params["loanAppReceiptsId"]
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
        loanAppReceiptsService.examineSave(examAppHistory)


        def taskId = params["taskId"]
        Task task = processEngine.getTaskService().getTask(taskId);
        def executionId = task.getExecutionId()

        workflowFactory.approveTask(processEngine,taskId,params["examAppIdea"]);

        List<ExmAppTask> list = loanAppReceiptsService.getTaskByExecutionId(executionId)
        loanAppReceipts = new LoanAppReceipts()
        loanAppReceipts = loanAppReceiptsService.getLoanAppReceiptsById(params["loanAppReceiptsId"])
        def loanUser = UserLogin.findByEmpNo(loanAppReceipts.loanEmpNo)
        if (list==null){
            loanAppReceipts.loanStatus = "已审核"
            loanAppReceiptsService.loanAppReceiptsSave(loanAppReceipts)
//            sendEmail(loanUser.getUserName(),params["loanAppReceiptsId"],loanUser.empEmail,0);//用户需要邮箱
        }else{
            def type = params["type"]
            if (type.equals("1")){
                loanAppReceipts.loanStatus = "已保存"
                loanAppReceiptsService.loanAppReceiptsSave(loanAppReceipts)
            }else{
                loanAppReceipts.loanStatus = "已提交"
                loanAppReceiptsService.loanAppReceiptsSave(loanAppReceipts)
            }
            def exmAppTask = list.get(0)
            def nextUserId = exmAppTask.assignId
            def nextUser = UserLogin.findByUserId(nextUserId)
            if (params["examAppIdea"].equals("approve")) {
//                sendEmail(nextUser.getUserName(),params["loanAppReceiptsId"],nextUser.empEmail,1);//用户需要邮箱  通过
            }else{
//                sendEmail(loanUser.getUserName(),params["loanAppReceiptsId"],loanUser.empEmail,2);//用户需要邮箱  不通过
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
     * Ldap帐号申请结束，根据结果发送Email
     * @param res
     *      1 成功，2  审批中， 3  失败
     */
    public void sendEmail(java.lang.String userName,String uid,String email,int res){
        SendMail mail = new SendMail();
        if (res==0){
            //申请成功
            mail.send(userName,uid,request.getRealPath("/email/SuccessMail.txt"),email,res)
        }else if (res==1){
            //审批人
            mail.send(userName,uid,request.getRealPath("/email/ApproverMail.txt"),email,res)
        }else if (res==2){
            //申请失败
            mail.send(userName,uid,request.getRealPath("/email/FailureMail.txt"),email,res)
        }

    }

    /**
     *  在新增时获得申请单号
     * @return  loanId 单据号
     */
    def getLoanId(){
        String loanId = "J";
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = UserLogin.findByLoginName(currentUserName)

        def comNo = user.companyNo  //公司代码（四位）

        //年月 （四位）
        Date date = new Date()
        SimpleDateFormat matter=new SimpleDateFormat("yyyyMM")
        String dates = matter.format(date);
        println("dates:"+dates)
        def comAndDate = comNo + dates.substring(2,6)
        loanId += comAndDate

        //流水号（三位）
        def serialNumberList = new ArrayList<String>()
        loan_list = new ArrayList<LoanAppReceipts>()
        loan_list = LoanAppReceipts.findAllByLoanCompanyNo(user.companyNo)
        def n1 = 0
        def n2 = 0
        if(loan_list !=null && loan_list.size()>0){
            for (int i=0;i<loan_list.size();i++){
                def str = loan_list.get(i).getLoanAppReceiptsId()
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
            loanId += "001"
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
                loanId += serialNumber
            }else{
                loanId += t
            }
        }
        return loanId
    }

    /**
     *  页面获取值之后赋给一个对象
     * @param loanApp  申请对象
     * @param params   页面参数
     * @return  loanApp 赋值后的对象
     */
    def loanAppRec(LoanAppReceipts loanApp,params){
        loanApp.loanEmpNo = params["loanEmpNo"]
        loanApp.loanCostCenter = params["loanCostCenter"]
        loanApp.loanCompanyNo = params["loanCompanyNo"]
//        loanApp.loanDepartmentNo = params["loanDepartmentNo"]
        loanApp.loanDepartmentNo = "02"
        loanApp.loanEmpName = params["loanEmpName"]
        loanApp.loanEmpPhone = params["loanEmpPhone"]
        loanApp.loanEmpPosition = params["loanEmpPosition"]
        loanApp.loanEmpIdNumber = params["loanEmpIdNumber"]
        loanApp.loanBudgetCenter = params["loanBudgetCenter"]
        loanApp.loanParagraphDate = params["loanParagraphDate"]
//        loanApp.loanParagraphDate = new Date(System.currentTimeMillis())
        loanApp.loanPaymentType = params["loanPaymentType"]
        loanApp.loanMoney = Double.parseDouble(params["loanMoney"])
        loanApp.loanAlreadyRefund = Double.parseDouble(params["loanAlreadyRefund"])
        loanApp.loanBalance = Double.parseDouble(params["loanBalance"])
//        loanApp.loanBalance = Double.parseDouble(params["loanMoney"]) - Double.parseDouble(params["loanAlreadyRefund"])
        loanApp.loanBegDate = params["loanBegDate"]
//        loanApp.loanBegDate = new Date(System.currentTimeMillis())
        loanApp.loanOperatorName = params["loanOperatorName"]
        loanApp.loanPurpose = params["loanPurpose"]
        loanApp.loanRemark = params["loanRemark"]
        return loanApp
    }

    /**
     *  通过  loanAppReceiptsId 得到对应的对象
     * @param loanAppReceiptsId   单据号
     * @return  loanAppReceipts  相应的对象
     */
    //
    def getLoanAppReceiptsById(String loanAppReceiptsId){
        List<LoanAppReceipts> list= loanAppReceiptsService.getLoanAppReceiptsById(loanAppReceiptsId)
        loanAppReceipts = new LoanAppReceipts()
        if(list!=null && list.size()>0){
            loanAppReceipts = list.get(0)
        }
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
                def role1 = new Role()
                for (UserRole userRole:userRoleList){
                    def role = new Role()
                    role = userRole.role
                    if (!role.description.equals("PT") && !role.description.equals("KJ") && !role.description.equals("CN")) {
                        role1=role
                        break;
                    }
                }
                taskStore.setExamAppNamePosition(role1.authority)
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
        def user = UserLogin.findByLoginName(currentUserName)
        def userId = user.userId
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
                def name = LoanAppReceipts.findByLoanAppReceiptsId(loanId.toString()).loanEmpName;
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

//        loan_list = processesService.getProcessList();
        render(view: '/processes/processesList', model: [list: list,loanList:loanList,bxList:bxList,userName:user.userName])
    }

}
