package com.cyj.piano_backend.service;

import com.cyj.piano_backend.bean.vo.PianoStudentVO;

import java.util.List;

/**
 * @author changyingjie
 */
public interface PianoStudentService {

    List<PianoStudentVO> getStudentList(String userId);
}
