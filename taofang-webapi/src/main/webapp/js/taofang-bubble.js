var sWidth = $(document).width();
var sHeight = 300;
var count = 0;
$(function(){
    $(".homeheaderdiseaseshow").luara({width:sWidth*0.96,height:sHeight,interval:10000,selected:"seleted"});
    changeBackground();
});
function changeBackground() {
    var index = count % 3;
    count += 1;
    $("#homeheaderdiseasediv").attr("style", "background-image:url('/image/bg/paopaobg" + index + ".png')");
    setTimeout(function () {
        changeBackground();
    }, 10000)
}

function mockBubbleChart(wordsearchlist) {
    var wordSearchElems1 = $("<div></div>");
    var wordSearchLine11Elems = $("<div class='wordsearchline1div'></div>");
    var wordSearchLine12Elems = $("<div class='wordsearchline2div'></div>");
    var wordSearchLine13Elems = $("<div class='wordsearchline3div'></div>");
    for(var i1=0; i1<10; i1++){
        var word = wordsearchlist[i1];
        var onClickFun = "createLiangfangPage(\"LIST\", 1, 5, \"" + word.wordName + "\", 0)";
        if(i1 < 3){
            wordSearchLine11Elems
                .append($("<div class='wordsearchline1'><div onclick='" + onClickFun + "' class='wordsearchline1" + i1 + "'>" + word.wordName + "</div></div>"));
        }else if(i1 > 5){
            wordSearchLine13Elems
                .append($("<div class='wordsearchline3'><div onclick='" + onClickFun + "' class='wordsearchline3" + (i1-6) + "'>" + word.wordName + "</div></div>"));
        }else{
            wordSearchLine12Elems
                .append($("<div class='wordsearchline2'><div onclick='" + onClickFun + "' class='wordsearchline2" + (i1-3) + "'>" + word.wordName + "</div></div>"));
        }
    }
    wordSearchElems1
        .append(wordSearchLine11Elems)
        .append(wordSearchLine12Elems)
        .append(wordSearchLine13Elems);
    $("#homeheaderdiseasediv1").html(wordSearchElems1);

    var wordSearchElems2 = $("<div></div>");
    var wordSearchLine21Elems = $("<div class='wordsearchline4div'></div>");
    var wordSearchLine22Elems = $("<div class='wordsearchline5div'></div>");
    var wordSearchLine23Elems = $("<div class='wordsearchline6div'></div>");
    for(var i2=10; i2<20; i2++){
        var word = wordsearchlist[i2];
        var onClickFun = "createLiangfangPage(\"LIST\", 1, 5, \"" + word.wordName + "\", 0)";
        if(i2 < 13){
            wordSearchLine21Elems
                .append($("<div class='wordsearchline4'><div onclick='" + onClickFun + "' class='wordsearchline4" + (i2-10) + "'>" + word.wordName + "</div></div>"));
        }else if(i2 > 15){
            wordSearchLine23Elems
                .append($("<div class='wordsearchline6'><div onclick='" + onClickFun + "' class='wordsearchline6" + (i2-16) + "'>" + word.wordName + "</div></div>"));
        }else{
            wordSearchLine22Elems
                .append($("<div class='wordsearchline5'><div onclick='" + onClickFun + "' class='wordsearchline5" + (i2-13) + "'>" + word.wordName + "</div></div>"));
        }
    }
    wordSearchElems2
        .append(wordSearchLine21Elems)
        .append(wordSearchLine22Elems)
        .append(wordSearchLine23Elems);
    $("#homeheaderdiseasediv2").html(wordSearchElems2);


    var wordSearchElems3 = $("<div></div>");
    var wordSearchLine31Elems = $("<div class='wordsearchline7div'></div>");
    var wordSearchLine32Elems = $("<div class='wordsearchline8div'></div>");
    var wordSearchLine33Elems = $("<div class='wordsearchline9div'></div>");
    for(var i3=20; i3<30; i3++){
        var word = wordsearchlist[i3];
        var onClickFun = "createLiangfangPage(\"LIST\", 1, 5, \"" + word.wordName + "\", 0)";
        if(i3 < 23){
            wordSearchLine31Elems
                .append($("<div class='wordsearchline7'><div onclick='" + onClickFun + "' class='wordsearchline7" + (i3-20) + "'>" + word.wordName + "</div></div>"));
        }else if(i3 > 25){
            wordSearchLine33Elems
                .append($("<div class='wordsearchline9'><div onclick='" + onClickFun + "' class='wordsearchline9" + (i3-26) + "'>" + word.wordName + "</div></div>"));
        }else{
            wordSearchLine32Elems
                .append($("<div class='wordsearchline8'><div onclick='" + onClickFun + "' class='wordsearchline8" + (i3-23) + "'>" + word.wordName + "</div></div>"));
        }
    }
    wordSearchElems3
        .append(wordSearchLine31Elems)
        .append(wordSearchLine32Elems)
        .append(wordSearchLine33Elems);
    $("#homeheaderdiseasediv3").html(wordSearchElems3);
}