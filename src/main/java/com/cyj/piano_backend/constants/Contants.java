package com.cyj.piano_backend.constants;

/**
 * @author changyingjie
 */
public class Contants {

    public static final String APPID = "wxcf5c5717d276a8d8";
    public static final String SECRET = "dc665fc82307079e428c5e8be8e8a5f3";

    public static final String BASE_URL = "https://api.weixin.qq.com";

    public static final String ACCESS_TOKEN_URL = "/cgi-bin/token?grant_type=client_credential&appid=wxcf5c5717d276a8d8&secret=dc665fc82307079e428c5e8be8e8a5f3";

    public static final String LOGIN_URL = "/sns/jscode2session?grant_type=authorization_code";

    public static final Integer FILE_TYPE_VIDEO = 1;
    public static final Integer FILE_TYPE_IMAGE = 2;
}
