package com.taofang.webapi.service;

import com.taofang.webapi.domain.*;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-16
 */
public interface IPrescriptionService {
    PrescriptionPaginationDomain getPrescriptionPaginationDomain(String prescription, String orderName, int page, int pageSize);

    PrescriptionDetailDomain getPrescriptionDetailDomain(int prescriptionId);

    PrescriptionCommentDomain getPrescriptionCommentDomain(int prescriptionId);

    PrescriptionMaterialDomain getPrescriptionMaterialDomain(int prescriptionId);


    PrescriptionPagination getPrescriptionPagination(String prescription, String sort, int page, int pageSize);

    PrescriptionRelateInfo getPrescriptionRelateInfo(String prescription);

    PrescriptionWithLinks getPrescriptionWithLinksById(int id);

    String getMaterialById(int id);
}
