function getStoryPagination(page, pageSize) {
    $.ajax({
        url: "http://192.168.31.199:8080/taofang/webapi/story?page=" + page + "&&pageSize=" + pageSize,
        success: processPaginationData
    });
}
function showStoryDetail(id) {
    var detailUrl = "detail.html?id=" + id;
    location.href = detailUrl;
}
function doPreviousPage() {
    var current = $("#page").find($("[class='currentpage']")).text().split("/")[0];
    if(current > 1){
        getStoryPagination(parseInt(current) - 1, 10);
    }
}
function doNextPage() {
    var currentTotal = $("#page").find($("[class='currentpage']")).text().split("/");
    var current = currentTotal[0];
    var total = currentTotal[1];
    if(current < total){
        getStoryPagination(parseInt(current) + 1, 10);
    }
}
function processPaginationData(data) {
    // 翻页区域
    processPage(data.curPage, data.totalPage);
    // 列表页
    var storyInfos = data.storyInfos;
    var storyInfoElems = $("<table><tbody></tbody></table>")
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