package com.chd.bx.flow



class ProcessesService {

    def serviceMethod() {
           //
    }

    def getProcessList() {
        List<Processes> list = Processes.findAllByType("1", [max: 3, offset: 0, sort: "lastUpdateTime", order: "desc"])
        if (list != null ) {
            if(list.size()>1){
            return list
            }
        }
    }
    def getProcessListDetail(String type){
        List<Processes>list = Processes.findAllByType(type,[offset: 0,sort: "lastUpdateTime",order: "desc"])
        if (list!=null&&list.size()>0){
            return list
        }
    }
}
