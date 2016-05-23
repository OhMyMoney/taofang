var ajaxBaseUrl = "http://localhost:8080/taofang/webapi";
var wordStatisticsPage = 1;

function getLiangfangList(prescription, order, page, pageSize) {
    var ajaxUrl = ajaxBaseUrl + "/prescription/list?prescription=" + prescription + "&page=" + page + "&pageSize=" + pageSize;
    ajaxUrl += "&order=" + order;
    $.ajax({
        url: ajaxUrl,
        success: processLiangfangPaginationData
    });
}
function getLiangfangDetail(prescriptionId) {
    var ajaxUrl = ajaxBaseUrl + "/prescription/detail/" + prescriptionId;
    $.ajax({
        url: ajaxUrl,
        success: processLiangfangDetailData
    });
}
function getLiangfangComment(prescriptionId) {
    var ajaxUrl = ajaxBaseUrl + "/prescription/comment/" + prescriptionId;
    $.ajax({
        url: ajaxUrl,
        success: processLiangfangCommentData
    });
}
function getLiangfangMaterial(prescriptionId) {
    var ajaxUrl = ajaxBaseUrl + "/prescription/material/" + prescriptionId;
    $.ajax({
        url: ajaxUrl,
        success: processLiangfangMaterialData
    });
}

function getArticlePagination(category, page, pageSize) {
    var ajaxUrl = ajaxBaseUrl + "/article/list/" + category + "?page=" + page + "&pageSize=" + pageSize;
    $.ajax({
        url: ajaxUrl,
        success: processArticlePaginationData
    });
}
function getJKZSPagination(page, pageSize, dateStr){
    var ajaxUrl = ajaxBaseUrl + "/article/list/JKZS?page=" + page + "&pageSize=" + pageSize;
    var notHashDate = typeof(dateStr) == "undefined" || !dateStr || dateStr == "";
    if(!notHashDate){
        ajaxUrl += ("&queryDate=" + dateStr);
    }
    $.ajax({
        url: ajaxUrl,
        success: processArticlePaginationData
    });
}
function getRitucharyaPagination(category, page, pageSize) {
    var ajaxUrl = ajaxBaseUrl + "/article/ritucharya/list/" + category + "?page=" + page + "&pageSize=" + pageSize;
    $.ajax({
        url: ajaxUrl,
        success: processRitucharyaPaginationData
    });
}
function getArticleDetail(category, id) {
    var ajaxUrl = ajaxBaseUrl + "/article/detail/" + id + "?category=" + category;
    $.ajax({
        url: ajaxUrl,
        success: processArticleDetailData
    });
}
function getUserInfo() {
    var userId = $.cookie('userId');
    var ajaxUrl = ajaxBaseUrl + "/user/userinfo/" + userId;
    $.ajax({
        url: ajaxUrl,
        success: processUserInfoData
    });
}
function getUserDetail(module) {
    var userId = $.cookie('userId');
    var ajaxUrl = ajaxBaseUrl + "/user/detail/" + userId + "?module=" + module;
    $.ajax({
        url: ajaxUrl,
        success: processUserDetailData
    });
}
function getUserView() {
    var userId = $.cookie('userId');
    var ajaxUrl = ajaxBaseUrl + "/user/view/" + userId;
    $.ajax({
        url: ajaxUrl,
        success: processUserViewData
    });
}
function getHomeUserView() {
    var userId = $.cookie('userId');
    var isUserView = typeof(userId) == "undefined" || !userId || userId == "";
    if(!isUserView){
        var ajaxUrl = ajaxBaseUrl + "/user/view/" + userId;
        $.ajax({
            url: ajaxUrl,
            success: processHomeUserViewData
        });
    }
}
function getWordStatistics() {
    var ajaxUrl = ajaxBaseUrl + "/word/statistics?pageSize=10&page=" + wordStatisticsPage;
    wordStatisticsPage += 1;
    $.ajax({
        url: ajaxUrl,
        success: processWordStatisticsData
    });
    setTimeout(function() {
        if(wordStatisticsPage > 30){
            wordStatisticsPage = 1;
        }
        getWordStatistics();
    }, 60000)
}


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
function shareArticleDetail() {

}
