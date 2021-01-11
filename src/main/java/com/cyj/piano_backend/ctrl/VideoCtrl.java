package com.cyj.piano_backend.ctrl;

import com.cyj.piano_backend.bean.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author changyingjie
 */
@Api(tags = "打卡视频")
@RestController
public class VideoCtrl {
    private Logger logger = LoggerFactory.getLogger(VideoCtrl.class);

    @ApiOperation(value = "视频上传", httpMethod = "POST")
    @RequestMapping(value = "/uploadFile", produces = "application/json;charset=UTF-8")
    public JsonResult<String> uploadFile(@RequestParam("fileName") MultipartFile file) {
        System.out.print("上传文件===" + "\n");
        //判断文件是否为空
        if (file.isEmpty()) {
            return new JsonResult<>(401, "上传文件不可为空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
//        System.out.print("上传的文件名为: "+fileName+"\n");
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: " + fileName + "\n");
        //加个时间戳，尽量避免文件名称重复
        String path = "D:/fileUpload/" + fileName;
        //String path = "E:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        //文件绝对路径
        System.out.print("保存文件绝对路径" + path + "\n");
        //创建文件路径
        File dest = new File(path);
        //判断文件是否已经存在
        if (dest.exists()) {
            return new JsonResult<>(401, "文件已经存在");
        }
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        String url;
        try {
            //上传文件
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径" + path + "\n");
            //url="http://你自己的域名/项目名/images/"+fileName;//正式项目
            url = "http://localhost:8080/images/" + fileName;//本地运行项目
//            int jieguo= shiPinService.insertUrl(fileName,path, url);
//            System.out.print("插入结果"+jieguo+"\n");
            System.out.print("保存的完整url====" + url + "\n");
        } catch (IOException e) {
            return new JsonResult<>(401, "上传失败");
        }
        return new JsonResult<>(200, "上传成功,文件url==" + url);
    }

}
