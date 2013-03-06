package com.chd.bx.security

class MenuCategory {

    String categoryName //菜单分类名称
    int sortIndex //菜单分类排序索引
    static hasMany = [menu:Menu]

    static constraints = {
        categoryName(blank: false,unique: true, maxSize: 50)
        sortIndex(blank: false)
    }

    static mapping = {
        table 'S_MENU_CATEGORY'
    }
}
