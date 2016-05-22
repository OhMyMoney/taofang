package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.taofang.webapi.domain.WordSearchDomain;
import com.taofang.webapi.model.Wordstatistics;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-22
 */
public class WordstatisticsModelUtil {

    public static WordSearchDomain tranWordstatisticsAsDomain(Wordstatistics wordstatistics){
        WordSearchDomain wordSearch = new WordSearchDomain();
        wordSearch.setWordId(Optional.fromNullable(wordstatistics.getWordstatisticsid()).or(0L));
        wordSearch.setWordName(Optional.fromNullable(wordstatistics.getWord()).or(""));
        wordSearch.setSearchCount(Optional.fromNullable(wordstatistics.getSearchcount()).or(0L));
        wordSearch.setWordType("");
        return wordSearch;
    }

    public static List<WordSearchDomain> tranWordstatisticsListAsDomain(List<Wordstatistics> wordstatisticsList){
        List<WordSearchDomain> wordSearchDomainList = new ArrayList<>();
        for(Wordstatistics wordstatistics : wordstatisticsList){
            wordSearchDomainList.add(tranWordstatisticsAsDomain(wordstatistics));
        }
        return wordSearchDomainList;
    }
}
