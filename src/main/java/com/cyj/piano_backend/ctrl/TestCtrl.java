package com.cyj.piano_backend.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目
 * <p>
 * com.cyj.piano_backend.ctrl
 * <p>
 * File: TestCtrl.java 2020/12/29 17:09
 * </p>
 * <p>
 * Title: [用例名称]_[说明]</p>
 * </p>
 * <p>
 * Description: [描述该类概要功能介绍]</p>
 * </p>
 * <p>
 * 模块: 生产运营-域名
 * </p>
 *
 * @author changyingjie
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 */
@RestController
@RequestMapping("/test")
public class TestCtrl {

    @RequestMapping("/test")
    public String getTestInfo(){
        return "这是测试方法！";
    }
}
