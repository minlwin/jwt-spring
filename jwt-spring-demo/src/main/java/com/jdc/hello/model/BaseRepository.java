package com.jdc.hello.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>{

	List<T> search(String jpql, Map<String, Object> params);
	<D> List<D> search(String jpql, Map<String, Object> params, Class<D> dtoType);
	
	T searchOne(String jpql, Map<String, Object> params);
	<D> D searchOne(String jpql, Map<String, Object> params, Class<D> dtoType);
	
	Long count(String jpql, Map<String, Object> params);
	
}
