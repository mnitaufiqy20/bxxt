<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-3-7
  Time: 下午4:10
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    %{--<meta name="layout" content="main"/>--}%
    <title>华电集团报销系统</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mark.css')}" type="text/css">
    <script type="text/javascript">
        function commForm(){
            var gForm = document.getElementById("gForm");
            gForm.action = "examineSave";
            gForm.controller = "loanAppReceipts";
            gForm.submit();
        }
    </script>
</head>

<body id="">
    <g:form id="gForm" name="gForm" action="commitLoanAppReceipts" controller="loanAppReceipts" method="post">
    <input type="hidden" name="act" value="update">
    <table width="100%"  height="100" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="8" align="center" height="40"><h2>流程查看界面</h2></td>
        </tr>
        <tr>
            <td colspan="8" height="30"><div style="width: 150px;background: #ADCDF4;">流程查看</div></td>
        </tr>
        <tr>
            <td colspan="8">
                <table width="100%" height="30"  border="1" cellpadding="0" cellspacing="0">
                    <tr style="background: #ADCDF4;">
                        <td height="30" width="10%">序号</td>
                        <td width="22%">流程定义ID</td>
                        <td width="24%">流程名称</td>
                        <td width="24%">流程描述</td>
                        <td width="24%" colspan="2">操作</td>
                    </tr>
                    <g:each in="${list}" var="item" status="index">
                    <tr>
                        <td height="30" width="10%">${index+1}</td>
                        <td width="22%">${item.processId}</td>
                        <td width="24%">${item.processName}</td>
                        <td width="24%">${item.description}</td>
                        <td width="14%"><a href="../flowLookUp/show?processId=${item.processId}" style="color: blue;">查看流程图</a></td>
                        <td width="10%"><a href="../flowLookUp/deleteProcess?deploymentId=${item.deploymentId}" style="color: blue;">删除</a></td>
                    </tr>
                    </g:each>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <span
            </td>
        </tr>
    </table>
</g:form>
</html>