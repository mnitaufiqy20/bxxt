package com.chd.bx

import com.chd.bx.security.Menu
import com.chd.bx.security.MenuCategory
import com.chd.bx.security.RoleMenu
import com.chd.bx.security.User
import com.chd.bx.security.Role

class WorkbenchController {

    def springSecurityService

    def index() {
        String currentUserId = springSecurityService.getPrincipal().username;
        User currentUser = User.findByUsername(currentUserId);
        String queryString = "from RoleMenu where roleId=" + currentUser.role.id + " order by menu.menuCategory.sortIndex asc,menu.sortIndex asc";
        List menus = RoleMenu.executeQuery(queryString)

        Map<String, List<Menu>> map = new HashMap<String, List<Menu>>();
        List<String> keys=new ArrayList<String>();
        for (RoleMenu roleMenu : menus) {
            MenuCategory mc = roleMenu.menu.menuCategory;
            List<Menu> ms = map.get(mc.categoryName);
            if (ms != null) {
                ms.add(roleMenu.menu);
            } else {
                ms = new ArrayList<Menu>();
                ms.add(roleMenu.menu);
                map.put(mc.categoryName, ms);
                keys.add(mc.categoryName);
            }
        }

        List <MenuItem> menuItems = new ArrayList<MenuItem>()
        for (String key : keys) {
            MenuItem item=new MenuItem(categoryName:key,menus:map.get(key));
            menuItems.add(item);
        }
        String isAuth="1";
        Role role=currentUser.getRole();
        String    authority=role.authority;
        if (authority.equals("系统管理员")){
            isAuth="0"
        }
        render view: 'index', model: ['menuItems': menuItems,isAuth:isAuth];
    }

    class MenuItem {
        String categoryName;
        List<Menu> menus;
    }
}
