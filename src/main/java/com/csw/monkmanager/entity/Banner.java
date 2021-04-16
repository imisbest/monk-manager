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
public class Banner implements Serializable {
    @Id
    private String id; //* `id` varchar(255) NOT NULL,
    private String title;//  `title` varchar(255) DEFAULT NULL,
    private String url;//  `url` varchar(255) DEFAULT NULL,
    private String href;//  `href` varchar(255) DEFAULT NULL,
    //@Column(name = "create_date")
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", timezone = "GMT+8")
    //@JSONField(format = "yyyy-MM-dd")
    private Date create_date;//  `create_date` date DEFAULT NULL,
    @Column(name = "`desc`")
    private String desc;//            `desc` varchar(255) DEFAULT NULL,
    private String status;//  `status` varchar(255) DEFAULT NULL,*/

    public Banner(String id, String title, String url, String href, Date create_date, String desc, String status) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.href = href;
        this.create_date = create_date;
        this.desc = desc;
        this.status = status;
    }
}
