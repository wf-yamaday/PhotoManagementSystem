$('select.dropdown')
	.dropdown()
;

flag=false
function popUpMenu(){
	if(flag){
		pMenu.style.visibility = "hidden";
	}else{
		pMenu.style.visibility = "visible";
	}
	flag = !flag;
}