package com.taofang.webapi.constant;

import com.google.common.base.Strings;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-23
 */
public enum TaofangModule {
    Unknow(-1, "unknow", "未知"),
    WDGS(0, "WDGS", "我的故事"),
    JKZX(1, "JKZX", "健康资讯"),
    ZRLF(2, "ZRLF", "自然疗法"),
    ZWCS(3, "ZWCS", "自我测试"),
    JKZS(4, "JKZS", "健康之声"),
    PTWZ(5, "PTWZ", "普通文章"),
    YDFA(6, "YDFA", "运动方案"),
    JJYS(7, "JJYS", "季节养生"),
    LIANGFANG(99, "liangfang", "良方");


    public int moduleId;

    public String moduleName;

    public String moduleDesc;

    TaofangModule(int moduleId, String moduleName, String moduleDesc) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.moduleDesc = moduleDesc;
    }

    public static TaofangModule getEnumById(int moduleId){
        for(TaofangModule taofangModule : TaofangModule.values()){
            if(taofangModule.moduleId == moduleId){
                return taofangModule;
            }
        }

        return TaofangModule.Unknow;
    }

    public static TaofangModule getEnumByName(String moduleName){
        if(!Strings.isNullOrEmpty(moduleName)){
            for(TaofangModule taofangModule : TaofangModule.values()){
                if(taofangModule.moduleName.equals(moduleName)){
                    return taofangModule;
                }
            }
        }
        return TaofangModule.Unknow;
    }
}
