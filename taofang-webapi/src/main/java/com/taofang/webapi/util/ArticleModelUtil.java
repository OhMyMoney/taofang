package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.constant.ArticleCategory;
import com.taofang.webapi.constant.ImageConstant;
import com.taofang.webapi.constant.Ritucharya;
import com.taofang.webapi.domain.ArticleDomain;
import com.taofang.webapi.domain.RitucharyaDomain;
import com.taofang.webapi.model.ArticleWithBLOBs;
import com.taofang.webapi.model.Dtsimage;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-18
 */
public class ArticleModelUtil {

    public static ArticleDomain tranArticleWithBLOBs(ArticleWithBLOBs articleWithBLOBs){
        ArticleDomain articleDomain = new ArticleDomain();
        articleDomain.setArticleId(Optional.fromNullable(articleWithBLOBs.getArticleid()).or(0));
        articleDomain.setArticleTitle(Optional.fromNullable(articleWithBLOBs.getArticlename()).or(""));
        if(articleWithBLOBs.getCategory() != null){
            articleDomain.setCategory(ArticleCategory.getCategoryNameById(articleWithBLOBs.getCategory()));
        }else{
            articleDomain.setCategory("");
        }
        if(Strings.isNullOrEmpty(articleWithBLOBs.getImageurl())){
            articleDomain.setImageUrl("");
        }else{
            articleDomain.setImageUrl(ImageConstant.IMAGE_BASE_URL + "Article/" + articleWithBLOBs.getImageurl());
        }
        articleDomain.setRitucharya(Optional.fromNullable(articleWithBLOBs.getIsdts()).or(0));
        articleDomain.setSummary(Optional.fromNullable(articleWithBLOBs.getSummary()).or(""));
        articleDomain.setVideoUrl(Optional.fromNullable(articleWithBLOBs.getVideo()).or(""));

        return articleDomain;
    }

    public static List<ArticleDomain> tranArticleWithBLOBsList(List<ArticleWithBLOBs> articleWithBLOBsList){
        List<ArticleDomain> articleDomainList = new ArrayList<>();
        for(ArticleWithBLOBs articleWithBLOBs : articleWithBLOBsList){
            articleDomainList.add(tranArticleWithBLOBs(articleWithBLOBs));
        }

        return articleDomainList;
    }

    public static RitucharyaDomain tranDtsimage(Dtsimage dtsimage){
        RitucharyaDomain ritucharyaDomain = new RitucharyaDomain();
        ritucharyaDomain.setRitucharyaId(Optional.fromNullable(dtsimage.getId()).or(0));
        ritucharyaDomain.setRitucharyaTitle(Optional.fromNullable(dtsimage.getTitle()).or(""));
        if(dtsimage.getCategory() != null){
            ritucharyaDomain.setRitucharya(Ritucharya.getRitucharyaNameById(dtsimage.getCategory()));
        }else{
            ritucharyaDomain.setRitucharya("");
        }
        ritucharyaDomain.setCategory("JJYS");
        ritucharyaDomain.setVideoUrl(Optional.fromNullable(dtsimage.getMusic()).or(""));
        if(!Strings.isNullOrEmpty(dtsimage.getImage())){
            ritucharyaDomain.setImageUrl(ImageConstant.IMAGE_BASE_URL + "DTS/" + dtsimage.getImage());
        }

        return ritucharyaDomain;
    }

    public static List<RitucharyaDomain> tranDtsimageList(List<Dtsimage> dtsimageList){
        List<RitucharyaDomain> ritucharyaDomainList = new ArrayList<>();
        for(Dtsimage dtsimage : dtsimageList){
            ritucharyaDomainList.add(tranDtsimage(dtsimage));
        }

        return ritucharyaDomainList;
    }
}
