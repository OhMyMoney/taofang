package com.taofang.webapi.mock;

import com.taofang.webapi.domain.HealthInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-17
 */
public class HealthInfoMock {
    public static List<HealthInfo> mockHealthInfoList(){
        List<HealthInfo> healthInfoList = new ArrayList<>();
        Random random = new Random();
        int listSize = random.nextInt(100);

        for(int i=0; i<listSize; i++){
            HealthInfo healthInfo = new HealthInfo();
            healthInfo.setId(i);
            healthInfo.setTitle("HealthInfo啊啊啊啊啊" + i + "哦哦哦哦哦哦");

            healthInfoList.add(healthInfo);
        }
        return healthInfoList;
    }
}
