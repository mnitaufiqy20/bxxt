package com.chd.bx.flowManage

import jbpm.WorkflowFactory

/**
 * 流程图部署或发布
 */
class FlowSheetInfuseController {
    def processEngine;
    WorkflowFactory  workflowFactory = new WorkflowFactory()

    def index() {
        redirect(action: "list", params: params)
    }
    def flowSheetInfuse(){
        def strMsg = ""
        render(view: '/flowManage/flowSheetInfuse', model: [strMsg: strMsg])
    }
    def flowSheetInfusePath(params){
        def filePath = params["filePath"]
        def strMsg = ""
        try{
            workflowFactory.applyWorkflow(processEngine,filePath)
            strMsg = "发布成功！"
        }catch (Exception e) {
            e.printStackTrace()
            strMsg = "发布失败！"
        }
        render(view: '/flowManage/flowSheetInfuse', model: [strMsg: strMsg])
    }
}
