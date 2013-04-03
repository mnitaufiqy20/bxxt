<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-3-28
  Time: 下午2:18
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>华电集团报销系统</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mark.css')}" type="text/css">
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
            gForm.action = "cerIntegrationQuery";
            gForm.controller = "cerIntegration"
            gForm.submit();
        }
    </script>
</head>
<body id="">
<table width="100%"  height="170" border="0" cellpadding="0" cellspacing="0">
    <tr height="30">
        <td colspan="7" align="center" ><h2>凭证集成</h2></td>
    </tr>
    <tr height="80">
        <td colspan="7" width="100%">
            <table  width="100%">
                <tr>
                    <td colspan="7" width="100%">
                        <g:form id="gForm" name="gForm" action="cerIntegrationQuery" controller="cerIntegration" method="post">
                            <table  width="100%" >
                                <tr>
                                    <td height="30"><div style="width: 160px;background: #ADCDF4;">凭证查询</div></td>
                                </tr>
                                <tr>
                                    <td colspan="7" width="100%">
                                        <table>
                                            <tr>
                                                <td height="30" align="right">公司代码：</td>
                                                <td height="30"><span>${user.companyNo}</span><input type="hidden" id="companyCode" name="companyCode" value="${user.companyNo}"></td>
                                                <td align="right">单据号：</td>
                                                <td ><input type="text" name="cerNo" value="J11000001"></td>
                                                <td>申请人：</td>
                                                <td><input type="text" name="empName" value="张三"></td>
                                                <td width="100">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td align="right">状态：</td>
                                                <td>
                                                    <select id="status" name="status">
                                                        %{--<option value="已保存">已保存</option>--}%
                                                        %{--<option value="已提交">已提交</option>--}%
                                                        %{--<option value="已审核">已审核</option>--}%
                                                        <option value="已过帐">已过帐</option>
                                                        <option value="已付款">已付款</option>
                                                    </select>
                                                </td>
                                                <td align="left" colspan="4">申请时间：
                                                    <input type="text" id="startDate" name="startDate" value="2013-3-14" class="Wdate">
                                                    至 <input type="text" id="endDate" name="endDate" class="Wdate" value="2013-3-18">
                                                </td>
                                                <td height="30" width="3%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="查询" onclick="query();"></td>
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
    <tr height="60">
        <td colspan="7" width="100%">
            <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                <tr height="60">
                    <td colspan="7" width="100%">
                        <table width="100%" height="60">
                            <tr  height="30">
                                <td colspan="7"><div style="width: 180px;background: #ADCDF4;">查询结果</div></td>
                            </tr>
                            <tr>
                                <td>
                                    <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td width="100">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                            <td>
                                                <table width="800"  border="1" cellpadding="0" cellspacing="0">
                                                    <tr align="center" height="30" bgcolor="#ADCDF4">
                                                        <td height="30" width="10%">公司代码</td>
                                                        <td width="15%">单号</td>
                                                        <td width="15%">申请人</td>
                                                        <td width="20%">SAP会计凭证号</td>
                                                        <td width="25%">SAP会计凭证状态</td>
                                                        <td width="15%">消息描述</td>
                                                    </tr>
                                                    <g:each in="${cerIntegrationList}" var="item" status="index">
                                                        <tr align="center">
                                                            <td height="30">${item.companyCode}</td>
                                                            <td>${item.accYear}</td>
                                                            <td>${item.cerNo}</td>
                                                            <td>${item.recNo}</td>
                                                            <td>${item.mesType}</td>
                                                            <td>${item.mesDes}</td>
                                                        </tr>
                                                    </g:each>
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
</table>
</body>
</html>