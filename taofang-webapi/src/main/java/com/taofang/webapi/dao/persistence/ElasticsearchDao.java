package com.taofang.webapi.dao.persistence;

import com.taofang.webapi.bean.LiangfangBean;
import com.taofang.webapi.constant.ElasticsearchConstant;
import com.taofang.webapi.domain.Prescription;
import com.taofang.webapi.domain.PrescriptionPagination;
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
import java.util.List;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-08
 */
@Repository
public class ElasticsearchDao{
    // ES客户端
    private JestClient client;

    public PrescriptionPagination searchPrescription(String name, String sortName, int start, int limit) throws IOException {
        List<Prescription> prescriptionList = new ArrayList<>();
        String query = ElasticsearchModelUtil.createPrescriptionQuery(name, sortName, start, limit);
        Search search = new Search.Builder(query).addIndex(ElasticsearchConstant.LIANGFANG_INDEX).build();

        SearchResult result = client.execute(search);
        int totalCount = result.getTotal();
        List<SearchResult.Hit<LiangfangBean, Void>> liangfangHitList = result.getHits(LiangfangBean.class);
        for(SearchResult.Hit<LiangfangBean, Void> liangfangHit : liangfangHitList){
            prescriptionList.add(PrescriptionModelUtil.tranLiangfangBean(liangfangHit.source));
        }
        return new PrescriptionPagination(totalCount, prescriptionList);
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
