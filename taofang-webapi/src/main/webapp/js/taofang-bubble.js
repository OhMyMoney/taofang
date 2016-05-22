function mockBubbleChart(wordsearchlist) {
    var wordSearchElems = $("<div></div>");
    var wordSearchLine1Elems = $("<div class='wordsearchline1div'></div>");
    var wordSearchLine2Elems = $("<div class='wordsearchline2div'></div>");
    var wordSearchLine3Elems = $("<div class='wordsearchline3div'></div>");
    for(var i=0; i<wordsearchlist.length; i++){
        var word = wordsearchlist[i];
        var onClickFun = "createLiangfangPage(\"LIST\", 1, 5, \"" + word.wordName + "\", 0)";
        if(i < 3){
            wordSearchLine1Elems
                .append($("<div class='wordsearchline1'><div onclick='" + onClickFun + "' class='wordsearchline1" + i + "'>" + word.wordName + "</div></div>"));
        }else if(i > 5){
            wordSearchLine3Elems
                .append($("<div class='wordsearchline3'><div onclick='" + onClickFun + "' class='wordsearchline3" + (i-6) + "'>" + word.wordName + "</div></div>"));
        }else{
            wordSearchLine2Elems
                .append($("<div class='wordsearchline2'><div onclick='" + onClickFun + "' class='wordsearchline2" + (i-3) + "'>" + word.wordName + "</div></div>"));
        }
    }
    wordSearchElems
        .append(wordSearchLine1Elems)
        .append(wordSearchLine2Elems)
        .append(wordSearchLine3Elems);
    $("#homeheaderdiseasediv").html(wordSearchElems);

}