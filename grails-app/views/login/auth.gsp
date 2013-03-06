<%--
  Created by IntelliJ IDEA.
  User: Lau
  Date: 12-12-26
  Time: 下午7:16
  To change this template use File | Settings | File Templates.


  登录页面
--%>
<!DOCTYPE html>
<html>
<head>
    %{--<meta name="layout" content="main"/>--}%
    <title>华电集团报销系统</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'index.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'login.css')}" type="text/css">
</head>

<body id="content">
<form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
    <table width=681 border="0" align="center" cellpadding=0 cellspacing=0 style="margin-top: 120px;">
        <tr>
            <td width=353 height=259 align=center valign=bottom background=../images/login/login_1.gif>
                <table width=90% border="0" cellspacing=2 cellpadding=0>
                    <tr>
                        <td align=right valign=bottom style="color: #05B8E4">
                            Power by Lau china Copyright 2012
                        </td>
                    </tr>
                </table>
            </td>
            <td width=195 height=90 background=../images/login/login_2.gif>
                <table width=190 height=60 border="0" align=center cellpadding=2 cellspacing=0>
                    <tr>
                        <td height=50 colspan="2" align=left>&nbsp;</td>
                    </tr>
                    <tr>
                        <td valign=bottom align=center style="width:90px;font-size: 12px;">
                            账号：
                        </td>
                        <td>
                            <g:textField name="j_username" id="username" value="" style="width: 130px;" maxlength="30"/>
                        </td>
                    </tr>
                    <tr>
                        <td height=10></td>
                    </tr>
                    <tr>
                        <td valign=bottom align=center style="font-size: 12px;width: 130px;">
                            密码：
                        </td>
                        <td>
                            <g:passwordField name="j_password" id="password" value="" style="width: 130px;"
                                             maxlength="30"/>
                        </td>

                    </tr>
                    <tr>
                        <td style="width: 130px;font-size: 12px;" height=40 colspan="2" align=center>
                            <g:if test="${flash.message}">
                                <div class="flash" style="width: 130px;" id="message">
                                    ${flash.message}
                                </div>
                            </g:if>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="0" align=center>
                            <div class="table_btn">
                                <div class="btn_left"></div>

                                <div class="btn_bar">
                                    <g:submitButton name="submit" value="登录"/>
                                </div>

                                <div class="btn_right"></div>
                            </div>
                        </td>
                        <td align=center>
                            <div class="table_btn">
                                <div class="btn_left"></div>

                                <div class="btn_bar">
                                    <input type="reset" value="取消"/>
                                </div>

                                <div class="btn_right"></div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td height=5 colspan="2"></td>
                    </tr>
                </table>
            </td>
            <td width=133 background=../images/login/login_3.gif>&nbsp;</td>
        </tr>
        <tr>
            <td height=161 colspan="3" background=../images/login/login_4.gif></td>
        </tr>
    </table>
</form>
<script type='text/javascript'>
    <!--
    (function () {
        document.forms['loginForm'].elements['j_username'].focus();
    })();
    // -->
</script>
</body>
</html>

