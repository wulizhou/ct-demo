package com.wulizhou.pets.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Create in 2019/5/15 0:16
 */
@Table(name = "collect_pet_supplies")
@Data
@Accessors(chain = true)
public class CollectPetSupplies {

	@Column(name = "userId")
	private Integer userId;

	@Column(name = "petSupplyId")
	private Integer petSupplyId;

	@Column(name = "createTime")
	private Date createTime;

	@Column(name = "updateTime")
	private Date updateTime;

}
