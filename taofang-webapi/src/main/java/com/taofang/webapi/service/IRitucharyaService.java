package com.taofang.webapi.service;

import com.taofang.webapi.domain.Ritucharya;

import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-07
 */
public interface IRitucharyaService {

    List<Ritucharya> getRitucharyaList();

    int getRitucharyaAmount(int categoryId);

    List<Ritucharya> getRitucharyaDetailPagination(int categoryId, int start, int limit);
}
