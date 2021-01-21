package com.cyj.piano_backend.service.impl;

import com.cyj.piano_backend.bean.po.PianoUserPO;
import com.cyj.piano_backend.mapper.PianoUserMapper;
import com.cyj.piano_backend.service.PianoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author changyingjie
 */
@Service
public class PianoUserServiceImpl implements PianoUserService {

    @Autowired
    private PianoUserMapper pianoUserMapper;

    @Override
    @Transactional
    public void insert(PianoUserPO po) {
        pianoUserMapper.insert(po);
    }

    @Override
    public String selectByOpenId(String openId) {
        return pianoUserMapper.selectByOpenId(openId);
    }
}
