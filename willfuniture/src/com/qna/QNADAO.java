package com.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QNADAO {
	private Connection conn;

	public QNADAO(Connection conn) {
		this.conn = conn;
	}

	public int getMaxNum() {
		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select nvl(max(qno), 0) from qna";
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

	public int getDataCount(String searchKey, String searchValue) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			searchValue = "%" + searchValue + "%";
			String sql;
			if (!searchKey.equals("") && searchKey != null) {
				sql = "select nvl(count(*), 0) from qna where " + searchKey + " like ? ";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setString(1, searchValue);
			} else {
				sql = "select nvl(count(*), 0) from qna";
				pstmt = this.conn.prepareStatement(sql);
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

			rs.close();
			pstmt.close();
		} catch (Exception var8) {
			System.out.println(var8.toString());
		}

		return count;
	}

	public int insertData(QNADTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "insert into qna(qno, qnacategory, userid, subject, writedate, qnacontent, hitcount, parentno, groupno ) ";
			sql = sql + "values(?, ?, ?, ?, sysdate, ?, 0, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			int qno = dto.getQno();
			int groupNo = dto.getGroupNo();
			int parentNo = dto.getParentNo();

			if (parentNo == 0)
				groupNo = qno + 1;

			pstmt.setInt(1, this.getMaxNum() + 1);
			pstmt.setString(2, dto.getQnaCategory());
			pstmt.setString(3, dto.getUserId());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getQnaContent());
			pstmt.setInt(6, parentNo);
			pstmt.setInt(7, groupNo);

			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception var6) {
			System.out.println(var6.toString());
		}

		return result;
	}

	public QNADTO getReadData(int qno) {
		QNADTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select qno, qnacategory, userid, subject, writedate, qnacontent, hitcount ";
			sql = sql + "from qna where qno = ? ";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, qno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new QNADTO();
				dto.setQno(qno);
				dto.setQnaCategory(rs.getString("qnacategory"));
				dto.setUserId(rs.getString("userid"));
				dto.setSubject(rs.getString("subject"));
				dto.setWriteDate(rs.getString("writedate"));
				dto.setQnaContent(rs.getString("qnacontent"));
				dto.setHitCount(rs.getInt("hitcount"));
			}

			rs.close();
			pstmt.close();
		} catch (Exception var7) {
			System.out.println(var7.toString());
		}

		return dto;
	}

	public List<QNADTO> getList(int start, int end, String searchKey, String searchValue) {
		List<QNADTO> lists = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		String admin = "admin";
		try {
			searchValue = "%" + searchValue + "%";

			if (searchKey.equalsIgnoreCase("userId") && (searchValue != null || !searchValue.equals(""))) {
				sql = "select * from(";
				sql += "select rownum rnum, lists.* from(";
				sql += "select level, qno, parentno, groupno, qnacategory, subject, userid, writedate, hitcount FROM QNA where "
						+ searchKey + " like ? or userid = ? ";
				sql += "start with userid like ? and parentno = 0 connect by prior qno = parentno order siblings by qno desc, groupno desc ";
				sql += ") lists) ";
				sql += "where rnum >= ? and rnum <= ?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setString(1, searchValue);
				pstmt.setString(2, "admin");
				pstmt.setString(3, searchValue);
				pstmt.setInt(4, start);
				pstmt.setInt(5, end);
			} else if (!searchKey.equals("") && searchKey != null) {
				sql = "select * from(";
				sql += "select rownum rnum, lists.* from(";
				sql += "select level, qno, parentno, groupno, qnacategory, subject, userid, writedate, hitcount FROM QNA where "
						+ searchKey + " like ? ";
				sql += "start with userid like ? and parentno = 0 connect by prior qno = parentno order siblings by qno desc, groupno desc ";
				sql += ") lists) ";
				sql += "where rnum >= ? and rnum <= ?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setString(1, searchValue);
				pstmt.setString(2, "%%");
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
			} else {
				sql = "select * from(";
				sql += "select rownum rnum, lists.* from(";
				sql += "select level, qno, parentno, groupno, qnacategory, subject, userid, writedate, hitcount FROM QNA ";
				sql += "start with userid like ? and parentno = 0 connect by prior qno = parentno order siblings by qno desc, groupno desc ";
				sql += ") lists) ";
				sql += "where rnum >= ? and rnum <= ?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setString(1, "%" + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				QNADTO dto = new QNADTO();
				dto.setLevel(rs.getInt("level"));
				dto.setQno(rs.getInt("qno"));
				dto.setUserId(rs.getString("userid"));
				dto.setQnaCategory(rs.getString("qnacategory"));
				dto.setSubject(rs.getString("subject"));
				dto.setWriteDate(rs.getString("writedate"));
				dto.setHitCount(rs.getInt("hitcount"));
				dto.setParentNo(rs.getInt("parentno"));
				dto.setGroupNo(rs.getInt("groupno"));
				lists.add(dto);
			}

			rs.close();
			pstmt.close();
		} catch (Exception var10) {
			System.out.println(var10.toString());
		}

		return lists;
	}

	public int updateHitCount(int qno) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "update qna set hitcount = hitcount + 1 where qno = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, qno);
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception var6) {
			System.out.println(var6.toString());
		}

		return result;
	}

	public int updateData(QNADTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "update qna set qnaCategory = ?, subject = ?, qnaContent = ? where qno = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, dto.getQnaCategory());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getQnaContent());
			pstmt.setInt(4, dto.getQno());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception var6) {
			System.out.println(var6.toString());
		}

		return result;
	}

	public int deleteData(int qno) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "delete qna where qno = ?";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, qno);
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception var6) {
			System.out.println(var6.toString());
		}

		return result;
	}

	public List<QNADTO> myQNAList(String userId, int start, int end) {

		List<QNADTO> lists = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select * from(";
			sql += "select rownum rnum, lists.* from(";
			sql += "select level, qno, parentno, groupno, qnacategory, subject, userid, writedate, hitcount FROM ";
			sql += "qna ";
			sql += "start with userid = ? and parentno = 0 connect by prior qno = parentno order siblings by qno desc, groupno desc ";
			sql += ") lists) ";
			sql += "where rnum >= ? and rnum <= ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				QNADTO dto = new QNADTO();
				dto.setLevel(rs.getInt("level"));
				dto.setQno(rs.getInt("qno"));
				dto.setUserId(rs.getString("userid"));
				dto.setQnaCategory(rs.getString("qnacategory"));
				dto.setSubject(rs.getString("subject"));
				dto.setWriteDate(rs.getString("writedate"));
				dto.setHitCount(rs.getInt("hitcount"));
				dto.setParentNo(rs.getInt("parentno"));
				dto.setGroupNo(rs.getInt("groupno"));
				lists.add(dto);
			}

			rs.close();
			pstmt.close();
		} catch (Exception var10) {
			System.out.println(var10.toString());
		}

		return lists;
	}

	public int updateStatus(int qno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "update qna set status = 'Y' where qno = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qno);

			result = pstmt.executeUpdate();

			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	public int getMyDataCount(String userId) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			/*
			 * searchValue = "%" + searchValue + "%"; String sql; if (!searchKey.equals("")
			 * && searchKey != null) { sql = "select nvl(count(), 0) from qna where " +
			 * searchKey + " like ? "; pstmt = this.conn.prepareStatement(sql);
			 * pstmt.setString(1, searchValue); } else {
			 */
			sql = "select nvl(count(*), 0) from(";
			sql += "select rownum rnum, lists.* from(";
			sql += "select level, qno, parentno, groupno, qnacategory, subject, userid, writedate, hitcount FROM QNA where userid like ? "
					+ "or userid = ?";
			sql += "start with userid like ? and parentno = 0 connect by prior qno = parentno order siblings by qno desc, groupno desc ";
			sql += ") lists) ";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, "admin");
			pstmt.setString(3, userId);

			/* } */

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

			rs.close();
			pstmt.close();
		} catch (Exception var8) {
			System.out.println(var8.toString());
		}

		return count;
	}

	public int getNoDataCount() {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select nvl(count(*), 0) from qna where userid != ? and status = ?";
			pstmt = this.conn.prepareStatement(sql);

			pstmt.setString(1, "admin");
			pstmt.setString(2, "N");
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}

			rs.close();
			pstmt.close();
		} catch (Exception var8) {
			System.out.println(var8.toString());
		}

		return count;
	}

	public List<QNADTO> noList(int start, int end) {

		List<QNADTO> lists = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select * from(";
			sql += "select rownum rnum, lists.* from(";
			sql += "select level, qno, parentno, groupno, qnacategory, subject, userid, writedate, hitcount FROM ";
			sql += "qna ";
			sql += "start with status = 'N' and parentno = 0 connect by prior qno = parentno order siblings by qno desc, groupno desc ";
			sql += ") lists) ";
			sql += "where rnum >= ? and rnum <= ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				QNADTO dto = new QNADTO();
				dto.setLevel(rs.getInt("level"));
				dto.setQno(rs.getInt("qno"));
				dto.setUserId(rs.getString("userid"));
				dto.setQnaCategory(rs.getString("qnacategory"));
				dto.setSubject(rs.getString("subject"));
				dto.setWriteDate(rs.getString("writedate"));
				dto.setHitCount(rs.getInt("hitcount"));
				dto.setParentNo(rs.getInt("parentno"));
				dto.setGroupNo(rs.getInt("groupno"));
				lists.add(dto);
			}

			rs.close();
			pstmt.close();
		} catch (Exception var10) {
			System.out.println(var10.toString());
		}

		return lists;
	}

	// 위에꺼 수정전 예비용
	/*
	 * public List<QNADTO> noList() {
	 * 
	 * List<QNADTO> lists = new ArrayList(); PreparedStatement pstmt = null;
	 * ResultSet rs = null; String sql;
	 * 
	 * try { sql = "select * from("; sql += "select rownum rnum, lists.* from("; sql
	 * +=
	 * "select level, qno, parentno, groupno, qnacategory, subject, userid, writedate, hitcount FROM "
	 * ; sql += "qna "; sql +=
	 * "start with status = 'N' and parentno = 0 connect by prior qno = parentno order siblings by qno desc, groupno desc "
	 * ; sql += ") lists) "; pstmt = conn.prepareStatement(sql);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * while (rs.next()) { QNADTO dto = new QNADTO();
	 * dto.setLevel(rs.getInt("level")); dto.setQno(rs.getInt("qno"));
	 * dto.setUserId(rs.getString("userid"));
	 * dto.setQnaCategory(rs.getString("qnacategory"));
	 * dto.setSubject(rs.getString("subject"));
	 * dto.setWriteDate(rs.getString("writedate"));
	 * dto.setHitCount(rs.getInt("hitcount"));
	 * dto.setParentNo(rs.getInt("parentno")); dto.setGroupNo(rs.getInt("groupno"));
	 * lists.add(dto); }
	 * 
	 * rs.close(); pstmt.close(); } catch (Exception var10) {
	 * System.out.println(var10.toString()); }
	 * 
	 * return lists; }
	 */

}