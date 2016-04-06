package com.taofang.webapi.application;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-06
 */
@ApplicationPath("/taofang/webapi/*")
public class TaofangResourceConfig extends ResourceConfig{

    public TaofangResourceConfig(){
        super();
        packages("com.taofang.webapi.resource");
    }
}
