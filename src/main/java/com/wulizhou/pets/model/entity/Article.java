package com.wulizhou.pets.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 *
 * @Create in 2019/5/12 21:29
 */
@Table(name = "article")
@Data
@Accessors(chain = true)
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "articleId")
	private Integer articleId;

	/**
	 * 宠物类型
	 */
	@Column(name = "petType")
	private String petType;

	/**
	 * 文章内容
	 */
	@Column(name = "content")
	private String content;

}
