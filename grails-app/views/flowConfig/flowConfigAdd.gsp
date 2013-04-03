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
        function nonEmpty(){
            var firstName = document.getElementById("firstName").value;
            var postAcc = document.getElementById("postAcc").value;
            var payTeller = document.getElementById("payTeller").value;
            if(firstName=="" || postAcc=="" || payTeller=="") {
                alert("第一审批人、过账会计和付款出纳不能为空！");
                return false;
            }else{
                return true;
            }
        }
        function verifyName(){
            if(nonEmpty()){
                var roleName = document.getElementById("empRole").value;
                var roleType = roleName.substring(0,1);
                var firstName = document.getElementById("firstName").value;
                var secondName = document.getElementById("secondName").value;
                var thirdName = document.getElementById("thirdName").value;
                var fourthName = document.getElementById("fourthName").value;
                var fifthName = document.getElementById("fifthName").value;
                var postAcc = document.getElementById("postAcc").value;
                var payTeller = document.getElementById("payTeller").value;
                var flag1 = false;
                var flag2 = false;
                var flag3 = false;
                var flag4 = false;
                var flag5 = false;
                var flag6 = false;
                var flag7 = false;
                if(roleType=="报"){
                    var bxAppName = document.getElementById("bxAppName").value;
                    if(bxAppName.indexOf(firstName)>-1){
                        flag1 = true;
                        if(secondName == "" || (secondName != "" && bxAppName.indexOf(secondName)>-1)){
                            flag2 = true;
                        }
                        if(thirdName == "" || (thirdName != "" && bxAppName.indexOf(thirdName)>-1)){
                            flag3 = true;
                        }
                        if(fourthName == "" || (fourthName != "" && bxAppName.indexOf(fourthName)>-1)){
                            flag4 = true;
                        }
                        if(fifthName == "" || (fifthName != "" && bxAppName.indexOf(fifthName)>-1)){
                            flag5 = true;
                        }
                    }
                    var bxAccName = document.getElementById("bxAccName").value;
                    if(bxAccName.indexOf(postAcc)>-1){
                        flag6 = true;
                    }
                    var bxCasName = document.getElementById("bxCasName").value;
                    if(bxCasName.indexOf(payTeller)>-1){
                        flag7 = true;
                    }

                }else if(roleType=="借"){
                    var loanAppName = document.getElementById("loanAppName").value;
                    if(loanAppName.indexOf(firstName)>-1){
                        flag1 = true;
                        if(secondName == "" || (secondName != "" && loanAppName.indexOf(secondName)>-1)){
                            flag2 = true;
                        }
                        if(thirdName == "" || (thirdName != "" && loanAppName.indexOf(thirdName)>-1)){
                            flag3 = true;
                        }
                        if(fourthName == "" || (fourthName != "" && loanAppName.indexOf(fourthName)>-1)){
                            flag4 = true;
                        }
                        if(fifthName == "" || (fifthName != "" && loanAppName.indexOf(fifthName)>-1)){
                            flag5 = true;
                        }
                    }
                    var loanAccName = document.getElementById("loanAccName").value;
                    if(loanAccName.indexOf(postAcc)>-1){
                        flag6 = true;
                    }
                    var loanCasName = document.getElementById("loanCasName").value;
                    if(loanCasName.indexOf(payTeller)>-1){
                        flag7 = true;
                    }
                }
                if(flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7){
                    return true;
                }else{
                    alert("输入的用户不存在或者不是有对应功能的人，请重新输入！")
                    return false;
                }
            }
        }
        function commForm(){
            if(verifyName()){
                var gForm = document.getElementById("gForm");
                gForm.action = "flowConfigSave";
                gForm.controller = "flowConfig";
                gForm.submit();
            }

        }
    </script>
</head>

<body id="">
    <g:form id="gForm" name="gForm" action="flowConfigSave" controller="flowConfig" method="post">
        <input type="hidden" name="loanAppName" id="loanAppName" value="${loanAppName}">
        <input type="hidden" name="bxAppName" id="bxAppName" value="${bxAppName}">
        <input type="hidden" name="loanAccName" id="loanAccName" value="${loanAccName}">
        <input type="hidden" name="bxAccName" id="bxAccName" value="${bxAccName}">
        <input type="hidden" name="loanCasName" id="loanCasName" value="${loanCasName}">
        <input type="hidden" name="bxCasName" id="bxCasName" value="${bxCasName}">
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
                                                            <select style="width: 100%;height:100%;" name="empRole" id="empRole">
                                                                <g:each in="${list}" var="role">
                                                                    <option value="${role.authority}">${role.authority}</option>
                                                                </g:each>
                                                            </select>
                                                        </td>
                                                        <td width="8%">
                                                            <input type="text" style="width: 100%;height: 100%" name="firstName" id="firstName" value="">
                                                        </td>
                                                        <td width="8%">
                                                            <input type="text" style="width: 100%;height: 100%" name="secondName" id="secondName" value="">
                                                        </td>
                                                        <td width="8%">
                                                            <input type="text" style="width: 100%;height: 100%" name="thirdName" id="thirdName" value="">
                                                        </td>
                                                        <td width="8%">
                                                            <input type="text" style="width: 100%;height: 100%" name="fourthName" id="fourthName" value="">
                                                        </td>
                                                        <td width="8%">
                                                            <input type="text" style="width: 100%;height: 100%" name="fifthName" id="fifthName" value="">
                                                        </td>
                                                        <td width="8%">
                                                            <input type="text" style="width: 100%;height: 100%" name="postAcc" id="postAcc" value="">
                                                        </td>
                                                        <td width="10%">
                                                            <input type="text" style="width: 100%;height: 100%" name="payTeller" id="payTeller" value="">
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