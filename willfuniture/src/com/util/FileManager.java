package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class FileManager {
	
	//���� �ٿ�ε�
	//saveFileName :������ ����� ���ϸ�
	//originalFileName :Ŭ���̾�Ʈ�� ���ε��� ���ϸ�
	//path :������ ����� ��ġ 
	
	public static boolean doFileDownload(HttpServletResponse response,
			String saveFileName, String originalFileName, String path) {
		
		try {
			String filePath = path + File.separator + saveFileName;
			
			if(originalFileName==null||originalFileName.equals("")) {//�ڹٿ��� ��Ʈ���� ���� ���Ҷ� �ι��ִ°� ����
						
				originalFileName = saveFileName;				
			}
			
			//������ �ٿ� �޾� ����ÿ� �����̸������� �ѱ۱��� ����
			//���ε�ÿ��� euc..�� ISO...��ġ �ٲٱ�
			//������� ���������� �״����� ������ ����������
			
			originalFileName = new String(originalFileName.getBytes("euc-kr"),"ISO-8859-1");

			
			File f = new File(filePath);
			
			if(!f.exists())
				return false;
		
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition",
					"attachment;fileName=" + originalFileName);
			
			
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(f));//������ �о� ���۾ȿ� �ְ�
			
			OutputStream out = response.getOutputStream(); //�о ��������
			
			int n;
			
			byte[] bytes = new byte[4096];
			
			while((n=bis.read(bytes,0,4096))!=-1) {
				out.write(bytes,0,n);
			}
			
			out.flush(); //���۸� ��ä���� ���� ������ ���� ���
			out.close();
			bis.close();
				
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		return true;
		
	}
	
	
	//���ϻ���
	
	public static void doFileDelete(String fileName, String path) {
		
		try {
			
			String filePath= path + File.separator + fileName;
			
			File f = new File(filePath);
			
			if(f.exists()) 
				f.delete();//���ϻ���
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}		
		
	}
	
	
	
	

}
