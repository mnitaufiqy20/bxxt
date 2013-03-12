package com.chd.bx.expenseAccount

import java.text.SimpleDateFormat
import com.chd.bx.login.UserLogin

class BudgetReportReceiptsController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def budgetReportReceiptsService = new BudgetReportReceiptsService()
    def budgetReportReceipts = new BudgetReportReceipts()
    def budgetReportReceipts1 = new BudgetReportReceipts()
    def budgetReportReceipts2 = new BudgetReportReceipts()
    def budgetReportReceipts3 = new BudgetReportReceipts()
    def budgetReportReceipts4 = new BudgetReportReceipts()
    def budgetReportReceiptsT1 = new BudgetReportReceiptsTemp()
    def budgetReportReceiptsT2 = new BudgetReportReceiptsTemp()
    def budgetReportReceiptsT3 = new BudgetReportReceiptsTemp()
    def budget_list = new ArrayList<BudgetReportReceipts>()

    def index() {
        redirect(action: "list", params: params)
    }
    /**
     * 主数据--报销标准查看
     * @author  MengMin
     * @return
     * @remark  此功能尚未完成，还需要附件上传后，从该表取出数据。
     */
    def bxStandard(){
        //未从数据库中取数据
        render(view: '/businessInfo/bxStandard')
    }
    /**
     * 主数据--预算费用查看
     * @author MengMin
     * @return
     */
    def budgetDetail() {
        String year = params["name"]
        if(year==null){
            Date date = new Date()
            SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd")
            String nowDate = matter.format(date);
            year = nowDate.substring(0,4)
        }
        //差旅费
        budgetReportReceipts1 = new BudgetReportReceiptsTemp()
        budgetReportReceipts1 = budgetReportReceiptsService.budgetDetailQuery(year,"大龙发电公司","生安部","差旅费")
        // 招待费
        budgetReportReceipts2 = new BudgetReportReceiptsTemp()
        budgetReportReceipts2 = budgetReportReceiptsService.budgetDetailQuery(year,"大龙发电公司","生安部","招待费")
        //办公费
        budgetReportReceipts3 = new BudgetReportReceiptsTemp()
        budgetReportReceipts3 = budgetReportReceiptsService.budgetDetailQuery(year,"大龙发电公司","生安部","办公费")
        //合计
        budgetReportReceipts4 = new BudgetReportReceiptsTemp()
        budgetReportReceipts4 = budgetReportReceiptsService.budgetDetailQuery(year,"大龙发电公司","生安部","合计")

        render(view: '/businessInfo/budgetDetail', model: [budgetReportReceipts1: budgetReportReceipts1,
                budgetReportReceipts2: budgetReportReceipts2,budgetReportReceipts3: budgetReportReceipts3,
                budgetReportReceipts4: budgetReportReceipts4])
    }
    def budgetReportReceipts() {
        println("BudgetReportReceiptsController budgetReportReceipts is loading....")
        Date date = new Date()
        SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd")
        String nowDate = matter.format(date);
        String year = nowDate.substring(0,4)
        budget_list = new ArrayList<BudgetReportReceipts>()
        budget_list =  budgetReportReceiptsService.budgetReportReceiptsQuery()
        String str = ""
        if (budget_list != null && budget_list.size()>0){
            List<String> listS = new ArrayList<String>();
            listS.add(budget_list.get(0).budgetYear);
            str += budget_list.get(0).budgetYear+";"
            for(int i=0;i<budget_list.size()-1;i++){
                boolean flag = true;
                for(def j=0;j<listS.size();j++){
                    if(budget_list.get(i+1).budgetYear.equals(listS.get(j))){
                        flag = false;
                        j=-1;
                        break;
                    }
                }
                if(flag){
                    listS.add(budget_list.get(i+1).budgetYear);
                    str += budget_list.get(i+1).budgetYear+";"
                }
            }
        }
        def user = (UserLogin)session.getAttribute("user")
        render(view: '/budgetReportReceipts/budgetReportReceipts', model: [nowDate: nowDate,year: year,budgetYear:str,user:user])
    }

    def checkBudgetYear(){
        String name = params["name"]
        budget_list = new ArrayList<BudgetReportReceipts>()
        budget_list = budgetReportReceiptsService.budgetReportReceiptsQuery()
        Iterator it = budget_list.iterator()
        while (it.hasNext()) {
            BudgetReportReceipts budgetReportReceipts = (BudgetReportReceipts) it.next()
            if (budgetReportReceipts.budgetYear.equals(name)) {
                render("true")
                return
            }
        }
        return null;
    }

    def  budgetReportReceiptsSave(params) {
        def budgetId = getBudgetId()
        budgetReportReceipts1 = new BudgetReportReceipts()
        budgetReportReceipts2 = new BudgetReportReceipts()
        budgetReportReceipts3 = new BudgetReportReceipts()
        budgetReportReceipts4 = new BudgetReportReceipts()
        for (int i=0;i<4;i++){
            budgetReportReceipts = new BudgetReportReceipts()
            def budgetReportReceiptsT = new BudgetReportReceiptsTemp()
            budgetReportReceipts.budgetReportReceiptsId = budgetId   //单据名称首字母S(1位)+公司代码（4位）+年份（4位）+3位流水号
            budgetReportReceiptsT.budgetReportReceiptsId = budgetId
            if (i == 0){
                budgetReportReceipts1 = budgetReportRec(budgetReportReceipts,params,"tra")
                budgetReportReceiptsService.budgetReportReceiptsSave(budgetReportReceipts1)
                budgetReportReceiptsT1 = budgetReportRecT(budgetReportReceiptsT,params,"tra")
                budgetReportReceiptsService.budgetReportReceiptsTSave(budgetReportReceiptsT1)
            }else if (i == 1){
                budgetReportReceipts2 = budgetReportRec(budgetReportReceipts,params,"rec")
                budgetReportReceiptsService.budgetReportReceiptsSave(budgetReportReceipts2)
                budgetReportReceiptsT2 = budgetReportRecT(budgetReportReceiptsT,params,"rec")
                budgetReportReceiptsService.budgetReportReceiptsTSave(budgetReportReceiptsT2)
            }else if (i == 2){
                budgetReportReceipts3 = budgetReportRec(budgetReportReceipts,params,"off")
                budgetReportReceiptsService.budgetReportReceiptsSave(budgetReportReceipts3)
                budgetReportReceiptsT3 = budgetReportRecT(budgetReportReceiptsT,params,"off")
                budgetReportReceiptsService.budgetReportReceiptsTSave(budgetReportReceiptsT3)
            }else if (i == 3){
                budgetReportReceipts4 = budgetReportRec(budgetReportReceipts,params,"sum")
                budgetReportReceiptsService.budgetReportReceiptsSave(budgetReportReceipts4)
            }
        }
        render(view: '/budgetReportReceipts/budgetReportReceipts', model: [budgetReportReceipts1: budgetReportReceipts1,
                budgetReportReceipts2: budgetReportReceipts2,budgetReportReceipts3: budgetReportReceipts3,
                budgetReportReceipts4: budgetReportReceipts4])
    }

    //在新增时获得单号
    def getBudgetId(){
        String budgetId = "S";
        def comNo = "0001"  //公司代码（四位）

        //年月 （四位）
        Date date = new Date()
        SimpleDateFormat matter=new SimpleDateFormat("yyyy")
        String dates = matter.format(date);
        def comAndDate = comNo + dates
        budgetId += comAndDate

        //流水号（三位）
        def serialNumberList = new ArrayList<String>()
        budget_list = new ArrayList<BudgetReportReceipts>()
        budget_list = budgetReportReceiptsService.budgetReportReceiptsQuery()
        def n1 = 0
        def n2 = 0
        if(budget_list !=null && budget_list.size()>0){
            for (int i=0;i<budget_list.size();i++){
                def str = budget_list.get(i).getBudgetReportReceiptsId()
                def s = str.substring(1,9)
                if (comAndDate.equals(s)){
                    if (serialNumberList.size()==0){
                        n1 = Integer.parseInt(str.substring(9,str.length()))
                        serialNumberList.add(str.substring(9,str.length()))
                    }else{
                        n2 = Integer.parseInt(str.substring(9,str.length()))
                        if (n2 > n1){
                            serialNumberList.clear()
                            serialNumberList.add(str.substring(9,str.length()))
                            n1 = n2
                        }
                    }
                }
            }
        }
        if (serialNumberList.size()==0){
            budgetId += "001"
        }else{
            int t =  Integer.parseInt(serialNumberList.get(0))+1
            int a = t/10
            def serialNumber = ""
            if (t<100){
                if (a>0){
                    serialNumber = "0"+t
                }else{
                    serialNumber = "00"+t
                }
                budgetId += serialNumber
            }else{
                budgetId += t
            }
        }
        return budgetId
    }

    def budgetReportRec(BudgetReportReceipts budRep,params,String str){
        budRep.budgetReportEmpNo = params["budgetReportEmpNo"]
        budRep.budgetReportEmpName = params["budgetReportEmpName"]
        budRep.budgetReportEmpPosition = params["budgetReportEmpPosition"]
        budRep.budgetReportDate = params["budgetReportDate"]
        budRep.budgetCompanyNo = params["budgetCompanyNo"]
        budRep.budgetDepartmentNo = params["budgetDepartmentNo"]
        budRep.budgetCostCenter = params["budgetCostCenter"]
        budRep.budgetCenter = params["budgetCenter"]
        budRep.budgetYear = params["budgetYear"]

        budRep.budgetCostType = params[str+"Expense"]

        if (str.equals("sum")){
            budRep.budgetTypeTotal = params["allSum"]
        }else{
            budRep.budgetTypeTotal = params[str+"Sum"]
        }

        budRep.budgetFirstQuarter = params[str+"Quarter1"]
        budRep.budgetSecondQuarter = params[str+"Quarter2"]
        budRep.budgetThirdQuarter = params[str+"Quarter3"]
        budRep.budgetFourthQuarter = params[str+"Quarter4"]

        budRep.budgetJanMoney = params[str+"Month1"]
        budRep.budgetFebMoney = params[str+"Month2"]
        budRep.budgetMarMoney = params[str+"Month3"]
        budRep.budgetAprilMoney = params[str+"Month4"]
        budRep.budgetMayMoney = params[str+"Month5"]
        budRep.budgetJuneMoney = params[str+"Month6"]
        budRep.budgetJulyMoney = params[str+"Month7"]
        budRep.budgetAugustMoney = params[str+"Month8"]
        budRep.budgetSepMoney = params[str+"Month9"]
        budRep.budgetOctMoney = params[str+"Month10"]
        budRep.budgetNovMoney = params[str+"Month11"]
        budRep.budgetDecMoney = params[str+"Month12"]

//        budRep.budgetJanMoney = Double.parseDouble(params[str+"Month1"])
//        budRep.budgetFebMoney = Double.parseDouble(params[str+"Month2"])
//        budRep.budgetMarMoney = Double.parseDouble(params[str+"Month3"])
//        budRep.budgetAprilMoney = Double.parseDouble(params[str+"Month4"])
//        budRep.budgetMayMoney = Double.parseDouble(params[str+"Month5"])
//        budRep.budgetJuneMoney = Double.parseDouble(params[str+"Month6"])
//        budRep.budgetJulyMoney = Double.parseDouble(params[str+"Month7"])
//        budRep.budgetAugustMoney = Double.parseDouble(params[str+"Month8"])
//        budRep.budgetSepMoney = Double.parseDouble(params[str+"Month9"])
//        budRep.budgetOctMoney = Double.parseDouble(params[str+"Month10"])
//        budRep.budgetNovMoney = Double.parseDouble(params[str+"Month11"])
//        budRep.budgetDecMoney = Double.parseDouble(params[str+"Month12"])

//        DecimalFormat df = new DecimalFormat("#.00");
//        budRep.budgetJanMoney = Double.parseDouble(df.format(new BigDecimal(params[str+"Month1"])))
//        budRep.budgetFebMoney = df.format(Double.parseDouble(params[str+"Month2"]))
//        budRep.budgetMarMoney = df.format(Double.parseDouble(params[str+"Month3"]))
//        budRep.budgetAprilMoney = df.format(Double.parseDouble(params[str+"Month4"]))
//        budRep.budgetMayMoney = df.format(Double.parseDouble(params[str+"Month5"]))
//        budRep.budgetJuneMoney = df.format(Double.parseDouble(params[str+"Month6"]))
//        budRep.budgetJulyMoney = df.format(Double.parseDouble(params[str+"Month7"]))
//        budRep.budgetAugustMoney = df.format(Double.parseDouble(params[str+"Month8"]))
//        budRep.budgetSepMoney = df.format(Double.parseDouble(params[str+"Month9"]))
//        budRep.budgetOctMoney = df.format(Double.parseDouble(params[str+"Month10"]))
//        budRep.budgetNovMoney = df.format(Double.parseDouble(params[str+"Month11"]))
//        budRep.budgetDecMoney = df.format(Double.parseDouble(params[str+"Month12"]))
        return  budRep
    }
    def budgetReportRecT(BudgetReportReceiptsTemp budRep,params,String str){
        budRep.budgetReportEmpNo = params["budgetReportEmpNo"]
        budRep.budgetReportEmpName = params["budgetReportEmpName"]
        budRep.budgetReportEmpPosition = params["budgetReportEmpPosition"]
        budRep.budgetReportDate = params["budgetReportDate"]
        budRep.budgetCompanyNo = params["budgetCompanyNo"]
        budRep.budgetDepartmentNo = params["budgetDepartmentNo"]
        budRep.budgetCostCenter = params["budgetCostCenter"]
        budRep.budgetCenter = params["budgetCenter"]
        budRep.budgetYear = params["budgetYear"]

        budRep.budgetCostType = params[str+"Expense"]

        if (str.equals("sum")){
            budRep.budgetTypeTotal = params["allSum"]
        }else{
            budRep.budgetTypeTotal = params[str+"Sum"]
        }

        budRep.budgetFirstQuarter = params[str+"Quarter1"]
        budRep.budgetSecondQuarter = params[str+"Quarter2"]
        budRep.budgetThirdQuarter = params[str+"Quarter3"]
        budRep.budgetFourthQuarter = params[str+"Quarter4"]

        budRep.budgetJanMoney = params[str+"Month1"]
        budRep.budgetFebMoney = params[str+"Month2"]
        budRep.budgetMarMoney = params[str+"Month3"]
        budRep.budgetAprilMoney = params[str+"Month4"]
        budRep.budgetMayMoney = params[str+"Month5"]
        budRep.budgetJuneMoney = params[str+"Month6"]
        budRep.budgetJulyMoney = params[str+"Month7"]
        budRep.budgetAugustMoney = params[str+"Month8"]
        budRep.budgetSepMoney = params[str+"Month9"]
        budRep.budgetOctMoney = params[str+"Month10"]
        budRep.budgetNovMoney = params[str+"Month11"]
        budRep.budgetDecMoney = params[str+"Month12"]

//        budRep.budgetJanMoney = Double.parseDouble(params[str+"Month1"])
//        budRep.budgetFebMoney = Double.parseDouble(params[str+"Month2"])
//        budRep.budgetMarMoney = Double.parseDouble(params[str+"Month3"])
//        budRep.budgetAprilMoney = Double.parseDouble(params[str+"Month4"])
//        budRep.budgetMayMoney = Double.parseDouble(params[str+"Month5"])
//        budRep.budgetJuneMoney = Double.parseDouble(params[str+"Month6"])
//        budRep.budgetJulyMoney = Double.parseDouble(params[str+"Month7"])
//        budRep.budgetAugustMoney = Double.parseDouble(params[str+"Month8"])
//        budRep.budgetSepMoney = Double.parseDouble(params[str+"Month9"])
//        budRep.budgetOctMoney = Double.parseDouble(params[str+"Month10"])
//        budRep.budgetNovMoney = Double.parseDouble(params[str+"Month11"])
//        budRep.budgetDecMoney = Double.parseDouble(params[str+"Month12"])

//        DecimalFormat df = new DecimalFormat("#.00");
//        budRep.budgetJanMoney = Double.parseDouble(df.format(new BigDecimal(params[str+"Month1"])))
//        budRep.budgetFebMoney = df.format(Double.parseDouble(params[str+"Month2"]))
//        budRep.budgetMarMoney = df.format(Double.parseDouble(params[str+"Month3"]))
//        budRep.budgetAprilMoney = df.format(Double.parseDouble(params[str+"Month4"]))
//        budRep.budgetMayMoney = df.format(Double.parseDouble(params[str+"Month5"]))
//        budRep.budgetJuneMoney = df.format(Double.parseDouble(params[str+"Month6"]))
//        budRep.budgetJulyMoney = df.format(Double.parseDouble(params[str+"Month7"]))
//        budRep.budgetAugustMoney = df.format(Double.parseDouble(params[str+"Month8"]))
//        budRep.budgetSepMoney = df.format(Double.parseDouble(params[str+"Month9"]))
//        budRep.budgetOctMoney = df.format(Double.parseDouble(params[str+"Month10"]))
//        budRep.budgetNovMoney = df.format(Double.parseDouble(params[str+"Month11"]))
//        budRep.budgetDecMoney = df.format(Double.parseDouble(params[str+"Month12"]))
        return  budRep
    }
}
