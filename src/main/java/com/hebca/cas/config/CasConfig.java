package com.hebca.cas.config;

/**
 * Cas 的一些配置项
 */
public class CasConfig {

    /**
     * 当前应用程序的baseUrl（注意最后面的斜线）
     */
    public static String SERVER_NAME = "192.168.1.102:8888";


    /**
     * App1 登出成功url
     */
    public static String APP_LOGOUT_PATH = SERVER_NAME + "user/logout/success";


    /**
     * CAS服务器地址
     */
    public static String CAS_SERVER_PATH = "http://10.1.2.45:8080/cas";

    /**
     * CAS登陆服务器地址
     */
    public static String CAS_SERVER_LOGIN_PATH = "http://10.1.2.45:8080/cas/login";

    /**
     * CAS登出服务器地址
     */
    public static String CAS_SERVER_LOGOUT_PATH = "http://10.1.2.45:8080/cas/logout";


}
