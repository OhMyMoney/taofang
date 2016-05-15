package com.taofang.webapi.service;

import com.taofang.webapi.domain.DiseaseInfo;

import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-14
 */
public interface IDiseaseService {
    List<DiseaseInfo> getDiseaseSearchStatistics();
}
