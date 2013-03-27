<%--
  Created by IntelliJ IDEA.
  User: mengmin
  Date: 13-3-21
  Time: 上午10:31
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>用户角色列表</title>
    <STYLE>
    #table1{
        border-top:1px solid black;
        border-left:1px solid black;
        cursor:default;
    }
    #table1 td{
        border-bottom:1px solid black;
        border-right:1px solid black;
        height:23px;
    }
    </STYLE>
    <script type="text/javascript">
        function commForm(){
            var gForm = document.getElementById("gFrom");
            gForm.action = "queryUserRole";
            gForm.controller = "userRole"
            gForm.submit();
        }
    </script>
</head>
<body>
<g:form id="gFrom" name="gFrom" action="queryUserRole"  controller="userRole" method="post">
<table width="100%">
    <tr >
        <td  align="center" width="100%" colspan="2">
            <h3>用户角色列表</h3>
        </td>
    </tr>
    <tr align="center">
        <td width="30%">角色选择：
            <select name="role" id="role">
                <option value="0">--请选择--</option>
                <g:each in="${roleList}" var="role" status="index">
                    <option value="${role.id}">${role.authority}</option>
                </g:each>
            </select>
        </td>
        <td width="20%">用户选择：
            <select name="user" id="user">
                <option value="0">--请选择--</option>
                <g:each in="${userList}" var="user" status="index">
                    <option value="${user.id}">${user.name}</option>
                </g:each>
            </select>
        </td>
        <td width="20%">
            <input type="button" value="查询" onclick="commForm()">
        </td>
    </tr>
    <tr ><td align="center">&nbsp;</td></tr>
</table>
</g:form>
<table id="table1"  width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr style="background-color: #f5f5dc;" align="center">
        <td width="8%">序号</td>
        <td width="52%">角色</td>
        <td width="32%">员工</td>
        <td width="8%">操作</td>
    </td>
    </tr>
    <g:each in="${list}" var="item" status="index">
        <tr align="center" onMouseOver="over()" onClick="change()" onMouseOut="out()">
            <td width="8%">${index+1}</td>
            <td width="52%">${item.roleName}</td>
            <td width="32%">&nbsp;${item.userName}</td>
            <td width="8%"><a href="../userRole/edit?roleId=${item.roleId}&roleName=${item.roleName}">查看</a></td>
            <input type="hidden" value="${item.roleId}" name="roleId">
            <input type="hidden" value="${item.userId}" name="userId">
        </tr>
    </g:each>
</table>
<SCRIPT language="javascript">
    function change(){
        var oObj = event.srcElement;
        if(oObj.tagName.toLowerCase() == "td"){
            var oTr = oObj.parentNode;
            for(var i = 1; i < document.all.table1.rows.length; i++){
                document.all.table1.rows[i].style.backgroundColor = "";
                document.all.table1.rows[i].tag = false;
            }
            oTr.style.backgroundColor = "#CCCCFF";
            oTr.tag = true;
        }
    }
    function out(){
        var oObj = event.srcElement;
        if(oObj.tagName.toLowerCase() == "td"){
            var oTr = oObj.parentNode;
            if(!oTr.tag){
                oTr.style.backgroundColor = "";
            }
        }
    }
    function over(){
        var oObj = event.srcElement;
        if(oObj.tagName.toLowerCase() == "td"){
            var oTr = oObj.parentNode;
            if(!oTr.tag){
                oTr.style.backgroundColor = "#E1E9FD";
            }
        }
    }
</SCRIPT>
</body>
<script>document.getElementById("role").value = "${roleId}"</script>
<script>document.getElementById("user").value = "${userId}"</script>
</html>