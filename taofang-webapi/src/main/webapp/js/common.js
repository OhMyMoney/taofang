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
        location.href = "http://101.201.47.48/";
    }else if(device == 'pc'){
        location.href = "http://www.99taofang.com/";
    }
}