package com.cyj.piano_backend.ctrl;

import com.cyj.piano_backend.bean.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author changyingjie
 */
@Api(tags = "文件相关")
@RestController
public class FileCtrl {
    private Logger logger = LoggerFactory.getLogger(FileCtrl.class);

    @ApiOperation(value = "视频上传")
    @PostMapping(value = "/uploadVideo")
    public JsonResult<String> uploadVideo(@RequestParam("video") MultipartFile file) {
        logger.info("打卡视频开始上传...");
        if (file.isEmpty()) {
            return new JsonResult<>(401, "上传视频不可为空");
        }
        //获取文件名，微信小程序组件会处理文件名，变成12位随机英文数字+32位随机英文数字，同一份文件的后32位相同
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String headFileName = fileName.substring(0, 12);
        String tailFileName = fileName.substring(12);
        String path = "D:/fileUpload/video/" + tailFileName;
        File dest = new File(path);
        if (dest.exists()) {
            logger.error("打卡视频上传失败！文件已经存在");
            return new JsonResult<>(401, "文件已经存在");
        }
        String url;
        try {
            file.transferTo(dest); //保存文件
            //url="http://你自己的域名/项目名/images/"+fileName;//正式项目
            url = "http://localhost:8080/images/" + fileName;//本地运行项目
//            int jieguo= shiPinService.insertUrl(fileName,path, url);
            logger.info("打卡视频上传结束！path={}", path);
            return JsonResult.bc_success("上传成功,文件url==" + url);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("打卡视频上传失败！msg = {}", Arrays.toString(e.getStackTrace()));
            return new JsonResult<>(401, "上传失败");
        }
    }

    @ApiOperation(value = "图片上传")
    @PostMapping(value = "/uploadImage")
    public JsonResult<String> uploadImage(@RequestParam("image") MultipartFile file) {
        logger.info("照片开始上传...");
        if (file.isEmpty()) {
            return new JsonResult<>(401, "照片不可为空");
        }
        //获取文件名，微信小程序组件会处理文件名，变成12位随机英文数字+32位随机英文数字，同一份文件的后32位相同
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String headFileName = fileName.substring(0, 12);
        String tailFileName = fileName.substring(12);
        String path = "D:/fileUpload/image/" + tailFileName;
        File dest = new File(path);
        if (dest.exists()) {
            logger.error("照片上传失败！文件已经存在");
            return new JsonResult<>(401, "文件已经存在");
        }
        String url;
        try {
            file.transferTo(dest); //保存文件
            //url="http://你自己的域名/项目名/images/"+fileName;//正式项目
            url = "http://localhost:8080/images/" + fileName;//本地运行项目
//            int jieguo= shiPinService.insertUrl(fileName,path, url);
            logger.info("照片上传结束！path={}", path);
            return JsonResult.bc_success("上传成功,照片地址==" + url);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("照片上传失败！msg = {}", Arrays.toString(e.getStackTrace()));
            return new JsonResult<>(401, "上传失败");
        }
    }
}