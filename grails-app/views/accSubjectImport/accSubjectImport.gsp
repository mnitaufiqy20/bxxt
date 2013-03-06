<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-2-28
  Time: 上午11:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>华电集团报销系统</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mark.css')}" type="text/css">
    <script src="${resource(dir: 'js/calendar', file: 'main.js')}"></script>
    <script type="text/javascript">
        function query(subjectNo){
            var gForm = document.getElementById("gForm");
            gForm.action = "accSubjectImport?subjectNo="+subjectNo;
            gForm.controller = "accSubjectImport"
            gForm.submit();
        }
    </script>
</head>
<body id="">
<table width="100%"  height="170" border="0" cellpadding="0" cellspacing="0">
    <tr height="30">
        <td colspan="6" align="center" ><h2>会计科目导入</h2></td>
    </tr>
    <tr height="80">
        <td colspan="6" width="100%">
            <table  width="100%">
                <tr>
                    <td colspan="6" width="100%">
                        <g:form id="gForm" name="gForm" action="accSubjectImport" controller="accSubjectImport" method="post">
                            <table  width="100%" >
                                <tr>
                                    <td height="30"><div style="width: 260px;background: #ADCDF4;">所属公司基本信息如下</div></td>
                                </tr>
                                <tr>
                                    <td colspan="6" width="100%">
                                        <table>
                                            <tr>
                                                <td height="30" width="30%" align="right">所属公司代码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30" width="20%"><span>${session.getAttribute("user").getProperties().companyNo}</span></td>
                                                <td width="30">&nbsp;<input type="hidden" id="companyCode" name="companyCode" value="${session.getAttribute("user").getProperties().companyNo}"></td>
                                                <td height="30" width="50" align="right"><input type="button" value="现金银行科目导入" onclick="query(1);"></td>
                                                <td width="30">&nbsp;</td>
                                                <td height="30" width="50" align="right"><input type="button" value="费用科目导入" onclick="query(5);"></td>
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
        <td colspan="6" width="100%">
            <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                <tr height="60">
                    <td colspan="6" width="100%">
                        <table width="100%" height="60">
                            <tr  height="30">
                                <td colspan="6"><div style="width: 280px;background: #ADCDF4;">会计科目导入结果</div></td>
                            </tr>
                            <tr>
                                <td>
                                    <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td width="100">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                            <td>
                                                <table width="600"  border="1" cellpadding="0" cellspacing="0">
                                                    <tr align="center" height="30" bgcolor="#ADCDF4">
                                                        <td height="30" width="10%">公司代码</td>
                                                        <td width="15%">科目编号</td>
                                                        <td width="15%">科目描述</td>
                                                    </tr>
                                                    <g:each in="${accSubjectImportList}" var="item" status="index">
                                                        <tr align="center">
                                                            <td height="30">${item.companyCode}</td>
                                                            <td>${item.subjectNo}</td>
                                                            <td>${item.subjectDes}</td>
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