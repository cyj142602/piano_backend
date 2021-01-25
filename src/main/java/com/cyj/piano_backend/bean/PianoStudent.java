package com.cyj.piano_backend.bean;

import lombok.*;

import java.util.Date;


/**
 * @author changyingjie
 */
@Getter
@Setter
public class PianoStudent extends BaseObject {

    private String studentName;
    private Integer gender;//1男2女
    private String photo;
    private Integer age;
    private String pianoUserId;
    private String nickName;

    private Date birthday;
    private Integer calendar;


}
