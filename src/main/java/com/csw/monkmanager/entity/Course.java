package com.csw.monkmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Accessors(chain = true)
@Data
public class Course implements Serializable {
    @Id
    private String id;//    `id` varchar(255) NOT NULL,
    private String title;//  `title` varchar(255) DEFAULT NULL,
    private String user_id;//  `user_id` varchar(255) DEFAULT NULL,
    private String type;//  `type` varchar(255) DEFAULT NULL,
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_date;//  `create_date` date DEFAULT NULL,

    public Course(String id, String title, String user_id, String type, Date create_date) {
        this.id = id;
        this.title = title;
        this.user_id = user_id;
        this.type = type;
        this.create_date = create_date;
    }
}
