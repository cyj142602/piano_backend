package com.cyj.piano_backend.mapper;

import com.cyj.piano_backend.bean.PianoUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author changyingjie
 */
@Mapper
public interface PianoUserMapper {

    //新增用户
    void insert(PianoUser po);

    //查询用户
    String selectByOpenId(String openId);

    Integer selectByUserId(String id);
}
