package com.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
	
private Connection conn;
	
	public CartDAO(Connection conn) {
		this.conn = conn;
	}
	
	// cartNo 매기기
	public int getMaxNum() {
		
		int maxNum = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select nvl(max(cartNo),0) from cart";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				maxNum = rs.getInt(1);
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return maxNum;
	}
	
	// insert
	public int insertData(CartDTO dto) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "insert into cart (cartNo,productNo,productName,userId,quantity,price) ";
			sql+= "values (?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getCartNo());
			pstmt.setInt(2, dto.getProductNo());
			pstmt.setString(3, dto.getProductName());
			pstmt.setString(4, dto.getUserId());
			pstmt.setInt(5, dto.getQuantity());
			pstmt.setInt(6, dto.getPrice());
					
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	// 해당 userId의 장바구니 리스트
	public List<CartDTO> getList(String userId){
		
		List<CartDTO> lists = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select cartNo,productNo,productName,userId,quantity,price ";
			sql+= "from cart where userId=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				CartDTO cDto = new CartDTO();
				
				cDto.setCartNo(rs.getInt("cartNo"));
				cDto.setProductNo(rs.getInt("productNo"));
				cDto.setProductName(rs.getString("productName"));
				cDto.setUserId(rs.getString("userId"));
				cDto.setQuantity(rs.getInt("quantity"));
				cDto.setPrice(rs.getInt("price"));
				
				lists.add(cDto);
				
			}
			
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return lists;
	}
	
	// 해당 userId의 장바구니 총액
	public int getCartTotal(String userId) {
		
		int cartTotal = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select SUM((price*quantity)) from cart ";
			sql+= "where userId=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				cartTotal = rs.getInt(1);
				
			}
			
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return cartTotal;
	}
	
	// 장바구니에서 해당 상품만 있는지 확인
	public int getQuantity(String userId, int productNo) {
		
		int quan = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select quantity ";
			sql+= "from cart where userId=? and productNo=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setInt(2, productNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				quan = rs.getInt("quantity");
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return quan;
	}
	

	// 장바구니 수량 변경
	public int updateQuantity(String userId, int productNo, int quan, int quantity) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "update cart set quantity=? ";
			sql+= "where userId=? and productNo=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, (quan+quantity));
			pstmt.setString(2, userId);
			pstmt.setInt(3, productNo);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	// 특정 상품만 delete
	public int deleteData(String userId, int productNo) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;
		
		try {
		
			sql = "delete cart where userId=? and productNo=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setInt(2, productNo);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	// 해당 id의 cart 갯수
	public int getCartCount(String userId) {
		
		int cartCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select nvl(count(*),0) from cart ";
			sql+= "where userId=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				cartCount = rs.getInt(1);
				
			}
			
			pstmt.close();
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return cartCount;
	}
	
	

}
