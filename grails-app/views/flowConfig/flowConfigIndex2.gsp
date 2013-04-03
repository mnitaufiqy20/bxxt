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
        function delExaApp(id){
            var gForm = document.getElementById("gForm");
            if (confirm("确定要删除吗？")){
                gForm.action = "flowConfigD?id="+id;
                gForm.controller = "flowConfig";
                gForm.submit();
            }
        }
        function commForm(){
//            var companyNo = document.getElementById("companyNo").value;
//            if(companyNo=="" || companyNo.trim()==""){
//                alert("公司编码不能为空，请输入！");
//                return;
//            }
//            alert("aaaaaaaaaaaaa");
            var gForm = document.getElementById("gForm");
//            alert(gForm);
            gForm.action = "flowConfigAdd";
            gForm.controller = "flowConfig";
            gForm.submit();
        }
    </script>
</head>

<body id="">
    <g:form id="gForm" name="gForm" action="flowConfigSave" controller="flowConfig" method="post">
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
                                                        <td height="30" width="15%" align="right">公司编码：</td>
                                                        <td height="30" width="15%"><input type="text" id="companyNo" name="companyNo" value="${user.companyNo}" readonly></td>
                                                        %{--<td height="30" width="4%" align="left"><input type="button" value="新增" onclick=""></td>--}%
                                                        <td height="30" width="4%" align="left"><input type="button" value="新增" onclick="commForm();"></td>
                                                        <td width="4%" align="left"><input type="button" value="取消"></td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="8" height="30"><div style="width: 280px;background: #ADCDF4;">费用报销单  角色-审批节点对应表</div></td>
                                        </tr>
                                        <tr>
                                            <td colspan="8">
                                                <table width="100%" height="30"  border="1" cellpadding="0" cellspacing="0">
                                                    <tr style="background: #ADCDF4;">
                                                        <td height="25" width="15%">费用报销单    审批节点<br>角色-申请人</td>
                                                        <td width="8%" align="center">第一审批人</td>
                                                        <td width="8%" align="center">第二审批人</td>
                                                        <td width="8%" align="center">第三审批人</td>
                                                        <td width="8%" align="center">第四审批人</td>
                                                        <td width="8%" align="center">第五审批人</td>
                                                        <td width="8%" align="center">过账会计</td>
                                                        <td width="8%" align="center">付款出纳</td>
                                                        <td width="10%" align="center">操作</td>
                                                    </tr>
                                                    <g:each in="${listFYBX}" var="item" status="index">
                                                        <tr>
                                                            <td width="20%">
                                                                ${item.empRole}
                                                                <input type="hidden" name="typeBX" value="FYBX">
                                                            </td>
                                                            <td width="8%" align="center"> &nbsp;
                                                                ${item.firstName}
                                                            </td>
                                                            <td width="8%" align="center"> &nbsp;
                                                                ${item.secondName}
                                                            </td>
                                                            <td width="8%" align="center"> &nbsp;
                                                                ${item.thirdName}
                                                            </td>
                                                            <td width="8%" align="center"> &nbsp;
                                                                ${item.fourthName}
                                                            </td>
                                                            <td width="8%" align="center"> &nbsp;
                                                                ${item.fifthName}
                                                            </td>
                                                            <td width="8%" align="center">
                                                                ${item.postAcc}
                                                            </td>
                                                            <td width="8%" align="center">
                                                                ${item.payTeller}
                                                            </td>
                                                            <td width="10%" align="center"><a style="color: blue;" href="../flowConfig/flowConfigE?id=${item.id}">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" style="color: blue;" onclick="delExaApp(${item.id});">删除</a></td>
                                                        </tr>
                                                    </g:each>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="8" height="30"><div style="width: 280px;background: #ADCDF4;">借款单  角色-审批节点对应表</div></td>
                                        </tr>
                                            <table width="100%" height="30"  border="1" cellpadding="0" cellspacing="0">
                                                <tr style="background: #ADCDF4;">
                                                    <td height="30" width="15%">借款单    审批节点<br>角色-申请人</td>
                                                    <td width="8%" align="center">第一审批人</td>
                                                    <td width="8%" align="center">第二审批人</td>
                                                    <td width="8%" align="center">第三审批人</td>
                                                    <td width="8%" align="center">第四审批人</td>
                                                    <td width="8%" align="center">第五审批人</td>
                                                    <td width="8%" align="center">过账会计</td>
                                                    <td width="8%" align="center">付款出纳</td>
                                                    <td width="10%" align="center">操作</td>
                                                </tr>
                                                <g:each in="${listLOAN}" var="item" status="index">
                                                    <tr>
                                                        <td width="20%">
                                                            ${item.empRole}
                                                            <input type="hidden" name="typeBX" value="LOAN">
                                                        </td>
                                                        <td width="8%" align="center"> &nbsp;
                                                            ${item.firstName}
                                                        </td>
                                                        <td width="8%" align="center"> &nbsp;
                                                            ${item.secondName}
                                                        </td>
                                                        <td width="8%" align="center"> &nbsp;
                                                            ${item.thirdName}
                                                        </td>
                                                        <td width="8%" align="center"> &nbsp;
                                                            ${item.fourthName}
                                                        </td>
                                                        <td width="8%" align="center"> &nbsp;
                                                            ${item.fifthName}
                                                        </td>
                                                        <td width="8%" align="center">
                                                            ${item.postAcc}
                                                        </td>
                                                        <td width="8%" align="center">
                                                            ${item.payTeller}
                                                        </td>
                                                        <td width="10%" align="center"><a href="../flowConfig/flowConfigE?id=${item.id}" style="color: blue;">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" style="color: blue;" onclick="delExaApp(${item.id});">删除</a></td>
                                                    </tr>
                                                </g:each>
                                            </table>
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