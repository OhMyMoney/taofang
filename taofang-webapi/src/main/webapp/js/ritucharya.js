function getRitucharya() {
    $.ajax({
        url: "http://192.168.31.199:8080/taofang/webapi/ritucharya",
        success: processListData
    });
}
function showRitucharyaDetailList(id) {
    var detailUrl = "detail.html?id=" + id;
    location.href = detailUrl;
}
function getRitucharyaDetailPagination(id, page, pageSize) {
    $.ajax({
        url: "http://192.168.31.199:8080/taofang/webapi/ritucharya/" + id + "?page=" + page + "&&pageSize=" + pageSize,
        success: processDetailPaginationData
    });
}
function playVideo(videoUrl, id) {
    var playimgClass = $('#playimg'+id).attr("class");
    if(playimgClass == "paralleldiv"){
        $('#playimg' + id).attr("class", "trianglediv");
        $('#playtitle' + id).attr("class", "videotitle1");
        var videoplay = document.getElementById("videoplay");
        videoplay.pause();
    }else{
        $('#videoplay').attr("src", videoUrl);
        $('div.circlediv div').attr("class", "trianglediv");
        $('.videolistdiv table tbody tr td:nth-child(2) div').attr("class", "videotitle1");
        $('#playimg' + id).attr("class", "paralleldiv");
        $('#playtitle' + id).attr("class", "videotitle2");
        var videoplay = document.getElementById("videoplay");
        videoplay.play();
    }
}
function doPreviousPage() {
    var current = $("#page").find($("[class='currentpage']")).text().split("/")[0];
    if(parseInt(current) > 1){
        getRitucharyaDetailPagination($.getUrlParam('id'), parseInt(current) - 1, 10);
    }
}
function doNextPage() {
    var currentTotal = $("#page").find($("[class='currentpage']")).text().split("/");
    var current = currentTotal[0];
    var total = currentTotal[1];
    if(parseInt(current) < parseInt(total)){
        getRitucharyaDetailPagination($.getUrlParam('id'), parseInt(current) + 1, 10);
    }
}
function processListData(data) {
    var ritucharyas = data.ritucharyas;
    var ritucharyaElems = $("<table><tbody></tbody></table>");
    for(var i=0; i<ritucharyas.length; i++){
        var ritucharya = ritucharyas[i];
        var onclickFunc = "showRitucharyaDetailList(" + ritucharya.isdts + ")";
        var ritucharyaTrElem = $("<tr onclick='" + onclickFunc + "'></tr>");
        ritucharyaTrElem.append($("<td><div class='ritucharyaimage'><img src='" + ritucharya.imageUrl + "' /></div></td>"));
        ritucharyaTrElem.append($("<td><div class='ritucharyatitle'>" + ritucharya.title + "</div><div class='ritucharyasummary'>" + ritucharya.summary + "</div></td>"));
        ritucharyaElems.append(ritucharyaTrElem);
    }
    $('#ritucharyalist').html(ritucharyaElems);
}
function processDetailPaginationData(data) {
    // 翻页区域
    processPage(data.curPage, data.totalPage);
    var ritucharyas = data.ritucharyas;
    // 播放区域
    $('#videoplaydiv').html($("<audio id='videoplay' class='videoplay' controls='controls' src='" + ritucharyas[0].videoUrl +"' />"));
    // 列表页
    var ritucharyaElems = $("<table><tbody></tbody></table>");
    for(var i=0; i<ritucharyas.length; i++){
        var ritucharya = ritucharyas[i];
        var onclickFunc = "playVideo('" + ritucharya.videoUrl + "', " + ritucharya.id + ")";
        var playimg = "playimg" + ritucharya.id;
        var playtitle = "playtitle" + ritucharya.id;
        var ritucharyaTrElem = $("<tr></tr>");
        ritucharyaTrElem.append($("<td><div class='circlediv' onclick=\"" + onclickFunc + "\"><div id='" + playimg + "' class='trianglediv'></div></div></td>"));
        ritucharyaTrElem.append($("<td><div id='" + playtitle + "' class='videotitle1'>" + ritucharya.title + "</div></td>"));
        ritucharyaTrElem.append($("<td><img src='../../image/common/link.png' /></td>"));
        ritucharyaElems.append(ritucharyaTrElem);
    }
    $('#videolist').html(ritucharyaElems);
}