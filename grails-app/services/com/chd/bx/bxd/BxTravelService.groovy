package com.chd.bx.bxd

class BxTravelService {

    def serviceMethod() {

    }
    def travelSave(BxTravel bxTravel) {
        try {
            if (bxTravel.save(flush: true)) {
                println("bxTravel success.....")
            } else {
                println("bxTravel error......")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
    def travelQueryByBxdNo(String bxdNo) {
        String strSql = "from BxTravel where bxdNo = '"+bxdNo+"' order by id asc "
        List<BxTravel> list = BxTravel.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list
        }
    }
    def travelGetOneByBxdNo(String bxdNo) {
        String strSql = "from BxTravel where bxdNo = '"+bxdNo+"' order by id asc "
        List<BxTravel> list = BxTravel.findAll(strSql)
        if (list != null && list.size() > 0) {
            return list.get(0)
        }
    }
}
