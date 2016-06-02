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

    PrescriptionDetailDomain getPrescriptionDetailDomain(String prescription, int pageId);

    PrescriptionCommentDomain getPrescriptionCommentDomain(int prescriptionId);

    PrescriptionMaterialDomain getPrescriptionMaterialDomain(int prescriptionId);
}
