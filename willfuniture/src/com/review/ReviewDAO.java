package com.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

	private Connection conn;

	public ReviewDAO(Connection conn) {
		this.conn = conn;
	}

	public int getMaxNum() {

		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select nvl(max(reviewno), 0) from review";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next())
				maxNum = rs.getInt(1);

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return maxNum;
	}

	// 전체 데이터 개수
	public int getDataCount(String searchKey, String searchValue) {

		int dataCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			searchValue = "%" + searchValue + "%";

			if (searchKey.equals("") || searchKey == null) {
				searchKey = "subject";
			}

			sql = "select nvl(count(*), 0) from review ";
			sql += "where " + searchKey + " like ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);

			rs = pstmt.executeQuery();

			if (rs.next())
				dataCount = rs.getInt(1);

			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dataCount;
	}

	public int insertData(ReviewDTO dto) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "insert into review(reviewno, orderno, userid, writedate, subject, content, hitcount, status, recnt) ";
			sql += "values(?, ?, ?, sysdate, ? ,?, 0, 1, 0)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getMaxNum() + 1);
			pstmt.setString(2, dto.getOrderNo());
			pstmt.setString(3, dto.getUserId());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContent());

			result = pstmt.executeUpdate();

			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	public int replyCount(int qno) {

		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select nvl(count(*), 0) from reply where qno = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, qno);

			rs = pstmt.executeQuery();

			if (rs.next())
				count = rs.getInt(1);

			pstmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return count;
	}

	public ReviewDTO getReadData(int reviewNo) {

		ReviewDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select reviewno, orderno, userid, writedate, subject, content, hitcount, status from review ";
			sql += "where reviewno = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new ReviewDTO();

				dto.setReviewNo(rs.getInt("reviewno"));
				dto.setOrderNo(rs.getString("orderno"));
				dto.setUserId(rs.getString("userid"));
				dto.setWriteDate(rs.getString("writedate"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setHitCount(rs.getInt("hitcount"));
				dto.setStatus(rs.getInt("status"));
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;
	}

	public int updateData(ReviewDTO dto) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "update review set subject = ?, content = ? where reviewno = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getReviewNo());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	public int deleteData(int reviewNo) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "delete review where reviewno = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, reviewNo);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	public int updateHitCount(int reviewNo) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "update review set hitcount = hitcount + 1 where reviewno = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNo);

			result = pstmt.executeUpdate();

			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	public List<ReviewDTO> getList(int start, int end, String searchKey, String searchValue) {

		List<ReviewDTO> lists = new ArrayList<ReviewDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			if (searchKey.equals("") || searchKey == null) {
				searchKey = "subject";
			}

			searchValue = "%" + searchValue + "%";

			sql = "select * from (";
			sql += "select rownum rnum, lists.* from (";
			sql += "select reviewno, orderno, userid, writedate, subject, content, hitcount, status, recnt ";
			sql += "from review where " + searchKey + " like ? ";
			sql += "order by reviewno desc) lists) ";
			sql += "where rnum >= ? and rnum <= ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, searchValue);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ReviewDTO dto = new ReviewDTO();

				dto.setReviewNo(rs.getInt("reviewno"));
				dto.setOrderNo(rs.getString("orderno"));
				dto.setUserId(rs.getString("userid"));
				dto.setWriteDate(rs.getString("writedate"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setHitCount(rs.getInt("hitcount"));
				dto.setStatus(rs.getInt("status"));
				dto.setRecnt(rs.getInt("recnt"));
				lists.add(dto);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return lists;
	}
}
