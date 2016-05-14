var isShowLoginRegister = false;
function initPage() {
    initPanelNavigation();
    initContentNavigation();
    initHeader();
    initDisease();
    initViewHistory();
}
/*导航*/
function initPanelNavigation() {
    var navigationElems = $("<div></div>");
    navigationElems.append($("<div class='navigationdiv' onclick='goNavigationPage(\"home.html\")'>" +
        "<div class='clickshowdiv_home'></div>" +
        "<div class='navigationimgdiv_home'><img src='../image/navigation/home_yellow.png'></div>" +
        "<div class='navigationtitlediv_home'>首页</div></div>"));
    navigationElems.append($("<div class='navigationdiv' onclick='goNavigationPage(\"healthvoice/list.html\")'>" +
        "<div class='clickshowdiv'></div>" +
        "<div class='navigationimgdiv'><img src='../image/navigation/healthvoice.png'></div>" +
        "<div class='navigationtitlediv'>健康之声</div></div>"));
    navigationElems.append($("<div class='navigationdiv' onclick='goNavigationPage(\"mystory/list.html\")'>" +
        "<div class='clickshowdiv'></div>" +
        "<div class='navigationimgdiv'><img src='../image/navigation/mystory.png'></div>" +
        "<div class='navigationtitlediv'>我的故事</div></div>"));
    navigationElems.append($("<div class='navigationdiv' onclick='goNavigationPage(\"naturetherapy/list.html\")'>" +
        "<div class='clickshowdiv'></div>" +
        "<div class='navigationimgdiv'><img src='../image/navigation/naturetherapy.png'></div>" +
        "<div class='navigationtitlediv'>自然疗法</div></div>"));
    navigationElems.append($("<div class='navigationdiv' onclick='goNavigationPage(\"ritucharya/list.html\")'>" +
        "<div class='clickshowdiv'></div>" +
        "<div class='navigationimgdiv'><img src='../image/navigation/ritucharya.png'></div>" +
        "<div class='navigationtitlediv'>季节养身</div></div>"));
    navigationElems.append($("<div class='navigationdiv' onclick='goNavigationPage(\"healthinfo/list.html\")'>" +
        "<div class='clickshowdiv'></div>" +
        "<div class='navigationimgdiv'><img src='../image/navigation/healthinfo.png'></div>" +
        "<div class='navigationtitlediv'>健康资讯</div></div>"));
    $("#navigation").html(navigationElems);
}
function initContentNavigation() {
    $("#contentnavigation").append("<div class='onetotaldivclass'><div class='contentnavigation1spacediv' onclick='goNavigationPage(\"healthvoice/list.html\")'>" +
        "<div class='cncircle1'><div class='cncircleimg'><img src='../image/home/healthvoice.png'/></div>" +
        "<div class='cncircletext'>健康之声</div></div>" + 
        "</div></div>").
    append("<div class='twototaldivclass'>" +
        "<div class='divtwoclass'><div class='contentnavigation2spaceleftdiv contentnavigation2spacediv_mystory' onclick='goNavigationPage(\"mystory/list.html\")'>" +
        "<div class='cncircle2'><div class='cncircleimg'><img src='../image/home/mystory.png'/></div>" +
        "<div class='cncircletext'>我的故事</div></div>" +
        "</div></div>" +
        "<div class='divtwoclass'><div class='contentnavigation2spacerightdiv contentnavigation2spacediv_naturaltherapy' onclick='goNavigationPage(\"naturetherapy/list.html\")'>" +
        "<div class='cncircle2'><div class='cncircleimg'><img src='../image/home/naturaltherapy.png'/></div>" +
        "<div class='cncircletext'>自然疗法</div></div>" +
        "</div></div>" +
        "</div>").
    append("<div class='twototaldivclass'>" +
        "<div class='divtwoclass'><div class='contentnavigation2spaceleftdiv contentnavigation2spacediv_ritucharya' onclick='goNavigationPage(\"ritucharya/list.html\")'>" +
        "<div class='cncircle2'><div class='cncircleimg'><img src='../image/home/ritucharya.png'/></div>" +
        "<div class='cncircletext'>季节养身</div></div>" +
        "</div></div>" +
        "<div class='divtwoclass'><div class='contentnavigation2spacerightdiv contentnavigation2spacediv_healthinfo' onclick='goNavigationPage(\"healthinfo/list.html\")'>" +
        "<div class='cncircle2'><div class='cncircleimg'><img src='../image/home/healthinfo.png'/></div>" +
        "<div class='cncircletext'>健康资讯</div></div>" +
        "</div></div>" +
    "</div>");
}
/*头部*/
function initHeader() {
    var headertitleElems = $("<div></div>");
    headertitleElems.append($("<div class='headernldiv'><a href='#navigation'><img src='../image/home/navigation.png' /></a></div>"));
    headertitleElems.append($("<div class='headerjiujiudiv'><img src='../image/home/jiujiutaofang.png' /></div>"));
    var userName = $.cookie('userName');
    if(typeof(userName) == "undefined" || !userName || userName == ""){
        headertitleElems.append($("<div class='headernldiv' onclick='showLoginRegister()'><img src='../image/home/login.png' /></div>"));
    }else{
        headertitleElems.append($("<div class='headernldiv' onclick='goToUserCenter()'><img src='../image/home/login_ok.png' /></div>"));
    }
    $("#headertitlediv").html(headertitleElems);
}
/*疾病*/
function initDisease() {

}
/*浏览历史*/
function initViewHistory() {

}






