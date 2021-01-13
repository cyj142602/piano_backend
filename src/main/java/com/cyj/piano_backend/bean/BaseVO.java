package com.cyj.piano_backend.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author changyingjie
 */
@Getter
@Setter
public class BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String createUserId;
    private Date createTime;
    private String updateUserId;
    private Date updateTime;


}
