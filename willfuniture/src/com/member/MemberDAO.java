package com.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {

	private Connection conn;

	public MemberDAO(Connection conn) {
		this.conn = conn;
	}

	public MemberDTO getReadData(String userId) {

		MemberDTO mDto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select userId,userPwd,userName,to_char(userBirth, 'YYYY-MM-DD') userBirth,userTel,userEmail,";
			sql+= "addr,addrDetail,totPoint,accumPrice,status from member ";
			sql+= "where userId=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if(rs.next()) {

				mDto = new MemberDTO();

				mDto.setUserId(rs.getString("userId"));
				mDto.setUserPwd(rs.getString("userPwd"));
				mDto.setUserName(rs.getString("userName"));
				mDto.setUserBirth(rs.getString("userBirth"));
				mDto.setUserTel(rs.getString("userTel"));
				mDto.setUserEmail(rs.getString("userEmail"));
				mDto.setAddr(rs.getString("addr"));
				mDto.setAddrDetail(rs.getString("addrDetail"));
				mDto.setTotPoint(rs.getInt("totPoint"));
				mDto.setAccumPrice(rs.getInt("accumPrice"));
				mDto.setStatus(rs.getInt("status"));

			}

			pstmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return mDto;
	}
	
	
	public int getOrderData(String userId) {

		int orderCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select count(*) from orders where userId=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if(rs.next()) {

				orderCount = rs.getInt(1);
			}

			
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return orderCount;
	}


	public int insertData(MemberDTO mDto) {

		int result = 0;

		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "insert into member (userId,userPwd,userName,userBirth, ";
			sql+= "userTel,userEmail,addr,addrDetail,totpoint,accumprice) ";
			sql+= "values (?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mDto.getUserId());
			pstmt.setString(2, mDto.getUserPwd());
			pstmt.setString(3, mDto.getUserName());
			pstmt.setString(4, mDto.getUserBirth());
			pstmt.setString(5, mDto.getUserTel());
			pstmt.setString(6, mDto.getUserEmail());
			pstmt.setString(7, mDto.getAddr());
			pstmt.setString(8, mDto.getAddrDetail());
			pstmt.setInt(9, mDto.getTotPoint());
			pstmt.setInt(10, mDto.getAccumPrice());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}


	public int updateData(MemberDTO mDto) {

		int result = 0;

		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "update member set userPwd=?,userTel=?,userEmail=?,addr=?,addrDetail=? ";
			sql+= "where userId=?";

			pstmt= conn.prepareStatement(sql);

			pstmt.setString(1, mDto.getUserPwd());
			pstmt.setString(2, mDto.getUserTel());
			pstmt.setString(3, mDto.getUserEmail());
			pstmt.setString(4, mDto.getAddr());
			pstmt.setString(5, mDto.getAddrDetail());
			pstmt.setString(6, mDto.getUserId());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	public boolean idCheck (String userId) {

		boolean result = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select userId from member where userId=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = true;
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	public int changeSatus(MemberDTO dto) {

		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "update member set status=0 where userId=?";
			pstmt= conn.prepareStatement(sql);

			pstmt.setString(1, dto.getUserId());

			result = pstmt.executeUpdate();
			pstmt.close();


		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	public int changePoint(int point, String userId) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "update member set totPoint=? where userId=?";
			pstmt= conn.prepareStatement(sql);

			pstmt.setInt(1, point);
			pstmt.setString(2, userId);

			result = pstmt.executeUpdate();
			pstmt.close();


		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

	
	

}






