package com.wulizhou.pets.service.facade;

import com.wulizhou.pets.model.entity.Article;
import com.wulizhou.pets.model.entity.Pets;
import com.wulizhou.pets.system.common.IBaseService;

import java.util.List;

/**
 *
 * @Create in 2019/5/5 22:26
 */
public interface IPetsService extends IBaseService<Pets> {

	/**
	 * 通过点赞数排序
	 * @return
	 */
	List<Pets> getPetsByLiked();

	/**
	 * 通过收藏数排序
	 * @return
	 */
	List<Pets> getPetsByCollected();

	/**
	 * 通过点赞和收藏数排序
	 * @return
	 */
	List<Pets> getPetsByLikedAndCollected();

	List<Pets> getPetsByPetType(String petType, Integer order);

	List<Article> getArtical(String petType);
}
