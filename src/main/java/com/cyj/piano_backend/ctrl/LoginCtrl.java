package com.cyj.piano_backend.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author changyingjie
 */
@RestController
public class LoginCtrl {

    private Logger logger = LoggerFactory.getLogger(LoginCtrl.class);

    @PostMapping("/getLoginUser")
    public void getLoginUser(@RequestBody Map<String, String> params) {
        logger.info(params.get("code"));
        logger.info(params.get("appid"));
        logger.info(params.get("secret"));
        logger.info(params.get("grant_type"));
    }
}
