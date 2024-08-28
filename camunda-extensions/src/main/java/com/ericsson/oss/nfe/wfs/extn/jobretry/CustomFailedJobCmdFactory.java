package com.ericsson.oss.nfe.wfs.extn.jobretry;

import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.jobexecutor.FailedJobCommandFactory;


/**
 * @author evigkum
 *
 */
public class CustomFailedJobCmdFactory implements FailedJobCommandFactory {

    @Override
    public Command<Object> getCommand(String jobId, Throwable exception) {
        //return new CustomFailedJobRetryCmd(jobId, exception);
        return new CustomRetryStrategy(jobId, exception);
    }

}
