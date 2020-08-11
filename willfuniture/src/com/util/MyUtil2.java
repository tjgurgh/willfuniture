package com.util;

public class MyUtil2 {
	
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
	
	public String pageIndexList(int currentPage, int totalPage, String listUrl) {
		
		int numPerBlock = 5;
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
			
			sb.append("<a href=\"" + listUrl + "pageNum="
					+ currentPageSetup + "\">������</a>&nbsp;");
			//listUrl�� ����ǥ�� ���α� ���ؿ������� �ٷεڿ����°� ���ڷ� �ν�
			//<a href="list.jsp?pageNum=5">������</a>&nbsp;
			
		}
		
		
		//�ٷΰ���������(�������)
		//���� �����ִ� �������� ��ũ���� ���� �ٲ��ָ� �ǰ� �׿��� �������� ��ũ
		
		page = currentPageSetup + 1;
		
		while(page<=totalPage && page <= (currentPageSetup+numPerBlock)) {
			
			if(page==currentPage) {
				
				sb.append("<a href=\""+listUrl + "pageNum="+page+"\">"
						+"<font size=\"50\">"+page+"</font>"+ "</a>&nbsp;");
				
			//	sb.append( "<a class=\"page-link\" href=\"#\"" + page + "&nbsp; </a>");

			/*	sb.append(page + "&nbsp;");*/

				
/*				sb.append("<font color=\"Fuchsia\">" + page + "</font>&nbsp;");
*/				
				
				//sb.append("<div class=\"row\"> <div class=\"col-12\"> <!-- Pagination --> <nav aria-label=\"navigation\"> <ul class=\"pagination justify-content-end mt-50\">  <li class=\"page-item active\"><a class=\"page-link\" href=\"#\"> " +page + "01.</a></li> </ul> </nav> </div> </div>");
				
           
				
				
				
				
				//<font color="Fuchsia">6</font>&nbsp;
				
			} else {
				
				sb.append("<a href=\""+listUrl + "pageNum="+page+"\">"
						+ page + "</a>&nbsp;");
				
			/*	sb.append("<a href=\""+listUrl + "pageNum="+page+"\">"
						+ page + "</a>&nbsp;");*/
				//<a href="list.jsp?pageNum=7">7</a>&nbsp;
				
			}
			
			//\${listUrl}pageNum=${page}\${page}">${status.index}
			
		//	\${listUrl}pageNum${listUrl}pageNum=${page}\${page}
			
			//<a href =\"+${listUrl} + ${pageNum} + no + "\" + ${page} + "</a>&nbsp;" 
			
		
			
			
			page++;
		}
		
		//������
		
		if(totalPage - currentPageSetup > numPerBlock)	{
			
			sb.append("<a href=\"" + listUrl + "pageNum=" + page +
					"\">������</a>&nbsp;");
			
			//<a href="list.jsp>pageNum=11">������</a>&nbsp;
			
		}
		
		return sb.toString();
		
	}

}
