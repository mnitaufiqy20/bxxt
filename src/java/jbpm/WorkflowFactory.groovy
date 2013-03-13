package jbpm

import java.util.zip.ZipInputStream

import org.jbpm.api.*

/**
 * jBPM4.4API实现类
 * User:
 * Date: 12-11-16
 * Time: 下午2:52
 */
class WorkflowFactory {

    /**
     * 发布流程
     * @param processEngine
     *      引擎对象
     * @param filePath
     *      文件路径
     */
    public void applyWorkflow(ProcessEngine processEngine,String filePath){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        InputStream is;
        try {
            // zip文件的路径
            is = new FileInputStream(filePath);
            ZipInputStream zis = new ZipInputStream(is);
            repositoryService.createDeployment().addResourcesFromZipInputStream(zis).deploy();
            is.close();
            zis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 流程列表
     * @param processEngine
     *      引擎对象
     * @return
     *      返回流程列表集合
     */
    public List<ProcessDefinition> searchWorkflow(ProcessEngine processEngine){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        return repositoryService.createProcessDefinitionQuery().list();
    }

    /**
     * 启动流程
     * @param processEngine
     *      引擎对象
     * @param workflowId
     *      流程ID
     * @param variables
     *      流程参数
     */
    public void startWorkflow(ProcessEngine processEngine,String workflowId,Map<String, Object> variables){
        ExecutionService executionService = processEngine.getExecutionService();
        ProcessInstance processInstance = executionService.startProcessInstanceByKey(workflowId,variables);
        executionService.createVariables(processInstance.getId(), variables,true);
    }

    /**
     * 获取用户任务
     * @param processEngine
     *      引擎对象
     * @param userId
     *      当前用户ID
     * @return
     */
    public getTaskByUserId(ProcessEngine processEngine,String userId){
        TaskService taskService = processEngine.getTaskService();
        return taskService.createTaskQuery().assignee(userId).orderAsc(TaskQuery.PROPERTY_CREATEDATE).list();
    }

    /**
     * 办理任务
     * @param processEngine
     *      引擎对象
     * @param workflowId
     *      流程ID
     * @param approveType
     *      办理结果
     */
    public void approveTask(ProcessEngine processEngine,String workflowId,String approveType){
        TaskService taskService = processEngine.getTaskService();
        if(approveType!=null&&approveType!=""){
            taskService.completeTask(workflowId,approveType);
        } else{
            taskService.completeTask(workflowId);
        }

    }


}
