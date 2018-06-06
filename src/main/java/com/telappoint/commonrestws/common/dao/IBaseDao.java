package com.telappoint.commonrestws.common.dao;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import com.telappoint.commonrestws.common.dao.exception.TelAppointDBException;

/**
 * @author Murali
 * @author Balaji
 */

public interface IBaseDao<Entity, PKey extends Serializable> {	
	
	String FWD_SLASH = "/";
	String PERIOD = ".";
	String UND_SCORE = "_";
	String EMPTY = "";	
	String PROPERTYVALUE = "propertyValue";
	final String SLASH_PERIOD = "\\.";

	public Entity findById(PKey primaryKey);
	
	public List<Entity> findByProperty(String propertyName, final Object value)throws TelAppointDBException;

	public Collection<Entity> findByNamedQuery(final String queryName)throws TelAppointDBException;

	public List<Entity> findByProperties(final Map<String, Object> parameters)throws TelAppointDBException;
	
	public List<Entity> findByPropertiesWithOrder(final Map<String, Object> parameters,String orderByProperty,String orderByClause) throws TelAppointDBException ;
		
	public List<Entity> findByPropertyWithOrder(String propertyName, final Object value,String orderByProperty,String orderByClause) throws TelAppointDBException;
	
	public Entity findByPropertySingleRow(String propertyName, final Object value)throws TelAppointDBException;

	public List<Entity> findByPropertiesWithNull(final Map<String, Object> parameters)throws TelAppointDBException;

	public List<Entity> findByQuery(final String queryString)throws TelAppointDBException;

	public List<Object[]> findByNativeQuery(final String queryString)throws TelAppointDBException;

	public List<Entity> findAll()throws TelAppointDBException;

	public Object findByAggregate(final String queryString)throws TelAppointDBException;

	public void save(Entity transientInstance)throws TelAppointDBException;
	
	public void save(List<Entity> transientInstance)throws TelAppointDBException;

	public Entity update(Entity detachedInstance)throws TelAppointDBException;
	
	public void update(List<Entity> detachedInstance)throws TelAppointDBException;

	public void delete(Entity persistentInstance)throws TelAppointDBException;
	
	public void delete(List<Entity> persistentInstance)throws TelAppointDBException;
	
	public void deleteMerge(Entity persistentInstance)throws TelAppointDBException;
	
	public void deleteAll()throws TelAppointDBException;
	
	public void deleteById(PKey pKey)throws TelAppointDBException;
	
	public void delete(Entity... persistentInstance)throws TelAppointDBException;

	public int updateQuery(final String queryString)throws TelAppointDBException;

	public int updateNativeQuery(final String queryString)throws TelAppointDBException;
	
	public List<Entity> getLatestValuesByProperties(final Map<String, Object> parameters)throws TelAppointDBException;

	public Object findByNativeQuerySingleRow(final String queryString)throws TelAppointDBException;
	
	public List<Object[]> getNativeQueryList(final String queryString) throws TelAppointDBException;
	
	public void callStoredProcedure(String storedProcedureName) throws TelAppointDBException;
	
	public void setEntityManager(EntityManager entityManager) throws TelAppointDBException;
	
	public Map<Integer, String> getNativeMap(String sql);
	
	public Set<Integer> getOrderSet(String sql);
	
	public void deleteByQuery(int propertyValue) throws TelAppointDBException;
}
