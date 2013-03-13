package processes;

/**
 * 封装流程管理页面实体
 * User:
 * Date: 12-12-10
 * Time: 下午4:34
 */
public class Process {

    //流程定义ID
    private String processId;
    //流程ID
    private String deploymentId;
    //流程名称
    private String processName;
    //流程描述
    private String description;
    //操作
    private String operate;

    public String getProcessId() {
        return processId;
    }
    public void setProcessId(String processId) {
        this.processId = processId;
    }
    public String getDeploymentId() {
        return deploymentId;
    }
    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }
    public String getProcessName() {
        return processName;
    }
    public void setProcessName(String processName) {
        this.processName = processName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getOperate() {
        return operate;
    }
    public void setOperate(String operate) {
        this.operate = operate;
    }
}
