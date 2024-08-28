package com.ericsson.oss.nfe.wfs.extn.bpmnparser;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @see https
 *      ://github.com/camunda/camunda-bpm-examples/tree/master/process-engine
 *      -plugin/bpmn-parse-listener
 * @author evigkum
 * 
 */
public class CustomBPMNParseListenerPlugin extends AbstractProcessEnginePlugin {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void preInit(
			ProcessEngineConfigurationImpl processEngineConfiguration) {

		System.out
				.println(" -------------------~ CustomBPMNParseListenerPlugin preInit 11 ~--------------------");
		
		logger.debug(" -------------------~ CustomBPMNParseListenerPlugin preInit started ~--------------------");

		// get all existing preParseListeners
		List<BpmnParseListener> preParseListeners = processEngineConfiguration
				.getPreParseListeners();

		if (preParseListeners == null) {

			// if no preParseListener exists, create new list
			preParseListeners = new ArrayList<BpmnParseListener>();
			processEngineConfiguration.setPreParseListeners(preParseListeners);
		}

		// add new BPMN Parse Listener
		preParseListeners.add(new CustomBPMNParseListener());
		
		logger.debug(" -------------------~ CustomBPMNParseListenerPlugin preInit ended ~--------------------");
	}
}
