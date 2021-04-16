package com.csw.monkmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Accessors(chain = true)
@NoArgsConstructor
@Data
public class Album implements Serializable {
    @Id
    private String id;//    `id` varchar(255) NOT NULL,
    private String title;//  `title` varchar(255) DEFAULT NULL,
    private String score;//  `score` varchar(255) DEFAULT NULL,
    private String author;//  `author` varchar(255) DEFAULT NULL,
    private String broadcast;//  `broadcast` varchar(255) DEFAULT NULL,
    private String count;//  `count` int(11) DEFAULT NULL,
    @Column(name = "`desc`")
    private String desc;//  `desc` varchar(255) DEFAULT NULL,
    private String status;//  `status` varchar(255) DEFAULT NULL,
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", timezone = "GMT+8")
    //@JSONField(format = "yyyy-MM-dd")
    private Date create_date;//  `create_date` date DEFAULT NULL,
    private String cover;//            `cover` varchar(255) DEFAULT NULL,

}
