package com.ericsson.oss.nfe.wfs.extn.jobretry;

/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParse;
import org.camunda.bpm.engine.impl.bpmn.parser.ErrorEventDefinition;
import org.camunda.bpm.engine.impl.cmd.FoxJobRetryCmd;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.runtime.AtomicOperation;

/**
 * @author evigkum
 *
 */
public class CustomRetryStrategy extends FoxJobRetryCmd {

  public static Logger LOG = Logger.getLogger(CustomRetryStrategy.class.getName());

  protected static final String FAILED_JOB_ERROR = "failedJobError";

  public CustomRetryStrategy(String jobId, Throwable exception) {
    super(jobId, exception);
  }

  public Object execute(CommandContext commandContext) {
	  
  	System.out.println("-------------------CustomRetryStrategy#execute ---------------------------------");
  	
	  
    JobEntity job = commandContext.getJobManager().findJobById(jobId);
    
    System.out.println("CustomRetryStrategy#"+job);
    if(job == null) {
      return null;
    }
    else {
    	
    	System.out.println("CustomRetryStrategy# job.getRetries()"+job.getRetries());
      if(job.getRetries() > 1) {
        // this is not the last retry => perform default behavior
        return super.execute(commandContext);

      } else {
        // this is the last retry and it failed => look for custom error handler

        ExecutionEntity execution = commandContext.getExecutionManager().findExecutionById(job.getExecutionId());
        ActivityImpl currentActivity = execution.getActivity();

        
    	System.out.println("CustomRetryStrategy#currentActivity"+currentActivity);

    	
        ErrorEventDefinition failedJobErrorDefinition = null;

        // look for an error handler boundary event:
        List<ErrorEventDefinition> definitions = (List<ErrorEventDefinition>) currentActivity.getProperty(BpmnParse.PROPERTYNAME_ERROR_EVENT_DEFINITIONS);
        if(definitions != null) {
          for (ErrorEventDefinition definition : definitions) {
            // check for error code
            if(definition.catchesError(FAILED_JOB_ERROR)) {
              failedJobErrorDefinition = definition;
            }
          }
        }

        System.out.println("CustomRetryStrategy#failedJobErrorDefinition"+failedJobErrorDefinition);
        
        if(failedJobErrorDefinition != null) {
          // boundary event found => execute it!

          job.delete();

          String handlerId = failedJobErrorDefinition.getHandlerActivityId();
          ActivityImpl errorHandler = execution.getProcessDefinition().findActivity(handlerId);

          execution.setActivity(errorHandler);
          execution.performOperation(AtomicOperation.ACTIVITY_START);

          return null;
        }
        else {
          // no boundary event found => perform default behavior
          return super.execute(commandContext);
        }
      }
    }
  }

}