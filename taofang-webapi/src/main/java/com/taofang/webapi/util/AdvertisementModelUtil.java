package com.taofang.webapi.util;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.taofang.webapi.constant.ImageConstant;
import com.taofang.webapi.domain.AdvertisementDomain;
import com.taofang.webapi.model.Advertisement;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-29
 */
public class AdvertisementModelUtil {

    public static AdvertisementDomain tranAdvertisement(Advertisement advertisement, String category){
        AdvertisementDomain advertisementDomain = new AdvertisementDomain();
        advertisementDomain.setAdId(Optional.fromNullable(advertisement.getId()).or(0));
        advertisementDomain.setAdCategory(category);
        if(!Strings.isNullOrEmpty(advertisement.getAdvertimage())){
            advertisementDomain.setAdImage(ImageConstant.DEFAULT_ADVERT_IMAGE_URL + advertisement.getAdvertimage());
        }else{
            advertisementDomain.setAdImage("");
        }
        advertisementDomain.setAdTitle(Optional.fromNullable(advertisement.getAdverttitle()).or(""));
        advertisementDomain.setAdLink("");

        return advertisementDomain;
    }

    public static List<AdvertisementDomain> tranAdvertisementList(List<Advertisement> advertisementList, String category){
        List<AdvertisementDomain> advertisementDomainList = new ArrayList<>();
        for(Advertisement advertisement : advertisementList){
            advertisementDomainList.add(tranAdvertisement(advertisement, category));
        }

        return advertisementDomainList;
    }
}
