<%--
  Created by IntelliJ IDEA.
  User: mengmin
  Date: 13-3-14
  Time: 下午6:32
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/html">
<head>
  <title>权限管理</title>
    <script src="${resource(dir: 'js', file: 'jquery-1.4.2.min.js')}"></script>
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
     <g:form  id="gFrom" name="gFrom" action="updateRights"  controller="rightsManagement" method="post">
          <table width="100%">
              <tr><td align="center" width="100%" colspan="6"><h3>系统权限管理</h3></td></tr>
              <tr>
                  <td width="20%"></td>
                  <td width="10%">角色名称：</td>
                  <td width="20%">
                      <select name="roleName" id="roleName" onchange="getRoleCode();">
                          <g:each in="${roleList}" var="item" status="index">
                              <g:if test="${roleCode==item.roleCode}">
                                  <option value="${item.roleCode}" selected="true">${item.authority}</option>
                              </g:if>
                              <g:else>
                                  <option value="${item.roleCode}">${item.authority}</option>
                              </g:else>
                          </g:each>
                      </select>
                  </td>
                  <td width="10%">角色代码：</td>
                  <td width="20%"><g:textField name="roleCode" id="roleCode" value="${roleCode}" disabled="true" style="background-color: #f0f8ff;"></g:textField></td>
                  <td width="20%"></td>
              </tr>
              <tr valign="2">
                  <td width="20%"></td>
                  <td width="10%">角色分配：</td>
                  <td width="50%" colspan="3" id="roleCc"><g:textArea name="userList" id="userList" rows="2" cols="50" value="${userGroup}" disabled="true" style="background-color: #f0f8ff;"></g:textArea></td>
                  <td width="20%"></td>
              </tr>
          </table>
          <table id="table1" width="100%"  border="0" cellpadding="0" cellspacing="0">
                <tr style="background-color: #f5f5dc;" align="center">
                    <td width="15%">&nbsp;</td>
                    <td width="15%">&nbsp;</td>
                    <td width="11%">查看/执行</td>
                    <td width="11%">新增</td>
                    <td width="12%">修改</td>
                    <td width="12%">删除</td>
                    <td width="12%">审核</td>
                    <td width="12%">被授权区域</td>
                </tr>
                <g:each in="${list}" var="item" status="index">
                      <tr align="center" onMouseOver="over()" onClick="change()" onMouseOut="out()">
                          <td width="15%">${item.menuDesc}</td>
                          <td width="15%">${item.functionName}</td>
                          <td width="11%"><g:checkBox name="rightLook" value="${item.rightLook}" checked="${item.rightLook}"></g:checkBox></td>
                          <td width="11%"><g:checkBox name="rightAdd" value="${item.rightAdd}" checked="${item.rightAdd}"></g:checkBox></td>
                          <td width="12%"><g:checkBox name="rightUpdate" value="${item.rightUpdate}" checked="${item.rightUpdate}"></g:checkBox></td>
                          <td width="12%"><g:checkBox name="rightDelete" value="${item.rightDelete}" checked="${item.rightDelete}"></g:checkBox></td>
                          <td width="12%"><g:checkBox name="rightCheck" value="${item.rightCheck}" checked="${item.rightCheck}"></g:checkBox></td>
                          <td width="12%"><g:checkBox name="rightArea" value="${item.rightArea}" checked="${item.rightArea}"></g:checkBox></td>
                          <input type="hidden" value="${item.functionCode}" name="functionCode">
                          <input type="hidden" value="${index}" name="index">
                      </tr>
                </g:each>
          </table>
         <input type="button" value="保存" name="saveRights"onclick="updateRights();">
         %{--<input type="button" value="保存" name="saveRights"onclick="getTable()">--}%
         <input type="button" value="取消" name="back" onclick="javascript:history.back(-1);">
         <input type="hidden" id="lookString" name="lookString" value="">
         <input type="hidden" id="addString" name="addString" value="">
         <input type="hidden" id="updateString" name="updateString" value="">
         <input type="hidden" id="deleteString" name="deleteString" value="">
         <input type="hidden" id="checkString" name="checkString" value="">
         <input type="hidden" id="areaString" name="areaString" value="">

     </g:form>
