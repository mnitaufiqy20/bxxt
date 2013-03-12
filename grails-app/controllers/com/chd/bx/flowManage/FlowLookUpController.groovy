package com.chd.bx.flowManage

import org.jbpm.api.ProcessDefinition
import jbpm.WorkflowFactory
import processes.Process
import org.jbpm.api.RepositoryService

class FlowLookUpController {
    def processEngine;

    WorkflowFactory  workflowFactory = new WorkflowFactory()
    def index() {
        redirect(action: "list", params: params)
    }

    def flowLookUp(){
        List<Process> list = new ArrayList<Process>();
        //获取流程列表
        List<ProcessDefinition> proList = workflowFactory.searchWorkflow(processEngine);
        if (proList!=null&&proList.size()>0){
            //封装表格数据
            for (ProcessDefinition pro:proList){
                Process process = new Process();
                process.setProcessId(pro.getId())
                process.setProcessName(pro.getName());
                process.setDescription(pro.getDescription());
                process.setDeploymentId(pro.getDeploymentId())
                list.add(process);
            }
        }
        render(view: '/flowLookUp/flowLookUp',model: [list:list,text: ""])
    }

    /**
     * 查看流程图
     * @author
     * @param id
     * @return
     */
    def process;
    def show() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String processId = params.processId;
        //通过流程ID查询流程实例
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().processDefinitionId(processId).list();
        //封装页面数据
        process = new Process();
        if (processDefinitions!=null&&processDefinitions.size()>0){
            process.setProcessName(processDefinitions.get(0).getName().toString());
            process.setDescription(processDefinitions.get(0).getDescription().toString());
            process.setProcessId(processDefinitions.get(0).getId().toString())
        }
        render(view: "/flowLookUp/show",proces:process)
    }

    /**
     * 显示流程图
     * @author
     * @return
     */
    def showImage(){
        String processId = params.processId;
        //传递参数
        process = new Process();
        process.setProcessId(processId);
        render(view: "processImage",process:process)
    }

    /**
     * 打印流程图
     * @author
     * @return
     */
    def processImage(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String processId = params.processId;
        //查找流程实例
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processId).uniqueResult();
        //查找流程图并且写出图片
        InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getImageResourceName());
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
        return null;
    }

    /**
     * 删除流程
     * @author
     * @return
     */
    def deleteProcess(){
        String flag = "true";
        String deploymentId = params.deploymentId;
        RepositoryService repositoryService = processEngine.getRepositoryService();
        try{
            //删除流程
            repositoryService.deleteDeployment(deploymentId);
        }catch (Exception e){
            e.printStackTrace();
            flag = "false";
            println("有未完成的流程实例！");
        }
        List list = new ArrayList();
        render(view: '/flowLookUp/flowLookUp',model: [list:list,text: flag])
    }

}
