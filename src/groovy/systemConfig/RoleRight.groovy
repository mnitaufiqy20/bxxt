package systemConfig

/**
 * Created with IntelliJ IDEA.
 * User: MengMin
 * Date: 13-3-13
 * Time: 下午1:51
 * To change this template use File | Settings | File Templates.
 */
class RoleRight {
    String functionCode                     //栏目Code
    String roleCode                          //角色Code
    String roleRight                         //角色权限
    String functionName                      //栏目name
    String roleName                           //角色name

    String getFunctionCode() {
        return functionCode
    }

    void setFunctionCode(String functionCode) {
        this.functionCode = functionCode
    }

    String getRoleCode() {
        return roleCode
    }

    void setRoleCode(String roleCode) {
        this.roleCode = roleCode
    }

    String getRoleRight() {
        return roleRight
    }

    void setRoleRight(String roleRight) {
        this.roleRight = roleRight
    }

    String getFunctionName() {
        return functionName
    }

    void setFunctionName(String functionName) {
        this.functionName = functionName
    }

    String getRoleName() {
        return roleName
    }

    void setRoleName(String roleName) {
        this.roleName = roleName
    }
}
