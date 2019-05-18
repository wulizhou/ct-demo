package com.wulizhou.pets.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @Create in 2019/5/5 16:07
 */
@Data
@Table(name = "pet_supplies")
public class PetSupplies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "petSupplyId")
	private Integer petSupplyId;

	/**
	 * 是否被收藏过 0为否，大于1为有
	 */
	@Column(name = "is_collected")
	private Integer isCollected;

	/**
	 * 是否被点赞过
	 */
	@Column(name = "is_lied")
	private Integer isLied;

	/**
	 * 用品名
	 */
	@Column(name = "imgUrl")
	private String imgUrl;

	/**
	 * 用品名
	 */
	@Column(name = "title")
	private String title;

	/**
	 * 单价
	 */
	@Column(name = "unitPrice")
	private String unitPrice;

	/**
	 * 现价
	 */
	@Column(name = "newCast")
	private String newCast;

	/**
	 * 原价
	 */
	@Column(name = "oriPrice")
	private Float oriPrice;

	/**
	 * 存货
	 */
	@Column(name = "stock")
	private Integer stock;

	/**
	 * 已售
	 */
	@Column(name = "sale")
	private Integer sale;


	/**
	 * 商品参数
	 */
	@Column(name = "params")
	private String params;

	/**
	 * 商品图片
	 */
	@Column(name = "imgNewList")
	private String imgNewList;

	/**
	 * 内容
	 */
	@Column(name = "detailContent")
	private String detailContent;

	/**
	 * 商品状态，1-正常，2-下架，3-删除
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 创建时间
	 */
	@Column(name = "createTime")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@Column(name = "updateTime")
	private Date updateTime;
}
