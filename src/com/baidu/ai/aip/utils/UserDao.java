package com.baidu.ai.aip.utils;

 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

 

public class UserDao {

    JdbcUtil jdbcUtil = JdbcUtil.getInstance();
     Connection conn = jdbcUtil.getConnection();
     String a =null;
     String b  =null;
     //注册
    public String register(String neirong) {
        if (conn == null) {
            return "连接失败";
        } else {
            String sql1=" select * from sheet1 where neirong like ?";
        
    // 设定参数
    try {
        PreparedStatement   pstmt = conn.prepareStatement(sql1);

		pstmt.setString(1, "%" + neirong + "%" );
		ResultSet  res= pstmt.executeQuery();
		while(res.next()){
			 System.out.println(res.getString("daan"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}       
    // 获取查询的结果集           
  
       

            
    }
        
     return "aa";
}

}