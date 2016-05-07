package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.constant.ImageConstant;
import com.taofang.webapi.domain.Ritucharya;
import com.taofang.webapi.domain.RitucharyaPagination;
import com.taofang.webapi.model.ArticleWithBLOBs;
import com.taofang.webapi.model.Dtsbkmusic;
import com.taofang.webapi.model.Dtsimage;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-07
 */
public class RitucharyaModelUtil {

    public static RitucharyaPagination tranTotalCount(int page, int pageSize, int totalCount){
        RitucharyaPagination ritucharyaPagination = new RitucharyaPagination();
        ritucharyaPagination.setCurPage(page);
        ritucharyaPagination.setPerPage(pageSize);
        ritucharyaPagination.setTotalCount(totalCount);
        ritucharyaPagination.setTotalPage(PaginationUtil.getTotalPage(totalCount, pageSize));

        return ritucharyaPagination;
    }

    public static Ritucharya tranArticle(ArticleWithBLOBs article){
        Ritucharya ritucharya = new Ritucharya();
        ritucharya.setId(Optional.fromNullable(article.getArticleid()).or(0));
        ritucharya.setTitle(Optional.fromNullable(article.getArticlename()).or(""));
        // 图片URL
        if(!Strings.isNullOrEmpty(article.getImageurl())){
            ritucharya.setImageUrl(ImageConstant.IMAGE_BASE_URL + "Article/" + article.getImageurl());
        }
        ritucharya.setSummary(Optional.fromNullable(article.getSummary()).or(""));
        ritucharya.setIsdts(Optional.fromNullable(article.getIsdts()).or(-1));
        return ritucharya;
    }

    public static List<Ritucharya> tranArticleList(List<ArticleWithBLOBs> articleList){
        List<Ritucharya> ritucharyaList = new ArrayList<>();
        for(ArticleWithBLOBs article : articleList){
            ritucharyaList.add(tranArticle(article));
        }
        return ritucharyaList;
    }

    public static Ritucharya tranDtsimage(Dtsimage dtsimage, Dtsbkmusic dtsbkmusic){
        Ritucharya ritucharya = new Ritucharya();
        ritucharya.setId(Optional.fromNullable(dtsimage.getId()).or(0));
        ritucharya.setTitle(Optional.fromNullable(dtsimage.getTitle()).or(""));
        ritucharya.setVideoUrl(Optional.fromNullable(dtsimage.getMusic()).or(""));
        ritucharya.setBgVideoUrl(Optional.fromNullable(dtsbkmusic.getMusic()).or(""));

        return ritucharya;
    }

    public static List<Ritucharya> tranDtsimageList(List<Dtsimage> dtsimageList, Dtsbkmusic dtsbkmusic){
        List<Ritucharya> ritucharyaList = new ArrayList<>();
        for(Dtsimage dtsimage : dtsimageList){
            ritucharyaList.add(tranDtsimage(dtsimage, dtsbkmusic));
        }
        return ritucharyaList;
    }
}
