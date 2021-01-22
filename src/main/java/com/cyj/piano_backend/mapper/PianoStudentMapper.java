package com.cyj.piano_backend.mapper;

import com.cyj.piano_backend.bean.vo.PianoStudentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author changyingjie
 */
@Mapper
public interface PianoStudentMapper {

    List<PianoStudentVO> getStudentList(String userId);
}
