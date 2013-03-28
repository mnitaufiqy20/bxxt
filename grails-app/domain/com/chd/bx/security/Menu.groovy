package com.chd.bx.security

class Menu {
    String menuCode
    String menuRight
    String menuName //菜单名称
    int sortIndex //菜单排序索引
    String actionUrl //菜单执行URL
    int parentId     //父ID
    MenuCategory menuCategory //菜单分类

    static constraints = {
        menuName(blank: false,unique: true, maxSize: 50 )
        actionUrl(blank: false, maxSize: 200)
        sortIndex(blank: false)
        parentId nullable: true
        menuCode nullable: true ,maxSize: 20
        menuRight nullable: true ,maxSize: 20
    }

    static mapping = {
        table 'S_MENU'
    }
}
