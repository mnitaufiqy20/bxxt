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

class LoanAppReceiptsController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def loanAppReceiptsService = new LoanAppReceiptsService()
    def loanAppReceipts = new LoanAppReceipts()
    def loan_list = new ArrayList<LoanAppReceipts>()
    def processEngine;
    WorkflowFactory  workflowFactory = new WorkflowFactory()

    def index() {
        redirect(action: "list", params: params)
    }

    def loanAppReceiptsQuery() {
//        workflowFactory.applyWorkflow(processEngine,"E:/Groovy/loanAppRec.zip")
        println("LoanAppReceiptsController loanAppReceiptsQuery is loading....")
        def user = (UserLogin)session.getAttribute("user")
        def empNo = user.empNo
        loan_list = new ArrayList<LoanAppReceipts>();
        loan_list = loanAppReceiptsService.loanAppReceiptsQuery(empNo)
        render(view: '/loanAppReceipts/loanAppReceiptsList', model: [loan_list: loan_list])
    }

    def  loanAppReceiptsGJQuery(params){
        loan_list = new ArrayList<LoanAppReceipts>();
        loan_list = loanAppReceiptsService.loanAppReceiptsGJQuery(params)
        render(view: '/loanAppReceipts/loanAppReceiptsList', model: [loan_list: loan_list,startDate:params["startDate"],
                endDate:params["endDate"],loanAppReceiptsId:params["loanAppReceiptsId"],loanStatus:params["loanStatus"]])
    }

    def loanAppReceiptsAdd(params) {
        println("LoanAppReceiptsController loanReceiptsAdd is loading....")
        Date date = new Date()
        SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd")
        String nowDate = matter.format(date);
//        def empNo = params["loanEmpNo"]
//        def emp = new UserLogin()
        def user = (UserLogin)session.getAttribute("user")
//        if (empNo!=null && empNo!=-1) {
//            emp = loanAppReceiptsService.loanAppReceiptsAddChangeEmpNo(empNo)
//            render(view: '/expenseAccount/loanAppReceiptsAdd', model: [nowDate: nowDate,emp:emp])
//        }else{
//            render(view: '/expenseAccount/loanAppReceiptsAdd', model: [nowDate: nowDate])
//        }
        render(view: '/loanAppReceipts/loanAppReceiptsAdd', model: [nowDate: nowDate,user:user])
    }

    //添加保存
    def loanAppReceiptsSave(params) {
        println("LoanAppReceiptsController loanAppReceiptsSave is loading....")
        def loanId = getLoanId()
        def user = (UserLogin)session.getAttribute("user")
        def exmApp = loanAppReceiptsService.getProcessApprove(user.empPosition)
        String type = "";
        if (user.empPosition.equals("公司领导")){
            type = "A";
        }else if (user.empPosition.equals("公司分管领导")){
            type = "B";
        }else if (user.empPosition.equals("公司责任部门领导")){
            type = "C";
        }else if (user.empPosition.equals("部门领导")){
            type = "D";
        }else if (user.empPosition.equals("分部领导")){
            type = "E";
        }else if (user.empPosition.equals("员工")){
            type = "E";
        }
        def map = new HashMap<String, Object>()
        map.put("loanId",loanId)
        map.put("type",type)
        map.put("userId",user.userId)
        if (exmApp.getFirstName()==null){
            map.put("first","")
        }else{
            map.put("first",exmApp.getFirstName())
        }
        if (exmApp.getSecondName()==null){
            map.put("second","")
        }else{
            map.put("second",exmApp.getSecondName())
        }
        if (exmApp.getThirdName()==null){
            map.put("third","")
        }else{
            map.put("third",exmApp.getThirdName())
        }
        if (exmApp.getFourthName()==null){
            map.put("fourth","")
        }else{
            map.put("fourth",exmApp.getFourthName())
        }
        if (exmApp.getFifthName()==null){
            map.put("fifth","")
        }else{
            map.put("fifth",exmApp.getFifthName())
        }

        workflowFactory.startWorkflow(processEngine,"LoanAppRec",map)
        loanAppReceipts = new LoanAppReceipts()
        loanAppReceipts.loanAppReceiptsId = loanId   //单据名称首字母J(1位)+公司代码（4位）+年份月分（4位）+3位流水号
        loanAppReceipts = loanAppRec(loanAppReceipts,params)
        loanAppReceipts.loanStatus = "已保存"
        loanAppReceiptsService.loanAppReceiptsSave(loanAppReceipts)

        render(view: '/loanAppReceipts/loanAppReceiptsUpdate',model: [loanAppReceipts: loanAppReceipts])
    }

    //提交
    def commitLoanAppReceipts(params){
        def action = params["act"]
        if (action.equals("add")){
            def loanId = getLoanId()
            loanAppReceipts = new LoanAppReceipts()
            loanAppReceipts.loanAppReceiptsId = loanId   //单据名称首字母J(1位)+公司代码（4位）+年份月分（4位）+3位流水号
        }else if (params["act"].equals("update")){
            loanAppReceipts = loanAppReceiptsService.getLoanAppReceiptsById(params["loanAppReceiptsId"])
        }
        loanAppReceipts = loanAppRec(loanAppReceipts,params)
        loanAppReceipts.loanStatus = "已提交"
        loanAppReceiptsService.loanAppReceiptsSave(loanAppReceipts)

        def user = (UserLogin)session.getAttribute("user")
        List<Task> list = workflowFactory.getTaskByUserId(processEngine,user.userId);
        if (list!=null&&list.size()>1){
            paiXu(list);
        }
        workflowFactory.approveTask(processEngine,list.get(0).getId(),"approve");

        render(view: '/loanAppReceipts/loanAppReceiptsCommit',model: [loanAppReceipts: loanAppReceipts])
    }

    def editLoanAppReceipts(params){
        loanAppReceipts = loanAppReceiptsService.getLoanAppReceiptsById(params["loanAppReceiptsId"])
        if (loanAppReceipts.loanStatus.equals("已保存")){
            render(view: '/loanAppReceipts/loanAppReceiptsUpdate', model: [loanAppReceipts: loanAppReceipts])
        }else{
            render(view: '/loanAppReceipts/loanAppReceiptsCommit', model: [loanAppReceipts: loanAppReceipts])
        }
    }

    def lookUpLoanAppReceipts(params){
        loanAppReceipts = loanAppReceiptsService.getLoanAppReceiptsById(params["loanAppReceiptsId"])
        def user = (UserLogin)session.getAttribute("user")
        render(view: '/loanAppReceipts/loanAppReceiptsCommit', model: [loanAppReceipts: loanAppReceipts,user: user])
    }

    //修改保存
    def loanAppReceiptsUpdate(params) {
        println("LoanAppReceiptsController loanAppReceiptsUpdate is loading....")
        loanAppReceipts = loanAppReceiptsService.getLoanAppReceiptsById(params["loanAppReceiptsId"])
        loanAppReceipts = loanAppRec(loanAppReceipts,params)
        loanAppReceipts.loanStatus = "已保存"
        loanAppReceiptsService.loanAppReceiptsSave(loanAppReceipts)
        render(view: '/loanAppReceipts/loanAppReceiptsUpdate', model: [loanAppReceipts: loanAppReceipts])
    }

    def examineLoanAppReceipts(params){
        Date date = new Date()
//        SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd")
        String nowDate = matter.format(date);
        def user = (UserLogin)session.getAttribute("user")
        def taskId = params["taskId"]
        def loanAppReceiptsId = params["loanAppReceiptsId"]
        loanAppReceipts = loanAppReceiptsService.getLoanAppReceiptsById(loanAppReceiptsId)
        def historyLists = handle(taskId,loanAppReceiptsId)
        render(view: '/loanAppReceipts/loanAppReceiptsExamine', model: [nowDate:nowDate,user:user,loanAppReceipts: loanAppReceipts,taskId:taskId,historyLists:historyLists])
    }

    def examineSave(params){
        def examAppHistory = new ExamAppHistory()
        examAppHistory.receiptsId = params["loanAppReceiptsId"]
        def user = (UserLogin)session.getAttribute("user")
        examAppHistory.examAppName = user.userName
        examAppHistory.examAppNamePosition = user.empPosition
        Date date = new Date()
//        SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd")
        String nowDate = matter.format(date);
        examAppHistory.examAppTime = nowDate
        examAppHistory.examAppIdea = params["examAppIdea"]

        loanAppReceiptsService.examineSave(examAppHistory)
        def taskId = params["taskId"]
        Task task = processEngine.getTaskService().getTask(taskId);
        def executionId = task.getExecutionId()
        workflowFactory.approveTask(processEngine,taskId,params["examAppIdea"]);
        List<ExmAppTask> list = loanAppReceiptsService.getTaskByExecutionId(executionId)
        if (list==null){
            loanAppReceipts = new LoanAppReceipts()
            loanAppReceipts = loanAppReceiptsService.getLoanAppReceiptsById(params["loanAppReceiptsId"])
            loanAppReceipts.loanStatus = "已审核"
            loanAppReceiptsService.loanAppReceiptsSave(loanAppReceipts)
        }
        returnProcessList()
    }

    //在新增时获得单号
    def getLoanId(){
        String loanId = "J";
        def comNo = "0001"  //公司代码（四位）

        //年月 （四位）
        Date date = new Date()
        SimpleDateFormat matter=new SimpleDateFormat("yyyyMM")
        String dates = matter.format(date);
        println("dates:"+dates)
        def comAndDate = comNo + dates.substring(2,6)
        loanId += comAndDate

        //流水号（三位）
        def serialNumberList = new ArrayList<String>()
        def user = (UserLogin)session.getAttribute("user")
        def empNo = user.empNo
        loan_list = new ArrayList<LoanAppReceipts>()
        loan_list = loanAppReceiptsService.loanAppReceiptsQuery(empNo)
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

    //页面获取值之后赋给一个对象
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

    //通过  loanAppReceiptsId 得到对应的对象
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
                UserLogin userL = UserLogin.findById(historyTask.getAssignee())
                taskStore.setAssignee(userL.userName);
                taskStore.setExamAppNamePosition(userL.empPosition)
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
            UserLogin userL = UserLogin.findById(historyTask.getId())
//            taskStore.setExamAppNamePosition(userL.getEmpPosition());
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

    def returnProcessList() {
        def user = (UserLogin)session.getAttribute("user")
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

//        loan_list = processesService.getProcessList();
        render(view: '/processes/processesList', model: [loan_list: list,userName:user.userName])
    }

}
