package systemConfig

/**
 * Created with IntelliJ IDEA.
 * User: MengMin
 * Date: 13-3-15
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
class RightManage {
     int id                         //角色权限对应表ID
    String functionCode            //子菜单code
    String functionName            //子菜单名字
    String menuDesc                //一级栏目
    String roleRight               //权限

    boolean rightLook = false               //查看
    boolean rightAdd  = false                //新增
    boolean rightDelete = false            //删除
    boolean rightUpdate = false          //修改
    boolean rightArea = false               //被授权区域
    int getId() {
        return id
    }

    void setId(int id) {
        this.id = id
    }

    String getFunctionCode() {
        return functionCode
    }

    void setFunctionCode(String functionCode) {
        this.functionCode = functionCode
    }

    String getFunctionName() {
        return functionName
    }

    void setFunctionName(String functionName) {
        this.functionName = functionName
    }

    String getMenuDesc() {
        return menuDesc
    }

    void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc
    }

    String getRoleRight() {
        return roleRight
    }

    void setRoleRight(String roleRight) {
        this.roleRight = roleRight
    }

    boolean getRightLook() {
        return rightLook
    }

    void setRightLook(boolean rightLook) {
        this.rightLook = rightLook
    }

    boolean getRightAdd() {
        return rightAdd
    }

    void setRightAdd(boolean rightAdd) {
        this.rightAdd = rightAdd
    }

    boolean getRightDelete() {
        return rightDelete
    }

    void setRightDelete(boolean rightDelete) {
        this.rightDelete = rightDelete
    }

    boolean getRightUpdate() {
        return rightUpdate
    }

    void setRightUpdate(boolean rightUpdate) {
        this.rightUpdate = rightUpdate
    }

    boolean getRightArea() {
        return rightArea
    }

    void setRightArea(boolean rightArea) {
        this.rightArea = rightArea
    }
}
