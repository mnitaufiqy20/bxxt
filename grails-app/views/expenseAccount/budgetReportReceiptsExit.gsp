<%--
  Created by IntelliJ IDEA.
  User: Lau
  Date: 12-12-26
  Time: 下午7:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
%{--<%@ include file="/js/calendar/DateHTML.htm"%>--}%
<html>
<head>
    %{--<meta name="layout" content="main"/>--}%
    <title>华电集团报销系统</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mark.css')}" type="text/css">
    %{--<script language="javascript" src="/js/calendar/calendar.js"></script>--}%
    %{--<script language="javascript" src="/js/calendar/DateHTML.htm"></script>--}%
    <script src="${resource(dir: 'js/calendar', file: 'jsdate.js')}"></script>
    <script src="${resource(dir: 'js/calendar', file: 'WdatePicker.js')}"></script>
    <script src="${resource(dir: 'js/calendar', file: 'main.js')}"></script>
    <script type="text/javascript">
        function statMoney(id){
            var sNum = document.getElementById(id).value;
            if(sNum==""){
//                alert("数据不能为空，请重新输入！");
//                return;
                sNum = 0;
            }
            var regular = /^(([1-9]\d{0,9})|0)(\.\d{0,2})?$/;
            if(regular.test(sNum)==false){
                alert("输入金额格式错误，请重新输入！");
                var e = 0;
                document.getElementById(id).value = e.toFixed(2);
                return;
            }
            var suNum = sNum*1;
            document.getElementById(id).value = suNum.toFixed(2);
            var type = id.substring(0,3);
            var num = "";
            if(id.length==9){
                num = id.substring(id.length-1,id.length);
            }else{
                num = id.substring(id.length-2,id.length);
            }
            var sumMonth = 0;
            var tra = document.getElementById("traMonth"+num).value;
            var rec = document.getElementById("recMonth"+num).value;
            var off = document.getElementById("offMonth"+num).value;
            sumMonth = tra*1.00 + rec*1.00 + off*1.00;
            document.getElementById("sumMonth"+num).value = sumMonth.toFixed(2);
            var a = 0.00;
            var b = 0.00;
            var c = 0.00;
            var traQ = 0.00;
            var recQ = 0.00;
            var offQ = 0.00;
            if(num<=3){
                a = document.getElementById(type+"Month"+1).value;
                b = document.getElementById(type+"Month"+2).value;
                c = document.getElementById(type+"Month"+3).value
                var jd1 = a*1.00 + b*1.00 + c*1.00;
                document.getElementById(type+"Quarter"+1).value = jd1.toFixed(2);
                traQ = document.getElementById("traQuarter1").value;
                recQ = document.getElementById("recQuarter1").value;
                offQ = document.getElementById("offQuarter1").value;
                var qSum1 = traQ*1.00 + recQ*1.00 + offQ*1.00;
                document.getElementById("sumQuarter1").value = qSum1.toFixed(2);
            }else if(3<num && num<=6){
                a = document.getElementById(type+"Month"+4).value;
                b = document.getElementById(type+"Month"+5).value;
                c = document.getElementById(type+"Month"+6).value;
                var jd2  = a*1.00 + b*1.00 + c*1.00;
                document.getElementById(type+"Quarter"+2).value = jd2.toFixed(2);
//                var jd = (document.getElementById(type+"Month"+4).value+document.getElementById(type+"Month"+5).value+document.getElementById(type+"Month"+6).value);
                traQ = document.getElementById("traQuarter2").value;
                recQ = document.getElementById("recQuarter2").value;
                offQ = document.getElementById("offQuarter2").value;
                var qSum2 = traQ*1.00 + recQ*1.00 + offQ*1.00;
                document.getElementById("sumQuarter2").value = qSum2.toFixed(2);
            }else if(6<num && num<=9){
                a = document.getElementById(type+"Month"+7).value;
                b = document.getElementById(type+"Month"+8).value;
                c = document.getElementById(type+"Month"+9).value;
                var jd3  = a*1.00 + b*1.00 + c*1.00;
                document.getElementById(type+"Quarter"+3).value = jd3.toFixed(2);
//                var jd = (document.getElementById(type+"Month"+7).value+document.getElementById(type+"Month"+8).value+document.getElementById(type+"Month"+9).value);
                traQ = document.getElementById("traQuarter3").value;
                recQ = document.getElementById("recQuarter3").value;
                offQ = document.getElementById("offQuarter3").value;
                var qSum3 = traQ*1.00 + recQ*1.00 + offQ*1.00;
                document.getElementById("sumQuarter3").value = qSum3.toFixed(2);
            }else if(9<num && num<=12){
                a = document.getElementById(type+"Month"+10).value;
                b = document.getElementById(type+"Month"+11).value;
                c = document.getElementById(type+"Month"+12).value;
                var jd4  = a*1.00 + b*1.00 + c*1.00;
                document.getElementById(type+"Quarter"+4).value = jd4.toFixed(2);
//                var jd = (document.getElementById(type+"Month"+10).value+document.getElementById(type+"Month"+11).value+document.getElementById(type+"Month"+12).value);
                traQ = document.getElementById("traQuarter4").value;
                recQ = document.getElementById("recQuarter4").value;
                offQ = document.getElementById("offQuarter4").value;
                var qSum4 = traQ*1.00 + recQ*1.00 + offQ*1.00;
                document.getElementById("sumQuarter4").value = qSum4.toFixed(2);
            }
            var sum1 = document.getElementById(type+"Quarter"+1).value;
            var sum2 = document.getElementById(type+"Quarter"+2).value;
            var sum3 = document.getElementById(type+"Quarter"+3).value;
            var sum4 = document.getElementById(type+"Quarter"+4).value;
            var sum = sum1*1.00 + sum2*1.00 + sum3*1.00 + sum4*1.00;
            document.getElementById(type+"Sum").value = sum.toFixed(2);
            var traS = document.getElementById("traSum").value;
            var recS = document.getElementById("recSum").value;
            var offS = document.getElementById("offSum").value;
            var allSum = traS*1.00 + recS*1.00 + offS*1.00;
            document.getElementById("allSum").value = allSum.toFixed(2);
        }
    </script>
