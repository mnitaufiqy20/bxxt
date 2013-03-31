<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-3-21
  Time: 下午6:56
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>添加角色用户</title>
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
<g:form  id="gFrom" name="gFrom" action="saveUserRole"  controller="userRole" method="post">
    <table  width="100%">
        <tr><td align="center" colspan="4" width="100%"><h3>添加角色用户</h3></td></tr>
        <tr>
            <td width="10%">角色：</td>
            <td width="30%">${roleName}</td>
            <td width="45%">&nbsp;</td>
            <td width="15%" align="left">
                <input type="button" value="添加" onclick="commForm(0)">
                <input type="button" value="返回" onclick="javascript:history.back(-1);">
            </td>
        </tr>
    </table>
    <table  id="table1"  width="100%"  border="0" cellpadding="0" cellspacing="0">
        <tr style="background-color: #f5f5dc;" align="center">
            <td width="10%"><g:checkBox name="checkAll" disabled="true"></g:checkBox></td>
            <td width="20%">序号</td>
            <td width="35%">员工编号</td>
            <td width="35%">员工登录名</td>
        </tr>
        <g:each in="${userList}" var="item" status="index">
            <tr  align="center" onMouseOver="over()" onClick="change()" onMouseOut="out()">
                <td width="10%"><g:checkBox name="checkRole" id="checkRole" checked="false" value="${item.id}"></g:checkBox></td>
                <td width="20%">${index+1}</td>
                <td width="35%">${item.empNo}</td>
                <td width="35%">${item.username}</td>
            </tr>
        </g:each>

    </table>
    <input type="hidden" id="ckString" name="ckString" value="">
    <input type="hidden" name="roleId" id="roleId" value="${roleId}">
</g:form>
<SCRIPT language="javascript">
    function commForm(id){
        var gForm = document.getElementById("gFrom");
        if(id==0){
            var checkRole=document.getElementsByName("checkRole")
            var ckList=""
            for(var i=0;i<checkRole.length;i++){
                if(checkRole[i].checked==true){
                    ckList = ckList+checkRole[i].value+","
                }
            }
            if(ckList==""){
                alert("请选择需要添加的用户！");
            }else{
                document.getElementById("ckString").value= ckList
//                var gForm = document.getElementById("gFrom");
                gForm.action = "saveUserRole";
                gForm.controller = "userRole"
                gForm.submit();
            }
        }else if(id==1){
            gForm.action = "XXXX";
            gForm.controller = "userRole"
            gForm.submit();
        }

    }
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
</html>