// Ajax请求域名
var ajaxBaseUrl = "http://192.168.31.199:8080/taofang/webapi";

function processPage(curPage, totalPage) {
    $("#page").find($("[class='currentpage']")).html(curPage + "/" + totalPage);
    if(curPage == 1 || curPage == 0){
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
function processRelationLink(relationLinks) {
    if(relationLinks.length > 0){
        $('#relationlink').show();
        var relationLinkElems = $("<table><tbody></tbody></table>");
        for(var i=0; i<relationLinks.length; i++){
            var relationLink = relationLinks[i];
            var relationLinkTrElem = $("<tr></tr>");
            relationLinkTrElem.append($("<td><div class='contentpointdiv'></div></td>"));
            relationLinkTrElem.append($("<td><div><a data-role='none' class='contentlistdiv' href='" + relationLink.linkUrl + "'>" + relationLink.title +"</a></div></td>"));
            relationLinkElems.append(relationLinkTrElem);
        }

        $('#relationlinklist').html(relationLinkElems);
    }else{
        $('#relationlink').hide();
    }
}
function goToHomePage(device){
    if(device == 'phone'){
        location.href = "http://localhost:8080/";
    }else if(device == 'pc'){
        location.href = "http://www.99taofang.com/";
    }
}
/*创建弹窗口*/
function creatediv(msgDivTop) {
    if(top.location == self.location){
        doc = document;
    }
    var docElement = doc.documentElement;
    var sWidth = $(document).width();
    var sHeight = $(document).height();
    if( docElement.scrollHeight > sHeight ){
        sHeight = docElement.scrollHeight;
    }
    var bgObj=document.createElement("div");
    bgObj.setAttribute('id','bgDiv');
    bgObj.style.position="absolute";
    bgObj.style.top="0";
    bgObj.style.left="0";
    bgObj.style.background="#777";
    bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
    bgObj.style.opacity="0.6";
    bgObj.style.width=sWidth + "px";
    bgObj.style.height=sHeight + "px";
    bgObj.style.zIndex = "10000";
    document.body.appendChild(bgObj);

    var msgObj=document.createElement("div");
    msgObj.setAttribute("id","msgDiv");
    msgObj.setAttribute("align","center");
    msgObj.style.position = "absolute";
    msgObj.style.background="#FFFFFF";
    msgObj.style.marginLeft = "5%" ;
    msgObj.style.top = msgDivTop + "px";
    msgObj.style.width = "90%";
    msgObj.style.height = "180px";
    msgObj.style.borderRadius = "10px";
    msgObj.style.zIndex = "10001";
    document.body.appendChild(msgObj);
}
function closepopup() {
    document.getElementById("bgDiv").style.display="none";
    document.getElementById("msgDiv").style.display="none";
    isshow=0;
}
function showpopup(msgDivTop) {
    isshow=1;
    if(!document.getElementById("msgDiv")){
        creatediv(msgDivTop);
    }else{
        document.getElementById("bgDiv").style.display = "";
        document.getElementById("msgDiv").style.display = "";
        document.getElementById("msgDiv").style.top = msgDivTop + "px";
    }
}
