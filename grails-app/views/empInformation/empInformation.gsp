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
</head>

<body id="">
<table width="100%"  height="170" border="0" cellpadding="0" cellspacing="0">
    <tr height="30">
        <td colspan="5" align="center" ><h2>本用户基本信息</h2></td>
    </tr>
    <tr height="80">
        <td colspan="5" width="100%">
            <table  width="100%">
                <tr>
                    <td colspan="5" width="100%">
                        <g:form id="gForm" name="gForm" action="loanAppReceiptsGJQuery" controller="loanAppReceipts" method="post">
                            <table  width="100%" >
                                <tr>
                                    <td height="30"><div style="width: 260px;background: #ADCDF4;">本用户基本信息如下</div></td>
                                </tr>
                                <tr>
                                    <td colspan="5" width="100%">
                                        <table>
                                            <tr>
                                                <td height="30" width="4%" align="right">用户代码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30" width="3%"><span>${empInformation.empNo}</span></td>
                                                <td height="30" width="4%" align="right">用户姓名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30" width="8%"><span>${empInformation.userName}</span></td>
                                            </tr>
                                            <tr>
                                                <td height="30" width="4%" align="right">所属公司：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30" width="3%"><span>${empInformation.companyNo}</span></td>
                                                <td height="30" width="4%" align="right">身份证号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30" width="8%"><span>${empInformation.idNumber}</span></td>
                                            </tr>
                                            <tr>
                                                <td height="30" width="4%" align="right">部门：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30" width="3%"><span>${empInformation.departmentNo}</span></td>
                                                <td height="30" width="4%" align="right">移动电话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30" width="8%"><span>${empInformation.telephone}</span></td>
                                            </tr>
                                            <tr>
                                                <td height="30" width="4%" align="right">职位：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30" width="3%"><span>${empInformation.empPosition}</span></td>
                                                <td height="30" width="4%" align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30" width="8%"><span>&nbsp;&nbsp;</span></td>
                                            </tr>
                                            %{--<tr>--}%
                                                %{--<td>&nbsp;</td>--}%
                                            %{--</tr>--}%
                                            <tr height="30" >
                                                <td width="50">&nbsp;</td>
                                                <td height="30" width="50" align="right"><input type="button" value="修改资料" onclick="location='../empInformation/editEmpInformation'"></td>
                                                <td align="left"><input type="button" value="修改密码" onclick="location='../empInformation/editPwEmpInformation'"></td>
                                                <td width="50">&nbsp;</td>
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
        <td colspan="5" width="100%">
            <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                <tr height="60">
                    <td colspan="5" width="100%">
                        <table width="100%" height="60">
                            <tr  height="30">
                                <td colspan="5"><div style="width: 280px;background: #ADCDF4;">本用户目前已经可以访问的应用程序</div></td>
                            </tr>
                            <tr>
                                <td>
                                    <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td width="100">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                            <td>
                                                <table width="600"  border="1" cellpadding="0" cellspacing="0">
                                                    <tr align="center" height="30" bgcolor="#0a78a2">
                                                        <td height="30" width="10%">应用系统</td>
                                                        <td width="15%">本人对应的角色</td>
                                                    </tr>
                                                    <g:each in="${emp_list}" var="item" status="index">
                                                        <tr align="center">
                                                            <td height="30">
                                                                ${item.functionName}
                                                            </td>
                                                            <td>${item.roleName}</td>
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