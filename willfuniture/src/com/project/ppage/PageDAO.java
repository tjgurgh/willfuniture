package com.project.ppage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.Request;


public class PageDAO {

	
	
	private Connection conn;
	
	public PageDAO(Connection conn) {
		this.conn = conn;
	}	

	public PageDTO getReadData(int categorytNo) {
		
		PageDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql="select pro.* , files.* from product pro , (select saveFileName , originalFileName from files) files ";
			sql+="where categoryNo=1";
			
			pstmt = conn.prepareStatement(sql);
			

			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto = new PageDTO();
				
				dto.setProductNo(rs.getInt("productNo"));
				dto.setProductName(rs.getString("productName"));
				dto.setPrice(rs.getInt("price"));
				/*dto.setBonusPoint(rs.getInt("bonusPoint"));*/
				dto.setCategoryNo(rs.getInt("categoryNo"));
				dto.setStatus(rs.getInt("status"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setOriginalFileName(rs.getString("originalFileName"));
													
			}
			rs.close();
			pstmt.close();
							
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}
	
	public List<PageDTO> getList(int start, int end) {
		
		List<PageDTO> lists = new ArrayList<PageDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {

/*			sql = "select * from (select ROWNUM rnum, data.* from (select productName,productNo,categoryNo,price,bonusPoint,status from product where CATEGORYNO =1 order by PRODUCTNO desc) data) a , (SELECT productno,saveFileName,originalFileName from files) b where a.productno = b.productno and b.originalfilename LIKE '%-1%' and a.rnum>=? and a.rnum<=?";
*/			sql = "select * from (select ROWNUM rnum, data.* from (select productName,productNo,categoryNo,price,status from product where CATEGORYNO =1 order by PRODUCTNO desc) data) a , (SELECT productno,saveFileName,originalFileName from files) b where a.productno = b.productno and b.originalfilename LIKE '%-1%' and a.rnum>=? and a.rnum<=?";

			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setInt(1, 카테고리넘버);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				PageDTO dto = new PageDTO();

				
				dto.setProductNo(rs.getInt("productNo"));
				dto.setProductName(rs.getString("productName"));
				dto.setPrice(rs.getInt("price"));
				/*dto.setBonusPoint(rs.getInt("bonusPoint"));*/
				dto.setCategoryNo(rs.getInt("categoryNo"));
				dto.setStatus(rs.getInt("status"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setOriginalFileName(rs.getString("originalFileName"));
				lists.add(dto);

				}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}
	
	
	public int getMaxNum() {
		
		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select nvl(max(productNo),0) from product";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				maxNum = rs.getInt(1);
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return maxNum;
	}

	public int getDataCount() {
		
		PageDTO dto = new PageDTO();
		
		
		int dataCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		
		
		try {
			
			sql = "select nvl(count(*),0) from product where categoryNo = 1";
			
			pstmt = conn.prepareStatement(sql);
			
			
		//	dto.setCategoryNo(rs.getInt("categoryNo"));

			int categoryNo = 0;

			
		//	pstmt.setInt(1, categoryNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {				
								
				dataCount = rs.getInt(1);
			}
			
			rs.close();
			pstmt.close();			
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}		
		
		return dataCount;
		
	}
	
}
