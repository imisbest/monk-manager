package com.csw.monkmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by HIAPAD on 2019/11/29.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Stu implements Serializable {
    private String id;
    private String name;
    private Integer age;
    private Date bir;
}
