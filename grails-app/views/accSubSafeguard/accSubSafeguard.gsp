<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-3-20
  Time: 下午1:43
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <script type="text/javascript">
        function commForm(){
            var gForm = document.getElementById("gForm");
            gForm.constructor = "accSubSafeguard";
            gForm.action = "accSubSafeguardSave";
            gForm.submit();
        }
    </script>
</head>
<body>
<g:form id="gForm" name="gForm" action="accSubSafeguard" controller="accSubSafeguard" method="post">
    <table width="100%"  height="200" border="0" cellpadding="0" cellspacing="0">
        <tr height="30">
            <td colspan="5" align="center" ><h2>会计科目维护</h2></td>
        </tr>
        <tr height="80">
            <td colspan="5" width="100%">
                <table  width="100%">
                    <tr>
                        <td colspan="5" width="100%">

                            <table  width="100%" >
                                <tr>
                                    <td height="30"><div style="width: 150px;background: #ADCDF4;">会计科目：</div></td>
                                </tr>
                                <tr>
                                    <td width="100%">
                                        <table width="100%" height="60"  border="1" cellpadding="0" cellspacing="0">
                                            <tr align="center">
                                                <td width="30%" height="30">申请单类型</td>
                                                <td width="20%">费用种类</td>
                                                <td width="15%">费用类型</td>
                                                <td width="20%">会计科目</td>
                                            </tr>
                                            <g:if test="${accSubSafeguard1==null}">
                                            <tr align="center">
                                                <td width="30%" height="30"><input type="text" style="width:100%;height:100%" id="loanAppType" name="loanAppType" value="借款单"></td>
                                                <td width="20%"><input type="text" style="width:100%;height:100%" id="loanCostKind" name="loanCostKind" value="无"></td>
                                                <td width="15%"><input type="text" style="width:100%;height:100%" id="loanCostType" name="loanCostType" value="无"></td>
                                                <td width="20%"><input type="text" style="width:100%;height:100%" id="loanAppSub" name="loanAppSub" value="5014072000"></td>
                                            </tr>
                                            </g:if>
                                            <g:else>
                                            <tr align="center">
                                                <td width="30%" height="30"><input type="text" style="width:100%;height:100%" id="loanAppType1" name="loanAppType1" value="借款单"></td>
                                                <td width="20%"><input type="text" style="width:100%;height:100%" id="loanCostKind1" name="loanCostKind1" value="无"></td>
                                                <td width="15%"><input type="text" style="width:100%;height:100%" id="loanCostType1" name="loanCostType1" value="无"></td>
                                                <td width="20%"><input type="text" style="width:100%;height:100%" id="loanAppSub1" name="loanAppSub1" value="${accSubSafeguard1.appSub}"></td>
                                            </tr>
                                            </g:else>
                                        </table>
                                    </td>
                                </tr>
                            </table>

                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;&nbsp;</td>
                        <td height="30"><input type="button" value="保存" onclick="commForm();"></td>
                        <td>&nbsp;&nbsp;</td>
                        <td>&nbsp;&nbsp;</td>
                        <td><input type="button" value="返回" onclick="location='../loanAppReceipts/loanAppReceiptsQuery'"></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</g:form>
</body>
</html>