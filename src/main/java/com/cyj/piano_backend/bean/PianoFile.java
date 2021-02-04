package com.cyj.piano_backend.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author changyingjie
 */
@Getter
@Setter
public class PianoFile extends BaseObject {

    private String fileName;
    private Integer fileType;
    private String studentId;
}
