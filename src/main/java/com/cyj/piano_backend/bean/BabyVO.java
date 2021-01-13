package com.cyj.piano_backend.bean;

import lombok.*;


/**
 * @author changyingjie
 */
@Getter
@Setter
public class BabyVO extends BaseVO{

    private String id;
    private String babyName;
    private Integer gender;
    private String photo;
    private Integer age;

}
