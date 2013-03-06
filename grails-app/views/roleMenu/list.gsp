
<%@ page import="com.chd.bx.security.RoleMenu" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'roleMenu.label', default: 'RoleMenu')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-roleMenu" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				%{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
                <li><g:link class="home" controller="role" action="index">角色管理</g:link></li>
				<li><g:link class="create" action="create" params="[roleId: params.roleId]">分配新菜单</g:link></li>
			</ul>
		</div>
		<div id="list-roleMenu" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /> - ${params.authority}</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<th><g:message code="roleMenu.menu.label" default="菜单名称" /></th>
                        <th><g:message code="roleMenu.menu.label" default="菜单分类" /></th>
                        <th><g:message code="roleMenu.menu.label" default="操作" /></th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${roleMenuInstanceList}" status="i" var="roleMenuInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${roleMenuInstance?.menu?.menuName}</td>
                        <td>${roleMenuInstance?.menu?.menuCategory?.categoryName}</td>
                        <td><g:link controller="roleMenu" action="delete" params="[roleId: params.roleId]" id="${roleMenuInstance?.id}">删除</g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${roleMenuInstanceTotal}" params="[roleId: params.roleId]" />
			</div>
		</div>
	</body>
</html>
