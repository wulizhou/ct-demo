package com.wulizhou.pets.system.common;


import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface IBaseService<T> {

	int save(T t);

	int delete(T t);

	int deleteById(Long id);

	int update(T t);

	T getById(Long id);

	List<T> getAll();

	int saveAll(List<T> list);

	List<T> searchByExample(Example example);

}
