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
//        def filePath = params["filePath"]
        def strMsg = ""
        def f = request.getFile("filePath");
        if((!f.empty)&&(f.size<200000)){//判断文件不为空且小于设定的大小
            String name = f.getOriginalFilename()//获得文件原始的名称
            def file = new File("grails-app/Upload/"+name)
            if(!file.exists()){
                file.mkdirs()//目录不存在则创建
            }
            f.transferTo( file )
            System.out.print(file.getAbsolutePath())
            try{
                workflowFactory.applyWorkflow(processEngine,file.getAbsolutePath())
                strMsg = "发布成功！"
            }catch (Exception e) {
                e.printStackTrace()
                strMsg = "发布失败！"
            }
            //将得到的文件转化成指定的文件                                                                          flash.message=”文件正好”
            //def myFileInstance = new MyFile(filePath:file.getAbsolutePath())
           // myFileInstance.save(flush:true)//保存文件的地址
           // redirect(action:”list”)
        }/*else if(f.empty){
            flash.message =”文件不能为空”
            render(view:”upload”)
        }else if(f.size>=200000){
            flash.message =”文件有点大”
            render(view:”upload”)
        }*/







        render(view: '/flowManage/flowSheetInfuse', model: [strMsg: strMsg])
    }
}
