package com.wulizhou.pets.model.mapper;

import com.wulizhou.pets.model.entity.Pets;
import com.wulizhou.pets.system.common.BaseMapper;

/**
 *
 * @Create in 2019/5/5 22:45
 */
public interface PetsMapper extends BaseMapper<Pets> {

	Pets getPetsByLike();
}
