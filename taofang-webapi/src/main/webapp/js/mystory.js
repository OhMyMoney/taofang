function getStoryPagination(page, pageSize) {
    $.ajax({
        url: ajaxBaseUrl + "/story?page=" + page + "&&pageSize=" + pageSize,
        success: processPaginationData
    });
}
function getStoryDetail(id) {
    if(id == null){
        id = 0;
    }
    $.ajax({
        url: ajaxBaseUrl + "/story/" + id,
        success: processDetailData
    });
}
function showStoryDetail(id) {
    var detailUrl = "detail.html?id=" + id;
    location.href = detailUrl;
}
function doPreviousPage() {
    var current = $("#page").find($("[class='currentpage']")).text().split("/")[0];
    if(parseInt(current) > 1){
        getStoryPagination(parseInt(current) - 1, 10);
    }
}
function doNextPage() {
    var currentTotal = $("#page").find($("[class='currentpage']")).text().split("/");
    var current = currentTotal[0];
    var total = currentTotal[1];
    if(parseInt(current) < parseInt(total)){
        getStoryPagination(parseInt(current) + 1, 10);
    }
}
function processPaginationData(data) {
    // 翻页区域
    processPage(data.curPage, data.totalPage);
    // 列表页
    var storyInfos = data.storyInfos;
    var storyInfoElems = $("<table><tbody></tbody></table>");
    for(var i=0; i<storyInfos.length; i++){
        var storyInfo = storyInfos[i];
        var onclickFunc = "showStoryDetail(" + storyInfo.id + ")";
        var storyInfoTrElem = $("<tr></tr>");
        storyInfoTrElem.append($("<td><div class='contentpointdiv'></div></td>"));
        storyInfoTrElem.append($("<td><div class='contentlistdiv' onclick=" + onclickFunc + ">" + storyInfo.title + "</div></td>"));
        storyInfoElems.append(storyInfoTrElem);
    }
    $('#pagecontentlist').html(storyInfoElems);
}
function processDetailData(data) {
    var articleId = data.storyInfo.id;
    if(articleId != 0){
        processStory(data.storyInfo);
        $('#relationlink').show();
        processRelationLink(data.relationLinkInfos);
    }else{
        $('#relationlink').hide();
    }
}
function processStory(storyInfo) {
    var title = storyInfo.title;
    var imgUrl = storyInfo.imageUrl;
    var thumb = storyInfo.thumbCount + "个表扬";
    var videoUrl = storyInfo.videoUrl
    if(videoUrl != ""){
        $('#detailvideo').show();
        $('#videomp3').attr("src", videoUrl);
    }else{
        $('#detailvideo').hide();
    }
    $('#detailtitle').html(title);
    $('#detailimage').html($("<img class='detailimage'/>").attr("src", imgUrl));
    $('#detailcontent').html(storyInfo.content);
    $('#thumbcount').html(thumb);
}