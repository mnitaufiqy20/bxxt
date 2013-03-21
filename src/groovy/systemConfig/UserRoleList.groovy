package systemConfig

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-21
 * Time: 下午2:51
 * To change this template use File | Settings | File Templates.
 */
class UserRoleList {
    String roleName        //角色名称
    String userName                  //该角色下姓名字符串
    String userId                    //该角色下用户Id字符串
    int roleId                                       //角色id

    String getRoleName() {
        return roleName
    }

    void setRoleName(String roleName) {
        this.roleName = roleName
    }

    String getUserName() {
        return userName
    }

    void setUserName(String userName) {
        this.userName = userName
    }

    String getUserId() {
        return userId
    }

    void setUserId(String userId) {
        this.userId = userId
    }

    int getRoleId() {
        return roleId
    }

    void setRoleId(int roleId) {
        this.roleId = roleId
    }
}
