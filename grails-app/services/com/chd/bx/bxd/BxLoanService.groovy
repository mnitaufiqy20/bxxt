package com.chd.bx.bxd

class BxLoanService {

    def serviceMethod() {

    }
    def loanSave(BxLoan bxLoan) {
        try {
            if (bxLoan.save(flush: true)) {
                println("bxLoan success.....")
            } else {
                println("bxLoan error......")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
    def loanQueryByBxdNo(String bxdNo) {
        String strSql = "from BxLoan where bxdNo = '"+bxdNo+"' order by id asc "
        List<BxLoan> list = BxLoan.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list
        }
    }
}
