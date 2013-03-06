<%@ page import="com.chd.bx.security.RoleMenu" %>

<g:hiddenField name="roleId" required="" value="${roleMenuInstance?.roleId}"/>

<div class="fieldcontain ${hasErrors(bean: roleMenuInstance, field: 'menu', 'error')} required">
	<label for="menu">
		<g:message code="roleMenu.menu.label" default="Menu" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="menu" name="menu.id" from="${com.chd.bx.security.Menu.list()}" optionKey="id" required="" value="${roleMenuInstance?.menu?.id}" optionValue="menuName" class="many-to-one"/>
</div>

