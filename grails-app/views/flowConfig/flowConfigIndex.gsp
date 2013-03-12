<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-3-7
  Time: 下午1:49
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
        <table width="100%"  height="200" border="0" cellpadding="0" cellspacing="0">
            <tr height="30">
                <td colspan="5" align="center" ><h2>流程配置</h2></td>
            </tr>
            <tr height="80">
                <td colspan="5" width="100%">
                    <table  width="100%">
                        <tr>
                            <td colspan="5" width="100%">
                                <g:form id="gForm" name="gForm" action="commitLoanAppReceipts" controller="flowSheetInfuse" method="post">
                                    <table  width="100%" >
                                        <tr>
                                            <td height="30"><div style="width: 150px;background: #ADCDF4;">所属公司：</div></td>
                                        </tr>
                                        <tr>
                                            <td width="100%">
                                                <table>
                                                    <tr>
                                                        <td height="30" width="15%" align="right">请输入公司编码：</td>
                                                        <td height="30" width="15%"><input type="text" id="companyNo" name="companyNo" value=""></td>
                                                        <td height="30" width="4%" align="left"><input type="button" value="确定" onclick="query();"></td>
                                                        <td width="4%" align="left"><input type="button" value="取消" onclick="location='../loanAppReceipts/loanAppReceiptsAdd'"></td>
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
        </table>
</g:form>
</html>