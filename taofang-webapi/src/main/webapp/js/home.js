// 生成验证码
function createVerificationCode() {
    var numbers = ['0','1','2','3','4','5','6','7','8','9'];
    var verificationCode = '';
    for(var i = 0 ; i < 4 ; i++){
        verificationCode += '' + numbers[Math.floor(Math.random() * numbers.length)];
    }
    return verificationCode;
}

// 登录验证
function checkLoginForm(){
    var errorMessage = "";
    var verificationCode = $("#loginForm").find($("[name='verificationCode']"));
    if($.trim(verificationCode.val()) == ""){
        errorMessage = "验证码不能为空";
        verificationCode.focus();
    }else if($.trim(verificationCode.val()) != $("#createdCode").text()){
        alert($("#createdCode").text())
        errorMessage = "验证码输入错误,请重新输入";
        verificationCode.val("");
        verificationCode.focus();
    }
    var userName = $("#loginForm").find($("[name='userName']"));
    var password = $("#loginForm").find($("[name='password']"));
    if($.trim(userName.val()) == ""){
        errorMessage = "用户名不能为空";
        userName.focus();
    }else if($.trim(password.val()) == ""){
        errorMessage = "密码不能为空";
        password.focus();
    }
    return errorMessage;
}

function doLoginSuccess(data){
    alert(data);
}