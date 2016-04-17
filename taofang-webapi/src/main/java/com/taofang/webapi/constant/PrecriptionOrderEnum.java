package com.taofang.webapi.constant;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-16
 */
public enum PrecriptionOrderEnum {
    VerifyNumber(0, "", "验证人数排序"),
    EffectiveLevel(1, "", "有效程度排序"),
    WorkingSpeed(2, "", "见效速度排序"),
    SecurityDegree(3, "", "安全性由排序"),
    ConvenientDegree(4, "", "方便性由排序"),
    SaveLevel(5, "", "省钱程度排序");

    public int orderId;

    public String orderName;

    public String orderDesc;

    PrecriptionOrderEnum(int orderId, String orderName, String orderDesc) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderDesc = orderDesc;
    }

    public static String getOrderNameById(int orderId){
        for(PrecriptionOrderEnum precriptionOrderEnum : PrecriptionOrderEnum.values()){
            if(precriptionOrderEnum.orderId == orderId){
                return precriptionOrderEnum.orderName;
            }
        }
        return "";
    }
}
