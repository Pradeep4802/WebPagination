package com.bean;
import java.sql.*;
import java.util.*;

public class EmpDao {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/MyTest";
			con = DriverManager.getConnection(url,"root","");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static List<Emp> getRecords(int start,int total) throws SQLException {
		List<Emp> list = new ArrayList<Emp>();
		Connection con = getConnection();
		String SELECT_ALL = "SELECT * from emp limit "+(start-1)+","+total;
		PreparedStatement pst = con.prepareStatement(SELECT_ALL);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			Emp e = new Emp();
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setSalary(rs.getFloat(3));
			list.add(e);
		}
		return list;
	}
}
