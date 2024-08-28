package com.ericsson.oss.nfe.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.nfe.persistence.ProcessTriggerDAO;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity.ProcessTriggerWorkflowEntity;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerDefinitionEntity;
import com.ericsson.oss.nfe.persistence.entity.ProcessTriggerEventEntity;
import com.ericsson.oss.nfe.persistence.entity.TriggerStatus;

public class ProcessTriggerDAOImpl implements ProcessTriggerDAO {

	
    private final Logger logger = LoggerFactory.getLogger(getClass());

    
	@PersistenceContext(unitName = "defaultPersistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public ProcessTriggerDefinitionEntity createProcessTriggerDefinition(ProcessTriggerDefinitionEntity processTrigger) {
	
		logger.trace("creatProcessTriggerDefn started "); 
		
		List<ProcessTriggerDefinitionEntity> triggers  = 
				this.getWorkFlowDefinitions(null, 
						processTrigger.getTriggerType(), 
						processTrigger.getFilterString());
		
		logger.trace("Triggers returned is : "+triggers);
		//If already a trigger Definition for a given FilterString and Trigger type exist, 
		//then update the workflowmappings 
		if(triggers!=null && !triggers.isEmpty())
		{
			
			logger.trace("TriggerDefn already present so updating mapped wfs "); 
			
			ProcessTriggerDefinitionEntity dbTrigger = triggers.get(0);
			
			processTrigger.setId(dbTrigger.getId());
			
			for(ProcessTriggerWorkflowEntity trigwf :processTrigger.getMappedWorkFlows())
			{
				trigwf.setTriggerdefnitionid(dbTrigger.getId());
			}
			
			if(dbTrigger.getMappedWorkFlows()!=null && !dbTrigger.getMappedWorkFlows().isEmpty())
			{
				for(ProcessTriggerWorkflowEntity trigwf :dbTrigger.getMappedWorkFlows())
				{
					if(!processTrigger.getMappedWorkFlows().contains(trigwf))
					{
						processTrigger.getMappedWorkFlows().add(trigwf);
					}					 
						
				}
			}
			
			entityManager.merge(processTrigger);
			
		}
		else 		
			entityManager.persist(processTrigger);
 		
		logger.trace("creatProcessTriggerDefn completed ");
		
		return processTrigger;
	}


	@Override
	public ProcessTriggerDefinitionEntity updateProcessTriggerDefinition(
			ProcessTriggerDefinitionEntity processTrigger) {
		logger.trace("updateProcessTriggerDefinition started ");

		List<ProcessTriggerDefinitionEntity> triggers = this
				.getWorkFlowDefinitions(null, processTrigger.getTriggerType(),
						processTrigger.getFilterString());

		logger.trace("Triggers returned is : " + triggers);
		// If already a trigger Definition for a given FilterString and Trigger
		// type exist,
		// then update the workflowmappings
		if (triggers != null && !triggers.isEmpty()) {

			logger.trace("TriggerDefn already present so updating mapped wfs ");

			ProcessTriggerDefinitionEntity dbTrigger = triggers.get(0);

			processTrigger.setId(dbTrigger.getId());

			for (ProcessTriggerWorkflowEntity trigwf : processTrigger
					.getMappedWorkFlows()) {
				trigwf.setTriggerdefnitionid(dbTrigger.getId());
			}

			/**************************************************
			 * Don't merge with old workflow keys into the current one
			 */
//			if (dbTrigger.getMappedWorkFlows() != null
//					&& !dbTrigger.getMappedWorkFlows().isEmpty()) {
//				for (ProcessTriggerWorkflowEntity trigwf : dbTrigger
//						.getMappedWorkFlows()) {
//					if (!processTrigger.getMappedWorkFlows().contains(trigwf)) {
//						processTrigger.getMappedWorkFlows().add(trigwf);
//					}
//
//				}
//			}

			entityManager.merge(processTrigger);

		} else
			entityManager.persist(processTrigger);

		logger.trace("creatProcessTriggerDefn completed ");

		return processTrigger;
	}
	
