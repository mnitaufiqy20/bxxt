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
    <script src="${resource(dir: 'js', file: 'jquery-1.6.4.min.js')}"></script>
    <script type="text/javascript">
        function commForm(){
            if(checkBudgetYear()){
                var gForm = document.getElementById("gForm");
                gForm.action = "budgetAdjustReceiptsSave";
                gForm.controller = "budgetAdjustReceipts"
                gForm.submit();
            }
        }
    </script>
</head>

<body id="">
    <g:form id="gForm" name="gForm" action="budgetAdjustReceiptsSave" controller="budgetAdjustReceipts" method="post">
    <input type="hidden" name="act" value="add">
    <table width="100%"  style="height:300px"  border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="9" align="center" height="30"><h2>预算调整单</h2></td>
        </tr>
        <tr width="100%">
            <td colspan="8">
                <table  width="100%">
                    <tr>
                        <td colspan="8">
                            <table style="height:120px"  width="100%">
                                <tr>
                                    <td height="30"><div style="width: 150px;background: #ADCDF4;">申请人填写：</div></td>
                                    %{--<td colspan="7" height="30">&nbsp;&nbsp;&nbsp;&nbsp;</td>--}%
                                    <td colspan="7" height="30" align="left">
                                        <div style="width: 650px;">
                                            单号：${budgetReportReceipts.budgetReportReceiptsId}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </div>
                                        <input type="hidden" value="${budgetReportReceipts.budgetReportReceiptsId}" name="budgetReportReceiptsId">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="8">
                                        <table width="100%">
                                            <tr  width="100%">
                                                <td height="30" width="8%">公司名称：</td>
                                                <td height="30" width="16%">
                                                    %{--系统自动带入--}%
                                                    <input type="text" id="budgetCompanyNo" name="budgetCompanyNo" value="${budgetReportReceipts.budgetCompanyNo}">
                                                </td>
                                                <td height="30" width="8%">费用种类：</td>
                                                %{--系统带入--}%
                                                <td height="30" width="16%">
                                                    <select id="budgetCostType" name="budgetCostType"  onchange="changeType();">
                                                        %{--<option value="-1">--请选择--</option>--}%
                                                        <option value="差旅费">差旅费</option>
                                                        <option value="招待费">招待费</option>
                                                        <option value="办公费">办公费</option>
                                                    </select>
                                                </td>
                                                <td height="30" width="12%">申报员工编号：</td>
                                                <td height="30" width="16%">
                                                    %{--系统自动带入当前登录人信息--}%
                                                    <input id="budgetReportEmpNo" name="budgetReportEmpNo" type="text" value="${budgetReportReceipts.budgetReportEmpNo}" onblur="emptyVerify('budgetEmpNo');">
                                                </td>
                                                <td height="30" width="9%">成本中心：</td>
                                                <td height="30" width="15%">
                                                    <select id="budgetCostCenter" name="budgetCostCenter">
                                                        <option value="1">2012-生安部-XXX</option>
                                                        <option value="2">2012-技术部-XXX</option>
                                                        <option value="3">2012-人事部-XXX</option>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td height="30">部门名称：</td>
                                                <td height="30">
                                                    %{--系统自动带入--}%
                                                    <input id="budgetDepartmentNo" name="budgetDepartmentNo" type="text" value="${budgetReportReceipts.budgetDepartmentNo}" readonly>
                                                </td>
                                                <td height="30">申请日期：</td>
                                                %{--系统带入当前日期，日期格式YYYYMMDD--}%
                                                <td height="30"><input id="budgetReportDate" name="budgetReportDate" type="text" value="${budgetReportReceipts.budgetReportDate}" readonly></td>
                                                <td height="30">申报员工职位：</td>
                                                %{--系统自动带入--}%
                                                <td><input type="text" id="budgetReportEmpPosition" name="budgetReportEmpPosition" type="text" value="${budgetReportReceipts.budgetReportEmpPosition}" readonly></td>
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
                                                %{--默认为当前年份--}%
                                                <td height="30"><input id="budgetYear" name="budgetYear" type="text" value="${budgetReportReceipts.budgetYear}" class="Wdate" onclick="SelectDate(this,'yyyy',null,null);" onchange="changeType();"></td>
                                                <td height="30">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30">申报员工姓名：</td>
                                                %{--系统自动带入--}%
                                                <td><input id="budgetReportEmpName" name="budgetReportEmpName" type="text" value="${budgetReportReceipts.budgetReportEmpName}" onblur="emptyVerify('budgetReportEmpName');"></td>
                                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
        %{--<tr>--}%
            %{--<td colspan="9" height="30"><div style="width: 150px;background: #ADCDF4;">审批历史</div></td>--}%
        %{--</tr>--}%
        <tr>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td colspan="5">
                <table width="600" height="60" border="1" cellpadding="0" cellspacing="0">
                    <tr align="center" bgcolor="#6fadf6">
                        <td height="30" width="20%">&nbsp;&nbsp;&nbsp;</td>
                        <td width="25%" id="original">调整前</td>
                        %{--该列默认金额为调整前金额--}%
                        <td width="25%" id="correct">调整后</td>
                        %{--计算公式：调整前金额－调整后金额（可为负）--}%
                        <td width="30%" id="change">变动额</td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">一月</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriMonth1" name="oriMonth1" value="${budgetReportReceipts.budgetJanMoney}" height="30" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth1" name="corMonth1" value="0.00" height="30" onblur="changeMoney('corMonth1');"></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth101" name="corMonth101" value="${budgetReportReceiptsT.budgetJanMoney}" height="30" onblur="changeMoney('corMonth101');"></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaMonth1" name="chaMonth1" value="0.00"  height="30" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">二月</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriMonth2" name="oriMonth2" value="${budgetReportReceipts.budgetFebMoney}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth2" name="corMonth2" value="0.00" onblur="changeMoney('corMonth2');"></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth201" name="corMonth201" value="${budgetReportReceiptsT.budgetFebMoney}" onblur="changeMoney('corMonth201');"></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaMonth2" name="chaMonth2" value="0.00" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">三月</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriMonth3" name="oriMonth3" value="${budgetReportReceipts.budgetMarMoney}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth3" name="corMonth3" value="0.00" onblur="changeMoney('corMonth3');"></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth301" name="corMonth301" value="${budgetReportReceiptsT.budgetMarMoney}" onblur="changeMoney('corMonth301');"></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaMonth3" name="chaMonth3" value="0.00" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">四月</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriMonth4" name="oriMonth4" value="${budgetReportReceipts.budgetAprilMoney}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth4" name="corMonth4" value="0.00" onblur="changeMoney('corMonth4');"></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth401" name="corMonth401" value="${budgetReportReceiptsT.budgetAprilMoney}" onblur="changeMoney('corMonth401');"></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaMonth4" name="chaMonth4" value="0.00" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">五月</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriMonth5" name="oriMonth5" value="${budgetReportReceipts.budgetMayMoney}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth5" name="corMonth5" value="0.00" onblur="changeMoney('corMonth5');"></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth501" name="corMonth501" value="${budgetReportReceiptsT.budgetMayMoney}" onblur="changeMoney('corMonth501');"></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaMonth5" name="chaMonth5" value="0.00" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">六月</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriMonth6" name="oriMonth6" value="${budgetReportReceipts.budgetJuneMoney}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth6" name="corMonth6" value="0.00" onblur="changeMoney('corMonth6');"></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth601" name="corMonth601" value="${budgetReportReceiptsT.budgetJuneMoney}" onblur="changeMoney('corMonth601');"></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaMonth6" name="chaMonth6" value="0.00" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">七月</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriMonth7" name="oriMonth7" value="${budgetReportReceipts.budgetJulyMoney}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth7" name="corMonth7" value="0.00" onblur="changeMoney('corMonth7');"></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth701" name="corMonth701" value="${budgetReportReceiptsT.budgetJulyMoney}" onblur="changeMoney('corMonth701');"></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaMonth7" name="chaMonth7" value="0.00" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">八月</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriMonth8" name="oriMonth8" value="${budgetReportReceipts.budgetAugustMoney}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth8" name="corMonth8" value="0.00" onblur="changeMoney('corMonth8');"></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth801" name="corMonth801" value="${budgetReportReceiptsT.budgetAugustMoney}" onblur="changeMoney('corMonth801');"></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaMonth8" name="chaMonth8" value="0.00" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">九月</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriMonth9" name="oriMonth9" value="${budgetReportReceipts.budgetSepMoney}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth9" name="corMonth9" value="0.00" onblur="changeMoney('corMonth9');"></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth901" name="corMonth901" value="${budgetReportReceiptsT.budgetSepMoney}" onblur="changeMoney('corMonth901');"></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaMonth9" name="chaMonth9" value="0.00" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">十月</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriMonth10" name="oriMonth10" value="${budgetReportReceipts.budgetOctMoney}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth10" name="corMonth10" value="0.00" onblur="changeMoney('corMonth10');"></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth1001" name="corMonth1001" value="${budgetReportReceiptsT.budgetOctMoney}" onblur="changeMoney('corMonth1001');"></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaMonth10" name="chaMonth10" value="0.00" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">十一月</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriMonth11" name="oriMonth11" value="${budgetReportReceipts.budgetNovMoney}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth11" name="corMonth11" value="0.00" onblur="changeMoney('corMonth11');"></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth1101" name="corMonth1101" value="${budgetReportReceiptsT.budgetNovMoney}" onblur="changeMoney('corMonth1101');"></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaMonth11" name="chaMonth11" value="0.00" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">十二月</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriMonth12" name="oriMonth12" value="${budgetReportReceipts.budgetDecMoney}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth12" name="corMonth12" value="0.00" onblur="changeMoney('corMonth12');"></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corMonth1201" name="corMonth1201" value="${budgetReportReceiptsT.budgetDecMoney}" onblur="changeMoney('corMonth1201');"></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaMonth12" name="chaMonth12" value="0.00" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">一季度</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriQuarter1" name="oriQuarter1" value="${budgetReportReceipts.budgetFirstQuarter}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corQuarter1" name="corQuarter1" value="0.00" readonly></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corQuarter101" name="corQuarter101" value="${budgetReportReceiptsT.budgetFirstQuarter}" readonly></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaQuarter1" name="chaQuarter1" value="0.00" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">二季度</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriQuarter2" name="oriQuarter2" value="${budgetReportReceipts.budgetSecondQuarter}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corQuarter2" name="corQuarter2" value="0.00" readonly></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corQuarter201" name="corQuarter201" value="${budgetReportReceiptsT.budgetSecondQuarter}" readonly></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaQuarter2" name="chaQuarter2" value="0.00" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">三季度</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriQuarter3" name="oriQuarter3" value="${budgetReportReceipts.budgetThirdQuarter}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corQuarter3" name="corQuarter3" value="0.00" readonly></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corQuarter301" name="corQuarter301" value="${budgetReportReceiptsT.budgetThirdQuarter}" readonly></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaQuarter3" name="chaQuarter3" value="0.00" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">四季度</td>
                        <td width="25%"><input style="width: 100%;height: 100%" type="text" id="oriQuarter4" name="oriQuarter4" value="${budgetReportReceipts.budgetFourthQuarter}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corQuarter4" name="corQuarter4" value="0.00" readonly></td>
                        </g:if>
                        <g:else>
                            <td width="25%"><input style="width: 100%;height: 100%" type="text" id="corQuarter401" name="corQuarter401" value="${budgetReportReceiptsT.budgetFourthQuarter}" readonly></td>
                        </g:else>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="chaQuarter4" name="chaQuarter4" value="0.00" readonly></td>
                    </tr>
                    <tr align="center">
                        <td height="30" width="20%" bgcolor="#6fadf6">金额合计</td>
                        <td width="30%"><input style="width: 100%;height: 100%" type="text" id="oriSum" name="oriSum" value="${budgetReportReceipts.budgetTypeTotal}" readonly></td>
                        <g:if test="${budgetReportReceiptsT==null}">
                            <td width="20%"><input style="width: 100%;height: 100%" type="text" id="corSum" name="corSum" value="0.00" readonly></td>
                        </g:if>
                        <g:else>
                            <td width="20%"><input style="width: 100%;height: 100%" type="text" id="corSum01" name="corSum01" value="${budgetReportReceiptsT.budgetTypeTotal}" readonly></td>
                        </g:else>

                        <td width="20%"><input style="width: 100%;height: 100%" type="text" id="chaSum" name="chaSum" value="0.00" readonly></td>
                    </tr>
                </table>
            </td>
            <td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td height="30">&nbsp;&nbsp;&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

            <td height="30"><input type="button" value="保存" onclick="commForm();"></td>
            <td>&nbsp;&nbsp;</td>
            <td><input type="button" value="退回" onclick=""></td>

            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;<div id="dataFlag" style="display: none">${budgetYear}</div></td>
        </tr>
    </table>
    <script>document.getElementById("budgetCostType").value = "${budgetReportReceipts.budgetCostType}";</script>
    <script>document.getElementById("budgetCostCenter").value = "${budgetReportReceipts.budgetCostCenter}";</script>
    <script>document.getElementById("budgetCenter").value = "${budgetReportReceipts.budgetCenter}";</script>
    </g:form>
    <script type="text/javascript">
        function checkBudgetYear(){
            var str = $("#dataFlag").html();
            var name = $("#budgetYear").val();
            var s = str.split(";")
            var index = 0;
            for(var i=0;i< s.length-1;i++){
                if(s[i]==name){
                    index = 1;
                    break;
                }
            }
            if(index==0){
                alert("该年份没有预算申报单，请重新选择！");
                return false;
            }else{
                return true;
            }
        }
        function changeType(){
//            var budgetYear = document.getElementById("budgetYear").value;
            if(checkBudgetYear()){
                var gForm = document.getElementById("gForm");
                gForm.action = "budgetAdjustReceiptsChangeType";
                gForm.controller = "budgetAdjustReceipts"
                gForm.submit();
            }
        }

        function changeMoney(id){
            var str = id.substring(3,id.length);
            var corMon = document.getElementById(id).value;
            if(corMon==""){
                alert("数据不能为空，请重新输入！");
                var e = document.getElementById('ori'+str).value;
                document.getElementById(id).value = e;
                return;
            }
            var regular = /^(([1-9]\d{0,9})|0)(\.\d{0,2})?$/;
            if(regular.test(corMon)==false){
                alert("输入金额格式错误，请重新输入！");
                var e = document.getElementById('ori'+str).value;
                document.getElementById(id).value = e;
                return;
            }
            var corS = corMon*1;
            document.getElementById(id).value = corS.toFixed(2);
            var ori = document.getElementById('ori'+str).value;
            var sumC = ori*1 - corMon*1;
            document.getElementById('cha'+str).value = sumC.toFixed(2);
            var num = "";
            var type = id.substring(0,3);
            if(id.length==9){
                num = id.substring(id.length-1,id.length);
            }else{
                num = id.substring(id.length-2,id.length);
            }
            var a = 0.00;
            var b = 0.00;
            var c = 0.00;
            var m = 0.00;
            var n = 0.00;
            var l = 0.00;
            if(num<=3){
                a = document.getElementById(type+"Month"+1).value;
                b = document.getElementById(type+"Month"+2).value;
                c = document.getElementById(type+"Month"+3).value
                var jd1 = a*1.00 + b*1.00 + c*1.00;
                document.getElementById(type+"Quarter"+1).value = jd1.toFixed(2);
                m = document.getElementById("chaMonth"+1).value;
                n = document.getElementById("chaMonth"+2).value;
                l = document.getElementById("chaMonth"+3).value;
                var chaSumM1 = m*1.00 + n*1.00 + l*1.00;
                document.getElementById("chaQuarter1").value = chaSumM1.toFixed(2);
            }else if(3<num && num<=6){
                a = document.getElementById(type+"Month"+4).value;
                b = document.getElementById(type+"Month"+5).value;
                c = document.getElementById(type+"Month"+6).value;
                var jd2  = a*1.00 + b*1.00 + c*1.00;
                document.getElementById(type+"Quarter"+2).value = jd2.toFixed(2);
                m = document.getElementById("chaMonth"+4).value;
                n = document.getElementById("chaMonth"+5).value;
                l = document.getElementById("chaMonth"+6).value;
                var chaSumM2 = m*1.00 + n*1.00 + l*1.00;
                document.getElementById("chaQuarter2").value = chaSumM2.toFixed(2);
            }else if(6<num && num<=9){
                a = document.getElementById(type+"Month"+7).value;
                b = document.getElementById(type+"Month"+8).value;
                c = document.getElementById(type+"Month"+9).value;
                var jd3  = a*1.00 + b*1.00 + c*1.00;
                document.getElementById(type+"Quarter"+3).value = jd3.toFixed(2);
                m = document.getElementById("chaMonth"+7).value;
                n = document.getElementById("chaMonth"+8).value;
                l = document.getElementById("chaMonth"+9).value;
                var chaSumM3 = m*1.00 + n*1.00 + l*1.00;
                document.getElementById("chaQuarter3").value = chaSumM3.toFixed(2);
            }else if(9<num && num<=12){
                a = document.getElementById(type+"Month"+10).value;
                b = document.getElementById(type+"Month"+11).value;
                c = document.getElementById(type+"Month"+12).value;
                var jd4  = a*1.00 + b*1.00 + c*1.00;
                document.getElementById(type+"Quarter"+4).value = jd4.toFixed(2);
                m = document.getElementById("chaMonth"+10).value;
                n = document.getElementById("chaMonth"+11).value;
                l = document.getElementById("chaMonth"+12).value;
                var chaSumM4 = m*1.00 + n*1.00 + l*1.00;
                document.getElementById("chaQuarter4").value = chaSumM4.toFixed(2);
            }
            var sum1 = document.getElementById(type+"Quarter"+1).value;
            var sum2 = document.getElementById(type+"Quarter"+2).value;
            var sum3 = document.getElementById(type+"Quarter"+3).value;
            var sum4 = document.getElementById(type+"Quarter"+4).value;
            var sum = sum1*1.00 + sum2*1.00 + sum3*1.00 + sum4*1.00;
            document.getElementById(type+"Sum").value = sum.toFixed(2);
            var sumC1 = document.getElementById("chaQuarter"+1).value;
            var sumC2 = document.getElementById("chaQuarter"+2).value;
            var sumC3 = document.getElementById("chaQuarter"+3).value;
            var sumC4 = document.getElementById("chaQuarter"+4).value;
            var sumC = sumC1*1.00 + sumC2*1.00 + sumC3*1.00 + sumC4*1.00;
            document.getElementById("chaSum").value = sumC.toFixed(2);
        }
    </script>
</body>
</html>