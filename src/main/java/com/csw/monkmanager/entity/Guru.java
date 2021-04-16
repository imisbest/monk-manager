package com.csw.monkmanager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.io.Serializable;

@Accessors(chain = true)
@NoArgsConstructor
@Data
public class Guru implements Serializable {
    @Id
    private String id;//    `id` varchar(255) NOT NULL,
    private String name;//  `name` varchar(255) DEFAULT NULL,
    private String photo;//  `photo` varchar(255) DEFAULT NULL,
    private String status;//  `status` varchar(255) DEFAULT NULL,
    private String nick_name;//  `nick_name` varchar(255) DEFAULT NULL,

}
