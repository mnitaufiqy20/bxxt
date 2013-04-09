package processes;

/**
 * 封装任务管理页面实体
 * User: 左鹏
 * Date: 12-12-10
 * Time: 下午5:29
 */
public class TaskStore {

    //申请单号
    private String wfNo;
    //任务ID
    private String taskId;
    //用户ID
    private String userid;
    //申请人姓名
    public String userName;
    //任务参与者
    private String assignee;
    //任务名称
    private String taskName;
    //流程实例ID
    private String executionId;
    //流程名称
    private String processName;
    //创建时间
    private String createTime;
    //结束时间
    private String endTime;
    //url
    private String formResourceName;
    //操作
    private String operate;
    //审批意见
    private String remark;
    //审批操作
    private String result;
    // 审批人职位
    private String examAppNamePosition;

    public String getWfNo() {
        return wfNo;
    }
    public void setWfNo(String wfNo) {
        this.wfNo = wfNo;
    }
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAssignee() {
        return assignee;
    }
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getExecutionId() {
        return executionId;
    }
    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }
    public String getProcessName() {
        return processName;
    }
    public void setProcessName(String processName) {
        this.processName = processName;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getFormResourceName() {
        return formResourceName;
    }
    public void setFormResourceName(String formResourceName) {
        this.formResourceName = formResourceName;
    }
    public String getOperate() {
        return operate;
    }
    public void setOperate(String operate) {
        this.operate = operate;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getExamAppNamePosition() {
        return examAppNamePosition;
    }
    public void setExamAppNamePosition(String examAppNamePosition) {
        this.examAppNamePosition = examAppNamePosition;
    }
}
