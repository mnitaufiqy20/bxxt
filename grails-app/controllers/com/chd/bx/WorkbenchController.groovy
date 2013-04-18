package com.chd.bx

import com.chd.bx.security.Menu
import com.chd.bx.security.MenuCategory
import com.chd.bx.security.RoleMenu
import com.chd.bx.security.User
import com.chd.bx.security.UserRole
import com.chd.bx.security.Role
import menu.TreeNode
import org.codehaus.groovy.grails.web.context.ServletContextHolder

class WorkbenchController {

    def springSecurityService
    protected TreeNode root;
    def index() {
//        String currentUserId = springSecurityService.getPrincipal().username;
//        User currentUser = User.findByUsername(currentUserId);
        String currentUserName = springSecurityService.getPrincipal().username;
        def user = User.findByUsername(currentUserName)
        def userRoleList = UserRole.findAllByUser(user)
        def role = new Role()
        def role2 = new Role()
//        for (UserRole userRole:userRoleList){
//            def role1 = new Role()
//            role1 = userRole.role
//            def str = role1.description.substring(0,1)
//            if (!role1.description.equals("PT") && !role1.description.equals("KJ")
//                    && !role1.description.equals("CN")) {
//                role = role1
//                break;
//            }
//        }
        for (UserRole userRole:userRoleList){
            def role1 = new Role()
            role1 = userRole.role
            def str = role1.description.substring(0,1)
            if (!role1.description.equals("PT") && !role1.description.equals("KJ")
                    && !role1.description.equals("CN") && str.equals("B")) {
                role = role1
                break;
            }
        }
        for (UserRole userRole:userRoleList){
            def role1 = new Role()
            role1 = userRole.role
            def str = role1.description.substring(0,1)
            if (!role1.description.equals("PT") && !role1.description.equals("KJ")
                    && !role1.description.equals("CN") && str.equals("J")) {
                role2 = role1
                break;
            }
        }
//        String queryString = "from RoleMenu where roleId=" + currentUser.role.id + " and roleRight is not null order by menu.menuCategory.sortIndex asc,menu.sortIndex asc";
//        String queryString = "select distinct * from RoleMenu where roleId=" + role.id + " or roleId=" + role2.id + "and roleRight is not null order by menu.menuCategory.sortIndex asc,menu.sortIndex asc";
        String queryString = "from RoleMenu where roleId=" + role.id + " or roleId=" + role2.id + " and roleRight is not null order by menu.menuCategory.sortIndex asc,menu.sortIndex asc";
//        String queryString2 = "from RoleMenu where roleId=" + role.id + " and roleRight is not null order by menu.menuCategory.sortIndex asc,menu.sortIndex asc";
        List menus = RoleMenu.executeQuery(queryString)
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

        Map<String, Map<String,List<Menu>>> map2 = new HashMap<String, Map<String,List<Menu>>>();
        Map<String, List<Menu>> map = new HashMap<String, List<Menu>>();
        String strMenu = ""
        List<String> keys=new ArrayList<String>();
        for (RoleMenu roleMenu : menus) {
            MenuCategory mc = roleMenu.menu.menuCategory;
            def str = strMenu.indexOf(roleMenu.menu.menuName)
            if (str==-1) {
                strMenu += (roleMenu.menu.menuName+",")
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
        }

//        Map<String, TreeNode> result = new HashMap<String, TreeNode>();
//        String strMenu2 = ""
//        String strPMC = ""
//        for(RoleMenu roleMenu : menus) {
//            MenuCategory mc = roleMenu.menu.menuCategory;
//            def str = strMenu2.indexOf(roleMenu.menu.menuName)
//            if (str==-1) {
//                strMenu2 += (roleMenu.menu.menuName+",")
//                TreeNode node = new TreeNode();
//                node.setId(roleMenu.menu.menuCode);
//                node.setName(roleMenu.menu.menuName);
//                def p = ""
//                if(roleMenu.menu.parentId==0){
//                    p = MenuCategory.findById(mc.id).mcCode
//                }else{
//                    p = Menu.findById(roleMenu.menu.parentId).menuCode
//                }
//                node.setPid(p);
//                node.setDisSeq(roleMenu.menu.sortIndex);
//                def url = roleMenu.menu.actionUrl
//                if (!url.equals("#")) {
//                    node.setAction(url);
//                }
//                result.put(node.getId(), node);
//                if(roleMenu.menu.parentId==0){
//                    def strP = strPMC.indexOf(mc.categoryName)
//                    if (strP==-1) {
//                        strPMC += (mc.categoryName+",")
//                        TreeNode node2 = new TreeNode();
//                        node2.setId(mc.mcCode);
//                        node2.setName(mc.categoryName);
//                        node2.setPid(mc.parentMenu==null?"":mc.parentMenu);
//                        node2.setDisSeq(mc.sortIndex);
//                        result.put(node2.getId(), node2);
//                    }
//                }
//            }
//        }
//        def menuCategory = MenuCategory.findById(1)
//        TreeNode node = new TreeNode();
//        node.setId(menuCategory.mcCode);
//        node.setName(menuCategory.categoryName);
//        node.setPid(menuCategory.parentMenu==null?"":menuCategory.parentMenu);
//        node.setDisSeq(menuCategory.sortIndex);
//        result.put(node.getId(), node);

//        def menuCList = new ArrayList<MenuCategory>();
//        menuCList = MenuCategory.findAll();
//        for (MenuCategory menuCategory:menuCList){
//            TreeNode node = new TreeNode();
//            node.setId(menuCategory.mcCode);
//            node.setName(menuCategory.categoryName);
//            node.setPid(menuCategory.parentMenu==null?"":menuCategory.parentMenu);
//            node.setDisSeq(menuCategory.sortIndex);
//            result.put(node.getId(), node);
//        }

        //遍历Map，加入parent关系
//        Set<Map.Entry<String, TreeNode>> params = result.entrySet();
//        for(Map.Entry<String, TreeNode> entry : params){
//            TreeNode temp = entry.getValue();
//            //如果当前节点的PID为空，表示该节点为root节点
//            if (temp.getPid() == null || temp.getPid().equals(""))
//                root = temp;
//            //给当前节点的父节点属性赋值
//            temp.setParent(result.get(temp.getPid()));
//        }


//        return result.toString();
        List <MenuItem> menuItems = new ArrayList<MenuItem>()
        for (String key : keys) {
            MenuItem item=new MenuItem(categoryName:key,menus:map.get(key));
//            MenuItem item=new MenuItem(categoryName:key,menus:map2.get(key));
            menuItems.add(item);
        }
//        String isAuth="1";
//        Role role=currentUser.getRole();
//        String    authority=role.authority;
//        if (authority.equals("系统管理员")){
            String isAuth="0"
//        }
//        render view: 'index', model: ['menuItems': menuItems,isAuth:isAuth];
        render view: 'index', model: ['menuItems': menuItems,isAuth:isAuth];
//        render view: '/loanAppReceipts/loan';
    }

    def tree(){
        TreeMap<String, TreeNode> nodeMap = getNodeMap();
        //加入child关系
        setChildRelation(nodeMap);
        //开始生成dTree的string
        StringBuffer result = new StringBuffer();
        result.append("[\n");
        generateTree(root,result);
        result.append("]");
        String treeStr = result.toString();
        println(treeStr)
        render(view: "index", text:treeStr,contentType:'text/plaintext',encoding:"UTF-8");
    }

    /**
     *          根据节点循环，生成javascript代码
     * @param node
     *          当前节点
     * @param result
     *          StringBuffer变量，在此变量中增加javascript代码
     */
    protected void generateTree(TreeNode node,StringBuffer result){
        if (!node)
            return ;
        if(isRoot(node)){
            result.append("{\"text\":\""+node.getName()+"\",\"children\":[");
        }else if(node.isLeaf()){
            if (node.isLastChild())
                result.append("{\"url\":\""
                        +ServletContextHolder.getServletContext().getContextPath()+"/"
                        + node.getAction() + "?funcCode=" + node.getId() + "\",\"text\":\""+ node.getName()  +"\"}\n");
            else
                result.append("{\"url\":\""
                        +ServletContextHolder.getServletContext().getContextPath()+"/"
                        + node.getAction() + "?funcCode=" + node.getId() + "\",\"text\":\""+ node.getName()  +"\"},\n");
        }else{
            result.append("{\"text\":\""+node.getName()+"\",\"children\":[");
        }
        if(node.getChildList() != null){
            for(TreeNode childNode : node.getChildList()){
                generateTree(childNode,result);
            }
            if (isRoot(node))
                result.append("]}\n");
            else{
                if ( node.isLastChild()){
                    result.append("]}\n");
                }else{
                    result.append("]},\n")
                }

            }
        }
    }

    /**
     * 判断当前节点是否是根节点
     * @param node
     *  当前节点
     * @return
     *  boolean量，是根节点则返回true
     */
    private boolean isRoot(TreeNode node){
        boolean isRoot  = false;
        if (node.getPid()==null || node.getPid().equals(""))
            isRoot = true;
        return isRoot;
    }


    /**
     *      根据用户获取所有菜单节点，这些节点已经加入了Parent关系，未加入Child关系
     * @return
     *          返回一个Map的列表
     */
    protected TreeMap<String, TreeNode> getNodeMap(){
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
                    && !role1.description.equals("CN") && str.equals("B")) {
                role = role1
                break;
            }
        }
        for (UserRole userRole:userRoleList){
            def role1 = new Role()
            role1 = userRole.role
            def str = role1.description.substring(0,1)
            if (!role1.description.equals("PT") && !role1.description.equals("KJ")
                    && !role1.description.equals("CN") && str.equals("J")) {
                role2 = role1
                break;
            }
        }
        String queryString = "from RoleMenu where roleId=" + role.id + " or roleId=" + role2.id + " and roleRight is not null order by menu.menuCategory.sortIndex asc,menu.sortIndex asc";
        List menus = RoleMenu.executeQuery(queryString)

