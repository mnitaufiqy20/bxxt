<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-3-13
  Time: 下午4:24
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>新增角色</title>
</head>
<body>
<g:form name="addRoleForm" id="addRoleForm" controller="roleManagement" action="addRole" method="post">
      <div>
          <table width="1000">
              <tr ><td align="center"><h3>新增角色</h3></td></tr>
              <tr align="center">
                  <td>角色编码：<g:textField name="roleCode" value="" style="width: 130px;" maxlength="30"/></td>
              </tr>
              <tr align="center">
                    <td>角色名称：<g:textField name="roleName" value="" style="width: 130px;" maxlength="30"/></td>
              </tr>
              <tr align="center">
                  <td><input type="button" value="保存" onclick="commForm(0)"> <input type="button" value="返回" onclick="javascript:history.back(-1);"></td>

              </tr>
          </table>
      </div>
</g:form>
<script type="text/javascript">
    function commForm(id){
        var gForm = document.getElementById("addRoleForm");
        if(id==0){
            gForm.action = "addRole";
        }else if(id==1){
//            gForm.action = "addRoleRight";
        }
        gForm.controller = "roleManagement"
        gForm.submit();
    }
</script>
</body>
</html>