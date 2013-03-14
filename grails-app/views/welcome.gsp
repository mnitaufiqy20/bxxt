<%--
  Created by IntelliJ IDEA.
  User: Lau
  Date: 12-12-26
  Time: 下午7:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    %{--<meta name="layout" content="main"/>--}%
    <title>华电集团报销系统</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'index.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'login.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'menu.css')}" type="text/css">
    <script type="text/javascript">
        function aa(){
            var url="http://localhost:8080/views/expenseAccount/loanAppReceiptsUpdate.gsp";
            var popScript = 'width=1024,height=768,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=yes,location=no, status=yes';
            window.open("http://localhost:8080/views/expenseAccount/loanAppReceiptsUpdate.gsp",null,'width=1024,height=768,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=yes,location=no, status=yes');
        }
    </script>
</head>

<body id="">
<table id="container" width="100%" height="100%" border="1" cellpadding="0" cellspacing="0">
    <tr height="10%">
        <g:form>
            <td>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" id="header">
                    <tr>
                        <td width="90%" height="71" class="header_left">&nbsp;</td>
                        <td width="10%" align="right" valign="middle" class="header_right">
                            <a>您&nbsp;好，&nbsp;<sec:loggedInUserInfo field="username"/></a>
                            <a href="../logout/index">注销</a>
                        </td>
                    </tr>
                </table>
            </td>
        </g:form>
    </tr>
    <tr height="85%">
        <td valign="top" id="content" >
            <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="content_bg" style="background: #ADCDF4;">
                <tr>
                    <td width="204" height="85%">
                        <div id="sidebar">
                            <div class="box">
                                <h2>导航栏</h2>
                                <ul class="menu">
                                    <li class="level1"><a href="#">系统配置</a>
                                        <ul class="level2">
                                            <li class="level3"><a href="#" target="contentFrame">角色管理</a></li>
                                            <li class="level3"><a href="#" target="contentFrame">权限分配</a></li>
                                        </ul>
                                    </li>
                                    <li class="level1"><a href="#">主数据</a>
                                        <ul class="level2">
                                            <li class="level3"><a href="../OrgStructure/index" target="contentFrame">组织架构</a></li>
                                            <li class="level3"><a href="../processes/initProcess" target="contentFrame">业务信息</a>
                                                <ul class="level4">
                                                    <li><a href="../budgetReportReceipts/bxStandard" target="contentFrame">报销标准</a></li>
                                                    <li><a href="../budgetReportReceipts/budgetDetail" target="contentFrame">预算费用</a></li>
                                                </ul>
                                            </li>
                                            <li class="level3"><a href="../processes/initProcess" target="contentFrame">信息提醒</a></li>
                                            <li class="level3"><a href="../processes/initProcess" target="contentFrame">日志管理</a></li>
                                            <li class="level3"><a href="../processes/initProcess" target="contentFrame">待办列表</a></li>
                                            <li class="level3"><a href="../empInformation/empInformation" target="contentFrame">员工信息</a></li>
                                        </ul>
                                    </li>
                                    <li class="level1"><a href="#">流程管理</a>
                                        <ul class="level2">
                                            <li class="level3"><a href="../flowLookUp/flowLookUp" target="contentFrame">流程查看</a></li>
                                            <li class="level3"><a href="../flowConfig/flowConfig" target="contentFrame">流程配置</a></li>
                                            <li class="level3"><a href="../flowSheetInfuse/flowSheetInfuse" target="contentFrame">流程发布</a></li>
                                            <li class="level3"><a href="#" target="contentFrame">费用报销核算流程</a></li>
                                            <li class="level3"><a href="#" target="contentFrame">员工借款核算流程</a></li>
                                        </ul>
                                    </li>
                                    <li class="level1"><a href="#">报销单</a>
                                        <ul class="level2">
                                            <li class="level3"><a href="../loanAppReceipts/loanAppReceiptsQuery" target="contentFrame">借款申请单</a></li>
                                            <li class="level3"><a href="../budgetReportReceipts/budgetReportReceipts" target="contentFrame">预算申报单</a></li>
                                            <li class="level3"><a href="../bxReceipt/index" target="contentFrame">费用报销单</a></li>
                                            <li class="level3"><a href="../budgetAdjustReceipts/budgetAdjustReceiptsQuery" target="contentFrame">预算调整单</a></li>
                                        </ul>
                                    </li>
                                    <li class="level1"><a href="#">集成接口</a>
                                        <ul class="level2">
                                            <li class="level3"><a href="#" target="contentFrame">SAP系统</a>
                                                <ul>
                                                    <li><a href="../costCenterImport/index2" target="contentFrame">成本中心导入</a></li>
                                                    <li><a href="../accSubjectImport/index2" target="contentFrame">会计科目导入</a></li>
                                                    <li><a href="#" target="contentFrame">费用报销科目导入</a></li>
                                                    <li><a href="#" target="contentFrame">凭证集成</a></li>
                                                    <li><a href="#" target="contentFrame">凭证集成</a></li>
                                                </ul>
                                            </li>
                                            <li class="level3"><a href="#" target="contentFrame">人事系统</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <script type="text/javascript" src="${resource(dir: 'js', file: 'menu.js')}"></script>
                        </div>
                    </td>
                    <td height="100%" >
                        <div id="main">
                            <iframe id="blank" name="contentFrame" width="100%" height="100%" frameborder="0"
                                    scrolling="yes" src="">
                            </iframe>
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr height="5%" >
        <td align="center">
            <div id="pagefooter">
                <div class="footer_right">
                    Copyright(C) 北京市联系孟敏 地址：中国华电集团公司
                </div>
                <div class="clear"></div>
            </div>
        </td>
    </tr>
</table>
</body>
</html>