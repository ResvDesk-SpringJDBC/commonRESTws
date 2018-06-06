package com.telappoint.commonrestws.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.telappoint.commonrestws.common.dao.IBaseDao;
import com.telappoint.commonrestws.common.dao.exception.ExceptionConstants;
import com.telappoint.commonrestws.common.dao.exception.TelAppointDBException;

@Repository
@Transactional
public abstract class BaseDao<Entity, PKey extends Serializable> implements IBaseDao<Entity, PKey> {

	private static Logger logger = Logger.getLogger(BaseDao.class);

	@PersistenceContext
	public EntityManager entityManager = null;

	public void setEntityManager(EntityManager entityManager) throws TelAppointDBException {
		this.entityManager = entityManager;
	}

	protected static final String QRY_SELECT = "select model from ";
	
	protected static final String DELETE_QRY = "DELETE FROM ";

	protected static final String QRY_MODEL = " model ";

	protected static final String QRY_MODEL_PERIOD = " model.";

	protected static final String QRY_WHERE = " where ";

	protected static final String QRY_WHERE_MODEL = " model where model.";

	protected static final String QRY_PROPERTY = "= :propertyValue";

	protected static final String ORDER_BY_MODEL = " order by model.";
	
	private String queryString = null;
	
	private String deleteQueryString = null;

