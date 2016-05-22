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
    }else{
        $("#wdgsarticledetailcontentbutton").hide();
    }
    if(data.category == "ZRLF"){
        $('#articledetailcontentvideo').html($("<video controls></video>").attr("src", data.videoUrl));
    }else{
        $('#articledetailcontentvideo').hide();
    }
    $('#articledetailcontentimage').html($("<img />").attr("src", data.imageUrl));
    $('#articledetailcontenttext').html(data.articleContent);
    $('#articledetailthumbtext').html(data.thumbCount + "个表扬");

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
    $('#articledetailrelationlist').html(relationlinkListEnums);

}
function processRitucharyaPaginationData(data) {
    var contentListTableElems = $("<table id='jjysjkzslisttable'><tbody></tbody></table>");
    var ritucharyaList = data.ritucharyaList;
    var pagination = data.pagination;

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
            var onclickFunc = "createLiangfangPage(\"DETAIL\", 0, 0, " + prescription.prescriptionId + ", 0)";
            var score = prescription.prescriptionScore + "分";
            var prescriptionTrEnum = $("<tr></tr>")
                .append($("<td><div class='contentpointdiv'></div></td>"))
                .append($("<td><div onclick='" + onclickFunc + "'>" + prescription.prescriptionTitle + "</div></td>"))
                .append($("<td><div>综合评分:</div></td>"))
                .append($("<td><div>" + score + "</div></td>"));
            prescriptionListEnums.append(prescriptionTrEnum);
        }
        $("#liangfangcontentlist").html(prescriptionListEnums);

        var diseaselinkList = data.diseaselinkList;
        var diseaselinkListEnums = $("<table><tbody></tbody></table>");
        for(var di=0; di<diseaselinkList.length; di++){
            var link = diseaselinkList[di];
            var trEnum = $("<tr></tr>")
                .append($("<td><div class='contentpointdiv'></div></td>"))
                .append($("<td><div>" + link.relationTitle + "</div></td>"));
            diseaselinkListEnums.append(trEnum);
        }
        $("#liangfangrelationdiseaselist").html(diseaselinkListEnums);

        var symptomlinkList = data.symptomlinkList;
        var symptomlinkListEnums = $("<table><tbody></tbody></table>");
        for(var si=0; si<symptomlinkList.length; si++){
            var link = symptomlinkList[si];
            var trEnum = $("<tr></tr>")
                .append($("<td><div class='contentpointdiv'></div></td>"))
                .append($("<td><div>" + link.relationTitle + "</div></td>"));
            symptomlinkListEnums.append(trEnum);
        }
        $("#liangfangrelationsymptomlist").html(symptomlinkListEnums);

        var nataropathylinkList = data.nataropathylinkList;
        var nataropathylinkListEnums = $("<table><tbody></tbody></table>");
        for(var ni=0; ni<nataropathylinkList.length; ni++){
            var onclickFunc = "gotoArticleDetailPage(\"ZRLF\", " + link.relationId + ")";

            var link = nataropathylinkList[ni];
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
    $("#liangfangcontentdetailauthor").html(data.author + "&nbsp;&nbsp;" + data.createTime);
    $("#liangfangcontentdetailstory").html(data.prescriptionStory);
    $("#liangfangcontentdetailimage").html($("<img src='" + data.imageUrl + "'/>"));
    $("#liangfangcontentdetailmore_zzyf").html(data.productionAndUsage);
    $("#liangfangcontentdetailmore_zysx").html(data.attentions);
    $("#liangfangcontentdetailmore_xgjb").html(data.diseaseNames);
    $("#liangfangcontentdetailmore_syzz").html(data.indication);
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
                "<div class='liangfangcommenttime'>" +  comment.commentTime + "</div></div>" +
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

}
function processWordStatisticsData(data) {
    var wordSearchList = data.wordSearchList;
    mockBubbleChart(wordSearchList);
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