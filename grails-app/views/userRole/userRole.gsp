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
</head>
<body>
    <div align="center">
        <span><h3>用户角色列表</h3></span>
    </div>
<table id="table1"  width="100%"  border="0" cellpadding="0" cellspacing="0">
    <tr style="background-color: #f5f5dc;" align="center">
        <td width="5%">序号</td>
        <td width="20%">角色</td>
        <td width="70%">员工</td>
        <td width="5%">操作</td>
    </td>
    </tr>
    <g:each in="${list}" var="item" status="index">
        <tr align="center" onMouseOver="over()" onClick="change()" onMouseOut="out()">
            <td width="5%">${index+1}</td>
            <td width="20%">${item.roleName}</td>
            <td width="70%">&nbsp;${item.userName}</td>
            <td width="5%"><a href="../userRole/edit?roleId=${item.roleId}&roleName=${item.roleName}">查看</a></td>
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
</html>