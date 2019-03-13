package com.wulizhou.pets.model.dao;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user")
public class User {

    @Id
    private Long id;

}
