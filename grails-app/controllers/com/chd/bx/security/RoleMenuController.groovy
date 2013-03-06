package com.chd.bx.security

import org.springframework.dao.DataIntegrityViolationException

class RoleMenuController {

    static allowedMethods = [save: "POST", update: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def queryParams = [max: params.max]
        if (params.offset) {
            queryParams["offset"] = params.offset
        }
        if (params.roleId) {
            params["authority"] = Role.get(params.roleId).authority
        }
        String queryString = "from RoleMenu where roleId="+params.roleId+" order by menu.menuCategory.sortIndex asc,menu.sortIndex asc"
        [roleMenuInstanceList: RoleMenu.executeQuery(queryString, queryParams),
                roleMenuInstanceTotal: RoleMenu.executeQuery(queryString).size()]
    }

    def create() {
        [roleMenuInstance: new RoleMenu(params)]
    }

    def save() {
        def roleMenuInstance = new RoleMenu(params)
        if (!roleMenuInstance.save(flush: true)) {
            render(view: "create", model: [roleMenuInstance: roleMenuInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'roleMenu.label', default: 'RoleMenu'), roleMenuInstance.id])
//        redirect(action: "show", id: roleMenuInstance.id)
        redirect(action: "list", params: params)
    }

    def delete(Long id) {
        def roleMenuInstance = RoleMenu.get(id)
        if (!roleMenuInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'roleMenu.label', default: 'RoleMenu'), id])
            redirect(action: "list", params: params)
            return
        }

        try {
            roleMenuInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'roleMenu.label', default: 'RoleMenu'), id])
            redirect(action: "list", params: params)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'roleMenu.label', default: 'RoleMenu'), id])
//            redirect(action: "show", id: id)
            redirect(action: "list", params: params)
        }
    }
}
