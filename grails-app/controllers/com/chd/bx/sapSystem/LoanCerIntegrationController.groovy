package com.chd.bx.sapSystem

import com.chd.bx.accSubSafeguard.AccSubSafeguard
import com.chd.bx.expenseAccount.LoanAppReceipts
import sapSystem.LoanCerIntegrationOutput
import com.chd.bx.security.User
import com.chd.bx.security.UserRole
import com.chd.bx.security.Role

class LoanCerIntegrationController {
    def loanCerIntegrationService
    def loanAppReceiptsService
    def springSecurityService
    def index() {
        redirect(action: "list", params: params)
    }

    def loanCerIntegration(params){
        def type = params["type"]
        def loanId
        def loanAppReceipts
        def readySubjectList
        def bankSubjectList
        if (type.equals("loan")){
            loanId = params["loanAppReceiptsId"]
            loanAppReceipts = new LoanAppReceipts()
            loanAppReceipts = LoanAppReceipts.findByLoanAppReceiptsId(loanId)
            readySubjectList = AccSubjectImport.findAllByCompanyCode(loanAppReceipts.loanCompanyNo,"1")
            bankSubjectList = AccSubjectImport.findAllByCompanyCode(loanAppReceipts.loanCompanyNo,"5")
            render(view: '/loanCerIntegration/loanCerIntegration',model:[loanAppReceipts:loanAppReceipts,type:type,
                    readySubjectList:readySubjectList,bankSubjectList:bankSubjectList])
        }else if (type.equals("jkfk")){
            loanId = params["loanAppReceiptsId"]
            loanAppReceipts = new LoanAppReceipts()
            loanAppReceipts = LoanAppReceipts.findByLoanAppReceiptsId(loanId)
            loanAppReceipts.loanStatus = "已付款"
            loanAppReceiptsService.loanAppReceiptsSave(loanAppReceipts)
            String currentUserName = springSecurityService.getPrincipal().username;
            def user = User.findByUsername(currentUserName)
            def role = new Role()
            role = getRole()
            def menuId = params["menuId"]
            render(view: '/loanAppReceipts/loanAppReceiptsCommit', model: [loanAppReceipts: loanAppReceipts,user: user,role:role,menuId: menuId])
        }

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
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        def role = new Role()
        role = getRole()
        def menuId = params["menuId"]
        render(view: '/loanAppReceipts/loanAppReceiptsCommit', model: [loanAppReceipts: loanAppReceipts,user: user,role:role,menuId: menuId])
    }

    /**
     * 获得角色
     * @return role
     */
    def getRole(){
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        def userRoleList = UserRole.findAllByUser(user)
        def role = new Role()
        for (UserRole userRole:userRoleList){
            def role1 = new Role()
            role1 = userRole.role
            def str = role1.description.substring(0,1)
            if (!role1.description.equals("PT") && !role1.description.equals("KJ")
                    && !role1.description.equals("CN") && str.equals("J")) {
                role = role1
                break;
            }
        }
        return role
    }
}
