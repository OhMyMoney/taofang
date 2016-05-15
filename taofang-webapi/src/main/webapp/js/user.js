function initPage() {
    $.cookie('lastUserClick', "", {expires: 1, path: '/'});
    $("#usercenter").hide();
    initUserInfo();
    initUserViewHistory();
}
function initUserInfo() {
    $.ajax({
        url: ajaxBaseUrl + "/user/info?userId=" + $.cookie('userId'),
        success: processUserInfoData
    });
    $("#useropperatediv")
        .append($("<div id='qiufang25div' class='useropperateunclick25div' onclick='showUserCenter(\"qiufang\")'>" +
                "<div class='useropperate25img'><img id='qiufangimage' src='../image/user/qiufang.png'/></div>" +
                "<div class='useropperate25title'><div id='qiufangtitle' class='userunclicktitle'>我的求方</div>" +
                     "<div class='userbuttondiv'><div id='qiufangbutton' class='userunclickbutton'></div></div></div>" +
            "</div>"))
        .append($("<div id='xianfang25div' class='useropperateunclick25div' onclick='showUserCenter(\"xianfang\")'>" +
            "<div class='useropperate25img'><img id='xianfangimage' src='../image/user/xianfang.png'/></div>" +
            "<div class='useropperate25title'><div id='xianfangtitle' class='userunclicktitle'>我的献方</div>" +
                 "<div class='userbuttondiv'><div id='xianfangbutton' class='userunclickbutton'></div></div></div>" +
            "</div>"))
        .append($("<div id='shoucang25div' class='useropperateunclick25div' onclick='showUserCenter(\"shoucang\")'>" +
            "<div class='useropperate25img'><img id='shoucangimage' src='../image/user/shoucang.png'/></div>" +
            "<div class='useropperate25title'><div id='shoucangtitle' class='userunclicktitle'>我的收藏</div>" +
                 "<div class='userbuttondiv'><div id='shoucangbutton' class='userunclickbutton'></div></div></div>" +
            "</div>"))
        .append($("<div id='pinglun25div' class='useropperateunclick25div' onclick='showUserCenter(\"pinglun\")'>" +
            "<div class='useropperate25img'><img id='pinglunimage' src='../image/user/pinglun.png'/></div>" +
            "<div class='useropperate25title'><div id='pingluntitle' class='userunclicktitle'>我的评论</div>" +
                 "<div class='userbuttondiv'><div id='pinglunbutton' class='userunclickbutton'></div></div></div>" +
            "</div>"));
}
function initUserViewHistory() {
    $.ajax({
        url: ajaxBaseUrl + "/user/viewhistory?userId=" + $.cookie('userId'),
        success: insertUserViewHistoryData
    });
}
function insertUserViewHistoryData(data) {
    var viewHistorys = data.viewHistorys;
    if(viewHistorys.length == 0){
        insertUserViewHistoryElems();
    }else{

    }
}
function insertUserViewHistoryElems() {
    $("#viewhistorylist").html("<div class='viewhistorylistemptyblockdiv'></div>" +
        "<div class='viewhistorylistemptydiv'>你尚未浏览相关内容</div>");
}
function processUserInfoData(data) {
    var name = data.userName;
    var icon = data.icon;
    $("#userinfo").append($("<div class='userinfoimagediv'><img src='" + icon + "' /></div>"))
        .append("<div class='userinfonamediv'>" + name + "</div>");
}
function showUserCenter(module) {
    insertModuleElems(module);
    var lastUserClick = $.cookie('lastUserClick');
    if(typeof(lastUserClick) == "undefined" || !lastUserClick || lastUserClick == ""){
        $("#" + module + "25div").removeClass("useropperateunclick25div").addClass("useropperateclick25div");
        $("#" + module + "image").attr("src", "../image/user/" + module + "_click.png");
        $("#" + module + "title").removeClass("userunclicktitle").addClass("userclicktitle");
        $("#" + module + "button").removeClass("userunclickbutton").addClass("userclickbutton");
        $("#usercenter").show();
    }else if(lastUserClick != module){
        $("#" + module + "25div").removeClass("useropperateunclick25div").addClass("useropperateclick25div");
        $("#" + module + "image").attr("src", "../image/user/" + module + "_click.png");
        $("#" + module + "title").removeClass("userunclicktitle").addClass("userclicktitle");
        $("#" + module + "button").removeClass("userunclickbutton").addClass("userclickbutton");
        $("#" + lastUserClick + "25div").removeClass("useropperateclick25div").addClass("useropperateunclick25div");
        $("#" + lastUserClick + "image").attr("src", "../image/user/" + lastUserClick + ".png");
        $("#" + lastUserClick + "title").removeClass("userclicktitle").addClass("userunclicktitle");
        $("#" + lastUserClick + "button").removeClass("userclickbutton").addClass("userunclickbutton");
        $("#usercenter").show();
    }else if(lastUserClick == module){
        if($("#usercenter").is(":hidden")){
            $("#usercenter").show();
            $("#" + module + "25div").removeClass("useropperateunclick25div").addClass("useropperateclick25div");
            $("#" + module + "image").attr("src", "../image/user/" + module + "_click.png");
            $("#" + module + "title").removeClass("userunclicktitle").addClass("userclicktitle");
            $("#" + module + "button").removeClass("userunclickbutton").addClass("userclickbutton");
        }else{
            $("#usercenter").hide();
            $("#" + lastUserClick + "25div").removeClass("useropperateclick25div").addClass("useropperateunclick25div");
            $("#" + lastUserClick + "image").attr("src", "../image/user/" + lastUserClick + ".png");
            $("#" + lastUserClick + "title").removeClass("userclicktitle").addClass("userunclicktitle");
            $("#" + lastUserClick + "button").removeClass("userclickbutton").addClass("userunclickbutton");
        }
    }
    $.cookie('lastUserClick', module, {expires: 1, path: '/'});
}
function insertModuleElems(module) {
    $.ajax({
        url: ajaxBaseUrl + "/user/module?module=" + module + "&userId=" + $.cookie('userId'),
        success: processModuleData
    });
}
function processModuleData(data) {
    var moduleInfoList = data.moduleInfoList;
    if(moduleInfoList.length == 0){
        insertEmptyModuleElems();
    }else{
        var userCenterTableElem = $("<table><tbody></tbody></table>");
        for(var i=0; i<moduleInfoList.length;i++){
            var moduleInfo = moduleInfoList[i];
            var userCenterTrElem = $("<tr></tr>");
            userCenterTrElem.append("<td><div class='usercenterpointdiv'></div></td>")
                .append("<td><div class='usercentertitlediv'>" + moduleInfo.title + "</div></td>")
                .append("<td><div class='usercentertimediv'>" + moduleInfo.dateTime.split(" ")[0] +"</div></td>");
            userCenterTableElem.append(userCenterTrElem);
        }
        $("#usercenter").html(userCenterTableElem);
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
