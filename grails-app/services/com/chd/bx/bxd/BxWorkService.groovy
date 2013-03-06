package com.chd.bx.bxd

class BxWorkService {

    def serviceMethod() {

    }
    def workSave(BxWork bxWork) {
        try {
            if (bxWork.save(flush: true)) {
                println("bxWork success.....")
            } else {
                println("bxWork error......")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
    def workQueryByBxdNo(String bxdNo) {
        String strSql = "from BxWork where bxdNo = '"+bxdNo+"' order by id asc "
        List<BxWork> list = BxWork.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list
        }
    }
}
