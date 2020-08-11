package com.files;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FilesDAO {
	
private Connection conn;
	
	public FilesDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<FilesDTO> getList(int productNo) {
		
		List<FilesDTO> lists = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select fNum,productNo,saveFileName,originalFileName ";
			sql+= "from files where productNo=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, productNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				FilesDTO dto = new FilesDTO();
				
				dto.setfNum(rs.getInt("fNum"));
				dto.setProductNo(rs.getInt("productNo"));
				dto.setSaveFileName(rs.getString("saveFileName"));
				dto.setOriginalFileName(rs.getString("originalFileName"));
				
				lists.add(dto);
				
			}
			
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return lists;
	}
	
	
	
	
	
	// 상품번호[]의 첫번째 이미지 경로만 가져오기
	public String[] getFirstFileName(int productNo[]) {
		
		String[] firstFileName = new String[productNo.length];
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			for(int i=0;i<productNo.length;i++) {
				
				sql = "select saveFileName from files ";
				sql+= "where productNo=? and savefilename like '%-1%'";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, productNo[i]);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					firstFileName[i] = rs.getString("saveFileName");
				
				}
				
			}
			
			
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return firstFileName;
	}
	
	
	public String[] getSecondFileName(int productNo[]) {

		String[] secondFileName = new String[productNo.length];

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			for(int i=0;i<productNo.length;i++) {

				sql = "select saveFileName from files ";
				sql+= "where productNo=? and SAVEFILENAME like '%-2%'";

				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, productNo[i]);

				rs = pstmt.executeQuery();

				if(rs.next()) {

					secondFileName[i] = rs.getString("saveFileName");

				}

			}


			pstmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return secondFileName;
	}
	
	

}
