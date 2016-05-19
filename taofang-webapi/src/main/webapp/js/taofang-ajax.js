var ajaxBaseUrl = "http://localhost:8080/taofang/webapi";

function getLiangfangList(prescription, order, page, pageSize) {
}

function getArticlePagination(category, page, pageSize) {
    var ajaxUrl = ajaxBaseUrl + "/article/list/" + category + "?page=" + page + "&pageSize=" + pageSize;
    $.ajax({
        url: ajaxUrl,
        success: processArticlePaginationData
    });
}
function getJKZSPagination(page, pageSize, dateStr){
    var ajaxUrl = ajaxBaseUrl + "/article/list/JKZS?page=" + page + "&pageSize=" + pageSize;
    var notHashDate = typeof(dateStr) == "undefined" || !dateStr || dateStr == "";
    if(!notHashDate){
        ajaxUrl += ("&queryDate=" + dateStr);
    }
    $.ajax({
        url: ajaxUrl,
        success: processArticlePaginationData
    });
}
function getRitucharyaPagination(category, page, pageSize) {
    var ajaxUrl = ajaxBaseUrl + "/article/ritucharya/list/" + category + "?page=" + page + "&pageSize=" + pageSize;
    $.ajax({
        url: ajaxUrl,
        success: processRitucharyaPaginationData
    });
}
function getArticleDetail(category, id) {

}
function shareArticleDetail() {

}
