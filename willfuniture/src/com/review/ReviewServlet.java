package com.review;

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
import com.qna.QNADTO;
import com.reply.ReplyDAO;
import com.reply.ReplyDTO;
import com.util.DBCPConn;
import com.util.MyUtil;
import com.util.ReplyPage;

public class ReviewServlet extends HttpServlet {

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
		
		ReviewDAO dao = new ReviewDAO(conn);
		OrdersDAO odao = new OrdersDAO(conn);
		ReplyDAO rdao = new ReplyDAO(conn);
		
		MyUtil util = new MyUtil();
		ReplyPage rp = new ReplyPage();
		
		String uri = req.getRequestURI();
		String url;

		if (uri.indexOf("created.do") != -1) {
			
			
			String userId = req.getParameter("userId");
			List<OrdersDTO> olists = odao.getList(userId);

			req.setAttribute("olists", olists);
			url = "/project/review/created.jsp";
			forward(req, resp, url);

		} else if (uri.indexOf("created_ok.do") != -1) {

			ReviewDTO dto = new ReviewDTO();

			dto.setReviewNo(dao.getMaxNum());
			dto.setOrderNo(req.getParameter("orderNo"));
			dto.setUserId(req.getParameter("userId"));
			dto.setSubject(req.getParameter("subject"));
			dto.setContent(req.getParameter("content"));
			dao.insertData(dto);

			url = cp + "/sreview/list.do";
			resp.sendRedirect(url);
		} else if (uri.indexOf("list.do") != -1) {

			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			String pageNum = req.getParameter("pageNum");

			int currentPage = 1;

			if (pageNum != null) {
				currentPage = Integer.parseInt(pageNum);
			} else {
				pageNum = "1";
			}

			if (searchValue != null) {
				if (req.getMethod().equalsIgnoreCase("get")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			} else {
				searchKey = "subject";
				searchValue = "";
			}

			int dataCount = dao.getDataCount(searchKey, searchValue);
			int numPerPage = 10;

			int totalPage = util.getPageCount(numPerPage, dataCount);

			if (currentPage > totalPage) {
				currentPage = totalPage;
			}

			int start = (currentPage - 1) * numPerPage + 1;
			int end = currentPage * numPerPage;
			System.out.println("¿©±â");
			List<ReviewDTO> lists = dao.getList(start, end, searchKey, searchValue);
			
			String params = "";
			if (!searchValue.equals("")) {
				params = "?searchKey=" + searchKey;
				params += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			String listUrl = "list.do" + params;
			String pageIndexList = util.pageIndexList(currentPage, totalPage, listUrl);
			String articleUrl = cp + "/sreview/article.do";

			if (params.equals("")) {
				articleUrl += "?pageNum=" + currentPage;
			} else {
				articleUrl += params + "&pageNum=" + currentPage;
			}
			
			req.setAttribute("lists", lists);
			req.setAttribute("articleUrl", articleUrl);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("params", params);
			 
			url = "/project/review/list.jsp";
			forward(req, resp, url);
			
		} else if (uri.indexOf("article.do") != -1) {
			
			int reviewNo = Integer.parseInt(req.getParameter("reviewNo"));
			String userId = req.getParameter("userId");
			
			String pageNum = req.getParameter("pageNum");
			
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");

			int currentPage = 1;

			if (pageNum != null) {
				currentPage = Integer.parseInt(pageNum);
			} else {
				pageNum = "1";
			}
			
			if (searchValue != null) {
				if (req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			} else {
				searchKey = "subject";
				searchValue = "";
			}
			
			ReviewDTO dto = dao.getReadData(reviewNo);
			
			dao.updateHitCount(reviewNo);
			
			
			
			String params = "pageNum=" + pageNum;

			if (!searchValue.equals("")) {
				params += "&searchKey=" + searchKey;
				params += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}
			
			String rePageNum = req.getParameter("rePageNum");

			int reCurrentPage = 1;

			if (rePageNum != null) {
				reCurrentPage = Integer.parseInt(rePageNum);
			} else {
				rePageNum = "1";
			}
			
			int dataCount = rdao.getDataCount(reviewNo);
			
			int numPerPage = 10;

			int totalPage = util.getPageCount(numPerPage, dataCount);

			if (reCurrentPage > totalPage) {
				reCurrentPage = totalPage;
			}

			int start = (reCurrentPage - 1) * numPerPage + 1;
			int end = reCurrentPage * numPerPage;
			
			List<ReplyDTO> rlists = rdao.getList(reviewNo, start, end);
			
			String reParams = "reviewNo=" + reviewNo;
			
			String listUrl = "article.do?" + reParams + "&" + params;
			
			String pageIndexList = rp.pageIndexList(currentPage, reCurrentPage, totalPage, listUrl);
			
			req.setAttribute("dto", dto);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("rePageNum", rePageNum);
			req.setAttribute("params", params);
			req.setAttribute("rlists", rlists);
			req.setAttribute("reParams", reParams);
			req.setAttribute("searchKey", searchKey);
			req.setAttribute("searchValue", searchValue);
			req.setAttribute("pageIndexList", pageIndexList);
			url = "/project/review/article.jsp";
			forward(req, resp, url);
			
		} else if (uri.indexOf("updated.do") != -1) {
			
			int reviewNo = Integer.parseInt(req.getParameter("reviewNo"));
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

			ReviewDTO dto = dao.getReadData(reviewNo);
		
			if (dto == null) {
				url = cp + "/sreview/list.do";
				resp.sendRedirect(url);
			}
			
			req.setAttribute("dto", dto);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("params", params);
			req.setAttribute("searchKey", searchKey);
			req.setAttribute("searchValue", searchValue);
			req.setAttribute("reviewNo", reviewNo);

			url = "/project/review/updated.jsp";
			forward(req, resp, url);
			
		} else if (uri.indexOf("updated_ok.do") != -1) {

			String pageNum = req.getParameter("pageNum");
			int reviewNo = Integer.parseInt(req.getParameter("reviewNo"));
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
			
			ReviewDTO dto = new ReviewDTO();
			
			dto.setSubject(req.getParameter("subject"));
			dto.setContent(req.getParameter("content"));
			dto.setReviewNo(reviewNo);
			dao.updateData(dto);
			url = cp + "/sreview/list.do?" + params;
			resp.sendRedirect(url);

		} else if(uri.indexOf("deleted_ok.do") != -1) {
			
			int reviewNo = Integer.parseInt(req.getParameter("reviewNo"));
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

			dao.deleteData(reviewNo);

			url = cp + "/sreview/list.do?" + params;
			resp.sendRedirect(url);
		}
	}
}
