package com.cyj.piano_backend.bean.vo;

import lombok.*;


/**
 * @author changyingjie
 */
@Getter
@Setter
public class PianoStudentVO extends BaseVO {

    private String studentName;
    private Integer gender;//1男2女
    private String photo;
    private Integer age;
    private String pianoUserId;
    private String nickName;

    private String birthday;
    private String calendar;


}
