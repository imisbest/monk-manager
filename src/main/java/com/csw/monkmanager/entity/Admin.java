package com.csw.monkmanager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Accessors(chain = true)
@NoArgsConstructor
@Data
public class Admin implements Serializable {
    @Id
    private String id;
    private String username;
    private String password;
    @Transient
    private List<Role> roles;

    public Admin(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
