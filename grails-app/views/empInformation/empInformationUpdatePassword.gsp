<%--
  Created by IntelliJ IDEA.
  User: Lau
  Date: 12-12-26
  Time: 下午7:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
%{--<%@ include file="/js/calendar/DateHTML.htm"%>--}%
<html>
<head>
    %{--<meta name="layout" content="main"/>--}%
    <title>华电集团报销系统</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mark.css')}" type="text/css">
    %{--<script language="javascript" src="/js/calendar/calendar.js"></script>--}%
    %{--<script language="javascript" src="/js/calendar/DateHTML.htm"></script>--}%
    %{--<script src="${resource(dir: 'js/calendar', file: 'jsdate.js')}"></script>--}%
    %{--<script src="${resource(dir: 'js/calendar', file: 'WdatePicker.js')}"></script>--}%
    <script src="${resource(dir: 'js/calendar', file: 'main.js')}"></script>
    <script type="text/javascript">
        function saveEmp(){
            document.getElementById("pw").innerHTML = "";
            document.getElementById("pwN").innerHTML = "";
            var pw1 = document.getElementById("passwordY").value;
            var pw2 = document.getElementById("password").value;
            if(pw1 != pw2){
                document.getElementById("pw").innerHTML = "输入的原密码错误，请重新输入！";
                return;
            }
            var pwNew1 = document.getElementById("passwordNew").value;
            var pwNew2 = document.getElementById("passwordNew2").value;
            if(pwNew1=="" && pwNew2==""){
                document.getElementById("pwN").innerHTML = "新密码不能为空，请重新输入！";
                return;
            }
            if(pwNew1!=pwNew2){
                document.getElementById("pwN").innerHTML = "两次输入的密码不一致，请重新输入！";
                return;
            }
            var gForm = document.getElementById("gForm");
            gForm.action = "saveEmpInformation?type=2";
            gForm.controller = "empInformation"
            gForm.submit();
        }
    </script>
</head>

<body id="">
<table width="100%"  height="170" border="0" cellpadding="0" cellspacing="0">
    <tr height="30">
        <td colspan="5" align="left" ><h2>本用户密码修改</h2></td>
    </tr>
    <tr height="80">
        <td colspan="5" width="100%">
            <table  width="100%">
                <tr>
                    <td colspan="5" width="100%">
                        <g:form id="gForm" name="gForm" action="saveEmpInformation" controller="empInformation" method="post">
                            <input type="hidden" name="empId" id="empId" value="${empInformation.id}">
                            <table  width="100%" >
                                <tr>
                                    <td height="30"><div style="width: 230px;background: #ADCDF4;">用户信息如下</div></td>
                                </tr>
                                <tr>
                                    <td colspan="5" width="100%">
                                        <table>
                                            <tr>
                                                <td height="30" width="30%" align="right">用户姓名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30" width="30%"><span>${empInformation.userName}</span></td>
                                            </tr>
                                            <tr>
                                                <td height="30" width="30%" align="right">原密码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30" width="30%">
                                                    <input type="text" name="password" id="password" value="">
                                                    <input type="hidden" name="passwordY" id="passwordY" value="${empInformation.userPwd}">
                                                </td>
                                                <td height="30" width="30%">
                                                    <span id="pw" style="color: red"></span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td height="30" width="30%" align="right">新密码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30" width="30%"><input type="text" name="passwordNew" id="passwordNew" value=""></td>
                                            </tr>
                                            <tr>
                                                <td height="30" width="30%" align="right">再输入一次新密码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                <td height="30" width="30%"><input type="text" name="passwordNew2" id="passwordNew2" value=""></td>
                                                <td height="30" width="20%">
                                                    <span id="pwN" style="color: red"></span>
                                                </td>
                                            </tr>
                                            <tr height="30" >
                                                <td height="30" width="10" align="right"><input type="button" value="保存" onclick="saveEmp();"></td>
                                                <td align="left"><input type="button" value="取消" onclick="location='../empInformation/empInformation'"></td>
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
</body>
</html>