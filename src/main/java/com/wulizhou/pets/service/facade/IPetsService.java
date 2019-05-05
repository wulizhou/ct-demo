package com.wulizhou.pets.service.facade;

import com.wulizhou.pets.model.entity.Pets;
import com.wulizhou.pets.system.common.IBaseService;

/**
 * @author D
 * @Create in 2019/5/5 22:26
 */
public interface IPetsService extends IBaseService<Pets> {

	/**
	 * 通过点赞数排序
	 * @return
	 */
	Pets getPetsByLike();
}
