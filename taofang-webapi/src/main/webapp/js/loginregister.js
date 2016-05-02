function backhomepage() {
    location.href = "home.html";
}
function refreshVCode() {
    $.ajax({
        url: "http://192.168.31.199:8080/taofang/webapi/user/vcode",
        success: doVcodeRefresh
    });
}
function doVcodeRefresh(data) {
    $("img.vcodeimg").attr("src", "../image/vcode/" + data + ".png");
}
function login() {
    // 错误消息
    var errorMessage = checkLoginForm();
    $("#loginregisterErrorMsg").addClass("error").text(errorMessage);
    if(errorMessage == ""){
        var userNameVal = $("#login").find($("[name='userName']")).val();
        var passwordVal = $("#login").find($("[name='password']")).val();
        $.ajax({
            timeout: 5000,
            url: "http://192.168.31.199:8080/taofang/webapi/user/login",
            contentType: "application/json",
            data: JSON.stringify({userName : userNameVal, password : passwordVal}),
            type: "post",
            success: doLoginSuccess
        });
    }
}
function register() {
    // 错误消息
    var errorMessage = checkRegisterForm();
    $("#loginregisterErrorMsg").addClass("error").text(errorMessage);
    if(errorMessage == ""){
        var phoneNumberVal = $("#register").find($("[name='phoneNumber']")).val();
        var userNameVal = $("#register").find($("[name='userName']")).val();
        var passwordVal = $("#register").find($("[name='password']")).val();
        var confirmPasswordVal = $("#register").find($("[name='confirmPassword']")).val();
        var smsCodeVal = $("#register").find($("[name='smsCode']")).val();
        $.ajax({
            timeout: 5000,
            url: "http://192.168.31.199:8080/taofang/webapi/user/register",
            contentType: "application/json",
            data: JSON.stringify({phoneNumber:phoneNumberVal, userName:userNameVal, password:passwordVal, confirmPassword:confirmPasswordVal, smsCode:smsCodeVal}),
            type: "post",
            success: doRegisterSuccess
        });
    }
}
function checkLoginForm(){
    var errorMessage = "";
    var verificationCode = $("#login").find($("[name='verificationCode']"));
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
    var userName = $("#login").find($("[name='userName']"));
    var password = $("#login").find($("[name='password']"));
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
    var phoneNumber = $("#register").find($("[name='phoneNumber']"));
    var userName = $("#register").find($("[name='userName']"));
    var password = $("#register").find($("[name='password']"));
    var confirmPassword = $("#register").find($("[name='confirmPassword']"));
    // 验证码=暂时不验证
    var smsCode = $("#register").find($("[name='smsCode']"));
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
    if(data.toLocaleLowerCase() == "ok"){
        $.cookie('userName', $("#login").find($("[name='userName']")).val(), {expires: 7, path: '/'});
        $.cookie('password', $("#login").find($("[name='password']")).val(), {expires: 7, path: '/'});
        location.href = "home.html";
    }else{
        $("#loginregisterErrorMsg").addClass("error").text(data);
    }
}
function doRegisterSuccess(data){
    if(data.toLocaleLowerCase() == "ok"){
        $.cookie('userName', $("#register").find($("[name='userName']")).val(), {expires: 7, path: '/'});
        $.cookie('password', $("#register").find($("[name='password']")).val(), {expires: 7, path: '/'});
        location.href = "home.html";
    }else{
        $("#loginregisterErrorMsg").addClass("error").text(data);
    }
}
function isPhoneNumber(phone) {
    var pattern = /^1[34578]\d{9}$/;
    return pattern.test(phone);
}
function loginByWxQQ(id) {
    switch (id){
        case 1:
            alert("微信登录");
            break;
        case 2:
            alert("QQ登录")
            break;
    }
}
function goRegisterPasswordPage(id) {
    if (id == 1){
        location.href = "register.html";
    }else{
        location.href = "";
    }
}
function registerSMSCode() {
    // 短信验证码
}