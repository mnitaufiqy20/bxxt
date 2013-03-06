<%@ page import="com.chd.bx.security.Role" %>



<div class="fieldcontain ${hasErrors(bean: roleInstance, field: 'authority', 'error')} required">
	<label for="authority">
		<g:message code="role.authority.label" default="名称" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="authority" required="" value="${roleInstance?.authority}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: roleInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="role.description.label" default="描述" />
		
	</label>
	<g:textField name="description" value="${roleInstance?.description}"/>
</div>

