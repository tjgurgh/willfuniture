package com.page;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.files.FilesDAO;
import com.product.ProductDAO;
import com.product.ProductDTO;
import com.project.ppage.PageDAO;
import com.project.ppage.PageDTO;
import com.util.DBCPConn;
import com.util.MyUtil;

public class PageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}


	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url )
			throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher(url);

		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		String cp = req.getContextPath();


		Connection conn = DBCPConn.getConnection();
		ProductDAO pDao = new ProductDAO(conn);
		FilesDAO fDao = new FilesDAO(conn);
		PageDAO dao = new PageDAO(conn);

		//cp는 study까지인데 uri는 파일이름까지나옴
		// ex : study/servlet/test.jsp
		String uri = req.getRequestURI();//study/sboard/list.do

		String url;

		//이미지 저장된 경로
		String imagePath = cp + "/project/image";

		HttpSession session = req.getSession();
		//CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
		//String userId = info.getUserId();

		String root = getServletContext().getRealPath("/");
		String path = root + File.separator + "image" ;

		File f = new File(path);

		if(!f.exists())
			f.mkdirs();

		//가상주소 맵핑 web.xml 추가하고 서버 리스타트 후 

		if(uri.indexOf("shop.do")!=-1) {

			MyUtil myUtil = new MyUtil();

			String pageNum = req.getParameter("pageNum");
			String param="pageNum=" + pageNum;
			String cateNo = req.getParameter("categoryNo");			
			int status = 1; 
			
			
			int categoryNo;
			if(cateNo==null || cateNo.equals("")) {
				categoryNo = 1;
			}else {
				categoryNo = Integer.parseInt(cateNo);
			}
			
			
			//System.out.println(categoryNo);

			// 페이징 ===============================================================
			int currentPage=1;

			if(pageNum!=null && !pageNum.equals("")) {
				currentPage = Integer.parseInt(pageNum);

			}else {
				pageNum = "1";
			}

			int dataCount = pDao.getDataCount(categoryNo);
			int numPerPage = 4;
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);

			if(currentPage > totalPage) {
				currentPage = totalPage;
			}

			int start = (currentPage-1)*numPerPage+1;
			int end = currentPage*numPerPage;


			List<ProductDTO> pLists = pDao.getProductList(start, end, categoryNo);

			
			String[] firstFileName = null;
			String[] secondFileName = null;
			
			if(!pLists.isEmpty()) {

				// CART 에서 각 상품의 첫번째 이미지 경로만 가져오기
				int proNo[] = new int[pLists.size()];
				for(int i=0;i<pLists.size();i++) {
					proNo[i] = pLists.get(i).getProductNo();
				}
				
				// 첫번째 이미지 이름 배열
				firstFileName = new String[proNo.length];
				firstFileName = fDao.getFirstFileName(proNo);    
				secondFileName = new String[proNo.length];
				secondFileName = fDao.getSecondFileName(proNo);

			}


			String listUrl = "shop.do";

			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);

			req.setAttribute("imagePath", imagePath);

			int maxNum = dao.getMaxNum();

			req.setAttribute("pLists", pLists);
			req.setAttribute("firstFileName", firstFileName); //배열
			req.setAttribute("secondFileName", secondFileName); //배열
			req.setAttribute("imagePath", imagePath);
			req.setAttribute("pageIndexList", pageIndexList);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("start", start);
			req.setAttribute("end", end);
			req.setAttribute("totalPage", totalPage);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("maxNum", maxNum);
			req.setAttribute("params", param);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("categoryNo", categoryNo);
			req.setAttribute("status", status); 

			url = "/project/shop.jsp";
			forward(req, resp, url);		

		}else if(uri.indexOf("index.do")!=-1) {
			
			
			
			session = req.getSession();
			
			url = "/project/index.jsp";
			
			forward(req, resp, url);
			
		}

		
		
		
		



	}

}
