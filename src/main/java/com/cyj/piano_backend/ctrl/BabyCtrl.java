package com.cyj.piano_backend.ctrl;

import com.cyj.piano_backend.bean.StudentVO;
import com.cyj.piano_backend.bean.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changyingjie
 */
@Api(tags = "宝贝相关信息")
@RestController
@Slf4j
public class BabyCtrl {

    @ApiOperation(value = "宝贝列表", httpMethod = "POST")
    @RequestMapping(value = "/getBabyList")
    public JsonResult<List<StudentVO>> getBabyList(){
        log.info("查看宝贝列表");
        List<StudentVO> list = new ArrayList<>();
        StudentVO babyVO = new StudentVO();
        babyVO.setAge(5);
        babyVO.setBabyName("兜兜");
        babyVO.setGender(1);
        babyVO.setPhoto("abc");
        babyVO.setId("1");
        list.add(babyVO);
        StudentVO babyVO1 = new StudentVO();
        babyVO1.setAge(5);
        babyVO1.setBabyName("妞妞");
        babyVO1.setGender(2);
        babyVO1.setPhoto("dfg");
        babyVO1.setId("2");
        list.add(babyVO1);
        return JsonResult.cx_success(list);
    }
}
