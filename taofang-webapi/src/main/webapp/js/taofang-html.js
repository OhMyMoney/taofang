function createHomePage() {
    $("#navigation").panel("close");
    insertPanelNavigation();
    insertHomeHeader();
    insertHomeContentBody();
    insertContentFoot();
    createFooter();
}
function createUserPage() {

}
function createLiangfangPage() {
    var prescription = $("#homeheaderprescriptioninput").val();
    getLiangfangList(prescription, 0, 1, 5);
}
function createJKZSPage(role, page, pageSize, articleId) {
    insertArticleHeader("JKZS", "健康之声", role);
    insertArticleContent("JKZS", role, page, pageSize, articleId);
}
function createWDGSPage(role, page, pageSize, articleId) {
    insertArticleHeader("WDGS", "我的故事", role);
    insertArticleContent("WDGS", role, page, pageSize, articleId);
}
function createZRLFPage(role, page, pageSize, articleId) {
    insertArticleHeader("ZRLF", "自然疗法", role);
    insertArticleContent("ZRLF", role, page, pageSize, articleId);
}
function createJJYSPage(role, page, pageSize, articleId) {
    insertArticleHeader("JJYS", "季节养生", role);
    insertArticleContent("JJYS", role, page, pageSize, articleId);
}
function createJKZXPage(role, page, pageSize, articleId) {
    insertArticleHeader("JKZX", "健康资讯", role);
    insertArticleContent("JKZX", role, page, pageSize, articleId);
}
function gotoPageByType(type) {
    if (type == "pc"){
        location.href = "http://www.99taofang.com";
    }else if(type == "phone"){
        location.href = "/views/taofang.html";
    }
}
function gotoBackPage(role) {
    if(role == "LIST"){
        createHomePage();
    }else if(role == "DETAIL"){
        var lastPage = $.cookie('lastPage');
        if(typeof(lastPage) == "undefined" || !lastPage || lastPage == ""){
            location.href = "/views/taofang.html";
        }else{
            var lastPageArray = lastPage.split(";");
            gotoArticlePage(lastPageArray[0], lastPageArray[1], lastPageArray[2]);
        }
    }
}
function gotoArticlePage(article, page, pageSize) {
    $("#navigation").panel("close");
    if(article == "JKZS"){
        createJKZSPage("LIST", page, pageSize, 0);
    }else if(article == "WDGS"){
        createWDGSPage("LIST", page, pageSize, 0);
    }else if(article == "ZRLF"){
        createZRLFPage("LIST", page, pageSize, 0);
    }else if(article == "JJYS"){
        createJJYSPage("LIST", page, pageSize, 0);
    }else if(article == "JKZX"){
        createJKZXPage("LIST", page, pageSize, 0);
    }
}
function gotoArticleDetailPage(article, articleId) {
    if(article == "JKZS"){
        createJKZSPage("DETAIL", 0, 0, articleId);
    }else if(article == "WDGS"){
        createWDGSPage("DETAIL", 0, 0, articleId);
    }else if(article == "ZRLF"){
        createZRLFPage("DETAIL", 0, 0, articleId);
    }else if(article == "JJYS"){
        createJJYSPage("DETAIL", 1, 10, articleId);
    }else if(article == "JKZX"){
        createJKZXPage("DETAIL", 0, 0, articleId);
    }
}
function insertContentFoot() {
    var contentFooterAdElems = $("<div id='footeraddiv' class='footeraddiv'></div>");
    var contentFooterAds = $("<div class='footeradimg'></div>");
    var adInfos = ["/image/guanggao.png", "/image/guanggao.png", "/image/guanggao.png"];
    for(var i=0; i<adInfos.length; i++){
        contentFooterAds
            .append($("<img src='" + adInfos[i] + "' title='广告" + i + "' />"));
    }
    var contentFooterBtn = $("<div class='footeradbtn'></div>");
    contentFooterBtn
        .append($("<a data-role='none' href='#' class='hover'>●</a>"))
        .append($("<a data-role='none' href='#'>●</a>"))
        .append($("<a data-role='none' href='#'>●</a>"));
    contentFooterAdElems
        .append(contentFooterAds)
        .append(contentFooterBtn)
        .append("<script src='/js/taofang-ad.js'></script>");
    $("#contentfooteraddiv").html(contentFooterAdElems);
    var contentFooterSwitchElems = $("<div class='phoneandpcdiv'></div>");
    contentFooterSwitchElems
        .append($("<div class='phonediv'><div class='phone' onclick='gotoPageByType(\"phone\")'>" +
                "<div class='phoneimg'><img src='/image/common/iphone.png'></div>" +
                "<div class='phonetext'>触屏版</div>" +
                "</div></div>"))
        .append($("<div class='phonepcblockdiv'></div>"))
        .append($("<div class='pcdiv'><div class='pc' onclick='gotoPageByType(\"pc\")'>" +
            "<div class='pcimg'><img src='/image/common/pc.png'></div>" +
            "<div class='pctext'>电脑版</div>" +
            "</div></div>"));

    $("#contentfooterswitchdiv").html(contentFooterSwitchElems);
}
/*导航*/
function insertPanelNavigation() {
    var navigationElems = $("<div></div>");
    var homeNavigationDivElem = $("<div class='navigationdiv' onclick='createHomePage()'></div>");
    homeNavigationDivElem
        .append($("<div class='clickshowdiv_home'></div>"))
        .append($("<div class='navigationimgdiv_home'><img src='/image/navigation/home.png'></div>"))
        .append($("<div class='navigationtitlediv_home'>首页</div>"));
    navigationElems
        .append(homeNavigationDivElem)
        .append(navigationDiv('JKZS', '健康之声'))
        .append(navigationDiv('WDGS', '我的故事'))
        .append(navigationDiv('ZRLF', '自然疗法'))
        .append(navigationDiv('JJYS', '季节养身'))
        .append(navigationDiv('JKZX', '健康资讯'));

    $("#navigation").html(navigationElems);
}
function navigationDiv(id, title) {
    var onclickFunc = "gotoArticlePage(\"" + id + "\", 1, 10)";
    var navigationDivElem = $("<div class='navigationdiv' onclick='" + onclickFunc + "'></div>");
    navigationDivElem
        .append($("<div class='clickshowdiv'></div>"))
        .append($("<div class='navigationimgdiv'><img src='/image/navigation/" + id + ".png'></div>"))
        .append($("<div class='navigationtitlediv'>" + title + "</div>"));

    return navigationDivElem;
}
/*首页*/
function insertHomeHeader() {
    var headerElems = $("<div class='homeheaderdiv'></div>");
    var headerTitleDivElem = $("<div class='homeheadertitlediv'></div>");
    var userId = $.cookie('userId');
    headerTitleDivElem
        .append($("<div class='homeheadertitle1'><a href='#navigation'><img src='/image/home/navigation.png'/></a></div>"))
        .append($("<div class='homeheadertitle2'><img src='/image/home/jiujiutaofang.png'/></div>"));
    if(typeof(userId) == "undefined" || !userId || userId == ""){
        headerTitleDivElem.append($("<div class='homeheadertitle3' onclick='createDLZCPopup()'><img src='/image/home/login.png'/></div>"));
    }else{
        headerTitleDivElem.append($("<div class='homeheadertitle3' onclick='createUserPage()'><img src='/image/home/login_ok.png'/></div>"));
    }
    var headerPrescriptionDivElem = $("<div class='homeheaderprescriptiondiv'></div>");
    headerPrescriptionDivElem
        .append($("<div class='homeheaderprescription1'>" +
                "<div class='homeheaderprescriptionimg'><img src='/image/home/search.png'/></div>" +
                "<input id='homeheaderprescriptioninput' data-role='none' class='homeheaderprescriptioninput' type='text' name='prescription' placeholder='输入关键字寻找相关偏方'/>" +
            "</div>"))
        .append($("<div class='homeheaderprescription2' onclick='createLiangfangPage()'>立即搜索</div>"));
    var headerDiseaseDivElem = $("<div class='homeheaderdiseasediv'></div>");

    headerElems
        .append(headerTitleDivElem)
        .append(headerPrescriptionDivElem)
        .append(headerDiseaseDivElem);

    $("#header").html(headerElems);
}
function insertHomeContentBody() {

}
/*良方*/
function createLiangfangHeader(role) {

}
/*文章*/
function insertArticleHeader(id, title, role) {
    var headerElems = $("<div class='articleblackheader'></div>");
    headerElems
        .append($("<div class='articleblackheaderback' onclick='gotoBackPage(\"" + role + "\")'><img src='/image/common/back.png'></div>"))
    if(role == "LIST" || id == "JJYS"){
        headerElems
            .append($("<div class='articleblackheadertitle'>" + title + "</div>"));
    }else if(role == "DETAIL" && id != "JJYS"){
        headerElems
            .append($("<div class='articleblackheadertitle_detail'>" + title + "</div>"))
            .append($("<div class='articleblackheadershare' onclick='shareArticleDetail()'><img src='/image/common/share.png'></div>"));
    }

    $("#header").html(headerElems);
}
function insertArticleContent(article, role, page, pageSize, articleId){
    var contentBodyElems = $("<div class='contentbody'></div>");

    if(role == "LIST"){
        if(article == "JKZS"){
            var contentBodyConditionElems = $("<div id='contentconditiondiv' class='dateconditiondiv'>");
            contentBodyConditionElems
                .append($("<div class='contentconditiondatebuttondiv'>" + 
                    "<label for='date'>筛选日期</label>" +
                    "<input id='date' name='date' data-role='none' style='display: none' />" +
                    "</div>"))
                .append($("<div id='contentconditionselectdiv' class='contentconditiondateselectdiv'></div>"));
            contentBodyElems.append(contentBodyConditionElems)
                .append($("<script src='/js/taofang-date.js'></script>"));
        }
        contentBodyElems.append($("<div id='contentbodylist' class='contentbodylist'></div>"));
        if(article != "JJYS"){
            contentBodyElems.append(createContentPageElems(article, articleId));
        }
    }else if(role == "DETAIL"){
        if(article == "JJYS"){
            contentBodyElems.append($("<div id='contentbodylist' class='contentbodylist'></div>"));
            contentBodyElems.append(createContentPageElems(article, articleId));
        }
        
    }
    $("#contentbodydiv").html(contentBodyElems);

    if (role == "LIST"){
        if(article == "JKZS"){
            getJKZSPagination(page, pageSize, $("#date").val());
        }else{
            getArticlePagination(article, page, pageSize);
        }
    }else if(role == "DETAIL"){
        if(article == "JJYS"){
            getRitucharyaPagination(articleId, page, pageSize);
        }
    }
}
function processArticlePaginationData(data) {
    var contentListTableElems;
    var articleList = data.articleList;
    var pagination = data.pagination;
    var category;
    if(articleList.length > 0){
        category = articleList[0].category;
    }else{
        category = "JKZS";
    }

    var lastPageDate = category + ";" + pagination.page + ";" + pagination.pageSize;
    $.cookie('lastPage', lastPageDate, {expires: 7, path: '/'});

    if(category == "WDGS" || category == "JKZX" || category == "ZRLF"){
        contentListTableElems = $("<table id='wdgsjkzxzrlflisttable'><tbody></tbody></table>");
        for(var i=0; i<articleList.length; i++){
            var article = articleList[i];
            var onclickFunc = "gotoArticleDetailPage(\"" + category + "\", " + article.articleId + ")";
            var contentListTrElems = $("<tr></tr>")
                .append($("<td><div class='contentpointdiv'></div></td>"))
                .append($("<td><div class='wdgsjkzxzrlflistlistdiv' onclick='" + onclickFunc + "'>" + article.articleTitle + "</div></td>"));
            contentListTableElems.append(contentListTrElems);
        }
        // 翻页区域
        processPage(pagination.page, pagination.totalPage);
    }else if(category == "JJYS"){
        contentListTableElems = $("<table id='jjyslisttable'><tbody></tbody></table>");
        for(var i=0; i<articleList.length; i++){
            var article = articleList[i];
            var onclickFunc = "gotoArticleDetailPage(\"" + category + "\", " + article.ritucharya + ")";
            var contentListTrElems = $("<tr onclick='" + onclickFunc + "'></tr>")
                .append($("<td><div class='jjyscontentimagediv'><img src='" + article.imageUrl + "' /></div></td>"))
                .append($("<td><div class='jjyscontentlisttitle'>" + article.articleTitle + "</div><div class='jjyscontentlistsummary'>" + article.summary + "</div></td>"));
            contentListTableElems.append(contentListTrElems);
        }
    }else if(category == "JKZS"){
        if(articleList.length == 0){
            contentListTableElems = $("<div class='jkzscontentemptylist'></div>")
                .append($("<div><img src='/image/common/note.png'></div>"))
                .append($("<div class='jkzscontentemptydate'>没有\"" + parseDate() + "\"的相关内容</div>"))
                .append($("<div class='jkzscontentemptybutton' onclick='gotoArticlePage(\"JKZS\", 1, 10)'>返回列表页</div>"));
        }else{
            contentListTableElems
        }
        // 翻页区域
        processPage(pagination.page, pagination.totalPage);
    }

    $(".contentbodylist").html(contentListTableElems);
}
function processRitucharyaPaginationData(data) {
    var contentListTableElems = $("<table id='jjysjkzslisttable'><tbody></tbody></table>");
    var ritucharyaList = data.ritucharyaList;
    var pagination = data.pagination;

    if($("#footer").is(':hidden') && ritucharyaList.length > 0){
        var videoIndex = 0;
        while (videoIndex < ritucharyaList.length){
            if(ritucharyaList[videoIndex].videoUrl != ""){
                $.cookie('videoUrl', ritucharyaList[videoIndex].videoUrl, {expires: 7, path: '/'});
                createFooter();
                break;
            }
            videoIndex += 1;
        }
    }

    for(var i=0; i<ritucharyaList.length; i++){
        var ritucharya = ritucharyaList[i];
        var videoPlayButtonId = "videoPlayButton" + ritucharya.ritucharyaId;
        var videoPlayTitleId = "videoPlayTitle" + ritucharya.ritucharyaId;
        var onclickFunc = "playVideo(\"" + ritucharya.videoUrl + "\", \"" + ritucharya.ritucharyaTitle + "\", " + ritucharya.ritucharyaId + ")";

        var contentListTrElems = $("<tr></tr>")
            .append($("<td><div class='jjysjkzscirclediv' onclick='" + onclickFunc + "'><div id='" + videoPlayButtonId + "' class='jjysjkzstrianglediv'></div></div></td>"))
            .append($("<td><div id='" + videoPlayTitleId + "' class='jjysjkzsvideotitle1'>" + ritucharya.ritucharyaTitle + "</div></td>"))
            .append($("<td><img src='/image/common/link.png' /></td>"));
        contentListTableElems.append(contentListTrElems);
    }
    // 翻页区域
    processPage(pagination.page, pagination.totalPage);

    $(".contentbodylist").html(contentListTableElems);
}


/*底部*/
function createFooter(){
    var videoUrl = $.cookie('videoUrl');
    if(typeof(videoUrl) == "undefined" || !videoUrl || videoUrl == ""){
        $("#footer").hide();
        $("#footer").removeClass("ui-footer-fixed").removeClass("slideup");
        $("#footer").removeAttr("data-position");
    }else{
        $("#footer").show();
        $("#footer").attr("data-position", "fixed");
        $("#footer").addClass("ui-footer-fixed").addClass("slideup");
        $("#audiomp3").attr("src", videoUrl);
    }
}