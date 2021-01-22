package com.cyj.piano_backend.ctrl;

import com.cyj.piano_backend.bean.vo.PianoStudentVO;
import com.cyj.piano_backend.bean.vo.JsonResult;
import com.cyj.piano_backend.service.PianoStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changyingjie
 */
@Api(tags = "宝贝相关信息")
@RestController
@Slf4j
public class PianoStudentCtrl {

    @Autowired
    private PianoStudentService pianoStudentService;

    @ApiOperation(value = "学生列表")
    @PostMapping(value = "/getBabyList")
    public JsonResult<List<PianoStudentVO>> getStudentList(@RequestParam String userId) {
        log.info("查看学生列表");
        List<PianoStudentVO> list;
        try {
            list = pianoStudentService.getStudentList(userId);
            log.info("学生列表查询成功");
            return JsonResult.cx_success(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("列表查询失败，e={}", e.getMessage());
            return new JsonResult<>(500, e.getMessage());
        }

    }
}