        Map<String, TreeNode> result = new HashMap<String, TreeNode>();
        String strMenu2 = ""
        String strPMC = ""
        for(RoleMenu roleMenu : menus) {
            MenuCategory mc = roleMenu.menu.menuCategory;
            def str = strMenu2.indexOf(roleMenu.menu.menuName)
            if (str==-1) {
                strMenu2 += (roleMenu.menu.menuName+",")
                TreeNode node = new TreeNode();
                node.setId(roleMenu.menu.menuCode);
                node.setName(roleMenu.menu.menuName);
                def p = ""
                if(roleMenu.menu.parentId==0){
                    p = MenuCategory.findById(mc.id).mcCode
                }else{
                    p = Menu.findById(roleMenu.menu.parentId).menuCode
                }
                node.setPid(p);
                node.setDisSeq(roleMenu.menu.sortIndex);
                def url = roleMenu.menu.actionUrl
                if (!url.equals("#")) {
                    node.setAction(url);
                }
                result.put(node.getId(), node);
                if(roleMenu.menu.parentId==0){
                    def strP = strPMC.indexOf(mc.categoryName)
                    if (strP==-1) {
                        strPMC += (mc.categoryName+",")
                        TreeNode node2 = new TreeNode();
                        node2.setId(mc.mcCode);
                        node2.setName(mc.categoryName);
                        node2.setPid(mc.parentMenu==null?"":mc.parentMenu);
                        node2.setDisSeq(mc.sortIndex);
                        result.put(node2.getId(), node2);
                    }
                }
            }
        }
        def menuCategory = MenuCategory.findById(1)
        TreeNode node = new TreeNode();
        node.setId(menuCategory.mcCode);
        node.setName(menuCategory.categoryName);
        node.setPid(menuCategory.parentMenu==null?"":menuCategory.parentMenu);
        node.setDisSeq(menuCategory.sortIndex);
        result.put(node.getId(), node);

//        def menuCList = new ArrayList<MenuCategory>();
//        menuCList = MenuCategory.findAll();
//        for (MenuCategory menuCategory:menuCList){
//            TreeNode node = new TreeNode();
//            node.setId(menuCategory.mcCode);
//            node.setName(menuCategory.categoryName);
//            node.setPid(menuCategory.parentMenu==null?"":menuCategory.parentMenu);
//            node.setDisSeq(menuCategory.sortIndex);
//            result.put(node.getId(), node);
//        }

