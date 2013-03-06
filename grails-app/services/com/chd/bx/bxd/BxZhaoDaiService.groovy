package com.chd.bx.bxd

class BxZhaoDaiService {

    def serviceMethod() {

    }
    def zhaoDaiSave(BxZhaoDai bxZhaoDai) {
        try {
            if (bxZhaoDai.save(flush: true)) {
                println("bxZhaoDai success.....")
            } else {
                println("bxZhaoDai error......")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
    def zhaoDaiQueryByBxdNo(String bxdNo) {
        String strSql = "from BxZhaoDai where bxdNo = '"+bxdNo+"' order by id asc "
        List<BxZhaoDai> list = BxZhaoDai.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list
        }
    }
}
