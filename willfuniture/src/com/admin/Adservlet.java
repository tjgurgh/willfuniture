package com.admin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.util.DBCPConn;
import com.util.FileManager;
import com.util.MyUtil;

public class Adservlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	

	protected void forward(HttpServletRequest req, HttpServletResponse resp,
			String url) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);		
	}
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("utf-8");
		String cp = req.getContextPath();
		
		Connection conn = DBCPConn.getConnection();
		AdminDAO dao = new AdminDAO(conn);
		
		String uri = req.getRequestURI();
		String url;
		
		String root = getServletContext().getRealPath("/");
		String path = root + File.separator + "project" +
		File.separator + "image";
		
		File f = new File(path);
		
		if(!f.exists())
			f.mkdirs();
		
		
		
		
if(uri.indexOf("adminindex.do")!=-1) {
			
			
			
			url = "/project/admin/index.jsp";
			forward(req, resp, url);
			
			
		
}else if(uri.indexOf("addProduct.do")!=-1) {
			
			url = "/project/admin/add-product-assets.jsp";
			forward(req, resp, url);
			
			
}  else if(uri.indexOf("addProduct_ok.do")!=-1) {
			
			String encType = "UTF-8";
			int maxSize = 10*1024*1024;
			
			MultipartRequest mr = new MultipartRequest(req,path,maxSize,encType,
					new DefaultFileRenamePolicy());


			String productName = mr.getParameter("productName");
			
			

				int maxNum1 = dao.getProMaxNum();
				int maxNum2 = dao.getFileMaxNum();
				int ProductNo = maxNum1+1;
								
				if(mr.getFile("upload1")!=null) {
					
				
				AdminDTO dto1 = new AdminDTO();
				
				dto1.setProductNo(ProductNo);
				dto1.setProductName(productName);
				dto1.setPrice(Integer.parseInt(mr.getParameter("price")));
				dto1.setBonusPoint(Integer.parseInt(mr.getParameter("price")));
				dto1.setCategoryNo(Integer.parseInt(mr.getParameter("categoryNo")));
				dto1.setProContent(mr.getParameter("proContent"));
				
				dao.insertProData(dto1);
				
				
				AdminDTO dto2 = new AdminDTO();
				
				dto2.setFnum(maxNum2+1);
				dto2.setProductNo(ProductNo);
				dto2.setSaveFileName(mr.getFilesystemName("upload1")+"-1");
				dto2.setOriginalFileName(mr.getOriginalFileName("upload1")+"-1");
				
				dao.insertProFile(dto2);
			
			} 
				
				if(mr.getFile("upload2")!=null) {
					
				int maxNum3 = dao.getFileMaxNum();
				AdminDTO dto3 = new AdminDTO();
				
				dto3.setFnum(maxNum3+1);
				dto3.setProductNo(ProductNo);
				dto3.setSaveFileName(mr.getFilesystemName("upload2")+"-2");
				dto3.setOriginalFileName(mr.getOriginalFileName("upload2")+"-2");
				
				dao.insertProFile(dto3);
				
			}
				if(mr.getFile("upload3")!=null) {
					
					
				int maxNum4 = dao.getFileMaxNum();
				AdminDTO dto4 = new AdminDTO();
				
				dto4.setFnum(maxNum4+1);
				dto4.setProductNo(ProductNo);
				dto4.setSaveFileName(mr.getFilesystemName("upload3")+"-3");
				dto4.setOriginalFileName(mr.getOriginalFileName("upload3")+"-3");
				
				dao.insertProFile(dto4);
				
			} 
				if(mr.getFile("upload4")!=null) {
					
				int maxNum5 = dao.getFileMaxNum();
				AdminDTO dto5 = new AdminDTO();
				
				dto5.setFnum(maxNum5+1);
				dto5.setProductNo(ProductNo);
				dto5.setSaveFileName(mr.getFilesystemName("upload4")+"-4");
				dto5.setOriginalFileName(mr.getOriginalFileName("upload4")+"-4");
				
				dao.insertProFile(dto5);
				
			}
		
	
			url = cp +"/master/productList.do"; 
			resp.sendRedirect(url);

			
} else if(uri.indexOf("productList.do")!=-1) {	
			
			MyUtil myUtil = new MyUtil();
			String pageNum = req.getParameter("pageNum");
			String param="pageNum=" + pageNum;
			
			int currentPage=1;
			
			if(pageNum!=null) {
				currentPage = Integer.parseInt(pageNum);
					
			}else {
				pageNum = "1";
			}
			
			int dataCount = dao.getDataCount();
			int numPerPage = 10;
			int totalPage = myUtil.getPageCount(numPerPage, dataCount);
			
			if(currentPage > totalPage) {
				currentPage = totalPage;
			}
			
				int start = (currentPage-1)*numPerPage+1;
				int end = currentPage*numPerPage;
			
			List<AdminDTO> lists = dao.getProductList(start,end);	

			String listUrl = "productList.do";
			
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
			
				String deletePath = cp + "/master/delProduct.do";
				
				String imagePath = cp + "/project/image";
				req.setAttribute("imagePath", imagePath);
				
				int maxNum = dao.getProMaxNum();
				
				req.setAttribute("lists", lists);
				req.setAttribute("listUrl", listUrl);
				req.setAttribute("deletePath", deletePath);
				req.setAttribute("pageIndexList", pageIndexList);
				req.setAttribute("dataCount", dataCount);
				req.setAttribute("start", start);
				req.setAttribute("end", end);
				req.setAttribute("totalPage", totalPage);
				req.setAttribute("currentPage", currentPage);
				req.setAttribute("maxProNum", maxNum);
				req.setAttribute("params", param);
				req.setAttribute("pageNum", pageNum);

				url = "/project/admin/product-assets.jsp";
				forward(req, resp, url);
			

} else if(uri.indexOf("editProduct.do")!=-1) {
			
			String pageNum = req.getParameter("pageNum");
			
			int productNo = Integer.parseInt(req.getParameter("productNo"));
			
						
			AdminDTO dto = dao.getReadData(productNo);
			
			if(dto==null) {
				
				url = cp + "/master/productList.do";
				resp.sendRedirect(url);
			}

			
			req.setAttribute("dto", dto);
			req.setAttribute("productNo", productNo);
			req.setAttribute("price",dto.getPrice());
			req.setAttribute("productName", dto.getProductName());
			req.setAttribute("categoryNo", dto.getCategoryNo());
			req.setAttribute("proContent",dto.getProContent());
			req.setAttribute("pageNum", pageNum);
			url = "/project/admin/edit-product-assets.jsp";
			
			forward(req, resp, url);
		
			
} else if(uri.indexOf("editProduct_ok.do")!=-1) {
	
				
			String encType = "UTF-8";
			int maxSize = 10*1024*1024;
			
			MultipartRequest mr = new MultipartRequest(req,path,maxSize,encType,
					new DefaultFileRenamePolicy());
			
			int productNo = Integer.parseInt(mr.getParameter("productNo"));

			String productName = mr.getParameter("productName");
			String pageNum = mr.getParameter("pageNum");
			
			String price = mr.getParameter("price");
			String categoryNo = mr.getParameter("categoryNo");
			String proContent = mr.getParameter("proContent");
			
			
			int Fnum= dao.findFnum(productNo);
								
				if(mr.getFile("upload1")!=null) {
					
				String 	getSaveFileName = dao.findSaveFileName(Fnum);
				String saveFileName=getSaveFileName.replaceAll(".jpg-1", ".jpg");
					
				FileManager.doFileDelete(saveFileName, path);
				
				
				AdminDTO dto1 = new AdminDTO();
				dto1.setProductName(productName);
				dto1.setPrice(Integer.parseInt(mr.getParameter("price")));
				dto1.setProductNo(productNo);
				dto1.setCategoryNo(Integer.parseInt(mr.getParameter("categoryNo")));
				dto1.setProContent(mr.getParameter("proContent"));
				
				dao.updateProData(dto1);
				
				
				
				
				AdminDTO dto2 = new AdminDTO();
				
				dto2.setFnum(Fnum);
				dto2.setProductNo(productNo);
				dto2.setSaveFileName(mr.getFilesystemName("upload1")+"-1");
				dto2.setOriginalFileName(mr.getOriginalFileName("upload1")+"-1");
				
				dao.updateProFile(dto2);
			
			} 
				
				if(mr.getFile("upload2")!=null) {
					
					String 	getSaveFileName = dao.findSaveFileName(Fnum+1);
					
					String saveFileName=getSaveFileName.replaceAll(".jpg-2", ".jpg");
					
					FileManager.doFileDelete(saveFileName, path);
						

				AdminDTO dto3 = new AdminDTO();
				
				dto3.setFnum(Fnum+1);
				dto3.setProductNo(productNo);
				dto3.setSaveFileName(mr.getFilesystemName("upload2")+"-2");
				dto3.setOriginalFileName(mr.getOriginalFileName("upload2")+"-2");
				
				dao.updateProFile(dto3);
				
			} else {
				
				String 	getSaveFileName = dao.findSaveFileName(Fnum+1);
				
				String saveFileName=getSaveFileName.replaceAll(".jpg-2", ".jpg");
				FileManager.doFileDelete(saveFileName, path);
				
				AdminDTO dto3 = new AdminDTO();
				
				dto3.setFnum(Fnum+1);
				dto3.setProductNo(productNo);
				dto3.setSaveFileName(mr.getFilesystemName("upload2")+"-2");
				dto3.setOriginalFileName(mr.getOriginalFileName("upload2")+"-2");
				
				dao.updateProFile(dto3);
			
			}
								
				if(mr.getFile("upload3")!=null) {
					
					String 	getSaveFileName = dao.findSaveFileName(Fnum+2);
					
					String saveFileName=getSaveFileName.replaceAll(".jpg-3", ".jpg");
					FileManager.doFileDelete(saveFileName, path);
					
					
				AdminDTO dto4 = new AdminDTO();
				
				dto4.setFnum(Fnum+2);
				dto4.setProductNo(productNo);
				dto4.setSaveFileName(mr.getFilesystemName("upload3")+"-3");
				dto4.setOriginalFileName(mr.getOriginalFileName("upload3")+"-3");
				
				dao.updateProFile(dto4);
				
				} else {
					
					String 	getSaveFileName = dao.findSaveFileName(Fnum+2);
					
					String saveFileName=getSaveFileName.replaceAll(".jpg-3", ".jpg");
					FileManager.doFileDelete(saveFileName, path);
					
					AdminDTO dto4 = new AdminDTO();
					
					dto4.setFnum(Fnum+2);
					dto4.setProductNo(productNo);
					dto4.setSaveFileName(mr.getFilesystemName("upload3")+"-3");
					dto4.setOriginalFileName(mr.getOriginalFileName("upload3")+"-3");
					
					dao.updateProFile(dto4);
				
				}
				if(mr.getFile("upload4")!=null) {
					
					String 	getSaveFileName = dao.findSaveFileName(Fnum+3);
					
					String saveFileName=getSaveFileName.replaceAll(".jpg-4", ".jpg");
					FileManager.doFileDelete(saveFileName, path);
					
				
				AdminDTO dto5 = new AdminDTO();
				
				dto5.setFnum(Fnum+3);
				dto5.setProductNo(productNo);
				dto5.setSaveFileName(mr.getFilesystemName("upload4")+"-4");
				dto5.setOriginalFileName(mr.getOriginalFileName("upload4")+"-4");
				
				dao.updateProFile(dto5);
				
				} else {
					
					String 	getSaveFileName = dao.findSaveFileName(Fnum+3);
					
					String saveFileName=getSaveFileName.replaceAll(".jpg-4", ".jpg");
					FileManager.doFileDelete(saveFileName, path);
					
					AdminDTO dto5 = new AdminDTO();
					
					dto5.setFnum(Fnum+3);
					dto5.setProductNo(productNo);
					dto5.setSaveFileName(mr.getFilesystemName("upload4")+"-4");
					dto5.setOriginalFileName(mr.getOriginalFileName("upload4")+"-4");
					
					dao.updateProFile(dto5);
				
				}
		
				
				
				
				
				
				
				req.setAttribute("pageNum", pageNum);	
	
				url = cp +"/master/productList.do?pageNum=" + pageNum;
				resp.sendRedirect(url);
			
			
			
			
			
			
			

} else if(uri.indexOf("delProduct.do")!=-1) {
			
	String pageNum = req.getParameter("pageNum");
	int productNo = Integer.parseInt(req.getParameter("productNo"));
	
	
	dao.deleteProData(productNo);
	
	
	
	
	req.setAttribute("pageNum", pageNum);
	
	
	url = cp +"/master/productList.do?pageNum=" + pageNum;
	resp.sendRedirect(url);
	
	
		}
			
			
		
			
		
		
		
		
		
		
		
		
		
			
		}

	}
