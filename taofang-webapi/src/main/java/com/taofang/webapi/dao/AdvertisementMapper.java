package com.taofang.webapi.dao;

import com.taofang.webapi.model.Advertisement;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdvertisementMapper {
    @Select({
            "select ID, AdvertTitle, AdvertImage, CategoryType from advertisement",
            "where CategoryType = ${categoryType}"
    })
    @ResultMap("BaseResultMap")
    List<Advertisement> selectByCategoryType(@Param("categoryType") int categoryType);
}