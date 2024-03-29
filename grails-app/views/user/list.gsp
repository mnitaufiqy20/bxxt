
<%@ page import="com.chd.bx.security.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				%{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-user" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="username" title="${message(code: 'user.username.label', default: '用户名')}" />
						<g:sortableColumn property="name" title="${message(code: 'user.name.label', default: '姓名')}" />
						<g:sortableColumn property="companyNo" title="${message(code: 'user.idNumber.label', default: '公司')}" />
                        <g:sortableColumn property="departmentNo" title="${message(code: 'user.idNumber.label', default: '部门')}" />
                        <g:sortableColumn property="empPosition" title="${message(code: 'user.idNumber.label', default: '职位')}" />
                        <g:sortableColumn property="role" title="${message(code: 'user.idNumber.label', default: '系统角色')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${userInstanceList}" status="i" var="userInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "username")}</g:link></td>
						<td>${fieldValue(bean: userInstance, field: "name")}</td>
						<td>${fieldValue(bean: userInstance, field: "companyNo")}</td>
						<td>${fieldValue(bean: userInstance, field: "departmentNo")}</td>
						<td>${fieldValue(bean: userInstance, field: "empPosition")}</td>
                        <td>${userInstance?.role?.authority}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
