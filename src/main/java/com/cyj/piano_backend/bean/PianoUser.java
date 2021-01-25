package com.cyj.piano_backend.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author changyingjie
 */
@Getter
@Setter
public class PianoUser extends BaseObject {

    private String openId;
    private String nickName;
    private Integer gender;
    private String phone;
    private Integer permission;//0 默认 1 申请 2学生家长
}
