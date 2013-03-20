<%--
  Created by IntelliJ IDEA.
  User: MengMin
  Date: 13-1-31
  Time: 上午11:13
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>组织架构</title>
</head>
<body>
     <table>
         <tr>
             <td colspan="3" ></td>
             <td colspan="5" height="30">
                 <div style="width: 300px;background: #ADCDF4;"><h3>本单位的基本信息如下</h3></div>
             </td>
         </tr>
         <tr>
             <td colspan="6" ></td>
             <td>组织代码：</td>
             <td>${orgCode}</td>
         </tr>
         <tr>
             <td colspan="6" ></td>
             <td>组织名称：</td>
             <td>${orgName}</td>
         </tr>
         <tr>
             <td colspan="8" >&nbsp;</td>
         </tr>

                <g:each in="${list}" var="item" status="index">
                     <tr>
                         <td colspan="6" ></td>
                         <g:if test="${index==0}">
                             <td>部门：</td>
                         </g:if>
                         <g:else>
                             <td> </td>
                         </g:else>

                         <td>${item.deptName}</td>
                     </tr>
                </g:each>

     </table>
</body>
</html>