	private Class<Entity> clazz;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		try {
			clazz = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			queryString = new StringBuilder(QRY_SELECT).append(clazz.getSimpleName()).append(QRY_MODEL).toString();
			deleteQueryString = new StringBuilder(DELETE_QRY).append(clazz.getSimpleName()).append(QRY_WHERE_MODEL).append("id").append(QRY_PROPERTY).toString();
		} catch (Exception e) {
		}
	}

	protected static final boolean isEmpty(String data) {
		return data == null || data.trim().length() == 0;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(Entity transientInstance) throws TelAppointDBException {
		try {
			entityManager.persist(transientInstance);
			entityManager.flush();
			logger.info("Sucessfully saved  Entity - " + clazz.getName());
		} catch (PersistenceException ex) {
			logger.error("Exception occured while saving Entity - " + clazz.getName(), ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_SAVING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(List<Entity> transientInstanceList) throws TelAppointDBException {
		try {
			for (Entity transientInstance : transientInstanceList) {
				entityManager.persist(transientInstance);
			}
			entityManager.flush();
			logger.info("Sucessfully saved List of  Entities - " + clazz.getName());
		} catch (PersistenceException ex) {
			logger.error("Exception occured while saving List of  Entities - " + clazz.getName(), ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_SAVING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
	}

	@Deprecated
	/*public Entity findById(PKey primaryKey, Class type) throws TelAppointDBException {
		Entity entity = null;
		try {
			entity = (Entity) entityManager.find(clazz, primaryKey);
			logger.info("Sucessfully finding Entity " + entity);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while finding Entity.. " + entity, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return entity;
	}*/

	public Entity findById(PKey primaryKey) throws TelAppointDBException {
		Entity entity = null;
		try {
			entity = (Entity) entityManager.find(clazz, primaryKey);
			logger.info("Sucessfully finding Entity -- " + entity);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while finding Entity.. " + entity, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return entity;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Entity update(Entity detachedInstance) throws TelAppointDBException {
		Entity entity = null;
		try {
			entity = entityManager.merge(detachedInstance);
			entityManager.flush();

			logger.info("Sucessfully updated Entity --  " + entity);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while udating Entity - " + clazz.getName(), ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_UPDATING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return entity;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(List<Entity> detachedInstanceList) throws TelAppointDBException {
		try {
			for (Entity detachedInstance : detachedInstanceList) {
				entityManager.merge(detachedInstance);
			}
			entityManager.flush();
			logger.info("Sucessfully updated Entity List");
		} catch (PersistenceException ex) {
			logger.error("Exception occured while udating List of Entities - " + clazz.getName(), ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_UPDATING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Entity persistentInstance) throws TelAppointDBException {
		try {
			entityManager.remove(persistentInstance);
			entityManager.flush();
			logger.info("Sucessfully Deleted  Entity  ---  " + persistentInstance);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while deleting Entity - " + clazz.getName(), ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_DELETING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(List<Entity> persistentInstanceList) throws TelAppointDBException {
		try {
			for (Entity persistentInstance : persistentInstanceList) {
				entityManager.remove(persistentInstance);
			}
			entityManager.flush();
			logger.info("Sucessfully Deleted  Entity List  ---  " + persistentInstanceList);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while deleting List of Entities - " + clazz.getName(), ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_DELETING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteById(PKey pKey) throws TelAppointDBException {
		try {
			Entity entity = entityManager.find(clazz, pKey);
			if (entity != null) {
				entityManager.remove(entity);
			}
			entityManager.flush();
			logger.info("Sucessfully Deleted  Entity by ID  ---  " + pKey);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while deleting Entity by ID  ---  " + pKey, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_DELETING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteMerge(Entity persistentInstance) throws TelAppointDBException {
		try {
			persistentInstance = entityManager.merge(persistentInstance);
			entityManager.remove(persistentInstance);
			entityManager.flush();
			logger.info("DeleteMerge operation performed Sucessfully for Entity  ---  " + persistentInstance);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while performing deleteMerge on Entity - " + clazz.getName(), ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_DELETING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Entity... persistentInstance) throws TelAppointDBException {
		try {
			for (Entity entity : persistentInstance) {
				entityManager.remove(entity);
			}
			entityManager.flush();
			logger.info("Sucessfully Deleted  Entity  Var Args");
		} catch (PersistenceException ex) {
			logger.error("Exception occured while performing delete  on Entity Var Args", ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_DELETING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteAll() throws TelAppointDBException {
		try {
			List<Entity> list = findAll();
			for (Entity entity : list) {
				entityManager.remove(entity);
			}
			entityManager.flush();
			logger.info("Sucessfully Deleted  All the records of an Entity");
		} catch (PersistenceException ex) {
			logger.error("Exception occured while deleting All the records of an Entity - " + clazz.getName(), ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_DELETING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Entity> findByQuery(final String queryString) throws TelAppointDBException {
		List<Entity> resultList = null;
		try {
			resultList = entityManager.createQuery(queryString).getResultList();
			logger.info("Sucessfully Selected records for the  queryString " + queryString);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while Selecting records for the  queryString " + queryString, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<Entity> findAll() throws TelAppointDBException {
		List<Entity> resultList = null;
		try {
			resultList = entityManager.createQuery(queryString).getResultList();
			logger.info("Sucessfully Selected All the records for the  queryString " + queryString);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while Selecting All the records for the Entity " + clazz.getName(), ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return resultList;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateQuery(final String queryString) throws TelAppointDBException {
		int count = 0;
		try {
			Query query = entityManager.createQuery(queryString);
			count = query.executeUpdate();
			entityManager.flush();
			logger.info("Sucessfully Updated records using queryString " + queryString);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while udating records using queryString " + queryString, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_UPDATING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return count;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateNativeQuery(final String queryString) throws TelAppointDBException {
		int count = 0;
		try {
			Query query = entityManager.createNativeQuery(queryString);
			count = query.executeUpdate();
			entityManager.flush();
			logger.info("Sucessfully Updated records using Native queryString " + queryString);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while udating records using Native queryString " + queryString, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_UPDATING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return count;
	}

	public Object findByAggregate(final String queryString) throws TelAppointDBException {
		Object result = null;
		try {
			Query query = entityManager.createQuery(queryString);
			result = query.getSingleResult();
			logger.info("Sucessfully Selected records using queryString " + queryString);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while Selecting records using queryString " + queryString, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Entity> findByProperty(String propertyName, final Object value) throws TelAppointDBException {
		List<Entity> entityList = null;
		try {
			final String queryString = new StringBuilder(QRY_SELECT).append(clazz.getSimpleName()).append(QRY_WHERE_MODEL).append(propertyName).append(QRY_PROPERTY).toString();
			Query query = entityManager.createQuery(queryString);
			query.setParameter(PROPERTYVALUE, value);
			entityList = query.getResultList();
			logger.info("Sucessfully Selected records using findByProperty propertyName --  " + propertyName + " and Property Value " + value);
		} catch (NoResultException ex) {
			logger.error("Exception occured while Selecting records using findByProperty propertyName --  " + propertyName + " and Property Value " + value, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_NORESULTFOUND_EXCEPTION, clazz.getSimpleName());
		} catch (PersistenceException ex) {
			logger.error("Exception occured while Selecting records using findByProperty propertyName --  " + propertyName + " and Property Value " + value, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return entityList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Entity> findByPropertyWithOrder(String propertyName, final Object value,String orderByProperty,String orderByClause) throws TelAppointDBException {
		List<Entity> entityList = null;
		try {
			String queryString = new StringBuilder(QRY_SELECT).append(clazz.getSimpleName()).append(QRY_WHERE_MODEL).append(propertyName).append(QRY_PROPERTY).toString();
			queryString = queryString+ORDER_BY_MODEL+orderByProperty+" "+orderByClause;
			//System.out.println(" findByPropertyWithOrder ----  queryString  ----> "+queryString);
			Query query = entityManager.createQuery(queryString);
			query.setParameter(PROPERTYVALUE, value);
			entityList = query.getResultList();
			logger.info("Sucessfully Selected records using findByProperty propertyName --  " + propertyName + " and Property Value " + value);
		} catch (NoResultException ex) {
			logger.error("Exception occured while Selecting records using findByProperty propertyName --  " + propertyName + " and Property Value " + value, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_NORESULTFOUND_EXCEPTION, clazz.getSimpleName());
		} catch (PersistenceException ex) {
			logger.error("Exception occured while Selecting records using findByProperty propertyName --  " + propertyName + " and Property Value " + value, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return entityList;
	}

	@SuppressWarnings("unchecked")
	public Entity findByPropertySingleRow(String propertyName, final Object value) throws TelAppointDBException {
		Entity entity = null;
		try {
			final String queryString = new StringBuilder(QRY_SELECT).append(clazz.getSimpleName()).append(QRY_WHERE_MODEL).append(propertyName).append(QRY_PROPERTY).toString();

			Query query = entityManager.createQuery(queryString);

			query.setParameter(PROPERTYVALUE, value);

			entity = (Entity) query.getSingleResult();

			logger.info("Sucessfully Selected records using findByPropertySingleRow propertyName --  " + propertyName + " and Property Value " + value);
		} catch (NonUniqueResultException ex) {
			logger.error("Exception occured while Selecting records using findByPropertySingleRow propertyName --  " + propertyName + " and Property Value " + value, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_NONUNIQUERESULTFOUND_EXCEPTION, clazz.getSimpleName());
		} catch (NoResultException ex) {
			logger.error("Exception occured while Selecting records using findByProperty propertyName --  " + propertyName + " and Property Value " + value, ex);

		} catch (PersistenceException ex) {

			logger.error("Exception occured while Selecting records using findByPropertySingleRow propertyName --  " + propertyName + " and Property Value " + value, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}

		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<Entity> findByPropertiesWithNull(final Map<String, Object> parameters) throws TelAppointDBException {
		List<Entity> entityList = null;
		String queryStringTemp = null;
		try {
			StringBuilder genQuery = new StringBuilder(queryString);
			if (parameters != null && !parameters.isEmpty()) {
				genQuery.append(QRY_WHERE);
				Set<String> keys = parameters.keySet();
				for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
					String param = iterator.next();
					Object val = parameters.get(param);
					if (val == null) {
						genQuery.append(QRY_MODEL_PERIOD).append(param).append(" is null").append(" and ");
					} else {
						genQuery.append(QRY_MODEL_PERIOD).append(param).append(" = :").append(param.replaceAll(SLASH_PERIOD, UND_SCORE)).append(" and ");
					}
				}
				queryStringTemp = genQuery.substring(0, genQuery.length() - 5);

			} else {
				queryStringTemp = genQuery.toString();
			}

			final String queryString = queryStringTemp;

			Query query = entityManager.createQuery(queryString);
			if (parameters != null && !parameters.isEmpty()) {
				if (parameters != null && !parameters.isEmpty()) {
					Set<String> keys = parameters.keySet();
					for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
						String param = iterator.next();
						Object value = parameters.get(param);
						if (value != null) {
							query.setParameter(param.replaceAll(SLASH_PERIOD, UND_SCORE), value);
						}
					}
				}
			}
			entityList = query.getResultList();
			logger.info("Sucessfully Selected records using findByPropertiesWithNull with parameters - " + parameters);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while Selecting records using findByPropertiesWithNull parameters --  " + parameters, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return entityList;

	}

	@SuppressWarnings("unchecked")
	public List<Entity> findByProperties(final Map<String, Object> parameters) throws TelAppointDBException {
		List<Entity> entityList = null;
		String queryStringTemp = null;
		try {
			StringBuilder genQuery = new StringBuilder(queryString);
			if (parameters != null && !parameters.isEmpty()) {
				genQuery.append(QRY_WHERE);
				Set<String> keys = parameters.keySet();
				for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
					String param = iterator.next();
					// String val = parameters.get(param);
					genQuery.append(QRY_MODEL_PERIOD).append(param).append(" = :").append(param.replaceAll(SLASH_PERIOD, UND_SCORE)).append(" and ");
				}
				queryStringTemp = genQuery.substring(0, genQuery.length() - 5);

			} else {
				queryStringTemp = genQuery.toString();
			}

			final String queryString = queryStringTemp;

			Query query = entityManager.createQuery(queryString);
			if (parameters != null && !parameters.isEmpty()) {
				if (parameters != null && !parameters.isEmpty()) {
					Set<String> keys = parameters.keySet();
					for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
						String param = iterator.next();
						Object value = parameters.get(param);
						query.setParameter(param.replaceAll(SLASH_PERIOD, UND_SCORE), value);
					}
				}
			}
			entityList = query.getResultList();
			logger.info("Sucessfully Selected records using findByProperties  parameters " + parameters);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while Selecting records using findByProperties parameters --  " + parameters, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return entityList;

	}
	
	@SuppressWarnings("unchecked")
	public List<Entity> findByPropertiesWithOrder(final Map<String, Object> parameters,String orderByProperty,String orderByClause) throws TelAppointDBException {
		List<Entity> entityList = null;
		String queryStringTemp = null;
		try {
			StringBuilder genQuery = new StringBuilder(queryString);
			if (parameters != null && !parameters.isEmpty()) {
				genQuery.append(QRY_WHERE);
				Set<String> keys = parameters.keySet();
				for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
					String param = iterator.next();
					// String val = parameters.get(param);
					genQuery.append(QRY_MODEL_PERIOD).append(param).append(" = :").append(param.replaceAll(SLASH_PERIOD, UND_SCORE)).append(" and ");
				}
				queryStringTemp = genQuery.substring(0, genQuery.length() - 5);

			} else {
				queryStringTemp = genQuery.toString();
			}
			queryStringTemp = queryStringTemp+ORDER_BY_MODEL+orderByProperty+" "+orderByClause;
			//System.out.println(" queryStringTemp -------------->  "+queryStringTemp);
			final String queryString = queryStringTemp;

			Query query = entityManager.createQuery(queryString);
			if (parameters != null && !parameters.isEmpty()) {
				if (parameters != null && !parameters.isEmpty()) {
					Set<String> keys = parameters.keySet();
					for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
						String param = iterator.next();
						Object value = parameters.get(param);
						query.setParameter(param.replaceAll(SLASH_PERIOD, UND_SCORE), value);
					}
				}
			}
			entityList = query.getResultList();
			logger.info("Sucessfully Selected records using findByProperties  parameters " + parameters);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while Selecting records using findByProperties parameters --  " + parameters, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return entityList;

	}

	@SuppressWarnings("unchecked")
	public Collection<Entity> findByNamedQuery(final String queryName) throws TelAppointDBException {
		Collection<Entity> entityCollection = null;
		try {
			entityCollection = entityManager.createNamedQuery(queryName).getResultList();
			logger.info("Sucessfully Selected records using findByNamedQuery -- queryName -- " + queryName);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while Selecting records using findByNamedQuery -- queryName -- " + queryName, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return entityCollection;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findByNativeQuery(final String queryString) throws TelAppointDBException {
		List<Object[]> entityList = null;
		try {
			entityList = entityManager.createNativeQuery(queryString).getResultList();
			logger.info("Sucessfully Selected records using findByNativeQuery  queryString -- " + queryString);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while Selecting records using findByNativeQuery  queryString -- " + queryString, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return entityList;
	}

	public Object findByNativeQuerySingleRow(final String queryString) throws TelAppointDBException {
		Object result = null;
		try {
			result = entityManager.createNativeQuery(queryString).getSingleResult();
			logger.info("Sucessfully Selected records using findByNativeQuerySingleRow  queryString -- " + queryString);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while Selecting records using findByNativeQuerySingleRow  queryString -- " + queryString, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Entity> getLatestValuesByProperties(final Map<String, Object> parameters) throws TelAppointDBException {
		List<Entity> entityList = null;
		String queryStringTemp = null;
		try {
			StringBuilder genQuery = new StringBuilder(queryString);
			if (parameters != null && !parameters.isEmpty()) {
				genQuery.append(QRY_WHERE);
				Set<String> keys = parameters.keySet();
				for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
					String param = iterator.next();
					// Object val = parameters.get(param);
					genQuery.append(QRY_MODEL_PERIOD).append(param).append(" = :").append(param.replaceAll(SLASH_PERIOD, UND_SCORE)).append(" and ");
				}
				queryStringTemp = genQuery.substring(0, genQuery.length() - 5);

			} else {
				queryStringTemp = genQuery.toString();
			}

			final String queryString = queryStringTemp;

			Query query = entityManager.createQuery(queryString);
			if (parameters != null && !parameters.isEmpty()) {
				if (parameters != null && !parameters.isEmpty()) {
					Set<String> keys = parameters.keySet();
					for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
						String param = iterator.next();
						Object value = parameters.get(param);
						query.setParameter(param.replaceAll(SLASH_PERIOD, UND_SCORE), value);
					}
				}
			}
			entityList = query.getResultList();
			logger.info("Sucessfully Selected records using getLatestValuesByProperties  parameters -- " + parameters);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while Selecting records using getLatestValuesByProperties  parameters -- " + parameters, ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return entityList;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getNativeQueryList(final String queryString) throws TelAppointDBException {
		List<Object[]> nativeQueryReslutList = null;
		try {

			nativeQueryReslutList = (List<Object[]>) entityManager.createNativeQuery(queryString).getResultList();

			logger.info("Sucessfully Selected records using getNativeQueryList  queryString -- " + queryString);

		} catch (PersistenceException ex) {

			logger.error("Exception occured while Selecting records using getLatestValuesByProperties  queryString -- " + queryString, ex);

			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_QUERING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
		return nativeQueryReslutList;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void callStoredProcedure(String storedProcedureName) throws TelAppointDBException {
		try {
			Query query = entityManager.createNativeQuery("call " + storedProcedureName + "()");

			query.executeUpdate();
			logger.info("callig procedure  performed Sucessfully ..   ---  " + storedProcedureName);
		} catch (PersistenceException ex) {
			logger.error("Exception occured while call the procudure- " + clazz.getName(), ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_DELETING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
	}
	
	@Override
	public Map<Integer, String> getNativeMap(String sql) {
		Map<Integer, String> map = null;
		List<Object[]> nativeList = getNativeQueryList(sql.toString());
		if (nativeList != null) {
			map = new LinkedHashMap<Integer, String>();
			for (Object[] ob : nativeList) {
				map.put((Integer) ob[0], (String) ob[1]);
			}
		}
		return map;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteByQuery(int propertyValue) throws TelAppointDBException {
		try {
			//System.out.println("Delete Query  :"+deleteQueryString);
			Query query = entityManager.createQuery(deleteQueryString);
			query.setParameter(PROPERTYVALUE, propertyValue).executeUpdate();
			 logger.info("Sucessfully Deleted  Entity  ---  " );
		} catch (PersistenceException ex) {
			logger.error("Exception occured while deleting Entity - " + clazz.getName(), ex);
			throw new TelAppointDBException(ExceptionConstants.TELAPPOINTDB_DELETING_PERSISTENCE_EXCEPTION, clazz.getSimpleName());
		}
	}
	
	@Override
	public Set<Integer> getOrderSet(String sql) {
		Set<Integer> resultList = null;
		List<Object[]> nativeResultList = getNativeQueryList(sql.toString());
		if (nativeResultList != null && nativeResultList.size() > 0) {
			resultList = new LinkedHashSet<Integer>();
			for (Object[] obj : nativeResultList) {
				resultList.add((Integer) obj[1]);
			}
		}
		logger.info("ResultList  : " + resultList);
		return resultList;
	}

	public Class<Entity> getClazz() throws TelAppointDBException {
		return clazz;
	}

	public void setClazz(Class<Entity> clazz) throws TelAppointDBException {
		this.clazz = clazz;
	}

	public String getQueryString() throws TelAppointDBException {
		return queryString;
	}

	public void setQueryString(String queryString) throws TelAppointDBException {
		this.queryString = queryString;
	}
}
