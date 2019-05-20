package com.wulizhou.pets.controller;

import com.wulizhou.pets.model.entity.Article;
import com.wulizhou.pets.model.entity.PetSupplies;
import com.wulizhou.pets.model.entity.Pets;
import com.wulizhou.pets.service.facade.IPetsService;
import com.wulizhou.pets.system.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @Create in 2019/5/5 22:24
 */
@Api(tags = "宠物controller")
@RequestMapping("/pets")
@RestController
public class PetsController {

	@Autowired
	private IPetsService petsService;

	/**
	 * 首页推荐 - 通过点赞数排序
	 * @return
	 */
	@ApiOperation(value="首页推荐 - 通过点赞数排序")
	@PostMapping("/getIndexPage")
	public Result getIndexPage() {
		List<Pets> pets = petsService.getPetsByLiked();
		return Result.ok(pets);
	}

	/**
	 * 分类 - 根据宠物类型进行查询，默认排序是综合（0 是综合，1点赞，2收藏）
	 * @return
	 */
	@ApiOperation(value="分类 - 根据宠物类型进行查询")
	@PostMapping("/getPetsByPetType")
	public Result getPetsByPetType(@ApiParam(name="petType",value="宠物类型:1-狗，2-猫，3-其它",required=true)@RequestParam("petType") String petType,
	                               @ApiParam(name="order",value="排序方式（0 是综合，1点赞，2收藏）",required=true)@RequestParam("order") Integer order ) {
		List<Pets> pets = petsService.getPetsByPetType(petType,order);
		return Result.ok(pets);
	}

	/**
	 * 宠物详情
	 * @return
	 */
	@ApiOperation(value="获取宠物详情")
	@PostMapping("/getPetsByPetId")
	public Result getPetsByPetId(@ApiParam(name="petId",value="宠物ID",required=true)@RequestParam("petId") Integer petId) {
		Pets pets = petsService.getPetsByPetId(petId);
		return Result.ok(pets);
	}

	/**
	 * 宠物用品详情
	 * @return
	 */
	@ApiOperation(value="获取宠物用品详情")
	@PostMapping("/getPetSuppliesByPetSupplyId")
	public Result getPetSuppliesByPetSupplyId(@ApiParam(name="petSupplyId",value="宠物用品ID",required=true)@RequestParam("petSupplyId") Integer petSupplyId) {
		PetSupplies petSupplies = petsService.getPetSuppliesByPetSupplyId(petSupplyId);
		return Result.ok(petSupplies);
	}

	/**
	 * 获取对应的文章
	 * @return
	 */
	@ApiOperation(value="获取对应宠物的文章")
	@PostMapping("/getArtical")
	public Result getArtical(@ApiParam(name="petType",value="宠物类型:1-狗，2-猫，3-其它",required=true)@RequestParam("petType") String petType) {
		List<Article> articals = petsService.getArtical(petType);
		return Result.ok(articals);
	}

}
