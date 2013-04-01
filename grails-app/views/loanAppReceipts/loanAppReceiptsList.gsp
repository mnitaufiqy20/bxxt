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
    %{--<script src="${resource(dir: 'js/calendar', file: 'jsdate.js')}"></script>--}%
    %{--<script src="${resource(dir: 'js/calendar', file: 'WdatePicker.js')}"></script>--}%
    <script src="${resource(dir: 'js/calendar', file: 'main.js')}"></script>
    <link rel="stylesheet" href="${resource(dir: 'css/calendar', file: 'jquery-ui.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: '/resources/demos/', file: 'style.css')}" type="text/css">
    <script src="${resource(dir: 'js/calendar', file: 'jquery-1.8.3.js')}"></script>
    <script src="${resource(dir: 'js/calendar', file: 'jquery-ui.js')}"></script>
    <script type="text/javascript">
        $(function() {
            $( "#startDate" ).datepicker({
//        defaultDate: "+0w",
//        changeMonth: true,
                numberOfMonths: 1,
                onClose: function( selectedDate ) {
                    $( "#endDate" ).datepicker( "option", "minDate", selectedDate );
                }
            });
            $( "#endDate" ).datepicker({
//        defaultDate: "+0w",
//        changeMonth: true,
                numberOfMonths: 1,
                onClose: function( selectedDate ) {
                    $( "#startDate" ).datepicker( "option", "maxDate", selectedDate );
                }
            });
        });
    </script>
    <script type="text/javascript">
        function query(){
            var gForm = document.getElementById("gForm");
            gForm.action = "loanAppReceiptsGJQuery";
            gForm.controller = "loanAppReceipts"
            gForm.submit();
        }
    </script>
</head>

<body id="">
<table width="100%"  height="200" border="0" cellpadding="0" cellspacing="0">
    <tr height="30">
        <td colspan="6" align="center" ><h2>借款单列表</h2></td>
    </tr>
    <tr height="80">
        <td colspan="6" width="100%">
            <table  width="100%">
                <tr>
                    <td colspan="6" width="100%">
                        <g:form id="gForm" name="gForm" action="loanAppReceiptsGJQuery" controller="loanAppReceipts" method="post">
                            <table  width="100%" >
                                <tr>
                                    <td height="30"><div style="width: 150px;background: #ADCDF4;">查询条件：</div></td>
                                </tr>
                                <tr>
                                    <td colspan="6" width="100%">
                                        <table>
                                            <tr>
                                                <td height="30" width="6%" align="right">单号：</td>
                                                <td height="30" width="15%"><input id="loanAppReceiptsId" name="loanAppReceiptsId" type="text" value="${loanAppReceiptsId}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30" width="10%" align="right">状态：</td>
                                                <td height="30" width="8%">
                                                    <select id="loanStatus" name="loanStatus">
                                                        <option value="已保存">已保存</option>
                                                        <option value="已提交">已提交</option>
                                                        <option value="已审核">已审核</option>
                                                        <option value="已过帐">已过帐</option>
                                                        <option value="已付款">已付款</option>
                                                    </select>
                                                </td>
                                                <td height="30" width="15%" align="right">时间范围：</td>
                                                <td height="30" width="50%">
                                                    %{--<input type="text" name="startDate" value="${startDate}" class="Wdate" onclick="SelectDate(this,'yyyy-MM-dd',null,null);">--}%
                                                    <input type="text" id="startDate" name="startDate" value="${startDate}" class="Wdate">
                                                    至
                                                    %{--<input type="text" id="endDate" name="endDate" class="Wdate" value="${endDate}" onclick="SelectDate(this,'yyyy-MM-dd',null,null);">--}%
                                                    <input type="text" id="endDate" name="endDate" class="Wdate" value="${endDate}">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>

                                                <g:if test="${a =="V"}">
                                                    <td height="30" colspan="3" align="right"><input type="button" value="查询" onclick="query();"></td>
                                                </g:if>
                                                <g:else>
                                                    <td height="30" colspan="3" align="right"><input type="button" value="查询" disabled></td>
                                                </g:else>
                                                <g:if test="${b =="N"}">
                                                    <td colspan="2" align="right"><input type="button" value="新增" onclick="location='../loanAppReceipts/loanAppReceiptsAdd'"></td>
                                                </g:if>
                                                <g:else>
                                                    <td colspan="2" align="right"><input type="button" value="新增" disabled></td>
                                                </g:else>
                                                <td>&nbsp;</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </g:form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr height="90">
        <td colspan="6" width="100%">
            <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                <tr height="90">
                    <td colspan="6" width="100%">
                        <table width="100%" height="90">
                            <tr  height="30">
                                <td colspan="6"><div style="width: 150px;background: #ADCDF4;">查询结果</div></td>
                            </tr>
                            <tr>
                                <td>
                                    <table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                            <td colspan="5">
                                                <table width="100%"  border="1" cellpadding="0" cellspacing="0">
                                                    <tr align="center" height="30">
                                                        <td height="30" width="8%">序号</td>
                                                        <td width="27%">单号</td>
                                                        <td width="15%">申请日期</td>
                                                        <td width="15%">借款金额</td>
                                                        <td width="20%">状态</td>
                                                        <td width="10%">操作</td>
                                                    </tr>
                                                    <g:each in="${loan_list}" var="item" status="index">
                                                        <tr align="center">
                                                            <td height="30">
                                                                ${index+1}
                                                                <g:hiddenField name="loanAppReceiptsId" id="loanAppReceiptsId" value="${item.loanAppReceiptsId}"/>
                                                                <input type="hidden" name="menuId" value="${menuId}">
                                                            </td>
                                                            <td>${item.loanAppReceiptsId}</td>
                                                            <td>${item.loanBegDate}</td>
                                                            <td>${item.loanMoney}</td>
                                                            <td>${item.loanStatus}</td>
                                                            <td>
                                                                <g:if test="${item.loanStatus=='已保存'}">
                                                                    <g:if test="${c =="E"}">
                                                                        <a href="../loanAppReceipts/editLoanAppReceipts?loanAppReceiptsId=${item.loanAppReceiptsId}&menuId=${menuId}">
                                                                            修改
                                                                        </a>
                                                                    </g:if>
                                                                    <g:else>
                                                                        <a href="#">
                                                                            修改
                                                                        </a>
                                                                    </g:else>

                                                                </g:if>

                                                                    %{--<g:if test="!${item.loanStatus}.equals('已保存')">--}%
                                                                    %{--查看--}%
                                                                    %{--</g:if>--}%
                                                                <g:else>
                                                                    <g:if test="${a =="V"}">
                                                                        <a href="../loanAppReceipts/lookUpLoanAppReceipts?loanAppReceiptsId=${item.loanAppReceiptsId}&menuId=${menuId}">
                                                                            查看
                                                                        </a>
                                                                    </g:if>
                                                                    <g:else>
                                                                        <a href="#">
                                                                            查看
                                                                        </a>
                                                                    </g:else>
                                                                </g:else>
                                                            </td>
                                                        </tr>
                                                    </g:each>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr height="30">
                                            <td colspan="6" align="right">
                                                <input type="button" value="上一页" disabled>每页共10条，目前是第1页<input type="button" value="下一页">
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
</table>
<script>document.getElementById("loanStatus").value = "${loanStatus}";</script>
</body>
</html>