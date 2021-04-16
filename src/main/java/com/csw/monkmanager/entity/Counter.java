package com.csw.monkmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Accessors(chain = true)
@NoArgsConstructor
@Data
public class Counter implements Serializable {
    @Id
    private String id;       //                `id` varchar(255) NOT NULL,
    private String title;     //              `title` varchar(255) DEFAULT NULL,
    private String count;       //              `count` varchar(255) DEFAULT NULL,
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;      //              `create_date` date DEFAULT NULL,
    private String user_id;       //                        `user_id` varchar(255) DEFAULT NULL,
    private String course_id;       //              `course_id` varchar(255) DEFAULT NULL,

    public Counter(String id, String title, String count, Date date, String user_id, String course_id) {
        this.id = id;
        this.title = title;
        this.count = count;
        this.date = date;
        this.user_id = user_id;
        this.course_id = course_id;
    }
}
