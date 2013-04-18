package menu;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.ArrayList;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: 杨辉
 * Date: 12-12-11
 * Time: 下午8:19
 * To change this template use File | Settings | File Templates.
 */
public class TreeNode {
    //取得log
    private static final Log log = LogFactory.getLog(TreeNode.class);
    //当前节点ID
	private String id;
    //父节点ID
	private String pid;
    //指向父节点的handler
	private TreeNode parent;
    //当前节点名称
	private String name;
    //当前节点的action属性（URL）
	private String action;
    //当前节点子节点列表
	private List<TreeNode> childList;
    //是否叶节点
	private boolean titleNode;
    //排序属性值
	private Integer disSeq;
	
	/**
	 * 创建默认的根节点
	 * @return
	 */
	public static TreeNode createDefaultRoot(){
		TreeNode result = new TreeNode();
		result.setId(new String("MO"));
		result.setName("根目录");
		return result;
	}
	
	/**
	 * 是否是根节点
	 * @return
	 */
	public boolean isRoot(){
		if(new Integer(0).equals(this.id))
			return true;
		else
			return false;
	}
	
	/**
	 * 是否是叶节点
	 * @return
	 */
	public boolean isLeaf(){
		if(childList == null || childList.isEmpty())
			return true;
		else
			return false;
	}
	
	/**
	 * 是否是第一级菜单，默认不显示根节点。
	 * @return
	 */
	public boolean isTitleNode(){
		return this.titleNode;
	}
	
	/**
	 * 是否当级最后一个菜单
	 * @return
	 */
	public boolean isLastChild(){
		TreeNode parent = this.getParent();
		if(parent != null && !parent.isRoot()){
			List<TreeNode> childList = parent.getChildList();
			if(this.equals(childList.get(childList.size() - 1))){
				return true;
			}
		}
		return false;
	}
	
	public void setTitleNode(boolean titleNode){
		this.titleNode = titleNode;
	}
	
	public void addChildNode(TreeNode node){
		if(childList == null)
			childList = new ArrayList<TreeNode>();
		childList.add(node);
	}
	
	public String toString(){
		return this.name;
	}
	
	
	public TreeNode getParent() {
		return parent;
	}
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public List<TreeNode> getChildList() {
		return childList;
	}
	public void setChildList(List<TreeNode> childList) {
		this.childList = childList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getDisSeq() {
		return disSeq;
	}

	public void setDisSeq(Integer disSeq) {
		this.disSeq = disSeq;
	}
}
