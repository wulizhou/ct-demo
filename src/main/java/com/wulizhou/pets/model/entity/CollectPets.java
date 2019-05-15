package com.wulizhou.pets.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Table(name = "collect_pets")
@Data
@Accessors(chain = true)
public class CollectPets {

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "petId")
    private Integer petId;

    @Column(name = "createTime")
    private Date createTime;

    @Column(name = "updateTime")
    private Date updateTime;

}
