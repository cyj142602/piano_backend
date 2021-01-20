package com.cyj.piano_backend.bean.vo;

import lombok.*;


/**
 * @author changyingjie
 */
@Getter
@Setter
public class PianoStudentVO extends BaseVO {

    private String babyName;
    private Integer gender;//1男2女
    private String photo;
    private Integer age;

}
