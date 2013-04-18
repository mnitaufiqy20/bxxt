package com.chd.bx.sapSystem

import com.chd.bx.bxd.BxReceipt
import com.chd.bx.security.User
import com.chd.bx.security.Role
import com.chd.bx.security.UserRole
import sapSystem.BxCerIntegrationHeadOutput
import sapSystem.BxCerIntegrationLineOutput
import com.chd.bx.bxd.BxOther
import com.chd.bx.bxd.BxLoan
import com.chd.bx.bxd.BxTravel
import com.chd.bx.bxd.BxWork
import com.chd.bx.bxd.BxZhaoDai
import com.chd.bx.accSubSafeguard.AccSubSafeguard

class BxCerIntegrationController {
    def bxReceiptService
    def bxCerIntegrationService
    def springSecurityService
    def bxOtherService
    def bxLoanService
    def bxTravelService
    def bxWorkService
    def bxZhaoDaiService

    def index() {
        redirect(action: "list", params: params)
    }

    def bxCerIntegration(params){
        def type = params["type"]
        def bxNo
        def bxReceipt
        def readySubjectList
        def bankSubjectList

        if (type.equals("fybx")){
            bxNo = params["bxNo"]
            bxReceipt = new BxReceipt()
            bxReceipt = BxReceipt.findByBxNo(bxNo)
            readySubjectList = AccSubjectImport.findAllByCompanyCodeAndSubject(bxReceipt.bxNo,"1")
            bankSubjectList = AccSubjectImport.findAllByCompanyCodeAndSubject(bxReceipt.bxNo,"5")
            render(view: '/loanCerIntegration/loanCerIntegration',model:[bxReceipt:bxReceipt,type:type,
                    readySubjectList:readySubjectList,bankSubjectList:bankSubjectList])
//            bxReceipt.bxdStatus = "已过帐"
//            bxReceiptService.bxdSave(bxReceipt)
        }else if (type.equals("bxfk")){
            bxNo = params["bxNo"]
            bxReceipt = new BxReceipt()
            bxReceipt = BxReceipt.findByBxNo(bxNo)
            bxReceipt.bxdStatus = "已付款"
            bxReceiptService.bxdSave(bxReceipt)
            String currentUserName = springSecurityService.getPrincipal().username;
            def user = User.findByUsername(currentUserName)
            def role = new Role()
            role = getRole()
            def menuId = params["menuId"]
            render(view: '/bxReceipt/bxReceiptCommit', model: [bxReceipt: bxReceipt,user: user,role:role,menuId: menuId])
        }
    }

