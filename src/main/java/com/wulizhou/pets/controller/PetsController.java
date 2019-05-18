package com.wulizhou.pets.controller;

import com.wulizhou.pets.model.entity.Article;
import com.wulizhou.pets.model.entity.PetSupplies;
import com.wulizhou.pets.model.entity.Pets;
import com.wulizhou.pets.service.facade.IPetsService;
import com.wulizhou.pets.system.common.Result;
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
@RequestMapping("/pets")
@RestController
public class PetsController {

	@Autowired
	private IPetsService petsService;

	/**
	 * 首页推荐 - 通过点赞数排序
	 * @return
	 */
	@PostMapping("/getIndexPage")
	public Result getIndexPage() {
		List<Pets> pets = petsService.getPetsByLiked();
		return Result.ok(pets);
	}

	/**
	 * 分类 - 根据宠物类型进行查询，默认排序是综合（0 是综合，1点赞，2收藏）
	 * @return
	 */
	@PostMapping("/getPetsByPetType")
	public Result getPetsByPetType(@RequestParam("petType") String petType,@RequestParam("order") Integer order ) {
		List<Pets> pets = petsService.getPetsByPetType(petType,order);
		return Result.ok(pets);
	}

	/**
	 * 宠物详情
	 * @return
	 */
	@PostMapping("/getPetsByPetId")
	public Result getPetsByPetId(@RequestParam("petId") Integer petId) {
		Pets pets = petsService.getPetsByPetId(petId);
		return Result.ok(pets);
	}

	/**
	 * 宠物详情
	 * @return
	 */
	@PostMapping("/getPetSuppliesByPetSupplyId")
	public Result getPetSuppliesByPetSupplyId(@RequestParam("petSupplyId") Integer petSupplyId) {
		PetSupplies petSupplies = petsService.getPetSuppliesByPetSupplyId(petSupplyId);
		return Result.ok(petSupplies);
	}

	/**
	 * 获取对应的文章
	 * @return
	 */
	@PostMapping("/getArtical")
	public Result getArtical(@RequestParam("petType") String petType) {
		List<Article> articals = petsService.getArtical(petType);
		return Result.ok(articals);
	}

}
