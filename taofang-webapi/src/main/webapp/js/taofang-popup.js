var isShowLoginRegister = false;
var isShowSCFX = false;
var countdown = 0;

function createDLZCPopup() {
    if(!document.getElementById("loginregisternavigation")){
        var loginRegisterNavigationElem = $("<div id='loginregisternavigation' class='loginregisternavigation'></div>");
        loginRegisterNavigationElem.append($("<div class='lrnavigationldiv' onclick='showLoginRegisterPopup(\"login\")'>登录</div>"));
        loginRegisterNavigationElem.append($("<div class='lrnavigationrdiv' onclick='showLoginRegisterPopup(\"register\")'>注册</div>"));
        $("body").append(loginRegisterNavigationElem);
        setTimeout(function() {
            isShowLoginRegister = true;
        }, 500);
    }else{
        if(isShowLoginRegister){
            $("#loginregisternavigation").hide();
            isShowLoginRegister = false;
        }else{
            $("#loginregisternavigation").show();
            setTimeout(function() {
                isShowLoginRegister = true;
            }, 500);
        }
    }
}
function showLoginRegisterPopup(role) {
    if(!document.getElementById("loginregisterdiv")){
        createLoginRegisterDiv();
    }else{
        var sWidth = $(document).width();
        var sHeight = $(document).height();
        if($(document).scrollHeight > sHeight ){
            sHeight = $(document).scrollHeight;
        }
        var bgWH = "width:" + sWidth + "px;" + "height:" + sHeight + "px;";
        $('#loginregisterbgdiv').attr("style", bgWH).show();
        $('#loginregisterdiv').show();
    }
    if(role == "login"){
        insertLoginElems();
        refreshVCode();
    }else if(role == "register"){
        insertRegisterElems();
    }
}
function showArticleThumbPopup(text) {
    if(!document.getElementById("articlethumbdiv")){
        createArticleThumbDiv();
    }else{
        var sWidth = $(document).width();
        var sHeight = $(document).height();
        if($(document).scrollHeight > sHeight ){
            sHeight = $(document).scrollHeight;
        }
        var bgWH = "width:" + sWidth + "px;" + "height:" + sHeight + "px;";
        $('#articlethumbbgdiv').attr("style", bgWH).show();
        var leftWidth = (sWidth - 180) / 2;
        var topHeight = $("div.articledetailthumbimg").offset().top;
        var thumbWH = "left:" + leftWidth + "px;" + "top:" + topHeight + "px;";
        $('#articlethumbdiv').attr("style", thumbWH).show();
    }
    $('#articlethumbdiv').html(text);
    setTimeout(function() {
        $('#articlethumbdiv').hide();
        $('#articlethumbbgdiv').hide();
    }, 2000)
}
function createLoginRegisterDiv() {
    var sWidth = $(document).width();
    var sHeight = $(document).height();
    if($(document).scrollHeight > sHeight ){
        sHeight = $(document).scrollHeight;
    }
    // 背景界面
    var backgroundDivElem = $("<div id='loginregisterbgdiv' class='loginregisterbgdiv'></div>");
    var bgWH = "width:" + sWidth + "px;" + "height:" + sHeight + "px;";
    backgroundDivElem.attr("style", bgWH);
    $('body').append(backgroundDivElem);
    // 内容界面
    var loginRegisterDivElem = $("<div id='loginregisterdiv' class='loginregisterdivclass'></div>");
    $('body').append(loginRegisterDivElem);
}
function createArticleThumbDiv() {
    var sWidth = $(document).width();
    var sHeight = $(document).height();
    if($(document).scrollHeight > sHeight ){
        sHeight = $(document).scrollHeight;
    }
    // 背景界面
    var backgroundDivElem = $("<div id='articlethumbbgdiv' class='popupbgdiv'></div>");
    var bgWH = "width:" + sWidth + "px;" + "height:" + sHeight + "px;";
    backgroundDivElem.attr("style", bgWH);
    $('body').append(backgroundDivElem);
    var leftWidth = (sWidth - 180) / 2;
    var topHeight = $("div.articledetailthumbimg").offset().top;
    var thumbWH = "left:" + leftWidth + "px;" + "top:" + topHeight + "px;";
    var thumbDivElem = $("<div id='articlethumbdiv' class='articlethumbdivclass'></div>");
    thumbDivElem.attr("style", thumbWH);
    $('body').append(thumbDivElem);
}
function insertLoginElems() {
    var loginElems = $("<div id='logindiv'></div>");
    loginElems.append($("<div class='lrpopupheaderdiv'><div class='lrpopupheadertitlediv'>登录</div>" +
        "<div class='lrpopupheaderimgdiv' onclick='closeLoginRegisterPopup()'><img src='../image/common/close_white.png' /></div></div>"));
    loginElems.append($("<div class='lrpopupinputdiv'>" +
        "<div class='inputdiv'><input class='npinput' type='text' name='userName' placeholder='请输入用户名' /></div>" +
        "<div class='inputdiv'><input class='npinput' type='password' name='password' placeholder='请输入密码' /></div>" +
        "<div class='inputdiv1'><div class='inputvcodediv'><input class='npinput' type='text' name='verificationCode' placeholder='请输入验证字符' /></div>" +
        "<div class='inputvcodeimgdiv'><img class='vcodeimg' src='/image/vcode/vcode.png'></div>" +
        "<div class='inputvcodebuttondiv'><img onclick='refreshVCode()' src='/image/common/refresh.png' /></div>" +
        "</div></div>"));
    loginElems.append($("<div class='lrpopupbuttondiv'><div class='lrpopupbutton' onclick='login()'>立即登录</div></div>"));
    loginElems.append($("<div id='loginregisterErrorMsg' class='error'></div>"));
    loginElems.append($("<div class='lrpopupwxqqlogindiv'>" +
        "<div class='wxqqlogindiv'><div class='wxqqlogin' onclick='loginByWX()'>" +
        "<div class='wxqqloginimgdiv'><img src='/image/home/weixin.png' /></div><div class='wxqqlogintextdiv'>微信登录</div></div></div>" +
        "<div class='wxqqlogindiv'><div class='wxqqlogin' onclick='loginByQQ()'>" +
        "<div class='wxqqloginimgdiv'><img src='/image/home/qq.png' /></div><div class='wxqqlogintextdiv'>QQ登录</div></div></div>" +
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
        "<div class='wxqqloginimgdiv'><img src='/image/home/weixin.png' /></div><div class='wxqqlogintextdiv'>微信登录</div></div></div>" +
        "<div class='wxqqlogindiv'><div class='wxqqlogin' onclick='loginByQQ()'>" +
        "<div class='wxqqloginimgdiv'><img src='/image/home/qq.png' /></div><div class='wxqqlogintextdiv'>QQ登录</div></div></div>" +
        "</div>"));

    $('#loginregisterdiv').html(registerElems);
}
function insertForgetPasswordElems() {

}
function closeLoginRegisterPopup() {
    $('#loginregisterbgdiv').hide();
    $('#loginregisterdiv').hide();
    $('#loginregisternavigation').hide();
    isShowLoginRegister = false;
}
function closeLRNavigationPopup() {
    if(isShowLoginRegister && !$('#loginregisternavigation').is(":hidden")){
        $('#loginregisternavigation').hide();
        isShowLoginRegister = false;
    }
}

