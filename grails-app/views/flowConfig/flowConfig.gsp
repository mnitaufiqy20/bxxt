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
            var gForm = document.getElementById("gForm");
            gForm.action = "examineSave";
            gForm.controller = "loanAppReceipts";
            gForm.submit();
        }
    </script>
</head>

<body id="">
    <g:form id="gForm" name="gForm" action="commitLoanAppReceipts" controller="loanAppReceipts" method="post">
    <input type="hidden" name="act" value="update">
    <table width="100%"  height="200" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td colspan="8" align="center" height="40"><h2>流程配置界面</h2></td>
        </tr>
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
                                                    <td height="30" width="4%" align="left"><input type="button" value="确定" onclick="query();"></td>
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
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName102" name="examAppName102">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName103" name="examAppName103">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName104" name="examAppName104">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName105" name="examAppName105">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="postAccount" name="postAccount">
                                <option value="无">无</option>
                                <option value="过账会计">过账会计</option>
                                <option value="会计">会计</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="postCashier" name="postCashier">
                                <option value="无">无</option>
                                <option value="过账会计">过账会计</option>
                                <option value="会计">会计</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td height="30" width="15%">公司分管领导</td>
                        <td width="10%">
                            <select style="width: 100%;height:100%;" id="examAppName201" name="examAppName101">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName202" name="examAppName102">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName203" name="examAppName103">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName204" name="examAppName104">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName205" name="examAppName105">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="postAccount2" name="postAccount">
                                <option value="无">无</option>
                                <option value="过账会计">过账会计</option>
                                <option value="会计">会计</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="postCashier2" name="postCashier">
                                <option value="无">无</option>
                                <option value="过账会计">过账会计</option>
                                <option value="会计">会计</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td height="30" width="15%">公司责任部门领导</td>
                        <td width="10%">
                            <select id="examAppName301" name="examAppName101">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName302" name="examAppName102">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName303" name="examAppName103">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName304" name="examAppName104">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName305" name="examAppName105">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="postAccount3" name="postAccount">
                                <option value="无">无</option>
                                <option value="过账会计">过账会计</option>
                                <option value="会计">会计</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="postCashier3" name="postCashier">
                                <option value="无">无</option>
                                <option value="过账会计">过账会计</option>
                                <option value="会计">会计</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td height="30" width="15%">部门领导</td>
                        <td width="10%">
                            <select id="examAppName401" name="examAppName101">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName402" name="examAppName102">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName403" name="examAppName103">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName404" name="examAppName104">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName405" name="examAppName105">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="postAccount4" name="postAccount">
                                <option value="无">无</option>
                                <option value="过账会计">过账会计</option>
                                <option value="会计">会计</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="postCashier4" name="postCashier">
                                <option value="无">无</option>
                                <option value="过账会计">过账会计</option>
                                <option value="会计">会计</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td height="30" width="15%">分部领导</td>
                        <td width="10%">
                            <select id="examAppName501" name="examAppName101">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName502" name="examAppName102">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName503" name="examAppName103">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName504" name="examAppName104">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName505" name="examAppName105">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="postAccount5" name="postAccount">
                                <option value="无">无</option>
                                <option value="过账会计">过账会计</option>
                                <option value="会计">会计</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="postCashier5" name="postCashier">
                                <option value="无">无</option>
                                <option value="过账会计">过账会计</option>
                                <option value="会计">会计</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td height="30" width="15%">员工</td>
                        <td width="10%">
                            <select id="examAppName601" name="examAppName101">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName602" name="examAppName102">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName603" name="examAppName103">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName604" name="examAppName104">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="examAppName605" name="examAppName105">
                                <option value="无">无</option>
                                <option value="公司领导">公司领导</option>
                                <option value="公司分管领导">公司分管领导</option>
                                <option value="公司责任部门领导">公司责任部门领导</option>
                                <option value="部门领导">部门领导</option>
                                <option value="分部领导">分部领导</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="postAccount6" name="postAccount">
                                <option value="无">无</option>
                                <option value="过账会计">过账会计</option>
                                <option value="会计">会计</option>
                            </select>
                        </td>
                        <td width="10%">
                            <select id="postCashier6" name="postCashier">
                                <option value="无">无</option>
                                <option value="过账会计">过账会计</option>
                                <option value="会计">会计</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</g:form>
</html>