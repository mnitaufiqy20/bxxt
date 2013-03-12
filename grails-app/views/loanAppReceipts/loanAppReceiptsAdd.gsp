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
    %{--<script src="${resource(dir: 'js/calendar', file: 'jsdate.js')}"></script>--}%
    %{--<script src="${resource(dir: 'js/calendar', file: 'WdatePicker.js')}"></script>--}%
    <script src="${resource(dir: 'js/calendar', file: 'main.js')}"></script>
    <link rel="stylesheet" href="${resource(dir: 'css/calendar', file: 'jquery-ui.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: '/resources/demos/', file: 'style.css')}" type="text/css">
    <script src="${resource(dir: 'js/calendar', file: 'jquery-1.8.3.js')}"></script>
    <script src="${resource(dir: 'js/calendar', file: 'jquery-ui.js')}"></script>
    <script type="text/javascript">
        function changeEmpNo(){
            var gForm = document.getElementById("gForm");
            gForm.action = "loanAppReceiptsAdd";
            gForm.controller = "loanAppReceipts"
            gForm.submit();
        }
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
                gForm.action = "loanAppReceiptsSave";
            }else{
                gForm.action = "commitLoanAppReceipts";
            }
            gForm.controller = "loanAppReceipts"
            gForm.submit();
        }
    </script>
    <script type="text/javascript">
        $(function() {
            $( "#loanParagraphDate" ).datepicker({
                minDate: 0, maxDate: "+50Y"
            });
        });
    </script>
</head>

