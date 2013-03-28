package com.chd.bx.sapSystem

import com.chd.bx.accSubSafeguard.AccSubSafeguard
import com.chd.bx.expenseAccount.LoanAppReceipts
import sapSystem.LoanCerIntegrationOutput

class LoanCerIntegrationController {
    def loanCerIntegrationService = new LoanCerIntegrationService()
    def loanAppReceiptsService
    def index() {
        redirect(action: "list", params: params)
    }

    def loanCerIntegration(params){
        def type = params["type"]
        def loanId = params["loanAppReceiptsId"]
        def loanAppReceipts = new LoanAppReceipts()
        def accSubjectImportList = AccSubjectImport.findAllByCompanyCode()
        if (type.equals("loan")){
            loanAppReceipts = LoanAppReceipts.findByLoanAppReceiptsId(loanId)
            accSubjectImportList = AccSubjectImport.findAllByCompanyCode(loanAppReceipts.loanCompanyNo)
        }
        render(view: '/loanCerIntegration/loanCerIntegration',model:[loanAppReceipts:loanAppReceipts,type:type,accSubjectImportList:accSubjectImportList])
    }
    def loanCerIntegrationSave(){
//        def type = params["type"]
        def loanId = params["loanAppReceiptsId"]
        def loanAppReceipts = new LoanAppReceipts()
        loanAppReceipts = LoanAppReceipts.findByLoanAppReceiptsId(loanId)
        def loanCerIntegrationOutput = new LoanCerIntegrationOutput()
        loanCerIntegrationOutput.companyCode = loanAppReceipts.loanCompanyNo
        loanCerIntegrationOutput.jkrq = loanAppReceipts.loanBegDate
        loanCerIntegrationOutput.xkrq = loanAppReceipts.loanParagraphDate
        loanCerIntegrationOutput.jkdh = loanAppReceipts.loanAppReceiptsId
        loanCerIntegrationOutput.jkye = loanAppReceipts.loanBalance
        loanCerIntegrationOutput.ygbh = loanAppReceipts.loanEmpNo
        loanCerIntegrationOutput.fkfs = loanAppReceipts.loanPaymentType
        loanCerIntegrationOutput.yt = loanAppReceipts.loanPurpose
        def str = loanAppReceipts.loanPaymentType
        if (str.equals("银行转账")){
            loanCerIntegrationOutput.xykm = params["bankSubject"]
        }else{
            loanCerIntegrationOutput.xykm = params["readySubject"]
        }
        def accSubSafeguard = new AccSubSafeguard()
        accSubSafeguard = AccSubSafeguard.findByAppType("借款单")
        loanCerIntegrationOutput.jkkm = accSubSafeguard.appSub
        String msg = loanCerIntegrationService.serviceMethod(loanCerIntegrationOutput)
        if (msg.equals("请求查询成功")){
            loanAppReceipts.loanStatus = "已过账"
            loanAppReceiptsService.loanAppReceiptsSave(loanAppReceipts)
        }
        render(view: '/loanAppReceipts/loanAppReceiptsCommit',model: [loanAppReceipts: loanAppReceipts])
    }
}
