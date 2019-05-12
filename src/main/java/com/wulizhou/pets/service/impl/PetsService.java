package com.wulizhou.pets.service.impl;

import com.wulizhou.pets.model.entity.Article;
import com.wulizhou.pets.model.entity.Pets;
import com.wulizhou.pets.model.mapper.ArticleMapper;
import com.wulizhou.pets.model.mapper.PetsMapper;
import com.wulizhou.pets.service.facade.IPetsService;
import com.wulizhou.pets.system.common.BaseMapper;
import com.wulizhou.pets.system.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 *
 * @Create in 2019/5/5 22:42
 */
@Service
public class PetsService extends BaseService<Pets> implements IPetsService {

	@Autowired
	private PetsMapper petsMapper;

	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public BaseMapper<Pets> getMapper() {
		return null;
	}

	/**
	 * 通过点赞数排序
	 * @return
	 */
	@Override
	public List<Pets> getPetsByLiked() {
		Example example = new Example(Pets.class);
		example.setOrderByClause("liked DESC");
		return petsMapper.selectByExample(example);
	}

	/**
	 * 通过收藏数排序
	 * @return
	 */
	@Override
	public List<Pets> getPetsByCollected() {
		Example example = new Example(Pets.class);
		example.setOrderByClause("collected DESC");
		return petsMapper.selectByExample(example);
	}

	/**
	 * 通过点赞和收藏数排序
	 * @return
	 */
	@Override
	public List<Pets> getPetsByLikedAndCollected() {
		Example example = new Example(Pets.class);
		example.setOrderByClause("liked DESC,collected DESC");
		return petsMapper.selectByExample(example);
	}

	@Override
	public List<Pets> getPetsByPetType(String petType, Integer order) {
		Example example = new Example(Pets.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("petType",petType);
		if (order == 0) {
			example.setOrderByClause("liked DESC,collected DESC");
		}else if (order == 1) {
			example.setOrderByClause("liked DESC");
		}else if (order == 2) {
			example.setOrderByClause("collected DESC");
		}
		return petsMapper.selectByExample(example);
	}

	@Override
	public List<Article> getArtical(String petType) {
		Example example = new Example(Article.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("petType",petType);
		return articleMapper.selectByExample(example);
	}
}
