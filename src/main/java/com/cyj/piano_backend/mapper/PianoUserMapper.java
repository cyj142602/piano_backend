package com.cyj.piano_backend.mapper;

import com.cyj.piano_backend.bean.po.PianoUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author changyingjie
 */
@Mapper
public interface PianoUserMapper {

    //新增用户
    void insert(PianoUserPO po);
}
