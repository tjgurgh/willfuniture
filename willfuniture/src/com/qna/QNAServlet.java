package com.qna;

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
import javax.servlet.http.HttpSession;

import com.member.CustomInfo;
import com.util.DBCPConn;
import com.util.MyUtil;

public class QNAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String cp = req.getContextPath();
		Connection conn = DBCPConn.getConnection();
		QNADAO dao = new QNADAO(conn);
		MyUtil util = new MyUtil();

		String uri = req.getRequestURI();
		String url;

		if (uri.indexOf("created.do") != -1) {
			url = "/project/qna/created.jsp";
			this.forward(req, resp, url);

		} else if (uri.indexOf("created_ok.do") != -1) {
			
			HttpSession session = req.getSession();
			CustomInfo info = (CustomInfo)session.getAttribute("customInfo");


			if(info==null) {

				url = cp + "/member/login.do";
				resp.sendRedirect(url);
				return;

			}

			String userId = info.getUserId();

			QNADTO dto = new QNADTO();

			dto.setQno(dao.getMaxNum());
			dto.setQnaCategory(req.getParameter("qnaCategory"));
			dto.setUserId(req.getParameter("userId"));
			dto.setSubject(req.getParameter("subject"));
			dto.setQnaContent(req.getParameter("qnaContent"));
			dao.insertData(dto);

			url = cp + "/sqna/myList.do?userId="+userId;
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
				searchKey = "";
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

			List<QNADTO> lists = dao.getList(start, end, searchKey, searchValue);

			String params = "";
			if (!searchValue.equals("")) {
				params = "?searchKey=" + searchKey;
				params += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			String listUrl = "list.do" + params;
			String pageIndexList = util.pageIndexList(currentPage, totalPage, listUrl);
			String articleUrl = cp + "/sqna/article.do";

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

			url = "/project/qna/list.jsp";
			this.forward(req, resp, url);

		} else if (uri.indexOf("article.do") != -1) {

			int qno = Integer.parseInt(req.getParameter("qno"));

			String pageNum = req.getParameter("pageNum");
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");

			if (searchValue != null) {
				if (req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			} else {
				searchKey = "";
				searchValue = "";
			}

			QNADTO dto = dao.getReadData(qno);
			dao.updateHitCount(qno);

			/*dto.setQnaContent(dto.getQnaContent().replaceAll("\n", "<br/>"));*/

			String params = "pageNum=" + pageNum;

			if (!searchValue.equals("")) {
				params += "&searchKey=" + searchKey;
				params += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			req.setAttribute("dto", dto);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("params", params);

			url = "/project/qna/article.jsp";
			this.forward(req, resp, url);

			
			
		} else if (uri.indexOf("updated.do") != -1) {
			int qno = Integer.parseInt(req.getParameter("qno"));
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

			QNADTO dto = dao.getReadData(qno);
			if (dto == null) {
				url = cp + "/sqna/list.do";
				resp.sendRedirect(url);
			}

			req.setAttribute("dto", dto);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("params", params);
			req.setAttribute("searchKey", searchKey);
			req.setAttribute("searchValue", searchValue);
			req.setAttribute("qno", qno);

			url = "/project/qna/updated.jsp";
			this.forward(req, resp, url);

		} else if (uri.indexOf("updated_ok.do") != -1) {
			
			
			HttpSession session = req.getSession();
			CustomInfo info = (CustomInfo)session.getAttribute("customInfo");


			if(info==null) {

				url = cp + "/member/login.do";
				resp.sendRedirect(url);
				return;

			}

			String userId = info.getUserId();

			String pageNum = req.getParameter("pageNum");
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");

			int qno = Integer.parseInt(req.getParameter("qno"));

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

			QNADTO dto = new QNADTO();
			dto.setQnaCategory(req.getParameter("qnaCategory"));
			dto.setSubject(req.getParameter("subject"));
			dto.setQnaContent(req.getParameter("qnaContent"));
			dto.setQno(qno);
			dao.updateData(dto);
/*			url = cp + "/sqna/list.do?" + params;
			resp.sendRedirect(url);*/
			
			
			if(userId!="admin") {
				url = cp + "/sqna/myList.do?userId=" + userId;
				resp.sendRedirect(url);
				return;
				} else {
					url = cp + "/sqna/list.do?" + params;
					resp.sendRedirect(url);
					
				}
			

			
			
			
		
			
			
		} else if (uri.indexOf("deleted_ok.do") != -1) {
			
			
		/*	HttpSession session = req.getSession();
			CustomInfo info = (CustomInfo)session.getAttribute("customInfo");*/
			
			String userId=req.getParameter("userId");

/*
			if(info==null) {

				url = cp + "/member/login.do";
				resp.sendRedirect(url);
				return;

			}

			String userId = info.getUserId();*/
			
			
			

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

			dao.deleteData(qno);
			String params = "pageNum=" + pageNum;

			if (!searchValue.equals("")) {
				params += "&searchKey=" + searchKey;
				params += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}


			if(userId.equals("admin") || userId=="admin") {
				url = cp + "/sqna/list.do";
				resp.sendRedirect(url);
			return;
			} else {

				url = cp + "/sqna/myList.do?userId=" + userId;
				resp.sendRedirect(url);
			}
			
			
			
		} else if (uri.indexOf("reply.do") != -1) {

			int qno = Integer.parseInt(req.getParameter("qno"));

			String pageNum = req.getParameter("pageNum");
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");

			QNADTO dto = dao.getReadData(qno);

			if (searchValue != null) {
				if (req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			} else {
				searchKey = "";
				searchValue = "";
			}

			String params = "pageNum=" + pageNum;

			if (!searchValue.equals("")) {
				params += "&searchKey=" + searchKey;
				params += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			req.setAttribute("pageNum", pageNum);
			req.setAttribute("params", params);
			req.setAttribute("searchKey", searchKey);
			req.setAttribute("searchValue", searchValue);
			req.setAttribute("qno", qno);
			req.setAttribute("dto", dto);

			url = "/project/qna/reply.jsp";
			forward(req, resp, url);

		} else if (uri.indexOf("reply_ok.do") != -1) {

			String pageNum = req.getParameter("pageNum");
			String searchKey = req.getParameter("searchKey");
			String searchValue = req.getParameter("searchValue");
			int qno = Integer.parseInt(req.getParameter("qno"));

			if (searchValue != null) {
				if (req.getMethod().equalsIgnoreCase("GET")) {
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			} else {
				searchKey = "";
				searchValue = "";
			}

			String params = "pageNum=" + pageNum;
			if (!searchValue.equals("")) {
				params += "&searchKey=" + searchKey;
				params += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			QNADTO dto = new QNADTO();

			dto.setQno(dao.getMaxNum());
			dto.setUserId(req.getParameter("userId"));
			dto.setSubject(req.getParameter("subject"));
			dto.setQnaCategory(req.getParameter("qnaCateogry"));
			dto.setQnaContent(req.getParameter("qnaContent"));
			dto.setGroupNo(Integer.parseInt(req.getParameter("groupNo")));
			dto.setParentNo(Integer.parseInt(req.getParameter("qno")));
			dao.insertData(dto);

			dao.updateStatus(qno);

			url = cp + "/sqna/list.do?" + params;
			resp.sendRedirect(url);

		} else if (uri.indexOf("myList.do") != -1) {

			String userId = req.getParameter("userId");
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
				searchKey = "";
				searchValue = "";
			}

			// 일반 List랑 별도의 데이터를 가져와야 한다.
			int dataCount = dao.getMyDataCount(userId);

			int numPerPage = 10;

			int totalPage = util.getPageCount(numPerPage, dataCount);

			if (currentPage > totalPage) {
				currentPage = totalPage;
			}

			int start = (currentPage - 1) * numPerPage + 1;
			int end = currentPage * numPerPage;

			List<QNADTO> lists = dao.myQNAList(userId, start, end);

			String params = "?userId=" + userId;
			if (!searchValue.equals("")) {
				params += "?searchKey=" + searchKey;
				params += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			String listUrl = "myList.do" + params;
			String pageIndexList = util.pageIndexList(currentPage, totalPage, listUrl);
			String articleUrl = cp + "/sqna/article.do";

			if (params.equals("")) {
				articleUrl += "&pageNum=" + currentPage;
			} else {
				articleUrl += params + "&pageNum=" + currentPage;
			}

			req.setAttribute("lists", lists);
			req.setAttribute("articleUrl", articleUrl);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("userId", userId);

			url = "/project/qna/myList.jsp";
			forward(req, resp, url);
		} else if (uri.indexOf("noList.do") != -1) {

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
				searchKey = "";
				searchValue = "";
			}

			
			int dataCount = dao.getNoDataCount();
			int numPerPage = 10;

			int totalPage = util.getPageCount(numPerPage, dataCount);

			if (currentPage > totalPage) {
				currentPage = totalPage;
			}

			int start = (currentPage - 1) * numPerPage + 1;
			int end = currentPage * numPerPage;
			
			String params = "";
			if (!searchValue.equals("")) {
				params = "?searchKey=" + searchKey;
				params += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
			}

			String listUrl = "noList.do" + params;
			String pageIndexList = util.pageIndexList(currentPage, totalPage, listUrl);
			String articleUrl = cp + "/sqna/article.do";

			if (params.equals("")) {
				articleUrl += "?pageNum=" + currentPage;
			} else {
				articleUrl += params + "&pageNum=" + currentPage;
			}
			

			List<QNADTO> lists = dao.noList(start, end);
			req.setAttribute("lists", lists);
			req.setAttribute("articleUrl", articleUrl);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("pageNum", pageNum);

			url = "/project/qna/noList.jsp";
			forward(req, resp, url);
		}
	}
}
