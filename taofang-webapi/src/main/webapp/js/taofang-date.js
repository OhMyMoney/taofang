$(function () {
    var opt = {}
    opt.date = {preset : 'date'};
    $('#date').val('').scroller('destroy').scroller($.extend(opt['date'], { theme: 'default',mode: 'scroller',
        display: 'modal', dateFormat: 'yyyymmdd', setText: '确定', cancelText: '取消', dateOrder: 'yyyymmdd',
        dayText: '日', monthText: '月', yearText: '年', endYear:2050, startYear:1970}));
    $('#date').change(searchByDateChange);
});
function searchByDateChange() {
    var date = $('#date').val();
    var dateStr = '搜索"' + parseDate() + '"';
    $('#contentconditionselectdiv').html($("<div class='contentconditionselect'>" + dateStr + "</div>"));
    getJKZSPagination(1, 10, date);
}
function parseDate() {
    var date = $('#date').val();
    var year = date.substr(0, 4);
    var month = date.substr(4, 2);
    var day = date.substr(6, 2);
    return year + "年" + month + "月" + day + "日"
}