	@Override
	public ProcessTriggerDefinitionEntity modifyProcessTriggerDefinition(ProcessTriggerDefinitionEntity processTrigger) {
	
		logger.trace("creatProcessTriggerDefn started "); 
		
		 
		
		List<ProcessTriggerDefinitionEntity> triggers  = 
				this.getWorkFlowDefinitions(null, 
						processTrigger.getTriggerType(), 
						processTrigger.getFilterString());
		
		logger.trace("Triggers returned is : "+triggers);
		//If already a trigger Definition for a given FilterString and Trigger type exist, 
		//then update the workflowmappings 
		if(triggers!=null && !triggers.isEmpty())
		{
			
			logger.trace("TriggerDefn already present so updating mapped wfs "); 
			
			ProcessTriggerDefinitionEntity dbTrigger = triggers.get(0);
			
			processTrigger.setId(dbTrigger.getId());
			
			for(ProcessTriggerWorkflowEntity trigwf :processTrigger.getMappedWorkFlows())
			{
				trigwf.setTriggerdefnitionid(dbTrigger.getId());
			}
			
			if(dbTrigger.getMappedWorkFlows()!=null && !dbTrigger.getMappedWorkFlows().isEmpty())
			{
				for(ProcessTriggerWorkflowEntity trigwf :dbTrigger.getMappedWorkFlows())
				{
					if(!processTrigger.getMappedWorkFlows().contains(trigwf))
					{
						processTrigger.getMappedWorkFlows().add(trigwf);
					}					 
						
				}
			}
			
			entityManager.merge(processTrigger);
			
		}
		else 		
			entityManager.merge(processTrigger);
 		
		logger.trace("creatProcessTriggerDefn completed ");
		
		return processTrigger;
	}

	
	@Override
	public List<ProcessTriggerDefinitionEntity> getAllTriggerDefinitions() {		
		
		Query query = entityManager.createQuery("from ProcessTriggerDefinitionEntity pt ORDER BY pt.id");
		List<ProcessTriggerDefinitionEntity> list = query.getResultList();		
		return list;
	}
	
	@Override
	public List<ProcessTriggerEventEntity> getAllTriggerEvents() {
		
		Query query = entityManager.createQuery("from ProcessTriggerEventEntity pte ORDER BY pte.id");
		List<ProcessTriggerEventEntity> list = query.getResultList();
		
		//Doing this to load the lazy loaded childs
		for (int i = 0; i < list.size(); i++) {
			logger.trace(list.get(i).getTriggeredWorkFlows().toString());		
			
		}
		
		return list;
	}

	@Override
	public List<ProcessTriggerWorkflowEntity> getMappedWorkFlowDefinitions(
			String triggerName, String triggerType, String filterString) {
		
		List<ProcessTriggerDefinitionEntity> triggers  = 
				this.getWorkFlowDefinitions(triggerName, triggerType, filterString);
		
		 List<ProcessTriggerWorkflowEntity> mappedWorkflows = new ArrayList<ProcessTriggerWorkflowEntity>();
		
		for(ProcessTriggerDefinitionEntity trigDefn:triggers){
			mappedWorkflows.addAll(trigDefn.getMappedWorkFlows());
		}
		
		return mappedWorkflows;
	}
	
	
 	 private List<ProcessTriggerDefinitionEntity> getWorkFlowDefinitions(
			String triggerName, String triggerType, String filterString) {
		
 		logger.trace("getWorkFlowDefinitions for following criterina triggerName :"
 				+ " {0}, triggerType : {1}, filterString:{3}",new String[]{triggerName,triggerType,filterString});
 		
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<ProcessTriggerDefinitionEntity> criteriaQuery = criteriaBuilder.createQuery(ProcessTriggerDefinitionEntity.class);
		Root from = criteriaQuery.from(ProcessTriggerDefinitionEntity.class);

		if(triggerName!=null)
		{
		
			Predicate predicate1 = criteriaBuilder.equal(from.get("triggerName"), triggerName);		
			Predicate predicate2 = criteriaBuilder.equal(from.get("triggerType"), triggerType);
			Predicate predicate3 = criteriaBuilder.like(from.get("filterString" ) , filterString);
			criteriaQuery.where(criteriaBuilder.and(new Predicate[]{predicate1,predicate2,predicate3}));
		}
		else
		{
			Predicate predicate2 = criteriaBuilder.equal(from.get("triggerType"), triggerType);
			Predicate predicate3 = criteriaBuilder.like(from.get("filterString" ) , filterString);
			criteriaQuery.where(criteriaBuilder.and(new Predicate[]{predicate2,predicate3}));
		}
		final TypedQuery<ProcessTriggerDefinitionEntity> query = entityManager.createQuery(criteriaQuery);
		
		logger.trace("getWorkFlowDefinitions ended with result count :");
		
		return query.getResultList();
	}


