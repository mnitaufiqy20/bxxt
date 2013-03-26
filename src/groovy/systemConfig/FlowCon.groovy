package systemConfig

import com.chd.bx.security.UserRole
import com.chd.bx.security.Role

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-24
 * Time: 下午5:15
 * To change this template use File | Settings | File Templates.
 */
class FlowCon {
    String receiptsType;     //流程类型
    String companyNo;         //公司编码
    String empRole;           //角色
    List<Role> empRoleList
    List<UserRole> firstName;  //第一审批人
    List<UserRole> secondName;        //第二审批人
    List<UserRole> thirdName;          //第三审批人
    List<UserRole> fourthName;         //第四审批人
    List<UserRole> fifthName;           //第五审批人
    List<UserRole> postAcc               //过账会计
    List<UserRole> payTeller             //付款出纳
    String firId;
    String secId;
    String thiId;
    String fouId;
    String fifId;
    String posId;
    String payId;

    String getReceiptsType() {
        return receiptsType
    }

    void setReceiptsType(String receiptsType) {
        this.receiptsType = receiptsType
    }

    String getCompanyNo() {
        return companyNo
    }

    void setCompanyNo(String companyNo) {
        this.companyNo = companyNo
    }

    String getEmpRole() {
        return empRole
    }

    void setEmpRole(String empRole) {
        this.empRole = empRole
    }

    List<Role> getEmpRoleList() {
        return empRoleList
    }

    void setEmpRoleList(List<Role> empRoleList) {
        this.empRoleList = empRoleList
    }

    List<UserRole> getFirstName() {
        return firstName
    }

    void setFirstName(List<UserRole> firstName) {
        this.firstName = firstName
    }

    List<UserRole> getSecondName() {
        return secondName
    }

    void setSecondName(List<UserRole> secondName) {
        this.secondName = secondName
    }

    List<UserRole> getThirdName() {
        return thirdName
    }

    void setThirdName(List<UserRole> thirdName) {
        this.thirdName = thirdName
    }

    List<UserRole> getFourthName() {
        return fourthName
    }

    void setFourthName(List<UserRole> fourthName) {
        this.fourthName = fourthName
    }

    List<UserRole> getFifthName() {
        return fifthName
    }

    void setFifthName(List<UserRole> fifthName) {
        this.fifthName = fifthName
    }

    List<UserRole> getPostAcc() {
        return postAcc
    }

    void setPostAcc(List<UserRole> postAcc) {
        this.postAcc = postAcc
    }

    List<UserRole> getPayTeller() {
        return payTeller
    }

    void setPayTeller(List<UserRole> payTeller) {
        this.payTeller = payTeller
    }

    String getFirId() {
        return firId
    }

    void setFirId(String firId) {
        this.firId = firId
    }

    String getSecId() {
        return secId
    }

    void setSecId(String secId) {
        this.secId = secId
    }

    String getThiId() {
        return thiId
    }

    void setThiId(String thiId) {
        this.thiId = thiId
    }

    String getFouId() {
        return fouId
    }

    void setFouId(String fouId) {
        this.fouId = fouId
    }

    String getFifId() {
        return fifId
    }

    void setFifId(String fifId) {
        this.fifId = fifId
    }

    String getPosId() {
        return posId
    }

    void setPosId(String posId) {
        this.posId = posId
    }

    String getPayId() {
        return payId
    }

    void setPayId(String payId) {
        this.payId = payId
    }
}
