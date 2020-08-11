package com.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategoryDAO {
	
	private Connection conn;
	
	public CategoryDAO(Connection conn) {
		this.conn = conn;
	}
	
	public String getCategoryName(int categoryNo) {
		
		String categoryName = "";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select categoryName from category ";
			sql+= "where categoryNo=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, categoryNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				categoryName = rs.getString("categoryName");
				
			}
			
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return categoryName;
	}
	
	

}