	@Override
	public ProcessTriggerEventEntity createProcessTriggerEventEntity(
			ProcessTriggerEventEntity processTriggerEvent) {
		
		logger.debug("creatProcessTrigger started "); 
 		
		entityManager.persist(processTriggerEvent);
 		
		logger.debug("creatProcessTrigger completed ");
		
		return processTriggerEvent;
	}

	/**
	 * Get the triggered events for a given triggerType and filterString which were 
	 * successful;
	 */

	@Override
	public List<ProcessTriggerEventEntity> getAllTriggerEvents(
			String triggerType, String filterString) {
		
		logger.trace("getAllTriggerEvents for following criteria "
				+ " triggerType : {0}, filterString:{1}",new String[]{triggerType,filterString});
 		

		final CriteriaBuilder criteriaBuilder = entityManager
				.getCriteriaBuilder();

		CriteriaQuery<ProcessTriggerEventEntity> criteriaQuery = criteriaBuilder
				.createQuery(ProcessTriggerEventEntity.class);
		Root from = criteriaQuery.from(ProcessTriggerEventEntity.class);

		Predicate predicate1 = criteriaBuilder.equal(from.get("triggerType"),
				triggerType);

		Predicate predicate2 = criteriaBuilder.notEqual(from.get("status"),
				TriggerStatus.FAILED.name());
		
		if (filterString != null) {
			
			Predicate predicate3 = criteriaBuilder.like(
					from.get("filterString"), filterString);
			criteriaQuery.where(criteriaBuilder.and(new Predicate[] {
					predicate1, predicate2,predicate3}));
		} else
			criteriaQuery.where(criteriaBuilder.and(new Predicate[] {
					predicate1, predicate2 }));

		final TypedQuery<ProcessTriggerEventEntity> query = entityManager
				.createQuery(criteriaQuery);

		return query.getResultList();
	}


	@Override
	public String deleteProcessTriggerDefinition(String definitionId) {
		
		Long id = Long.parseLong(definitionId);		
		ProcessTriggerDefinitionEntity triggerDefn = entityManager.
				find(ProcessTriggerDefinitionEntity.class,id);
		if(triggerDefn!=null)
			entityManager.remove(triggerDefn);
		else
			return "No Process Trigger for Id : "+definitionId;
		
		return "OK";
		
	}


	@Override
	public String deleteProcessTriggerWorkflowEntity(String id) {
		
		Long defnid = Long.parseLong(id);	
		ProcessTriggerWorkflowEntity triggerDefn = entityManager.
				find(ProcessTriggerWorkflowEntity.class,defnid);
		
		if(triggerDefn!=null)
			entityManager.remove(triggerDefn);
		else
			return "No ProcessTriggerWorkflow for Id : "+id;
		
		return "OK";
		
	}

}
