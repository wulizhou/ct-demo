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

	@Select("select a.*,IFNULL(b.petId,0)as is_liked,IFNULL(c.petId,0)as is_collected from pets a LEFT JOIN like_pets b on (a.petId = b.petId and b.userId = #{userId}) " +
			"LEFT JOIN collect_pets c on (a.petId = c.petId and c.userId = #{userId}) where a.petId = #{petId}")
	Pets selectByPetId(@Param("petId")Integer petId, @Param("userId")Integer userId);
}
