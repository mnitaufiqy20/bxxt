package com.chd.bx.flow



class ProcessesService {

    def serviceMethod() {
           //
    }

    def getProcessList(String type) {
        List<Processes> list = Processes.findAllByType(type, [max: 3, offset: 0, sort: "lastUpdateTime", order: "desc"])
        if (list != null ) {
            if(!list.empty){
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

    def init(String type){
        List<Processes> loan_list = new ArrayList<Processes>();
        loan_list = getProcessList(type);
        if (loan_list==null){
            loan_list= new ArrayList<Processes>();
        }
        return   loan_list
    }
}
