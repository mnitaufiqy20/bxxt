<%@ page import="com.chd.bx.security.Role" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'role.label', default: 'Role')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-role" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                           default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        %{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-role" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/>
        <g:if test="${roleInstance?.user?.size() > 0}">
            <span id="description-label" class="property-label">&nbsp;&nbsp;(提示：角色已存在所属用户无法删除)</span>
        </g:if>
    </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list role">

        <g:if test="${roleInstance?.authority}">
            <li class="fieldcontain">
                <span id="authority-label" class="property-label"><g:message code="role.authority.label"
                                                                             default="名称"/></span>

                <span class="property-value" aria-labelledby="authority-label"><g:fieldValue bean="${roleInstance}"
                                                                                             field="authority"/></span>

            </li>
        </g:if>

        <g:if test="${roleInstance?.description}">
            <li class="fieldcontain">
                <span id="description-label" class="property-label"><g:message code="role.description.label"
                                                                               default="描述"/></span>

                <span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${roleInstance}"
                                                                                               field="description"/></span>

            </li>
        </g:if>

        <g:if test="${roleInstance?.user}">
            <li class="fieldcontain">
                <span id="user-label" class="property-label"><g:message code="role.user.label" default="所属用户"/></span>

                <g:each in="${roleInstance.user}" var="u">
                    <span class="property-value" aria-labelledby="user-label">
                        <g:link controller="user" action="show"
                                id="${u.id}">${u?.username}&nbsp;(${u?.companyNo}/${u?.departmentNo}/${u?.name})</g:link>
                    </span>
                </g:each>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${roleInstance?.id}"/>
            <g:link class="edit" action="edit" id="${roleInstance?.id}"><g:message code="default.button.edit.label"
                                                                                   default="Edit"/></g:link>
            <g:if test="${!(roleInstance?.user?.size() > 0)}">
                <g:actionSubmit class="delete" action="delete"
                                value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
            </g:if>
            <g:else>

            </g:else>
        </fieldset>
    </g:form>
</div>
</body>
</html>
