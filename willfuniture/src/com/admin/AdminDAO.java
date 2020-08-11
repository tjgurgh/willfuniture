package com.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.InterningXmlVisitor;



public class AdminDAO {
	
	
	
	private Connection conn;
	
	public AdminDAO(Connection conn) {
		this.conn = conn;
	}
	
	
	public int getFileMaxNum() {//사진파일넘버링
		
		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select nvl(max(FNUM),0) from files";
			
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
	
	public int getProMaxNum() {//상품넘버링
		
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
	
	
	public int insertProFile(AdminDTO dto) {//사진등록
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		
		try {
			sql = "insert into files (fnum,productNo,saveFileName,originalFileName)";
			sql+=" values(?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getFnum());
			pstmt.setInt(2, dto.getProductNo());
			pstmt.setString(3, dto.getSaveFileName());
			pstmt.setString(4, dto.getOriginalFileName());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	
	
	public int insertProData(AdminDTO dto) {//상품등록
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "insert into product (productNo,productName,price,categoryNo,status,proContent)";
			sql+=" values(?,?,?,?,1,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getProductNo());
			pstmt.setString(2, dto.getProductName());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setInt(4, dto.getCategoryNo());
			pstmt.setString(5, dto.getProContent().replaceAll("\n", "<br/>"));
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}	
	
	
	
	public int getDataCount() {//페이징에사용
		int dataCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql="select nvl(count(*),0) from product";
			
			pstmt = conn.prepareStatement(sql);
			
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
	
	
	public List<AdminDTO> getProductList(int start, int end) {
		
		List<AdminDTO> lists = new ArrayList<AdminDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql="select * from(";
			sql+="select rownum rnum, data.* from(";
			sql+="select productName,a.productNo,categoryNo,proContent,status,price,b.saveFilename," + 
					"b.FNUM from product a , " + 
					"(SELECT fnum,productno,saveFileName,originalFileName from files) b " + 
					"where a.productno = b.productno and savefilename like '%-1%'" + 
					"order by a.productno asc";
			sql+=") data)";
			sql+=" where rnum>=? and rnum<=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AdminDTO dto = new AdminDTO();
				
				rs.getString("saveFileName");
				String str = rs.getString("saveFileName");
				String saveFileName=str.replaceAll("-11.jpg", ".jpg");
				saveFileName=str.replaceAll(".jpg-1", ".jpg");
				/*String saveFileName=str.replaceAll(".jpg-1", ".jpg");*/
				
				dto.setProductNo(rs.getInt("productNo"));
				dto.setPrice(rs.getInt("price"));
				dto.setCategoryNo(rs.getInt("categoryNo"));
				dto.setSaveFileName(saveFileName);
				dto.setProductName(rs.getString("productName"));
				dto.setStatus(rs.getInt("status"));
				
				
				lists.add(dto);
			}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}
	
	public String getSaveFileName(int productNo) {
		
		String SaveFileName = "";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql="select saveFileName from files where productNo=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, productNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				SaveFileName = rs.getString(1);
			}
			
			rs.close();
			pstmt.close();			
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return SaveFileName;
	}
	
	
public AdminDTO getReadData(int productNo) {
		

		AdminDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
				
		try {
			
			sql="select categoryNo,productName,price,proContent from product where productNo=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, productNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto = new AdminDTO();
				dto.setProductNo(productNo);
				dto.setCategoryNo(rs.getInt("CategoryNo"));
				dto.setProductName(rs.getString("ProductName"));
				dto.setPrice(rs.getInt("Price"));
				dto.setProContent(rs.getString("proContent"));
													
			}
			rs.close();
			pstmt.close();
							
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}



public int updateProData(AdminDTO dto) {//상품수정
	int result = 0;

	PreparedStatement pstmt = null;
	String sql;

	try {
		sql = "update product set productName=?,price=?,categoryNo=?,proContent=? where productNo=?";


		pstmt = conn.prepareStatement(sql);

		
		pstmt.setString(1, dto.getProductName());
		pstmt.setInt(2, dto.getPrice());
		pstmt.setInt(3, dto.getCategoryNo());
		pstmt.setString(4, dto.getProContent());
		pstmt.setInt(5, dto.getProductNo());

		

		
		
		result = pstmt.executeUpdate();

		pstmt.close();

	} catch (Exception e) {
		System.out.println(e.toString());
	}
	return result;
}	


public int updateProFile(AdminDTO dto) {//사진수정
	int result = 0;
	
	PreparedStatement pstmt = null;
	String sql;
	
	
	try {
		sql = "update files set productNo=?,saveFileName=?,originalFileName=? where Fnum=?";
		pstmt = conn.prepareStatement(sql);
		
		
		pstmt.setInt(1, dto.getProductNo());
		pstmt.setString(2, dto.getSaveFileName());
		pstmt.setString(3, dto.getOriginalFileName());
		pstmt.setInt(4, dto.getFnum());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
	} catch (Exception e) {
		System.out.println(e.toString());
	}
	return result;
}




	
public int findFnum(int productNo) {//사진수정시 fnum 찾아오기
		
		int findFnum = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql="select min(Fnum) from files where productNo=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, productNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				findFnum = rs.getInt(1);
			}
			
			rs.close();
			pstmt.close();			
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return findFnum;
	}
	

public String findSaveFileName(int Fnum) {//사진수정시 파일세이브네임 찾아오기
	
	String findSaveFileName = "";
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql;
	
	try {
		
		sql="select saveFileName from files where fnum=?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Fnum);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			findSaveFileName = rs.getString(1);
		}
		
		rs.close();
		pstmt.close();			
		
		
	} catch (Exception e) {
		System.out.println(e.toString());
	}
	return findSaveFileName;
}





public int deleteProData(int productNo) {//상품수정
	int result = 0;

	PreparedStatement pstmt = null;
	String sql;

	try {
		sql = "update product set status=2 where productNo=?";


		pstmt = conn.prepareStatement(sql);

		
		pstmt.setInt(1, productNo);

	
		
		result = pstmt.executeUpdate();

		pstmt.close();

	} catch (Exception e) {
		System.out.println(e.toString());
	}
	return result;
}	

	




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	


