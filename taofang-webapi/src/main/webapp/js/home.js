var isShowLoginRegister = false;
var countdown = 0;
function initPage() {
    initHeader();
    initPanelNavigation();
    initContentNavigation();
    // initDisease();
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
    $.ajax({
        url: ajaxBaseUrl + "/disease/statistics",
        success: createDiseaseView
    });
}
/*浏览历史*/
function initViewHistory() {
    var userName = $.cookie('userName');
    if(typeof(userName) == "undefined" || !userName || userName == ""){
        insertEmptyViewHistoryElems();
    }else{
        insertUserViewHistoryElems();
    }
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
    loginElems.append($("<div id='loginregisterErrorMsg' class='error'></div>"));
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
    var registerElems = $("<div id='registerdiv'></div>");
    registerElems.append($("<div class='lrpopupheaderdiv'><div class='lrpopupheadertitlediv'>注册</div>" +
        "<div class='lrpopupheaderimgdiv' onclick='closeLoginRegisterPopup()'><img src='../image/common/close_white.png' /></div></div>"));
    registerElems.append($("<div class='lrpopupinputdiv'>" +
        "<div class='inputdiv'><input class='npinput' type='text' name='phoneNumber' placeholder='输入您的手机号码' /></div>" +
        "<div class='inputdiv'><input class='npinput' type='text' name='userName' placeholder='设置您的名称' /></div>" +
        "<div class='inputdiv'><input class='npinput' type='password' name='password' placeholder='设置您的密码,数字加字母安全性更高' /></div>" +
        "<div class='inputdiv'><input class='npinput' type='password' name='confirmPassword' placeholder='再次输入您的密码' /></div>" +
        "<div class='inputdiv1'><div class='inputsmscodediv'><input class='npinput' type='text' name='smsCode' placeholder='输入您收到的激活码' /></div>" +
        "<div class='inputsmscodebuttondiv'><div id='inputsmscodebutton' class='inputsmscodebutton' onclick='registerSMSCode(-1)'>免费获取短信激活码</div></div>" +
        "</div></div>"));
    registerElems.append($("<div class='lrpopupbuttondiv'><div class='lrpopupbutton' onclick='register()'>立即注册</div></div>"));
    registerElems.append($("<div id='loginregisterErrorMsg' class='error'></div>"));
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
    $('#loginregisternavigation').hide();
    isShowLoginRegister = false;
}
function insertEmptyViewHistoryElems() {
    var viewHistoryElems = $("<div class='emptyviewhistorydiv'></div>");
    viewHistoryElems.append("<div class='viewhistorylinediv'>" +
        "<div class='viewhistorylinetitle'>偏方</div>" +
        "<div class='viewhistorylinepointdiv'><div class='viewhistorylinepoint'></div></div>" +
        "<div class='viewhistorylinecontentdiv'><div class='viewhistorylinecontent'>您尚未浏览相关内容</div></div>" +
    "</div>");
    viewHistoryElems.append("<div class='viewhistorylinediv'>" +
        "<div class='viewhistorylinetitle'>文章</div>" +
        "<div class='viewhistorylinepointdiv'><div class='viewhistorylinepoint'></div></div>" +
        "<div class='viewhistorylinecontentdiv'><div class='viewhistorylinecontent'>您尚未浏览相关内容</div></div>" +
        "</div>");
    $('#userviewhistory').html(viewHistoryElems);
}
function insertUserViewHistoryElems() {
    $.ajax({
        url: ajaxBaseUrl + "/user/viewhistory?userId=" + $.cookie('userId'),
        success: insertUserViewHistoryData
    });
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
    var errorMessage = checkLoginForm();
    $("#loginregisterErrorMsg").addClass("error").text(errorMessage);
    if(errorMessage == ""){
        var userNameVal = $("#logindiv").find($("[name='userName']")).val();
        var passwordVal = $("#logindiv").find($("[name='password']")).val();
        $.ajax({
            timeout: 5000,
            url: ajaxBaseUrl + "/user/login",
            contentType: "application/json",
            data: JSON.stringify({userName : userNameVal, password : passwordVal}),
            type: "post",
            success: doLoginSuccess
        });
    }
}
// 注册
function register() {
    var errorMessage = checkRegisterForm();
    $("#loginregisterErrorMsg").addClass("error").text(errorMessage);
    if(errorMessage == ""){
        var phoneNumberVal = $("#registerdiv").find($("[name='phoneNumber']")).val();
        var userNameVal = $("#registerdiv").find($("[name='userName']")).val();
        var passwordVal = $("#registerdiv").find($("[name='password']")).val();
        var confirmPasswordVal = $("#registerdiv").find($("[name='confirmPassword']")).val();
        var smsCodeVal = $("#registerdiv").find($("[name='smsCode']")).val();
        $.ajax({
            timeout: 5000,
            url: ajaxBaseUrl + "/user/register",
            contentType: "application/json",
            data: JSON.stringify({phoneNumber:phoneNumberVal, userName:userNameVal, password:passwordVal, confirmPassword:confirmPasswordVal, smsCode:smsCodeVal}),
            type: "post",
            success: doRegisterSuccess
        });
    }
}
// 微信登录
function loginByWX(){

}
// QQ登录
function loginByQQ() {

}
// 短信验证码
function registerSMSCode(id) {
    if(id == -1 && countdown > 0){
        return;
    }else if(id == -1){
        countdown = 60;
    }
    if (countdown == 0) {
        $("#inputsmscodebutton").attr("disabled", false)
        $("#inputsmscodebutton").html("免费获取短信激活码");
    } else {
        $("#inputsmscodebutton").attr("disabled", true);
        $("#inputsmscodebutton").html("重新发送(" + countdown + ")");
        countdown--;
    }
    setTimeout(function() {
        registerSMSCode(0);
    }, 1000)
}
/*数据处理区域*/
function doVcodeRefresh(data) {
    $("img.vcodeimg").attr("src", "../image/vcode/" + data + ".png");
}
function checkLoginForm(){
    var errorMessage = "";
    var verificationCode = $("#logindiv").find($("[name='verificationCode']"));
    var vcodeArr = $("img.vcodeimg").attr("src").split("/");
    var vcode = vcodeArr[vcodeArr.length-1].split(".")[0];
    if($.trim(verificationCode.val()) == ""){
        errorMessage = "验证码不能为空";
        verificationCode.focus();
    }else if($.trim(verificationCode.val().toLocaleLowerCase()) != vcode.toLocaleLowerCase()){
        errorMessage = "验证码输入错误,请重新输入";
        verificationCode.val("");
        verificationCode.focus();
    }
    var userName = $("#logindiv").find($("[name='userName']"));
    var password = $("#logindiv").find($("[name='password']"));
    if($.trim(userName.val()) == ""){
        errorMessage = "用户名不能为空";
        userName.focus();
    }else if($.trim(password.val()) == ""){
        errorMessage = "密码不能为空";
        password.focus();
    }
    return errorMessage;
}
function checkRegisterForm() {
    var errorMessage = "";
    var phoneNumber = $("#registerdiv").find($("[name='phoneNumber']"));
    var userName = $("#registerdiv").find($("[name='userName']"));
    var password = $("#registerdiv").find($("[name='password']"));
    var confirmPassword = $("#registerdiv").find($("[name='confirmPassword']"));
    // 验证码=暂时不验证
    var smsCode = $("#registerdiv").find($("[name='smsCode']"));
    if($.trim(phoneNumber.val()) == ""){
        errorMessage = "手机号码不能为空";
        phoneNumber.focus();
    }else if(isPhoneNumber(phoneNumber.val()) == false){
        errorMessage = '手机号码不正确';
        phoneNumber.focus();
    }else if($.trim(userName.val()) == ""){
        errorMessage = "用户名不能为空";
        userName.focus();
    }else if($.trim(password.val()) == ""){
        errorMessage = "密码不能为空";
        password.focus();
    }else if($.trim(confirmPassword.val()) == ""){
        errorMessage = "确认密码不能为空";
        confirmPassword.focus();
    }else if($.trim(password.val()) != $.trim(confirmPassword.val())){
        errorMessage = "两次输入的密码不相等";
        password.focus();
        confirmPassword.focus();
    }
    return errorMessage;
}
function doLoginSuccess(data){
    var dataArr = data.split(";");
    if(dataArr[0].toLocaleLowerCase() == "ok"){
        $.cookie('userName', $("#logindiv").find($("[name='userName']")).val(), {expires: 7, path: '/'});
        $.cookie('userId', dataArr[1], {expires: 7, path: '/'});
        location.href = "home.html";
    }else{
        $("#loginregisterErrorMsg").addClass("error").text(data);
    }
}
function doRegisterSuccess(data){
    var dataArr = data.split(";");
    if(dataArr[0].toLocaleLowerCase() == "ok"){
        $.cookie('userName', $("#registerdiv").find($("[name='userName']")).val(), {expires: 7, path: '/'});
        $.cookie('userId', dataArr[1], {expires: 7, path: '/'});
        location.href = "home.html";
    }else{
        $("#loginregisterErrorMsg").addClass("error").text(data);
    }
}
function isPhoneNumber(phone) {
    var pattern = /^1[34578]\d{9}$/;
    return pattern.test(phone);
}
function insertUserViewHistoryData(data) {
    var viewHistorys = data.viewHistorys;
    if(viewHistorys.length == 0){
        insertEmptyViewHistoryElems();
    }else{
        
    }
}
function createDiseaseView(data) {
    var diseaseList = data.diseaseList;
    var firstLineDivElems = $("<div class='diseasediv1'>" +
        "<div class='flsubdiv1'>" +
            "<div onclick='goPrescriptionPage(\"" + diseaseList[1].name + "\")'>" +
                "" +diseaseList[1].name + "" +
            "</div>" +
        "</div>" +
        "<div class='flsubdiv2'><div onclick='goPrescriptionPage(\"" + diseaseList[3].name + "\")'>" + diseaseList[3].name + "</div></div>" +
        "<div class='flsubdiv3'><div onclick='goPrescriptionPage(\"" + diseaseList[5].name + "\")'>" + diseaseList[5].name + "</div></div>" +
        "<div class='flsubdiv4'><div onclick='goPrescriptionPage(\"" + diseaseList[7].name + "\")'>" + diseaseList[7].name + "</div></div>" +
        "</div>");
    var secondLineDivElems = $("<div class='diseasediv2'>" +
        "<div class='slsubdiv1'><div onclick='goPrescriptionPage(\"" + diseaseList[10].name + "\")' class='slsubdiv11'>" + diseaseList[10].name + "</div><div onclick='goPrescriptionPage(\"" + diseaseList[11].name + "\")' class='slsubdiv12'>" + diseaseList[11].name + "</div></div>" +
        "<div class='slsubdiv2'><div onclick='goPrescriptionPage(\"" + diseaseList[0].name + "\")'>" + diseaseList[0].name + "</div></div>" +
        "<div class='slsubdiv3'><div onclick='goPrescriptionPage(\"" + diseaseList[9].name + "\")'>" + diseaseList[9].name + "</div></div>" +
        "</div>");
    var thirdLineDivElems = $("<div class='diseasediv3'>" +
        "<div class='tlsubdiv1'><div onclick='goPrescriptionPage(\"" + diseaseList[2].name + "\")'>" + diseaseList[2].name + "</div></div>" +
        "<div class='tlsubdiv2'><div onclick='goPrescriptionPage(\"" + diseaseList[4].name + "\")'>" + diseaseList[4].name + "</div></div>" +
        "<div class='tlsubdiv3'><div onclick='goPrescriptionPage(\"" + diseaseList[6].name + "\")'>" + diseaseList[6].name + "</div></div>" +
        "<div class='tlsubdiv4'><div onclick='goPrescriptionPage(\"" + diseaseList[8].name + "\")'>" + diseaseList[8].name + "</div></div>" +
        "</div>");
    $("#headerdiseasediv").append(firstLineDivElems).append(secondLineDivElems).append(thirdLineDivElems);
}
function goPrescriptionPage(prescriptionName) {
    location.href = 'prescription/list.html?prescription=' + prescriptionName;
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