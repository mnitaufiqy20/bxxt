<%--
  Created by IntelliJ IDEA.
  User: MengMin
  Date: 13-3-12
  Time: 下午1:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>角色管理</title>
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
<g:form id="gFrom" name="gFrom" action="index"  controller="roleManagement" method="post">
<table width="100%">
    <tr >
        <td  align="center" width="100%" colspan="2">
            <h3>角色列表</h3>
        </td>
    </tr>
    <tr align="center">
        <td>应用系统：
            <select name="menu">
                <option value="借款申请单">借款申请单</option>
                <option value="费用报销单">费用报销单</option>
            </select>
        </td>
        <td >角色编码：<g:textField name="roleCode" value="${roleCode}"   maxlength="30"/></td>
    </tr>
    <tr align="center">
        <td >角色名称：<g:textField name="roleName" value="${roleName}" style="width: 130px;" maxlength="30"/></td>
        <td>
            <input type="button" value="查询" onclick="commForm(0)">
            <input type="button" value="新增" onclick="commForm(1)">
            <input type="button" value="删除" onclick="getChecked()">
        </td>
    </tr>


</table>
<table id="table1" border="0.5" width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr style="background-color: #f5f5dc;">
        <td  width="2%"><g:checkBox name="checkAll" disabled="true"></g:checkBox></td>
        <td width="36%" align="center">应用系统</td>
        <td width="26%" align="center">角色编码</td>
        <td width="36%" align="center">角色名称</td>
    </tr>
    <g:each in="${list}" var="item" status="index">

        <tr onMouseOver="over()" onClick="change()" onMouseOut="out()">
            <td width="2%" align="center"><g:checkBox name="checkRole" id="checkRole" checked="false" value="${item.roleCode}"></g:checkBox> </td>
            <td  width="36%" align="center"> ${item.functionName}</td>
            <td  width="26%" align="center">${item.roleCode} </td>
            <td  width="36%" align="center"> ${item.roleName}</td>
        </tr>
    </g:each>


</table>
    <input type="hidden" id="ckString" name="ckString" value="">
</g:form>
<script type="text/javascript">
    function commForm(id){
        var gForm = document.getElementById("gFrom");
        if(id==0){
            gForm.action = "index";
        }else if(id==1){
            gForm.action = "addRoleRight";
        }
        gForm.controller = "roleManagement"
        gForm.submit();
    }
    function getChecked (){
        var checkRole=document.getElementsByName("checkRole")
        var ckList=""
        for(var i=0;i<checkRole.length;i++){
             if(checkRole[i].checked==true){
                 ckList = ckList+checkRole[i].value+","
             }
        }
        if(ckList==""){
             alert("请选择需要删除的数据！");
        }else{
            document.getElementById("ckString").value= ckList
            var gForm = document.getElementById("gFrom");
            gForm.action = "deleteRole";
            gForm.controller = "roleManagement"
            gForm.submit();
        }


    }
</script>
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