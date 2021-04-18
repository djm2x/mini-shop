package com.example.minishop.Services;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jinq.jpa.JPAJinqStream;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractJpaDao<T extends Serializable> {

	private Class<T> clazz;

	@PersistenceContext
	EntityManager entityManager;

	private String idFieldName = "id";

	/**
	 * begin jink
	 */

	// @Autowired
	private JinqJPAStreamProvider jinqDataProvider;

	// protected abstract Class<T> entityType();

	// public JPAJinqStream<T> stream() {
	// 	return streamOf(entityType());
	// }

	// protected <U> JPAJinqStream<U> streamOf(Class<U> clazz) {
	// 	return jinqDataProvider.streamAll(entityManager, clazz);
	// }

	// @SuppressWarnings("unchecked")
	public JPAJinqStream<T> jink() {
		// Class<T> genericType = (Class<T>)
		// GenericTypeResolver.resolveTypeArgument(getClass(), (Class<T>)
		// entityManager.getClass());
		// Initialize Jinq
		jinqDataProvider = new JinqJPAStreamProvider(entityManager.getMetamodel());

		// Issue a query
		return jinqDataProvider.streamAll(entityManager, this.clazz);
		// return jinqDataProvider.streamAll(entityManager, (Class<T>)
		// entityManager.getClass());
	}

	public JPAJinqStream<T> jink(Class<T> args) {
		// Initialize Jinq
		jinqDataProvider = new JinqJPAStreamProvider(entityManager.getMetamodel());

		// Issue a query
		return jinqDataProvider.streamAll(entityManager, args);
	}

	// public JPAJinqStream<T> where(Class<T> args, JinqStream.Where<T, Exception> where) {
	// 	return jink(args).where(where);
	// }

	/**
	 * end jink
	 */

	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
		this.idFieldName = this.getIdFieldName();
	}

	public T findOne(long id) {
		return entityManager.find(clazz, id);
	}

	public List<T> findAll() {
		return entityManager.createQuery("from " + this.clazz.getName()).getResultList();
	}

	// public List<T> findAll() {
	// return entityManager.createQuery("from " + this.clazz.getName() + " ORDER BY
	// " + this.idFieldName).getResultList();
	// }

	/**
	 * Méthode permettant d'envoyer une liste suite à une requette Attention ! Bien
	 * respecter l'ordre des paramètres selon leur apparition dans la requete ex:
	 * requette : "from utilisateur u where u.nom := ?0 and u.prenom := ?1 les
	 * paramètres : param[0]="nom" param[1]="prenom"
	 * 
	 * @param query
	 * @param params
	 * @return
	 */
	public List<T> findBy(String query, Object... params) {
		Query q = entityManager.createQuery(query);
		for (int i = 0; i < params.length; i++) {
			q.setParameter(i, params[i]);
		}
		return q.getResultList();
	}

	public T findOneBy(String query, Object... params) {
		Query q = entityManager.createQuery(query);
		for (int i = 0; i < params.length; i++) {
			q.setParameter(i, params[i]);
		}
		List<T> result = q.getResultList();
		return (result == null || result.isEmpty()) ? null : result.get(0);
	}

	public List<T> findAllActive() {
		return entityManager.createQuery("from " + this.clazz.getName() + " where active = TRUE", this.clazz)
				.getResultList();
	}

	public T findByEmail(String email) {
		Query q = entityManager.createQuery("from " + this.clazz.getName() + " where email = :email", this.clazz)
				.setParameter("email", email);
		@SuppressWarnings("unchecked")
		List<T> l = q.getResultList();
		if (l.isEmpty()) {
			return null;
		}
		return l.get(0);
	}

	public T save(T entity) {
		entityManager.persist(entity);
		entityManager.flush();
		return entity;
	}

	public T update(T entity) {
		return entityManager.merge(entity);
	}

	public void delete(T entity) {
		if (entityManager.contains(entity)) {
			entityManager.remove(entity);
		} else {
			entityManager.remove(entityManager.merge(entity));
		}
	}

	public void deleteById(long entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}

	public void deleteQuery(String query) {
		entityManager.createQuery(query).executeUpdate();
	}

	public void updateQuery(String query) {
		entityManager.createQuery(query).executeUpdate();
	}

	private String getIdFieldName() {
		String nameIdField = "";
		for (Field field : clazz.getDeclaredFields()) {
			String name = field.getName();
			Annotation[] annotations = field.getDeclaredAnnotations();
			if (annotations.length > 0 && annotations[0].toString().equals("@javax.persistence.Id()")) {
				return name;
			}
		}
		return nameIdField;
	}

}