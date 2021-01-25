package com.cyj.piano_backend.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cyj.piano_backend.bean.PianoStudent;
import com.cyj.piano_backend.mapper.PianoStudentMapper;
import com.cyj.piano_backend.service.PianoStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author changyingjie
 */
@Service
public class PianoStudentServiceImpl implements PianoStudentService {

    @Autowired
    private PianoStudentMapper pianoStudentMapper;

    @Override
    public List<PianoStudent> getStudentList(String userId) {
        return pianoStudentMapper.getStudentList(userId);
    }

    @Override
    public void saveStudentInfo(PianoStudent pianoStudent) {
        pianoStudent.setId(IdUtil.fastSimpleUUID());
        pianoStudentMapper.saveStudentInfo(pianoStudent);
    }
}
