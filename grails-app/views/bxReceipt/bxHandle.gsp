<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-3-11
  Time: 下午3:28
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>华电集团报销系统</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mark.css')}" type="text/css">
    <script src="${resource(dir: 'js/calendar', file: 'jsdate.js')}"></script>
    <script src="${resource(dir: 'js/calendar', file: 'WdatePicker.js')}"></script>
    <script src="${resource(dir: 'js/calendar', file: 'main.js')}"></script>
    <script type="text/javascript">
        function changeIdea(){
            var idea = document.getElementById("examAppIdea").value;
            if(idea=="approve") {
                document.getElementById("goBack").disabled = true;
                document.getElementById("comm").disabled = false;
            }else{
                document.getElementById("comm").disabled = true;
                document.getElementById("goBack").disabled = false;
            }
        }
    </script>
    <script type="text/javascript">
        function commForm(){
            var gForm = document.getElementById("gForm");
            gForm.action = "examineSave";
            gForm.controller = "loanAppReceipts";
            gForm.submit();
        }
    </script>

</head>
<body>
<g:form id="gForm" name="gForm" action="commitLoanAppReceipts" controller="loanAppReceipts" method="post">
<table width="100%"  height="430" border="0" cellpadding="0" cellspacing="0">
<tr>
    <td colspan="9" align="center" height="40"><h2>费用报销流程任务办理</h2></td>
</tr>

    <tr>
        <td colspan="9" height="30"><div style="width: 150px;background: #ADCDF4;">业务相关</div></td>
    </tr>
    <tr>
        <td colspan="9" height="30">单号：${loanAppReceiptsId}
            <input type="hidden" name="loanAppReceiptsId" id="loanAppReceiptsId" value="${loanAppReceiptsId}">
        </td>

    </tr>

<tr>
    <td colspan="9" height="30"><div style="width: 150px;background: #ADCDF4;">审批历史</div></td>
</tr>
<tr>
    <td>&nbsp;&nbsp;</td>
    <td colspan="7">
        <table width="100%" height="60"  border="1" cellpadding="0" cellspacing="0">
            <tr align="center">
                <td height="30" width="10%">序号</td>
                <td width="30%">审批时间</td>
                <td width="20%">审批人职位</td>
                <td width="15%">审批人</td>
                <td width="20%">审批意见</td>
            </tr>
            <g:each in="${historyLists}" var="item" status="index">
                <tr align="center">
                    <td height="30">
                        ${index+1}
                    </td>
                    <td>${item.endTime}</td>
                    <td>${item.examAppNamePosition}</td>
                    <td>${item.assignee}</td>
                    <td>${item.result}</td>
                </tr>
            </g:each>
        </table>
    </td>
    <td>&nbsp;&nbsp;</td>
</tr>
<tr>
    <td colspan="9" height="30"><div style="width: 150px;background: #ADCDF4;">审批意见</div></td>
</tr>
<tr>
    <td>&nbsp;&nbsp;</td>
    <td colspan="7">
        <table width="100%" height="60"  border="1" cellpadding="0" cellspacing="0">
            <tr align="center">
                <td height="30" width="10%">序号</td>
                <td width="30%">审批时间</td>
                <td width="20%">审批人职位</td>
                <td width="15%">审批人</td>
                <td width="20%">审批意见</td>
            </tr>
            <tr align="center">
                <td height="30" width="10%">1<input type="hidden" name="taskId" value="${taskId}"></td>
                <td width="30%">${nowDate}</td>
                <td width="20%">${user.empPosition}</td>
                <td width="15%">${user.userName}</td>
                <td  width="20%">
                    <select id="examAppIdea" width="20%" name="examAppIdea" onchange="changeIdea();">
                        <option width="20%" value="approve">同意</option>
                        <option width="20%" value="reject">不同意</option>
                    </select>
                </td>
            </tr>
        </table>
    </td>
    <td>&nbsp;&nbsp;</td>
</tr>
<tr>
    <td height="30">&nbsp;&nbsp;&nbsp;</td>
</tr>
<tr>
    <td>&nbsp;&nbsp;</td>
    %{--<td height="30"><input type="button" value="保存"  onclick="commForm(0);"></td>--}%
    <td height="30">&nbsp;&nbsp;&nbsp;</td>
    <td><input type="button" id="comm" value="提交"  onclick="commForm();"></td>
    <td><input type="button" id="goBack" value="退回" onclick="commForm();"></td>
    %{--<td><input type="button" value="返回" onclick="location='../processes/initProcess'"></td>--}%
    <td><input type="button" value="执行过账" disabled></td>
    <td>
        <span style="color: red">
            <div>此功能在所有审批通过后</div>
            <div>由负责费用报销的</div>
            <div>会计人员执行！</div>
        </span>
    </td>
    <td>&nbsp;&nbsp;</td>
    <td>&nbsp;&nbsp;</td>
    <td>&nbsp;&nbsp;</td>
</tr>
</table>
</g:form>
</body>
</html>