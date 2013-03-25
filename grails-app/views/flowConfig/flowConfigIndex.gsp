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
                                                        <td height="30" width="4%" align="left"><input type="button" value="确定" onclick="commForm();"></td>
                                                        <td width="4%" align="left"><input type="button" value="取消" onclick="location='../loanAppReceipts/loanAppReceiptsAdd'"></td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="8" height="30"><div style="width: 280px;background: #ADCDF4;">费用报销单  角色-审批节点对应表</div></td>
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
                                                    <g:each in="${list}" var="item" status="index">
                                                        <tr>
                                                            <td width="15%">
                                                                <input name="empRole" style="width: 100%;height:100%;" value="${item.empRole}" readonly>
                                                                <input type="hidden" name="typeBX" value="FYBX">
                                                            </td>
                                                            <td width="10%">
                                                                <select style="width: 100%;height:100%;" name="firstName">
                                                                    <g:each in="${item.firstName}" var="fir" status="ind">
                                                                        <g:if test="${fir.user.userId==item.firId}">
                                                                            <option value="${fir.user.userId}" selected="true">${fir.user.name}</option>
                                                                        </g:if>
                                                                        <g:else>
                                                                            <option value="${fir.user.userId}">${fir.user.name}</option>
                                                                        </g:else>
                                                                    </g:each>
                                                                </select>
                                                            </td>
                                                            <td width="10%">
                                                                <select style="width: 100%;height:100%;" name="secondName">
                                                                    <g:each in="${item.secondName}" var="fir2">
                                                                        <g:if test="${fir2.user.userId==item.secId}">
                                                                            <option value="${fir2.user.userId}" selected="true">${fir2.user.name}</option>
                                                                        </g:if>
                                                                        <g:else>
                                                                            <option value="${fir2.user.userId}">${fir2.user.name}</option>
                                                                        </g:else>
                                                                    </g:each>
                                                                </select>
                                                            </td>
                                                            <td width="10%">
                                                                <select style="width: 100%;height:100%;" name="thirdName">
                                                                    <g:each in="${item.thirdName}" var="fir3">
                                                                        <g:if test="${fir3.user.userId==item.thiId}">
                                                                            <option value="${fir3.user.userId}" selected="true">${fir3.user.name}</option>
                                                                        </g:if>
                                                                        <g:else>
                                                                            <option value="${fir3.user.userId}">${fir3.user.name}</option>
                                                                        </g:else>
                                                                    </g:each>
                                                                </select>
                                                            </td>
                                                            <td width="10%">
                                                                <select style="width: 100%;height:100%;" name="fourthName">
                                                                    <g:each in="${item.fourthName}" var="fir4">
                                                                        <g:if test="${fir4.user.userId==item.fouId}">
                                                                            <option value="${fir4.user.userId}" selected="true">${fir4.user.name}</option>
                                                                        </g:if>
                                                                        <g:else>
                                                                            <option value="${fir4.user.userId}">${fir4.user.name}</option>
                                                                        </g:else>
                                                                    </g:each>
                                                                </select>
                                                            </td>
                                                            <td width="10%">
                                                                <select style="width: 100%;height:100%;" name="fifthName">
                                                                    <g:each in="${item.fifthName}" var="fir5">
                                                                        <g:if test="${fir5.user.userId==item.fifId}">
                                                                            <option value="${fir5.user.userId}" selected="true">${fir5.user.name}</option>
                                                                        </g:if>
                                                                        <g:else>
                                                                            <option value="${fir5.user.userId}">${fir5.user.name}</option>
                                                                        </g:else>
                                                                    </g:each>
                                                                </select>
                                                            </td>
                                                            <td width="10%">
                                                                <select style="width: 100%;height:100%;" name="postAcc">
                                                                    <g:each in="${item.postAcc}" var="fir6">
                                                                        <g:if test="${fir6.user.userId==item.posId}">
                                                                            <option value="${fir6.user.userId}" selected="true">${fir6.user.name}</option>
                                                                        </g:if>
                                                                        <g:else>
                                                                            <option value="${fir6.user.userId}">${fir6.user.name}</option>
                                                                        </g:else>
                                                                    </g:each>
                                                                </select>
                                                            </td>
                                                            <td width="10%">
                                                                <select style="width: 100%;height:100%;" name="payTeller">
                                                                    <g:each in="${item.payTeller}" var="fir7">
                                                                        <g:if test="${fir7.user.userId==item.payId}">
                                                                            <option value="${fir7.user.userId}" selected="true">${fir7.user.name}</option>
                                                                        </g:if>
                                                                        <g:else>
                                                                            <option value="${fir7.user.userId}">${fir7.user.name}</option>
                                                                        </g:else>
                                                                    </g:each>
                                                                </select>
                                                            </td>
                                                        </tr>
                                                    </g:each>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="8" height="30"><div style="width: 280px;background: #ADCDF4;">借款单  角色-审批节点对应表</div></td>
                                        </tr>
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
                                                <g:each in="${list2}" var="item" status="index">
                                                    <tr>
                                                        <td width="15%">
                                                            <input name="empRole" style="width: 100%;height:100%;" value="${item.empRole}" readonly>
                                                            <input type="hidden" name="typeBX" value="LOAN">
                                                        </td>
                                                        <td width="10%">
                                                            <select style="width: 100%;height:100%;" name="firstName">
                                                                <g:each in="${item.firstName}" var="fir" status="ind">
                                                                    <g:if test="${fir.user.userId==item.firId}">
                                                                        <option value="${fir.user.userId}" selected="true">${fir.user.name}</option>
                                                                    </g:if>
                                                                    <g:else>
                                                                        <option value="${fir.user.userId}">${fir.user.name}</option>
                                                                    </g:else>
                                                                </g:each>
                                                            </select>
                                                        </td>
                                                        <td width="10%">
                                                            <select style="width: 100%;height:100%;" name="secondName">
                                                                <g:each in="${item.secondName}" var="fir2">
                                                                    <g:if test="${fir2.user.userId==item.secId}">
                                                                        <option value="${fir2.user.userId}" selected="true">${fir2.user.name}</option>
                                                                    </g:if>
                                                                    <g:else>
                                                                        <option value="${fir2.user.userId}">${fir2.user.name}</option>
                                                                    </g:else>
                                                                </g:each>
                                                            </select>
                                                        </td>
                                                        <td width="10%">
                                                            <select style="width: 100%;height:100%;" name="thirdName">
                                                                <g:each in="${item.thirdName}" var="fir3">
                                                                    <g:if test="${fir3.user.userId==item.thiId}">
                                                                        <option value="${fir3.user.userId}" selected="true">${fir3.user.name}</option>
                                                                    </g:if>
                                                                    <g:else>
                                                                        <option value="${fir3.user.userId}">${fir3.user.name}</option>
                                                                    </g:else>
                                                                </g:each>
                                                            </select>
                                                        </td>
                                                        <td width="10%">
                                                            <select style="width: 100%;height:100%;" name="fourthName">
                                                                <g:each in="${item.fourthName}" var="fir4">
                                                                    <g:if test="${fir4.user.userId==item.fouId}">
                                                                        <option value="${fir4.user.userId}" selected="true">${fir4.user.name}</option>
                                                                    </g:if>
                                                                    <g:else>
                                                                        <option value="${fir4.user.userId}">${fir4.user.name}</option>
                                                                    </g:else>
                                                                </g:each>
                                                            </select>
                                                        </td>
                                                        <td width="10%">
                                                            <select style="width: 100%;height:100%;" name="fifthName">
                                                                <g:each in="${item.fifthName}" var="fir5">
                                                                    <g:if test="${fir5.user.userId==item.fifId}">
                                                                        <option value="${fir5.user.userId}" selected="true">${fir5.user.name}</option>
                                                                    </g:if>
                                                                    <g:else>
                                                                        <option value="${fir5.user.userId}">${fir5.user.name}</option>
                                                                    </g:else>
                                                                </g:each>
                                                            </select>
                                                        </td>
                                                        <td width="10%">
                                                            <select style="width: 100%;height:100%;" name="postAcc">
                                                                <g:each in="${item.postAcc}" var="fir6">
                                                                    <g:if test="${fir6.user.userId==item.posId}">
                                                                        <option value="${fir6.user.userId}" selected="true">${fir6.user.name}</option>
                                                                    </g:if>
                                                                    <g:else>
                                                                        <option value="${fir6.user.userId}">${fir6.user.name}</option>
                                                                    </g:else>
                                                                </g:each>
                                                            </select>
                                                        </td>
                                                        <td width="10%">
                                                            <select style="width: 100%;height:100%;" name="payTeller">
                                                                <g:each in="${item.payTeller}" var="fir7">
                                                                    <g:if test="${fir7.user.userId==item.payId}">
                                                                        <option value="${fir7.user.userId}" selected="true">${fir7.user.name}</option>
                                                                    </g:if>
                                                                    <g:else>
                                                                        <option value="${fir7.user.userId}">${fir7.user.name}</option>
                                                                    </g:else>
                                                                </g:each>
                                                            </select>
                                                        </td>
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