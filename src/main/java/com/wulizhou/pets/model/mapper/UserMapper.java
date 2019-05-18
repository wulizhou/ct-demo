package com.wulizhou.pets.model.mapper;

import com.wulizhou.pets.model.entity.PetSupplies;
import com.wulizhou.pets.model.entity.Pets;
import com.wulizhou.pets.model.entity.User;
import com.wulizhou.pets.system.common.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

	@Select("select a.*,IFNULL(b.petId,0)as is_like  from pets a LEFT JOIN like_pets b on a.petId = b.petId where userId = #{userId}  ")
	List<Pets> getLike(@Param("userId")Integer userId);

	@Select("select a.*,IFNULL(b.petId,0)as is_collected  from pets a LEFT JOIN collect_pets b on a.petId = b.petId where userId = #{userId}  ")
	List<Pets> getCollectPets(@Param("userId")Integer userId);

	@Select("select a.*,IFNULL(b.petSupplyId,0)as is_collected  from pet_supplies a LEFT JOIN collect_pet_supplies b on a.petSupplyId = b.petSupplyId where userId = #{userId}  ")
	List<PetSupplies> getCollectPetSupplies(@Param("userId")Integer userId);
}
