package menu

import org.apache.commons.logging.LogFactory

/**
 * Created with IntelliJ IDEA.
 * User: HUYANG
 * Date: 13-1-11
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
class TreeGenerater {
    //取得log
    private static final log = LogFactory.getLog(this);
    //树
    protected TreeNode root;

    /**
     * 取得数据，并整理成TreeNode对象
     * @return
     *      TreeNode对象的一个HashMap
     */
    protected Map<String, TreeNode> getNodeMap(){

    }

    /**
     *          根据节点循环，生成Tree的代码
     * @param node
     *          当前节点
     * @param result
     *          StringBuffer变量，在此变量中增加Tree代码
     */
    protected void generateTree(TreeNode node,StringBuffer result){

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
}
