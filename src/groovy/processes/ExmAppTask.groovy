package processes

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-8
 * Time: 下午3:52
 * To change this template use File | Settings | File Templates.
 */
class ExmAppTask {
    String dbId;
    String executionId;
    String assignId;
    String taskDefName;

    String getDbId() {
        return dbId
    }

    void setDbId(String dbId) {
        this.dbId = dbId
    }

    String getAssignId() {
        return assignId
    }

    void setAssignId(String assignId) {
        this.assignId = assignId
    }

    String getExecutionId() {
        return executionId
    }

    void setExecutionId(String executionId) {
        this.executionId = executionId
    }

    String getTaskDefName() {
        return taskDefName
    }

    void setTaskDefName(String taskDefName) {
        this.taskDefName = taskDefName
    }
}
