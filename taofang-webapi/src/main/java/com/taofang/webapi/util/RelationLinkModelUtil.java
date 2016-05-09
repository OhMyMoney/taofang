package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.taofang.webapi.domain.RelationLinkInfo;
import com.taofang.webapi.model.Relationlink;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-07
 */
public class RelationLinkModelUtil {

    public static RelationLinkInfo tranRelationLink(Relationlink relationlink){
        RelationLinkInfo relationLinkInfo = new RelationLinkInfo();
        relationLinkInfo.setId(relationlink.getId());
        relationLinkInfo.setTitle(Optional.fromNullable(relationlink.getLinktitle()).or(""));
        relationLinkInfo.setLinkUrl(Optional.fromNullable(relationlink.getLinkurl()).or(""));

        return relationLinkInfo;
    }

    public static List<RelationLinkInfo> tranRelationLinkList(List<Relationlink> relationlinkList){
        List<RelationLinkInfo> relationLinkInfoList = new ArrayList<>();
        for(Relationlink relationlink : relationlinkList){
            relationLinkInfoList.add(tranRelationLink(relationlink));
        }
        return relationLinkInfoList;
    }
}
