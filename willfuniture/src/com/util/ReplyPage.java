package com.util;

public class ReplyPage {
	
	//����¡ ����
	//����ٲ��� �ʴ� �� rownum�� ���
	//���۰�(���������� 3�� �Խ��� ���):(3-1)*3+1= (������-1)*1�������ǰԽù���+1
	//���尪:3*3=9 => ��������*1�������ǰԽù���
	//��ü�����������ϱ�=> ��ü�Խù��ǰ���/1�������� �Խù����� �������� ������ +1 �ƴϸ� �׳� ��

	
	//��ü �������� ���ϱ� 
	//numPerPage : �� ȭ�鿡 ǥ���� �Խù� ��
	//dataCount : ��ü �������� ����(sql�̱� ������DAO���� �̾Ƽ� �Ѱܹ޾ƾ���)
	
	public int getPageCount(int numPerPage, int dataCount) {
		
		int pageCount = 0;
		
		pageCount = dataCount / numPerPage;
		
		if(dataCount%numPerPage!=0)
			pageCount++;
		
		return pageCount;
		
	}
	
	
	//����¡ ó�� 
	//currentPage : ���� ǥ���� ������
	//totalPage : ��ü ��������
	//listUrl : ��ũ�� ������ url(list.jsp) �⺻����Ʈ�� ����Ʈ.jsp
	
	public String pageIndexList(int listPage, int currentPage, int totalPage, String listUrl) {
		
		int numPerBlock = 3;
		int currentPageSetup;//������(ǥ���� ù ������-1)
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		//�޸𸮳�������� ���� ��Ʈ�� ���ۻ��
		
		if(currentPage==0||totalPage==0) {
			return "";
		}
		
		if(listUrl.indexOf("?")!=-1) {
			//�Ѿ���� ����Ʈ���˿��� ����ǥ�� ������~
			//-1�� �������ε� �װſ� ���� �����̱⶧����
			listUrl = listUrl+"&";
		} else {
			listUrl = listUrl + "?";			
		}
		
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;//currentpage�� ���ϴ� ����
		
		if(currentPage % numPerBlock==0)
			currentPageSetup = currentPageSetup-numPerBlock;
		
		//������
		if(totalPage>numPerBlock && currentPageSetup>0) {
			
			sb.append("<a href=\"" + listUrl + "rePageNum=" + currentPageSetup + "\">������</a>&nbsp;");
			//listUrl�� ����ǥ�� ���α� ���ؿ������� �ٷεڿ����°� ���ڷ� �ν�
			//<a href="list.jsp?pageNum=5">������</a>&nbsp;
			
		}
		
		
		//�ٷΰ���������(�������)
		//���� �����ִ� �������� ��ũ���� ���� �ٲ��ָ� �ǰ� �׿��� �������� ��ũ
		
		page = currentPageSetup + 1;
		
		while(page<=totalPage && page <= (currentPageSetup+numPerBlock)) {
			
			if(page==currentPage) {
				
				sb.append("<a href=\""+listUrl + "rePageNum="+page+"\">"
						+"<font color=\"Fuchsia\">"+page+"</font>"+ "</a>&nbsp;");
				
				//<a href="list.jsp?pageNum=7"><font color="Fuchsia">7</font>   </a>&nbsp;
				
				//sb.append("<font color=\"Fuchsia\">" + page + "</font>");
				//<font color="Fuchsia">6</font>&nbsp;
				
			} else {
				
				sb.append("<a href=\""+listUrl + "rePageNum="+page+"\">"
						+ page + "</a>&nbsp;");
				//<a href="list.jsp?pageNum=7">        7</a>&nbsp;
				
			}
			
			page++;
		}
		
		//������
		
		if(totalPage - currentPageSetup > numPerBlock)	{
			
			sb.append("<a href=\"" + listUrl + "rePageNum=" + page +
					"\">������</a>&nbsp;");
			
			//<a href="list.jsp>pageNum=11">������</a>&nbsp;
			
		}
		
		return sb.toString();
		
	}

}