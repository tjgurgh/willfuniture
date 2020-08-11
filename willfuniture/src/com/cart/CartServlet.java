package com.cart;

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
import com.files.FilesDTO;
import com.member.CustomInfo;
import com.member.MemberDAO;
import com.member.MemberDTO;
import com.util.DBCPConn;
import com.util.DBConn;

public class CartServlet extends HttpServlet{

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
		MemberDAO dao = new MemberDAO(conn);
		CartDAO cDao = new CartDAO(conn);
		FilesDAO fDao = new FilesDAO(conn);
		//MyUtil myUtil = new MyUtil();
		
		
		String uri = req.getRequestURI();
		String url;
		
		//이미지 저장된 경로
		String imagePath = cp + "/project/image";
		
		HttpSession session = req.getSession();
		CustomInfo info = (CustomInfo)session.getAttribute("customInfo");

		if(info==null) {

			url = cp + "/member/login.do";
			resp.sendRedirect(url);
			return;

		}

		String userId = info.getUserId();

		
		if(uri.indexOf("list.do")!=-1) {
		
			/////////////////////////////////////////////////////////////////////////////
			// 상품페이지에서 바로 넘어온 경우 //////////////////////////////////////////
			// 해당 상품 CART 에 추가
			 
			int cartNo = cDao.getMaxNum()+1;
			String productNo = req.getParameter("productNo");
			String productName = req.getParameter("productName");
			String price = req.getParameter("price");
			String quantity = req.getParameter("quantity");
			
			CartDTO cDto = null;
			
			if(productNo!=null && !productNo.equals("")) {
				
				// 장바구니에 해당 상품이 이미 있는지 확인하고
				int quan = cDao.getQuantity(userId, Integer.parseInt(productNo));
				
				// 해당 상품이 있으면 수량 추가
				if(quan!=0) {
					
					cDao.updateQuantity(userId, Integer.parseInt(productNo), quan, 
							Integer.parseInt(quantity));
					
					
				// 장바구니에 해당 상품이 없으면 리스트 추가	
				}else {
					
					cDto = new CartDTO();
					
					cDto.setCartNo(cartNo);
					cDto.setUserId(userId);
					cDto.setProductNo(Integer.parseInt(productNo));
					cDto.setProductName(productName);
					cDto.setPrice(Integer.parseInt(price));
					cDto.setQuantity(Integer.parseInt(quantity));
					
					cDao.insertData(cDto);
					
				}
				
			}
			
			
			/////////////////////////////////////////////////////////////////////////////
			
			// CART 리스트
			List<CartDTO> lists = cDao.getList(userId);
			
			int cartTotal = 0;
			String[] firstFileName = null;
			
			if(!lists.isEmpty()) {
				
				// CART 에서 각 상품의 첫번째 이미지 경로만 가져오기
				int proNo[] = new int[lists.size()];
				for(int i=0;i<lists.size();i++) {
					proNo[i] = lists.get(i).getProductNo();
				}
				
				firstFileName = new String[proNo.length];
				firstFileName = fDao.getFirstFileName(proNo);
				
				// CART 총 금액 가져오기
				cartTotal = cDao.getCartTotal(userId);
				
			}
			session = req.getSession();
			
			info = new CustomInfo();
			CartDAO cdao= new CartDAO(conn);
			MemberDTO dto = dao.getReadData(userId);
			
			info.setUserId(dto.getUserId());
			info.setUserName(dto.getUserName());
			info.setCartCount(cdao.getCartCount(userId));
			
			session.setAttribute("customInfo", info);
			
			// cart.jsp 로 보낼것
			req.setAttribute("lists", lists);
			req.setAttribute("imagePath", imagePath);
			req.setAttribute("firstFileName", firstFileName); //배열
			req.setAttribute("cartTotal", cartTotal);
			
			url = "/project/cart.jsp";
			forward(req, resp, url);
			
			
		}else if(uri.indexOf("pre.do")!=-1) {
			
			
			/////////////////////////////////////////////////////////////////////////////
			// cart.jsp 에서 수량변경으로 넘어온 경우 ///////////////////////////////////
			
			int productNo = Integer.parseInt(req.getParameter("productNo"));
			int quantity = Integer.parseInt(req.getParameter("quantity"));
			
			// 수량이 1보다 작게 들어오면 안됨
			if(quantity<=0) {
				quantity = 1;
			}
			
			cDao.updateQuantity(userId, productNo, 0, quantity);
			
			url = cp + "/cart/list.do";
			resp.sendRedirect(url);
			
		}else if(uri.indexOf("deleted_ok.do")!=-1) {
			
			
			int productNo = Integer.parseInt(req.getParameter("productNo"));
			
			cDao.deleteData(userId, productNo);	
			
			url = cp + "/cart/list.do";
			resp.sendRedirect(url);
			
		}
		
		
		
		
		
		
		
		
	}
	
	
	
	

}
