package com.ericsson.oss.nfe.poc.core;

public interface ApplicationConstants {
	
	String GET_VDC_URL_KEY = "QUERY_VDC_FOR_TENANT";
	
	String GET_VNS_FOR_VDC_URL_KEY = "QUERY_VNS_FOR_TENANT_VDCID";
	
	String BASE_ECM_URL_KEY = "BASE_ECM_URL";
	
	String GET_VMS_FOR_VDC_URL_KEY = "QUERY_VMS_FOR_TENANT_VDCID";
	
	String GET_VAPPS_FOR_VDC_URL_KEY = "QUERY_VAPPS_FOR_TENANT_VDCID";
	
	String COMPLETED_PROVISIONING = "COM";
	
	String ACTIVE_PROVISIONING = "ACTIVE";
	
	String TENANTNAME_KEY = "";
	
	String ECM_NAMEID_MAP = "ECM_NAMEID_MAP";
	
	String NAME_JSON_KEY = "name";
	
	String ID_JSON_KEY = "id";
	
	String PROVISION_STATUS_KEY = "provisioningStatus";
	
	String VDC_PREFIX = "vdc_";
	
	String VN_PREFIX = "vn_";
	
	String VM_PREFIX = "vm_";
	
	String APPCONFIGDIR ="APPCONFIGDIR";
	
	String VDC_NAME_PLACHEHOLDER = "VDC_NAME";
	
    String DEFAULT_VDCNAME_KEY="default.VDCName";


    //BPMN Error Relate
    
    String BPMN_BUSINESS_ERROR="BPMN_BUSINESS_ERROR";
    
    String BPMN_RUNTIME_ERROR="BPMN_RUNTIME_ERROR";
    
    String BPMN_MISSING_PARAM ="BPMN_MISSING_PARAM";
    
    String FILE_DETECTOR_TYPE="file.detector.type";
    
    String INPUT_DIRECTORY="input.directory";
    
    String DEFAULT_TOR_USER = "default.tor.user";
    
}
