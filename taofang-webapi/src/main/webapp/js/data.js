function processHealthInfoPagination(data) {
    var curPage = data.curPage;
    var totalPage = data.totalPage;
    var healthInfos = data.healthInfos;
    $("#currentPage").html(curPage + "/" + totalPage);
    var tableEnums = $("<table></table>");
    for(var i=0; i<healthInfos.length; i++){
        var url = "detail.html?id=" + healthInfos[i].id;
        var trEnums = $("<tr></tr>");
        trEnums.append("<td class='listTableTd1'><img src='../../image/icon/dot_icon.png'></td>")
            .append("<td><a href='" + url + "'>" + healthInfos[i].title + "</a></td>");
        tableEnums.append(trEnums);
    }
    $("#list").html(tableEnums);
}