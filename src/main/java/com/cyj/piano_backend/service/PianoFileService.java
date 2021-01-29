package com.cyj.piano_backend.service;

import com.cyj.piano_backend.bean.PianoFile;

/**
 * @author changyingjie
 */
public interface PianoFileService {

    //新增用户
    void insert(PianoFile po);

    //查询用户
    String selectById(String openId);
}
