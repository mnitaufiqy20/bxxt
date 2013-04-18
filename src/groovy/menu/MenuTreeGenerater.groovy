package menu

import java.sql.Statement
import java.sql.ResultSet
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.web.context.ServletContextHolder
import jbpm.SpringUtil

/**
 * Created with IntelliJ IDEA.
 * User: 杨辉--根据用户权限处理菜单树
 * Date: 12-12-11
 * Time: 下午8:19
 * To change this template use File | Settings | File Templates.
 */
class MenuTreeGenerater extends TreeGenerater {
    //取得log
    private static final log = LogFactory.getLog(this);
    //树
    private String userId;

    /**
     * 根据userId取得用户的对应菜单，组成dTree的javascript代码
     * @param userId
     *         用户登录名
     * @return
     *         dTree的javascript代码
     */
    public String getTreeString(String userid){
        this.userId = userid;
        Map<String, TreeNode> nodeMap = getNodeMap();
        //加入child关系
        setChildRelation(nodeMap);
        //开始生成dTree的string
        StringBuffer result = new StringBuffer();
        result.append("[\n");
        generateTree(root,result);
        result.append("]");
        return result.toString();
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
                result.append("{\"url\":\""+ServletContextHolder.getServletContext().getContextPath()+"/"
                        + node.getAction() + "?funcCode=" + node.getId() + "\",\"text\":\""+ node.getName()  +"\"}\n");
            else
                result.append("{\"url\":\""+ServletContextHolder.getServletContext().getContextPath()+"/"
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
     *      根据用户ID获取所有菜单节点，这些节点已经加入了Parent关系，未加入Child关系
     * @return
     *          返回一个Map的列表
     */
    protected Map<String, TreeNode> getNodeMap(){
        Map<String, TreeNode> result = new HashMap<String, TreeNode>();
        def conn = null;
        try
        {
            //取得数据库连接
            org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy ds = SpringUtil.getBean("dataSource");
            conn = ds.getConnection()
            //先取得当前用户拥有权限的function
            String sql="select distinct FUNCTION_CODE,FUNCTION_NAME,MENU_CODE,ORDER_SEQ,FUNCTION_URL from v_user_function where (user_id='"+ userId + "' or role_code='S002001') order by order_seq" ;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                TreeNode node = new TreeNode();
                node.setId( rs.getString("FUNCTION_CODE") );
                node.setName( rs.getString("FUNCTION_NAME") );
                node.setPid( rs.getString("MENU_CODE") );
                node.setDisSeq( rs.getInt("ORDER_SEQ") );
                node.setAction( rs.getString("FUNCTION_URL"));
                result.put(node.getId(), node);
            }
            rs.close()
            //然后取得当前用户拥有权限function所对应的菜单（回溯到菜单顶端）
            sql = "select distinct menu_code,menu_desc,order_seq,parent_menu from ts_menu start with menu_code in (select distinct menu_code from v_user_function where user_id='"+ userId +"' or role_code='S002001') connect by prior parent_menu=menu_code order by order_seq";
            rs =  stmt.executeQuery(sql);
            while(rs.next()){
                TreeNode node = new TreeNode();
                node.setId( rs.getString("MENU_CODE") );
                node.setName( rs.getString("MENU_DESC") );
                node.setPid( rs.getString("PARENT_MENU")==null?"":rs.getString("PARENT_MENU") );
                node.setDisSeq( rs.getInt("ORDER_SEQ") );
                result.put(node.getId(), node);
            }
            rs.close();
            stmt.close();
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
        }catch(Exception e){
            log.error(e.toString());
        }finally{
            conn.close();
        }
        return result;
    }

}
