package com.cyj.piano_backend.service.impl;

import com.cyj.piano_backend.bean.vo.PianoStudentVO;
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
    public List<PianoStudentVO> getStudentList(String userId) {
        return pianoStudentMapper.getStudentList(userId);
    }
}
