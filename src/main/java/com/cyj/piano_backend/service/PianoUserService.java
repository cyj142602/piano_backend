package com.cyj.piano_backend.service;

import com.cyj.piano_backend.bean.po.PianoUserPO;

/**
 * @author changyingjie
 */
public interface PianoUserService {

    //新增用户
    void insert(PianoUserPO po);
}
