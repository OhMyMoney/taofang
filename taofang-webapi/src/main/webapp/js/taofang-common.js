function initWeixinShare(data){
    if(data != ""){
        wx.config({
            debug: false,
            appId: 'wx0f263786638d7cac', // 必填，公众号的唯一标识
            timestamp: 1465116554000, // 必填，生成签名的时间戳
            nonceStr: '99taofang', // 必填，生成签名的随机串
            signature: data,// 必填，签名，见附录1
            jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        wx.ready(function(){
            // 分享到朋友圈
            wx.onMenuShareTimeline({
                title: $.cookie('currLiangfang'),
                link: 'http://m.99taofang.com/views/taofang.html?prescriptionId=' + $.cookie('currLiangfangId'),
                imgUrl: 'http://m.99taofang.com/image/share.jpg',
                success: function () {
                    console.log("share");
                },
                cancel: function () {
                    console.log("cancle");
                }
            });
            // 分享给朋友
            wx.onMenuShareAppMessage({
                title: $.cookie('currLiangfang'), // 分享标题
                desc: $.cookie('currLiangfang'), // 分享描述
                link: 'http://m.99taofang.com/views/taofang.html?prescriptionId=' + $.cookie('currLiangfangId'), // 分享链接
                imgUrl: 'http://m.99taofang.com/image/share.jpg', // 分享图标
                type: '', // 分享类型,music、video或link，不填默认为link
                dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                success: function () {
                    console.log("share");
                },
                cancel: function () {
                    console.log("cancle");
                }
            });
            // 分享到QQ
            wx.onMenuShareQQ({
                title: $.cookie('currLiangfang'), // 分享标题
                desc: $.cookie('currLiangfang'), // 分享描述
                link: 'http://m.99taofang.com/views/taofang.html?prescriptionId=' + $.cookie('currLiangfangId'), // 分享链接
                imgUrl: 'http://m.99taofang.com/image/share.jpg', // 分享图标
                success: function () {
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                }
            });
        });
        wx.error(function(res){
            console.log(res);
        });
    }
}

function createContentPageElems(article, articleId) {
    var contentPageElems = $("<div id='contentbodypage' class='contentbodypage'></div>");
    contentPageElems
        .append($("<div class='previouspagediv'>" +
            "<div id='previouspage' class='previouspage_opacity' onclick='getPreviousPagination(\"" + article + "\", \"" + articleId + "\")'>" +
            "上一页</div></div>"))
        .append($("<div class='currentpagediv'><div class='currentpage'>0/0</div></div>"))
        .append($("<div class='nextpagediv'>" +
            "<div id='nextpage' class='nextpage' onclick='getNextPagination(\"" + article + "\", \"" + articleId + "\")'>" +
            "下一页</div></div>"));
    return contentPageElems;
}

function getPreviousPagination(category, articleId){
    var current = $("#contentbodypage").find($("[class='currentpage']")).text().split("/")[0];
    if(parseInt(current) > 1){
        if(category == "WDGS" || category == "ZRLF" || category == "JKZX"){
            getArticlePagination(category, parseInt(current) - 1, 10);
        }else if(category == "JJYS"){
            getRitucharyaPagination(parseInt(articleId), parseInt(current) - 1, 10);
        }else if(category == "JKZS"){
            getJKZSPagination(parseInt(current) - 1, 10, $("#date").val());
        }else if(category == "liangfang"){
            var orderId = $.cookie('orderId');
            if(typeof(orderId) == "undefined" || !orderId || orderId == ""){
                orderId = 0;
            }
            getLiangfangList(articleId, orderId, parseInt(current) - 1, 5);
        }
    }
}
function getNextPagination(category, articleId){
    var currentTotal = $("#contentbodypage").find($("[class='currentpage']")).text().split("/");
    var current = currentTotal[0];
    var total = currentTotal[1];
    if(parseInt(current) < parseInt(total)){
        if(category == "WDGS" || category == "ZRLF" || category == "JKZX"){
            getArticlePagination(category, parseInt(current) + 1, 10);
        }else if(category == "JJYS"){
            getRitucharyaPagination(parseInt(articleId), parseInt(current) + 1, 10);
        }else if(category == "JKZS"){
            getJKZSPagination(parseInt(current) + 1, 10, $("#date").val());
        }else if(category == "liangfang"){
            var orderId = $.cookie('orderId');
            if(typeof(orderId) == "undefined" || !orderId || orderId == ""){
                orderId = 0;
            }
            getLiangfangList(articleId, orderId, parseInt(current) + 1, 5);
        }
    }
}
function processPage(curPage, totalPage) {
    $("#contentbodypage").find($("[class='currentpage']")).html(curPage + "/" + totalPage);
    if(curPage == 1 || curPage == 0){
        $('#previouspage').addClass("previouspage_opacity").removeClass("previouspage");
    }else{
        $('#previouspage').addClass("previouspage").removeClass("previouspage_opacity");
    }
    if(curPage == totalPage){
        $('#nextpage').addClass("nextpage_opacity").removeClass("nextpage");
    }else{
        $('#nextpage').addClass("nextpage").removeClass("nextpage_opacity");
    }
}
function playVideo(videoUrl, title, id) {
    $.cookie('videoUrl', videoUrl, {expires: 7, path: '/'});
    $.cookie('videoTitle', title, {expires: 7, path: '/'});
    $.cookie('JJYSVideoId', 0, {expires: 7, path: '/'});
    var videoPlayButtonClass = $('#videoPlayButton' + id).attr("class");
    if(videoPlayButtonClass == "jjysjkzsparalleldiv"){
        $('#videoPlayButton' + id).attr("class", "jjysjkzstrianglediv");
        $('#videoPlayTitle' + id).attr("class", "jjysjkzsvideotitle1");
        $("#footervideobutton").attr("class", "footervideostop");
        document.getElementById("audiomp3").pause();
    }else{
        $("div.footervideotitle").html(title);
        $("#audiomp3").attr("src", videoUrl);
        $('div.jjysjkzscirclediv div').attr("class", "jjysjkzstrianglediv");
        $('#jjysjkzslisttable tbody tr td:nth-child(2) div').attr("class", "jjysjkzsvideotitle1");
        $('#videoPlayButton' + id).attr("class", "jjysjkzsparalleldiv");
        $('#videoPlayTitle' + id).attr("class", "jjysjkzsvideotitle2");
        if($("#footer").is(":hidden")){
            $("#footer").attr("data-position", "fixed");
            $("#footer").addClass("ui-footer-fixed").addClass("slideup");
            $("div.declarediv").attr("style", "height:140px");
            $("#footer").show();
            isFirstLoad = false;
        }
        $("#footervideobutton").attr("class", "footervideoplay");
        document.getElementById("audiomp3").play();
    }
}
function playWDGSVideo(videoUrl, title) {
    $.cookie('videoUrl', videoUrl, {expires: 7, path: '/'});
    $.cookie('videoTitle', title, {expires: 7, path: '/'});
    $.cookie('JJYSVideoId', 0, {expires: 7, path: '/'});
    var videoPlayButtonClass = $('#wdgsarticledetailcontentbutton div').attr("class");
    if(videoPlayButtonClass == "jjysjkzsparalleldiv"){
        $('#wdgsarticledetailcontentbutton div').attr("class", "jjysjkzstrianglediv");
        $("#footervideobutton").attr("class", "footervideostop");
        document.getElementById("audiomp3").pause();
    }else{
        $("div.footervideotitle").html(title);
        $("#audiomp3").attr("src", videoUrl);
        $('#wdgsarticledetailcontentbutton div').attr("class", "jjysjkzsparalleldiv");
        if($("#footer").is(":hidden")){
            $("#footer").attr("data-position", "fixed");
            $("#footer").addClass("ui-footer-fixed").addClass("slideup");
            $("div.declarediv").attr("style", "height:140px");
            $("#footer").show();
            isFirstLoad = false;
        }
        $("#footervideobutton").attr("class", "footervideoplay");
        document.getElementById("audiomp3").play();
    }
}
function playFooterVideo(){
    var footervideobuttonClass = $("#footervideobutton").attr("class");
    var videoplay = document.getElementById("audiomp3");
    if(footervideobuttonClass == "footervideostop"){
        $("#footervideobutton").attr("class", "footervideoplay");
        videoplay.play();
        checkVideoStop(1);
    }else{
        $("#footervideobutton").attr("class", "footervideostop");
        videoplay.pause();
    }
}
function playRitucharyaVideo(videoUrl, title, ritucharya, id) {
    $.cookie('videoUrl', videoUrl, {expires: 7, path: '/'});
    $.cookie('videoTitle', title, {expires: 7, path: '/'});
    $.cookie('videoFrom', "JJYS;" + ritucharya + ";" + id, {expires: 7, path: '/'});
    $.cookie('JJYSVideoId', id, {expires: 7, path: '/'});
    $('div.jjysjkzscirclediv div').attr("class", "jjysjkzstrianglediv");
    $('#jjysjkzslisttable tbody tr td:nth-child(2) div').attr("class", "jjysjkzsvideotitle1");
    $('#videoPlayButton' + id).attr("class", "jjysjkzsparalleldiv");
    $('#videoPlayTitle' + id).attr("class", "jjysjkzsvideotitle2");
    if($("#footer").is(":hidden")){
        $("#footer").attr("data-position", "fixed");
        $("#footer").addClass("ui-footer-fixed").addClass("slideup");
        $("div.declarediv").attr("style", "height:140px");
        $("#footer").show();
        isFirstLoad = false;
    }
    doPlayFooterVideo(videoUrl, title, id);
}
function doPlayFooterVideo(videoUrl, videoTitle, id) {
    var videoplay = document.getElementById("audiomp3");
    if($("#footervideobutton").attr("class") == "footervideoplay"){
        $("#footervideobutton").attr("class", "footervideostop");
        videoplay.pause();
    }
    $("div.footervideotitle").html(videoTitle);
    $("#audiomp3").attr("src", videoUrl);
    $("#footervideobutton").attr("class", "footervideoplay");
    videoplay.play();
    checkFooterVideoStop(id);
}
function checkFooterVideoStop(id) {
    if(id != $.cookie('JJYSVideoId')){
        return;
    }
    setTimeout(function() {
        if(document.getElementById("audiomp3").ended){
            var videoFrom = $.cookie('videoFrom');
            var cookieBad = typeof(videoFrom) == "undefined" || !videoFrom || videoFrom == "";
            if(!cookieBad){
                var videoFromArr = videoFrom.split(";");
                if(videoFromArr[0] == "JJYS"){
                    getNextJJYSVideo(videoFromArr[1], videoFromArr[2]);
                }
            }
        }else{
            checkFooterVideoStop(id);
        }
    }, 1000);
}


function playLoopJJYSVideo(ritucharyaList, index, flag) {
    if(flag == 0 || index >= ritucharyaList.length){
        return;
    }
    playVideo(ritucharyaList[index].videoUrl, ritucharyaList[index].ritucharyaTitle, ritucharyaList[index].ritucharyaId);
    checkLoopVideoStop(ritucharyaList, index, flag);
}
function checkLoopVideoStop(ritucharyaList, index, flag) {
    setTimeout(function() {
        if(document.getElementById("audiomp3").ended){
            playLoopJJYSVideo(ritucharyaList, index+1, flag)
        }else{
            checkLoopVideoStop(ritucharyaList, index, flag);
        }
    }, 1000);
}

function checkVideoStop(flag) {
    if(flag == 0){
        $("#footervideobutton").attr("class", "footervideostop");
        return true;
    }
    setTimeout(function() {
        if(document.getElementById("audiomp3").ended){
            checkVideoStop(0);
        }else{
            checkVideoStop(1);
        }
    }, 1000);
}