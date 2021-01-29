package com.cyj.piano_backend.mapper;

import com.cyj.piano_backend.bean.PianoFile;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author changyingjie
 */
@Mapper
public interface PianoFileMapper {

    //新增文件
    void insert(PianoFile po);

    //查询文件名
    String selectById(String id);
}
