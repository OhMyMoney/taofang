// 处理偏方结果分页信息
function processPrescriptionPagination(data) {
    var prescriptions = data.prescriptions;

    var prescriptionsElems = $("<ul></ul>")
    for(var i=0; i< prescriptions.length; i++){
        prescriptionsElems.append("<li>" + prescriptions[i].title + "</li>")
    }
    $("div#prescriptionList").html(prescriptionsElems);
}