</head>

<body id="">
<g:form id="gForm" name="gForm" action="budgetReportReceiptsSave" controller="budgetReportReceipts" method="post">
<table width="100%"  height="200" border="0" cellpadding="0" cellspacing="0">
    <tr height="30">
        <td colspan="18" align="center" ><h2>预算申报单</h2></td>
    </tr>
    <tr width="100%">
        <td colspan="18">
            <table  width="100%">
                <tr>
                    <td colspan="18">
                        <table style="height:120px"  width="100%">
                            <tr>
                                <td height="30"><div style="width: 150px;background: #ADCDF4;">申请人填写：</div></td>

                                <g:if test="${budgetReportReceipts==null}">
                                    <td colspan="17" height="30">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                </g:if>
                                <g:else>
                                    <td colspan="17" height="30">单号：${budgetReportReceipts.budgetReportReceiptsId}
                                        <input type="hidden" value="${budgetReportReceipts.budgetReportReceiptsId}" name="budgetReportReceiptsId">
                                    </td>
                                </g:else>
                            </tr>
                            <tr>
                                <td colspan="18">
                                    <table width="100%">
                                        <tr  width="100%">
                                            <td height="30" width="8%">公司名称：</td>
                                            <g:if test="${budgetReportReceipts==null}">
                                                <td height="30" width="16%">
                                                    %{--系统自动带入--}%
                                                    <input type="text" id="budgetCompanyNo" name="budgetCompanyNo" value="大龙发电公司" onblur="emptyVerify('budgetCompanyNo');" readonly>
                                                </td>
                                            </g:if>
                                            <g:else>
                                                <td height="30" width="16%">
                                                    %{--系统自动带入--}%
                                                    <input type="text" id="budgetCompanyNo" name="budgetCompanyNo" value="${budgetReportReceipts.budgetCompanyNo}" onblur="emptyVerify('budgetCompanyNo');" readonly>
                                                </td>
                                            </g:else>
                                            <td height="30" width="12%">申报员工编号：</td>
                                            <g:if test="${budgetReportReceipts==null}">
                                                <td height="30" width="16%">
                                                    %{--系统自动带入当前登录人信息--}%
                                                    <input id="budgetReportEmpNo" name="budgetReportEmpNo" type="text" value="4310343" onblur="emptyVerify('budgetReportEmpNo');" readonly>
                                                </td>
                                            </g:if>
                                            <g:else>
                                                <td height="30" width="16%">
                                                    %{--系统自动带入当前登录人信息--}%
                                                    <input id="budgetReportEmpNo" name="budgetReportEmpNo" type="text" value="${budgetReportReceipts.budgetReportEmpNo}" onblur="emptyVerify('budgetReportEmpNo');" readonly>
                                                </td>
                                            </g:else>

                                            <td height="30" width="9%">成本中心：</td>
                                            <g:if test="${budgetReportReceipts==null}">

                                            </g:if>
                                            <g:else>

                                            </g:else>
                                            <td height="30" width="15%">
                                                <select id="budgetCostCenter" name="budgetCostCenter" onblur="emptyVerify('budgetCostCenter');">
                                                    %{--<option value="-1">----请选择----</option>--}%
                                                    <option value="1">2012-生安部-XXX</option>
                                                    <option value="2">2012-技术部-XXX</option>
                                                    <option value="3">2012-人事部-XXX</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="30">部门名称：</td>
                                            <g:if test="${budgetReportReceipts==null}">
                                                <td height="30">
                                                    %{--系统自动带入--}%
                                                    <input id="budgetDepartmentNo" name="budgetDepartmentNo" type="text" value="生安部" readonly>
                                                </td>
                                            </g:if>
                                            <g:else>
                                                <td height="30">
                                                    %{--系统自动带入--}%
                                                    <input id="budgetDepartmentNo" name="budgetDepartmentNo" type="text" value="${budgetReportReceipts.budgetDepartmentNo}" readonly>
                                                </td>
                                            </g:else>

                                            <td height="30">申报员工职位：</td>

                                            %{--系统自动带入--}%
                                            <td><input id="budgetReportEmpPosition" name="budgetReportEmpPosition" type="text" value="财务专责" readonly></td>
                                            <td height="30">预算中心：</td>
                                            <td height="30">
                                                <select id="budgetCenter" name="budgetCenter" onblur="emptyVerify('budgetCenter');">
                                                    <option value="1">2012-生安部-XXX</option>
                                                    <option value="2">2012-技术部-XXX</option>
                                                    <option value="3">2012-人事部-XXX</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="30">预算年份：</td>
                                            <g:if test="${budgetReportReceipts==null}">

                                            </g:if>
                                            <g:else>

                                            </g:else>
                                            %{--默认为当前年份--}%
                                            <td height="30"><input id="budgetYear" name="budgetYear" type="text" value="${year}" class="Wdate" onclick="SelectDate(this,'yyyy',null,null);" onblur="emptyVerify('budgetYear');"></td>
                                            <td height="30">申报员工姓名：</td>
                                            <g:if test="${budgetReportReceipts==null}">

                                            </g:if>
                                            <g:else>

                                            </g:else>
                                            %{--系统自动带入--}%
                                            <td><input id="budgetReportEmpName" name="budgetReportEmpName" type="text" value="Ann" onblur="emptyVerify('budgetReportEmpName');"></td>
                                            <td height="30">申请日期：</td>

                                            %{--系统带入当前日期，日期格式YYYYMMDD--}%
                                            <td height="30"><input id="budgetReportDate" name="budgetReportDate" type="text" value="${nowDate}" readonly></td>
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
    <tr height="90">
        <td colspan="18" width="100%">
            <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                <tr height="90">
                    <td colspan="18" width="100%">
                        <table width="100%" height="90">
                            <tr  height="30">
                                <td colspan="18"><div style="width: 150px;background: #ADCDF4;">预算明细</div></td>
                            </tr>
                            <tr>
                                <td>
                                    <table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td colspan="18">
                                                <table width="100%"  border="1" cellpadding="0" cellspacing="0">
                                                    <tr align="center" height="30" bgcolor="#6fadf6">
                                                        <td height="30" width="6%">费用种类</td>
                                                        <td width="7%">金额合计</td>
                                                        <td width="7%">一季度</td>
                                                        <td width="7%">二季度</td>
                                                        <td width="7%">三季度</td>
                                                        <td width="6%">四季度</td>
                                                        <td width="5%">一月</td>
                                                        <td width="5%">二月</td>
                                                        <td width="5%">三月</td>
                                                        <td width="5%">四月</td>
                                                        <td width="5%">五月</td>
                                                        <td width="5%">六月</td>
                                                        <td width="5%">七月</td>
                                                        <td width="5%">八月</td>
                                                        <td width="5%">九月</td>
                                                        <td width="5%">十月</td>
                                                        <td width="5%">十一月</td>
                                                        <td width="5%">十二月</td>
                                                    </tr>
                                                    <tr align="left" height="30">
                                                        <td bgcolor="#6fadf6"><span id="travelExpense">差旅费</span><input type="hidden" name="traExpense" value="差旅费"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traSum" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traQuarter1" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traQuarter2" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traQuarter3" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traQuarter4" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traMonth1" name="traMonth1" value="0.00" onblur="statMoney('traMonth1');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traMonth2" name="traMonth2" value="0.00" onblur="statMoney('traMonth2');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traMonth3" name="traMonth3" value="0.00" onblur="statMoney('traMonth3');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traMonth4" name="traMonth4" value="0.00" onblur="statMoney('traMonth4');">></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traMonth5" name="traMonth5" value="0.00" onblur="statMoney('traMonth5');">></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traMonth6" name="traMonth6" value="0.00" onblur="statMoney('traMonth6');">></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traMonth7" name="traMonth7" value="0.00" onblur="statMoney('traMonth7');">></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traMonth8" name="traMonth8" value="0.00" onblur="statMoney('traMonth8');">></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traMonth9" name="traMonth9" value="0.00" onblur="statMoney('traMonth9');">></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traMonth10" name="traMonth10" value="0.00" onblur="statMoney('traMonth10');">></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traMonth11" name="traMonth11" value="0.00" onblur="statMoney('traMonth11');">></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="traMonth12" name="traMonth12" value="0.00" onblur="statMoney('traMonth12');">></td>
                                                    </tr>
                                                    <tr align="left" height="30">
                                                        <td bgcolor="#6fadf6"><span id="receiveExpense">招待费</span><input type="hidden" name="recExpense" value="招待费"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recSum" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recQuarter1" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recQuarter2" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recQuarter3" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recQuarter4" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recMonth1" name="recMonth1" value="0.00" onblur="statMoney('recMonth1');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recMonth2" name="recMonth2" value="0.00" onblur="statMoney('recMonth2');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recMonth3" name="recMonth3" value="0.00" onblur="statMoney('recMonth3');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recMonth4" name="recMonth4" value="0.00" onblur="statMoney('recMonth4');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recMonth5" name="recMonth5" value="0.00" onblur="statMoney('recMonth5');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recMonth6" name="recMonth6" value="0.00" onblur="statMoney('recMonth6');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recMonth7" name="recMonth7" value="0.00" onblur="statMoney('recMonth7');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recMonth8" name="recMonth8" value="0.00" onblur="statMoney('recMonth8');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recMonth9" name="recMonth9" value="0.00" onblur="statMoney('recMonth9');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recMonth10" name="recMonth10" value="0.00" onblur="statMoney('recMonth10');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recMonth11" name="recMonth11" value="0.00" onblur="statMoney('recMonth11');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="recMonth12" name="recMonth12" value="0.00" onblur="statMoney('recMonth12');"></td>
                                                    </tr>
                                                    <tr align="left" height="30">
                                                        <td bgcolor="#6fadf6"><span id="officeExpense">办公费</span><input type="hidden" name="offExpense" value="办公费"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offSum" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offQuarter1" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offQuarter2" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offQuarter3" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offQuarter4" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offMonth1" name="offMonth1" value="0.00" onblur="statMoney('offMonth1');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offMonth2" name="offMonth2" value="0.00" onblur="statMoney('offMonth2');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offMonth3" name="offMonth3" value="0.00" onblur="statMoney('offMonth3');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offMonth4" name="offMonth4" value="0.00" onblur="statMoney('offMonth4');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offMonth5" name="offMonth5" value="0.00" onblur="statMoney('offMonth5');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offMonth6" name="offMonth6" value="0.00" onblur="statMoney('offMonth6');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offMonth7" name="offMonth7" value="0.00" onblur="statMoney('offMonth7');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offMonth8" name="offMonth8" value="0.00" onblur="statMoney('offMonth8');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offMonth9" name="offMonth9" value="0.00" onblur="statMoney('offMonth9');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offMonth10" name="offMonth10" value="0.00" onblur="statMoney('offMonth10');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offMonth11" name="offMonth11" value="0.00" onblur="statMoney('offMonth11');"></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="offMonth12" name="offMonth12" value="0.00" onblur="statMoney('offMonth12');"></td>
                                                    </tr>
                                                    <tr align="left" height="30">
                                                        <td bgcolor="#6fadf6">合计</td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="allSum" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumQuarter1" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumQuarter2" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumQuarter3" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumQuarter4" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumMonth1" name="sumMonth1" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumMonth2" name="sumMonth2" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumMonth3" name="sumMonth3" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumMonth4" name="sumMonth4" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumMonth5" name="sumMonth5" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumMonth6" name="sumMonth6" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumMonth7" name="sumMonth7" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumMonth8" name="sumMonth8" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumMonth9" name="sumMonth9" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumMonth10" name="sumMonth10" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumMonth11" name="sumMonth11" value="0.00" readonly></td>
                                                        <td><input style="width: 100%;height: 100%" type="text" id="sumMonth12" name="sumMonth12" value="0.00" readonly></td>
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
            </table>
        </td>
    </tr>
    <tr>
        <td height="30">&nbsp;&nbsp;&nbsp;</td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;</td>
        <td>&nbsp;&nbsp;</td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td height="30"><input type="submit" value="保存"></td>
        <td>&nbsp;&nbsp;</td>
        <td><input type="button" value="退回" onclick="location='../processes/initProcess'"></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>&nbsp;&nbsp;</td>
        <td>&nbsp;&nbsp;</td>
    </tr>
</table>
</g:form>
</body>
</html>