
<%@ page import="com.chd.bx.security.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				%{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-user" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list user">
			
				<g:if test="${userInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="user.username.label" default="用户名" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${userInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="user.name.label" default="姓名" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${userInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.empNo}">
				<li class="fieldcontain">
					<span id="empNo-label" class="property-label"><g:message code="user.empNo.label" default="工号" /></span>
					
						<span class="property-value" aria-labelledby="empNo-label"><g:fieldValue bean="${userInstance}" field="empNo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.telephone}">
				<li class="fieldcontain">
					<span id="telephone-label" class="property-label"><g:message code="user.telephone.label" default="联系电话" /></span>
					
						<span class="property-value" aria-labelledby="telephone-label"><g:fieldValue bean="${userInstance}" field="telephone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.idNumber}">
				<li class="fieldcontain">
					<span id="idNumber-label" class="property-label"><g:message code="user.idNumber.label" default="身份证号" /></span>
					
						<span class="property-value" aria-labelledby="idNumber-label"><g:fieldValue bean="${userInstance}" field="idNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.companyNo}">
				<li class="fieldcontain">
					<span id="companyNo-label" class="property-label"><g:message code="user.companyNo.label" default="公司" /></span>
					
						<span class="property-value" aria-labelledby="companyNo-label"><g:fieldValue bean="${userInstance}" field="companyNo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.departmentNo}">
				<li class="fieldcontain">
					<span id="departmentNo-label" class="property-label"><g:message code="user.departmentNo.label" default="部门" /></span>
					
						<span class="property-value" aria-labelledby="departmentNo-label"><g:fieldValue bean="${userInstance}" field="departmentNo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.empPosition}">
				<li class="fieldcontain">
					<span id="empPosition-label" class="property-label"><g:message code="user.empPosition.label" default="职位" /></span>
					
						<span class="property-value" aria-labelledby="empPosition-label"><g:fieldValue bean="${userInstance}" field="empPosition"/></span>
					
				</li>
				</g:if>

                <g:if test="${userInstance?.role}">
                    <li class="fieldcontain">
                        <span id="role-label" class="property-label"><g:message code="user.role.label" default="系统角色" /></span>

                        <span class="property-value" aria-labelledby="role-label"><g:link controller="role" action="show" id="${userInstance?.role?.id}">${userInstance?.role?.authority}</g:link></span>

                    </li>
                </g:if>

				<g:if test="${userInstance?.enabled}">
				<li class="fieldcontain">
					<span id="enabled-label" class="property-label"><g:message code="user.enabled.label" default="用户状态" /></span>
					
						<span class="property-value" aria-labelledby="enabled-label"><g:formatBoolean boolean="${userInstance?.enabled}" /></span>
					
				</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${userInstance?.id}" />
					<g:link class="edit" action="edit" id="${userInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
