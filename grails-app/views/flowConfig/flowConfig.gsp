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
            var companyNo = document.getElementById("companyNo").value;
            if(companyNo=="" || companyNo.trim()==""){
                alert("公司编码不能为空，请输入！");
                return;
            }
            var gForm = document.getElementById("gForm");
            gForm.action = "flowConfigSave";
            gForm.controller = "flowConfig";
            gForm.submit();
        }
    </script>
</head>

<body id="">
    <g:form id="gForm" name="gForm" action="flowConfig" controller="flowConfig" method="post">
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
                                                    <td height="30" width="4%" align="left"><input type="button" value="确定" onclick="commForm();"></td>
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
        <tr>
            <td colspan="8" height="30"><div style="width: 280px;background: #ADCDF4;">费用报销清单  角色-审批节点对应表</div></td>
        </tr>
        <tr>
            <td colspan="8">
                <table width="100%" height="60"  border="1" cellpadding="0" cellspacing="0">
                    <tr style="background: #ADCDF4;">
                        <td height="30" width="15%">费用报销单    审批节点<br>角色-申请人</td>
                        <td width="10%">第一审批人</td>
                        <td width="10%">第二审批人</td>
                        <td width="10%">第三审批人</td>
                        <td width="10%">第四审批人</td>
                        <td width="10%">第五审批人</td>
                        <td width="10%">过账会计</td>
                        <td width="10%">付款出纳</td>
                    </tr>
                    <tr>
                        <td height="30" width="15%">公司领导</td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName101" name="examAppName101">
                                %{--<option value="无">无</option>--}%
                                %{--<option value="公司领导">公司领导</option>--}%
                                %{--<option value="公司分管领导">公司分管领导</option>--}%
                                %{--<option value="公司责任部门领导">公司责任部门领导</option>--}%
                                %{--<option value="部门领导">部门领导</option>--}%
                                %{--<option value="分部领导">分部领导</option>--}%
                                <g:each in="${userRoleList1}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName102" name="examAppName102" disabled>
                                <option value="无">无</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName103" name="examAppName103" disabled>
                                <option value="无">无</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName104" name="examAppName104" disabled>
                                <option value="无">无</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName105" name="examAppName105" disabled>
                                <option value="无">无</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="postAccount" name="postAccount">
                                %{--<option value="无">无</option>--}%
                                %{--<option value="过账会计">过账会计</option>--}%
                                %{--<option value="会计">会计</option>--}%
                                <g:each in="${userRoleList6}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="postCashier" name="postCashier">
                                %{--<option value="无">无</option>--}%
                                %{--<option value="过账会计">过账会计</option>--}%
                                %{--<option value="会计">会计</option>--}%
                                <g:each in="${userRoleList7}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td height="30" width="15%">公司分管领导</td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" style="width: 100%;height:100%;" id="examAppName201" name="examAppName201">
                                <g:each in="${userRoleList2}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName202" name="examAppName202">
                                <g:each in="${userRoleList1}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName203" name="examAppName203" disabled>
                                <option value="无">无</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName204" name="examAppName204" disabled>
                                <option value="无">无</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName205" name="examAppName205" disabled>
                                <option value="无">无</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="postAccount2" name="postAccount2">
                                <g:each in="${userRoleList6}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="postCashier2" name="postCashier2">
                                <g:each in="${userRoleList7}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td height="30" width="15%">公司责任部门领导</td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName301" name="examAppName301">
                                <g:each in="${userRoleList3}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName302" name="examAppName302">
                                <g:each in="${userRoleList2}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName303" name="examAppName303">
                                <g:each in="${userRoleList1}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName304" name="examAppName304" disabled>
                                <option value="无">无</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName305" name="examAppName305" disabled>
                                <option value="无">无</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="postAccount3" name="postAccount3">
                                <g:each in="${userRoleList6}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="postCashier3" name="postCashier3">
                                <g:each in="${userRoleList7}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td height="30" width="15%">部门领导</td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName401" name="examAppName401">
                                <g:each in="${userRoleList4}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName402" name="examAppName402">
                                <g:each in="${userRoleList3}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName403" name="examAppName403">
                                <g:each in="${userRoleList2}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName404" name="examAppName404">
                                <g:each in="${userRoleList1}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName405" name="examAppName405" disabled>
                                <option value="无">无</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="postAccount4" name="postAccount4">
                                <g:each in="${userRoleList6}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="postCashier4" name="postCashier4">
                                <g:each in="${userRoleList7}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td height="30" width="15%">分部领导</td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName501" name="examAppName501">
                                <g:each in="${userRoleList5}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName502" name="examAppName502">
                                <g:each in="${userRoleList4}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName503" name="examAppName103">
                                <g:each in="${userRoleList3}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName504" name="examAppName104">
                                <g:each in="${userRoleList2}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName505" name="examAppName505">
                                <g:each in="${userRoleList1}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="postAccount5" name="postAccount5">
                                <g:each in="${userRoleList6}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="postCashier5" name="postCashier5">
                                <g:each in="${userRoleList7}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td height="30" width="15%">员工</td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName601" name="examAppName601">
                                <g:each in="${userRoleList5}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName602" name="examAppName602">
                                <g:each in="${userRoleList4}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName603" name="examAppName603">
                                <g:each in="${userRoleList3}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName604" name="examAppName604">
                                <g:each in="${userRoleList2}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName605" name="examAppName605">
                                <g:each in="${userRoleList1}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="postAccount6" name="postAccount6">
                                <g:each in="${userRoleList6}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="postCashier6" name="postCashier6">
                                <g:each in="${userRoleList7}" var="item" status="index">
                                    <option value="${item.user.id}">${item.user.name}</option>
                                </g:each>
                            </select>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    <tr>
        <td colspan="8" height="30"><div style="width: 280px;background: #ADCDF4;">借款单  角色-审批节点对应表</div></td>
    </tr>
    <tr>
    <td colspan="8">
    <table width="100%" height="60"  border="1" cellpadding="0" cellspacing="0">
    <tr style="background: #ADCDF4;">
        <td height="30" width="15%">借款单    审批节点<br>角色-申请人</td>
        <td width="10%">第一审批人</td>
        <td width="10%">第二审批人</td>
        <td width="10%">第三审批人</td>
        <td width="10%">第四审批人</td>
        <td width="10%">第五审批人</td>
        <td width="10%">过账会计</td>
        <td width="10%">付款出纳</td>
    </tr>
    <tr>
        <td height="30" width="15%">公司领导</td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1101" name="examAppName1101">
            %{--<option value="无">无</option>--}%
            %{--<option value="公司领导">公司领导</option>--}%
            %{--<option value="公司分管领导">公司分管领导</option>--}%
            %{--<option value="公司责任部门领导">公司责任部门领导</option>--}%
            %{--<option value="部门领导">部门领导</option>--}%
            %{--<option value="分部领导">分部领导</option>--}%
                <g:each in="${userRoleList1}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1102" name="examAppName1102" disabled>
                <option value="无">无</option>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1103" name="examAppName1103" disabled>
                <option value="无">无</option>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1104" name="examAppName1104" disabled>
                <option value="无">无</option>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1105" name="examAppName1105" disabled>
                <option value="无">无</option>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="postAccount1" name="postAccount1">
            %{--<option value="无">无</option>--}%
            %{--<option value="过账会计">过账会计</option>--}%
            %{--<option value="会计">会计</option>--}%
                <g:each in="${userRoleList6}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="postCashier1" name="postCashier1">
            %{--<option value="无">无</option>--}%
            %{--<option value="过账会计">过账会计</option>--}%
            %{--<option value="会计">会计</option>--}%
                <g:each in="${userRoleList7}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
    </tr>
    <tr>
        <td height="30" width="15%">公司分管领导</td>
        <td width="10%">
            <select style="width: 100%;height:100%;" style="width: 100%;height:100%;" id="examAppName1201" name="examAppName1201">
                <g:each in="${userRoleList2}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1202" name="examAppName1202">
                <g:each in="${userRoleList1}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1203" name="examAppName1203" disabled>
                <option value="无">无</option>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1204" name="examAppName1204" disabled>
                <option value="无">无</option>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1205" name="examAppName1205" disabled>
                <option value="无">无</option>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="postAccount12" name="postAccount12">
                <g:each in="${userRoleList6}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="postCashier12" name="postCashier12">
                <g:each in="${userRoleList7}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
    </tr>
    <tr>
        <td height="30" width="15%">公司责任部门领导</td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1301" name="examAppName1301">
                <g:each in="${userRoleList3}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1302" name="examAppName1302">
                <g:each in="${userRoleList2}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1303" name="examAppName1303">
                <g:each in="${userRoleList1}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1304" name="examAppName1304" disabled>
                <option value="无">无</option>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1305" name="examAppName1305" disabled>
                <option value="无">无</option>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="postAccount13" name="postAccount13">
                <g:each in="${userRoleList6}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="postCashier13" name="postCashier13">
                <g:each in="${userRoleList7}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
    </tr>
    <tr>
        <td height="30" width="15%">部门领导</td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1401" name="examAppName1401">
                <g:each in="${userRoleList4}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1402" name="examAppName1402">
                <g:each in="${userRoleList3}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1403" name="examAppName1403">
                <g:each in="${userRoleList2}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1404" name="examAppName1404">
                <g:each in="${userRoleList1}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1405" name="examAppName1405" disabled>
                <option value="无">无</option>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="postAccount14" name="postAccount14">
                <g:each in="${userRoleList6}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="postCashier14" name="postCashier14">
                <g:each in="${userRoleList7}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
    </tr>
    <tr>
        <td height="30" width="15%">分部领导</td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1501" name="examAppName1501">
                <g:each in="${userRoleList5}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1502" name="examAppName1502">
                <g:each in="${userRoleList4}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1503" name="examAppName1503">
                <g:each in="${userRoleList3}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1504" name="examAppName1504">
                <g:each in="${userRoleList2}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1505" name="examAppName1505">
                <g:each in="${userRoleList1}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="postAccount15" name="postAccount15">
                <g:each in="${userRoleList6}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="postCashier15" name="postCashier15">
                <g:each in="${userRoleList7}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
    </tr>
    <tr>
        <td height="30" width="15%">员工</td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1601" name="examAppName1601">
                <g:each in="${userRoleList5}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1602" name="examAppName1602">
                <g:each in="${userRoleList4}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1603" name="examAppName1603">
                <g:each in="${userRoleList3}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1604" name="examAppName1604">
                <g:each in="${userRoleList2}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="examAppName1605" name="examAppName1605">
                <g:each in="${userRoleList1}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="postAccount16" name="postAccount16">
                <g:each in="${userRoleList6}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="10%">
            <select style="width: 100%;height:100%;" id="postCashier16" name="postCashier16">
                <g:each in="${userRoleList7}" var="item" status="index">
                    <option value="${item.user.id}">${item.user.name}</option>
                </g:each>
            </select>
        </td>
    </tr>
    </table>
    </td>
    </tr>
    </table>
</g:form>
</html>