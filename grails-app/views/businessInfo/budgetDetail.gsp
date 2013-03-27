<%--
  Created by IntelliJ IDEA.
  User: MengMin
  Date: 13-01-26
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

</head>

<body id="">
<g:form id="gForm" name="gForm" action="budgetDetail" controller="budgetReportReceipts" method="post">
<table width="100%"  height="200" border="0" cellpadding="0" cellspacing="0">
<tr height="30">
    <td colspan="18" align="center" ><h2>预算费用</h2></td>
</tr>
<tr width="100%">
    <td colspan="18">
        <table  width="100%">
            <tr>
                <td colspan="18">
                    <table style="height:120px"  width="100%">
                        <tr>
                            <td height="30"><div style="width: 150px;background: #ADCDF4;">申请人填写：</div></td>
                            <g:if test="${budgetReportReceipts1==null}">
                                <td colspan="17" height="30"><div style="width: 650px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                            </g:if>
                            <g:else>
                                <td colspan="17" height="30" align="left">
                                    <div style="width: 650px;">
                                        单号：${budgetReportReceipts1.budgetReportReceiptsId}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </div>
                                    <input type="hidden" value="${budgetReportReceipts1.budgetReportReceiptsId}" name="budgetReportReceiptsId">
                                </td>
                            </g:else>
                        </tr>
                        <tr>
                            <td colspan="18">
                                <table width="100%">
                                    <tr  width="100%">
                                        <td height="30" width="8%">公司名称：</td>

                                        <g:if test="${budgetReportReceipts1==null}">
                                            <td height="30" width="16%">
                                                %{--系统自动带入--}%
                                                <input type="text" id="budgetCompanyNo" name="budgetCompanyNo" value="大龙发电公司" onblur="emptyVerify('budgetCompanyNo');" readonly>
                                            </td>
                                        </g:if>
                                        <g:else>
                                            <td height="30" width="16%">
                                                %{--系统自动带入--}%
                                                <input type="text" id="budgetCompanyNo2" name="budgetCompanyNo" value="${budgetReportReceipts1.budgetCompanyNo}" onblur="emptyVerify('budgetCompanyNo');" readonly>
                                            </td>
                                        </g:else>
                                        <td height="30" width="12%">申报员工编号：</td>

                                        <g:if test="${budgetReportReceipts1==null}">
                                            <td height="30" width="16%">
                                                %{--系统自动带入当前登录人信息--}%
                                                <input id="budgetReportEmpNo" name="budgetReportEmpNo" type="text" value="4310343" onblur="emptyVerify('budgetReportEmpNo');" readonly>
                                            </td>
                                        </g:if>
                                        <g:else>
                                            <td height="30" width="16%">
                                                %{--系统自动带入当前登录人信息--}%
                                                <input id="budgetReportEmpNo2" name="budgetReportEmpNo" type="text" value="${budgetReportReceipts1.budgetReportEmpNo}" onblur="emptyVerify('budgetReportEmpNo');" readonly>
                                            </td>
                                        </g:else>

                                        <td height="30" width="9%">成本中心：</td>
                                        <g:if test="${budgetReportReceipts1==null}">
                                            <td height="30" width="15%">
                                                <select id="budgetCostCenter" name="budgetCostCenter">
                                                    %{--<option value="-1">----请选择----</option>--}%
                                                    <option value="1">2012-生安部-XXX</option>
                                                    <option value="2">2012-技术部-XXX</option>
                                                    <option value="3">2012-人事部-XXX</option>
                                                </select>
                                            </td>
                                        </g:if>
                                        <g:else>
                                            <td height="30" width="15%">
                                                <select id="budgetCostCenter2" name="budgetCostCenter2" disabled>
                                                    %{--<option value="-1">----请选择----</option>--}%
                                                    <option value="1">2012-生安部-XXX</option>
                                                    <option value="2">2012-技术部-XXX</option>
                                                    <option value="3">2012-人事部-XXX</option>
                                                </select>
                                            </td>
                                        </g:else>
                                    </tr>
                                    <tr>
                                        <td height="30">部门名称：</td>

                                        <g:if test="${budgetReportReceipts1==null}">
                                            <td height="30">
                                                %{--系统自动带入--}%
                                                <input id="budgetDepartmentNo" name="budgetDepartmentNo" type="text" value="生安部" readonly>
                                            </td>
                                        </g:if>
                                        <g:else>
                                            <td height="30">
                                                %{--系统自动带入--}%
                                                <input id="budgetDepartmentNo2" name="budgetDepartmentNo" type="text" value="${budgetReportReceipts1.budgetDepartmentNo}" readonly>
                                            </td>
                                        </g:else>

                                        <td height="30">申报员工职位：</td>
                                    %{--系统自动带入--}%

                                        <g:if test="${budgetReportReceipts1==null}">
                                            <td><input id="budgetReportEmpPosition" name="budgetReportEmpPosition" type="text" value="财务专责" readonly></td>
                                        </g:if>
                                        <g:else>
                                            <td><input id="budgetReportEmpPosition2" name="budgetReportEmpPosition" type="text" value="${budgetReportReceipts1.budgetReportEmpPosition}" readonly></td>
                                        </g:else>


                                        <td height="30">预算中心：</td>
                                        <g:if test="${budgetReportReceipts1==null}">
                                            <td height="30">
                                                <select id="budgetCenter" name="budgetCenter" onblur="emptyVerify('budgetCenter');">
                                                    <option value="1">2012-生安部-XXX</option>
                                                    <option value="2">2012-技术部-XXX</option>
                                                    <option value="3">2012-人事部-XXX</option>
                                                </select>
                                            </td>
                                        </g:if>
                                        <g:else>
                                            <td height="30">
                                                <select id="budgetCenter2" name="budgetCenter" onblur="emptyVerify('budgetCenter');" disabled>
                                                    <option value="1">2012-生安部-XXX</option>
                                                    <option value="2">2012-技术部-XXX</option>
                                                    <option value="3">2012-人事部-XXX</option>
                                                </select>
                                            </td>
                                        </g:else>
                                    </tr>
                                    <tr>
                                        <td height="30">预算年份：</td>
                                    %{--默认为当前年份--}%
                                        %{--<g:if test="${budgetReportReceipts1==null}">--}%
                                            <td height="30">
                                                %{--<input id="budgetYear" name="budgetYear" type="text" value="${budgetReportReceipts1.budgetYear}" class="Wdate" onclick="SelectDate(this,'yyyy',null,null);" onchange="change();" >--}%
                                                <select id="budgetYear" name="budgetYear" onchange="change();" >
                                                <option value="2012">2012</option>
                                                <option value="2013">2013</option>
                                                <option value="2014">2014</option>
                                                </select>
                                            </td>
                                        %{--</g:if>--}%
                                        %{--<g:else>--}%
                                            %{--<td height="30"><input id="budgetYear2" name="budgetYear" type="text" value="${budgetReportReceipts1.budgetYear}" readonly></td>--}%
                                        %{--</g:else>--}%

                                        <td height="30">申报员工姓名：</td>
                                    %{--系统自动带入--}%
                                        <g:if test="${budgetReportReceipts1==null}">
                                            <td><input id="budgetReportEmpName" name="budgetReportEmpName" type="text" value="Ann" onblur="emptyVerify('budgetReportEmpName');" readonly></td>
                                        </g:if>
                                        <g:else>
                                            <td><input id="budgetReportEmpName2" name="budgetReportEmpName" type="text" value="${budgetReportReceipts1.budgetReportEmpName}" readonly></td>
                                        </g:else>

                                        <td height="30">申请日期：</td>
                                    %{--系统带入当前日期，日期格式YYYYMMDD--}%
                                        <g:if test="${budgetReportReceipts1==null}">
                                            <td height="30"><input id="budgetReportDate" name="budgetReportDate" type="text" value="" readonly></td>
                                        </g:if>
                                        <g:else>
                                            <td height="30"><input id="budgetReportDate2" name="budgetReportDate" type="text" value="${budgetReportReceipts1.budgetReportDate}" readonly></td>
                                        </g:else>

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
                            <g:if test="${budgetReportReceipts1==null}">
                                <tr align="left" height="30">
                                    <td bgcolor="#6fadf6"><span id="travelExpense">差旅费</span><input type="hidden" name="traExpense" value="差旅费"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traSum" name="traSum" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traQuarter1" name="traQuarter1" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traQuarter2" name="traQuarter2" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traQuarter3" name="traQuarter3" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traQuarter4" name="traQuarter4" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth1" name="traMonth1" value="0.00" onblur="statMoney('traMonth1');"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth2" name="traMonth2" value="0.00" onblur="statMoney('traMonth2');"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth3" name="traMonth3" value="0.00" onblur="statMoney('traMonth3');"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth4" name="traMonth4" value="0.00" onblur="statMoney('traMonth4');"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth5" name="traMonth5" value="0.00" onblur="statMoney('traMonth5');"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth6" name="traMonth6" value="0.00" onblur="statMoney('traMonth6');"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth7" name="traMonth7" value="0.00" onblur="statMoney('traMonth7');"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth8" name="traMonth8" value="0.00" onblur="statMoney('traMonth8');"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth9" name="traMonth9" value="0.00" onblur="statMoney('traMonth9');"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth10" name="traMonth10" value="0.00" onblur="statMoney('traMonth10');"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth11" name="traMonth11" value="0.00" onblur="statMoney('traMonth11');"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth12" name="traMonth12" value="0.00" onblur="statMoney('traMonth12');"></td>
                                </tr>
                                <tr align="left" height="30">
                                    <td bgcolor="#6fadf6"><span id="receiveExpense">招待费</span><input type="hidden" name="recExpense" value="招待费"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recSum" name="recSum" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recQuarter1" name="recQuarter1" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recQuarter2" name="recQuarter2" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recQuarter3" name="recQuarter3" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recQuarter4" name="recQuarter4" value="0.00" readonly></td>
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
                                    <td><input style="width: 100%;height: 100%" type="text" id="offSum" name="offSum" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offQuarter1" name="offQuarter1" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offQuarter2" name="offQuarter2" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offQuarter3" name="offQuarter3" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offQuarter4" name="offQuarter4" value="0.00" readonly></td>
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
                                    <td bgcolor="#6fadf6"><span id="sumExpense">合计</span><input type="hidden" name="sumExpense" value="合计"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="allSum" name="allSum" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumQuarter1" name="sumQuarter1" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumQuarter2" name="sumQuarter2" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumQuarter3" name="sumQuarter3" value="0.00" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumQuarter4" name="sumQuarter4" value="0.00" readonly></td>
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
                            </g:if>
                            <g:else>
                                <tr align="left" height="30">
                                    <td bgcolor="#6fadf6"><span id="travelExpense2">差旅费</span><input type="hidden" name="traExpense" value="差旅费"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traSum2" value="${budgetReportReceipts1.budgetTypeTotal}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traQuarter12" value="${budgetReportReceipts1.budgetFirstQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traQuarter22" value="${budgetReportReceipts1.budgetSecondQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traQuarter32" value="${budgetReportReceipts1.budgetThirdQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traQuarter42" value="${budgetReportReceipts1.budgetFourthQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth1222" name="traMonth1" value="${budgetReportReceipts1.budgetJanMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth22" name="traMonth2" value="${budgetReportReceipts1.budgetFebMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth32" name="traMonth3" value="${budgetReportReceipts1.budgetMarMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth42" name="traMonth4" value="${budgetReportReceipts1.budgetAprilMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth52" name="traMonth5" value="${budgetReportReceipts1.budgetMayMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth62" name="traMonth6" value="${budgetReportReceipts1.budgetJuneMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth72" name="traMonth7" value="${budgetReportReceipts1.budgetJulyMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth82" name="traMonth8" value="${budgetReportReceipts1.budgetAugustMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth92" name="traMonth9" value="${budgetReportReceipts1.budgetSepMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth102" name="traMonth10" value="${budgetReportReceipts1.budgetOctMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth112" name="traMonth11" value="${budgetReportReceipts1.budgetNovMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="traMonth122" name="traMonth12" value="${budgetReportReceipts1.budgetDecMoney}" readonly></td>
                                </tr>
                                <tr align="left" height="30">
                                    <td bgcolor="#6fadf6"><span id="receiveExpense2">招待费</span><input type="hidden" name="recExpense" value="招待费"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recSum2" value="${budgetReportReceipts2.budgetTypeTotal}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recQuarter12" value="${budgetReportReceipts2.budgetFirstQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recQuarter22" value="${budgetReportReceipts2.budgetSecondQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recQuarter32" value="${budgetReportReceipts2.budgetThirdQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recQuarter42" value="${budgetReportReceipts2.budgetFourthQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recMonth1222" name="recMonth1" value="${budgetReportReceipts2.budgetJanMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recMonth22" name="recMonth2" value="${budgetReportReceipts2.budgetFebMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recMonth32" name="recMonth3" value="${budgetReportReceipts2.budgetMarMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recMonth42" name="recMonth4" value="${budgetReportReceipts2.budgetAprilMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recMonth52" name="recMonth5" value="${budgetReportReceipts2.budgetMayMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recMonth62" name="recMonth6" value="${budgetReportReceipts2.budgetJuneMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recMonth72" name="recMonth7" value="${budgetReportReceipts2.budgetJulyMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recMonth82" name="recMonth8" value="${budgetReportReceipts2.budgetAugustMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recMonth92" name="recMonth9" value="${budgetReportReceipts2.budgetSepMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recMonth102" name="recMonth10" value="${budgetReportReceipts2.budgetOctMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recMonth112" name="recMonth11" value="${budgetReportReceipts2.budgetNovMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="recMonth122" name="recMonth12" value="${budgetReportReceipts2.budgetDecMoney}" readonly></td>
                                </tr>
                                <tr align="left" height="30">
                                    <td bgcolor="#6fadf6"><span id="officeExpense2">办公费</span><input type="hidden" name="offExpense" value="办公费"></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offSum2" value="${budgetReportReceipts3.budgetTypeTotal}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offQuarter12" value="${budgetReportReceipts3.budgetFirstQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offQuarter22" value="${budgetReportReceipts3.budgetSecondQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offQuarter32" value="${budgetReportReceipts3.budgetThirdQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offQuarter42" value="${budgetReportReceipts3.budgetFourthQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offMonth1222" name="offMonth1" value="${budgetReportReceipts3.budgetJanMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offMonth22" name="offMonth2" value="${budgetReportReceipts3.budgetFebMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offMonth32" name="offMonth3" value="${budgetReportReceipts3.budgetMarMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offMonth42" name="offMonth4" value="${budgetReportReceipts3.budgetAprilMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offMonth52" name="offMonth5" value="${budgetReportReceipts3.budgetMayMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offMonth62" name="offMonth6" value="${budgetReportReceipts3.budgetJuneMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offMonth72" name="offMonth7" value="${budgetReportReceipts3.budgetJulyMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offMonth82" name="offMonth8" value="${budgetReportReceipts3.budgetAugustMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offMonth92" name="offMonth9" value="${budgetReportReceipts3.budgetSepMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offMonth102" name="offMonth10" value="${budgetReportReceipts3.budgetOctMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offMonth112" name="offMonth11" value="${budgetReportReceipts3.budgetNovMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="offMonth122" name="offMonth12" value="${budgetReportReceipts3.budgetDecMoney}" readonly></td>
                                </tr>
                                <tr align="left" height="30">
                                    <td bgcolor="#6fadf6">合计</td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="allSum2" value="${budgetReportReceipts4.budgetTypeTotal}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumQuarter12" value="${budgetReportReceipts4.budgetFirstQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumQuarter22" value="${budgetReportReceipts4.budgetSecondQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumQuarter32" value="${budgetReportReceipts4.budgetThirdQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumQuarter42" value="${budgetReportReceipts4.budgetFourthQuarter}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumMonth1222" name="sumMonth1" value="${budgetReportReceipts4.budgetJanMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumMonth22" name="sumMonth2" value="${budgetReportReceipts4.budgetFebMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumMonth32" name="sumMonth3" value="${budgetReportReceipts4.budgetMarMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumMonth42" name="sumMonth4" value="${budgetReportReceipts4.budgetAprilMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumMonth52" name="sumMonth5" value="${budgetReportReceipts4.budgetMayMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumMonth62" name="sumMonth6" value="${budgetReportReceipts4.budgetJuneMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumMonth72" name="sumMonth7" value="${budgetReportReceipts4.budgetJulyMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumMonth82" name="sumMonth8" value="${budgetReportReceipts4.budgetAugustMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumMonth92" name="sumMonth9" value="${budgetReportReceipts4.budgetSepMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumMonth102" name="sumMonth10" value="${budgetReportReceipts4.budgetOctMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumMonth112" name="sumMonth11" value="${budgetReportReceipts4.budgetNovMoney}" readonly></td>
                                    <td><input style="width: 100%;height: 100%" type="text" id="sumMonth122" name="sumMonth12" value="${budgetReportReceipts4.budgetDecMoney}" readonly></td>
                                </tr>
                            </g:else>
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

    <td>&nbsp;&nbsp;</td>
    <td><input type="button" value="退回" onclick="location='../processes/initProcess'"></td>

    <td>&nbsp;&nbsp;</td>
</tr>
</table>
<g:if test="${budgetReportReceipts1!=null}">
    <script>document.getElementById("budgetCostCenter2").value = "${budgetReportReceipts1.budgetCostCenter}";</script>
    <script>document.getElementById("budgetCenter2").value = "${budgetReportReceipts1.budgetCenter}";</script>
</g:if>
</g:form>
<script type="text/javascript">
    function change(){
        var name = document.getElementById("budgetYear").value;
        var gForm = document.getElementById("gForm");
        gForm.action = "budgetDetail";
        gForm.controller = "budgetReportReceipts";
        gForm.submit();
    }
</script>
</body>
</html>