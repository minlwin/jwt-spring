package com.jdc.hello.model;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID>{

	private EntityManager em;
	
	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
	}

	@Override
	public List<T> search(String jpql, Map<String, Object> params) {
		return query(jpql, params, getDomainClass()).getResultList();
	}

	@Override
	public <D> List<D> search(String jpql, Map<String, Object> params, Class<D> dtoType) {
		return query(jpql, params, dtoType).getResultList();
	}

	@Override
	public T searchOne(String jpql, Map<String, Object> params) {
		return query(jpql, params, getDomainClass()).getSingleResult();
	}

	@Override
	public <D> D searchOne(String jpql, Map<String, Object> params, Class<D> dtoType) {
		return query(jpql, params, dtoType).getSingleResult();
	}

	@Override
	public Long count(String jpql, Map<String, Object> params) {
		return query(jpql, params, Long.class).getSingleResult();
	}
	
	private <O> TypedQuery<O> query(String jpql, Map<String, Object> params, Class<O> dtoType) {
		TypedQuery<O> query = em.createQuery(jpql, dtoType);
		
		if(null != params) {
			for(String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		
		return query;
	}

}
