package com.wulizhou.pets.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Create in 2019/5/15 0:16
 */
@Table(name = "users_pets")
@Data
@Accessors(chain = true)
public class UsersPetSupplies {

	@Column(name = "userId")
	private Integer userId;

	@Column(name = "petSupplyId")
	private Integer petSupplyId;

	@Column(name = "createTime")
	private Date createTime;

	@Column(name = "updateTime")
	private Date updateTime;

}
