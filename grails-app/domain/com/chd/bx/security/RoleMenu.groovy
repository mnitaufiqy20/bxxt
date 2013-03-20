package com.chd.bx.security

class RoleMenu {

    String roleId
    String roleRight
    Menu menu

    static constraints = {
        roleId blank: false
        roleRight  blank: true
    }

    static mapping = {
        table 'S_ROLE_MENU'
    }
}
