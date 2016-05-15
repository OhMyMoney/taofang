function getHealthInfoPagination(page, pageSize) {
    $.ajax({
        url: ajaxBaseUrl + "/healthinfo?page=" + page + "&&pageSize=" + pageSize,
        success: processPaginationData
    });
}
function getHealthInfoDetail(id) {
    if(id == null){
        id = 0;
    }
    $.ajax({
        url: ajaxBaseUrl + "/healthinfo/" + id,
        success: processDetailData
    });
}
function showHealthInfoDetail(id) {
    var detailUrl = "detail.html?id=" + id;
    location.href = encodeURI(detailUrl);
}
function doPreviousPage() {
    var current = $("#page").find($("[class='currentpage']")).text().split("/")[0];
    if(parseInt(current) > 1){
        getHealthInfoPagination(parseInt(current) - 1, 10);
    }
}
function doNextPage() {
    var currentTotal = $("#page").find($("[class='currentpage']")).text().split("/");
    var current = currentTotal[0];
    var total = currentTotal[1];
    if(parseInt(current) < parseInt(total)){
        getHealthInfoPagination(parseInt(current) + 1, 10);
    }
}
function processPaginationData(data) {
    // 翻页区域
    processPage(data.curPage, data.totalPage);
    // 列表页
    var healthInfos = data.healthInfos;
    var storyInfoElems = $("<table><tbody></tbody></table>");
    for(var i=0; i<healthInfos.length; i++){
        var healthInfo = healthInfos[i];
        var onclickFunc = "showHealthInfoDetail(" + healthInfo.id + ")";
        var storyInfoTrElem = $("<tr></tr>");
        storyInfoTrElem.append($("<td><div class='contentpointdiv'></div></td>"));
        storyInfoTrElem.append($("<td><div class='contentlistdiv' onclick=" + onclickFunc + ">" + healthInfo.title + "</div></td>"));
        storyInfoElems.append(storyInfoTrElem);
    }
    $('#pagecontentlist').html(storyInfoElems);
}
function processDetailData(data) {
    var articleId = data.healthInfo.id;
    if(articleId != 0){
        processHealthInfo(data.healthInfo);
        $('#relationlink').show();
        processRelationLink(data.relationLinkInfos);
    }else{
        $('#relationlink').hide();
    }
}
function processHealthInfo(healthInfo) {
    var title = healthInfo.title;
    var imgUrl = healthInfo.imageUrl;
    var thumb = healthInfo.thumbCount + "个表扬";
    $('#detailtitle').html(title);
    $('#detailimage').html($("<img class='detailimage'/>").attr("src", imgUrl));
    $('#detailcontent').html(healthInfo.content);
    $('#thumbcount').html(thumb);
}