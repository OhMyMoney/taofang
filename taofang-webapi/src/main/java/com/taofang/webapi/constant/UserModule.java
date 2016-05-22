package com.taofang.webapi.constant;

import com.google.common.base.Optional;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-18
 */
public enum UserModule {
    qiufang(0, "qiufang", "我的求方"),
    xianfang(1, "xianfang", "我的献方"),
    shoucang(2, "shoucang", "我的收藏"),
    pinglun(3, "pinglun", "我的评论");

    public int moduleId;

    public String moduleName;

    public String moduleDesc;

    UserModule(int moduleId, String moduleName, String moduleDesc) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.moduleDesc = moduleDesc;
    }

    public static int getModuleIdByName(String moduleName){
        int moduleId = -1;
        for(UserModule userModule : UserModule.values()){
            if(Optional.fromNullable(moduleName).or("").equals(userModule.moduleName)){
                moduleId = userModule.moduleId;
            }
        }
        return moduleId;
    }

    public static String getModuleNameById(int moduleId){
        String moduleName = "";
        for(UserModule userModule : UserModule.values()){
            if(userModule.moduleId == moduleId){
                moduleName = userModule.moduleName;
            }
        }
        return moduleName;
    }
}
