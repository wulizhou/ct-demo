package com.wulizhou.pets.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author D
 * @Create in 2019/5/5 16:07
 */
@Data
@Table(name = "pet_supplies")
public class PetSupplies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "petSupplyId")
	private Long petSupplyId;

	/**
	 * 用品名
	 */
	@Column(name = "title")
	private String title;

	/**
	 * 价格
	 */
	@Column(name = "price")
	private Float price;

	/**
	 * 商品总量
	 */
	@Column(name = "totalCount")
	private Integer totalCount;

	/**
	 * 商品图片
	 */
	@Column(name = "image")
	private String image;

	/**
	 * 商品状态，1-正常，2-下架，3-删除
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 点赞数
	 */
	@Column(name = "liked")
	private Integer liked;

	/**
	 * 收藏数
	 */
	@Column(name = "collected")
	private Integer collected;

	/**
	 * 创建时间
	 */
	@Column(name = "created")
	private Date created;

	/**
	 * 更新时间
	 */
	@Column(name = "updated")
	private Date updated;
}
