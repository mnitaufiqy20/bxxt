
<%@ page import="com.chd.bx.security.Menu" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'menu.label', default: 'Menu')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-menu" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				%{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-menu" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list menu">
			
				<g:if test="${menuInstance?.menuName}">
				<li class="fieldcontain">
					<span id="menuName-label" class="property-label"><g:message code="menu.menuName.label" default="名称" /></span>
					
						<span class="property-value" aria-labelledby="menuName-label"><g:fieldValue bean="${menuInstance}" field="menuName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${menuInstance?.actionUrl}">
				<li class="fieldcontain">
					<span id="actionUrl-label" class="property-label"><g:message code="menu.actionUrl.label" default="操作URL" /></span>
					
						<span class="property-value" aria-labelledby="actionUrl-label"><g:fieldValue bean="${menuInstance}" field="actionUrl"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${menuInstance?.sortIndex}">
				<li class="fieldcontain">
					<span id="sortIndex-label" class="property-label"><g:message code="menu.sortIndex.label" default="排序索引" /></span>
					
						<span class="property-value" aria-labelledby="sortIndex-label"><g:fieldValue bean="${menuInstance}" field="sortIndex"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${menuInstance?.menuCategory}">
				<li class="fieldcontain">
					<span id="menuCategory-label" class="property-label"><g:message code="menu.menuCategory.label" default="分类" /></span>
					
						<span class="property-value" aria-labelledby="menuCategory-label">${menuInstance?.menuCategory?.categoryName}</span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${menuInstance?.id}" />
					<g:link class="edit" action="edit" id="${menuInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
