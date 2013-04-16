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
        function commForm(type){
            var readySubject = document.getElementById("readySubject").value;
            var bankSubject = document.getElementById("bankSubject").value;
            var type = "${loanAppReceipts.loanPaymentType}";
            if(type=="银行转账"){
                if(bankSubject==0){
                    alert("请选择银行科目！");
                    return;
                }
            }else{
                if(readySubject==0){
                    alert("请选择现金科目!");
                    return;
                }
            }
            var gForm = document.getElementById("gForm");
            if(type=="loan"){
                gForm.constructor = "loanCerIntegration";
                gForm.action = "loanCerIntegrationSave";
            }else{
                gForm.constructor = "bxCerIntegration";
                gForm.action = "bxCerIntegrationSave";
            }
            gForm.submit();
        }
    </script>
</head>
<body>
<g:form id="gForm" name="gForm" action="loanCerIntegration" controller="loanCerIntegration" method="post">
<table width="100%"  height="100" border="0" cellpadding="0" cellspacing="0">
    <tr height="30">
        <td colspan="5" align="center" ><h2>SAP凭证集成</h2></td>
    </tr>
    <tr height="70">
        <td colspan="6" width="100%">
            <table  width="100%">
                <tr>
                    <td colspan="5" height="30"><div style="width: 150px;background: #ADCDF4;">现金、银行科目：</div></td>
                </tr>
                <tr><td>&nbsp;</td></tr>
                <tr>
                    <td width="10%">&nbsp;&nbsp;
                        <g:if test="${type=="loan"}">
                            <input type="hidden" name="loanAppReceiptsId" id="loanAppReceiptsId" value="${loanAppReceipts.loanAppReceiptsId}">
                        </g:if>
                        <g:else>
                            <input type="hidden" name="bxNo" id="bxNo" value="${bxReceipt.bxNo}">
                        </g:else>
                        <input type="hidden" name="type" id="type" value="${type}">
                    </td>
                    <g:if test="${loanAppReceipts.loanPaymentType=="银行转账"}">
                        <td width="15%"> 现金科目：
                            <select name="readySubject" disabled>
                                <option value="0">-----请选择-----</option>
                            </select>
                        </td>
                        <td width="15%"> 银行科目：
                            <select name="bankSubject">
                                <option value="0">-----请选择-----</option>
                                <g:each in="${bankSubjectList}" var="item">
                                    <option value="${item.id}">${item.subjectNo}</option>
                                </g:each>
                            </select>
                        </td>
                    </g:if>
                    <g:else>
                        <td width="15%"> 现金科目：
                            <select name="readySubject">
                                <option value="0">-----请选择-----</option>
                                <g:each in="${readySubjectList}" var="item">
                                    <option value="${item.id}">${item.subjectNo}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="15%"> 银行科目：
                            <select name="bankSubject" disabled>
                                <option value="0">-----请选择-----</option>
                            </select>
                        </td>
                    </g:else>

                    <td  width="5%"><input type="button" value="确定" onclick="commForm('${type}');"></td>
                    <g:if test="${type=="loan"}">
                        <td width="10%"><input type="button" value="返回" onclick="location='../loanAppReceipts/loanAppReceiptsQuery'"></td>
                    </g:if>
                    <g:else>
                        <td width="10%"><input type="button" value="返回" onclick="location='../bxReceipt/index'"></td>
                    </g:else>
                </tr>
            </table>
        </td>
    </tr>
</table>
</g:form>
</body>
</html>