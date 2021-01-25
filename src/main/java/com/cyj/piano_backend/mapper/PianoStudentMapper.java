package com.cyj.piano_backend.mapper;

import com.cyj.piano_backend.bean.PianoStudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author changyingjie
 */
@Mapper
public interface PianoStudentMapper {

    List<PianoStudent> getStudentList(String userId);

    void saveStudentInfo(PianoStudent pianoStudent);
}
