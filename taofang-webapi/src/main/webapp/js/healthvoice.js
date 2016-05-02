function backtolist() {
    location.href = "list.html";
}
function searchByDateChange() {
    var date = $('#date').val();
    $('#dateselectresult').attr("class", "dateselectresultclass2");
    $('#dateselectresult label').html('搜索"' + parseDate() + '"').attr("class", "dateselectlabelclass");
    $.ajax({
        url: "http://localhost:8080/taofang/webapi/healthvoices/" + date + "?page=1&&pageSize=10",
        success: processHealthVoicePagination
    });
}
function handleAudio(url, id, title) {
    var buttonClass = $('#'+id).attr("class");
    if(buttonClass == "pause"){
        $('#'+id).attr("class", "start");
        $('#label'+id).attr("class", "label1");
        var hvmp3 = document.getElementById("hvmp3");
        hvmp3.pause();
    }else{
        $('#hvmp3').attr("src", url);
        $('button').attr("class", "start");
        $('td').attr("class", "label1");
        $('#healthvoiceheadertd1').attr("class", "lrtabletd1");
        $('#healthvoiceheadertd2').attr("class", "lrtabletd2");
        $('#'+id).attr("class", "pause");
        $('#label'+id).attr("class", "label2");
        $('#audioname').html(title);
        var hvmp3 = document.getElementById("hvmp3");
        hvmp3.play();
    }
}

function processHealthVoicePagination(data) {
    var totalPage = data.totalPage;
    if(totalPage == 0){
        $('#emptylist').show()
        $('#prenextpage').hide();
        $('#healthvoicemp3').hide();
        $("#healthvoicelist").hide();
        $('#emptydate').html('没有"' + parseDate() + '"的相关内容');
    }else{
        $('#emptylist').hide()
        $('#prenextpage').show();
        $('#healthvoicemp3').show();
        $("#healthvoicelist").show();
        var curPage = data.curPage;
        $('#page').html(curPage + "/" + totalPage);
        if(curPage == 1){
            $('#previousimg').attr("src", "../../image/common/previous1.png");
        }else{
            $('#previousimg').attr("src", "../../image/common/previous2.png");
        }
        if(curPage == totalPage){
            $('#nextimg').attr("src", "../../image/common/next2.png");
        }else{
            $('#nextimg').attr("src", "../../image/common/next1.png");
        }
        var healthVoices = data.healthVoices;
        var healthVoiceElems = $("<table><colgroup id='listtd1' span='1' /><colgroup id='listtd2' span='1' /><colgroup id='listtd3' span='1' /><tbody></tbody></table>");
        for(var i=0; i< healthVoices.length; i++){
            var id = healthVoices[i].id;
            var labelid = "label" + id;
            var title = healthVoices[i].title;
            var url = healthVoices[i].url;
            var onclickvalue = "handleAudio('" + url + "'," + id + ", '" + title + "')";
            var healthVoiceElemTr = $("<tr></tr>");
            healthVoiceElemTr.append("<td><button id='" + id + "' data-role='none' class='start' onclick=\"" + onclickvalue + "\"></button></td>");
            healthVoiceElemTr.append("<td id='" + labelid + "' class='label1'>" + title + "</td>");
            healthVoiceElemTr.append("<td><img src='../../image/common/link.png' /></td>");
            healthVoiceElems.append(healthVoiceElemTr);
        }
        $("#healthvoicelist").html(healthVoiceElems);
        $("#audioname").html(healthVoices[0].title);
        $("#hvmp3").attr("src", healthVoices[0].url);
    }
}

function previousnextPage(id) {
    var page = $('#page').text();
    var currpage = page.split("/")[0];
    var totalpage = page.split("/")[1];
    if(id == 1 && currpage != 1){
        $.ajax({
            url: "http://localhost:8080/taofang/webapi/healthvoices?page=" + (parseInt(currpage) - 1) +"&&pageSize=10",
            success: processHealthVoicePagination
        });
    }else if(id == 2 && (currpage < totalpage)){
        $.ajax({
            url: "http://localhost:8080/taofang/webapi/healthvoices?page=" + (parseInt(currpage) + 1) +"&&pageSize=10",
            success: processHealthVoicePagination
        });
    }
}

function parseDate() {
    var date = $('#date').val();
    var year = date.substr(0, 4);
    var month = date.substr(4, 2);
    var day = date.substr(6, 2);
    return year + "年" + month + "月" + day + "日"
}
