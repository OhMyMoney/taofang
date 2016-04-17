package com.taofang.webapi.util;

import com.taofang.webapi.bean.PrescriptionCondition;
import com.taofang.webapi.constant.PrecriptionOrderEnum;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-16
 */
public class PrescriptionModelUtil {

    public static PrescriptionCondition tranPrescriptionCondition(String name, int page, int pageSize, int orderId){
        PrescriptionCondition condition = new PrescriptionCondition();
        condition.setPrescriptionName(name);
        condition.setCurPage(page);
        condition.setPerPage(pageSize);
        condition.setOrderId(orderId);
        condition.setOrderName(PrecriptionOrderEnum.getOrderNameById(orderId));

        return condition;
    }
}
