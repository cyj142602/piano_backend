package com.cyj.piano_backend.ctrl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cyj.piano_backend.bean.PianoUser;
import com.cyj.piano_backend.bean.JsonResult;
import com.cyj.piano_backend.constants.Contants;
import com.cyj.piano_backend.redis.RedisBaseDao;
import com.cyj.piano_backend.service.PianoUserService;
import com.cyj.piano_backend.util.MiniAESUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author changyingjie
 */
@RestController
@Api(tags = "登录接口")
@Slf4j
@RequestMapping("/login")
public class LoginCtrl {

    @Autowired
    private PianoUserService pianoUserService;

    @Autowired
    private RedisBaseDao redisBaseDao;

    @PostMapping("/getThirdSession")
    @ApiOperation(value = "登录获取thirdSession")
    public JsonResult<Map<String, String>> getLoginUser(@RequestParam Map<String, String> params) {
        String loginUrl = Contants.BASE_URL + Contants.LOGIN_URL;
        Map<String, String> result = new HashMap<>();
        String code = params.get("code");
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("appid", Contants.APPID);
        paramsMap.put("secret", Contants.SECRET);
        paramsMap.put("js_code", code);
        try {
            //调用微信服务器获取openid和session_key
            String wxResult = HttpUtil.get(loginUrl, paramsMap);
            JSONObject jsonObject = JSONUtil.parseObj(wxResult);
            String sessionKey = (String) jsonObject.get("session_key");
            //sessionKey加密存储，并返给小程序端
            String thirdSession = new MD5().digestHex16(sessionKey);
            result.put("thirdSession", thirdSession);
            //本小程序中每个用户的唯一id
            String openId = (String) jsonObject.get("openid");
            String userId = pianoUserService.selectByOpenId(openId);
            //id存在，返回登录信息，不存在 新增用户
            if (StrUtil.hasEmpty(userId)) {
                String iv = params.get("iv");
                String encryptedData = params.get("encryptedData");
                String userInfo = MiniAESUtil.getUserInfo(encryptedData, sessionKey, iv);
                JSONObject json = JSONUtil.parseObj(userInfo);
                PianoUser po = new PianoUser();
                userId = IdUtil.fastSimpleUUID();
                po.setId(userId);
                po.setGender((Integer) json.get("gender"));
                po.setNickName((String) json.get("nickName"));
                po.setOpenId(openId);
                pianoUserService.insert(po);
            }
            redisBaseDao.setString(userId,thirdSession);
            result.put("userId", userId);
            log.info("登录成功");
            return new JsonResult<>(200, "登录成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("登陆失败，e={}", e.getMessage());
            return new JsonResult<>(500, e.getMessage());
        }
    }


}
