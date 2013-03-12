package com.chd.bx.flowManage

class FlowConfigController {

    def index() {
        redirect(action: "list", params: params)
    }

    def flowConfigIndex(){
        render(view: '/flowConfig/flowConfigIndex')
    }
    def flowConfig(){
        render(view: '/flowConfig/flowConfig')
    }
}
