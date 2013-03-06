<%@ page import="com.chd.bx.security.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="用户名" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="密码" />
		<span class="required-indicator">*</span>
	</label>
	<g:passwordField name="password" required="" value="${userInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="user.name.label" default="姓名" />
		
	</label>
	<g:textField name="name" value="${userInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'empNo', 'error')} ">
	<label for="empNo">
		<g:message code="user.empNo.label" default="工号" />
		
	</label>
	<g:textField name="empNo" value="${userInstance?.empNo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'telephone', 'error')} ">
	<label for="telephone">
		<g:message code="user.telephone.label" default="联系电话" />
		
	</label>
	<g:textField name="telephone" value="${userInstance?.telephone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'idNumber', 'error')} ">
	<label for="idNumber">
		<g:message code="user.idNumber.label" default="身份证号" />
		
	</label>
	<g:textField name="idNumber" value="${userInstance?.idNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'companyNo', 'error')} ">
	<label for="companyNo">
		<g:message code="user.companyNo.label" default="公司" />
		
	</label>
	<g:textField name="companyNo" value="${userInstance?.companyNo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'departmentNo', 'error')} ">
	<label for="departmentNo">
		<g:message code="user.departmentNo.label" default="部门" />
		
	</label>
	<g:textField name="departmentNo" value="${userInstance?.departmentNo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'empPosition', 'error')} ">
	<label for="empPosition">
		<g:message code="user.empPosition.label" default="职位" />
		
	</label>
	<g:textField name="empPosition" value="${userInstance?.empPosition}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'role', 'error')} required">
    <label for="role">
        <g:message code="user.role.label" default="系统角色" />
        <span class="required-indicator">*</span>
    </label>
    <g:select id="role" name="role.id" from="${com.chd.bx.security.Role.list()}" optionKey="id" required="" value="${userInstance?.role?.id}" optionValue="authority" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="user.enabled.label" default="用户状态" />
		
	</label>
	<g:checkBox name="enabled" value="${userInstance?.enabled}" />
</div>



