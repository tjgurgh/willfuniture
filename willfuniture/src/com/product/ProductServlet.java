package com.product;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.CartDAO;
import com.cart.CartDTO;
import com.category.CategoryDAO;
import com.files.FilesDAO;
import com.files.FilesDTO;
import com.member.CustomInfo;
import com.util.DBCPConn;
import com.util.DBConn;


public class ProductServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url) 
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		
		req.setCharacterEncoding("utf-8");
		String cp = req.getContextPath();
		
		Connection conn = DBCPConn.getConnection();
		ProductDAO pDao = new ProductDAO(conn);
		FilesDAO fDao = new FilesDAO(conn);
		CategoryDAO cDao = new CategoryDAO(conn);
		CartDAO cartDao = new CartDAO(conn);
		//MyUtil myUtil = new MyUtil();
		
		
		String uri = req.getRequestURI();
		String url;

		//이미지 저장된 경로
		String imagePath = cp + "/project/image";
		
		HttpSession session = req.getSession();
		CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
		
		String userId = "";

		if(info!=null) {

			userId = info.getUserId();

		}

		

		
		
		
		if(uri.indexOf("article.do")!=-1) {
			
			
			int productNo = Integer.parseInt(req.getParameter("productNo")) ;
			
	
			
			int cartCount=0;
			if(userId!=null && !userId.equals("")) {
				cartCount = cartDao.getCartCount(userId);
			}
			
			ProductDTO pDto = pDao.getReadData(productNo);
			List<FilesDTO> lists = fDao.getList(productNo);
			String CategoryName = cDao.getCategoryName(pDto.getCategoryNo());
			
			
			req.setAttribute("cartCount", cartCount);
			req.setAttribute("imagePath", imagePath);
			req.setAttribute("pDto", pDto);
			req.setAttribute("lists", lists);
			req.setAttribute("CategoryName", CategoryName);
			req.setAttribute("proContent", pDto.getProContent());

			url = "/project/product-details.jsp";
			forward(req, resp, url);
			
			
		}
		
		
		
		
		
		
	}

}