function goNavigationPage(url) {
    location.href = url;
}
function showLoginRegister() {
    if(!document.getElementById("loginregisternavigation")){
        var loginRegisterNavigationElem = $("<div id='loginregisternavigation' class='loginregisternavigation'></div>");
        loginRegisterNavigationElem.append($("<div class='lrnavigationldiv' onclick='showLoginRegisterPopup(\"login\")'>登录</div>"));
        loginRegisterNavigationElem.append($("<div class='lrnavigationrdiv' onclick='showLoginRegisterPopup(\"register\")'>注册</div>"));
        $("body").append(loginRegisterNavigationElem);
        isShowLoginRegister = true;
    }else{
        if(isShowLoginRegister){
            $("#loginregisternavigation").hide();
            isShowLoginRegister = false;
        }else{
            $("#loginregisternavigation").show();
            isShowLoginRegister = true;
        }
    }
}
function goToUserCenter() {
    location.href = "user.html";
}
/*登录和注册弹窗*/
function showLoginRegisterPopup(pageName) {
    if(!document.getElementById("loginregisterdiv")){
        createLoginRegisterDiv();
    }else{
        $('#backgrounddiv').show();
        $('#loginregisterdiv').show();
    }
    if(pageName == "login"){
        insertLoginElems();
        refreshVCode();
    }else if(pageName == "register"){
        insertRegisterElems();
    }
}
function createLoginRegisterDiv() {
    var sWidth = $(document).width();
    var sHeight = $(document).height();
    if($(document).scrollHeight > sHeight ){
        sHeight = $(document).scrollHeight;
    }
    // 背景界面
    var backgroundDivElem = $("<div id='backgrounddiv' class='backgrounddivclass'></div>");
    var bgWH = "width:" + sWidth + "px;" + "height:" + sHeight + "px;"
    backgroundDivElem.attr("style", bgWH);
    $('body').append(backgroundDivElem);
    // 内容界面
    var loginRegisterDivElem = $("<div id='loginregisterdiv' class='loginregisterdivclass'></div>");
    $('body').append(loginRegisterDivElem);
}
function insertLoginElems() {
    var loginElems = $("<div id='logindiv'></div>");
    loginElems.append($("<div class='lrpopupheaderdiv'><div class='lrpopupheadertitlediv'>登录</div>" +
        "<div class='lrpopupheaderimgdiv' onclick='closeLoginRegisterPopup()'><img src='../image/common/close_white.png' /></div></div>"));
    loginElems.append($("<div class='lrpopupinputdiv'>" +
        "<div class='inputdiv'><input class='npinput' type='text' name='userName' placeholder='请输入用户名' /></div>" +
        "<div class='inputdiv'><input class='npinput' type='password' name='password' placeholder='请输入密码' /></div>" +
        "<div class='inputdiv1'><div class='inputvcodediv'><input class='npinput' type='text' name='verificationCode' placeholder='请输入验证字符' /></div>" +
        "<div class='inputvcodeimgdiv'><img class='vcodeimg' src='../image/vcode/vcode.png'></div>" +
        "<div class='inputvcodebuttondiv'><img onclick='refreshVCode()' src='../image/common/refresh.png' /></div>" +
        "</div></div>"));
    loginElems.append($("<div class='lrpopupbuttondiv'><div class='lrpopupbutton' onclick='login()'>立即登录</div></div>"));
    loginElems.append($("<div id='loginregisterErrorMsg'></div>"));
    loginElems.append($("<div class='lrpopupwxqqlogindiv'>" +
        "<div class='wxqqlogindiv'><div class='wxqqlogin' onclick='loginByWX()'>" +
        "<div class='wxqqloginimgdiv'><img src='../image/home/weixin.png' /></div><div class='wxqqlogintextdiv'>微信登录</div></div></div>" +
        "<div class='wxqqlogindiv'><div class='wxqqlogin' onclick='loginByQQ()'>" +
        "<div class='wxqqloginimgdiv'><img src='../image/home/qq.png' /></div><div class='wxqqlogintextdiv'>QQ登录</div></div></div>" +
        "</div>"));
    loginElems.append($("<div class='lrpopupfooterdiv'>" +
        "<div class='lrfooterregisterdiv' onclick='insertRegisterElems()'>新用户注册</div>" +
        "<div class='lrfooterforgetpassworddiv' onclick='insertForgetPasswordElems()'>忘记密码?</div>" +
        "</div>"));

    $('#loginregisterdiv').html(loginElems);
}
function insertRegisterElems() {
    var registerElems = $("<div id='logindiv'></div>");
    registerElems.append($("<div class='lrpopupheaderdiv'><div class='lrpopupheadertitlediv'>注册</div>" +
        "<div class='lrpopupheaderimgdiv' onclick='closeLoginRegisterPopup()'><img src='../image/common/close_white.png' /></div></div>"));
    registerElems.append($("<div class='lrpopupinputdiv'>" +
        "<div class='inputdiv'><input class='npinput' type='text' name='phoneNumber' placeholder='输入您的手机号码' /></div>" +
        "<div class='inputdiv'><input class='npinput' type='text' name='userName' placeholder='设置您的名称' /></div>" +
        "<div class='inputdiv'><input class='npinput' type='password' name='password' placeholder='设置您的密码,数字加字母安全性更高' /></div>" +
        "<div class='inputdiv'><input class='npinput' type='password' name='password' placeholder='再次输入您的密码' /></div>" +
        "<div class='inputdiv1'><div class='inputsmscodediv'><input class='npinput' type='text' name='smsCode' placeholder='输入您收到的激活码' /></div>" +
        "<div class='inputsmscodebuttondiv'><div class='inputsmscodebutton' onclick='registerSMSCode()'>免费获取短信激活码</div></div>" +
        "</div></div>"));
    registerElems.append($("<div class='lrpopupbuttondiv'><div class='lrpopupbutton' onclick='register()'>立即注册</div></div>"));
    registerElems.append($("<div id='loginregisterErrorMsg'></div>"));
    registerElems.append($("<div class='lrpopupwxqqlogindiv'>" +
        "<div class='wxqqlogindiv'><div class='wxqqlogin' onclick='loginByWX()'>" +
        "<div class='wxqqloginimgdiv'><img src='../image/home/weixin.png' /></div><div class='wxqqlogintextdiv'>微信登录</div></div></div>" +
        "<div class='wxqqlogindiv'><div class='wxqqlogin' onclick='loginByQQ()'>" +
        "<div class='wxqqloginimgdiv'><img src='../image/home/qq.png' /></div><div class='wxqqlogintextdiv'>QQ登录</div></div></div>" +
        "</div>"));

    $('#loginregisterdiv').html(registerElems);
}
function insertForgetPasswordElems() {

}
function closeLoginRegisterPopup() {
    $('#backgrounddiv').hide();
    $('#loginregisterdiv').hide();
    $("#loginregisternavigation").hide();
    isShowLoginRegister = false;
}
/*Ajax请求区域*/
// 验证码
function refreshVCode() {
    $.ajax({
        url: ajaxBaseUrl + "/user/vcode",
        success: doVcodeRefresh
    });
}
// 登录
function login(){
    $.ajax({
        url: ajaxBaseUrl + "/user/vcode",
        success: doVcodeRefresh
    });
}
// 微信登录
function loginByWX(){

}
// QQ登录
function loginByQQ() {

}
// 注册
function register() {

}
// 短信验证码
function registerSMSCode() {

}
/*数据处理区域*/
function doVcodeRefresh(data) {
    $("img.vcodeimg").attr("src", "../image/vcode/" + data + ".png");
}

