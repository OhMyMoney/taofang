package com.taofang.webapi.util;

import com.taofang.webapi.bean.HealthInfoCondition;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-17
 */
public class HealthInfoModelUtil {

    public static HealthInfoCondition tranHealthInfoCondition(int page, int pageSize){
        HealthInfoCondition condition = new HealthInfoCondition();
        condition.setPerPage(pageSize);
        condition.setCurPage(page);

        return condition;
    }
}
