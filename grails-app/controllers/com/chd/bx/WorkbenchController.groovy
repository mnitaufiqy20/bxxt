package com.chd.bx

import com.chd.bx.security.Menu
import com.chd.bx.security.MenuCategory
import com.chd.bx.security.RoleMenu
import com.chd.bx.security.User
import com.chd.bx.security.UserRole
import com.chd.bx.security.Role

class WorkbenchController {

    def springSecurityService

    def index() {
//        String currentUserId = springSecurityService.getPrincipal().username;
//        User currentUser = User.findByUsername(currentUserId);
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        def userRoleList = UserRole.findAllByUser(user)
        def role = new Role()
        def role2 = new Role()
        for (UserRole userRole:userRoleList){
            def role1 = new Role()
            role1 = userRole.role
            def str = role1.description.substring(0,1)
            if (!role1.description.equals("PT") && !role1.description.equals("KJ")
                    && !role1.description.equals("CN")) {
                role = role1
                break;
            }
        }
//        for (UserRole userRole:userRoleList){
//            def role1 = new Role()
//            role1 = userRole.role
//            def str = role1.description.substring(0,1)
//            if (!role1.description.equals("PT") && !role1.description.equals("KJ")
//                    && !role1.description.equals("CN") && str.equals("B")) {
//                role = role1
//                break;
//            }
//        }
//        for (UserRole userRole:userRoleList){
//            def role1 = new Role()
//            role1 = userRole.role
//            def str = role1.description.substring(0,1)
//            if (!role1.description.equals("PT") && !role1.description.equals("KJ")
//                    && !role1.description.equals("CN") && str.equals("J")) {
//                role2 = role1
//                break;
//            }
//        }
//        String queryString = "from RoleMenu where roleId=" + currentUser.role.id + " and roleRight is not null order by menu.menuCategory.sortIndex asc,menu.sortIndex asc";
//        String queryString = "select distinct * from RoleMenu where roleId=" + role.id + " or roleId=" + role2.id + "and roleRight is not null order by menu.menuCategory.sortIndex asc,menu.sortIndex asc";
        String queryString = "from RoleMenu where roleId=" + role.id + " and roleRight is not null order by menu.menuCategory.sortIndex asc,menu.sortIndex asc";
//        String queryString2 = "from RoleMenu where roleId=" + role.id + " and roleRight is not null order by menu.menuCategory.sortIndex asc,menu.sortIndex asc";
        List menus = RoleMenu.executeQuery(queryString)
//        Set set = new HashSet();
//        set.addAll(menus2);
//        List menus = new ArrayList();
//        menus.addAll(set);
//        def s = menus.size()
//        List menus2 = RoleMenu.executeQuery(queryString2)
//        List menus3
//        for (RoleMenu rM:menus){
//            for (RoleMenu rM2:menus2){
//               if (rM.menuId==rM2.menuId){
//                   continue
//               }
//                menus3.add(rM2)
//            }
//        }
//
//        for (RoleMenu rM:menus3){
//            menus.add(rM)
//        }
//        def menuSecond = new ArrayList<RoleMenu>()
//        def menuThird = new ArrayList<RoleMenu>()
//        def menuForth = new ArrayList<RoleMenu>()
//        for (RoleMenu men:menus){
//            if (men.menu.parentId==0){
//                menuSecond.add(men)
//            }else {
//                def m = Menu.findById(men.menu.parentId)
//                if (m.parentId==0){
//                    menuThird.add(men)
//                }else{
//                    menuForth.add(men)
//                }
//            }
//        }
//
//        Map<String, Map> map = new HashMap<String, Map>();
//        List<String> keys=new ArrayList<String>();
//
//        for (RoleMenu roleMenu1 : menus) {
//            Menu m2=roleMenu1.menu;
//            String menuString= "from Menu where parentId=" + m2.id;
//            List mu=Menu.executeQuery(menuString);
//            if (m2.id==21){
//                System.out.print("mu:"+mu)
//            } else {
//                System.out.print("gg")
//            }
//
//            roleMenu1.menu.list=mu
//
//            System.out.print("mu:"+mu.list)
//        }
//
//        for (RoleMenu roleMenu : menus) {
//            MenuCategory mc = roleMenu.menu.menuCategory;
//            String categoryName=mc.categoryName;
//            if (categoryName.equals("测试用例")){
//                //证明当前 MenuCategory 对应的节点为 某一节点的子节点
//                System.out.print("121");
//            }else {
//                List<Menu> ms = map.get(mc.categoryName);
//                if (ms != null) {
//                    ms.add(roleMenu.menu);
//                } else {
//                    ms = new ArrayList<Menu>();
//                    ms.add(roleMenu.menu);
//                    map.put(mc.categoryName, ms);
//                    keys.add(mc.categoryName);
//                }
//             }
//
//
//            }

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
//        String isAuth="1";
//        Role role=currentUser.getRole();
//        String    authority=role.authority;
//        if (authority.equals("系统管理员")){
            String isAuth="0"
//        }
        render view: 'index', model: ['menuItems': menuItems,isAuth:isAuth];
    }

    class MenuItem {
        String categoryName;
        List<Menu> menus;
    }


}
