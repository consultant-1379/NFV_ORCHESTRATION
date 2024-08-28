package com.ericsson.oss.nfe.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import com.ericsson.oss.nfe.persistence.ProcessInstanceProgressDAO;
import com.ericsson.oss.nfe.persistence.entity.ExtendedProcessInstance;
import com.ericsson.oss.nfe.persistence.entity.ProcessDeploymentEntity;

public class ProcessInstanceProgressDAOImpl implements ProcessInstanceProgressDAO {

	@Resource(mappedName = "java:jboss/datasources/WorkflowDatabase")
	private DataSource ds;
	
	@Resource(mappedName = "java:jboss/datasources/ProcessEngine")
	private DataSource processEngineDs;

	@Override
	public List<ExtendedProcessInstance> getProcessInstancesProgressMap(List<ExtendedProcessInstance> instancesList) {

		if(instancesList.isEmpty()) {
			return instancesList;
		}
		Map<String, String> progressMap = new HashMap<String, String>();
			
		List<String> instancesListStrID = new ArrayList<String>();
		for (ExtendedProcessInstance i : instancesList) {
			instancesListStrID.add("'" + i.getId() + "'");
		}

		String sqlQuery = "SELECT wfinstanceid, max(regexp_matches) AS progress "
				+ "FROM (SELECT wfinstanceid, regexp_matches(nodeid,'p\\d+') FROM PUBLIC.workflowprogressevent "
				+ "WHERE nodeid LIKE '%prg__p%' AND wfinstanceid IN " + "(" + StringUtils.join(instancesListStrID, ',') + ") "
				+ "AND eventtype='end' ORDER BY regexp_matches desc) d1 GROUP BY wfinstanceid";

		ResultSet rs = null;

		Connection conn = null;
		
		try {
			conn =  ds.getConnection();
			rs =conn.prepareStatement(sqlQuery).executeQuery();
			while (rs.next()) {
				String wfinstanceid = rs.getString("wfinstanceid");
				String progress = rs.getString("progress");
				progressMap.put(wfinstanceid, progress.replace("{p", "").replace("}", ""));
			}
		} catch (SQLException e) {
			System.err.println("Error in sql query for getProcessInstancesProgressMap"+e.getMessage());
		} finally {
			 this.releaseResources(conn);
		}

		for (ExtendedProcessInstance instance : instancesList) {
			String progress = "";
			progress = progressMap.get(instance.getId());
			
			if (progress == "" || progress == null)
				progress = "NA";
			instance.setProgressPercentage(progress);
		}

		return instancesList;
	}
	
	
	public List<ProcessDeploymentEntity> getAllProcessDeployments()
	{
		ResultSet rs = null;
		List<ProcessDeploymentEntity> alldeployments = new ArrayList<ProcessDeploymentEntity>();
		Connection conn = null;
		
		try {
			conn =  processEngineDs.getConnection();
			rs =conn.prepareStatement("select id_ as deploymentId,name_ as deploymentName,deploy_time_ as deploymentTime from act_re_deployment").executeQuery();
			
//			while (rs.next()) {				 
//				ProcessDeploymentEntity entity = new ProcessDeploymentEntity();
//				entity.setDeploymentId(rs.getString("deploymentId"));
//				entity.setName(rs.getString("deploymentName"));
//				
//				Date tempDate=new Date(rs.getDate("deploymentTime").getTime());
//                entity.setDeploymentTime(tempDate);
//                System.out.println("Date: "+tempDate.getTime());
//
//				entity.setDeploymentTime(new Date(rs.getDate("deploymentTime").getTime()));
//				alldeployments.add(entity);	
			
			while (rs.next()) {                      
                ProcessDeploymentEntity entity = new ProcessDeploymentEntity();
                entity.setDeploymentId(rs.getString("deploymentId"));
                entity.setName(rs.getString("deploymentName"));
                java.sql.Timestamp tempSQLTimestamp=rs.getTimestamp("deploymentTime");
                long dateLong=tempSQLTimestamp.getTime();
                java.util.Date tempUtilDate=new java.util.Date(dateLong);
                entity.setDeploymentTime(tempUtilDate);
                System.out.println("Date: "+tempUtilDate.getTime());
                alldeployments.add(entity); 
                
          }

		} catch (SQLException e) {
			System.err.println("Error in sql query for getAllProcessDeployments"+e.getMessage());
		} finally {
			 this.releaseResources(conn);
		} 
		return alldeployments;
	}
	
	public void releaseResources(AutoCloseable closable) {
		try {
			if (closable != null)
				closable.close();
		} catch (Exception eignore) {
			System.out.println("releaseResources failed");
		}
	}
}