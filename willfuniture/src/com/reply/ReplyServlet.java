package com.reply;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orders.OrdersDAO;
import com.orders.OrdersDTO;
import com.review.ReviewDAO;
import com.review.ReviewDTO;
import com.util.DBCPConn;
import com.util.MyUtil;

public class ReplyServlet extends HttpServlet {

	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url)
			throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String cp = req.getContextPath();
		Connection conn = DBCPConn.getConnection();

		ReplyDAO dao = new ReplyDAO(conn);
		ReviewDAO rvdao = new ReviewDAO(conn);

		MyUtil util = new MyUtil();

		String uri = req.getRequestURI();
		String url;

		if (uri.indexOf("created.do") != -1) {
			
		} else if (uri.indexOf("created_ok.do") != -1) {

			ReplyDTO dto = new ReplyDTO();

			dto.setRno(dao.getMaxNum());
			dto.setQno(Integer.parseInt(req.getParameter("qno")));
			dto.setUserId(req.getParameter("userId"));
			dto.setContent(req.getParameter("content"));

			if (dao.insertData(dto) == 1)
				dao.updateReplyCount(Integer.parseInt(req.getParameter("qno")));

			url = cp + "/sreview/list.do";

			resp.sendRedirect(url);
		} else if(uri.indexOf("updated.do") != -1) {
			
			int rno = Integer.parseInt(req.getParameter("rno"));
			String pageNum = req.getParameter("pageNum");

			if (pageNum == null) {
				pageNum = "1";
			}

			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");

			if (searchValue != null) {
				if (req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			} else {
				searchKey = "subject";
				searchValue = "";
			}

			String params = "pageNum=" + pageNum;

			if (!searchValue.equals("")) {
				params += "&searchKey=" + searchKey;
				params += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			ReplyDTO dto = dao.getReadData(rno);
		
			if (dto == null) {
				url = cp + "/sreview/list.do";
				resp.sendRedirect(url);
			}
			
			req.setAttribute("dto", dto);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("params", params);
			req.setAttribute("searchKey", searchKey);
			req.setAttribute("searchValue", searchValue);
			req.setAttribute("rno", rno);

			url = "/project/review/repUpdated.jsp";
			forward(req, resp, url);
			
		} else if (uri.indexOf("updated_ok.do") != -1) {

			int rno = Integer.parseInt(req.getParameter("rno"));
			int qno = Integer.parseInt(req.getParameter("qno"));
			String content = req.getParameter("content");
			
			String pageNum = req.getParameter("pageNum");

			if (pageNum == null) {
				pageNum = "1";
			}

			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");

			if (searchValue != null) {
				if (req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			} else {
				searchKey = "subject";
				searchValue = "";
			}

			String params = "pageNum=" + pageNum;

			if (!searchValue.equals("")) {
				params += "&searchKey=" + searchKey;
				params += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			
			ReplyDTO dto = new ReplyDTO();
			
			dto.setRno(rno);
			dto.setContent(content);
			
			dao.updateData(dto);
			
			url = cp + "/sreview/article.do?" + params + "&reviewNo=" + qno;
			resp.sendRedirect(url);
		} else if(uri.indexOf("deleted_ok.do") != -1) {
			
			int rno = Integer.parseInt(req.getParameter("rno"));
			int qno = Integer.parseInt(req.getParameter("qno"));
			
			String pageNum = req.getParameter("pageNum");
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");

			if (searchValue != null) {
				if (req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			} else {
				searchKey = "subject";
				searchValue = "";
			}

			String params = "pageNum=" + pageNum;

			if (!searchValue.equals("")) {
				params += "&searchKey=" + searchKey;
				params += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			if(dao.deleteData(rno) == 1)
				dao.deleteReplyCount(qno);

			url = cp + "/sreview/list.do?" + params;
			resp.sendRedirect(url);
		}
	}
}
