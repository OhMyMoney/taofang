package com.taofang.webapi.util;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-16
 */
public class PaginationUtil {

    public static int getTotalPage(int totalCount, int perPageCount){
        return totalCount % perPageCount == 0 ? totalCount / perPageCount : (totalCount / perPageCount + 1);
    }
}
