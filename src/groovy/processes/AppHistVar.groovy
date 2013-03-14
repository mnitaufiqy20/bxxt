package processes

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-13
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
class AppHistVar {
    String proId                //流程Id
    String varName              //设置的值name
    String value                //对应的用户Id
    String getProId() {
        return proId
    }

    void setProId(String proId) {
        this.proId = proId
    }

    String getVarName() {
        return varName
    }

    void setVarName(String varName) {
        this.varName = varName
    }

    String getValue() {
        return value
    }

    void setValue(String value) {
        this.value = value
    }
}
