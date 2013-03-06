package com.chd.bx.empInformation

import empInformation.EmpInformation
import com.chd.bx.login.UserLogin
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class EmpInformationService {

    def empInformationQuery(def id) {
        String strSql = "from UserLogin where id='"+id+"' order by id asc "
        List<UserLogin> list = UserLogin
                .findAll(strSql)
        if (list != null && list.size() > 0) {
            return list.get(0)
        }
    }
    def empInformationQuery2(def id) {
        String strSql = "SELECT TF.FUNCTION_NAME,TF.FUNCTION_CODE,TR.ROLE_NAME,TR.ROLE_CODE \n" +
                "FROM TT_USER_ROLE TUR,TS_ROLE TR,TS_FUNCTION TF,TS_ROLE_RIGHT TRR\n" +
                "WHERE TR.ROLE_CODE = TUR.ROLE_CODE \n" +
                "AND TRR.ROLE_CODE = TUR.ROLE_CODE\n" +
                "AND TRR.FUNCTION_CODE = TF.FUNCTION_CODE\n" +
                "AND USERID = "+id+"\n" +
                "ORDER BY TF.FUNCTION_CODE"
        try {
            def conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.190:1521:ORCL","peihao","peihao")
//            def pre = conn.prepareStatement(strSql)
//            def rs = pre.executeQuery()
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(strSql);
            List<EmpInformation> list = new ArrayList<EmpInformation>()
            while(rs.next()){
                def emp = new EmpInformation()
                emp.setRoleName(rs.getString("ROLE_NAME"))
                emp.setRoleCode(rs.getString("ROLE_CODE"))
                emp.setFunctionName(rs.getString("FUNCTION_NAME"))
                emp.setFunctionCode(rs.getString("FUNCTION_CODE"))
                list.add(emp)
            }
            if (list != null && list.size() > 0) {
                return list
            }else{
                return null
            }
        } catch (Exception e1) {
            e1.printStackTrace()
        }
    }

    def saveEmpInformation(UserLogin empInformation){
        try {
            if (empInformation.save(flush: true)) {
                println("success")
            } else {
                println("error")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
