function backhomepage() {
    location.href = "../home.html";
}
function processPage(curPage, totalPage) {
    $("#page").find($("[class='currentpage']")).html(curPage + "/" + totalPage);
    if(curPage == 1){
        $('#previouspage').addClass("previouspage_opacity").removeClass("previouspage");
    }else{
        $('#previouspage').addClass("previouspage").removeClass("previouspage_opacity");
    }
    if(curPage == totalPage){
        $('#nextpage').addClass("nextpage_opacity").removeClass("nextpage");
    }else{
        $('#nextpage').addClass("nextpage").removeClass("nextpage_opacity");
    }
}
function goToHomePage(device){
    if(device == 'phone'){
        location.href = "http://192.168.31.199:8080/";
    }else if(device == 'pc'){
        location.href = "http://www.99taofang.com/";
    }
}