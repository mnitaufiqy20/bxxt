<%--
  Created by IntelliJ IDEA.
  User: YoungerOu
  Date: 13-1-31
  Time: 下午12:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>华电集团报销系统</title>
</head>

<body id="">
<table width="100%" height="200" border="0" cellpadding="0" cellspacing="0">
    <tr height="90">
        <td colspan="6" width="100%">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr height="90">
                    <td colspan="6" width="100%">
                        <table width="100%" border="1" cellpadding="0" cellspacing="0">
                            <tr align="center" height="30" bgcolor="#6fadf6">
                                <td height="30" width="15%">单据类别</td>
                                <td width="15%">单号</td>
                                <td width="15%">部门</td>
                                <td width="15%">提交人</td>
                                <td width="15%">
                                    <g:if test="${list[0].type=='2'}">借款金额</g:if>
                                    <g:else>报销金额</g:else>
                                </td>
                                <td width="15%">状态</td>
                                <td width="10%">操作</td>
                            </tr>
                            <g:each in="${list}" var="item">
                                <tr align="left">
                                    <td height="30">
                                        <g:if test="${item.type == '1'}">费用报销单</g:if>
                                        <g:else>借款单</g:else>
                                    </td>
                                    <td>${item.transId}</td>
                                    <td>${item.deptCode}</td>
                                    <td>${item.userId}</td>
                                    <td>${item.amount}</td>
                                    <td>${item.status}</td>
                                    <td>
                                        <g:if test="${item.status == '已保存'}">
                                            <a href="../loanAppReceipts/editLoanAppReceipts?loanAppReceiptsId=${item.transId}">
                                                修改
                                            </a>
                                        </g:if>
                                        <g:else>
                                            <a href="../loanAppReceipts/lookUpLoanAppReceipts?loanAppReceiptsId=${item.transId}">
                                                查看
                                            </a>
                                        </g:else>
                                    </td>
                                </tr>
                            </g:each>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>