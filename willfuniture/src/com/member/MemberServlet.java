package com.member;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.CartDAO;
import com.util.DBCPConn;

public class MemberServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	

	protected void forward(HttpServletRequest req, HttpServletResponse resp, String url)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String cp = req.getContextPath();
		
		Connection conn = DBCPConn.getConnection();
		MemberDAO dao = new MemberDAO(conn);
		
		String uri = req.getRequestURI();
		String url;

//------------------------------------------------------------------------------------------
		if(uri.indexOf("signIn.do")!=-1) {
			
			url = "/project/signIn.jsp";
			forward(req, resp, url);

//------------------------------------------------------------------------------------------			

		}else if(uri.indexOf("signIn_ok.do")!=-1) {

			MemberDTO dto = new MemberDTO();
			
			dto.setUserId(req.getParameter("userId"));
			dto.setUserPwd(req.getParameter("userPwd"));
			dto.setUserName(req.getParameter("userName"));
			dto.setUserBirth(req.getParameter("userBirth"));
			dto.setUserTel(req.getParameter("userTel"));
			dto.setUserEmail(req.getParameter("userEmail"));
			dto.setAddr(req.getParameter("addr"));
			dto.setAddrDetail(req.getParameter("addrDetail"));
			dto.setTotPoint(Integer.parseInt(req.getParameter("totPoint")));
			dto.setAccumPrice(Integer.parseInt(req.getParameter("accumPrice")));
			
			dao.insertData(dto);

			url = cp + "/project/main.jsp";
			resp.sendRedirect(url);

//------------------------------------------------------------------------------------------			

			}else if(uri.indexOf("login.do")!=-1) {

			url = "/project/login.jsp";
			forward(req, resp, url);

//------------------------------------------------------------------------------------------			

}else if(uri.indexOf("login_ok.do")!=-1) {
				
				String userId = req.getParameter("userId");
				String userPwd = req.getParameter("userPwd");
				String status = req.getParameter("status");
				int cartCount=0;
				
				MemberDTO dto = dao.getReadData(userId);
				
				
				
		
				if(dto==null || !dto.getUserPwd().equals(userPwd)) {
					
					req.setAttribute("message",
							"아이디 또는 패스워드를 정확히 입력하세요.");
					
					url = "/project/login.jsp";
					forward(req, resp, url);
					
					return;
					
				}
				
				if(dto.getStatus()!=1) {
					
					req.setAttribute("message", "탈퇴한 회원입니다.");
					
					url = "/project/login.jsp";
					forward(req, resp, url);
					
					return;
				}
	

			HttpSession session = req.getSession();
			
			CustomInfo info = new CustomInfo();
			CartDAO cdao= new CartDAO(conn);
			
			info.setUserId(dto.getUserId());
			info.setUserName(dto.getUserName());
			info.setCartCount(cdao.getCartCount(userId));
			
			session.setAttribute("customInfo", info);
			
			url = cp + "/project/main.jsp";
			resp.sendRedirect(url);


//------------------------------------------------------------------------------------------			

			}else if(uri.indexOf("logout.do")!=-1) {
				
				HttpSession session = req.getSession();
				
				session.removeAttribute("customInfo");
				session.invalidate();
				
				url = cp + "/project/main.jsp";
				resp.sendRedirect(url);
				
				
			}else if(uri.indexOf("memberUpdate.do")!=-1)  {
				HttpSession session = req.getSession();
				CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
				
				String userId = info.getUserId();
				
				MemberDTO dto = dao.getReadData(userId);
				
				req.setAttribute("dto", dto);
		
				url = "/project/memberUpdate.jsp";
				forward(req, resp, url);

				
				
				
				
				
			}else if(uri.indexOf("update_ok.do")!=-1) {//수정완료 버튼 누르면 수정결과 반영

				
				HttpSession session = req.getSession();
				CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
				
				String userId = info.getUserId();
				
				String userPwd = req.getParameter("userPwd");
				String userTel = req.getParameter("userTel");
				String userEmail = req.getParameter("userEmail");
				String addr = req.getParameter("addr");
				String addrDetail = req.getParameter("addrDetail");
				
				MemberDTO dto = new MemberDTO();
				
				dto.setUserName(req.getParameter("userName"));
				dto.setUserBirth(req.getParameter("userBirth"));
				dto.setUserPwd(req.getParameter("userPwd"));
				dto.setUserTel(req.getParameter("userTel"));
				dto.setUserEmail(req.getParameter("userEmail"));
				dto.setAddr(req.getParameter("addr"));
				dto.setAddrDetail(req.getParameter("addrDetail"));
				dto.setUserId(req.getParameter("userId"));
				
				dao.updateData(dto);
				
//				session.setAttribute("customInfo", info)
				
				url = cp +"/project/main.jsp";
				resp.sendRedirect(url);
			}else if(uri.indexOf("deleteMember.do")!=-1) {


				HttpSession session = req.getSession();
				CustomInfo info = (CustomInfo)session.getAttribute("customInfo");

				String userId = info.getUserId();

				MemberDTO dto = dao.getReadData(userId);

				req.setAttribute("dto", dto);

				url = "/project/deleteMember.jsp";
				forward(req, resp, url);

				//--------------------------------------------------------------------------------------		

			}else if(uri.indexOf("delete_ok.do")!=-1) {//수정완료 버튼 누르면 수정결과 반영

				HttpSession session = req.getSession();
				CustomInfo info = (CustomInfo)session.getAttribute("customInfo");

				String userId = info.getUserId();

				MemberDTO dto = new MemberDTO();


				dto.setUserId(req.getParameter("userId"));

				dao.changeSatus(dto);

				url = cp +"/member/logout.do";
				resp.sendRedirect(url);	

			}else if(uri.indexOf("idCheck.do")!=-1) {

				String userId = req.getParameter("userId");
				//int satus = Integer.parseInt(req.getParameter("status"));

				boolean result = dao.idCheck(userId);


				if(result==true) {

					req.setAttribute("message",
							userId +" (은)는 사용 중인 Id 입니다. <br/> 다른 아이디를 입력해주세요.");


					url = "/project/idCheck.jsp";
					forward(req, resp, url);

					return;

					/*			}else if(result==true && dto.getStatus()==0 ){
					req.setAttribute("message",
							userId +" (은)는 탈퇴 한 Id 입니다. <br/> 다른 아이디를 입력해주세요.");


					url = "/project/idCheck.jsp";
					forward(req, resp, url);

					return;
					 */				

				}else {
					req.setAttribute("userId", userId);

					url = "/project/idCheck.jsp";
					forward(req, resp, url);

				}


}else if(uri.indexOf("searchpw.do")!=-1) {
				
				url = "/project/searchpw.jsp";
				forward(req, resp, url);
				
				
				
}else if(uri.indexOf("searchpw_ok.do")!=-1) {
	
	String userId = req.getParameter("userId");
	String userTel = req.getParameter("userTel");

	MemberDTO dto = dao.getReadData(userId);
	
	if(dto==null||!dto.getUserTel().equals(userTel)) {
		
		req.setAttribute("message",
				"회원정보가 존재하지 않습니다.");
		
		url = "/project/login.jsp";
		forward(req, resp, url);
		
		return;
		
	}else {
		String str = "비밀번호는 [ " + dto.getUserPwd() + " ] 입니다.";
		
		req.setAttribute("message", str);
		
		url = "/project/login.jsp";
		forward(req, resp, url);
	}
	
}else if(uri.indexOf("mypage.do")!=-1)  {
				HttpSession session = req.getSession();
				CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
				
				String userId = info.getUserId();
				
				MemberDTO dto = dao.getReadData(userId);
				
				int orderCount=dao.getOrderData(userId);
				
				int totPoint = dto.getTotPoint();
				String grade;
				if(0<=totPoint && totPoint<50000) {
					grade = "일반회원";
				}else if(50000<=totPoint && totPoint<100000) {
					grade = "VIP회원";
				}else {
					grade = "VVIP회원";
				}

				req.setAttribute("grade", grade);
				req.setAttribute("dto", dto);
				req.setAttribute("orderCount",orderCount );
		
				url = "/project/mypage.jsp";
				forward(req, resp, url);
				
			}

		
		
		
		
	
		
	}
}
