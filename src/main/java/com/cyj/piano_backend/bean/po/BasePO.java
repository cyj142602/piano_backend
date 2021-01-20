package com.cyj.piano_backend.bean.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author changyingjie
 */
@Getter
@Setter
public class BasePO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private Date createTime;
    private Date updateTime;
}
