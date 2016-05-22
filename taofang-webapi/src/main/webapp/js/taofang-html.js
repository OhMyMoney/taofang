var isFirstLoad = true;
function createHomePage() {
    $("#navigation").panel("close");
    insertPanelNavigation();
    insertHomeHeader();
    insertHomeContentBody();
    insertContentFoot();
    createFooter();
}
function createUserPage() {
    insertUserinfoHeader();
    insertUserinfoContent();
}
function createLiangfangPage(role, page, pageSize, prescription, order) {
    insertLiangfangHeader(role, prescription);
    insertLiangfangContent(role, page, pageSize, prescription, order);
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
        $("#contentfooterdiv").show();
        createHomePage();
    }else if(role == "DETAIL"){
        var lastPage = $.cookie('lastPage');
        if(typeof(lastPage) == "undefined" || !lastPage || lastPage == ""){
            location.href = "/views/taofang.html";
        }else{
            var lastPageArray = lastPage.split(";");
            if(lastPageArray[0] == "liangfang"){
                createLiangfangPage("LIST", lastPageArray[1], lastPageArray[2], lastPageArray[3], lastPageArray[4])
            }else{
                gotoArticlePage(lastPageArray[0], lastPageArray[1], lastPageArray[2]);
            }
        }
    }else if(role == "DETAIL_IMAGE"){
        var imagePageDetail = $.cookie('imagePageDetail');
        if(typeof(imagePageDetail) == "undefined" || !imagePageDetail || imagePageDetail == ""){
            location.href = "/views/taofang.html";
        }else{
            var imagePageArray = imagePageDetail.split(";");
            createLiangfangPage("DETAIL", 0, 0, imagePageArray[1], 0);
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
function homeNavigationDiv(id, title) {
    var onclickFunc = "gotoArticlePage(\"" + id + "\", 1, 10)";
    var imageUrl = "/image/home/" + id + ".png";
    var navigationDivElem;
    var navigationOnclickDivElem;
    if(id == "JKZS"){
        navigationDivElem = $("<div class='homenavigationjkzsdiv'></div>");
        navigationOnclickDivElem = ($("<div class='homenavigationjkzs' onclick='" + onclickFunc + "'></div>"))
            .append($("<div class='homenavigationjkzscircle'>" +
                    "<div class='homenavigationcircleimg'><img src='" + imageUrl + "'/></div>" +
                    "<div class='homenavigationcircletext'>" + title + "</div></div>"));
    }else if(id == "WDGS" || id == "JJYS"){
        navigationDivElem = $("<div class='homenavigationotherdiv'></div>");
        navigationOnclickDivElem = ($("<div class='homenavigationotherdiv_left homenavigationotherdiv_" + id + "' onclick='" + onclickFunc + "'></div>"))
            .append($("<div class='homenavigationothercircle'>" +
                    "<div class='homenavigationcircleimg'><img src='" + imageUrl + "'/></div>" +
                    "<div class='homenavigationcircletext'>" + title + "</div></div>"));
    }else if(id == "ZRLF" || id == "JKZX"){
        navigationDivElem = $("<div class='homenavigationotherdiv'></div>");
        navigationOnclickDivElem = ($("<div class='homenavigationotherdiv_right homenavigationotherdiv_" + id + "' onclick='" + onclickFunc + "'></div>"))
        .append($("<div class='homenavigationothercircle'>" +
                "<div class='homenavigationcircleimg'><img src='" + imageUrl + "'/></div>" +
                "<div class='homenavigationcircletext'>" + title + "</div></div>"));
    }
    return navigationDivElem.append(navigationOnclickDivElem);
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
                "<input id='prescriptioninput' data-role='none' class='homeheaderprescriptioninput' type='text' name='prescription' placeholder='输入关键字寻找相关偏方'/>" +
            "</div>"))
        .append($("<div class='homeheaderprescription2' onclick='searchPrescription(0)'>立即搜索</div>"));
    var headerDiseaseDivElem = $("<div id='homeheaderdiseasediv'></div>");

    headerElems
        .append(headerTitleDivElem)
        .append(headerPrescriptionDivElem)
        .append(headerDiseaseDivElem);
    $("#header").html(headerElems);
    getWordStatistics();
}
function insertHomeContentBody() {
    var contentBodyElems = $("<div class='contentbody'></div>");
    // 浏览历史
    var contentViewElems = $("<div id='homecontentviewdiv' class='homecontentviewdiv'></div>");
    var contentViewHistoryElems = $("<div class='homeemptyviewhistorydiv'></div>");
    contentViewHistoryElems.append(homeEmptyViewElems("偏方"));
    contentViewHistoryElems.append(homeEmptyViewElems("文章"));
    contentViewElems.append(contentViewHistoryElems);
    // 导航
    var contentNavigationElems = $("<div class='homecontentnavigationdiv'></div>");
    contentNavigationElems.append(homeNavigationDiv("JKZS", "健康之声"));
    var contentWDGSZRLFElems = $("<div class='homenavigationother2div'></div>")
        .append(homeNavigationDiv('WDGS', '我的故事'))
        .append(homeNavigationDiv('ZRLF', '自然疗法'));
    contentNavigationElems.append(contentWDGSZRLFElems);
    var contentJJYSJKZXElems = $("<div class='homenavigationother2div'></div>")
        .append(homeNavigationDiv('JJYS', '季节养身'))
        .append(homeNavigationDiv('JKZX', '健康资讯'));
    contentNavigationElems.append(contentJJYSJKZXElems);

    contentBodyElems.append(contentViewElems).append($("<div class='contentblock'></div>")).append(contentNavigationElems);
    $("#contentbodydiv").html(contentBodyElems);
    getHomeUserView();
}
function homeEmptyViewElems(title) {
    var elem = $("<div class='homeviewhistorylinediv'></div>")
        .append($("<div class='homeviewhistorylinetitle'>" + title + "</div>"))
        .append($("<div class='homeviewhistorylinepointdiv'><div class='homeviewhistorylinepoint'></div></div>"))
        .append($("<div class='homeviewhistorylinecontentdiv'>" +
                    "<div class='homeviewhistorylinecontent'>您尚未浏览相关内容</div>" +
                "</div>"));
    return elem;
}
/*良方*/
function searchPrescription(orderId) {
    var prescription = $("#prescriptioninput").val();
    var isPrescription = typeof(prescription) == "undefined" || !prescription || prescription == "";
    if(!isPrescription){
        createLiangfangPage("LIST", 1, 5, prescription, orderId);
    }
}
function insertLiangfangHeader(role, prescription) {
    var headerElems = $("<div class='liangfangblackheader'></div>");
    headerElems
        .append($("<div class='liangfangblackheaderimg' onclick='gotoBackPage(\"" + role + "\")'><img src='/image/common/back.png'></div>"))
    if (role == "LIST"){
        headerElems
            .append($("<div class='liangfangprescriptiondiv'>" +
                "<input id='prescriptioninput' data-role='none' class='liangfangprescriptioninput' type='text' name='prescription'/>" +
                "</div>"))
            .append($("<div class='liangfangprescriptionbutton' onclick='searchPrescription(0)'>搜索</div>"));

    }else if (role == "DETAIL"){
        headerElems
            .append($("<div class='liangfangheadertitle_detail'>偏方</div>"))
            .append($("<div class='liangfangheadershare_detail' onclick='createSCFXPopup()'><img src='/image/common/more.png'></div>"));
    }else if(role == "DETAIL_IMAGE"){
        headerElems
            .append($("<div class='liangfangheadertitle_detailimage'>相关用料</div>"))
    }
    $("#header").html(headerElems);
    if (role == "LIST"){
        $("#prescriptioninput").val(prescription);
    }
}
function insertLiangfangContent(role, page, pageSize, prescription, order) {
    var liangfangContentBodyElems = $("<div class='liangfangcontentbodydiv'></div>");
    if(role == "LIST"){
        $.cookie('orderId', order, {expires: 7, path: '/'});
        var liangfangSearchOrderElems = $("<div class='liangfangsearchcountdiv'></div>")
            .append("<div class='liangfangsearchcountorderdiv'>" +
                    "<div id='liangfangsearchcountorder' class='liangfangsearchcountorder'>默认排序</div>" +
                    "<div class='liangfangsearchcountimg' onclick='createOrderPopup()'><img src='/image/common/select.png'/></div>" +
                "</div>")
            .append($("<div id='liangfangsearchcountresult' class='liangfangsearchcountresult'></div>"));
        var liangfangContentListElems = $("<div id='liangfangcontentlist' class='liangfangcontentlistdiv'></div>");
        var liangfangRelationElems = $("<div class='liangfangcontentrelationdiv'></div>")
            .append("<div class='liangfangrelationdiv'>" +
                    "<div class='liangfangrelationtitle'>相关疾病</div>" +
                    "<div id='liangfangrelationdiseaselist' class='liangfangrelationlist'></div>" +
                "</div>")
            .append("<div class='liangfangrelationdiv'>" +
                "<div class='liangfangrelationtitle'>相关症状</div>" +
                "<div id='liangfangrelationsymptomlist' class='liangfangrelationlist'></div>" +
                "</div>")
            .append("<div class='liangfangrelationdiv'>" +
                "<div class='liangfangrelationtitle'>自然疗法</div>" +
                "<div id='liangfangrelationnataropathylist' class='liangfangrelationlist'></div>" +
                "</div>");
        liangfangContentBodyElems.append(liangfangSearchOrderElems).append(liangfangContentListElems)
            .append(createContentPageElems("liangfang", prescription)).append(liangfangRelationElems);
    }else if (role == "DETAIL"){
        liangfangContentBodyElems
            .append($("<div id='liangfangcontentdetailtitle'></div>"))
            .append($("<div id='liangfangcontentdetailauthor'></div>"))
            .append($("<div id='liangfangcontentdetailstory'></div>"))
            .append($("<div class='liangfangcontentdetailplacediv'></div>"))
            .append($("<div class='liangfangcontentdetailimagediv'>" +
                        "<div id='liangfangcontentdetailimage'></div>" +
                        "<div class='liangfangcontentdetailbutton' onclick='createLiangfangPage(\"DETAIL_IMAGE\", 0, 0, " + prescription + ", 0)'>" +
                        "查看相关用料图片</div>" +
                    "</div>"))
            .append($("<div class='liangfangcontentdetailmorediv'>" +
                        "<div class='liangfangcontentdetailmore'>" +
                            "<div class='liangfangcontentdetailmoretitle'>制作用法</div>" +
                            "<div id='liangfangcontentdetailmore_zzyf' class='liangfangcontentdetailmorecontent'></div>" +
                        "</div>" +
                        "<div class='liangfangcontentdetailmore'>" +
                            "<div class='liangfangcontentdetailmoretitle'>注意事项</div>" +
                            "<div id='liangfangcontentdetailmore_zysx' class='liangfangcontentdetailmorecontent'></div>" +
                        "</div>" +
                        "<div class='liangfangcontentdetailmore'>" +
                            "<div class='liangfangcontentdetailmoretitle'>相关疾病</div>" +
                            "<div id='liangfangcontentdetailmore_xgjb' class='liangfangcontentdetailmorecontent'></div>" +
                        "</div>" +
                        "<div class='liangfangcontentdetailmore'>" +
                            "<div class='liangfangcontentdetailmoretitle'>适应症状</div>" +
                            "<div id='liangfangcontentdetailmore_syzz' class='liangfangcontentdetailmorecontent'></div>" +
                        "</div>" +
                    "</div>"))
            .append($("<div class='liangfangcontentdetailplacediv'></div>"))
            .append($("<div class='liangfangcontentdetailcommentdiv'>" +
                        "<div class='liangfangcontentdetailcommenttitlediv'>" +
                            "<div class='liangfangcontentdetailcommenttitlespace'></div>" +
                            "<div id='liangfangcontentdetailcommenttitle'></div>" +
                            "<div class='liangfangcontentdetailcommenttitleimg' onclick='showLiangfangComment(" + prescription + ")'><img src='/image/common/detail.png'/></div>" +
                        "</div>" +
                        "<div id='liangfangcontentdetailcommentlistdiv' class='liangfangcontentdetailcommentlistdiv'>" +
                            "<div id='liangfangcontentdetailcommentlist'></div>" +
                            "<div class='liangfangcontentdetailcommentbuttondiv'><div class='liangfangcontentdetailcommentbutton' onclick='createWYPLPopup()'>我要评论</div></div>" +
                        "</div>" +
                        "<div class='liangfangcontentdetailcommentbackdiv'>" +
                            "<div onclick='gotoBackPage(\"DETAIL\")' class='liangfangcontentdetailcommentbackbutton'>换一个偏方</div>" +
                        "</div>" +
                    "</div>"))
            .append($("<div class='liangfangcontentdetailrelationdiv'>" +
                        "<div class='liangfangrelationtitle'>相关音频</div>" +
                        "<div id='liangfangrelationvideolist' class='liangfangrelationlist'></div>" +
                        "<div class='liangfangrelationtitle'>相关链接</div>" +
                        "<div id='liangfangrelationlinklist' class='liangfangrelationlist'></div>" +
                    "</div>"));

    }else if (role == "DETAIL_IMAGE"){
        liangfangContentBodyElems.append($("<div id='liangfangmateriallistdiv'></div>"));
    }
    $("#contentbodydiv").html(liangfangContentBodyElems);
    if(role == "LIST"){
        getLiangfangList(prescription, order, page, pageSize);
    }else if(role == "DETAIL"){
        getLiangfangDetail(prescription);
    }else if (role == "DETAIL_IMAGE"){
        getLiangfangMaterial(prescription);
    }
}
function showLiangfangComment(prescriptionId) {
    if($('#liangfangcontentdetailcommentlistdiv').is(":hidden")){
        $('#liangfangcontentdetailcommentlistdiv').show();
        getLiangfangComment(prescriptionId);
    }else{
        $('#liangfangcontentdetailcommentlistdiv').hide();
    }
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
        }else if(article == "JKZX" || article == "ZRLF" || article == "WDGS"){
            if(article == "WDGS"){
                contentBodyElems
                    .append($("<div class='articledetailcontentdiv'>" +
                            "<div class='wdgsarticledetailtitlediv'>" +
                                "<div id='articledetailcontenttitle' class='wdgsarticledetailcontenttitle'></div>" +
                                "<div class='wdgsarticledetailcontentbutton'>" +
                                    "<div id='wdgsarticledetailcontentbutton' class='jjysjkzscirclediv'><div class='jjysjkzstrianglediv'></div></div>" +
                                "</div>" +
                            "</div>" +
                            "<div id='articledetailcontentvideo'></div>" +
                            "<div id='articledetailcontentimage'></div>" +
                            "<div id='articledetailcontenttext'></div>" +
                        "</div>"))
            }else{
                contentBodyElems
                    .append($("<div class='articledetailcontentdiv'>" +
                        "<div id='articledetailcontenttitle' class='articledetailcontenttitle'></div>" +
                        "<div id='articledetailcontentvideo'></div>" +
                        "<div id='articledetailcontentimage'></div>" +
                        "<div id='articledetailcontenttext'></div>" +
                        "</div>"))
            }
            contentBodyElems
                .append($("<div class='articledetailthumbdiv'>" +
                            "<div class='articledetailthumbimg'><img src='/image/common/thumb.png'></div>" +
                            "<div class='articledetailthumblabel'>写得好,赞一个</div>" +
                            "<div id='articledetailthumbtext' class='articledetailthumbtext'></div>" +
                        "</div>"))
                .append($("<div class='articledetailrelationlinkdiv'>" +
                            "<div class='articledetailrelationtitle'>相关链接</div>" +
                            "<div id='articledetailrelationlist'></div>" +
                        "</div>"));
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
        }else if(article == "JKZX" || article == "ZRLF" || article == "WDGS"){
            getArticleDetail(article, articleId);
        }
    }
}
function insertUserinfoHeader(){
    $.cookie('lastUserClick', "", {expires: 1, path: '/'});
    var headerElems = $("<div class='articleblackheader'></div>");
    headerElems
        .append($("<div class='articleblackheaderback' onclick='gotoBackPage(\"LIST\")'><img src='/image/common/back.png'></div>"))
        .append($("<div class='articleblackheadertitle'>个人中心</div>"));
    $("#header").html(headerElems);
}
function insertUserinfoContent(){
    var userInfoElems = $("<div class='userinfodiv'></div>");
    var userOpperateElems = $("<div id='useropperatediv' class='useropperatediv'></div>")
        .append(userOpperateDiv("qiufang", "我的求方"))
        .append(userOpperateDiv("xianfang", "我的献方"))
        .append(userOpperateDiv("shoucang", "我的收藏"))
        .append(userOpperateDiv("pinglun", "我的评论"));
    userInfoElems
        .append($("<div id='userinfoicon'></div>"))
        .append($("<div id='userinfoname'></div>"))
        .append(userOpperateElems);

    var userinfoconentElems = $("<div class='userinfocontentdiv'></div>")
        .append(userInfoElems)
        .append($("<div id='usercenter' class='usercenter'></div>"))
        .append($("<div class='userviewhistorydiv'>" +
                    "<div class='userviewhistorytitle'>最近浏览</div>" +
                    "<div id='userviewhistorylist'></div>" +
                    "<div class='userviewhistorymorebuttondiv'>" +
                        "<div class='userviewhistorymorebutton'>" +
                            "<div class='userviewhistorybuttontext'>更多</div>" +
                            "<div class='userviewhistorybuttonimg'><img src='/image/user/more.png' /></div>" +
                        "</div>" +
                    "</div>" +
                "</div>"));
    $("#contentbodydiv").html(userinfoconentElems);
    $("#contentfooterdiv").hide();
    $("#usercenter").hide();
    getUserInfo();
    getUserView();
}
function userOpperateDiv(id, title) {
    var onclickFun = "showUserCenter(\"" + id + "\")";
    var divid = id + "25div";
    var imgid = id + "image";
    var titleid = id + "title";
    var buttonid = id + "button";
    var elem = $("<div id='" + divid + "' class='useropperateunclick25div' onclick='" + onclickFun + "'></div>")
        .append($("<div class='useropperate25img'><img id='" + imgid + "' src='/image/user/" + id + ".png'/></div>"))
        .append($("<div class='useropperate25title'>" +
                    "<div id='" + titleid + "' class='userunclicktitle'>" + title + "</div>" +
                    "<div class='userbuttondiv'><div id='" + buttonid + "' class='userunclickbutton'></div></div>" +
                "</div>"));

    return elem;
}
function showUserCenter(module) {
    getUserDetail(module);
    var lastUserClick = $.cookie('lastUserClick');
    if(typeof(lastUserClick) == "undefined" || !lastUserClick || lastUserClick == ""){
        $("#" + module + "25div").removeClass("useropperateunclick25div").addClass("useropperateclick25div");
        $("#" + module + "image").attr("src", "/image/user/" + module + "_click.png");
        $("#" + module + "title").removeClass("userunclicktitle").addClass("userclicktitle");
        $("#" + module + "button").removeClass("userunclickbutton").addClass("userclickbutton");
        $("#usercenter").show();
    }else if(lastUserClick != module){
        $("#" + module + "25div").removeClass("useropperateunclick25div").addClass("useropperateclick25div");
        $("#" + module + "image").attr("src", "/image/user/" + module + "_click.png");
        $("#" + module + "title").removeClass("userunclicktitle").addClass("userclicktitle");
        $("#" + module + "button").removeClass("userunclickbutton").addClass("userclickbutton");
        $("#" + lastUserClick + "25div").removeClass("useropperateclick25div").addClass("useropperateunclick25div");
        $("#" + lastUserClick + "image").attr("src", "/image/user/" + lastUserClick + ".png");
        $("#" + lastUserClick + "title").removeClass("userclicktitle").addClass("userunclicktitle");
        $("#" + lastUserClick + "button").removeClass("userclickbutton").addClass("userunclickbutton");
        $("#usercenter").show();
    }else if(lastUserClick == module){
        if($("#usercenter").is(":hidden")){
            $("#usercenter").show();
            $("#" + module + "25div").removeClass("useropperateunclick25div").addClass("useropperateclick25div");
            $("#" + module + "image").attr("src", "/image/user/" + module + "_click.png");
            $("#" + module + "title").removeClass("userunclicktitle").addClass("userclicktitle");
            $("#" + module + "button").removeClass("userunclickbutton").addClass("userclickbutton");
        }else{
            $("#usercenter").hide();
            $("#" + lastUserClick + "25div").removeClass("useropperateclick25div").addClass("useropperateunclick25div");
            $("#" + lastUserClick + "image").attr("src", "/image/user/" + lastUserClick + ".png");
            $("#" + lastUserClick + "title").removeClass("userclicktitle").addClass("userunclicktitle");
            $("#" + lastUserClick + "button").removeClass("userclickbutton").addClass("userunclickbutton");
        }
    }
    $.cookie('lastUserClick', module, {expires: 1, path: '/'});
}

/*底部*/
function createFooter(){
    var videoUrl = $.cookie('videoUrl');
    var videoTitle = $.cookie('videoTitle');
    if(typeof(videoUrl) == "undefined" || !videoUrl || videoUrl == ""){
        $("#footer").hide();
    }else{
        if(isFirstLoad){
            $("div.footervideotitle").html(videoTitle);
            $("#audiomp3").attr("src", videoUrl);
            isFirstLoad = false;
        }
    }
}