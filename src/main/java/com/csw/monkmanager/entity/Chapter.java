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
public class Chapter implements Serializable {
    @Id
    private String id;//    `id` varchar(255) NOT NULL,
    private String title;//  `title` varchar(255) DEFAULT NULL,
    private String url;//  `url` varchar(255) DEFAULT NULL,
    @Column(name = "`size`")
    private String size;//  `size` double DEFAULT NULL,
    private String time;//            `time` varchar(255) DEFAULT NULL,
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", timezone = "GMT+8")
    //@JSONField(format = "yyyy-MM-dd")
    private Date create_time;//  `create_time` date DEFAULT NULL,
    private String album_id;//            `album_id` varchar(255) DEFAULT NULL,

    public Chapter(String id, String title, String url, String size, String time, Date create_time, String album_id) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.size = size;
        this.time = time;
        this.create_time = create_time;
        this.album_id = album_id;
    }
}
