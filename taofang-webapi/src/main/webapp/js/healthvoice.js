function getHealthVoicePagination(date, page, pageSize) {
    var ajaxUrl = "http://192.168.31.199:8080/taofang/webapi/healthvoices";
    if(date != ""){
        ajaxUrl += "/" + date
    }
    ajaxUrl += "?page=" + page + "&pageSize=" + pageSize;
    $.ajax({
        url: ajaxUrl,
        success: processHealthVoicePagination
    });
}
function searchByDateChange() {
    var date = $('#date').val();
    var dateStr = '搜索"' + parseDate() + '"';
    var videoplay = document.getElementById("videoplay");
    videoplay.pause();
    $('#dateselectdiv').html($("<div class='dateselect'>" + dateStr + "</div>"));
    getHealthVoicePagination(date, 1, 10);
}
function doPreviousPage() {
    var current = $("#page").find($("[class='currentpage']")).text().split("/")[0];
    if(parseInt(current) > 1){
        getHealthVoicePagination("", parseInt(current) - 1, 10);
    }
}
function doNextPage() {
    var currentTotal = $("#page").find($("[class='currentpage']")).text().split("/");
    var current = currentTotal[0];
    var total = currentTotal[1];
    if(parseInt(current) < parseInt(total)){
        getHealthVoicePagination("", parseInt(current) + 1, 10);
    }
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
        $('#videotitlediv').html(title);
        $('div.circlediv div').attr("class", "trianglediv");
        $('.videolistdiv table tbody tr td:nth-child(2) div').attr("class", "videotitle1");
        $('#playimg' + id).attr("class", "paralleldiv");
        $('#playtitle' + id).attr("class", "videotitle2");
        var videoplay = document.getElementById("videoplay");
        videoplay.play();
    }
}
function processHealthVoicePagination(data) {
    // 翻页区域
    processPage(data.curPage, data.totalPage);
    var totalCount = data.totalCount;
    if(totalCount == 0){ // 没有结果
        $('#page').hide();
        $('#videodiv').hide();
        var emptyDivElem = $("<div class='emptylist'></div>");
        emptyDivElem.append($("<div><img src='../../image/common/note.png'></div>"))
        emptyDivElem.append($("<div class='emptydate'>没有\"" + parseDate() + "\"的相关内容</div>"));
        emptyDivElem.append($("<div class='emptybutton' onclick='backtolist()'>返回列表页</div>"));
        $("#healthvoicelist").html(emptyDivElem);
    }else{
        $('#page').show();
        $('#videodiv').show();
        var healthVoices = data.healthVoices;
        // 播放区域
        $('#videotitlediv').html(healthVoices[0].title);
        $('#videoplay').attr("src", healthVoices[0].url);
        // 列表页
        var healthVoiceElems = $("<table><tbody></tbody></table>");
        for(var i=0; i< healthVoices.length; i++){
            var healthVoice = healthVoices[i];
            var onclickFunc = "playVideo('" + healthVoice.url + "', " + healthVoice.id + ", '" + healthVoice.title +  "')";
            var playimg = "playimg" + healthVoice.id;
            var playtitle = "playtitle" + healthVoice.id;
            var healthVoiceTrElem = $("<tr></tr>");
            healthVoiceTrElem.append($("<td><div class='circlediv' onclick=\"" + onclickFunc + "\"><div id='" + playimg + "' class='trianglediv'></div></div></td>"));
            healthVoiceTrElem.append($("<td><div id='" + playtitle + "' class='videotitle1'>" + healthVoice.title + "</div></td>"));
            healthVoiceTrElem.append($("<td><img src='../../image/common/link.png' /></td>"));
            healthVoiceElems.append(healthVoiceTrElem);
        }
        $("#healthvoicelist").html($("<div id='videolist' class='videolistdiv'></div>").append(healthVoiceElems));
    }
}
function parseDate() {
    var date = $('#date').val();
    var year = date.substr(0, 4);
    var month = date.substr(4, 2);
    var day = date.substr(6, 2);
    return year + "年" + month + "月" + day + "日"
}
function backtolist() {
    location.href = "list.html";
}
