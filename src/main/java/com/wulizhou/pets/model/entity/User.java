package com.wulizhou.pets.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user")
@Data
@Accessors(chain = true)
public class User {

    @Id
    private Long id;

    private String mobile;

    private String username;

    private String avatar;

    private Date createTime;

}
