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
		
		//�̹��� ����� ���
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
			// ��ǰ���������� �ٷ� �Ѿ�� ��� //////////////////////////////////////////
			// �ش� ��ǰ CART �� �߰�
			 
			int cartNo = cDao.getMaxNum()+1;
			String productNo = req.getParameter("productNo");
			String productName = req.getParameter("productName");
			String price = req.getParameter("price");
			String quantity = req.getParameter("quantity");
			
			CartDTO cDto = null;
			
			if(productNo!=null && !productNo.equals("")) {
				
				// ��ٱ��Ͽ� �ش� ��ǰ�� �̹� �ִ��� Ȯ���ϰ�
				int quan = cDao.getQuantity(userId, Integer.parseInt(productNo));
				
				// �ش� ��ǰ�� ������ ���� �߰�
				if(quan!=0) {
					
					cDao.updateQuantity(userId, Integer.parseInt(productNo), quan, 
							Integer.parseInt(quantity));
					
					
				// ��ٱ��Ͽ� �ش� ��ǰ�� ������ ����Ʈ �߰�	
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
			
			// CART ����Ʈ
			List<CartDTO> lists = cDao.getList(userId);
			
			int cartTotal = 0;
			String[] firstFileName = null;
			
			if(!lists.isEmpty()) {
				
				// CART ���� �� ��ǰ�� ù��° �̹��� ��θ� ��������
				int proNo[] = new int[lists.size()];
				for(int i=0;i<lists.size();i++) {
					proNo[i] = lists.get(i).getProductNo();
				}
				
				firstFileName = new String[proNo.length];
				firstFileName = fDao.getFirstFileName(proNo);
				
				// CART �� �ݾ� ��������
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
			
			// cart.jsp �� ������
			req.setAttribute("lists", lists);
			req.setAttribute("imagePath", imagePath);
			req.setAttribute("firstFileName", firstFileName); //�迭
			req.setAttribute("cartTotal", cartTotal);
			
			url = "/project/cart.jsp";
			forward(req, resp, url);
			
			
		}else if(uri.indexOf("pre.do")!=-1) {
			
			
			/////////////////////////////////////////////////////////////////////////////
			// cart.jsp ���� ������������ �Ѿ�� ��� ///////////////////////////////////
			
			int productNo = Integer.parseInt(req.getParameter("productNo"));
			int quantity = Integer.parseInt(req.getParameter("quantity"));
			
			// ������ 1���� �۰� ������ �ȵ�
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
