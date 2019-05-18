package com.wulizhou.pets.model.mapper;

import com.wulizhou.pets.model.entity.Pets;
import com.wulizhou.pets.system.common.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @Create in 2019/5/5 22:45
 */
public interface PetsMapper extends BaseMapper<Pets> {

	@Select("select a.*,IFNULL(b.petId,0)as is_liked,IFNULL(c.petId,0)as is_collected from pets a LEFT JOIN like_pets b on a.petId = b.petId  " +
			"LEFT JOIN collect_pets c on a.petId = c.petId where b.userId = #{userId} and a.petId = #{petId}")
	Pets selectByPetId(@Param("petId")Integer petId, @Param("userId")Integer userId);
}
