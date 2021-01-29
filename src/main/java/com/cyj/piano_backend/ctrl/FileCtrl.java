package com.cyj.piano_backend.ctrl;

import cn.hutool.core.util.IdUtil;
import com.cyj.piano_backend.bean.JsonResult;
import com.cyj.piano_backend.bean.PianoFile;
import com.cyj.piano_backend.constants.Contants;
import com.cyj.piano_backend.service.PianoFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;

/**
 * @author changyingjie
 */
@Api(tags = "文件相关")
@RestController
public class FileCtrl {

    @Value("${cyj.videoPath}")
    private String videoPath;

    @Value("${cyj.imagePath}")
    private String imagePath;

    private Logger logger = LoggerFactory.getLogger(FileCtrl.class);

    @Autowired
    private PianoFileService pianoFileService;

    @ApiOperation(value = "视频上传")
    @PostMapping(value = "/uploadVideo")
    public JsonResult<String> uploadVideo(@RequestParam("video") MultipartFile file) {
        logger.info("打卡视频开始上传...");
        if (file.isEmpty()) {
            return new JsonResult<>(401, "上传视频不可为空");
        }
        //获取文件名，微信开发者工具会处理文件名，变成12位随机英文数字+32位随机英文数字，同一份文件的后32位相同
        //真机调试，文件名就是原名称，但是同名会在文件名后自动追加（1）序号，以确定不同文件
        //所以这里直接使用文件名，不需要再分名称前后段
        String fileName = file.getOriginalFilename();
        String path = videoPath + fileName;
        File dest = new File(path);
        if (dest.exists()) {
            logger.error("打卡视频上传失败！文件已经存在");
            return new JsonResult<>(401, "文件已经存在");
        }
        try {
            file.transferTo(dest); //保存文件
            PianoFile pianoFile = new PianoFile();
            String fileId = IdUtil.fastSimpleUUID();
            pianoFile.setId(fileId);
            pianoFile.setFileName(fileName);
            pianoFile.setFileType(Contants.FILE_TYPE_VIDEO);
            pianoFileService.insert(pianoFile);
            logger.info("打卡视频上传结束！path={}", path);
            return JsonResult.bc_success(fileId);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("打卡视频上传失败！msg = {}", Arrays.toString(e.getStackTrace()));
            return new JsonResult<>(401, "上传失败");
        }
    }

    @ApiOperation(value = "图片上传")
    @PostMapping(value = "/uploadImage")
    public JsonResult<String> uploadImage(@RequestParam("image") MultipartFile file, @RequestParam String session_token) {
        logger.info("照片开始上传...");
        if (file.isEmpty()) {
            return new JsonResult<>(401, "照片不可为空");
        }
        String fileName = file.getOriginalFilename();
        String path = imagePath + fileName;
        File dest = new File(path);
        if (dest.exists()) {
            logger.error("照片上传失败！文件已经存在");
            return JsonResult.bc_success(fileName);
        }
        try {
            file.transferTo(dest); //保存文件
            PianoFile pianoFile = new PianoFile();
            String fileId = IdUtil.fastSimpleUUID();
            pianoFile.setId(fileId);
            pianoFile.setFileName(fileName);
            pianoFile.setFileType(Contants.FILE_TYPE_IMAGE);
            pianoFileService.insert(pianoFile);
            logger.info("照片上传结束！path={}", path);
            return JsonResult.bc_success(fileId);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("照片上传失败！msg = {}", Arrays.toString(e.getStackTrace()));
            return new JsonResult<>(401, "上传失败");
        }
    }

    @GetMapping("/readImg")
    public void readImg(String fileId, HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info("预览照片");
            request.setCharacterEncoding("utf-8");
            response.setContentType("image/jpeg");
            String fileName = pianoFileService.selectById(fileId);
            //图片读取路径
            FileInputStream in = new FileInputStream(imagePath + fileName);
            // 得到文件大小
            int i = in.available();
            //创建存放文件内容的数组
            byte[] data = new byte[i];
            in.read(data);
            in.close();
            //把图片写出去
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(data);
            //将缓存区的数据进行输出
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @GetMapping("/readVideo")
    public void readVideo(String fileId, HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info("预览视频");
            request.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Accept-Ranges", "bytes");
            String fileName = pianoFileService.selectById(fileId);
            FileInputStream fis = new FileInputStream(videoPath + fileName);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            byte[] buffer = bos.toByteArray();
            response.setContentLength(buffer.length);
            response.getOutputStream().write(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
