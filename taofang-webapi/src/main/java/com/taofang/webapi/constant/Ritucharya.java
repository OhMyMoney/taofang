package com.taofang.webapi.constant;

import com.google.common.base.Optional;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-18
 */
public enum Ritucharya {
    WDGS(0, "CYG", "春养肝"),
    JKZX(1, "XYX", "夏养心"),
    ZRLF(2, "QYF", "秋养肺"),
    ZWCS(3, "DTS", "冬通肾");

    public int ritucharyaId;

    public String ritucharyaName;

    public String ritucharyaDesc;

    Ritucharya(int ritucharyaId, String ritucharyaName, String ritucharyaDesc) {
        this.ritucharyaId = ritucharyaId;
        this.ritucharyaName = ritucharyaName;
        this.ritucharyaDesc = ritucharyaDesc;
    }

    public static int getRitucharyaIdByName(String ritucharyaName){
        int ritucharyaId = -1;
        for(Ritucharya ritucharya : Ritucharya.values()){
            if(Optional.fromNullable(ritucharyaName).or("").equals(ritucharya.ritucharyaName)){
                ritucharyaId = ritucharya.ritucharyaId;
            }
        }
        return ritucharyaId;
    }

    public static String getRitucharyaNameById(int ritucharyaId){
        String ritucharyaName = "";
        for(Ritucharya ritucharya : Ritucharya.values()){
            if(ritucharyaId == ritucharya.ritucharyaId){
                ritucharyaName = ritucharya.ritucharyaName;
            }
        }
        return ritucharyaName;
    }
}
