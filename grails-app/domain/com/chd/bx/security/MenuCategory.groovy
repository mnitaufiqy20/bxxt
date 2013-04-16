package com.chd.bx.security

class MenuCategory {

    String categoryName //菜单分类名称
    int sortIndex //菜单分类排序索引
    String mcCode //code
    String parentMenu    //父menu
    static hasMany = [menu:Menu]

    static constraints = {
        categoryName(blank: false,unique: true, maxSize: 50)
        sortIndex(blank: false)
        mcCode (blank: false,maxSize: 40)
        parentMenu nullable: true
    }

    static mapping = {
        table 'S_MENU_CATEGORY'
    }
}
