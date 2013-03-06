package com.chd.bx.flow

import  com.chd.bx.flow.ProcessesService
/***
 * 在办箱业务逻辑处理
 */
class ProcessesController {

    def processesService = new ProcessesService()

    def index() {
        render(view: '../processes/processesList.gsp')
    }

    def initProcess() {
        List<Processes> loan_list = new ArrayList<Processes>();//费用报销单
        List<Processes> jkd_list = new ArrayList<Processes>();//借款单
        String type="1";
        loan_list=processesService.init(type);
        type="2";
        jkd_list = processesService.init(type);
        render(view: '/processes/processesList', model: [loan_list: loan_list,jkd_list:jkd_list])
    }

    def processesListDetail() {
        String type = request.getParameter("type");
        List<Processes> list = new ArrayList<Processes>();
        list = processesService.getProcessListDetail(type);
        render(view: '/processes/processesListDetail', model: [list: list])
    }
}
