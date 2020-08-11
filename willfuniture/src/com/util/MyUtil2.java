package com.util;

public class MyUtil2 {
	
	//페이징 공식
	//절대바뀌지 않는 값 rownum을 사용
	//시작값(한페이지에 3개 게시할 경우):(3-1)*3+1= (페이지-1)*1페이지의게시물수+1
	//엔드값:3*3=9 => 페이지수*1페이지의게시물수
	//전체페이지수구하기=> 전체게시물의갯수/1페이지의 게시물수가 나머지가 있으면 +1 아니면 그냥 몫

	
	//전체 페이지수 구하기 
	//numPerPage : 한 화면에 표시할 게시물 수
	//dataCount : 전체 데이터의 갯수(sql이기 때문에DAO에서 뽑아서 넘겨받아야함)
	
	public int getPageCount(int numPerPage, int dataCount) {
		
		int pageCount = 0;
		
		pageCount = dataCount / numPerPage;
		
		if(dataCount%numPerPage!=0)
			pageCount++;
		
		return pageCount;
		
	}
	
	
	//페이징 처리 
	//currentPage : 현재 표시할 페이지
	//totalPage : 전체 페이지수
	//listUrl : 링크를 설정할 url(list.jsp) 기본디폴트는 리스트.jsp
	
	public String pageIndexList(int currentPage, int totalPage, String listUrl) {
		
		int numPerBlock = 5;
		int currentPageSetup;//이전◀(표시할 첫 페이지-1)
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		//메모리낭비방지를 위해 스트링 버퍼사용
		
		if(currentPage==0||totalPage==0) {
			return "";
		}
		
		if(listUrl.indexOf("?")!=-1) {
			//넘어오는 리스트유알엘에 물음표가 있으면~
			//-1은 없으면인데 그거에 대한 부정이기때문에
			listUrl = listUrl+"&";
		} else {
			listUrl = listUrl + "?";			
		}
		
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;//currentpage를 구하는 공식
		
		if(currentPage % numPerBlock==0)
			currentPageSetup = currentPageSetup-numPerBlock;
		
		//◀이전
		if(totalPage>numPerBlock && currentPageSetup>0) {
			
			sb.append("<a href=\"" + listUrl + "pageNum="
					+ currentPageSetup + "\">◀이전</a>&nbsp;");
			//listUrl을 따옴표로 감싸기 위해역슬러시 바로뒤에오는건 문자로 인식
			//<a href="list.jsp?pageNum=5">◀이전</a>&nbsp;
			
		}
		
		
		//바로가기페이지(숫자찍기)
		//내가 보고있는 페이지는 링크없이 색깔만 바꿔주면 되고 그외의 페이지에 링크
		
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
		
		//다음▶
		
		if(totalPage - currentPageSetup > numPerBlock)	{
			
			sb.append("<a href=\"" + listUrl + "pageNum=" + page +
					"\">다음▶</a>&nbsp;");
			
			//<a href="list.jsp>pageNum=11">다음▶</a>&nbsp;
			
		}
		
		return sb.toString();
		
	}

}
