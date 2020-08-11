package com.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

	private Connection conn;

	public ProductDAO(Connection conn) {
		this.conn = conn;
	}

	public ProductDTO getReadData(int productNo) {

		ProductDTO pDto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

/*			sql = "select productNo,productName,price,bonusPoint,categoryNo,status ";
			sql+= "from product where productNo=?";*/
			
			sql = "select productNo,productName,price,categoryNo,status,proContent ";
			sql+= "from product where productNo=? and status=1";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, productNo);

			rs = pstmt.executeQuery();

			if(rs.next()) {

				pDto = new ProductDTO();

				pDto.setProductNo(rs.getInt("productNo"));
				pDto.setProductName(rs.getString("productName"));
				pDto.setPrice(rs.getInt("price"));
			/*	pDto.setBonusPoint(rs.getInt("bonusPoint"));*/
				pDto.setCategoryNo(rs.getInt("categoryNo"));
				pDto.setStatus(rs.getInt("status"));
				pDto.setProContent(rs.getString("proContent"));

			}

			pstmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return pDto;
	}

	public List<ProductDTO> getProductList(int start, int end, int categoryNo){

		List<ProductDTO> pLists = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select * from (select rownum rnum, data.* from";
			sql+= "(select productNo,productName,price from product ";
			sql+= "where categoryNo=? and status=1 order by productNo desc) data) ";
			sql+= "where rnum>=? and rnum<=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, categoryNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				ProductDTO pDto = new ProductDTO();

				pDto.setProductNo(rs.getInt("productNo"));
				pDto.setProductName(rs.getString("productName"));
				pDto.setPrice(rs.getInt("price"));

				pLists.add(pDto);

			}

			pstmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return pLists;
	}

	public int getDataCount(int categoryNo) {

		int dataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select nvl(count(*),0) from product where categoryNo=? and status=1 ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, categoryNo);

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
