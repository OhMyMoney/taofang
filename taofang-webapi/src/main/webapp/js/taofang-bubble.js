var sWidth = $(document).width();
var sHeight = 280;
$(".homeheaderdiseaseshow").attr("style","width:"+ 3*sWidth*0.96 + "px;height:" + sHeight + "px;");
$(".homeheaderdisease").attr("style","width:"+ sWidth*0.96 + "px;height:" + sHeight + "px;");
var diseaseBase = {
    config:{
        index:0,
        auto:true,
        direct:'left'
    },
    init:function(){
        this.slide = this.$id('homeheaderdiseasediv');
        this.img_div = this.$c('homeheaderdiseaseshow')[0],
        this.slide_btn = this.$tag('a',this.$c('homeheaderdiseasebutt')[0]);
        this.img_arr = this.$tag1('div','homeheaderdisease',this.img_div);
        if(this.config.auto) this.play();
        this.hover();
    },
    $id:function(id){return document.getElementById(id);},
    $tag:function(tagName,obj){return (obj ?obj : document).getElementsByTagName(tagName);	},
    $tag1:function(tagName, className, obj){
        var tags = obj.getElementsByTagName(tagName), arr=[];
        for(var i=0;i<tags.length;i++){
            if (tags[i].className == className){
                arr.push(tags[i]);
            }
        }
        return arr;
    },
    $c: function (claN,obj){
        var tag = this.$tag('*'),reg = new RegExp('(^|\\s)'+claN+'(\\s|$)'),arr=[];
        for(var i=0;i<tag.length;i++){
            if (reg.test(tag[i].className)){
                arr.push(tag[i]);
            }
        }
        return arr;
    },
    $add:function(obj,claN){
        reg = new RegExp('(^|\\s)'+claN+'(\\s|$)');
        if (!reg.test(obj.className)){

            obj.className += ' '+claN;
        }
    },
    $remve:function(obj,claN){var cla=obj.className,reg="/\\s*"+claN+"\\b/g";obj.className=cla?cla.replace(eval(reg),''):''},
    css:function(obj,attr,value){
        if(value){
            obj.style[attr] = value;
        }else{
            return  typeof window.getComputedStyle != 'undefined' ? window.getComputedStyle(obj,null)[attr] : obj.currentStyle[attr];
        }
    },
    animate:function(obj,attr,val){
        var d = 1000;//动画时间一秒完成。
        if(obj[attr+'timer']) clearInterval(obj[attr+'timer']);
        var start = parseInt(diseaseBase.css(obj,attr));//动画开始位置
        //space = 动画结束位置-动画开始位置，即动画要运动的距离。
        var space =  val- start,st=(new Date).getTime(),m=space>0? 'ceil':'floor';
        obj[attr+'timer'] = setInterval(function(){
            var t=(new Date).getTime()-st;//表示运行了多少时间，
            if (t < d){//如果运行时间小于动画时间
                diseaseBase.css(obj,attr,Math[m](diseaseBase.easing['easeOut'](t,start,space,d)) +'px');
            }else{
                clearInterval(obj[attr+'timer']);
                diseaseBase.css(obj,attr,top+space+'px');
            }
        },20);
    },
    play:function(){
        this.slide.timer = setInterval(function(){
            diseaseBase.config.index++;
            if(diseaseBase.config.index>=diseaseBase.img_arr.length) diseaseBase.config.index=0;//如果当前索引大于图片总数，把索引设置0

            diseaseBase.animate(diseaseBase.img_div,diseaseBase.config.direct,-diseaseBase.config.index*sWidth);
            for(var j=0;j<diseaseBase.slide_btn.length;j++){
                diseaseBase.$remve(diseaseBase.slide_btn[j],'hoverhearer');
            }
            diseaseBase.$add(diseaseBase.slide_btn[diseaseBase.config.index],'hoverhearer');
            $("#homeheaderdiseasediv").attr("style", "background-image:url('/image/bg/paopaobg" + diseaseBase.config.index + ".png')");

        },10000)


    },
    hover:function(){
        for(var i=0;i<this.slide_btn.length;i++){
            this.slide_btn[i].index = i;//储存每个导航的索引值
            this.slide_btn[i].onmouseover = function(){
                if(diseaseBase.slide.timer) clearInterval(diseaseBase.slide.timer);
                diseaseBase.config.index =this.index;

                for(var j=0;j<diseaseBase.slide_btn.length;j++){
                    diseaseBase.$remve(diseaseBase.slide_btn[j],'hoverhearer') ;
                }
                diseaseBase.$add(diseaseBase.slide_btn[diseaseBase.config.index],'hoverhearer');
                diseaseBase.animate(diseaseBase.img_div,diseaseBase.config.direct,-diseaseBase.config.index*sWidth);
            }
            this.slide_btn[i].onmouseout = function(){
                diseaseBase.play();
            }
        }
    },
    easing :{
        linear:function(t,b,c,d){return c*t/d + b;},
        swing:function(t,b,c,d) {return -c/2 * (Math.cos(Math.PI*t/d) - 1) + b;},
        easeIn:function(t,b,c,d){return c*(t/=d)*t*t*t + b;},
        easeOut:function(t,b,c,d){return -c*((t=t/d-1)*t*t*t - 1) + b;},
        easeInOut:function(t,b,c,d){return ((t/=d/2) < 1)?(c/2*t*t*t*t + b):(-c/2*((t-=2)*t*t*t - 2) + b);}
    }
};
diseaseBase.init();

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