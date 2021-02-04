package com.cyj.piano_backend.service;

import com.cyj.piano_backend.bean.PianoFile;

/**
 * @author changyingjie
 */
public interface PianoFileService {

    //新增文件
    void insert(PianoFile po);

    //查询文件名
    String selectById(String openId);
}
