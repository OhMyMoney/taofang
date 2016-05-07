package com.taofang.webapi.service;

import com.taofang.webapi.domain.NatureTherapyInfo;

import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-04
 */
public interface INatureTherapyService {
    int getNatureTherapyAmount();

    List<NatureTherapyInfo> getNatureTherapyByPagination(int start, int limit);
}
