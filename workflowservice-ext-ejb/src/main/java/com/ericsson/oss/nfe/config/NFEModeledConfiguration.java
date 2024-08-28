/*package com.ericsson.oss.nfe.config;
import com.ericsson.oss.itpf.modeling.annotation.DefaultValue;
import com.ericsson.oss.itpf.modeling.annotation.EModel;
import com.ericsson.oss.itpf.modeling.annotation.configparam.ConfParamDefinition;
import com.ericsson.oss.itpf.modeling.annotation.configparam.ConfParamDefinitions;
import com.ericsson.oss.itpf.modeling.annotation.configparam.Scope;
import com.ericsson.oss.itpf.modeling.annotation.constraints.NotNull;
import com.ericsson.oss.itpf.modeling.annotation.constraints.Size;

@EModel(namespace = "NFEModeledConfiguration", description = "Modeled Configuration for NFE")
@ConfParamDefinitions
public class NFEModeledConfiguration {

	@NotNull
    @ConfParamDefinition(description = "Configuration parameter used for Camunda ServerBase URL",scope = Scope.GLOBAL, overridableInScopes = { Scope.SERVICE })
    @DefaultValue("http://localhost:8080/engine-rest/engine/default")
    public String camundaServerBaseURL;

    @NotNull
    @Size(min = 23, max = 42)
    @ConfParamDefinition(description = "The type of service", scope = Scope.GLOBAL, overridableInScopes = { Scope.SERVICE })
    @DefaultValue("10")
    public Integer type;
}
*/