    def bxCerIntegrationSave(){
        def bxNo = params["bxNo"]
        def bxReceipt = new BxReceipt()
        bxReceipt = BxReceipt.findByBxNo(bxNo)

        //根据付款方式取科目
        def str = bxReceipt.paymentMode
        def typeSub = ""
        if (str.equals("银行转账")){
            typeSub = params["bankSubject"]
        }else{
            typeSub = params["readySubject"]
        }

        //成本中心
        def costCenter = bxReceipt.costCenter

        //给抬头栏赋值
        BxCerIntegrationHeadOutput bxCerIntegrationHeadOutput = new BxCerIntegrationHeadOutput()
        bxCerIntegrationHeadOutput.companyCode = bxReceipt.companyName
        bxCerIntegrationHeadOutput.pjbb = bxReceipt.billsCurr
        bxCerIntegrationHeadOutput.bxdh = bxReceipt.bxNo
        bxCerIntegrationHeadOutput.sqrq = bxReceipt.applicationDate

        //给行项目赋值
        List<BxCerIntegrationLineOutput> bxCerIntegrationLineOutputList = new ArrayList<BxCerIntegrationLineOutput>()
        BxCerIntegrationLineOutput bxCerIntegrationLineOutput

        //差旅信息
        List<BxTravel> listTravel = new ArrayList<BxTravel>()
        listTravel = bxTravelService.travelQueryByBxdNo(bxNo)
        if (listTravel!=null && listTravel.size()>0){
            for (BxTravel travel:listTravel){
                bxCerIntegrationLineOutput  = new BxCerIntegrationLineOutput()
                bxCerIntegrationLineOutput.bxdh = bxNo
                def accSubSafeguard = new AccSubSafeguard()
                accSubSafeguard = AccSubSafeguard.findByAppTypeAndCostKindAndCostType("费用报销单","差旅费",travel.clExpenseType)
                if (accSubSafeguard!=null && accSubSafeguard!=""){
                    bxCerIntegrationLineOutput.fykm = accSubSafeguard.appSub
                }else{
                    bxCerIntegrationLineOutput.fykm = ""
                }

                bxCerIntegrationLineOutput.ybje = travel.clOriginalSum.toString()
                bxCerIntegrationLineOutput.bz = travel.clRemark
                bxCerIntegrationLineOutput.kostl = costCenter
                bxCerIntegrationLineOutput.xykm = typeSub
                bxCerIntegrationLineOutput.sjbx = travel.clBxRmbSum.toString()
                bxCerIntegrationLineOutput.jkdh = ""      //借款单号
                bxCerIntegrationLineOutput.jkkm = ""//借款费用会计科目
                bxCerIntegrationLineOutput.bcje = ""//借款费用本次还款金额
                bxCerIntegrationLineOutput.ygbh = ""//员工编号
                bxCerIntegrationLineOutputList.add(bxCerIntegrationLineOutput)
            }
        }
        BxTravel bxTravel = new BxTravel();
        bxTravel=bxTravelService.travelGetOneByBxdNo(bxNo)
        if (bxTravel==null){
            bxTravel = new BxTravel();
            bxTravel.clTravelDaysCount=0
            bxTravel.clTravelDetails = ""
        }

        //招待信息
        List<BxZhaoDai> listZhaoDai = new ArrayList<BxZhaoDai>()
        listZhaoDai = bxZhaoDaiService.zhaoDaiQueryByBxdNo(bxNo)
        if (listZhaoDai!=null && listZhaoDai.size()>0){
            for (BxZhaoDai bxZhaoDai:listZhaoDai){
                bxCerIntegrationLineOutput  = new BxCerIntegrationLineOutput()
                bxCerIntegrationLineOutput.bxdh = bxNo
                def accSubSafeguard = new AccSubSafeguard()
                accSubSafeguard = AccSubSafeguard.findByAppTypeAndCostKindAndCostType("费用报销单","招待费",bxZhaoDai.zdExpenseType)
                if (accSubSafeguard!=null && accSubSafeguard!=""){
                    bxCerIntegrationLineOutput.fykm = accSubSafeguard.appSub
                }else{
                    bxCerIntegrationLineOutput.fykm = ""
                }

                bxCerIntegrationLineOutput.ybje = bxZhaoDai.zdOriginalSum.toString()
                bxCerIntegrationLineOutput.bz = bxZhaoDai.zdRemark
                bxCerIntegrationLineOutput.kostl = costCenter
                bxCerIntegrationLineOutput.xykm = typeSub
                bxCerIntegrationLineOutput.sjbx = bxZhaoDai.zdBxRmbSum.toString()
                bxCerIntegrationLineOutput.jkdh = ""      //借款单号
                bxCerIntegrationLineOutput.jkkm = ""//借款费用会计科目
                bxCerIntegrationLineOutput.bcje = ""//借款费用本次还款金额
                bxCerIntegrationLineOutput.ygbh = ""//员工编号
                bxCerIntegrationLineOutputList.add(bxCerIntegrationLineOutput)
            }
        }

        //办公信息
        List<BxWork> listWork = new ArrayList<BxWork>()
        listWork = bxWorkService.workQueryByBxdNo(bxNo)
        if (listWork!=null && listWork.size()>0){
            for (BxWork bxWork:listWork){
                bxCerIntegrationLineOutput  = new BxCerIntegrationLineOutput()
                bxCerIntegrationLineOutput.bxdh = bxNo
                def accSubSafeguard = new AccSubSafeguard()
                accSubSafeguard = AccSubSafeguard.findByAppTypeAndCostKindAndCostType("费用报销单","办公费",bxWork.bgExpenseType)
                if (accSubSafeguard!=null && accSubSafeguard!=""){
                    bxCerIntegrationLineOutput.fykm = accSubSafeguard.appSub
                }else{
                    bxCerIntegrationLineOutput.fykm = ""
                }

                bxCerIntegrationLineOutput.ybje = bxWork.bgOriginalSum.toString()
                bxCerIntegrationLineOutput.bz = bxWork.bgRemark
                bxCerIntegrationLineOutput.kostl = costCenter
                bxCerIntegrationLineOutput.xykm = typeSub
                bxCerIntegrationLineOutput.sjbx = bxWork.bgBxRmbSum.toString()
                bxCerIntegrationLineOutput.jkdh = ""      //借款单号
                bxCerIntegrationLineOutput.jkkm = ""//借款费用会计科目
                bxCerIntegrationLineOutput.bcje = ""//借款费用本次还款金额
                bxCerIntegrationLineOutput.ygbh = ""//员工编号
                bxCerIntegrationLineOutputList.add(bxCerIntegrationLineOutput)
            }
        }

        //其他信息
        List<BxOther> listOther = new ArrayList<BxOther>()
        listOther=bxOtherService.otherQueryByBxdNo(bxNo)
        if (listOther!=null && listOther.size()>0){
            for (BxOther bxOther:listOther){
                bxCerIntegrationLineOutput  = new BxCerIntegrationLineOutput()
                bxCerIntegrationLineOutput.bxdh = bxNo
                def accSubSafeguard = new AccSubSafeguard()
                accSubSafeguard = AccSubSafeguard.findByAppTypeAndCostKind("费用报销单","其他费用")
                if (accSubSafeguard!=null && accSubSafeguard!=""){
                    bxCerIntegrationLineOutput.fykm = accSubSafeguard.appSub
                }else{
                    bxCerIntegrationLineOutput.fykm = ""
                }

                bxCerIntegrationLineOutput.ybje = bxOther.otherOriginalSum.toString()
                bxCerIntegrationLineOutput.bz = bxOther.otherRemark
                bxCerIntegrationLineOutput.kostl = costCenter
                bxCerIntegrationLineOutput.xykm = typeSub
                bxCerIntegrationLineOutput.sjbx = bxOther.otherBxRmbSum.toString()
                bxCerIntegrationLineOutput.jkdh = ""      //借款单号
                bxCerIntegrationLineOutput.jkkm = ""//借款费用会计科目
                bxCerIntegrationLineOutput.bcje = ""//借款费用本次还款金额
                bxCerIntegrationLineOutput.ygbh = ""//员工编号
                bxCerIntegrationLineOutputList.add(bxCerIntegrationLineOutput)
            }
        }


        //借款信息
        List<BxLoan> listLoan = new ArrayList<BxLoan>()
        listLoan = bxLoanService.loanQueryByBxdNo(bxNo)
        if (listLoan!=null && listLoan.size()>0){
            for (BxLoan bxLoan:listLoan){
                bxCerIntegrationLineOutput  = new BxCerIntegrationLineOutput()
                bxCerIntegrationLineOutput.bxdh = bxNo
//                def accSubSafeguard = new AccSubSafeguard()
//                accSubSafeguard = AccSubSafeguard.findByAppTypeAndCostKind("费用报销单","其他费用")
//                if (accSubSafeguard!=null && accSubSafeguard!=""){
//                    bxCerIntegrationLineOutput.fykm = accSubSafeguard.appSub
//                }else{
                    bxCerIntegrationLineOutput.fykm = ""
//                }

                bxCerIntegrationLineOutput.ybje = bxLoan.loanOriginalSum.toString()
                bxCerIntegrationLineOutput.bz = bxLoan.loanRemark
                bxCerIntegrationLineOutput.kostl = costCenter
                bxCerIntegrationLineOutput.xykm = typeSub
                bxCerIntegrationLineOutput.sjbx = bxLoan.loanBalance.toString()
                bxCerIntegrationLineOutput.jkdh = bxLoan.loanNo      //借款单号
                def accSubSafeguard2 = new AccSubSafeguard()
                accSubSafeguard2 = AccSubSafeguard.findByAppType("借款单")
                if(accSubSafeguard2!=null && accSubSafeguard2!=""){
                    bxCerIntegrationLineOutput.jkkm = accSubSafeguard2.appSub//借款费用会计科目
                }else{
                    bxCerIntegrationLineOutput.jkkm = ""//借款费用会计科目
                }

                bxCerIntegrationLineOutput.bcje = bxLoan.loanTheRepayment.toString()//借款费用本次还款金额
                bxCerIntegrationLineOutput.ygbh = ""//员工编号 (费/借/当)
                bxCerIntegrationLineOutputList.add(bxCerIntegrationLineOutput)
            }
        }

        String msg = bxCerIntegrationService.serviceMethod(bxCerIntegrationHeadOutput,bxCerIntegrationLineOutputList)
        if (msg.equals("请求查询成功")){
            bxReceipt.bxdStatus = "已过账"
            bxReceiptService.bxdSave(bxReceipt)
        }
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        def role = new Role()
        role = getRole()
        def menuId = params["menuId"]
        render(view: '/bxReceipt/bxReceiptCommit', model: [bxReceipt: bxReceipt,user: user,role:role,menuId: menuId])
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
                    && !role1.description.equals("CN") && str.equals("B")) {
                role = role1
                break;
            }
        }
        return role
    }
}
