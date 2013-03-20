package com.chd.bx.flowManage

import jbpm.WorkflowFactory
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile

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
        def strMsg = ""
        def f = request.getFile("filePath");
        if(!f.empty){//判断文件不为空
            String name = f.getOriginalFilename()//获得文件原始的名称
            def file = new File("grails-app/Upload/"+name)
            if(!file.exists()){
                file.mkdirs()//目录不存在则创建
            }
            f.transferTo(file)
            try{
                workflowFactory.applyWorkflow(processEngine,file.getAbsolutePath())
                strMsg = "发布成功！"
            }catch (Exception e) {
                e.printStackTrace()
                strMsg = "发布失败！"
            }
        }else if(f.empty){
            flash.message ="文件不能为空"
        }
        render(view: '/flowManage/flowSheetInfuse', model: [strMsg: strMsg])
    }
}
