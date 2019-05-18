package com.wulizhou.pets.model.mapper;

import com.wulizhou.pets.model.entity.PetSupplies;
import com.wulizhou.pets.system.common.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author D
 * @Create in 2019/5/16 0:10
 */
public interface PetSuppliesMapper extends BaseMapper<PetSupplies> {

	@Select("select a.*,IFNULL(b.petSupplyId,0)as is_collected  from pet_supplies a LEFT JOIN collect_pet_supplies b " +
			"on (a.petSupplyId = b.petSupplyId and b.userId = #{userId}) where a.petSupplyId = #{petSupplyId}")
	PetSupplies selectByPetSupplyId(@Param("petSupplyId")Integer petSupplyId, @Param("userId")Integer userId);
}
