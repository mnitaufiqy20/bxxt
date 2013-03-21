package com.chd.bx.accSubSafeguard

class AccSubSafeguardService {

    def accSubSafeguardSave(AccSubSafeguard accSubSafeguard) {
        try {
            if (accSubSafeguard.save(flush: true)) {
                println("success")
            } else {
                println("error")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
