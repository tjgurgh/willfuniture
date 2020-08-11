package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class FileManager {
	
	//파일 다운로드
	//saveFileName :서버에 저장된 파일명
	//originalFileName :클라이언트가 업로드한 파일명
	//path :서버에 저장된 위치 
	
	public static boolean doFileDownload(HttpServletResponse response,
			String saveFileName, String originalFileName, String path) {
		
		try {
			String filePath = path + File.separator + saveFileName;
			
			if(originalFileName==null||originalFileName.equals("")) {//자바에서 스트링은 널을 비교할때 두번주는게 안전
						
				originalFileName = saveFileName;				
			}
			
			//파일을 다운 받아 저장시에 파일이름생성시 한글깨짐 방지
			//업로드시에는 euc..과 ISO...위치 바꾸기
			//헤더먼저 내려보내고 그다음에 데이터 내려보내기
			
			originalFileName = new String(originalFileName.getBytes("euc-kr"),"ISO-8859-1");

			
			File f = new File(filePath);
			
			if(!f.exists())
				return false;
		
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition",
					"attachment;fileName=" + originalFileName);
			
			
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(f));//파일을 읽어 버퍼안에 넣고
			
			OutputStream out = response.getOutputStream(); //읽어서 내보내기
			
			int n;
			
			byte[] bytes = new byte[4096];
			
			while((n=bis.read(bytes,0,4096))!=-1) {
				out.write(bytes,0,n);
			}
			
			out.flush(); //버퍼를 꽉채우지 못한 데이터 강제 출력
			out.close();
			bis.close();
				
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		return true;
		
	}
	
	
	//파일삭제
	
	public static void doFileDelete(String fileName, String path) {
		
		try {
			
			String filePath= path + File.separator + fileName;
			
			File f = new File(filePath);
			
			if(f.exists()) 
				f.delete();//파일삭제
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}		
		
	}
	
	
	
	

}
