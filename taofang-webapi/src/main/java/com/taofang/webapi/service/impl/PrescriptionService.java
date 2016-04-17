package com.taofang.webapi.service.impl;

import com.taofang.webapi.bean.PrescriptionCondition;
import com.taofang.webapi.domain.Prescription;
import com.taofang.webapi.domain.PrescriptionPagination;
import com.taofang.webapi.domain.PrescriptionRelateInfo;
import com.taofang.webapi.mock.PresciptionMock;
import com.taofang.webapi.service.IPrescriptionService;
import com.taofang.webapi.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-16
 */
@Service
public class PrescriptionService implements IPrescriptionService{
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionService.class);

    @Override
    public PrescriptionPagination getPrescriptionPaginationByCondition(PrescriptionCondition condition) {
        PrescriptionPagination prescriptionPagination = new PrescriptionPagination();

        List<Prescription> mockPrescriptionList = PresciptionMock.mockPrescriptionList(36);
        prescriptionPagination.setCurPage(condition.getCurPage());
        prescriptionPagination.setPerPage(condition.getPerPage());
        prescriptionPagination.setTotalCount(mockPrescriptionList.size());
        prescriptionPagination.setTotalPage(PaginationUtil.getTotalPage(mockPrescriptionList.size(), condition.getPerPage()));
        int start = (condition.getCurPage() - 1) * condition.getPerPage();
        int end = start + condition.getPerPage();
        prescriptionPagination.setPrescriptionList(mockPrescriptionList.subList(start, end));

        return prescriptionPagination;
    }

    @Override
    public PrescriptionRelateInfo getPrescriptionRelateInfoByName(String prescriptionName) {
        PrescriptionRelateInfo prescriptionRelateInfo = new PrescriptionRelateInfo();
        prescriptionRelateInfo.setFoodTherapies(PresciptionMock.mockRelateInfo(5, "FoodTherapies"));
        prescriptionRelateInfo.setRecommends(PresciptionMock.mockRelateInfo(5, "Recommends"));
        prescriptionRelateInfo.setNaturalRemedies(PresciptionMock.mockRelateInfo(5, "NaturalRemedies"));
        prescriptionRelateInfo.setProducts(PresciptionMock.mockRelateInfo(5, "Products"));

        return prescriptionRelateInfo;
    }

}
