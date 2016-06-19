var client = new ZeroClipboard( $('#liangfangscfx_fx') );
client.on( 'ready', function(event) {
    client.on( 'copy', function(event) {
        var value = 'http://m.99taofang.com/views/taofang.html?prescriptionId=' + $.cookie('currLiangfangId');
        event.clipboardData.setData('text/plain', value);
    });
    client.on( 'aftercopy', function(event) {
        alert('已经复制链接到剪贴板,可以分享该链接给其他人')
    });
});
client.on( 'error', function(event) {
    // console.log( 'ZeroClipboard error of type "' + event.name + '": ' + event.message );
    ZeroClipboard.destroy();
});