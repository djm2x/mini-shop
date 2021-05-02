package com.example.minishop.Repositories.old;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

@Repository
@Transactional
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GenericJpaDao<T extends Serializable> extends AbstractJpaDao<T> {

	@Override
	public List<T> findAll() {
		return super.findAll();
	}

	@Override
	public T findOne(long id) {
		return super.findOne(id);
	}

	@Override
	public T save(T entity) {
		return super.save(entity);
	}

	@Override
	public T update(T entity) {
		return super.update(entity);
	}

	@Override
	public T findByEmail(String email) {
		return super.findByEmail(email);
	}

	// @Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	public List<Object> query(String query, Object... params) {
		Query q = entityManager.createQuery(query);
		for (int i = 0; i < params.length; i++) {
			q.setParameter(i, params[i]);
		}
		return q.getResultList();
	}

}
