package com.taofang.webapi.util;

import com.google.common.base.Strings;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-08
 */
public class ElasticsearchModelUtil {

    public static String createPrescriptionQuery(String name, String sortName, int start, int limit){
        String query =
                "    \"from\": " + start + "," +
                "    \"size\": " + limit + "," +
                "    \"query\": {\n" +
                "            \"query_string\" : {\n" +
                "                \"query\" : \"" + name.replaceAll("病", "") + "\",\n" +
                "                \"fields\" : [\"d_aliasname.*\",\"d_name.*^2\",\"s_name.*^2\",\"p_name.*\",\"p_Material.*\"],\n" +
                "                \"use_dis_max\" : true" +
                "            }\n" +
                "    }\n";
        if(!Strings.isNullOrEmpty(sortName)){
            query += ",\"sort\":[{\"" + sortName + "\":{\"order\" : \"desc\"}},{\"_score\": {\"order\": \"desc\"}}]";
        }else{
            query += ",\"sort\":[{\"_score\":{\"order\": \"desc\"}},{\"p_Score\": {\"order\": \"asc\"}}]";
        }
        String fullQuery = "{\n" + query + "}";
        return fullQuery;
    }

    public static String createPrescriptionSimpleQuery(int prescriptionId){
        String query = "{\"query\":{\"query_string\":{\"query\":\"p_id:" + prescriptionId + "\"}}}";
        return query;
    }
}
