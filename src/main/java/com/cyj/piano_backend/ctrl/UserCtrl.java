package com.cyj.piano_backend.ctrl;

import com.cyj.piano_backend.bean.JsonResult;
import com.cyj.piano_backend.service.PianoUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changyingjie
 */
@RestController
@Api(tags = "用户接口")
@Slf4j
public class UserCtrl {

    @Autowired
    private PianoUserService pianoUserService;

    @PostMapping("/getAuth")
    @ApiOperation(value = "获取用户权限")
    public JsonResult<Integer> getAuth(@RequestParam String userId) {
        try {
            log.info("查看用户权限");
            return JsonResult.bc_success(pianoUserService.selectByUserId(userId));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("登陆失败，e={}", e.getMessage());
            return new JsonResult<>(500, e.getMessage());
        }
    }
}
