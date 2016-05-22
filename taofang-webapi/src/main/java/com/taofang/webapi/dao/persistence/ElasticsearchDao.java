package com.taofang.webapi.dao.persistence;

import com.google.common.base.Strings;
import com.taofang.webapi.bean.LiangfangBean;
import com.taofang.webapi.constant.ElasticsearchConstant;
import com.taofang.webapi.constant.PrecriptionOrder;
import com.taofang.webapi.domain.PaginationDomain;
import com.taofang.webapi.domain.PrescriptionDomain;
import com.taofang.webapi.domain.PrescriptionPaginationDomain;
import com.taofang.webapi.domain.RelationlinkDomain;
import com.taofang.webapi.util.ElasticsearchModelUtil;
import com.taofang.webapi.util.PrescriptionModelUtil;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-08
 */
@Repository
public class ElasticsearchDao{
    // ES客户端
    private JestClient client;

    public PrescriptionPaginationDomain searchPrescriptionPagination(String prescription, String orderName, int page, int start, int limit) throws IOException {
        PrescriptionPaginationDomain prescriptionPagination = new PrescriptionPaginationDomain(prescription, PrecriptionOrder.getOrderIdByName(orderName));
        List<PrescriptionDomain> prescriptionList = new ArrayList<>();
        List<RelationlinkDomain> diseaselinkList = new ArrayList<>();
        List<RelationlinkDomain> symptomlinkList = new ArrayList<>();
        Set<String> diseaseNameSet = new HashSet<>();
        Set<String> symptomNameSet = new HashSet<>();

        String query = ElasticsearchModelUtil.createPrescriptionQuery(prescription, orderName, start, limit);
        Search search = new Search.Builder(query).addIndex(ElasticsearchConstant.LIANGFANG_INDEX).build();

        SearchResult result = client.execute(search);
        int totalCount = result.getTotal();
        prescriptionPagination.setPagination(new PaginationDomain(page, limit, totalCount));
        List<SearchResult.Hit<LiangfangBean, Void>> liangfangHitList = result.getHits(LiangfangBean.class);
        for(SearchResult.Hit<LiangfangBean, Void> liangfangHit : liangfangHitList){
            LiangfangBean liangfangBean = liangfangHit.source;
            prescriptionList.add(PrescriptionModelUtil.tranLiangfangBeanAsPrescription(liangfangBean));

            if(!Strings.isNullOrEmpty(liangfangBean.getD_name())){
                String[] dNames = liangfangBean.getD_name().split(",");
                for(String dName : dNames){
                    diseaseNameSet.add(dName);
                }
            }
            if(!Strings.isNullOrEmpty(liangfangBean.getS_name())){
                String[] sNames = liangfangBean.getS_name().split(",");
                for(String sName : sNames){
                    symptomNameSet.add(sName);
                }
            }
        }
        for(String dName : diseaseNameSet){
            if(diseaselinkList.size() < 5){
                diseaselinkList.add(new RelationlinkDomain(dName));
            }
        }
        for(String sName : symptomNameSet){
            if(symptomlinkList.size() < 5){
                symptomlinkList.add(new RelationlinkDomain(sName));
            }
        }

        prescriptionPagination.setPrescriptionList(prescriptionList);
        prescriptionPagination.setDiseaselinkList(diseaselinkList);
        prescriptionPagination.setSymptomlinkList(symptomlinkList);
        return prescriptionPagination;
    }

    public String searchDiseaseById(int prescriptionId){
        String diseaseNames = "";
        try{
            String query = ElasticsearchModelUtil.createPrescriptionSimpleQuery(prescriptionId);
            Search search = new Search.Builder(query).addIndex(ElasticsearchConstant.LIANGFANG_INDEX).build();
            SearchResult result = client.execute(search);
            SearchResult.Hit<LiangfangBean, Void> liangfangHit = result.getFirstHit(LiangfangBean.class);
            diseaseNames = liangfangHit.source.getD_name();
        }catch(Exception e){
        }
        return diseaseNames;
    }

    public String searchMaterialById(int prescriptionId) throws IOException {
        String query = ElasticsearchModelUtil.createPrescriptionSimpleQuery(prescriptionId);
        Search search = new Search.Builder(query).addIndex(ElasticsearchConstant.LIANGFANG_INDEX).build();
        SearchResult result = client.execute(search);
        SearchResult.Hit<LiangfangBean, Void> liangfangHit = result.getFirstHit(LiangfangBean.class);

        return liangfangHit.source.getP_Material();
    }

    @PostConstruct
    public void init(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(
                new HttpClientConfig
                    .Builder(ElasticsearchConstant.ES_URL)
                    .multiThreaded(true)
                    .build()
        );
        client = factory.getObject();
    }
}
