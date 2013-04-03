package sapSystem

class CerIntegration {
    String companyCode;//公司代码
    String accYear;//会计年度
    String cerNo;//凭证编号
    String recNo;//借款单号
    String mesType;//消息类型
    String mesDes;//消息描述
    String empName;//申请人
    String getCompanyCode() {
        return companyCode
    }

    void setCompanyCode(String companyCode) {
        this.companyCode = companyCode
    }

    String getAccYear() {
        return accYear
    }

    void setAccYear(String accYear) {
        this.accYear = accYear
    }

    String getCerNo() {
        return cerNo
    }

    void setCerNo(String cerNo) {
        this.cerNo = cerNo
    }

    String getRecNo() {
        return recNo
    }

    void setRecNo(String recNo) {
        this.recNo = recNo
    }

    String getMesType() {
        return mesType
    }

    void setMesType(String mesType) {
        this.mesType = mesType
    }

    String getMesDes() {
        return mesDes
    }

    void setMesDes(String mesDes) {
        this.mesDes = mesDes
    }

    String getEmpName() {
        return empName
    }

    void setEmpName(String empName) {
        this.empName = empName
    }
}
