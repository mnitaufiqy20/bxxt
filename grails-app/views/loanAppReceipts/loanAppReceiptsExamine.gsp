<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-3-6
  Time: 下午3:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    %{--<meta name="layout" content="main"/>--}%
    <title>华电集团报销系统</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mark.css')}" type="text/css">
    <style type="text/css" mce_bogus="1">
        /*.table {*/
            /*background: blue;*/
        /*}*/
        /*.table tr,td {*/
            /*background: white;*/
        /*}*/
        #table1{
            border-top:1px solid black;
            border-left:1px solid black;
            cursor:default;
        }
        #table1 td{
            border-bottom:1px solid black;
            border-right:1px solid black;
            height:23px;
        }
    </style>
    <script src="${resource(dir: 'js/calendar', file: 'jsdate.js')}"></script>
    <script src="${resource(dir: 'js/calendar', file: 'WdatePicker.js')}"></script>
    <script src="${resource(dir: 'js/calendar', file: 'main.js')}"></script>
    <script type="text/javascript">
        function markColor(){
            if('${loanAppReceipts.loanStatus}'=='已保存'){
                document.getElementById("save").style.backgroundColor = "green";
            }else if('${loanAppReceipts.loanStatus}'=='已提交'){
                document.getElementById("save").style.backgroundColor = "green";
                document.getElementById("commit").style.backgroundColor = "green";
            }else if('${loanAppReceipts.loanStatus}'=='已审核'){
                document.getElementById("save").style.backgroundColor = "green";
                document.getElementById("commit").style.backgroundColor = "green";
                document.getElementById("deputy").style.backgroundColor = "green";
            }else if('${loanAppReceipts.loanStatus}'=='已过账'){
                document.getElementById("save").style.backgroundColor = "green";
                document.getElementById("commit").style.backgroundColor = "green";
                document.getElementById("deputy").style.backgroundColor = "green";
                document.getElementById("post").style.backgroundColor = "green";
            }else if('${loanAppReceipts.loanStatus}'=='已付款'){
                document.getElementById("save").style.backgroundColor = "green";
                document.getElementById("commit").style.backgroundColor = "green";
                document.getElementById("deputy").style.backgroundColor = "green";
                document.getElementById("post").style.backgroundColor = "green";
                document.getElementById("back").style.backgroundColor = "green";
            }
            document.getElementById("goBack").disabled = true;
            document.getElementById("ideaRemark").display = "none";
        }
    </script>
    <script type="text/javascript">
        function changeIdea(){
            var idea = document.getElementById("examAppIdea").value;
            if(idea=="approve") {
                document.getElementById("goBack").disabled = true;
                document.getElementById("comm").disabled = false;
                document.getElementById("ideaRemark").style.display="none";
            }else{
                document.getElementById("comm").disabled = true;
                document.getElementById("goBack").disabled = false;
                document.getElementById("ideaRemark").style.display="block";
            }
        }
    </script>
    <script type="text/javascript">
        function commForm(id){
            if(id==1){
                var approveRemark = document.getElementById("approveRemark").value;
                if(approveRemark==null || approveRemark==""){
                    alert("请输入退回原因！");
                    return;
                }else{
                    var gForm = document.getElementById("gForm");
                    gForm.action = "examineSave?type=1";
                    gForm.controller = "loanAppReceipts";
                    gForm.submit();
                }
            }else{
                var gForm = document.getElementById("gForm");
                gForm.action = "examineSave?type=0";
                gForm.controller = "loanAppReceipts";
                gForm.submit();
            }
        }
    </script>
</head>