function getUserViewHistory(userName) {
    $.ajax({
        url: "http://localhost:8080/taofang/webapi/user/view?userName=" + userName,
        success: doGetUserViewHistory
    });
}
function doGetUserViewHistory(data) {
    var viewHistorys = data.viewHistorys;
    if(viewHistorys.length == 0){
        $("#viewhistory").hide();
        $("#emptyviewhistory").show();
    }else{
        $("#viewhistory").show();
        $("#emptyviewhistory").hide();
        var viewHistoryElems = $("<ul class='timeline'></ul>");
        for(var i=0; i<viewHistorys.length; i++){
            var date = viewHistorys[i].viewDate;
            var week = viewHistorys[i].viewWeek;
            var type = "上次浏览的" + viewHistorys[i].typeName;
            var titile = viewHistorys[i].title;
            var viewHistoryElem = $("<li></li>");
            viewHistoryElem.append($("<div class='time'><pre>" + week + "</pre><pre>" + date + "</pre></div>"));
            viewHistoryElem.append($("<div class='point'></div>"));

            var viewHistoryContentElem = $("<div class='content'></div>");
            var viewHistoryContentTableElem = ($("<table><tbody></tbody></table>"));
            viewHistoryContentTableElem.append($("<tr><td class='viewType'>" + type + "</td><td rowspan='2'><img src='../image/icon/time_view_icon.png'></td></tr>"));
            viewHistoryContentTableElem.append($("<tr><td class='viewTitle'>" + titile + "</td></tr>"));
            viewHistoryContentElem.append(viewHistoryContentTableElem);
            viewHistoryElem.append(viewHistoryContentElem);

            viewHistoryElems.append(viewHistoryElem);
        }
        $("#viewhistory").html(viewHistoryElems);
    }
}

function prescriptionSearch() {
    var prescriptionName = $("#prescriptionnamediv").find($("[name='prescriptionName']")).val();
    if(prescriptionName != "" && prescriptionName != undefined){
        location.href = 'prescription/list.html?prescription=' + prescriptionName;
    }
}