// 短信验证码
function registerSMSCode(id) {
    if(id == -1 && countdown > 0){
        return;
    }else if(id == -1){
        countdown = 120;
        var phoneNumber = $("#registerdiv").find($("[name='phoneNumber']")).val();
        if(typeof(phoneNumber) == "undefined" || !phoneNumber || phoneNumber == ""){
            $("#loginregisterErrorMsg").addClass("error").text("手机号码不能为空");
            countdown = 0;
            return;
        }else if(!isPhoneNumber(phoneNumber)){
            $("#loginregisterErrorMsg").addClass("error").text("手机号码格式不正确");
            countdown = 0;
            return;
        }else{
            $("#loginregisterErrorMsg").text("");
            sendSMSVCode(phoneNumber);
        }
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
function doVcodeRefresh(data) {
    $("img.vcodeimg").attr("src", "/image/vcode/" + data + ".png");
}
function isPhoneNumber(phone) {
    var pattern = /^1[34578]\d{9}$/;
    return pattern.test(phone);
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
        location.href = "/views/taofang.html";
    }else{
        $("#loginregisterErrorMsg").addClass("error").text(data);
    }
}
function doRegisterSuccess(data){
    var dataArr = data.split(";");
    if(dataArr[0].toLocaleLowerCase() == "ok"){
        $.cookie('userName', $("#registerdiv").find($("[name='userName']")).val(), {expires: 7, path: '/'});
        $.cookie('userId', dataArr[1], {expires: 7, path: '/'});
        location.href = "/views/taofang.html";
    }else{
        $("#loginregisterErrorMsg").addClass("error").text(data);
    }
}

function createOrderPopup() {
    if(!document.getElementById("liangfangorderselectdiv")){
        createLiangfangOrderDiv();
    }else{
        if($('#liangfangorderbgdiv').is(":hidden")){
            $('#liangfangorderbgdiv').show();
            $('#liangfangorderselectdiv').show();
        }else{
            $('#liangfangorderbgdiv').hide();
            $('#liangfangorderselectdiv').hide();
        }
    }
}
function createSCFXPopup() {
    if(!document.getElementById("liangfangscfxdiv")){
        var scfxDivElem = $("<div id='liangfangscfxdiv' class='liangfangscfxdiv'></div>")
            .append($("<div id='liangfangscfx_sc' class='liangfangscfx' onclick='doUserCollect()'><div class='liangfangscfximg'><img src='/image/common/collect.png' /></div><div class='liangfangscfxtext'>收藏</div></div>"))
            .append($("<div id='liangfangscfx_fx' class='liangfangscfx' onclick='doUserShare()'><div class='liangfangscfximg'><img src='/image/common/share_black.png' /></div><div class='liangfangscfxtext'>分享</div></div>"));
        $('body').append(scfxDivElem).append("<script type='text/javascript' src='/js/taofang-clipboard.js'></script>");

        setTimeout(function() {
            isShowSCFX = true;
        }, 500);
    }else{
        if(isShowSCFX){
            $("#liangfangscfxdiv").hide();
            isShowSCFX = false;
        }else{
            $("#liangfangscfxdiv").show();
            setTimeout(function() {
                isShowSCFX = true;
            }, 500);
        }
    }
}
function doUserCollect(clickId, clickTitle){
    var userId = $.cookie('userId');
    $("#liangfangscfxdiv").hide();
    isShowSCFX = false;
    if(typeof(userId) == "undefined" || !userId || userId == ""){
        createUnloginPopup();
    }else{
        postUserCollect(userId, clickId, clickTitle);
    }
}
function doUserShare(){
}
function createUnloginPopup() {
    var sWidth = $(document).width();
    var sHeight = $(document).height();
    if($(document).scrollHeight > sHeight ){
        sHeight = $(document).scrollHeight;
    }
    var bgWH = "width:" + sWidth + "px;" + "height:" + sHeight + "px;";
    if(!document.getElementById("liangfangscfxunlogindiv")){
        // 背景界面
        var backgroundDivElem = $("<div id='liangfangscfxunloginbgdiv' class='liangfangscfxunloginbgdiv'></div>");
        backgroundDivElem.attr("style", bgWH);
        // 登陆界面
        var scfxUnloginDivElem = $("<div id='liangfangscfxunlogindiv' class='liangfangscfxunlogindiv'></div>")
            .append($("<div class='liangfangscfxunloginclosediv'><div onclick='closeSCFXUnlogin()'><img src='/image/common/close.png'></div></div>"))
            .append($("<div class='liangfangscfxunloginpromptdiv'>" +
                        "<div class='liangfangscfxunloginpromptimg'><img src='/image/common/note_yellow.png'/></div>" +
                        "<div class='liangfangscfxunloginprompttext'>使用此功能前请登录</div>" +
                    "</div>"))
            .append($("<div class='liangfangscfxunloginloginregisterdiv'>" +
                        "<div class='liangfangscfxunloginlogindiv'><div class='liangfangscfxunloginlogin' onclick='liangfangLogin()'>马上去登录</div></div>" +
                        "<div class='liangfangscfxunloginregisterdiv'><div class='liangfangscfxunloginregister' onclick='liangfangRegister()'>新用户注册</div></div>" +
                    "</div>"));
        $('body').append(backgroundDivElem).append(scfxUnloginDivElem);
    }else{
        $("#liangfangscfxunloginbgdiv").attr("style", bgWH).show();
        $("#liangfangscfxunlogindiv").show();
    }
}
function liangfangLogin(){
    closeSCFXUnlogin();
    showLoginRegisterPopup("login");
}
function liangfangRegister() {
    closeSCFXUnlogin();
    showLoginRegisterPopup("register");
}
function closeSCFXUnlogin(){
    $("#liangfangscfxunlogindiv").hide();
    $("#liangfangscfxunloginbgdiv").hide();
}
function createUserCollectResultPopup(collectMsg) {
    var sWidth = $(document).width();
    var sHeight = $(document).height();
    if($(document).scrollHeight > sHeight ){
        sHeight = $(document).scrollHeight;
    }
    var bgWH = "width:" + sWidth + "px;" + "height:" + sHeight + "px;";
    var leftW = "left:" + (sWidth - 180)/2 + "px";
    if(!document.getElementById("liangfangscfxcollectdiv")){
        // 背景界面
        var backgroundDivElem = $("<div id='liangfangscfxcollectbgdiv' class='liangfangscfxunloginbgdiv'></div>");
        backgroundDivElem.attr("style", bgWH);
        var liangfangCollectElemDiv = $("<div id='liangfangscfxcollectdiv' class='liangfangscfxcollectdiv'></div>")
            .append("<div class='liangfangscfxcollectimage'><img src='/image/common/heart.png' /></div>")
            .append("<div class='liangfangscfxcollecttext'></div>").attr("style", leftW);
        $('body').append(backgroundDivElem).append(liangfangCollectElemDiv);
    }else{
        $("#liangfangscfxcollectbgdiv").attr("style", bgWH).show();
        $("#liangfangscfxcollectdiv").attr("style", leftW).show();
    }
    $("div.liangfangscfxcollecttext").html(collectMsg);
    setTimeout(function() {
        $('#liangfangscfxcollectdiv').hide();
        $('#liangfangscfxcollectbgdiv').hide();
    }, 2000)
}
function closeSCFXPopup() {
    if(isShowSCFX && !$('#liangfangscfxdiv').is(":hidden")){
        $('#liangfangscfxdiv').hide();
        isShowSCFX = false;
    }
}
function createWYPLPopup() {

}

function createLiangfangSCFXDiv() {
    var sWidth = $(document).width();
    var sHeight = $(document).height();
    if($(document).scrollHeight > sHeight ){
        sHeight = $(document).scrollHeight;
    }
    // 背景界面
    var backgroundDivElem = $("<div id='liangfangscfxbgdiv' class='liangfangscfxbgdiv'></div>");
    var bgWH = "width:" + sWidth + "px;" + "height:" + sHeight + "px;";
    backgroundDivElem.attr("style", bgWH);
    $('body').append(backgroundDivElem);
    // 内容界面
    var scfxDivElem = $("<div id='liangfangscfxdiv' class='liangfangscfxdiv'></div>")
        .append($("<div><div class='liangfangscfximg'><img src='/image/common/collect.png' /></div><div class='liangfangscfxtext'>收藏</div></div>"))
        .append($("<div><div class='liangfangscfximg'><img src='/image/common/share_black.png' /></div><div class='liangfangscfxtext'>分享</div></div>"));
    $('body').append(scfxDivElem);
}
function createLiangfangOrderDiv() {
    var sWidth = $(document).width();
    var sHeight = $(document).height();
    if($(document).scrollHeight > sHeight ){
        sHeight = $(document).scrollHeight;
    }
    // 背景界面
    var backgroundDivElem = $("<div id='liangfangorderbgdiv' class='liangfangorderbgdiv'></div>");
    var bgWH = "width:" + sWidth + "px;" + "height:" + sHeight + "px;";
    backgroundDivElem.attr("style", bgWH);
    $('body').append(backgroundDivElem);
    // 内容界面
    var selectDivElem = $("<div id='liangfangorderselectdiv' class='liangfangorderselectdiv'></div>");
    var selectTableElem = $("<table><tbody></tbody></table>");
    selectTableElem
        .append($("<tr id='liangfangorderselectdivtr1' onclick='liangfangSearchByOrderId(1, \"验证人数\")'><td>验证人数排序</td><td><img src='/image/common/selected.png'/></td></tr>"))
        .append($("<tr id='liangfangorderselectdivtr2' onclick='liangfangSearchByOrderId(2, \"有效程度\")'><td>有效程度排序</td><td><img src='/image/common/selected.png'/></td></tr>"))
        .append($("<tr id='liangfangorderselectdivtr3' onclick='liangfangSearchByOrderId(3, \"见效速度\")'><td>见效速度排序</td><td><img src='/image/common/selected.png'/></td></tr>"))
        .append($("<tr id='liangfangorderselectdivtr4' onclick='liangfangSearchByOrderId(4, \"安全性由\")'><td>安全性由排序</td><td><img src='/image/common/selected.png'/></td></tr>"))
        .append($("<tr id='liangfangorderselectdivtr5' onclick='liangfangSearchByOrderId(5, \"方便性由\")'><td>方便性由排序</td><td><img src='/image/common/selected.png'/></td></tr>"))
        .append($("<tr id='liangfangorderselectdivtr6' onclick='liangfangSearchByOrderId(6, \"省钱程度\")'><td>省钱程度排序</td><td><img src='/image/common/selected.png'/></td></tr>"))
    selectDivElem.append(selectTableElem);
    $('body').append(selectDivElem);
}
function liangfangSearchByOrderId(orderId, orderName) {
    $.cookie('orderId', orderId, {expires: 7, path: '/'});
    var prescription = $("#prescriptioninput").val();
    getLiangfangList(prescription, orderId, 1, 5);
    $("#liangfangsearchcountorder").html(orderName);
    $('#liangfangorderbgdiv').hide();
    $('#liangfangorderselectdiv').hide();
    $('#liangfangorderselectdiv table tbody tr td:nth-child(1)').attr("style", "color:#000000");
    $('#liangfangorderselectdiv table tbody tr td:nth-child(2) img').attr("style", "display: none");
    $('#liangfangorderselectdivtr' + orderId +' td:nth-child(1)').attr("style", "color:#F39F51");
    $('#liangfangorderselectdivtr' + orderId +' td:nth-child(2) img').attr("style", "display: block");
}
