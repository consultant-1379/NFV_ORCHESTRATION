package com.ericsson.oss.nfe.wfs.extn.jobretry;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;

/**
 * @author evigkum
 *
 */
public class CustomFailedJobCmdProcessEnginePlugin implements ProcessEnginePlugin {

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

    }

    @Override
    public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    	
    	System.out.println("-----------------------------------CustomFailedJobCmdProcessEnginePlugin registered --------------------------");
        processEngineConfiguration.setFailedJobCommandFactory(new CustomFailedJobCmdFactory());
        System.out.println("---------------------- processEngineConfiguration.getFailedJobCommandFactory() "+processEngineConfiguration.getFailedJobCommandFactory());
    }

    @Override
    public void postProcessEngineBuild(ProcessEngine processEngine) {

    }

}
