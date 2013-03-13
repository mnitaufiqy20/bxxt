package com.chd.bx.examAppHistory

class ExamAppHistory {
    String taskId                        //任务ID
    String receiptsId                   //单号
    String examAppName                  // 审批人
    String examAppNamePosition        // 审批人职位
    String examAppTime                 //审批时间
    String examAppIdea                 //审批意见
    String approveRemark               //办理意见

    static constraints = {
        taskId maxSize: 20,blank: true,nullable: true
        receiptsId nullable: false, maxSize: 15
        examAppName nullable: false, maxSize: 20
        examAppNamePosition nullable: false, maxSize: 20
        examAppTime nullable: false, maxSize: 20
        examAppIdea nullable: false, maxSize: 20
        approveRemark maxSize: 100,blank: true,nullable: true
    }
    static mapping = {
        table 'TH_EXAM_APP_HISTORY'
        id generator:'sequence', params:[sequence:'SEQ_TH_EXAM_APP_HISTORY_ID']
    }
}
