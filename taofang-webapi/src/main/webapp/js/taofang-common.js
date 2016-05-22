function createContentPageElems(article, articleId) {
    var contentPageElems = $("<div id='contentbodypage' class='contentbodypage'></div>");
    contentPageElems
        .append($("<div class='previouspagediv'>" +
            "<div id='previouspage' class='previouspage_opacity' onclick='getPreviousPagination(\"" + article + "\", \"" + articleId + "\")'>" +
            "上一页</div></div>"))
        .append($("<div class='currentpagediv'><div class='currentpage'>0/0</div></div>"))
        .append($("<div class='nextpagediv'>" +
            "<div id='nextpage' class='nextpage' onclick='getNextPagination(\"" + article + "\", \"" + articleId + "\")'>" +
            "下一页</div></div>"));
    return contentPageElems;
}

function getPreviousPagination(category, articleId){
    var current = $("#contentbodypage").find($("[class='currentpage']")).text().split("/")[0];
    if(parseInt(current) > 1){
        if(category == "WDGS" || category == "ZRLF" || category == "JKZX"){
            getArticlePagination(category, parseInt(current) - 1, 10);
        }else if(category == "JJYS"){
            getRitucharyaPagination(parseInt(articleId), parseInt(current) - 1, 10);
        }else if(category == "JKZS"){
            getJKZSPagination(parseInt(current) - 1, 10, $("#date").val());
        }else if(category == "liangfang"){
            var orderId = $.cookie('orderId');
            if(typeof(orderId) == "undefined" || !orderId || orderId == ""){
                orderId = 0;
            }
            getLiangfangList(articleId, orderId, parseInt(current) - 1, 5);
        }
    }
}
function getNextPagination(category, articleId){
    var currentTotal = $("#contentbodypage").find($("[class='currentpage']")).text().split("/");
    var current = currentTotal[0];
    var total = currentTotal[1];
    if(parseInt(current) < parseInt(total)){
        if(category == "WDGS" || category == "ZRLF" || category == "JKZX"){
            getArticlePagination(category, parseInt(current) + 1, 10);
        }else if(category == "JJYS"){
            getRitucharyaPagination(parseInt(articleId), parseInt(current) + 1, 10);
        }else if(category == "JKZS"){
            getJKZSPagination(parseInt(current) + 1, 10, $("#date").val());
        }else if(category == "liangfang"){
            var orderId = $.cookie('orderId');
            if(typeof(orderId) == "undefined" || !orderId || orderId == ""){
                orderId = 0;
            }
            getLiangfangList(articleId, orderId, parseInt(current) + 1, 5);
        }
    }
}
function processPage(curPage, totalPage) {
    $("#contentbodypage").find($("[class='currentpage']")).html(curPage + "/" + totalPage);
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
function playVideo(videoUrl, title, id) {
    $.cookie('videoUrl', videoUrl, {expires: 7, path: '/'});
    var videoPlayButtonClass = $('#videoPlayButton' + id).attr("class");
    if(videoPlayButtonClass == "jjysjkzsparalleldiv"){
        $('#videoPlayButton' + id).attr("class", "jjysjkzstrianglediv");
        $('#videoPlayTitle' + id).attr("class", "jjysjkzsvideotitle1");
        var videoplay = document.getElementById("audiomp3");
        videoplay.pause();
    }else{
        $('#audiomp3').attr("src", videoUrl);
        $('div.jjysjkzscirclediv div').attr("class", "jjysjkzstrianglediv");
        $('#jjysjkzslisttable tbody tr td:nth-child(2) div').attr("class", "jjysjkzsvideotitle1");
        $('#videoPlayButton' + id).attr("class", "jjysjkzsparalleldiv");
        $('#videoPlayTitle' + id).attr("class", "jjysjkzsvideotitle2");
        var videoplay = document.getElementById("audiomp3");
        videoplay.play();
    }
}