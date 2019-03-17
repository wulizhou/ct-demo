package com.wulizhou.pets.system.common;


import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.List;

public abstract class BaseService<T> implements IBaseService<T> {

    public abstract BaseMapper<T> getMapper();

	@Override
	@Transactional
	public int save(T t) {
		return getMapper().insertSelective(t);
	}

	@Override
	@Transactional
	public int saveAll(List<T> list) {
		return getMapper().insertList(list);
	}

	@Override
	@Transactional
	public int delete(T t) {
		return getMapper().delete(t);
	}

	@Override
	@Transactional
	public int deleteById(Long id) {
		return getMapper().deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int update(T t) {
		return getMapper().updateByPrimaryKey(t);
	}

	@Override
	public T getById(Long id) {
		return getMapper().selectByPrimaryKey(id);
	}

	@Override
	public List<T> getAll() {
		return getMapper().selectAll();
	}

	@Override
	public List<T> searchByExample(Example example) {
		return Collections.emptyList();
	}

}
