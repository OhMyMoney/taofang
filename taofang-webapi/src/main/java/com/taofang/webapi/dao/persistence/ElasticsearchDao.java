package com.taofang.webapi.dao.persistence;

import com.taofang.webapi.bean.LiangfangBean;
import com.taofang.webapi.constant.ElasticsearchConstant;
import com.taofang.webapi.domain.Prescription;
import com.taofang.webapi.domain.PrescriptionPagination;
import com.taofang.webapi.domain.PrescriptionRelateInfo;
import com.taofang.webapi.domain.RelateInfo;
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

    public PrescriptionRelateInfo searchPrescriptionRelationInfos(String name) throws IOException {
        PrescriptionRelateInfo prescriptionRelateInfo = new PrescriptionRelateInfo();

        String query = ElasticsearchModelUtil.createPrescriptionQuery(name, "", 0, 100);
        Search search = new Search.Builder(query).addIndex(ElasticsearchConstant.LIANGFANG_INDEX).build();
        SearchResult result = client.execute(search);
        List<SearchResult.Hit<LiangfangBean, Void>> liangfangHitList = result.getHits(LiangfangBean.class);
        Set<String> diseaseSet = new HashSet<>();
        List<RelateInfo> recommends = new ArrayList<>();
        List<RelateInfo> products = new ArrayList<>();
        for(SearchResult.Hit<LiangfangBean, Void> liangfangHit : liangfangHitList){
            if(recommends.size() < 5){
                String[] d_nameArray = liangfangHit.source.getD_name().split(",");
                for(String d_name : d_nameArray){
                    if(diseaseSet.add(d_name)){
                        recommends.add(new RelateInfo(d_name));
                    }
                }
            }
            if(products.size() < 5){
                String[] s_nameArray = liangfangHit.source.getS_name().split(",");
                for(String s_name : s_nameArray){
                    if(diseaseSet.add(s_name)){
                        products.add(new RelateInfo(s_name));
                    }
                }
            }
            if(recommends.size() >= 5 && products.size() >= 5){
                break;
            }
        }
        prescriptionRelateInfo.setProducts(products);
        prescriptionRelateInfo.setRecommends(recommends);
        return prescriptionRelateInfo;
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
