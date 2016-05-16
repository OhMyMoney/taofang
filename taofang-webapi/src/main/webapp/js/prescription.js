function backhomepage() {
    location.href = "../home.html";
}
function getPrescriptionPagination(prescription, order, page, pageSize) {
    $.cookie('PrescriptionName', prescription, {expires: 7, path: '/'});
    var url = ajaxBaseUrl + "/prescription?name=" + prescription + "&order=" + order + "&page=" + page + "&pageSize=" + pageSize;
    $.ajax({
        url: url,
        success: processPaginationData
    });
}
function prescriptionSearch() {
    var prescriptionName = $("#prescriptionname").val();
    if(prescriptionName != "" && prescriptionName != undefined){
        location.href = 'list.html?prescription=' + prescriptionName;
    }
}
function doPreviousPage() {
    var current = $("#page").find($("[class='currentpage']")).text().split("/")[0];
    var prescription = $.getUrlParam('prescription');
    if(parseInt(current) > 1){
        getPrescriptionPagination(prescription, "", parseInt(current) - 1, 5);
    }
}
function doNextPage() {
    var currentTotal = $("#page").find($("[class='currentpage']")).text().split("/");
    var current = currentTotal[0];
    var total = currentTotal[1];
    var prescription = $.getUrlParam('prescription');
    if(parseInt(current) < parseInt(total)){
        getPrescriptionPagination(prescription, "", parseInt(current) + 1, 5);
    }
}
function processPaginationData(data) {
    var totalCount = data.totalCount;
    $('#searchresult').html("共 " + totalCount + " 条搜索结果");
    if(totalCount == 0){
        // 翻页区域
        processPage(0, data.totalPage);
        var emptyEnum = $("<div class='emptylistdiv'><div class='emptylist'>没有搜索结果</div></div>")
        $("#pagecontentlist").html(emptyEnum);
    }else{
        // 翻页区域
        processPage(data.curPage, data.totalPage);
        var prescriptions = data.prescriptions;
        var prescriptionsEnum = $("<table><tbody></tbody></table>");
        for(var i=0; i<prescriptions.length; i++){
            var prescription = prescriptions[i];
            var onclickFunc = "showPrescriptionDetail(" + prescription.id + ",\"" + prescription.title + "\")";
            var score = prescription.score + "分";
            var prescriptionTrEnum = $("<tr></tr>");
            prescriptionTrEnum.append($("<td><div class='contentpointdiv'></div></td>"));
            prescriptionTrEnum.append($("<td><div class='contentlistdiv' onclick='" + onclickFunc + "'>" + prescription.title + "</div></td>"));
            prescriptionTrEnum.append($("<td><div class='contentscoretitlediv'>综合评分:</div></td>"));
            prescriptionTrEnum.append($("<td><div class='contentscorediv'>" + score + "</div></td>"));
            prescriptionsEnum.append(prescriptionTrEnum);
        }
        $("#pagecontentlist").html(prescriptionsEnum);
    }
}
function showPrescriptionDetail(id, title) {
    addUserView("prescription", title, id);
    location.href = "detail.html?id=" + id;
}
/*详细信息页面区域*/
function getPrescriptionDetail(id) {
    $.ajax({
        url: ajaxBaseUrl + "/prescription/" + id,
        success: processDetailData
    });
}
function showPrescriptionImages(){
    var id = $.cookie('PrescriptionId');
    location.href = "detailimage.html?id=" + id;
}
function showCommentDetail() {
    if($("#commentlistdiv").is(":hidden")){
        $("#commentlistdiv").show();
    }else{
        $("#commentlistdiv").hide();
    }
}
function prescriptionComment() {
    var id = $.cookie('PrescriptionId');
    var userName = $.cookie('userName');
    if(userName == "" || userName == undefined){
        var toTop = $("#commentbutton").offset().top;
        showpopup(toTop - 100);
        $("#msgDiv").html(inertPopupEmum());
    }
}
function backPrescriptionList(){
    location.href = "list.html?prescription=" + $.cookie('PrescriptionName');
}
function processDetailData(data) {
    $("#commentlistdiv").hide();
    var prescription = data.prescription;
    $("#contenttitle").html(prescription.title);
    var authorAndTime = prescription.createTime;
    if(prescription.author != ""){
        authorAndTime = prescription.author + "      " + authorAndTime;
    }
    $("#contentauthor").html(authorAndTime);
    $("#contentstory").html(prescription.story);
    $("#contentimage").html($("<img src='" + prescription.imageUrl + "' />"));
    $("#productionAndUsage").html(prescription.productionAndUsage);
    $("#attentions").html(prescription.attentions);
    $("#diseaseNames").html($("<p>" + prescription.diseaseNames + "</p>"));
    $("#indication").html(prescription.indication);
    $("#commenttitle").html("查看用户评论(" + prescription.commentCount +")");
    // 评论区域
    if(prescription.commentCount > 0){
        var commentsEnum = $("<table><tbody></tbody></table>")
        var comments = data.comments;
        for(var i=0; i<comments.length; i++){
            var comment = comments[i];
            var commentTrEnum = $("<tr></tr>");
            commentTrEnum.append($("<td><div><img src='" + comment.icon + "' /></div></td>"));
            commentTrEnum.append($("<td><div class='commentmemberdiv'>" +
                "<div class='commentmember'>" + comment.memberName + "</div>" +
                "<div class='commentscore'>" +  comment.score + "分" + "</div>" +
                "<div class='commenttitme'>" +  comment.createdDate + "</div></div>" +
                "<div class='commentexperiencediv'>" + comment.experience + "</div></td>"));
            commentsEnum.append(commentTrEnum);
        }
        $("#commentlist").html(commentsEnum);
    }
    // 相关音频
    var videoLinks = data.videoLinks;
    if(videoLinks.length == 0){
        $("#videodiv").hide();
    }else{
        $("#videoplay").attr("src", videoLinks[0].linkUrl);
    }
    var videoLinksEnum = $("<table><tbody></tbody></table>");
    for(var i=0; i<videoLinks.length; i++){
        var videoLink = videoLinks[i];
        var onclickFunc = "playVideo('" + videoLink.linkUrl + "', " + videoLink.id + ", '" + videoLink.title +  "')";
        var playimg = "playimg" + videoLink.id;
        var playtitle = "playtitle" + videoLink.id;
        var videoLinkTrEnum = $("<tr></tr>");
        videoLinkTrEnum.append("<td><div class='contentpointdiv'></div></td>");
        videoLinkTrEnum.append("<td><div id='" + playtitle + "' class='videotitle1'>" + videoLink.title +"</div></td>")
        videoLinkTrEnum.append("<td><div class='circlediv' onclick=\"" + onclickFunc + "\"><div id='" + playimg + "' class='trianglediv'></div></di></div></td>")
        videoLinksEnum.append(videoLinkTrEnum);
    }
    $("#relationvideo").html(videoLinksEnum);
    // 相关链接
    var contentLinks = data.contentLinks;
    var contentLinksEnum = $("<table><tbody></tbody></table>");
    for(var i=0; i<contentLinks.length; i++){
        var contentLink = contentLinks[i];
        var contentLinkTrEnum = $("<tr></tr>");
        contentLinkTrEnum.append("<td><div class='contentpointdiv'></div></td>");
        contentLinkTrEnum.append("<td><div><a data-role='none' class='contentlistdiv' href='" + contentLink.linkUrl + "'>" + contentLink.title +"</a></div></td>")
        contentLinksEnum.append(contentLinkTrEnum);
    }
    $("#relationlink").html(contentLinksEnum);
}
function playVideo(videoUrl, id, title) {
    var playimgClass = $('#playimg'+id).attr("class");
    if(playimgClass == "paralleldiv"){
        $('#playimg' + id).attr("class", "trianglediv");
        $('#playtitle' + id).attr("class", "videotitle1");
        var videoplay = document.getElementById("videoplay");
        videoplay.pause();
    }else{
        $('#videoplay').attr("src", videoUrl);
        $('div.circlediv div').attr("class", "trianglediv");
        $('#relationvideo table tbody tr td:nth-child(2) div').attr("class", "videotitle1");
        $('#playimg' + id).attr("class", "paralleldiv");
        $('#playtitle' + id).attr("class", "videotitle2");
        var videoplay = document.getElementById("videoplay");
        videoplay.play();
    }
}
function collectPrescription() {
    var userName = $.cookie('userName');
    if(userName == "" || userName == undefined){
        showpopup(100);
        $("#msgDiv").html(inertPopupEmum());
    }
}
function sharePrescription() {
    var userName = $.cookie('userName');
    if(userName == "" || userName == undefined){
        showpopup(100);
        $("#msgDiv").html(inertPopupEmum());
    }
}
function gologinpage() {
    location.href = "../login.html";
}
function goregisterpage() {
    location.href = "../register.html";
}
function inertPopupEmum() {
    return $("<div class='closediv'><img onclick='closepopup()' src='../../image/common/close.png' /></div>"
        + "<div><div class='noteimagediv'><img src='../../image/common/note_yellow.png' /></div><div class='noteworlddiv'>使用此功能前请登录</div></div>"
        + "<div><div class='logindiv'><div onclick='gologinpage()'>马上去登录</div></div><div class='registerdiv'><div onclick='goregisterpage()'>新用户注册</div></div></div>");
}
/*相关用料页面*/
function getPrescriptionImageDetail(id) {
    if(id == null || id == "" || id == undefined){
        id = 0;
    }
    $.ajax({
        url: ajaxBaseUrl + "/prescription/" + id + "/material",
        success: processImageDetailData
    });
}
function processImageDetailData(data) {
    $("#imagediv").html(data);
}
function showSelectDiv() {
    if(!document.getElementById("selectdiv")){
        createSelectDiv();
    }else{
        if($('#backgrounddiv').is(":hidden")){
            $('#backgrounddiv').show();
            $('#selectdiv').show();
        }else{
            $('#backgrounddiv').hide();
            $('#selectdiv').hide();
        }
    }
}
function createSelectDiv() {
    var sWidth = $(document).width();
    var sHeight = $(document).height();
    if($(document).scrollHeight > sHeight ){
        sHeight = $(document).scrollHeight;
    }
    // 背景界面
    var backgroundDivElem = $("<div id='backgrounddiv' class='backgrounddivclass'></div>");
    var bgWH = "width:" + sWidth + "px;" + "height:" + sHeight + "px;";
    backgroundDivElem.attr("style", bgWH);
    $('body').append(backgroundDivElem);
    // 内容界面
    var selectDivElem = $("<div id='selectdiv' class='selectdivclass'></div>");
    var selectTableElem = $("<table><tbody></tbody></table>");
    selectTableElem
        .append($("<tr id='selectdivtr1' onclick='doSearchByOrderId(1, \"验证人数\")'><td>验证人数排序</td><td><img src='../../image/common/selected.png'/></td></tr>"))
        .append($("<tr id='selectdivtr2' onclick='doSearchByOrderId(2, \"有效程度\")'><td>有效程度排序</td><td><img src='../../image/common/selected.png'/></td></tr>"))
        .append($("<tr id='selectdivtr3' onclick='doSearchByOrderId(3, \"见效速度\")'><td>见效速度排序</td><td><img src='../../image/common/selected.png'/></td></tr>"))
        .append($("<tr id='selectdivtr4' onclick='doSearchByOrderId(4, \"安全性由\")'><td>安全性由排序</td><td><img src='../../image/common/selected.png'/></td></tr>"))
        .append($("<tr id='selectdivtr5' onclick='doSearchByOrderId(5, \"方便性由\")'><td>方便性由排序</td><td><img src='../../image/common/selected.png'/></td></tr>"))
        .append($("<tr id='selectdivtr6' onclick='doSearchByOrderId(6, \"省钱程度\")'><td>省钱程度排序</td><td><img src='../../image/common/selected.png'/></td></tr>"))
    selectDivElem.append(selectTableElem);
    $('body').append(selectDivElem);
}
function doSearchByOrderId(orderId, orderName) {
    var prescription = $.cookie('PrescriptionName');
    getPrescriptionPagination(prescription, orderId, 1, 5);
    $("#searchselecttext").html(orderName);
    $('#backgrounddiv').hide();
    $('#selectdiv').hide();
    $('#selectdiv table tbody tr td:nth-child(1)').attr("style", "color:#000000");
    $('#selectdiv table tbody tr td:nth-child(2) img').attr("style", "display: none");
    $('#selectdivtr' + orderId +' td:nth-child(1)').attr("style", "color:#F39F51");
    $('#selectdivtr' + orderId +' td:nth-child(2) img').attr("style", "display: block");
}
function getPrescriptionRelation(prescription) {
    $.ajax({
        url: ajaxBaseUrl + "/prescription/relation?name=" + prescription,
        success: processRelationData
    });
}
function processRelationData(data) {
    var jibing = data.recommends;
    var zhenzhuang = data.products;
    var liaofa = data.naturalRemedies;
    var relationTableElem1 = $("<table><tbody></tbody></table>");
    var length = 5;
    if(jibing.length < 5){
        length = jibing.length;
    }
    for(var i=0;i<length;i++){
        var relationTrElem1 = $("<tr></tr>");
        relationTrElem1.append("<td><div class='contentpointdiv'></div></td>")
            .append("<td><div class='contentlistdiv'>" + jibing[i].title + "</div></td>");
        relationTableElem1.append(relationTrElem1);
    }
    $("#prescriptionrelationlist1").html(relationTableElem1);
    if(zhenzhuang.length < 5){
        length = zhenzhuang.length;
    }
    var relationTableElem2 = $("<table><tbody></tbody></table>");
    for(var i=0;i<length;i++){
        var relationTrElem2 = $("<tr></tr>");
        relationTrElem2.append("<td><div class='contentpointdiv'></div></td>")
            .append("<td><div class='contentlistdiv'>" + zhenzhuang[i].title + "</div></td>");
        relationTableElem2.append(relationTrElem2);
    }
    $("#prescriptionrelationlist2").html(relationTableElem2);
    if(liaofa.length < 5){
        length = liaofa.length;
    }
    var relationTableElem3 = $("<table><tbody></tbody></table>");
    for(var i=0;i<length;i++){
        var relationTrElem3 = $("<tr></tr>");
        relationTrElem3.append("<td><div class='contentpointdiv'></div></td>")
            .append("<td><div class='contentlistdiv'>" + liaofa[i].title + "</div></td>");
        relationTableElem3.append(relationTrElem3);
    }
    $("#prescriptionrelationlist3").html(relationTableElem3);
}