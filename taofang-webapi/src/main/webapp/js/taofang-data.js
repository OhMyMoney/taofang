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
            $("#contentbodypage").hide();
        }else{
            contentListTableElems = $("<table id='jjysjkzslisttable'><tbody></tbody></table>");
            for(var i=0; i<articleList.length; i++){
                var article = articleList[i];
                var videoPlayButtonId = "videoPlayButton" + article.articleId;
                var videoPlayTitleId = "videoPlayTitle" + article.articleId;
                var onclickFunc = "playVideo(\"" + article.videoUrl + "\", \"" + article.articleTitle + "\", " + article.articleId + ")";

                var contentListTrElems = $("<tr></tr>")
                    .append($("<td><div class='jjysjkzscirclediv' onclick='" + onclickFunc + "'><div id='" + videoPlayButtonId + "' class='jjysjkzstrianglediv'></div></div></td>"))
                    .append($("<td><div id='" + videoPlayTitleId + "' class='jjysjkzsvideotitle1'>" + article.articleTitle + "</div></td>"))
                    .append($("<td><img src='/image/common/link.png' /></td>"));
                contentListTableElems.append(contentListTrElems);
            }
            // 翻页区域
            processPage(pagination.page, pagination.totalPage);
        }
    }

    $(".contentbodylist").html(contentListTableElems);
}
function processArticleDetailData(data) {
    $('#articledetailcontenttitle').html(data.articleTitle);
    if(data.category == "WDGS" && data.videoUrl != ""){
        var onclickFunc = "playWDGSVideo(\"" + data.videoUrl + "\", \"" + data.articleTitle + "\")";
        $("#wdgsarticledetailcontentbutton").attr("onclick", onclickFunc);
        playWDGSVideo(data.videoUrl, data.articleTitle);
    }else{
        $("#wdgsarticledetailcontentbutton").hide();
    }
    if(data.category == "ZRLF"){
        $('#articledetailcontentvideo').html($("<video controls></video>").attr("src", data.videoUrl));
    }else{
        $('#articledetailcontentvideo').hide();
        $('#articledetailcontentimage').html($("<img />").attr("src", data.imageUrl));
    }
    $('#articledetailcontenttext').html(data.articleContent);
    if(data.category == "WDGS"){
        var onClickFunc =  "postArticleThumb(" + data.articleId + ",\"" + data.category + "\")";
        $('div.articledetailthumbimg').attr("onclick", onClickFunc);
        $('#articledetailthumbtext').html(data.thumbCount + "个表扬");
    }
    if(data.category != "ZRLF"){
        var relationlinkList = data.relationlinkList;
        var relationlinkListEnums = $("<table><tbody></tbody></table>");
        for(var i=0; i< relationlinkList.length; i++){
            var link = relationlinkList[i];
            var onclickFunc="gotoArticleDetailPage(\"" + link.relationArticleCategory + "\", " + link.relationArticleId + ")";
            var trEnum = $("<tr></tr>")
                .append($("<td><div class='contentpointdiv'></div></td>"));
            if(data.category == "WDGS"){
                trEnum.append($("<td onclick='" + onclickFunc + "'><div>" + link.relationTitle + "</div></td>"));
            }else{
                trEnum.append($("<td><div>" + link.relationTitle + "</div></td>"));
            }
            relationlinkListEnums.append(trEnum);
        }
    }
    $('#articledetailrelationlist').html(relationlinkListEnums);
    // 增加浏览
    if(data.category == "WDGS" || data.category == "JKZX" || data.category == "ZRLF"){
        postUserView(data.category, data.articleId, data.articleTitle)
    }
}
function processRitucharyaPaginationData(data) {
    var contentListTableElems = $("<table id='jjysjkzslisttable'><tbody></tbody></table>");
    var ritucharyaList = data.ritucharyaList;
    var pagination = data.pagination;

    for(var i=0; i<ritucharyaList.length; i++){
        var ritucharya = ritucharyaList[i];
        var videoPlayButtonId = "videoPlayButton" + ritucharya.ritucharyaId;
        var videoPlayTitleId = "videoPlayTitle" + ritucharya.ritucharyaId;
        var onclickFunc = "playRitucharyaVideo(\"" + ritucharya.videoUrl + "\", \"" + ritucharya.ritucharyaTitle + "\", \"" + ritucharya.ritucharya + "\", " + ritucharya.ritucharyaId + ")";

        var contentListTrElems = $("<tr></tr>")
            .append($("<td><div class='jjysjkzscirclediv' onclick='" + onclickFunc + "'><div id='" + videoPlayButtonId + "' class='jjysjkzstrianglediv'></div></div></td>"))
            .append($("<td><div id='" + videoPlayTitleId + "' class='jjysjkzsvideotitle1'>" + ritucharya.ritucharyaTitle + "</div></td>"))
            .append($("<td><img src='/image/common/link.png' /></td>"));
        contentListTableElems.append(contentListTrElems);
    }
    // 翻页区域
    processPage(pagination.page, pagination.totalPage);

    $(".contentbodylist").html(contentListTableElems);
    // 循环播放
    playRitucharyaVideo(ritucharyaList[0].videoUrl, ritucharyaList[0].ritucharyaTitle, ritucharyaList[0].ritucharya, ritucharyaList[0].ritucharyaId);
    // playVideo(ritucharyaList[0].videoUrl, ritucharyaList[0].ritucharyaTitle, ritucharyaList[0].ritucharyaId);
}
function processLiangfangPaginationData(data) {
    var pagination = data.pagination;
    $("#liangfangsearchcountresult").html("共 " + pagination.totalCount + " 条搜索结果");
    if(pagination.totalCount == 0){
        $("#liangfangcontentlist").html($("<div class='liangfangcontentemptylistdiv'><div class='liangfangcontentemptylist'>没有搜索结果</div></div>"));
    }else{
        var lastPageDate = "liangfang;" + pagination.page + ";" + pagination.pageSize + ";" + data.prescription + ";" + data.orderId;
        $.cookie('lastPage', lastPageDate, {expires: 7, path: '/'});
        var prescriptionList = data.prescriptionList;
        var prescriptionListEnums = $("<table><tbody></tbody></table>");
        for(var ip=0; ip<prescriptionList.length; ip++){
            var prescription = prescriptionList[ip];
            var currId = (pagination.page - 1) * pagination.pageSize + ip;
            var onclickFunc = "gotoLiangfangDetailPage(\"" + data.prescription + "\", " + currId + ", " + pagination.totalCount + ", " + prescription.prescriptionId + ")";
            var score = prescription.prescriptionScore + "分";
            var prescriptionTrEnum = $("<tr></tr>")
                .append($("<td><div class='contentpointdiv'></div></td>"))
                .append($("<td><div onclick='" + onclickFunc + "'>" + prescription.prescriptionTitle + "</div></td>"))
                .append($("<td><div>综合评分:</div></td>"))
                .append($("<td><div>" + score + "</div></td>"));
            prescriptionListEnums.append(prescriptionTrEnum);
        }
        $("#liangfangcontentlist").html(prescriptionListEnums);

        // var diseaselinkList = data.diseaselinkList;
        // var diseaselinkListEnums = $("<table><tbody></tbody></table>");
        // for(var di=0; di<diseaselinkList.length; di++){
        //     var link = diseaselinkList[di];
        //     var trEnum = $("<tr></tr>")
        //         .append($("<td><div class='contentpointdiv'></div></td>"))
        //         .append($("<td><div>" + link.relationTitle + "</div></td>"));
        //     diseaselinkListEnums.append(trEnum);
        // }
        // $("#liangfangrelationdiseaselist").html(diseaselinkListEnums);
        //
        // var symptomlinkList = data.symptomlinkList;
        // var symptomlinkListEnums = $("<table><tbody></tbody></table>");
        // for(var si=0; si<symptomlinkList.length; si++){
        //     var link = symptomlinkList[si];
        //     var trEnum = $("<tr></tr>")
        //         .append($("<td><div class='contentpointdiv'></div></td>"))
        //         .append($("<td><div>" + link.relationTitle + "</div></td>"));
        //     symptomlinkListEnums.append(trEnum);
        // }
        // $("#liangfangrelationsymptomlist").html(symptomlinkListEnums);

        var nataropathylinkList = data.nataropathylinkList;
        var nataropathylinkListEnums = $("<table><tbody></tbody></table>");
        for(var ni=0; ni<nataropathylinkList.length; ni++){
            var link = nataropathylinkList[ni];
            var onclickFunc = "gotoArticleDetailPage(\"ZRLF\", " + link.relationId + ")";

            var trEnum = $("<tr></tr>")
                .append($("<td><div class='contentpointdiv'></div></td>"))
                .append($("<td onclick='" + onclickFunc + "'><div>" + link.relationTitle + "</div></td>"));
            nataropathylinkListEnums.append(trEnum);
        }
        $("#liangfangrelationnataropathylist").html(nataropathylinkListEnums);
    }

    // 翻页区域
    processPage(pagination.page, pagination.totalPage);
}
function processLiangfangDetailData(data) {
    $("#liangfangcontentdetailcommentlistdiv").hide();
    $("#liangfangcontentdetailtitle").html(data.prescriptionTitle);
    if(data.videoUrl != ""){
        var onclickFunc = "playWDGSVideo(\"" + data.videoUrl + "\", \"" + data.prescriptionTitle + "\")";
        $("#wdgsarticledetailcontentbutton").attr("onclick", onclickFunc).show();
        if(!$("#footer").is(":hidden")){
            $('#wdgsarticledetailcontentbutton div').attr("class", "jjysjkzstrianglediv");
            $("#footervideobutton").attr("class", "footervideostop");
            document.getElementById("audiomp3").pause();
        }
        playWDGSVideo(data.videoUrl, data.prescriptionTitle);
    }else{
        $("#wdgsarticledetailcontentbutton").hide();
    }
    if(data.author == "" && data.createTime == ""){
        $("#liangfangcontentdetailauthor").hide();
    }else{
        $("#liangfangcontentdetailauthor").html(data.author + "&nbsp;&nbsp;" + data.createTime);
    }
    $("#liangfangcontentdetailstory").html(data.prescriptionStory);
    $("#liangfangcontentdetailimage").html($("<img src='" + data.imageUrl + "'/>"));
    if(data.productionAndUsage == ""){
        $("#liangfangcontentdetailmore_zzyf_div").hide();
    }else{
        $("#liangfangcontentdetailmore_zzyf").html(data.productionAndUsage);
    }
    if(data.attentions == ""){
        $("#liangfangcontentdetailmore_zysx_div").hide();
    }else{
        $("#liangfangcontentdetailmore_zysx").html(data.attentions);
    }
    if(data.diseaseNames == ""){
        $("#liangfangcontentdetailmore_xgjb_div").hide();
    }else{
        $("#liangfangcontentdetailmore_xgjb").html(data.diseaseNames);
    }
    if(data.indication == ""){
        $("#liangfangcontentdetailmore_syzz_div").hide();
    }else{
        $("#liangfangcontentdetailmore_syzz").html(data.indication);
    }
    $("#liangfangcontentdetailcommenttitle").html("查看用户评论(" + data.commentCount +")");

    var videolinkList = data.videolinkList;
    var videolinkListEnums = $("<table><tbody></tbody></table>");
    for(var vi=0; vi<videolinkList.length; vi++){
        var link = videolinkList[vi];
        var videoPlayButtonId = "videoPlayButton" + link.relationId;
        var videoPlayTitleId = "videoPlayTitle" + link.relationId;
        var onclickFunc = "playVideo(\"" + link.relationLink + "\", \"" + link.relationTitle + "\", " + link.relationId + ")";
        var trElems = $("<tr></tr>")
            .append($("<td><div class='contentpointdiv'></div></td>"))
            .append($("<td><div id='" + videoPlayTitleId + "' class='jjysjkzsvideotitle1'>" + link.relationTitle + "</div></td>"))
            .append($("<td><div class='jjysjkzscirclediv' onclick='" + onclickFunc + "'><div id='" + videoPlayButtonId + "' class='jjysjkzstrianglediv'></div></div></td>"));

        videolinkListEnums.append(trElems);
    }
    $("#liangfangrelationvideolist").html(videolinkListEnums);

    var contentlinkList = data.contentlinkList;
    var contentlinkListEnums = $("<table><tbody></tbody></table>");
    for(var ci=0; ci<contentlinkList.length; ci++){
        var link = contentlinkList[ci];
        var trEnum = $("<tr></tr>")
            .append($("<td><div class='contentpointdiv'></div></td>"))
            .append($("<td><div>" + link.relationTitle + "</div></td>"));
        contentlinkListEnums.append(trEnum);
    }
    $("#liangfangrelationlinklist").html(contentlinkListEnums);
    // 换一个偏方按钮的方法
    $("#liangfangnextdetailbutton").attr("onclick", "nextLiangfangDetail()");
    // 增加浏览
    postUserView("liangfang", data.prescriptionId, data.prescriptionTitle)
}
function processLiangfangCommentData(data) {
    var commentList = data.commentList;
    var commentsEnum = $("<table><tbody></tbody></table>");
    for(var i=0; i<commentList.length; i++){
        var comment = commentList[i];
        var commentTrEnum = $("<tr></tr>");
        commentTrEnum
            .append($("<td><div><img src='" + comment.memberIcon + "' /></div></td>"))
            .append($("<td><div class='liangfangcommentmemberdiv'>" +
                "<div class='liangfangcommentmember'>" + comment.memberName + "</div>" +
                "<div class='liangfangcommentscore'>" +  comment.score + "分" + "</div>" +
                // "<div class='liangfangcommenttime'>" +  comment.commentTime + "</div></div>" +
                "<div class='liangfangcommentexperience'>" + comment.commentContent + "</div></td>"));
        commentsEnum.append(commentTrEnum);
    }
    $("#liangfangcontentdetailcommentlist").html(commentsEnum);
}
function processLiangfangMaterialData(data) {
    var imagePageDetail = "liangfang_image;" + data.prescriptionId;
    $.cookie('imagePageDetail', imagePageDetail, {expires: 7, path: '/'});
    $("#liangfangmateriallistdiv").html(data.material);
}
function processUserInfoData(data) {
    $("#userinfoicon").html($("<img />").attr("src", data.icon));
    $("#userinfoname").html(data.userName);
}
function processUserDetailData(data) {
    var userModuleList = data.userModuleList;
    if(userModuleList.length == 0){
        insertEmptyModuleElems();
    }else{
        var userCenterTableElem = $("<table><tbody></tbody></table>");
        for(var i=0; i<userModuleList.length;i++){
            var moduleInfo = userModuleList[i];
            var userCenterTrElem = $("<tr></tr>");
            userCenterTrElem.append("<td><div class='usercenterpointdiv'></div></td>")
                .append("<td><div class='usercentertitlediv'>" + moduleInfo.moduleTitle + "</div></td>")
                .append("<td><div class='usercentertimediv'>" + moduleInfo.dateTime.split(" ")[0] +"</div></td>");
            userCenterTableElem.append(userCenterTrElem);
        }
        $("#usercenter").html(userCenterTableElem);
    }
}
function processUserViewData(data) {
    var viewList = data.viewList;
    if(viewList.length == 0){
        $("#userviewhistorylist").html($("<div class='userviewhistorylistemptyblockdiv'></div>" +
            "<div class='userviewhistorylistemptydiv'>你尚未浏览相关内容</div>"));
    }
}
function processHomeUserViewData(data) {
    var viewList = data.viewList;
    var len = viewList.length > 10 ? 10 : viewList.length;
    var homeListViewHistoryElems = $("<div class='homelistviewdiv'></div>");
    for(var i=0; i<len; i++){
        var onclickFunc;
        var view = viewList[i];
        if(view.categoryName == 'liangfang'){
            onclickFunc = "createLiangfangPage(\"DETAIL\", 0, 0, " + view.articleId + ", 0)";
        }else{
            onclickFunc = "gotoArticleDetailPage(\"" + view.categoryName + "\"," + view.articleId + ")";
        }
        var homeListViewHistoryElem = $("<div class='homelistview'></div>")
            .append($("<div class='homelistviewtimediv'>" +
                        "<div class='homelistviewtimedayweek'>" +
                            "<div class='homelistviewtimeday'>" + view.viewDateDay + "</div>" +
                            "<div class='homelistviewtimeweek'>" + view.viewDateWeek + "</div>" +
                        "</div>" +
                        "<div class='homelistviewtimeyearmonth'>" + view.viewDateYear + "." + view.viewDateMonth + "</div>" +
                    "</div>"))
            .append($("<div class='homelistviewpointdiv'><div class='homelistviewpoint'></div></div>"))
            .append($("<div class='homelistviewcontentdiv'>" +
                        "<div class='homelistviewcontent'>" +
                            "<div class='homelistviewtitle'>上次浏览的" + (view.categoryName == 'liangfang' ? '偏方' : '文章') + "</div>" +
                            "<div class='homelistviewtext' onclick='" + onclickFunc + "'>" + view.articleTitle + "</div>" +
                        "</div>" +
                        "<div class='homelistviewimage'><img src='/image/home/time_view.png'/></div>" +
                    "</div>"));
        homeListViewHistoryElems.append(homeListViewHistoryElem);
    }
    $("#homecontentviewdiv").html(homeListViewHistoryElems);
}
function processWordStatisticsData(data) {
    var wordSearchList = data.wordSearchList;
    mockBubbleChart(wordSearchList);
}
function processAdvertData(data) {
    var adList = data.advertisementList;
    var sWidth = $(document).width();
    var sHeight = sWidth * 0.4;
    var contentFooterAds = $("<div></div>");
    if(adList.length == 0){
        contentFooterAds
            .append($("<img src='/image/ads/yhxq.jpg' title='养护血气' />"))
            .append($("<img src='/image/ads/yhxb.jpg' title='养护细胞' />"))
            .append($("<img src='/image/ads/sypw.jpg' title='始于脾胃' />"));
        $("#footeradimg").html(contentFooterAds);
        $(".footeraddiv").attr("style", "height:" + (sHeight + 40) + "px");
        $(".footeraddiv .footeradimg").attr("style","width:"+ 3*sWidth + "px;height:" + sHeight + "px;");
        $(".footeraddiv .footeradimg img").attr("style","width:"+ sWidth + "px;height:" + sHeight + "px;");
    }else if(adList.length == 1){
        $("#footeradimg").html($("<img src='" + adList[0].adImage + "' title='" + adList[0].adTitle + "' />"));
        var sWidth = $(document).width();
        var sHeight = sWidth * 0.4;
        $("div.footeradbtn").hide();
        $(".footeraddiv").attr("style", "height:" + (sHeight + 20) + "px");
        $(".footeraddiv .footeradimg").attr("style","width:"+ sWidth + "px;height:" + sHeight + "px;");
        $(".footeraddiv .footeradimg img").attr("style","width:"+ sWidth + "px;height:" + sHeight + "px;");
        zBase.config.index=0;
    }else{
        for(var i=0; i<3; i++){
            var ad;
            if(i >= adList.length){
                ad = adList[0];
            }else{
                ad = adList[i];
            }
            contentFooterAds.append($("<img src='" + ad.adImage + "' title='" + ad.adTitle + "' />"));
        }
        $("#footeradimg").html(contentFooterAds);
        $(".footeraddiv").attr("style", "height:" + (sHeight + 40) + "px");
        $(".footeraddiv .footeradimg").attr("style","width:"+ 3*sWidth + "px;height:" + sHeight + "px;");
        $(".footeraddiv .footeradimg img").attr("style","width:"+ sWidth + "px;height:" + sHeight + "px;");
    }
}
function doUserViewData(data) {
    console.log(data);
}
function doUserCollectData(data) {
    if(data == "success"){
        createUserCollectResultPopup("收藏成功");
    }else{
        createUserCollectResultPopup("已经收藏");
    }
}
function processArticleThumbData(data) {
    $('#articledetailthumbtext').html(data + "个表扬");
}
function processSMSVCodeData(data) {
    console.log(data);
}
function doArticleThumbData(data) {
    var dataArr = data.split(";");
    if(dataArr[0] == "success"){
        showArticleThumbPopup("点赞成功");
        getArticleThumb(parseInt(dataArr[1]));
    }else{
        showArticleThumbPopup("您今天已经点赞");
    }
}
function insertEmptyModuleElems() {
    var lastUserClick = $.cookie('lastUserClick');
    var text = "暂时没有";
    if(lastUserClick == "qiufang"){
        text += "我的求方";
    }else if(lastUserClick == "xianfang"){
        text += "我的献方";
    }else if(lastUserClick == "shoucang"){
        text += "我的收藏";
    }else if(lastUserClick == "pinglun"){
        text += "我的评论";
    }
    text += "数据";
    $("#usercenter").html("<div class='usercenteremptyblockdiv'></div><div class='usercenteremptydiv'>" + text +"</div>");
}

function processNextJJYSVideoData(data) {
    if(data.ritucharyaId != 0){
        playRitucharyaVideo(data.videoUrl, data.ritucharyaTitle, data.ritucharya, data.ritucharyaId);
    }else{
        $("#footervideobutton").attr("class", "footervideostop");
        document.getElementById("audiomp3").pause();
    }
}