        //遍历Map，加入parent关系
        Set<Map.Entry<String, TreeNode>> params = result.entrySet();
        for(Map.Entry<String, TreeNode> entry : params){
            TreeNode temp = entry.getValue();
            //如果当前节点的PID为空，表示该节点为root节点
            if (temp.getPid() == null || temp.getPid().equals(""))
                root = temp;
            //给当前节点的父节点属性赋值
            temp.setParent(result.get(temp.getPid()));
        }
        return result;
    }

    /**
     * 为菜单节点加入Child关系
     * @param nodeMap
     */
    protected void setChildRelation(Map<String, TreeNode> nodeMap){
        //循环所有节点，为每个节点加入child关系
        Set<Map.Entry<String, TreeNode>> params = nodeMap.entrySet();
        for(Map.Entry<String, TreeNode> entry : params){
            //取得当前节点的Node
            TreeNode temp = entry.getValue();
            //取得当前节点的父节点
            TreeNode parent = temp.getParent();
            //如果父节点不为空，则设置父节点的子节点=当前节点（添加child关系）
            if(parent != null){
                parent.addChildNode(temp);
            }
        }
        setFirstNodesProperties(nodeMap);
        //调用排序接口，实现排序
        MyComparator comparator = new MyComparator();
        for(Map.Entry<String, TreeNode> entry : params){
            TreeNode temp = entry.getValue();
            if(temp.getChildList() != null){
                Collections.sort(temp.getChildList(), comparator);
            }
        }
    }

    /**
     * 设置第一级节点属性
     * @param nodeMap
     */
    protected void setFirstNodesProperties(Map<String, TreeNode> nodeMap){
        //设置第一级节点的属性
        if(this.root != null && this.root.getChildList() != null){
            for(TreeNode node : this.root.getChildList()){
                node.setTitleNode(true);
            }
        }
    }

    // 内部类排序
    static class MyComparator implements Comparator {
        public int compare(Object arg0, Object arg1) {
            TreeNode node0 = (TreeNode) arg0;
            TreeNode node1 = (TreeNode) arg1;
            //根据disSeq属性实现排序
            if(node0.getDisSeq() > node1.getDisSeq())
                return 1;
            else
                return 0;
        }
    }

    class MenuItem {
        String categoryName;
        List<Menu> menus;
//        Map<String,List<Menu>> menus;
    }


}
