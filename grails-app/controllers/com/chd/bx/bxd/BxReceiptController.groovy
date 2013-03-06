package com.chd.bx.bxd

import java.text.SimpleDateFormat

/**
 *   用户登录service
 *   @author 孟敏
 *   @time 2013年1月6日
 */
class BxReceiptController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def bxReceipt   = new BxReceipt();
    def bxReceiptService
    def bxOtherService
    def bxLoanService
    def bxTravelService
    def bxWorkService
    def bxZhaoDaiService
    /**
     *  初始 进入查询报销单界面
     * @return
     */
    def index() {
        List<BxReceipt> bxdList = new ArrayList<BxReceipt>()
        bxdList = bxReceiptService.bxdQueryAll()
        render(view: '../bxReceipt/bxReceiptQuery',model: [bxdList:bxdList])
    }
    /**
     *   查询报销单据
     * @param params
     * @return
     */
    def queryReceipt(params){
        List<BxReceipt> bxdList = new ArrayList<BxReceipt>()
        bxdList=bxReceiptService.queryReceipt(params)
        String rNo = params['rNo']
        String startDate = params['startDate']
        String endDate = params['endDate']
        String status = params['status']
        render(view: '../bxReceipt/bxReceiptQuery',model: [bxdList:bxdList,rNo:rNo,startDate:startDate,endDate:endDate,status:status])
    }
    /**
     * 进入新增报销单页面
     * @return
     */
    def bxdDetail(){
        bxReceipt = new BxReceipt();
        bxReceipt.bxNo="保存后自动生成"
        bxReceipt.approvalTime=sysDateFormat()
        List<BxOther> listOther = new ArrayList<BxOther>()
        List<BxLoan>  listLoan = new ArrayList<BxLoan>();
        List<BxTravel> listTravel = new ArrayList<BxTravel>();
        List<BxZhaoDai> listZhaoDai = new ArrayList<BxZhaoDai>();
        List<BxWork> listWork = new ArrayList<BxWork>();
        BxTravel bxTravel = new BxTravel();
        bxTravel.clTravelDaysCount=0
        bxTravel.clTravelDetails = ""
        render(view: '../bxReceipt/bxReceiptDetail',model: [bxReceipt:bxReceipt,bxTravel:bxTravel,listOther:listOther,listLoan:listLoan,listTravel:listTravel,listZhaoDai:listZhaoDai,listWork:listWork])

    }
    /**
     *   进入修改界面
     * @param params
     * @return
     */
    def bxdEdit(params){
        String bxdNo=params['bxNo']
        bxReceipt = getBxdById(params);
        List<BxOther> listOther = new ArrayList<BxOther>()
        listOther=bxOtherService.otherQueryByBxdNo(bxdNo)
        if (listOther==null){
            listOther = new ArrayList<BxOther>()
        }
        List<BxLoan> listLoan = new ArrayList<BxLoan>()
        listLoan = bxLoanService.loanQueryByBxdNo(bxdNo)
        if (listLoan==null){
            listLoan = new ArrayList<BxLoan>()
        }
        //差旅信息
        List<BxTravel> listTravel = new ArrayList<BxTravel>()
        listTravel = bxTravelService.travelQueryByBxdNo(bxdNo)
        if (listTravel==null){
            listTravel = new ArrayList<BxTravel>()
        }
        BxTravel bxTravel = new BxTravel();
        bxTravel=bxTravelService.travelGetOneByBxdNo(bxdNo)
        if (bxTravel==null){
            bxTravel = new BxTravel();
            bxTravel.clTravelDaysCount=0
            bxTravel.clTravelDetails = ""
        }
        //办公信息
        List<BxWork> listWork = new ArrayList<BxWork>()
        listWork = bxWorkService.workQueryByBxdNo(bxdNo)
        if (listWork==null){
            listWork = new ArrayList<BxWork>()
        }
        //招待信息
        List<BxZhaoDai> listZhaoDai = new ArrayList<BxZhaoDai>()
        listZhaoDai = bxZhaoDaiService.zhaoDaiQueryByBxdNo(bxdNo)
        if (listZhaoDai==null){
            listZhaoDai = new ArrayList<BxZhaoDai>()
        }
        render(view: '../bxReceipt/bxReceiptDetail',model: [bxReceipt:bxReceipt,bxTravel:bxTravel,listOther:listOther,listLoan:listLoan,listTravel:listTravel,listZhaoDai:listZhaoDai,listWork:listWork])
    }
    /**
     * 根据报销单号获取报销单信息
     * @param params
     * @return
     */
    def getBxdById(params){
        List<BxReceipt> list = new ArrayList<BxReceipt>();
        list=bxReceiptService.getBxdById(params["bxNo"])
        bxReceipt = new BxReceipt();
        if(list!=null && list.size()>0){
            bxReceipt = list.get(0)
        }
    }
    /**
     * 添加保存
     */
    def bxdSave(params){
        String type="add"
        String  bxdNo="0";
        bxReceipt.bxdStatus="已保存"
        bxReceipt=bxRep(bxReceipt,params)
        bxdNo=getBxdNo()
        bxReceipt.setBxNo(bxdNo)
        //保存报销单信息
        bxReceiptService.bxdSave(bxReceipt)
        //保存其他报销信息
        saveOther(params,bxdNo,type)
        List<BxOther> listOther = new ArrayList<BxOther>()
        listOther=bxOtherService.otherQueryByBxdNo(bxdNo)
        //保存借款信息
        saveLoan(params,bxdNo,type)
        List<BxLoan> listLoan = new ArrayList<BxLoan>()
        listLoan = bxLoanService.loanQueryByBxdNo(bxdNo)
        //保存差旅信息
        saveTravel(params,bxdNo,type)
        List<BxTravel> listTravel = new ArrayList<BxTravel>()
        listTravel = bxTravelService.travelQueryByBxdNo(bxdNo)
        BxTravel bxTravel = new BxTravel();
        bxTravel.clTravelDaysCount=Integer.parseInt( params['clTravelDaysCount'])
        bxTravel.clTravelDetails = params['clTravelDetails']
        //保存办公信息
        saveWork(params,bxdNo,type)
        List<BxWork> listWork = new ArrayList<BxWork>()
        listWork = bxWorkService.workQueryByBxdNo(bxdNo)
        //保存招待信息
        saveZhaoDai(params,bxdNo,type)
        List<BxZhaoDai> listZhaoDai = new ArrayList<BxZhaoDai>()
        listZhaoDai = bxZhaoDaiService.zhaoDaiQueryByBxdNo(bxdNo)
        render(view: '/bxReceipt/bxReceiptDetail',model: [bxReceipt:bxReceipt,listOther:listOther,listLoan:listLoan,listTravel:listTravel,listWork:listWork,listZhaoDai:listZhaoDai,bxTravel:bxTravel])
    }
    /**
     *   修改保存
     * @return
     */
    def bxdUpdate(params){
        String type="update"
        print("Start update ......")
        String  bxdNo="0"
        bxdNo = params['bxNo'];
        bxReceipt=bxReceiptService.getBxdById(bxdNo).get(0)
        bxReceipt=bxRep(bxReceipt,params)
        bxReceipt.setBxNo(bxdNo)

        //保存报销单信息
        bxReceiptService.bxdSave(bxReceipt)
        //保存其他报销信息
        saveOther(params,bxdNo,type)
        List<BxOther> listOther = new ArrayList<BxOther>()
        listOther=bxOtherService.otherQueryByBxdNo(bxdNo)
        //保存借款信息
        saveLoan(params,bxdNo,type)
        List<BxLoan> listLoan = new ArrayList<BxLoan>()
        listLoan = bxLoanService.loanQueryByBxdNo(bxdNo)
        //保存差旅信息
        saveTravel(params,bxdNo,type)
        List<BxTravel> listTravel = new ArrayList<BxTravel>()
        listTravel = bxTravelService.travelQueryByBxdNo(bxdNo)
        BxTravel bxTravel = new BxTravel();
        bxTravel.clTravelDaysCount=Integer.parseInt( params['clTravelDaysCount'])
        bxTravel.clTravelDetails = params['clTravelDetails']
        //保存办公信息
        saveWork(params,bxdNo,type)
        List<BxWork> listWork = new ArrayList<BxWork>()
        listWork = bxWorkService.workQueryByBxdNo(bxdNo)
        //保存招待信息
        saveZhaoDai(params,bxdNo,type)
        List<BxZhaoDai> listZhaoDai = new ArrayList<BxZhaoDai>()
        listZhaoDai = bxZhaoDaiService.zhaoDaiQueryByBxdNo(bxdNo)
        render(view: '/bxReceipt/bxReceiptDetail',model: [bxReceipt:bxReceipt,listOther:listOther,listLoan:listLoan,listTravel:listTravel,listWork:listWork,listZhaoDai:listZhaoDai,bxTravel:bxTravel])
    }
    //在新增时获得单号
    def getBxdNo(){
        String bxdNo = "J";
        def comNo = "0001"  //公司代码（四位）

        //年月 （四位）
        Date date = new Date()
        SimpleDateFormat matter=new SimpleDateFormat("yyyyMM")
        String dates = matter.format(date);
        println("dates:"+dates)
        def comAndDate = comNo + dates.substring(2,6)
        bxdNo += comAndDate

        //流水号（三位）
        def serialNumberList = new ArrayList<String>()
        List<BxReceipt> loan_list = bxReceiptService.bxdQueryAll()
        def n1 = 0
        def n2 = 0
        if(loan_list!=null&&loan_list.size()>0){
            for (int i=0;i<loan_list.size();i++){
                def str = loan_list.get(i).getBxNo()
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
            bxdNo += "001"
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
                bxdNo += serialNumber
            }else{
                bxdNo += t
            }
        }
        return bxdNo
    }
    def deleteOther(String bxdNo){
        List<BxOther> listOther = new ArrayList<BxOther>()
        listOther= bxOtherService.otherQueryByBxdNo(bxdNo)
        if (listOther!=null){
            int listSize=listOther.size();
            if (listSize>0){
                for (int i=0;i<listSize;i++){
                    BxOther bo = new BxOther()
                    bo = listOther.get(i)
                    bo.delete()
                    print("other  delete ......")
                }
            }
        }
    }
    def deleteLoan(String bxdNo){
        List<BxLoan> listLoan = new ArrayList<BxLoan>()
        listLoan= bxLoanService.loanQueryByBxdNo(bxdNo)
        if (listLoan!=null){
            int listSize=listLoan.size();
            if (listSize>0){
                for (int i=0;i<listSize;i++){
                    BxLoan bl = new BxLoan()
                    bl = listLoan.get(i)
                    bl.delete()
                    print("BxLoan  delete ......")
                }
            }
        }
    }
    def deleteTravel(String bxdNo){
        List<BxTravel> listTravel = new ArrayList<BxTravel>()
        listTravel= bxTravelService.travelQueryByBxdNo(bxdNo)
        if (listTravel!=null){
            int listSize=listTravel.size();
            if (listSize>0){
                for (int i=0;i<listSize;i++){
                    BxTravel bt = new BxTravel()
                    bt = listTravel.get(i)
                    bt.delete()
                    print("BxTravel  delete ......")
                }
            }
        }
    }
    def deleteWork(String bxdNo){
        List<BxWork> listWork = new ArrayList<BxWork>()
        listWork= bxWorkService.workQueryByBxdNo(bxdNo)
        if (listWork!=null){
            int listSize=listWork.size();
            if (listSize>0){
                for (int i=0;i<listSize;i++){
                    BxWork bw = new BxWork()
                    bw = listWork.get(i)
                    bw.delete()
                    print("BxWork  delete ......")
                }
            }
        }
    }
    def deleteZhaoDai(String bxdNo){
        List<BxZhaoDai> listZhaoDai = new ArrayList<BxZhaoDai>()
        listZhaoDai= bxZhaoDaiService.zhaoDaiQueryByBxdNo(bxdNo)
        if (listZhaoDai!=null){
            int listSize=listZhaoDai.size();
            if (listSize>0){
                for (int i=0;i<listSize;i++){
                    BxZhaoDai bzd = new BxZhaoDai()
                    bzd = listZhaoDai.get(i)
                    bzd.delete()
                    print("BxZhaoDai  delete ......")
                }
            }
        }
    }
    def saveOther(params,String bxdNo,String type){
        int index= Integer.parseInt(params['otherIndex'])
        if (type.equals("add")){
            if (index>1){
                for(int i=1;i<index;i++){
                    BxOther bxOther = new BxOther();
                    bxOther=bxOtherRep(bxOther,params,i,bxdNo,type)
                    if (bxOther.oNo>0){
                        bxOtherService.otherSave(bxOther)
                    }
                }
            }
        }else if (type.equals("update")){
            deleteOther(bxdNo)
            def oNoList=params['oNoList']
            int listCount=0
            if (oNoList!=null){
                listCount=oNoList.size();
                if (listCount>0){
                    for(int i=0;i<listCount;i++){
                        BxOther bxOther = new BxOther();
                        int a=i
                        if (listCount==1){
                            a = -1;
                        }
                        bxOther=bxOtherRep(bxOther,params,a,bxdNo,type)
                        if (bxOther.oNo>0){
                            bxOtherService.otherSave(bxOther)
                        }
                    }
                }
            }
            if (index>1){
                for(int i=1;i<index;i++){
                    BxOther bxOther = new BxOther();
                    bxOther=bxOtherRep(bxOther,params,i,bxdNo,"add")
                    if (bxOther.oNo>0){
                        bxOtherService.otherSave(bxOther)
                    }

                }
            }

        }


    }
    def saveLoan(params,String bxdNo,String type){
        int index= Integer.parseInt(params['loanIndex'])
        if (type.equals("add")){
            if (index>1){
                for(int i=1;i<index;i++){
                    BxLoan bxLoan = new BxLoan();
                    bxLoan=bxLoanRep(bxLoan,params,i,bxdNo,type)
                    if (bxLoan.lNo>0){
                        bxLoanService.loanSave(bxLoan)
                    }

                }
            }
        }else if (type.equals("update")){
            deleteLoan(bxdNo)
            def lNoList=params['lNoList']
            int listCount=0
            if (lNoList!=null){
                listCount=lNoList.size();
                if (listCount>0){
                    for(int i=0;i<listCount;i++){
                        BxLoan bxLoan = new BxLoan();
                        int a=i
                        if (listCount==1){
                            a = -1;
                        }
                        bxLoan=bxLoanRep(bxLoan,params,a,bxdNo,type)
                        if (bxLoan.lNo>0){
                            bxLoanService.loanSave(bxLoan)
                        }
                    }
                }
            }
            if (index>1){
                for(int i=1;i<index;i++){
                    BxLoan bxLoan = new BxLoan();
                    bxLoan=bxLoanRep(bxLoan,params,i,bxdNo,"add")
                    if (bxLoan.lNo>0){
                        bxLoanService.loanSave(bxLoan)
                    }

                }
            }

        }
    }
    def saveTravel(params,String bxdNo,String type){
        int index= Integer.parseInt(params['clIndex'])
        if (type.equals("add")){
            if (index>1){
                for(int i=1;i<index;i++){
                    BxTravel bxTravel = new BxTravel();
                    bxTravel=bxTravelRep(bxTravel,params,i,bxdNo,type)
                    if (bxTravel.cNo>0){
                        bxTravelService.travelSave(bxTravel)
                    }

                }
            }
        }else if (type.equals("update")){
            deleteTravel(bxdNo)
            def cNoList=params['cNoList']
            int listCount=0
            if (cNoList!=null){
                listCount=cNoList.size();
                if (listCount>0){
                    for(int i=0;i<listCount;i++){
                        BxTravel bxTravel = new BxTravel();
                        int a=i
                        if (listCount==1){
                            a = -1;
                        }
                        bxTravel=bxTravelRep(bxTravel,params,a,bxdNo,type)
                        if (bxTravel.cNo>0){
                            bxTravelService.travelSave(bxTravel)
                        }
                    }
                }
            }
            if (index>1){
                for(int i=1;i<index;i++){
                    BxTravel bxTravel = new BxTravel();
                    bxTravel=bxTravelRep(bxTravel,params,i,bxdNo,"add")
                    if (bxTravel.cNo>0){
                        bxTravelService.travelSave(bxTravel)
                    }

                }
            }
        }
    }
    def saveWork(params,String bxdNo,String type){
        int index= Integer.parseInt(params['bgIndex'])
        if (type.equals("add")){
            if (index>1){
                for(int i=1;i<index;i++){
                    BxWork bxWork = new BxWork();
                    bxWork=bxWorkRep(bxWork,params,i,bxdNo)
                    if (bxWork.bNo>0){
                        bxWorkService.workSave(bxWork)
                    }

                }
            }
        }else if (type.equals("update")){
            deleteWork(bxdNo)
            def bNoList=params['bNoList']
            int listCount=0
            if (bNoList!=null){
                listCount=bNoList.size();
                if (listCount>0){
                    for(int i=0;i<listCount;i++){
                        BxWork bxWork = new BxWork();
                        int a=i
                        if (listCount==1){
                            a = -1;
                        }
                        bxWork=bxWorkRep(bxWork,params,a,bxdNo,type)
                        if (bxWork.bNo>0){
                            bxWorkService.workSave(bxWork)
                        }
                    }
                }
            }
            if (index>1){
                for(int i=1;i<index;i++){
                    BxWork bxWork = new BxWork();
                    bxWork=bxWorkRep(bxWork,params,i,bxdNo,"add")
                    if (bxWork.bNo>0){
                        bxWorkService.workSave(bxWork)
                    }

                }
            }
        }
    }
    def saveZhaoDai(params,String bxdNo,String type){
        int index= Integer.parseInt(params['zdIndex'])
        if (type.equals("add")){
            if (index>1){
                for(int i=1;i<index;i++){
                    BxZhaoDai bxZhaoDai = new BxZhaoDai();
                    bxZhaoDai=bxZhaoDaiRep(bxZhaoDai,params,i,bxdNo)
                    if (bxZhaoDai.zNo>0){
                        bxZhaoDaiService.zhaoDaiSave(bxZhaoDai)
                    }

                }
            }
        }else if (type.equals("update")){
            deleteZhaoDai(bxdNo)
            def zNoList=params['zNoList']
            int listCount=0
            if (zNoList!=null){
                listCount=zNoList.size();
                if (listCount>0){
                    for(int i=0;i<listCount;i++){
                        BxZhaoDai bxZhaoDai = new BxZhaoDai();
                        int a=i
                        if (listCount==1){
                            a = -1;
                        }
                        bxZhaoDai=bxZhaoDaiRep(bxZhaoDai,params,a,bxdNo,type)
                        if (bxZhaoDai.zNo>0){
                            bxZhaoDaiService.zhaoDaiSave(bxZhaoDai)
                        }
                    }
                }
            }
            if (index>1){
                for(int i=1;i<index;i++){
                    BxZhaoDai bxZhaoDai = new BxZhaoDai();
                    bxZhaoDai=bxZhaoDaiRep(bxZhaoDai,params,i,bxdNo,"add")
                    if (bxZhaoDai.zNo>0){
                        bxZhaoDaiService.zhaoDaiSave(bxZhaoDai)
                    }

                }
            }
        }
    }
    /**
     * 赋值给对象
     */
    def bxOtherRep(BxOther bo,params,int i,String bxdNo,String type){
        print("other赋值.........")
        if (type.equals("add")){
            bo.bxdNo = bxdNo
            bo.oNo = Integer.parseInt(params['oNo'+i])
            bo.otherId = UUID.randomUUID().toString();
            bo.otherDate = params['otherDate'+i]
            bo.otherOriginalSum = Double.parseDouble(params['otherOriginalSum'+i])
            bo.otherBxRmbSum = Double.parseDouble(params['otherBxRmbSum'+i])
            bo.otherBillsExplain = params['otherBillsExplain'+i]
            bo.otherRemark = params['otherRemark'+i]
        }else if (type.equals("update")){
            if (i==-1){
                bo.bxdNo = bxdNo
                bo.oNo = Integer.parseInt(params['oNoList'])
                bo.otherId = UUID.randomUUID().toString();
                bo.otherDate = params['otherDateList']
                bo.otherOriginalSum = Double.parseDouble(params['otherOriginalSumList'])
                bo.otherBxRmbSum = Double.parseDouble(params['otherBxRmbSumList'])
                bo.otherBillsExplain = params['otherBillsExplainList']
                bo.otherRemark = params['otherRemarkList']
            }else{
                bo.bxdNo = bxdNo
                bo.oNo = Integer.parseInt(params['oNoList'].getAt(i))
                bo.otherId = UUID.randomUUID().toString();
                bo.otherDate = params['otherDateList'].getAt(i)
                bo.otherOriginalSum = Double.parseDouble(params['otherOriginalSumList'].getAt(i))
                bo.otherBxRmbSum = Double.parseDouble(params['otherBxRmbSumList'].getAt(i))
                bo.otherBillsExplain = params['otherBillsExplainList'].getAt(i)
                bo.otherRemark = params['otherRemarkList'].getAt(i)
            }

        }

        return bo;
    }
    def bxLoanRep(BxLoan bl,params,int i,String bxdNo,String type){
        if (type.equals("add")){
            bl.bxdNo = bxdNo
            bl.lNo = Integer.parseInt(params['lNo'+i])
            bl.loanNo = params['loanNo'+i]
            bl.loanDate = params['loanDate'+i]
            bl.loanBalance = Double.parseDouble(params['loanBalance'+i])
            bl.loanBillsCurr = params['loanBillsCurr'+i]
            bl.loanOriginalSum = Double.parseDouble(params['loanOriginalSum'+i])
            bl.loanTheRepayment = Double.parseDouble(params['loanTheRepayment'+i])
            bl.loanRemark = params['loanRemark'+i]
        }else if (type.equals("update")){
            if (i==-1){
                bl.bxdNo = bxdNo
                bl.lNo = Integer.parseInt(params['lNoList'])
                bl.loanNo = params['loanNoList']
                bl.loanDate = params['loanDateList']
                bl.loanBalance = Double.parseDouble(params['loanBalanceList'])
                bl.loanBillsCurr = params['loanBillsCurrList']
                bl.loanOriginalSum = Double.parseDouble(params['loanOriginalSumList'])
                bl.loanTheRepayment = Double.parseDouble(params['loanTheRepaymentList'])
                bl.loanRemark = params['loanRemarkList']
            }else{
                bl.bxdNo = bxdNo
                bl.lNo = Integer.parseInt(params['lNoList'].getAt(i))
                bl.loanNo = params['loanNoList'].getAt(i)
                bl.loanDate = params['loanDateList'].getAt(i)
                bl.loanBalance = Double.parseDouble(params['loanBalanceList'].getAt(i))
                bl.loanBillsCurr = params['loanBillsCurrList'].getAt(i)
                bl.loanOriginalSum = Double.parseDouble(params['loanOriginalSumList'].getAt(i))
                bl.loanTheRepayment = Double.parseDouble(params['loanTheRepaymentList'].getAt(i))
                bl.loanRemark = params['loanRemarkList'].getAt(i)
            }
        }
        return bl;
    }
    def bxZhaoDaiRep(BxZhaoDai bzd,params,int i,String bxdNo,String type){
        if (type.equals("update")){
            if (i==-1){
                bzd.bxdNo = bxdNo
                bzd.zNo = Integer.parseInt(params['zNoList'])
                bzd.zdNo = UUID.randomUUID().toString();
                bzd.zdDate = params['zdDateList']
                bzd.zdExpenseType = params['zdExpenseTypeList']
                bzd.zdOriginalSum = Double.parseDouble(params['zdOriginalSumList'])
                bzd.zdBxRmbSum =Double.parseDouble( params['zdBxRmbSumList'] )
                bzd.zdBillsExplain = params['zdBillsExplainList']
                bzd.zdRemark = params['zdRemarkList']
            }else{
                bzd.bxdNo = bxdNo
                bzd.zNo = Integer.parseInt(params['zNoList'].getAt(i))
                bzd.zdNo = UUID.randomUUID().toString();
                bzd.zdDate = params['zdDateList'].getAt(i)
                bzd.zdExpenseType = params['zdExpenseTypeList'].getAt(i)
                bzd.zdOriginalSum = Double.parseDouble(params['zdOriginalSumList'].getAt(i))
                bzd.zdBxRmbSum =Double.parseDouble( params['zdBxRmbSumList'].getAt(i) )
                bzd.zdBillsExplain = params['zdBillsExplainList'].getAt(i)
                bzd.zdRemark = params['zdRemarkList'].getAt(i)
            }
        }else if (type.equals("add")){
            bzd.bxdNo = bxdNo
            bzd.zNo = Integer.parseInt(params['zNo'+i])
            bzd.zdNo = UUID.randomUUID().toString();
            bzd.zdDate = params['zdDate'+i]
            bzd.zdExpenseType = params['zdExpenseType'+i]
            bzd.zdOriginalSum = Double.parseDouble(params['zdOriginalSum'+i])
            bzd.zdBxRmbSum =Double.parseDouble( params['zdBxRmbSum'+i]   )
            bzd.zdBillsExplain = params['zdBillsExplain'+i]
            bzd.zdRemark = params['zdRemark'+i]
        }
        return bzd;
    }
    def bxWorkRep(BxWork bw,params,int i,String bxdNo,String type){
        if (type.equals("add")){
            bw.bxdNo = bxdNo
            bw.bgNo =  UUID.randomUUID().toString();
            bw.bNo = Integer.parseInt(params['bNo'+i])
            bw.bgDate = params['bgDate'+i]
            bw.bgOriginalSum = Double.parseDouble(params['bgOriginalSum'+i] )
            bw.bgBxRmbSum = Double.parseDouble(params['bgBxRmbSum'+i] )
            bw.bgBillsExplain = params['bgBillsExplain'+i]
            bw.bgRemark = params['bgRemark'+i]
            bw.bgExpenseType = params['bgExpenseType'+i]
        }else if (type.equals("update")){
            if (i==-1){
                bw.bxdNo = bxdNo
                bw.bgNo =  UUID.randomUUID().toString();
                bw.bNo = Integer.parseInt(params['bNoList'])
                bw.bgDate = params['bgDateList']
                bw.bgOriginalSum = Double.parseDouble(params['bgOriginalSumList'] )
                bw.bgBxRmbSum = Double.parseDouble(params['bgBxRmbSumList'])
                bw.bgBillsExplain = params['bgBillsExplainList']
                bw.bgRemark = params['bgRemarkList']
                bw.bgExpenseType = params['bgExpenseTypeList']
            }else{
                bw.bxdNo = bxdNo
                bw.bgNo =  UUID.randomUUID().toString();
                bw.bNo = Integer.parseInt(params['bNoList'].getAt(i))
                bw.bgDate = params['bgDateList'].getAt(i)
                bw.bgOriginalSum = Double.parseDouble(params['bgOriginalSumList'].getAt(i) )
                bw.bgBxRmbSum = Double.parseDouble(params['bgBxRmbSumList'].getAt(i) )
                bw.bgBillsExplain = params['bgBillsExplainList'].getAt(i)
                bw.bgRemark = params['bgRemarkList'].getAt(i)
                bw.bgExpenseType = params['bgExpenseTypeList'].getAt(i)
            }
        }
        return bw;
    }
    def bxTravelRep(BxTravel bt,params,int i,String bxdNo,String type){
        if (type.equals("add")){
            bt.bxdNo = bxdNo
            bt.clNo =  UUID.randomUUID().toString();
            bt.cNo = Integer.parseInt(params['cNo'+i])
            bt.clStartDate = params['clStartDate'+i]
            bt.clEndDate = params['clEndDate'+i]
            bt.clExpenseType = params['clExpenseType'+i]
            bt.clTransport = params['clTransport'+i]
            bt.clTravelDays = Integer.parseInt(params['clTravelDays'+i])
            bt.clOriginalSum = Double.parseDouble(params['clOriginalSum'+i] )
            bt.clBxRmbSum =Double.parseDouble( params['clBxRmbSum'+i] )
            bt.clSeAddress = params['clSeAddress'+i]
            bt.clTravelDaysCount = Integer.parseInt(params['clTravelDaysCount'])
            bt.clTravelDetails = params['clTravelDetails']
            bt.clRemark = params['clRemark'+i]
        }else if (type.equals("update")){
            if (i==-1){
                bt.bxdNo = bxdNo
                bt.clNo =  UUID.randomUUID().toString();
                bt.cNo = Integer.parseInt(params['cNoList'])
                bt.clStartDate = params['clStartDateList']
                bt.clEndDate = params['clEndDateList']
                bt.clExpenseType = params['clExpenseTypeList']
                bt.clTransport = params['clTransportList']
                bt.clTravelDays = Integer.parseInt(params['clTravelDaysList'])
                bt.clOriginalSum = Double.parseDouble(params['clOriginalSumList'] )
                bt.clBxRmbSum =Double.parseDouble( params['clBxRmbSumList'] )
                bt.clSeAddress = params['clSeAddressList']
                bt.clTravelDaysCount = Integer.parseInt(params['clTravelDaysCount'])
                bt.clTravelDetails = params['clTravelDetails']
                bt.clRemark = params['clRemarkList']
            }else{
                bt.bxdNo = bxdNo
                bt.clNo =  UUID.randomUUID().toString();
                bt.cNo = Integer.parseInt(params['cNoList'].getAt(i))
                bt.clStartDate = params['clStartDateList'].getAt(i)
                bt.clEndDate = params['clEndDateList'].getAt(i)
                bt.clExpenseType = params['clExpenseTypeList'].getAt(i)
                bt.clTransport = params['clTransportList'].getAt(i)
                bt.clTravelDays = Integer.parseInt(params['clTravelDaysList'].getAt(i))
                bt.clOriginalSum = Double.parseDouble(params['clOriginalSumList'].getAt(i) )
                bt.clBxRmbSum =Double.parseDouble( params['clBxRmbSumList'].getAt(i) )
                bt.clSeAddress = params['clSeAddressList'].getAt(i)
                bt.clTravelDaysCount = Integer.parseInt(params['clTravelDaysCount'])
                bt.clTravelDetails = params['clTravelDetails']
                bt.clRemark = params['clRemarkList'].getAt(i)
            }
        }
        return bt;
    }
    /**
     * 赋值给对象
     */
    def bxRep(BxReceipt bxd,params){
        String bxEmpId=params['bxEmpNo']
        bxd.bxEmpNo = Integer.parseInt(bxEmpId)
        bxd.costCenter = params['costCenter']
        bxd.companyName = params['companyName']
        bxd.bxEmpName = params['bxEmpName']
        bxd.bxEmpPhoneNumber = params['bxEmpPhoneNumber']
        bxd.bxEmpPosition = params['bxEmpPosition']
        bxd.budgetCenter = params['budgetCenter']
        bxd.needMoneyDate =params['needMoneyDate']
        bxd.paymentMode = params['paymentMode']
        bxd.applicationDate = params['applicationDate']
        bxd.managerName = params['managerName']
        bxd.billsCurr = params['billsCurr']

//        bxd.clTravelDetails = params['clTravelDetails']
//        bxd.clTravelDays = Integer.parseInt(params['clTravelDays'])
//        bxd.clStartDate = params['clStartDate']
//        bxd.clEndDate = params['clEndDate']
//        bxd.clSeAddress = params['clSeAddress']
//        bxd.clExpenseType = params['clExpenseType']
//        bxd.clTransport = params['clTransport']
//        bxd.clOriginalSum = Double.parseDouble(params['clOriginalSum'])
//        bxd.clBxRmbSum = Double.parseDouble(params['clBxRmbSum'])
//        bxd.clRemark = params['clRemark']

//        bxd.zdDate = params['zdDate']
//        bxd.zdExpenseType = params['zdExpenseType']
//        bxd.zdOriginalSum = Double.parseDouble(params['zdOriginalSum'] )
//        bxd.zdBxRmbSum =Double.parseDouble( params['zdBxRmbSum'])
//        bxd.zdBillsExplain = params['zdBillsExplain']
//        bxd.zdRemark = params['zdRemark']

//        bxd.bgDate = params['bgDate']
//        bxd.bgExpenseType = params['bgExpenseType']
//        bxd.bgOriginalSum = Double.parseDouble(params['bgOriginalSum'] )
//        bxd.bgBxRmbSum = Double.parseDouble(params['bgBxRmbSum']     )
//        bxd.bgBillsExplain = params['bgBillsExplain']
//        bxd.bgRemark = params['bgRemark']

//        bxd.otherDate = params['otherDate']
//        bxd.otherOriginalSum =Double.parseDouble( params['otherOriginalSum']   )
//        bxd.otherBxRmbSum =Double.parseDouble( params['otherBxRmbSum']    )
//        bxd.otherBillsExplain = params['otherBillsExplain']
//        bxd.otherRemark = params['otherRemark']

        bxd.bxCostCounts =Double.parseDouble( params['bxCostCounts']   )

//        bxd.loanDate =params['loanDate']
//        bxd.loanNo = params['loanNo']
//        bxd.loanBillsCurr = params['loanBillsCurr']
//        bxd.loanOriginalSum =Double.parseDouble( params['loanOriginalSum'] )
//        bxd.loanBalance =Double.parseDouble( params['loanBalance']   )
//        bxd.loanTheRepayment =Double.parseDouble( params['loanTheRepayment'] )
//        bxd.loanRemark = params['loanRemark']

        bxd.payCounts = Double.parseDouble(params['payCounts'] )
        bxd.billsCounts = Integer.parseInt(params['billsCounts'] )
        bxd.expenseCategory = params['expenseCategory']
        bxd.budgetYear = Integer.parseInt(params['budgetYear']  )
        bxd.budgetMonth = Integer.parseInt(params['budgetMonth']  )
        bxd.budgetQuarter = Integer.parseInt(params['budgetQuarter'] )
        bxd.balanceBudgetYear =Double.parseDouble( params['balanceBudgetYear']        )
        bxd.balanceBudgetMonth = Double.parseDouble(params['balanceBudgetMonth']     )
        bxd.balanceBudgetQuarter =Double.parseDouble( params['balanceBudgetQuarter'])
        bxd.budgetYearPro = Double.parseDouble(params['budgetYearPro']        )
        bxd.budgetMonthPro =Double.parseDouble( params['budgetMonthPro']     )
        bxd.budgetQuarterPro = Double.parseDouble(params['budgetQuarterPro'])
        bxd.approvalTime = params['approvalTime']
        bxd.approvalOpinions = params['approvalOpinions']
        bxd.approverPosition = params['approverPosition']
        bxd.approver = params['approver']
        bxd.approvalStatus = params['approvalStatus']
        return  bxd;
    }
    def sysDateFormat(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sysDate = sdf.format(date);
        return  sysDate;
    }

}
