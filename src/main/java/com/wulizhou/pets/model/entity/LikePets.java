package com.wulizhou.pets.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "like_pets")
@Data
@Accessors(chain = true)
public class LikePets {

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "petId")
    private Integer petId;

    @Column(name = "createTime")
    private Date createTime;

    @Column(name = "updateTime")
    private Date updateTime;

}
