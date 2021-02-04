package com.cyj.piano_backend.ctrl;

import com.cyj.piano_backend.bean.PianoStudent;
import com.cyj.piano_backend.bean.JsonResult;
import com.cyj.piano_backend.service.PianoStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author changyingjie
 */
@Api(tags = "宝贝相关信息")
@RestController
@Slf4j
@RequestMapping("/student")
public class PianoStudentCtrl {

    @Autowired
    private PianoStudentService pianoStudentService;

    @ApiOperation(value = "学生列表")
    @PostMapping(value = "/getStudentList")
    public JsonResult<List<PianoStudent>> getStudentList(@RequestParam String userId) {
        log.info("查看学生列表");
        List<PianoStudent> list;
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

    @ApiOperation(value = "添加学生")
    @PostMapping(value = "/saveStudentInfo")
    public JsonResult<String> saveStudentInfo(@RequestBody PianoStudent pianoStudent) {
        log.info("添加学生");
        try {
            pianoStudentService.saveStudentInfo(pianoStudent);
            log.info("添加学生成功");
            return JsonResult.bc_success("");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加学生信息失败，e={}", e.getMessage());
            return new JsonResult<>(500, e.getMessage());
        }
    }
}
