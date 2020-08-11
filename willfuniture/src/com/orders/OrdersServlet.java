package com.orders;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.CartDAO;
import com.cart.CartDTO;
import com.files.FilesDAO;
import com.member.CustomInfo;
import com.member.MemberDAO;
import com.member.MemberDTO;
import com.util.DBCPConn;
import com.util.DBConn;

public class OrdersServlet extends HttpServlet{

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
		MemberDAO mDao = new MemberDAO(conn);
		OrdersDAO oDao = new OrdersDAO(conn);
		CartDAO cDao = new CartDAO(conn);

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










		if(uri.indexOf("checkout.do")!=-1) {

			int cartTotal = Integer.parseInt(req.getParameter("cartTotal"));
			int bonusPoint = (int)(cartTotal*0.05);
			
			MemberDTO mDto = mDao.getReadData(userId);

			req.setAttribute("cartTotal", cartTotal);
			req.setAttribute("bonusPoint", bonusPoint);
			req.setAttribute("mDto", mDto);

			url = "/project/checkout.jsp";
			forward(req, resp, url);

		}else if(uri.indexOf("checkout_ok.do")!=-1) {

			
			String addr = req.getParameter("addr");
			String addrDetail = req.getParameter("addrDetail");
			String options = req.getParameter("options");
			
			int total = Integer.parseInt(req.getParameter("total"));
			int totPoint = Integer.parseInt(req.getParameter("totPoint"));
			int bonusPoint = Integer.parseInt(req.getParameter("bonusPoint"));
			String subusePoint = req.getParameter("usePoint");
			
			int usePoint = 0;
			if(subusePoint!=null && subusePoint.equals("")) {
				usePoint = Integer.parseInt(subusePoint);
			}
			

			// CART 리스트
			List<CartDTO> cLists = cDao.getList(userId);

			// ORDERS 테이블에 INSERT
			for(CartDTO dto : cLists) {

				OrdersDTO oDto = new OrdersDTO();

				oDto.setOrderNo(oDao.getOrderNo());
				oDto.setUserId(userId);
				oDto.setProductNo(dto.getProductNo());
				oDto.setProductName(dto.getProductName());
				oDto.setPrice(dto.getPrice());
				oDto.setOrderQuantity(dto.getQuantity());
				oDto.setOptions(options);
				oDto.setAddr(addr);
				oDto.setAddrDetail(addrDetail);
				oDto.setProgress("결제완료");

				oDao.insertData(oDto);
				
				// CART 리스트 지우기
				cDao.deleteData(userId, dto.getProductNo());

			}
			
			
			//========================================================================
			
			// Member 테이블 포인트 처리
			// 사용한포인트(usePoint)만큼 차감 + 적립금(bonusPoint) 적립
			
			int point = totPoint+bonusPoint-usePoint;
			mDao.changePoint(point, userId);
			
			

			req.setAttribute("total", total);
			req.setAttribute("point", point);

			url = "/project/checkout_ok.jsp";
			forward(req, resp, url);

		}else if(uri.indexOf("orderHistory.do")!=-1) {

			List<OrdersDTO> lists = oDao.getList(userId);

			req.setAttribute("lists", lists);

			url = "/project/orderHistory.jsp";
			forward(req, resp, url);

		}else if(uri.indexOf("return_ok.do")!=-1) {

			url = cp +"/project/main.jsp";
			forward(req, resp, url);

		}











	}




}
