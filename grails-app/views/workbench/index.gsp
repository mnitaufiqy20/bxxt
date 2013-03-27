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
        function aa() {
            var url = "http://localhost:8080/views/expenseAccount/loanAppReceiptsUpdate.gsp";
            var popScript = 'width=1024,height=768,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=yes,location=no, status=yes';
            window.open("http://localhost:8080/views/expenseAccount/loanAppReceiptsUpdate.gsp", null, 'width=1024,height=768,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=yes,location=no, status=yes');
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
                        <td width="20%" align="right" valign="middle" class="header_right">
                            <a>您&nbsp;好，&nbsp;<sec:loggedInUserInfo field="username"/></a> <br>
                            <a href="../logout/index">注销</a>
                        </td>
                    </tr>
                </table>
            </td>
        </g:form>
    </tr>
    <tr height="85%">
        <td valign="top" id="content">
            <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="content_bg"
                   style="background: #ADCDF4;">
                <tr>
                    <td width="204" height="85%">
                        <div id="sidebar">
                            <div class="box">
                                <h2>导航栏</h2>
                                <ul class="menu">

                                    <g:each in="${menuItems}" status="i" var="menuItem">
                                        <li class="level1"><a href="#">${menuItem.categoryName}</a>
                                            <ul class="level2">
                                                <g:each in="${menuItem.menus}" status="ii" var="menu">
                                                    <li class="level3"><a href="${menu.actionUrl}"
                                                                          target="contentFrame">${menu.menuName}</a>

                                                    </li>
                                                </g:each>
                                            </ul>
                                        </li>
                                    </g:each>

                                </ul>
                            </div>
                            <script type="text/javascript" src="${resource(dir: 'js', file: 'menu.js')}"></script>
                        </div>
                    </td>
                    <td height="100%">
                        <div id="main">
                            %{--<iframe id="blank" name="contentFrame" width="100%" height="100%" frameborder="0"--}%
                                    %{--scrolling="yes" src="../processes/initProcess" >--}%

                            %{--</iframe>--}%
                            <g:if test="${isAuth=='1'}">
                                <iframe id="blank" name="contentFrame" width="100%" height="100%" frameborder="0"
                                        scrolling="yes" src="../processes/initProcess" >
                                </iframe>
                            </g:if>
                            <g:else>
                                <iframe id="blank" name="contentFrame" width="100%" height="100%" frameborder="0"
                                        scrolling="yes"  >
                                </iframe>
                            </g:else>

                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr height="5%">
        <td align="center">
            <div id="pagefooter">
                <div class="footer_right">
                    Copyright(C) 北京市联系 地址：中国华电集团公司
                </div>

                <div class="clear"></div>
            </div>
        </td>
    </tr>
</table>
</body>
</html>