<script language="javascript"  type="text/javascript" >
    var xmlHttp;
    function createXMLHttp(){
        if(window.XmlHttpRequest){
            xmlHttp=new XmlHttpRequest();
        }else{
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
    }

    function getUser(roleId){
        createXMLHttp();//创建出XMLHttpRequest对象
        xmlHttp.open("post","../rightsManagement/getUser?roleId="+roleId);
        xmlHttp.setRequestHeader("Content-Type","text/html;charset=gbk");
        xmlHttp.onreadystatechange=callBack;
        xmlHttp.send(null);
    }
    function callBack(){
//        alert("xmlHttp.readyState  "+xmlHttp.readyState+ "  xmlHttp.status  "+xmlHttp.status);
        if(xmlHttp.readyState == 4){
            if(xmlHttp.status ==200){
                var text=xmlHttp.responseText;
                document.getElementById("userList").value = text;
            }
        }
    }
    function getRoleCode(){
        var roleSel= document.getElementById("roleName") ;
        var index=roleSel.selectedIndex   ;
        var value = roleSel.options[index].value ;
        var text = roleSel.options[index].text;
//        document.getElementById("roleCode").value = value;
//        getUser(value);
        var gForm = document.getElementById("gFrom");
        gForm.action = "getEdit";
        gForm.controller = "rightsManagement"
        gForm.submit();
    }
    function updateRights(){
        alert(document.getElementById("roleCode").value);
        var checkLook=document.getElementsByName("rightLook");
        var lookList="";
        for(var i=0;i<checkLook.length;i++){
            if(checkLook[i].checked==true){
                lookList = lookList+"true"+"," ;
            }else{
                lookList = lookList+"false"+"," ;
            }
        }
        var checkAdd=document.getElementsByName("rightAdd");
        var addList="";
        for(var i=0;i<checkAdd.length;i++){
            if(checkAdd[i].checked==true){
                addList = addList+"true"+"," ;
            }else{
                addList = addList+"false"+"," ;
            }
        }
        var checkUpdate=document.getElementsByName("rightUpdate") ;
        var updateList="" ;
        for(var i=0;i<checkUpdate.length;i++){
            if(checkUpdate[i].checked==true){
                updateList = updateList+"true"+"," ;
            }else{
                updateList = updateList+"false"+"," ;
            }
        }
        var checkDelete=document.getElementsByName("rightDelete") ;
        var deleteList="";
        for(var i=0;i<checkDelete.length;i++){
            if(checkDelete[i].checked==true){
                deleteList = deleteList+"true"+"," ;
            }else{
                deleteList = deleteList+"false"+"," ;
            }
        }
        var checkC=document.getElementsByName("rightCheck") ;
        var checkList="";
        for(var i=0;i<checkC.length;i++){
            if(checkC[i].checked==true){
                checkList = checkList+"true"+"," ;
            }else{
                checkList = checkList+"false"+"," ;
            }
        }
        var checkArea=document.getElementsByName("rightArea");
        var areaList="" ;
        for(var i=0;i<checkArea.length;i++){
            if(checkArea[i].checked==true){
                areaList = areaList+"true"+"," ;
            }else{
                areaList = areaList+"false"+"," ;
            }
        }
        document.getElementById("lookString").value= lookList;
        document.getElementById("addString").value= addList ;
        document.getElementById("updateString").value= updateList ;
        document.getElementById("deleteString").value= deleteList ;
        document.getElementById("checkString").value= checkList ;
        document.getElementById("areaString").value= areaList;
        var gForm = document.getElementById("gFrom");
        gForm.action = "updateRights";
        gForm.controller = "rightsManagement"
        gForm.submit();
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