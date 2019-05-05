package com.wulizhou.pets.service.impl;

import com.wulizhou.pets.model.entity.Pets;
import com.wulizhou.pets.model.mapper.PetsMapper;
import com.wulizhou.pets.service.facade.IPetsService;
import com.wulizhou.pets.system.common.BaseMapper;
import com.wulizhou.pets.system.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author D
 * @Create in 2019/5/5 22:42
 */
@Service
public class PetsService extends BaseService<Pets> implements IPetsService {

	@Autowired
	private PetsMapper petsMapper;

	@Override
	public BaseMapper<Pets> getMapper() {
		return null;
	}

	/**
	 * 通过点赞数排序
	 * @return
	 */
	@Override
	public Pets getPetsByLike() {
		return petsMapper.getPetsByLike();
	}
}
