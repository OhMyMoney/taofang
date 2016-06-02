package com.taofang.webapi.service;

import com.taofang.webapi.domain.AdvertisementListDomain;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-29
 */
public interface IAdvertisementService {
    AdvertisementListDomain getAdvertisementListByCategory(String category);
}
