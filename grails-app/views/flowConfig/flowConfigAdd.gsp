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
//            var companyNo = document.getElementById("companyNo").value;
//            if(companyNo=="" || companyNo.trim()==""){
//                alert("公司编码不能为空，请输入！");
//                return;
//            }
//            alert("aaaaaaaaaaaaa");
            var gForm = document.getElementById("gForm");
//            alert(gForm);
            gForm.action = "flowConfigSave";
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
                <td colspan="5" align="center" ><h2>新增角色流程配置</h2></td>
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
                                                        <td height="30" width="4%" align="left"><input type="button" value="保存" onclick="commForm();"></td>
                                                        <td width="4%" align="left"><input type="button" value="取消" onclick="javascript:history.back(-1);"></td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="8" height="30"><div style="width: 280px;background: #ADCDF4;">角色-审批节点对应表</div></td>
                                        </tr>
                                        <tr>
                                            <td colspan="8">
                                                <table width="100%" height="60"  border="1" cellpadding="0" cellspacing="0">
                                                    <tr style="background: #ADCDF4;">
                                                        <td height="30" width="15%">审批节点<br>角色-申请人</td>
                                                        <td width="10%">第一审批人</td>
                                                        <td width="10%">第二审批人</td>
                                                        <td width="10%">第三审批人</td>
                                                        <td width="10%">第四审批人</td>
                                                        <td width="10%">第五审批人</td>
                                                        <td width="10%">过账会计</td>
                                                        <td width="10%">付款出纳</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="25%">
                                                            <select style="width: 100%;height:100%;" name="empRole">
                                                                <g:each in="${list}" var="role">
                                                                    <option value="${role.authority}">${role.authority}</option>
                                                                </g:each>
                                                            </select>
                                                        </td>
                                                        <td width="8%">
                                                            <input type="text" style="width: 100%;height: 100%" name="firstName" value="">
                                                        </td>
                                                        <td width="8%">
                                                            <input type="text" style="width: 100%;height: 100%" name="secondName" value="">
                                                        </td>
                                                        <td width="8%">
                                                            <input type="text" style="width: 100%;height: 100%" name="thirdName" value="">
                                                        </td>
                                                        <td width="8%">
                                                            <input type="text" style="width: 100%;height: 100%" name="fourthName" value="">
                                                        </td>
                                                        <td width="8%">
                                                            <input type="text" style="width: 100%;height: 100%" name="fifthName" value="">
                                                        </td>
                                                        <td width="8%">
                                                            <input type="text" style="width: 100%;height: 100%" name="postAcc" value="">
                                                        </td>
                                                        <td width="10%">
                                                            <input type="text" style="width: 100%;height: 100%" name="payTeller" value="">
                                                        </td>
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