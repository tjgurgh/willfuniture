package com.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.review.ReviewDTO;

public class ReplyDAO {
	private Connection conn;

	public ReplyDAO(Connection conn) {
		this.conn = conn;
	}

	public int getMaxNum() {
		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select nvl(max(rno), 0) from reply";
			pstmt = this.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxNum = rs.getInt(1);
			}

			rs.close();
			pstmt.close();
		} catch (Exception var6) {
			System.out.println(var6.toString());
		}

		return maxNum;
	}

	public int getDataCount(int qno) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select nvl(count(*), 0) from reply where qno = ?";
			pstmt = this.conn.prepareStatement(sql);

			pstmt.setInt(1, qno);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

			rs.close();
			pstmt.close();
		} catch (Exception var6) {
			System.out.println(var6.toString());
		}

		return count;
	}

	public int insertData(ReplyDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "insert into reply(rno, qno, userid, content, writedate) ";
			sql += "values(?, ?, ?, ?, sysdate)";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, getMaxNum() + 1);
			pstmt.setInt(2, dto.getQno());
			pstmt.setString(3, dto.getUserId());
			pstmt.setString(4, dto.getContent());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception var6) {
			System.out.println(var6.toString());
		}

		return result;
	}

	public List<ReplyDTO> getList(int qno, int start, int end) {
		List<ReplyDTO> lists = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql;
		try {

			sql = "select * from(";
			sql += "select rownum rnum, lists.* from(";
			sql += "select rno, qno, userid, content, writedate from reply where qno = ? order by rno desc ";
			sql += ") lists) where rnum >= ? and rnum <= ?";

			pstmt = this.conn.prepareStatement(sql);

			pstmt.setInt(1, qno);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReplyDTO dto = new ReplyDTO();
				dto.setRno(rs.getInt("rno"));
				dto.setQno(rs.getInt("qno"));
				dto.setUserId(rs.getString("userid"));
				dto.setContent(rs.getString("content"));
				dto.setWriteDate(rs.getString("writedate"));
				lists.add(dto);
			}

			rs.close();
			pstmt.close();
		} catch (Exception var6) {
			System.out.println(var6.toString());
		}

		return lists;
	}

	public int updateData(ReplyDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "update reply set content = ? where rno = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, dto.getContent());
			pstmt.setInt(2, dto.getRno());
			
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception var6) {
			System.out.println(var6.toString());
		}

		return result;
	}

	public int deleteData(int rno) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "delete reply where rno = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception var6) {
			System.out.println(var6.toString());
		}

		return result;
	}

	public int updateReplyCount(int qno) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "update review set recnt = recnt +1 where reviewno = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, qno);

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	public int deleteReplyCount(int qno) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "update review set recnt = recnt -1 where reviewno = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, qno);

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	public ReplyDTO getReadData(int rno) {

		ReplyDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select rno, qno, userid, content, writedate from reply ";
			sql += "where rno = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new ReplyDTO();
				
				dto.setRno(rs.getInt("rno"));
				dto.setQno(rs.getInt("qno"));
				dto.setUserId(rs.getString("userid"));
				dto.setContent(rs.getString("content"));
				dto.setWriteDate(rs.getString("writedate"));
				
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;
	}
}