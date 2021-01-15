package com.cyj.piano_backend.ctrl;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cyj.piano_backend.bean.JsonResult;
import com.cyj.piano_backend.constants.Contants;
import com.cyj.piano_backend.util.AES;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author changyingjie
 */
@RestController
@Api(tags = "登录接口")
public class LoginCtrl {

    private Logger logger = LoggerFactory.getLogger(LoginCtrl.class);

    @GetMapping("/getThirdSession")
    @ApiOperation(value = "登录获取thirdSession", httpMethod = "GET")
    public JsonResult<String> getLoginUser(@RequestParam String code) {
        String loginUrl = Contants.BASE_URL + Contants.LOGIN_URL;
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("appid", Contants.APPID);
        paramsMap.put("secret", Contants.SECRET);
        paramsMap.put("js_code", code);
        String result = HttpUtil.get(loginUrl, paramsMap);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        //微信记录的登录态，建议加密后返回给小程序端存储，后续用加密后的密文通过服务器获取用户登录信息
        String session_key = (String) jsonObject.get("session_key");
        //本小程序中每个用户的唯一id
        String openid = (String) jsonObject.get("openid");
        System.out.println(jsonObject);
        String third_session = new MD5().digestHex16(session_key);
        //openid session_key third_session 存数据库缓存 记录登录态
        //目前没有数据库存储，先传session_key
//        return new JsonResult<>(200, "登录成功", third_session);
        return new JsonResult<>(200, "登录获取成功", session_key);
    }

    @PostMapping("/decodeUserInfo")
    @ApiOperation(value = "解密encryptedData", httpMethod = "POST")
    public JsonResult<String> decodeUserInfo(@RequestBody Map<String, String> params) throws Exception {
        byte[] iv = Base64.decode(params.get("iv"));//偏移量
        byte[] encryptedData = Base64.decode(params.get("encryptedData"));//加密个人信息
        byte[] third_session = Base64.decode(params.get("thirdSession"));//登录态
        //根据third_session从缓存中获取session_key
        //从缓存中获取session_key

        byte[] resultByte = AES.decrypt(Base64.decode(encryptedData), Base64.decode(third_session), AES.generateIV(iv));
        System.out.println(resultByte);
        return new JsonResult<>(200, "解密成功");
    }


}
