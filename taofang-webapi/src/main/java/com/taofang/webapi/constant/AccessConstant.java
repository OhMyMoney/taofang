package com.taofang.webapi.constant;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-06-05
 */
public class AccessConstant {
    public static final String WX_APP_ID = "wx0f263786638d7cac";

    public static final String WX_SECRET = "015f512e92f84b4998574505149c7c53";

    public static final String WX_GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" +
            WX_APP_ID + "&secret=" + WX_SECRET;

    public static final String WX_GET_JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    public static void main(String[] args){
        System.out.println(WX_GET_ACCESS_TOKEN_URL);
        System.out.println(WX_GET_JSAPI_TICKET_URL.replace("ACCESS_TOKEN", "F5D1sRInXdjO4-rko3-83tQemJdPThpX0NX6O5154KIp5ewx-WV3D7mKQalTXMUmxSvscyBbFtX6Nsi3HBz3DCHcdVXHxTDOBDlcrDFD8ES5cLMYaXuEytv0GoZ3huCbFKXiACAWBO"));
    }
}
