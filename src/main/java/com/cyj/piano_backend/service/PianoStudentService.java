package com.cyj.piano_backend.service;

import com.cyj.piano_backend.bean.PianoStudent;

import java.util.List;

/**
 * @author changyingjie
 */
public interface PianoStudentService {

    List<PianoStudent> getStudentList(String userId);

    void saveStudentInfo(PianoStudent pianoStudent);
}
