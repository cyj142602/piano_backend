package com.cyj.piano_backend.bean;

import com.cyj.piano_backend.redis.RedisBaseDao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author changyingjie
 */

public class Test {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\0\\Desktop\\test图片\\11.jpg");
        File result = new File("C:\\Users\\0\\Desktop\\test\\11.jpg");
        //判断文件是否存在
        if (result.exists()) {
            result.delete();
        }
        if (!result.exists()) {
            //创建一个空的文件
            result.createNewFile();
        }
        //获得输入流,将文件读到内存
        FileInputStream in = new FileInputStream(file);
        System.out.println("in:" + in);
        FileOutputStream out = new FileOutputStream(result);
        //创建数组
        int n = 0;
        int a = 0;
        //1024字节 ,相当于每次读取1kb
        byte[] arr = new byte[1024];
        //循环读取,读到末尾会返回-1
        while ((n = in.read(arr)) != -1) {
            //将读取的内容写入到输出流当中
            out.write(arr, 0, n);
        }
        out.flush();
        out.close();
        in.close();

    }
}
