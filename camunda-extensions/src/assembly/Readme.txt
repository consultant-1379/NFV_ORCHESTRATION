
Deploy the module and enable the plugin
- update module.xml in /jboss-eap-6.1/modules/system/layers/base/org/camunda/bpm/jboss/camunda-jboss-subsystem/main  to add below line
 
 	<module name="com.ericsson.oss.services.wfs.extension"/> 
 
  
- update the standalone-full.xml 

<subsystem xmlns="urn:org.camunda.bpm.jboss:1.1">
            <process-engines>
                <process-engine name="default" default="true">
                    <datasource>
                        java:jboss/datasources/ProcessEngine
                  .....
                    <plugins>
                        <plugin>
                            <class>
                                org.camunda.bpm.application.impl.event.ProcessApplicationEventListenerPlugin
                            </class>
                        </plugin>
                        <plugin>
                            <class>
                              com.ericsson.oss.nfe.wfs.extn.bpmnparser.CustomBPMNParseListenerPlugin
                            </class>
                        </plugin>
                         <plugin>
                            <class>
                             com.ericsson.oss.nfe.wfs.extn.jobretry.CustomFailedJobCmdProcessEnginePlugin
                            </class>
                        </plugin>
                           </plugins>
                </process-engine>
            </process-engines>