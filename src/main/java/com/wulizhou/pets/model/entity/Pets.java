package com.wulizhou.pets.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author D
 * @Create in 2019/5/5 15:39
 */
@Data
@Table(name = "pets")
public class Pets {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "petId")
	private Long petId;

	/**
	 * 宠物类型
	 */
	@Column(name = "petType")
	private String petType;

	/**
	 * 宠物中文名
	 */
	@Column(name = "chineseName")
	private String chineseName;

	/**
	 * 宠物英文名
	 */
	@Column(name = "englishName")
	private String englishName;

	/**
	 * 起源
	 */
	@Column(name = "original")
	private String original;

	/**
	 * 市场价格
	 */
	@Column(name = "marketValue")
	private Float marketValue;

	/**
	 * 寿命
	 */
	@Column(name = "lifetime")
	private String lifetime;

	/**
	 * 身高
	 */
	@Column(name = "height")
	private String height;

	/**
	 * 体重
	 */
	@Column(name = "weight")
	private String weight;

	/**
	 * 体形
	 */
	@Column(name = "build")
	private String build;

	/**
	 * 用途
	 */
	@Column(name = "use")
	private String use;

	/**
	 * 图片
	 */
	@Column(name = "images")
	private String images;

	/**
	 * 形态特征
	 */
	@Column(name = "morphological")
	private String morphological;

	/**
	 * 性格特点
	 */
	@Column(name = "personality")
	private String personality;

	/**
	 * 养护知识
	 */
	@Column(name = "maintenance")
	private String maintenance;

	/**
	 * 喂食要点
	 */
	@Column(name = "feed")
	private String feed;

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