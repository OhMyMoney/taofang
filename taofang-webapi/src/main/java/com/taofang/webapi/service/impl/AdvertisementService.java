package com.taofang.webapi.service.impl;

import com.taofang.webapi.constant.AdvertisementCategory;
import com.taofang.webapi.dao.AdvertisementMapper;
import com.taofang.webapi.domain.AdvertisementDomain;
import com.taofang.webapi.domain.AdvertisementListDomain;
import com.taofang.webapi.model.Advertisement;
import com.taofang.webapi.service.IAdvertisementService;
import com.taofang.webapi.util.AdvertisementModelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-29
 */
@Service
public class AdvertisementService implements IAdvertisementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementService.class);
    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Override
    public AdvertisementListDomain getAdvertisementListByCategory(String category) {
        AdvertisementListDomain advertisementListDomain = new AdvertisementListDomain(category);
        List<AdvertisementDomain> advertisementDomainList;
        try{
            int categoryType = AdvertisementCategory.getCategoryIdByName(category);
            List<Advertisement> advertisementList = advertisementMapper.selectByCategoryType(categoryType);
            advertisementDomainList = AdvertisementModelUtil.tranAdvertisementList(advertisementList, category);
        }catch(Exception e){
            LOGGER.error(e.getMessage(), e);
            advertisementDomainList = new ArrayList<>();
        }
        advertisementListDomain.setAdvertisementList(advertisementDomainList);
        return advertisementListDomain;
    }
}
