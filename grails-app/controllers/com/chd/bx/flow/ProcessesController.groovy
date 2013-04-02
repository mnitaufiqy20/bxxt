package com.chd.bx.flow

import org.jbpm.api.task.Task
import org.jbpm.api.ProcessDefinition
import processes.TaskStore
import jbpm.WorkflowFactory
import com.chd.bx.login.UserLogin
import java.text.SimpleDateFormat
import com.chd.bx.security.User

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
        render(view: '/processes/processesList', model: [loan_list: list,userName:user.name])
    }

    def processesListDetail() {
        String type = request.getParameter("type");
        List<Processes> list = new ArrayList<Processes>();
        list = processesService.getProcessListDetail(type);
        render(view: '/processes/processesListDetail', model: [list: list])
    }
}
