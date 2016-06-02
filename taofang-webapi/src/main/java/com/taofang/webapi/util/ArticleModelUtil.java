package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.constant.ArticleCategory;
import com.taofang.webapi.constant.ImageConstant;
import com.taofang.webapi.domain.ArticleDetailDomain;
import com.taofang.webapi.domain.ArticleDomain;
import com.taofang.webapi.domain.RelationlinkDomain;
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
            ritucharyaDomain.setRitucharya(dtsimage.getCategory() + "");
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

    public static RelationlinkDomain tranArticleWithBLOBAsRelationlink(ArticleWithBLOBs articleWithBLOBs){
        RelationlinkDomain relationlink = new RelationlinkDomain();
        relationlink.setRelationId(Optional.fromNullable(articleWithBLOBs.getArticleid()).or(0));
        relationlink.setRelationTitle(Optional.fromNullable(articleWithBLOBs.getArticlename()).or(""));
        return relationlink;
    }

    public static List<RelationlinkDomain> tranArticleWithBLOBListAsRelationlink(List<ArticleWithBLOBs> articleWithBLOBsList){
        List<RelationlinkDomain> relationlinkList = new ArrayList<>();
        for(ArticleWithBLOBs articleWithBLOBs : articleWithBLOBsList){
            relationlinkList.add(tranArticleWithBLOBAsRelationlink(articleWithBLOBs));
        }

        return relationlinkList;
    }

    public static ArticleDetailDomain tranArticleWithBLOBAsDetail(ArticleWithBLOBs articleWithBLOBs){
        ArticleDetailDomain articleDetail = new ArticleDetailDomain();
        articleDetail.setArticleId(Optional.fromNullable(articleWithBLOBs.getArticleid()).or(0));
        articleDetail.setArticleTitle(Optional.fromNullable(articleWithBLOBs.getArticlename()).or(""));
        // 文章图片
        if(!Strings.isNullOrEmpty(articleWithBLOBs.getImageurl())){
            articleDetail.setImageUrl(ImageConstant.IMAGE_BASE_URL + "Article/" + articleWithBLOBs.getImageurl());
        }
        // 文章内容
        String articleContent = articleWithBLOBs.getArticlecontent();
        if(!Strings.isNullOrEmpty(articleContent)){
            articleContent = articleContent.substring(articleContent.indexOf("<h"));
            int videoIndex = articleContent.indexOf("</object></p>");
            if(videoIndex > 0){
                // mp3
                String videoContent = articleContent.substring(0, videoIndex);
                int srcIndex = videoContent.indexOf("src=");
                int typeIndex = videoContent.indexOf("type=");
                String videoUrl = videoContent.substring(srcIndex+4, typeIndex).trim().replaceAll("\"", "");
                articleDetail.setVideoUrl(videoUrl);
                articleContent = articleContent.substring(videoIndex + 13);
            }else{
                articleDetail.setVideoUrl("");
            }
            articleContent = articleContent.substring(articleContent.indexOf("<p"));
            articleContent = articleContent.replaceAll("/Content/Resources/", ImageConstant.IMAGE_BASE_URL);
            articleDetail.setArticleContent(articleContent);
        }
        // 点赞数
        articleDetail.setThumbCount((int) (Math.random() * 100));
        return articleDetail;

    }
}
