package com.chd.bx.bxd

class BxOtherService {

    def serviceMethod() {

    }
    def otherSave(BxOther bxOther) {
        try {
            if (bxOther.save(flush: true)) {
                println("bxOther success.....")
            } else {
                println("bxOther error......")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
    def otherQueryByBxdNo(String bxdNo) {
        String strSql = "from BxOther where bxdNo = '"+bxdNo+"' order by oNo asc "
        List<BxOther> list = BxOther.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list
        }
    }
    def otherDeleteByBxdNo(String bxdNo) {
        String strSql = "from BxOther where bxdNo = '"+bxdNo+"' "
        try{
            BxOther.deleteAll(strSql)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
