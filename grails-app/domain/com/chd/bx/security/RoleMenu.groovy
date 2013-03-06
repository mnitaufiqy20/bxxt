package com.chd.bx.security

class RoleMenu {

    Long roleId
    Menu menu

    static constraints = {
        roleId blank: false
    }

    static mapping = {
        table 'S_ROLE_MENU'
    }
}
