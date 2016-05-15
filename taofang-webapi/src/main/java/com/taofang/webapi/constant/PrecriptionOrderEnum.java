package com.taofang.webapi.constant;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-16
 */
public enum PrecriptionOrderEnum {
    Default(0, "", "默认排序"),
    VerifyNumber(1, "p_Score", "验证人数排序"),
    EffectiveLevel(2, "p_EffectiveDegree", "有效程度排序"),
    WorkingSpeed(3, "p_EffectiveSpeed", "见效速度排序"),
    SecurityDegree(4, "p_Security", "安全性由排序"),
    ConvenientDegree(5, "p_Convenience", "方便性由排序"),
    SaveLevel(6, "p_SaveMoney", "省钱程度排序");

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
