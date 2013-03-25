package com.chd.bx.flowManage

import com.chd.bx.flow.ExmApp

class FlowConfigService {

    def exmAppSave(ExmApp exmApp) {
        try {
            if (exmApp.save(flush: true)) {
                println("success")
            } else {
                println("error")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

}
