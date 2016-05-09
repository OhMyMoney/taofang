function doNavigationById(id) {
    var url;
    switch (id){
        case 1:
            url = "home.html";
            break;
        case 2:
            url = "healthvoice/list.html";
            break;
        case 3:
            url = "mystory/list.html";
            break;
        case 4:
            url = "naturetherapy/list.html";
            break;
        case 5:
            url = "ritucharya/list.html";
            break;
        case 6:
            url = "healthinfo/list.html";
            break;
    }
    location.href = url;
}
function goLoginRegisterPage(id) {
    if (id == 1){
        location.href = "login.html";
    }else{
        location.href = "register.html";
    }
}
function getUserViewHistory(userName) {
    $.ajax({
        url: "http://192.168.31.199:8080/taofang/webapi/user/view?userName=" + userName,
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