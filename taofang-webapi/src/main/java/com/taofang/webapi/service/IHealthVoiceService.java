package com.taofang.webapi.service;

import com.taofang.webapi.domain.HealthVoice;

import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-01
 */
public interface IHealthVoiceService {
    /**
     * 查询健康之声的总数
     * @param dateStr
     * @return
     */
    int getHealthVoiceAmountByDate(String dateStr);

    /**
     * 分页获取健康之声的信息
     * @param dateStr
     * @param start
     * @param limit
     * @return
     */
    List<HealthVoice> getHealthVoiceByPagination(String dateStr, int start, int limit);
}
