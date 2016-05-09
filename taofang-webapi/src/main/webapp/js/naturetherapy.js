function getNatureTherapyPagination(page, pageSize) {
    $.ajax({
        url: "http://101.201.47.48/taofang/webapi/naturethearpy?page=" + page + "&&pageSize=" + pageSize,
        success: processPaginationData
    });
}
function getNatureTherapyDetail(id) {
    if(id == null){
        id = 0;
    }
    $.ajax({
        url: "http://101.201.47.48/taofang/webapi/naturethearpy/" + id,
        success: processDetailData
    });
}
function showNatureTherapyDetail(id) {
    var detailUrl = "detail.html?id=" + id;
    location.href = detailUrl;
}
function doPreviousPage() {
    var current = $("#page").find($("[class='currentpage']")).text().split("/")[0];
    if(parseInt(current) > 1){
        getNatureTherapyPagination(parseInt(current) - 1, 10);
    }
}
function doNextPage() {
    var currentTotal = $("#page").find($("[class='currentpage']")).text().split("/");
    var current = currentTotal[0];
    var total = currentTotal[1];
    if(parseInt(current) < parseInt(total)){
        getNatureTherapyPagination(parseInt(current) + 1, 10);
    }
}
function processPaginationData(data) {
    // 翻页区域
    processPage(data.curPage, data.totalPage);
    // 列表页
    var natureTherapys = data.natureTherapys;
    var storyInfoElems = $("<table><tbody></tbody></table>")
    for(var i=0; i<natureTherapys.length; i++){
        var natureTherapy = natureTherapys[i];
        var onclickFunc = "showNatureTherapyDetail(" + natureTherapy.id + ")";
        var storyInfoTrElem = $("<tr></tr>");
        storyInfoTrElem.append($("<td><div class='contentpointdiv'></div></td>"));
        storyInfoTrElem.append($("<td><div class='contentlistdiv' onclick=" + onclickFunc + ">" + natureTherapy.title + "</div></td>"));
        storyInfoElems.append(storyInfoTrElem);
    }
    $('#pagecontentlist').html(storyInfoElems);
}
function processDetailData(data) {
    var articleId = data.natureTherapyInfo.id;
    if(articleId != 0){
        processNatureTherapy(data.natureTherapyInfo);
        processRelationLink(data.relationLinkInfos);
    }else{
        $('#relationlink').hide();
    }
}
function processNatureTherapy(natureTherapyInfo) {
    var title = natureTherapyInfo.title;
    var imgUrl = natureTherapyInfo.imageUrl;
    var thumb = natureTherapyInfo.thumbCount + "个表扬";
    var videoUrl = natureTherapyInfo.videoUrl
    if(videoUrl != ""){
        $('#detailvideo').show();
        $('#detailimage').hide();
        $('#videomp3').attr("src", videoUrl);
    }else{
        $('#detailvideo').hide();
        $('#detailimage').show();
    }
    $('#detailtitle').html(title);
    $('#detailimage').html($("<img class='detailimage'/>").attr("src", imgUrl));
    $('#detailcontent').html(natureTherapyInfo.content);
    $('#thumbcount').html(thumb);
}