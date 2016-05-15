package com.taofang.webapi.service;

import com.taofang.webapi.domain.PrescriptionPagination;
import com.taofang.webapi.domain.PrescriptionRelateInfo;
import com.taofang.webapi.domain.PrescriptionWithLinks;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-16
 */
public interface IPrescriptionService {
    PrescriptionPagination getPrescriptionPagination(String prescription, String sort, int page, int pageSize);

    PrescriptionRelateInfo getPrescriptionRelateInfo(String prescription);

    PrescriptionWithLinks getPrescriptionWithLinksById(int id);

    String getMaterialById(int id);
}
