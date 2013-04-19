<%--
  Created by IntelliJ IDEA.
  User: Lau
  Date: 12-12-26
  Time: 下午7:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    %{--<meta name="layout" content="main"/>--}%
    <title>华电集团报销系统</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mark.css')}" type="text/css">
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
        }
    </script>
    <script type="text/javascript">
        function commForm(id){
            var loanEmpNo = document.getElementById("loanEmpNo").value;
            emptyVerify('loanEmpNo');
            var loanEmpName = document.getElementById("loanEmpName").value;
            emptyVerify('loanEmpName');
            var loanCostCenter = document.getElementById("loanCostCenter").value;
            emptyVerify('loanCostCenter');
            var loanPurpose = document.getElementById("loanPurpose").value;
            emptyVerify('loanPurpose');
            var loanBudgetCenter = document.getElementById("loanBudgetCenter").value;
            emptyVerify('loanBudgetCenter');
            var loanMoney = document.getElementById("loanMoney").value;
            emptyVerify('loanMoney');
            var loanParagraphDate = document.getElementById("loanParagraphDate").value;
            emptyVerify('loanParagraphDate');
            var loanAlreadyRefund = document.getElementById("loanAlreadyRefund").value;
            emptyVerify('loanAlreadyRefund');
            var loanOperatorName = document.getElementById("loanOperatorName").value;
            emptyVerify('loanOperatorName');
            var loanBalance = document.getElementById("loanBalance").value;
            emptyVerify('loanBalance');
            if(loanEmpNo=='-1' || loanEmpName=='' || loanCostCenter=='-1' || loanPurpose=='' || loanBudgetCenter=='-1'
                    || loanMoney=='' || loanParagraphDate=='' || loanAlreadyRefund=='' || loanOperatorName=='' || loanBalance==''){
                alert("数据有误，请检查！");
                return;
            }
//            var loanBegDate = document.getElementById("loanBegDate").value;
//            var loanBalance = document.getElementById("loanBalance").value;
            var gForm = document.getElementById("gForm");
            if(id==0){
                gForm.action = "loanAppReceiptsUpdate";
            }else{
                gForm.action = "commitLoanAppReceipts";
            }
            gForm.controller = "loanAppReceipts";
            gForm.submit();
        }
    </script>
</head>

<body id="" onload="markColor();">
<g:form id="gForm" name="gForm" action="commitLoanAppReceipts" controller="loanAppReceipts" method="post">
    <input type="hidden" name="act" value="update">
<table width="100%"  height="430" border="0" cellpadding="0" cellspacing="0">
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
                                    <input type="hidden" name="funcCode" value="${funcCode}">
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
                                                <select id="loanCostCenter" name="loanCostCenter">
                                                    %{--<g:each in="${costCenterList}" var="item">--}%
                                                        %{--<option value="${item.costCenterNo}">${item.costCenterDes}</option>--}%
                                                    %{--</g:each>--}%
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
                                            <td colspan="3" height="60"><textarea id="loanPurpose" name="loanPurpose" rows="3" cols="51" onblur="emptyVerify('loanPurpose');">${loanAppReceipts.loanPurpose}</textarea></td>
                                        </tr>
                                        <tr>
                                            <td height="60">备注：</td>
                                            <td colspan="3" height="60"><textarea id="loanRemark" name="loanRemark" rows="3" cols="51">${loanAppReceipts.loanRemark}</textarea></td>
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
                                                <select id="loanBudgetCenter" name="loanBudgetCenter">
                                                    %{--<g:each in="${costCenterList}" var="item">--}%
                                                        %{--<option value="${item.costCenterNo}">${item.costCenterDes}</option>--}%
                                                    %{--</g:each>--}%
                                                    <option value="1">2012-生安部-XXX</option>
                                                    <option value="2">2012-技术部-XXX</option>
                                                    <option value="3">2012-人事部-XXX</option>
                                                </select>
                                            </td>
                                            <td height="30">借款金额：</td>
                                            <td height="30"><input id="loanMoney" name="loanMoney" type="text" value="${loanAppReceipts.loanMoney}" onblur="emptyVerify('loanMoney');"></td>
                                        </tr>
                                        <tr>
                                            <td height="30">借款日期：</td>
                                            <td><input id="loanBegDate" name="loanBegDate" type="text" value="${loanAppReceipts.loanBegDate}" readonly></td>
                                            <td>需款日期：</td>
                                            <td><input id="loanParagraphDate" name="loanParagraphDate" type="text" value="${loanAppReceipts.loanParagraphDate}" class="Wdate" onclick="SelectDate(this,'yyyy-MM-dd',null,null);" onblur="emptyVerify('loanParagraphDate');"></td>
                                        </tr>
                                        <tr>
                                            <td height="30">已还金额：</td>
                                            <td><input id="loanAlreadyRefund" name="loanAlreadyRefund" type="text" value="${loanAppReceipts.loanAlreadyRefund}" onblur="emptyVerify('loanAlreadyRefund');"></td>
                                            <td>经办人姓名：</td>
                                            <td><input id="loanOperatorName" name="loanOperatorName" type="text" value="${loanAppReceipts.loanOperatorName}" onblur="emptyVerify('loanOperatorName');"></td>
                                        </tr>
                                        <tr>
                                            <td height="30">付款方式：</td>
                                            <td>
                                                <select id="loanPaymentType" name="loanPaymentType">
                                                    <option value="银行转账">银行转账</option>
                                                    <option value="现金">现金</option>
                                                </select>
                                            </td>
                                            <td>借款余额：</td>
                                            <td><input id="loanBalance" name="loanBalance" type="text" value="${loanAppReceipts.loanBalance}" readonly></td>
                                        </tr>

                                        <tr>
                                            <td height="30">票据类别：</td>
                                            <td>
                                                <select id="billsCurr" name="billsCurr">
                                                    <option value="CNY">CNY</option>
                                                    <option value="USD">USD</option>
                                                </select>
                                            </td>
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
        <td height="30">&nbsp;&nbsp;&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;</td>
        <td height="30"><input type="button" value="保存"  onclick="commForm(0);"></td>
        <td><input type="button" value="提交"  onclick="commForm(1);"></td>
        <td><input type="button" value="返回" onclick="location='../loanAppReceipts/loanAppReceiptsQuery?funcCode=${funcCode}'"></td>
        <td><input type="button" value="执行过账" disabled></td>
        <td>
            <span style="color: red">
                <div>此功能在所有审批通过后</div>
                <div>由负责费用报销的</div>
                <div>会计人员执行！</div>
            </span>
        </td>
        <td><input type="button" value="执行付款" disabled></td>
        <td>&nbsp;&nbsp;</td>
        <td>&nbsp;&nbsp;</td>
    </tr>
</table>
</g:form>
<script>document.getElementById("loanEmpNo").value = "${loanAppReceipts.loanEmpNo}";</script>
<script>document.getElementById("loanCostCenter").value = "${loanAppReceipts.loanCostCenter}";</script>
<script>document.getElementById("loanBudgetCenter").value = "${loanAppReceipts.loanBudgetCenter}";</script>
<script>document.getElementById("loanPaymentType").value = "${loanAppReceipts.loanPaymentType}";</script>
<script>document.getElementById("billsCurr").value = "${loanAppReceipts.billsCurr}";</script>
</body>
</html>