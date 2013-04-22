package com.chd.bx.flow

import org.jbpm.api.task.Task
import org.jbpm.api.ProcessDefinition
import processes.TaskStore
import jbpm.WorkflowFactory
import com.chd.bx.login.UserLogin
import java.text.SimpleDateFormat
import com.chd.bx.security.User
import com.chd.bx.expenseAccount.LoanAppReceipts
import com.chd.bx.bxd.BxReceipt
import com.chd.bx.security.Menu
import com.chd.bx.security.UserRole
import com.chd.bx.security.Role
import com.chd.bx.security.RoleMenu

/***
 * 在办箱业务逻辑处理
 */
class ProcessesController {
    def processesService = new ProcessesService()
    def processEngine
    def springSecurityService
    WorkflowFactory  workflowFactory = new WorkflowFactory()
    def index() {
        render(view: '../processes/processesList.gsp')
    }

    def initProcess() {
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


    def processesListDetail() {
        String type = request.getParameter("type");
        List<Processes> list = new ArrayList<Processes>();
        list = processesService.getProcessListDetail(type);
        render(view: '/processes/processesListDetail', model: [list: list])
    }
}
