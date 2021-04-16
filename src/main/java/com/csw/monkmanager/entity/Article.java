package com.csw.monkmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Data
@Accessors(chain = true)
@AllArgsConstructor
@Document(indexName = "cmfz", type = "article")
public class Article implements Serializable {
    @Id
    @org.springframework.data.annotation.Id
    private String id;//     `id` varchar(255) NOT NULL,
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;//  `title` varchar(255) DEFAULT NULL,
    @org.springframework.data.annotation.Transient
    private String url;//  `img` varchar(255) DEFAULT NULL,
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;//  `content` varchar(255) DEFAULT NULL,

    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_date;//  `create_date` date DEFAULT NULL,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @Column(name = "publish_date")
//    //@JSONField(format = "yyyy-MM-dd")
//    @Field(type = FieldType.Date)
    private Date publish_date;//            `publish_date` date DEFAULT NULL,
    private String status;//            `status` varchar(255) DEFAULT NULL,
    @Column(name = "guru_id")
    private String guru_id;//  `guru_id` varchar(255) DEFAULT NULL,

}
