package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.taofang.webapi.domain.DiseaseInfo;
import com.taofang.webapi.model.Wordstatistics;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-14
 */
public class DiseaseModelUtil {

    public static DiseaseInfo tranWordstatistics(Wordstatistics wordstatistics){
        DiseaseInfo diseaseInfo = new DiseaseInfo();
        diseaseInfo.setId(Integer.parseInt(Optional.fromNullable(wordstatistics.getWordstatisticsid()).or(0L) + ""));
        diseaseInfo.setCount(Integer.parseInt(Optional.fromNullable(wordstatistics.getSearchcount()).or(0L) + ""));
        diseaseInfo.setName(Optional.fromNullable(wordstatistics.getWord()).or(""));
        return diseaseInfo;
    }

    public static List<DiseaseInfo> tranWordstatisticsList(List<Wordstatistics> wordstatisticsList){
        List<DiseaseInfo> diseaseInfoList = new ArrayList<>();
        for(Wordstatistics wordstatistics : wordstatisticsList){
            diseaseInfoList.add(tranWordstatistics(wordstatistics));
        }
        return diseaseInfoList;
    }
}
