function sendIt() {
    f = document.adminForm;
    
    if(!f.categoryNo.value){	
		alert("카테고리를 선택하세요");
		f.categoryNo.focus();
		return;
	} 
    
    if(!f.productName.value){	
		alert("상품명을 입력하세요");
		f.productName.focus();
		return;
	} 
    
    if(!f.price.value){	
		alert("가격을 입력하세요");
		f.price.focus();
		return;
	} 
    
    
    
    str = f.productContent.value;
    str = str.trim();
    if(!str) {
        alert("상품설명을 입력하세요!!!");
        f.productContent.focus();
        return;
    }
    
    
    
    
    f.categoryNo.value = str;

    str = f.upload.value;
    
    if(!str) {
        alert("이미지 파일을 선택 하세요 !!!");
        f.upload.focus();
        return;
    }
    
    f.action="/semi/project/addProduct_ok.do";
    f.submit();
}
