function backhomepage() {
    location.href = "home.html";
}

function refreshVCode() {
    alert(1);
}

function login() {
    alert("login()");
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
    alert("registerSMSCode()");
}

function register() {
    alert("register()");
}