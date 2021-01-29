package com.cyj.piano_backend.service.impl;

import com.cyj.piano_backend.bean.PianoFile;
import com.cyj.piano_backend.mapper.PianoFileMapper;
import com.cyj.piano_backend.service.PianoFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author changyingjie
 */
@Service
public class PianoFileServiceImpl implements PianoFileService {

    @Autowired
    private PianoFileMapper pianoFileMapper;

    @Override
    @Transactional
    public void insert(PianoFile po) {
        pianoFileMapper.insert(po);
    }

    @Override
    public String selectById(String id) {
        return pianoFileMapper.selectById(id);
    }
}