<body id="">
<g:form id="gForm" name="gForm" action="commitLoanAppReceipts" controller="loanAppReceipts" method="post">
    <input type="hidden" name="act" value="add">
    <table width="100%"  style="height:430px"  border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="9" align="center" height="40"><h2>借款单</h2></td>
        </tr>
        <tr>
            <td colspan="4">
                <table>
                    <tr>
                        <td colspan="4">
                            <table style="height:240px">
                                <tr>
                                    <td height="30"><div style="width: 150px;background: #ADCDF4;">申请人填写：</div></td>
                                    <td colspan="3" height="30">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                </tr>
                                <tr>
                                    <td colspan="4">
                                        <table>
                                            <tr>
                                                %{--<td height="30">借款员工编号：</td>--}%
                                                <td height="30">借款员工身份证号：</td>
                                                <td height="30">
                                                    <input type="text" id="loanEmpIdNumber" name="loanEmpIdNumber" value="${user.idNumber}" readonly>
                                                    <input type="hidden" id="loanEmpNo" name="loanEmpNo" value="${user.empNo}">
                                                    %{--选项只能为本公司员工--}%
                                                    %{--<select id="loanEmpNo" name="loanEmpNo" onblur="emptyVerify('loanEmpNo');" onchange="changeEmpNo();">--}%
                                                        %{--<option value="-1">--请选择--</option>--}%
                                                        %{--<option value="43910343">43910343</option>--}%
                                                        %{--<option value="43910342">43910342</option>--}%
                                                        %{--<option value="43910345">43910345</option>--}%
                                                        %{--<option value="43910348">43910348</option>--}%
                                                    %{--</select>--}%
                                                </td>
                                                <td height="30">借款员工姓名：</td>
                                                %{--系统带入--}%
                                                %{--<g:if test="${emp==null}">--}%
                                                    %{--<td height="30"><input type="text" id="loanEmpName" name="loanEmpName" value="" readonly></td>--}%
                                                %{--</g:if>--}%
                                                %{--<g:else>--}%
                                                    %{--<td height="30"><input type="text" id="loanEmpName2" name="loanEmpName" value="${emp.userName}" readonly></td>--}%
                                                %{--</g:else>--}%
                                                <td height="30"><input type="text" id="loanEmpName" name="loanEmpName" value="${user.userName}" readonly></td>
                                            </tr>
                                            <tr>
                                                <td height="30">成本中心：</td>
                                                <td height="30">
                                                    <select id="loanCostCenter" name="loanCostCenter" onblur="emptyVerify('loanCostCenter');">
                                                        <option value="-1">----请选择----</option>
                                                        <option value="1">2012-生安部-XXX</option>
                                                        <option value="2">2012-技术部-XXX</option>
                                                        <option value="3">2012-人事部-XXX</option>
                                                    </select>
                                                </td>
                                                <td height="30">借款员工电话：</td>
                                                %{--系统带入--}%
                                                %{--<g:if test="${emp==null}">--}%
                                                    %{--<td height="30"><input type="text" id="loanEmpPhone" name="loanEmpPhone" value="" readonly></td>--}%
                                                %{--</g:if>--}%
                                                %{--<g:else>--}%
                                                    %{--<td height="30"><input type="text" id="loanEmpPhone2" name="loanEmpPhone" value="${emp.telephone}" readonly></td>--}%
                                                %{--</g:else>--}%
                                                <td height="30"><input type="text" id="loanEmpPhone" name="loanEmpPhone" value="${user.telephone}" readonly></td>
                                            </tr>
                                            <tr>
                                                <td height="30">公司名称：</td>
                                                %{--选择报销员工编号后自动带入--}%
                                                %{--<g:if test="${emp==null}">--}%
                                                    %{--<td height="30"><input type="text" id="loanCompanyNo" name="loanCompanyNo" value="" readonly></td>--}%
                                                %{--</g:if>--}%
                                                %{--<g:else>--}%
                                                    %{--<td height="30"><input type="text" id="loanCompanyNo2" name="loanCompanyNo" value="${emp.companyNo}" readonly></td>--}%
                                                %{--</g:else>--}%
                                                <td height="30"><input type="text" id="loanCompanyNo" name="loanCompanyNo" value="${user.companyNo}" readonly></td>
                                                <td height="30">借款员工职位：</td>
                                                %{--系统带入--}%
                                                %{--<g:if test="${emp==null}">--}%
                                                    %{--<td height="30"><input type="text" id="loanEmpPosition" name="loanEmpPosition" value="" readonly></td>--}%
                                                %{--</g:if>--}%
                                                %{--<g:else>--}%
                                                    %{--<td height="30"><input type="text" id="loanEmpPosition2" name="loanEmpPosition" value="${emp.empPosition}" readonly></td>--}%
                                                %{--</g:else>--}%
                                                <td height="30"><input type="text" id="loanEmpPosition" name="loanEmpPosition" value="${user.empPosition}" readonly></td>
                                            </tr>
                                            <tr>
                                                <td height="60">用途：</td>
                                                <td colspan="3" height="60"><textarea id="loanPurpose" name="loanPurpose" rows="3" cols="51" onblur="emptyVerify('loanPurpose');"></textarea></td>
                                            </tr>
                                            <tr>
                                                <td height="60">备注：</td>
                                                <td colspan="3" height="60"><textarea id="loanRemark" name="loanRemark" rows="3" cols="51">enter text...</textarea></td>
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
                                        <div>已保存</div>
                                        <div>已提交</div>
                                        <div>已审核</div>
                                        <div>已过账</div>
                                        <div>已付款</div>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="5">
                                        <table>
                                            <tr>
                                                <td height="30">预算中心：</td>
                                                <td height="30">
                                                    <select id="loanBudgetCenter" name="loanBudgetCenter" onblur="emptyVerify('loanBudgetCenter');">
                                                        <option value="1">2012-生安部-XXX</option>
                                                        <option value="2">2012-技术部-XXX</option>
                                                        <option value="3">2012-人事部-XXX</option>
                                                    </select>
                                                </td>
                                                <td height="30">借款金额：</td>
                                                <td height="30"><input id="loanMoney" name="loanMoney" type="text" value="" onblur="emptyVerify('loanMoney');"></td>
                                            </tr>
                                            <tr>
                                                <td height="30">借款日期：</td>
                                                %{--系统带入当前日期，日期格式YYYYMMDD--}%
                                                <td><input id="loanBegDate" name="loanBegDate" type="text" value="${nowDate}" readonly></td>
                                                <td>需款日期：</td>
                                                %{--不能小于当前日期--}%
                                                %{--<td><input id="loanParagraphDate" name="loanParagraphDate" type="text" value="" class="Wdate" onclick="SelectDate(this,'yyyy-MM-dd',null,null);" onblur="emptyVerify('loanParagraphDate');"></td>--}%
                                                <td><input id="loanParagraphDate" name="loanParagraphDate" type="text" value="${nowDate}" class="Wdate" onblur="emptyVerify('loanParagraphDate');"></td>
                                            </tr>
                                            <tr>
                                                <td height="30">已还金额：</td>
                                                <td><input id="loanAlreadyRefund" name="loanAlreadyRefund" type="text" value="0" onblur="emptyVerify('loanAlreadyRefund');"></td>
                                                <td>经办人姓名：</td>
                                                %{--系统带入--}%
                                                <td><input id="loanOperatorName" name="loanOperatorName" type="text" value="${user.userName}" onblur="emptyVerify('loanOperatorName');"></td>
                                            </tr>
                                            <tr>
                                                <td height="30">付款方式：</td>
                                                %{--默认值为银行转账--}%
                                                <td>
                                                    <select id="loanPaymentType" name="loanPaymentType">
                                                        <option value="银行转账">银行转账</option>
                                                        <option value="现金">现金</option>
                                                    </select>
                                                </td>
                                                <td>借款余额：</td>
                                                %{--计算公式：借款金额－已还金额--}%
                                                <td><input id="loanBalance" name="loanBalance" type="text" value="0" readonly></td>
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
                        <td height="30" width="10%">序号</td>
                        <td width="30%">审批时间</td>
                        <td width="20%">审批人职位</td>
                        <td width="15%">审批人</td>
                        <td width="20%">审批意见</td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="10%">1</td>
                        <td width="30%">2011-11-6 00:00:00</td>
                        <td width="20%">生安部部长</td>
                        <td width="15%">ELLE</td>
                        <td  width="20%"> 同意
                            %{--<select id="search" width="20%">--}%
                                %{--<option width="20%" value="同意">同意</option>--}%
                                %{--<option width="20%" value="不同意">不同意</option>--}%
                            %{--</select>--}%
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
            <td height="30"><input type="button" value="保存" onclick="commForm(0);"></td>
            <td><input type="button" value="提交" onclick="commForm(1);"></td>
            <td><input type="button" value="返回" onclick="location='../loanAppReceipts/loanAppReceiptsQuery'"></td>
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
    </table>
<g:if test="${emp!=null}">
    <script>document.getElementById("loanEmpNo").value = "${emp.empNo}";</script>
    <script>document.getElementById("loanEmpName").value = "${emp.userName}";</script>
    <script>document.getElementById("loanEmpPhone").value = "${emp.telephone}";</script>
    <script>document.getElementById("loanCompanyNo").value = "${emp.companyNo}";</script>
    <script>document.getElementById("loanEmpPosition").value = "${emp.empPosition}";</script>
</g:if>
</g:form>
</body>
</html>