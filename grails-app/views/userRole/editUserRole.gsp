<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-3-21
  Time: 下午4:31
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>用户角色配置</title>
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
<g:form  id="gFrom" name="gFrom" action="getUser"  controller="userRole" method="post">
      <table  width="100%">
             <tr><td align="center" colspan="4" width="100%"><h3>用户角色配置</h3></td></tr>
            <tr>
                <td width="10%">角色：<input type="hidden" name="funcCode" value="${funcCode}"></td>
                <td width="30%">${roleName}</td>
                <td width="45%">&nbsp;</td>
                <td width="15%" align="left">
                    <g:if test="${a =="N"}">
                        <input type="button" value="添加员工" onclick="commForm(0)">
                    </g:if>
                    <g:else>
                        <input type="button" value="添加员工" disabled>
                    </g:else>
                    <input type="button" value="返回" onclick="commForm(1);">
                </td>
            </tr>
      </table>
       <table  id="table1"  width="100%"  border="0" cellpadding="0" cellspacing="0">
            <tr style="background-color: #f5f5dc;" align="center">
                <td width="12%">序号</td>
                <td width="26%">角色</td>
                <td width="25%">员工编号</td>
                <td width="25%">员工登录名</td>
                <td width="12%">操作</td>
            </tr>
           <g:each in="${list}" var="item" status="index">
               <tr  align="center" onMouseOver="over()" onClick="change()" onMouseOut="out()">
                   <td width="12%">${index+1}</td>
                   <td width="26%">${item.role.authority}</td>
                   <td width="25%">${item.user.empNo}</td>
                   <td width="25%">${item.user.username}</td>
                   <td width="12%">
                       <g:if test="${b =="D"}">
                           <a href="../userRole/deleteUserRole?userId=${item.user.id}&roleId=${item.role.id}&roleName=${item.role.authority}&funcCode=${funcCode}">删除</a>
                       </g:if>
                       <g:else>
                           <a href="#">删除</a>
                       </g:else>
                   </td>
               </tr>
           </g:each>

       </table>
    <input type="hidden" name="roleId" id="roleId" value="${roleId}">
    <input type="hidden" name="roleName" id="roleName" value="${roleName}">
</g:form>
<SCRIPT language="javascript">
    function commForm(id){
        var gForm = document.getElementById("gFrom");
        if(id==0){
            gForm.action = "getUser";
        }else if(id==1){
            gForm.action = "index";
        }else if(id==2){
            gForm.action = "addRoleRight";
        }
        gForm.controller = "userRole"
        gForm.submit();
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
</body>
</html>