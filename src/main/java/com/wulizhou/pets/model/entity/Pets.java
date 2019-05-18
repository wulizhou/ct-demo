package com.wulizhou.pets.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @Create in 2019/5/5 15:39
 */
@Data
@Table(name = "pets")
public class Pets {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "petId")
	private Integer petId;

	/**
	 * 是否被收藏过 0为否，大于1为有
	 */
	@Transient
	private Integer is_collected;

	/**
	 * 是否被点赞过
	 */
	@Transient
	private Integer is_liked;

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
	@Column(name = "ancestralHome")
	private String ancestralHome;

	/**
	 * 市场价格
	 */
	@Column(name = "marketValue")
	private String marketValue;

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
	@Column(name = "createTime")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@Column(name = "updateTime")
	private Date updateTime;
}
