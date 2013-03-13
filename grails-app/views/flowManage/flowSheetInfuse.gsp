<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-3-7
  Time: 上午11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>华电集团报销系统</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mark.css')}" type="text/css">
    <script src="${resource(dir: 'js/calendar', file: 'main.js')}"></script>
    <script type="text/javascript">
        function query(){
            var gForm = document.getElementById("gForm");
            gForm.action = "flowSheetInfusePath";
            gForm.controller = "flowSheetInfuse"
            gForm.submit();
        }
    </script>
</head>

<body id="">
<table width="100%"  height="200" border="0" cellpadding="0" cellspacing="0">
    <tr height="30">
        <td colspan="5" align="center" ><h2>流程发布</h2></td>
    </tr>
    <tr height="80">
        <td colspan="5" width="100%">
            <table  width="100%">
                <tr>
                    <td colspan="5" width="100%">
                        <g:form id="gForm" name="gForm" action="flowSheetInfusePath" controller="flowSheetInfuse" method="post">
                            <table  width="100%" >
                                <tr>
                                    <td height="30"><div style="width: 150px;background: #ADCDF4;">发布选项：</div></td>
                                </tr>
                                <tr>
                                    <td width="100%">
                                        <table>
                                            <tr>
                                                <td height="30" width="15%" align="right">请选择要发布的文件：</td>
                                                <td height="30" width="15%"><input type="file" id="filePath" name="filePath" value=""></td>
                                                <td height="30" width="4%" align="left"><input type="button" value="确定" onclick="query();"></td>
                                                <td width="4%" align="left"><input type="button" value="取消" onclick="location='../loanAppReceipts/loanAppReceiptsAdd'"></td>
                                                <td width="10%" align="left"><span style="color: red">${strMsg}</span></td>
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
</body>
</html>