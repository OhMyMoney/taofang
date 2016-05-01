package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.taofang.webapi.domain.HealthVoice;
import com.taofang.webapi.domain.HealthVoicePagination;
import com.taofang.webapi.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-01
 */
public class HealthVoiceModelUtil {

    public static HealthVoicePagination tranTotalCount(int page, int pageSize, int totalCount){
        HealthVoicePagination healthVoicePagination = new HealthVoicePagination();
        healthVoicePagination.setCurPage(page);
        healthVoicePagination.setPerPage(pageSize);
        healthVoicePagination.setTotalCount(totalCount);
        healthVoicePagination.setTotalPage(PaginationUtil.getTotalPage(totalCount, pageSize));

        return healthVoicePagination;
    }

    public static HealthVoice tranArticle(Article article){
        HealthVoice healthVoice = new HealthVoice();
        healthVoice.setId(Optional.fromNullable(article.getArticleid()).or(0));
        healthVoice.setTitle(Optional.fromNullable(article.getArticlename()).or(""));
        healthVoice.setUrl(Optional.fromNullable(article.getVideo()).or(""));

        return healthVoice;
    }

    public static List<HealthVoice> tranArticleList(List<Article> articleList){
        List<HealthVoice> healthVoiceList = new ArrayList<>();
        for(Article article : articleList){
            healthVoiceList.add(tranArticle(article));
        }

        return healthVoiceList;
    }
}
