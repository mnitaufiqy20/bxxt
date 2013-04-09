<%--
  Created by IntelliJ IDEA.
  User: xugj
  Date: 13-1-11
  Time: 下午4:26
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>待办列表展示</title>
    <script src="${resource(dir: 'js/calendar', file: 'jsdate.js')}"></script>
    <script src="${resource(dir: 'js/calendar', file: 'WdatePicker.js')}"></script>
    <style type="text/css">
    .Wdate{
        border:#999 1px solid;
        height:20px;
        background:#fff url(../images/main/datePicker.gif) no-repeat right;
    }

    .WdateFmtErr{
        font-weight:bold;
        color:red;
    }
    </style>
</head>
<body>
%{--<tr style="height: 20%">--}%
      %{--<h1><div style="color: blue;">欢迎进入费用报销系统</div></h1>--}%
      %{--<h3><div style="color: #000000;">费用报销单～待办事项</div></h3>--}%
%{--<td colspan="6" width="100%">--}%
    %{--<table width="100%"  border="0" cellpadding="0" cellspacing="0">--}%
        %{--<tr >--}%
            %{--<td colspan="6" width="100%">--}%
                %{--<table width="100%" >--}%
                    %{--<tr >--}%
                        %{--<td>--}%
                            %{--<table width="100%"  border="0" cellpadding="0" cellspacing="0">--}%
                                %{--<tr>--}%
                                    %{--<td colspan="6">--}%
                                        %{--<table width="100%"  border="1" cellpadding="0" cellspacing="0">--}%
                                            %{--<tr align="center">--}%
                                                %{--<td  width="4%">编号</td>--}%
                                                %{--<td width="27%">单号</td>--}%
                                                %{--<td width="15%">部门</td>--}%
                                                %{--<td width="15%">提交人</td>--}%
                                                %{--<td width="15%">报销金额</td>--}%
                                                %{--<td width="15%">状态</td>--}%
                                                %{--<td width="19%">操作</td>--}%
                                            %{--</tr>--}%
                                            %{--<g:each in="${loan_list}" var="item">--}%
                                                %{--<tr align="center">--}%
                                                    %{--<td height="30">--}%
                                                        %{--${item.wfNo}--}%
                                                        %{--<g:hiddenField name="transId" id="transId" value="${item.taskId}"/>--}%
                                                    %{--</td>--}%
                                                    %{--<td>${item.taskId}</td>--}%
                                                    %{--<td>${item.taskName}</td>--}%
                                                    %{--<td>${userId}</td>--}%
                                                    %{--<td>${item.operate}</td>--}%
                                                    %{--<td>${item.processName}</td>--}%
                                                    %{--<td>--}%
                                                     %{--<a style="color: blue;" href="../loanAppReceipts/editLoanAppReceipts?loanAppReceiptsId=${item.transId}">--}%
                                                         %{--查看--}%
                                                            %{--<g:if test="${item.status=='已保存'}">--}%
                                                                %{--修改--}%
                                                            %{--</g:if>--}%
                                                            %{--<g:else>查看</g:else>--}%
                                                        %{--</a>--}%
                                                    %{--</td>--}%
                                                %{--</tr>--}%
                                            %{--</g:each>--}%
                                        %{--</table>--}%
                                %{--</tr>--}%
                            %{--</table>--}%
                        %{--</td>--}%
                    %{--</tr>--}%
                %{--</table>--}%
            %{--</td>--}%
        %{--</tr>--}%
    %{--<g:if test="${loan_list.size()==3}">--}%
        %{--<tr>--}%
            %{--<td align="right" >--}%
                %{--<a style="color: blue;" href="../processes/processesListDetail?type=1"><b>点击查看更多～</b></a>--}%
            %{--</td>--}%
        %{--</tr>--}%
    %{--</g:if>--}%
    %{--</table>--}%
%{--</tr>--}%



<h3><div style="color: #000000;">借款单～待办事项</div></h3>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr >
        <td colspan="6" width="100%">
            <table width="100%" >
                <tr >
                    <td>
                        <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td colspan="6">
                                    <table width="100%"  border="1" cellpadding="0" cellspacing="0">
                                        <tr  align="center">
                                            <td  width="4%">序号</td>
                                            <td width="17%">申请单号</td>
                                            <td width="10%">任务单号</td>
                                            <td width="15%">任务名称</td>
                                            <td width="15%">申请人</td>
                                            <td width="15%">流程名称</td>
                                            <td width="15%">申请时间</td>
                                            <td width="19%">操作</td>
                                        </tr>
                                        <g:each in="${loanList}" var="item" status="index">
                                            <tr align="center">
                                                <td height="30">${index+1}</td>
                                                <td height="30">
                                                    ${item.wfNo}
                                                    <g:hiddenField name="transId" id="transId" value="${item.taskId}"/>
                                                </td>
                                                <td>${item.taskId}</td>
                                                <td>${item.taskName}</td>
                                                <td>${item.userName}</td>
                                                <td>${item.processName}</td>
                                                <td>${item.createTime}</td>
                                                <td>
                                                    <a style="color: blue;" href="../loanAppReceipts/examineLoanAppReceipts?loanAppReceiptsId=${item.wfNo}&taskId=${item.taskId}">
                                                        办理
                                                        %{--<g:if test="${item.status=='已保存'}">--}%
                                                            %{--修改--}%
                                                        %{--</g:if>--}%
                                                        %{--<g:else>查看</g:else>--}%
                                                    </a>
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
        </td>
    </tr>
    <g:if test="${loanList.size()==3}">
    <tr>
        <td align="right" >
            <a style="color: blue;" href="../processes/processesListDetail?type=2"><b>点击查看更多～</b></a>
        </td>
    </tr>
    </g:if>
</table>
<h3><div style="color: #000000;">报销单～待办事项</div></h3>
<table width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr >
        <td colspan="6" width="100%">
            <table width="100%" >
                <tr >
                    <td>
                        <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td colspan="6">
                                    <table width="100%"  border="1" cellpadding="0" cellspacing="0">
                                        <tr  align="center">
                                            <td  width="4%">序号</td>
                                            <td width="17%">申请单号</td>
                                            <td width="10%">任务单号</td>
                                            <td width="15%">任务名称</td>
                                            <td width="15%">申请人</td>
                                            <td width="15%">流程名称</td>
                                            <td width="15%">申请时间</td>
                                            <td width="19%">操作</td>
                                        </tr>
                                        <g:each in="${bxList}" var="item" status="index">
                                            <tr align="center">
                                                <td height="30">${index+1}</td>
                                                <td height="30">
                                                    ${item.wfNo}
                                                    <g:hiddenField name="transId" id="transId" value="${item.taskId}"/>
                                                </td>
                                                <td>${item.taskId}</td>
                                                <td>${item.taskName}</td>
                                                <td>${item.userName}</td>
                                                <td>${item.processName}</td>
                                                <td>${item.createTime}</td>
                                                <td>
                                                    <a style="color: blue;" href="../bxReceipt/examineBxReceipts?bxReceiptsId=${item.wfNo}&taskId=${item.taskId}">
                                                        办理
                                                        %{--<g:if test="${item.status=='已保存'}">--}%
                                                        %{--修改--}%
                                                        %{--</g:if>--}%
                                                        %{--<g:else>查看</g:else>--}%
                                                    </a>
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
        </td>
    </tr>
    <g:if test="${bxList.size()==3}">
        <tr>
            <td align="right" >
                <a style="color: blue;" href="../processes/processesListDetail?type=2"><b>点击查看更多～</b></a>
            </td>
        </tr>
    </g:if>
</table>
</body>

</html>