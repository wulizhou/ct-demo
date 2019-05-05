package com.wulizhou.pets.controller;

import com.wulizhou.pets.model.entity.Pets;
import com.wulizhou.pets.service.facade.IPetsService;
import com.wulizhou.pets.system.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author D
 * @Create in 2019/5/5 22:24
 */
@RequestMapping("/pets")
@RestController
public class PetsController {

	@Autowired
	private IPetsService petsService;

	@GetMapping("/getIndexPage")
	public Result getIndexPage() {
		Pets pets = petsService.getPetsByLike();
		return Result.ok(pets);
	}

}