<body id="" onload="markColor();">
<g:form id="gForm" name="gForm" action="commitLoanAppReceipts" controller="loanAppReceipts" method="post">
<input type="hidden" name="act" value="update">
<table width="100%"  height="500" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td colspan="9" align="center" height="40"><h2>借款单</h2></td>
    </tr>
    <tr>
        <td colspan="4">
            <table>
                <tr>
                    <td colspan="4">
                        <table height="240">
                            <tr>
                                <td height="30"><div style="width: 150px;background: #ADCDF4;">申请人填写：</div></td>
                                <td colspan="3" height="30">单号：${loanAppReceipts.loanAppReceiptsId}
                                    <input type="hidden" name="loanAppReceiptsId" id="loanAppReceiptsId" value="${loanAppReceipts.loanAppReceiptsId}">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">
                                    <table>
                                        <tr>
                                            <td height="30">借款员工身份证号：</td>
                                            %{--<td height="30">借款员工编号：</td>--}%
                                            <td height="30">
                                                <input type="text" id="loanEmpIdNumber" name="loanEmpIdNumber" value="${loanAppReceipts.loanEmpIdNumber}" readonly>
                                                <input type="hidden" id="loanEmpNo" name="loanEmpNo" value="${loanAppReceipts.loanEmpNo}">
                                                %{--选项只能为本公司员工--}%
                                                %{--<select id="loanEmpNo" name="loanEmpNo">--}%
                                                %{--<option value="43910343">43910343</option>--}%
                                                %{--<option value="43910342">43910342</option>--}%
                                                %{--<option value="43910345">43910345</option>--}%
                                                %{--<option value="43910348">43910348</option>--}%
                                                %{--</select>--}%
                                            </td>
                                            <td height="30">借款员工姓名：</td>
                                            <td height="30"><input type="text" id="loanEmpName" name="loanEmpName" value="${loanAppReceipts.loanEmpName}" readonly></td>
                                        </tr>
                                        <tr>
                                            <td height="30">成本中心：</td>
                                            <td height="30">
                                                <select id="loanCostCenter" name="loanCostCenter" readonly>
                                                    <option value="1">2012-生安部-XXX</option>
                                                    <option value="2">2012-技术部-XXX</option>
                                                    <option value="3">2012-人事部-XXX</option>
                                                </select>
                                            </td>
                                            <td height="30">借款员工电话：</td>
                                            <td height="30"><input type="text" id="loanEmpPhone" name="loanEmpPhone" value="${loanAppReceipts.loanEmpPhone}" readonly></td>
                                        </tr>
                                        <tr>
                                            <td height="30">公司名称：</td>
                                            <td height="30"><input type="text" id="loanCompanyNo" name="loanCompanyNo" value="${loanAppReceipts.loanCompanyNo}" readonly></td>
                                            <td height="30">借款员工职位：</td>
                                            <td height="30"><input type="text" id="loanEmpPosition" name="loanEmpPosition" value="${loanAppReceipts.loanEmpPosition}" readonly></td>
                                        </tr>
                                        <tr>
                                            <td height="60">用途：</td>
                                            <td colspan="3" height="60"><textarea id="loanPurpose" name="loanPurpose" rows="3" cols="51" onblur="emptyVerify('loanPurpose');" readonly>${loanAppReceipts.loanPurpose}</textarea></td>
                                        </tr>
                                        <tr>
                                            <td height="60">备注：</td>
                                            <td colspan="3" height="60"><textarea id="loanRemark" name="loanRemark" rows="3" cols="51" readonly>${loanAppReceipts.loanRemark}</textarea></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
        <td colspan="5">
            <table>
                <tr>
                    <td colspan="5">
                        <table>
                            <tr id="allTabs">
                                <td height="30" colspan="5">
                                    <div id="save">已保存</div>
                                    <div id="commit">已提交</div>
                                    <div id="deputy">已审核</div>
                                    <div id="post">已过账</div>
                                    <div id="back">已付款</div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5">
                                    <table>
                                        <tr>
                                            <td height="30">预算中心：</td>
                                            <td height="30">
                                                <select id="loanBudgetCenter" name="loanBudgetCenter" readonly>
                                                    <option value="1">2012-生安部-XXX</option>
                                                    <option value="2">2012-技术部-XXX</option>
                                                    <option value="3">2012-人事部-XXX</option>
                                                </select>
                                            </td>
                                            <td height="30">借款金额：</td>
                                            <td height="30"><input id="loanMoney" name="loanMoney" type="text" value="${loanAppReceipts.loanMoney}" onblur="emptyVerify('loanMoney');" readonly></td>
                                        </tr>
                                        <tr>
                                            <td height="30">借款日期：</td>
                                            <td><input id="loanBegDate" name="loanBegDate" type="text" value="${loanAppReceipts.loanBegDate}" readonly></td>
                                            <td>需款日期：</td>
                                            <td><input id="loanParagraphDate" name="loanParagraphDate" type="text" value="${loanAppReceipts.loanParagraphDate}" class="Wdate" onclick="SelectDate(this,'yyyy-MM-dd',null,null);" onblur="emptyVerify('loanParagraphDate');" readonly></td>
                                        </tr>
                                        <tr>
                                            <td height="30">已还金额：</td>
                                            <td><input id="loanAlreadyRefund" name="loanAlreadyRefund" type="text" value="${loanAppReceipts.loanAlreadyRefund}" onblur="emptyVerify('loanAlreadyRefund');" readonly></td>
                                            <td>经办人姓名：</td>
                                            <td><input id="loanOperatorName" name="loanOperatorName" type="text" value="${loanAppReceipts.loanOperatorName}" onblur="emptyVerify('loanOperatorName');" readonly></td>
                                        </tr>
                                        <tr>
                                            <td height="30">付款方式：</td>
                                            <td>
                                                <select id="loanPaymentType" name="loanPaymentType" readonly>
                                                    <option value="银行转账">银行转账</option>
                                                    <option value="现金">现金</option>
                                                </select>
                                            </td>
                                            <td>借款余额：</td>
                                            <td><input id="loanBalance" name="loanBalance" type="text" value="${loanAppReceipts.loanBalance}" readonly></td>
                                        </tr>

                                        <tr>
                                            <td height="30">&nbsp;&nbsp;&nbsp;</td>
                                            <td>&nbsp;&nbsp;&nbsp;</td>
                                            <td>&nbsp;&nbsp;&nbsp;</td>
                                            <td>&nbsp;&nbsp;&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td height="30">&nbsp;&nbsp;&nbsp;</td>
                                            <td>&nbsp;&nbsp;&nbsp;</td>
                                            <td>&nbsp;&nbsp;&nbsp;</td>
                                            <td>&nbsp;&nbsp;&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td height="30">&nbsp;&nbsp;&nbsp;</td>
                                            <td colspan="3"><input type="button" value="华电集团关于借款的有关规定"></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="9" height="30"><div style="width: 150px;background: #ADCDF4;">审批历史</div></td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;</td>
        <td colspan="7">
            <table width="100%" height="60"  border="1" cellpadding="0" cellspacing="0">
                <tr align="center">
                    <td height="30">序号</td>
                    <td width="15%">审批时间</td>
                    <td width="20%">审批人职位</td>
                    <td width="15%">审批人</td>
                    <td width="20%">审批意见</td>
                    <td width="15%">原因</td>
                </tr>
                <g:each in="${historyLists}" var="item" status="index">
                    <tr align="center">
                        <td height="30">
                            ${index+1}
                        </td>
                        <td>${item.endTime}</td>
                        <td>${item.examAppNamePosition}</td>
                        <td>${item.assignee}</td>
                        <td>${item.result}</td>
                        <td>${item.remark}&nbsp;</td>
                    </tr>
                </g:each>
            </table>
        </td>
        <td>&nbsp;&nbsp;</td>
    </tr>
    <tr>
        <td colspan="9" height="30"><div style="width: 150px;background: #ADCDF4;">审批意见</div></td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;</td>
        <td colspan="7">
            <table width="100%" height="60"  border="1" cellpadding="0" cellspacing="0">
                <tr align="center">
                    <td height="30" width="10%">序号</td>
                    <td width="30%">审批时间</td>
                    <td width="20%">审批人职位</td>
                    <td width="15%">审批人</td>
                    <td width="20%">审批意见</td>
                </tr>
                <tr align="center">
                    <td height="30" width="10%">1<input type="hidden" name="taskId" value="${taskId}"></td>
                    <td width="30%">${nowDate}</td>
                    <td width="20%">${user.empPosition}</td>
                    <td width="15%">${user.userName}</td>
                    <td  width="20%">
                        <select id="examAppIdea" width="20%" name="examAppIdea" onchange="changeIdea();">
                            <option width="20%" value="approve">同意</option>
                            <option width="20%" value="reject">不同意</option>
                        </select>
                    </td>
                </tr>
            </table>
        </td>
        <td>&nbsp;&nbsp;</td>
    </tr>
    <tr>
        <td height="30">&nbsp;&nbsp;&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;</td>
        %{--<td height="30"><input type="button" value="保存"  onclick="commForm(0);"></td>--}%
        <td height="30">&nbsp;&nbsp;&nbsp;</td>
        <td><input type="button" id="comm" value="提交"  onclick="commForm(0);"></td>
        <td><input type="button" id="goBack" value="退回" onclick="commForm(1);"></td>
        %{--<td><input type="button" value="返回" onclick="location='../processes/initProcess'"></td>--}%
        <td><input type="button" value="执行过账" disabled></td>
        <td>
            <span style="color: red">
                <div>此功能在所有审批通过后</div>
                <div>由负责费用报销的</div>
                <div>会计人员执行！</div>
            </span>
        </td>
        <td>&nbsp;&nbsp;</td>
        <td>&nbsp;&nbsp;</td>
        <td>&nbsp;&nbsp;</td>
    </tr>
    <div id="ideaRemark" name="ideaRemark" style="width:100px;height:50px" >
    <tr>
        <td>&nbsp;&nbsp;</td>
        <td colspan="2" height="60" align="right">请填写退回原因：</td>
        <td colspan="5" height="60"><textarea id="approveRemark" name="approveRemark" rows="3" cols="30" onblur="emptyVerify('approveRemark');"></textarea></td>
    </tr>
    </div>
</table>
</g:form>
<script>document.getElementById("loanEmpNo").value = "${loanAppReceipts.loanEmpNo}";</script>
<script>document.getElementById("loanCostCenter").value = "${loanAppReceipts.loanCostCenter}";</script>
<script>document.getElementById("loanBudgetCenter").value = "${loanAppReceipts.loanBudgetCenter}";</script>
<script>document.getElementById("loanPaymentType").value = "${loanAppReceipts.loanPaymentType}";</script>
</body>
</html>