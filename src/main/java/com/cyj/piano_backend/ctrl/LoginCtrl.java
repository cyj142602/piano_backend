package com.cyj.piano_backend.ctrl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cyj.piano_backend.constants.Contants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author changyingjie
 */
@RestController
public class LoginCtrl {

    private Logger logger = LoggerFactory.getLogger(LoginCtrl.class);

    @GetMapping("/getLoginUser")
    public String getLoginUser(@RequestParam String code) {
        String loginUrl = Contants.BASE_URL + Contants.LOGIN_URL;
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("appid", Contants.APPID);
        paramsMap.put("secret", Contants.SECRET);
        paramsMap.put("js_code", code);
        String result = HttpUtil.get(loginUrl, paramsMap);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String session_key = (String) jsonObject.get("session_key");
        String openid = (String) jsonObject.get("openid");
        System.out.println(jsonObject);
        return "返回值";
    }
}
