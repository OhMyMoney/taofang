package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.constant.ArticleCategory;
import com.taofang.webapi.domain.RelationlinkDomain;
import com.taofang.webapi.model.Relationlink;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-07
 */
public class RelationLinkModelUtil {

    public static RelationlinkDomain tranRelationLinkAsDomain(Relationlink relationlink){
        RelationlinkDomain relationlinkDomain = new RelationlinkDomain();
        relationlinkDomain.setRelationId(relationlink.getId());
        relationlinkDomain.setRelationTitle(Optional.fromNullable(relationlink.getLinktitle()).or(""));
        relationlinkDomain.setRelationLink(Optional.fromNullable(relationlink.getLinkurl()).or(""));
        if(relationlink.getSourcetype() == 0){
            String articleIdStr = relationlinkDomain.getRelationLink().replaceAll("http://99taofang.com/Article/", "").replaceAll(".html", "");
            if(!Strings.isNullOrEmpty(articleIdStr)){
                relationlinkDomain.setRelationArticleCategory(ArticleCategory.getCategoryNameById(2));
                relationlinkDomain.setRelationArticleId(Integer.parseInt(articleIdStr));
            }
        }

        return relationlinkDomain;
    }
    public static List<RelationlinkDomain> tranRelationLinkListAsDomain(List<Relationlink> relationlinkList){
        List<RelationlinkDomain> relationlinkDomainList = new ArrayList<>();
        for(Relationlink relationlink : relationlinkList){
            relationlinkDomainList.add(tranRelationLinkAsDomain(relationlink));
        }
        return relationlinkDomainList;
    }
}
