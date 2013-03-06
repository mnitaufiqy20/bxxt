<%@ page import="com.chd.bx.security.Menu" %>



<div class="fieldcontain ${hasErrors(bean: menuInstance, field: 'menuName', 'error')} required">
    <label for="menuName">
        <g:message code="menu.menuName.label" default="名称"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="menuName" maxlength="50" required="" value="${menuInstance?.menuName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: menuInstance, field: 'actionUrl', 'error')} required">
    <label for="actionUrl">
        <g:message code="menu.actionUrl.label" default="操作URL"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="actionUrl" maxlength="200" required="" value="${menuInstance?.actionUrl}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: menuInstance, field: 'sortIndex', 'error')} required">
    <label for="sortIndex">
        <g:message code="menu.sortIndex.label" default="排序索引"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="sortIndex" type="number" value="${menuInstance.sortIndex}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: menuInstance, field: 'menuCategory', 'error')} required">
    <label for="menuCategory">
        <g:message code="menu.menuCategory.label" default="分类"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="menuCategory" name="menuCategory.id" from="${com.chd.bx.security.MenuCategory.list()}" optionKey="id"
              required="" value="${menuInstance?.menuCategory?.id}" optionValue="categoryName" class="many-to-one"/>
</div>

