/* Copyright (c) Ericsson 2015 */

define("3pps/wfs/WfDefinitionClient",["jscore/core","jscore/ext/mvp","jscore/ext/net","3pps/wfs/WfClientCommon"],function(e,t,r,s){var i={};return i.getAllDefinitions=function(e){if(e=e||{},null==e.success)throw new Error("success callback missing");var r="/workflowservice-ext/rest/nfv_resource/process-definition-ext",s=0;if(null!=e.latest&&(r+="?latest="+(e.latest?"true":"false"),s++),null!=e.offset&&(r+="&firstResult="+e.offset),null!=e.limit&&(r+="&maxResults="+e.limit),null!=e.sortBy){if(-1==["name"].indexOf(e.sortBy))throw new Error("Invalid sortBy value: "+sortBy);if(r+=(s>0?"&":"?")+"sortBy="+e.sortBy,null!=e.sortOrder){if(-1==["asc","desc"].indexOf(e.sortOrder))throw new Error("Invalid sortOrder value: "+sortOrder);r+="&sortOrder="+e.sortOrder}}var i=new t.Collection([],{url:r});i.fetch({success:function(r){var s=new t.Collection;r.each(function(e){var r=new t.Model;r.setAttribute("id",e.getAttribute("id")),r.setAttribute("name",e.getAttribute("name")),r.setAttribute("key",e.getAttribute("key")),r.setAttribute("version",e.getAttribute("version")),r.setAttribute("description",e.getAttribute("description")),r.setAttribute("deploymentId",e.getAttribute("deploymentId")),r.setAttribute("createdOn",e.getAttribute("createdOn")),r.setAttribute("createdOnStr",e.getAttribute("createdOnStr")),r.setAttribute("category",e.getAttribute("category")),s.addModel(r)}),e.success.call(this,s)},error:function(t){e.error&&e.error.call(this,t)}})},i.getAllDeployments=function(e){if(e=e||{},null==e.success)throw new Error("success callback missing");var r="/workflowservice-ext/rest/progress-service/process-deployments",s=new t.Collection([],{url:r});s.fetch({success:function(r){var s=new t.Collection;r.each(function(e){var r=new t.Model;r.setAttribute("id",e.getAttribute("deploymentId")),r.setAttribute("name",e.getAttribute("name")),r.setAttribute("deploymentTime",e.getAttribute("deploymentTime")),s.addModel(r)}),e.success.call(this,s)},error:function(t){e.error&&e.error.call(this,t)}})},i.getStartFormModelById=function(e){if(e=e||{},null==e.success)throw new Error("success callback missing");if(null==e.wfDefinitionId||""==e.wfDefinitionId)throw new Error("wfDefinitionId missing");var i="/engine-rest/engine/default/process-definition/"+e.wfDefinitionId+"/startForm",n=new t.Model([],{url:i});n.fetch({success:function(i){if(null==i.getAttribute("key"))e.success.call(this,null);else try{var n=i.getAttribute("contextPath"),l=i.getAttribute("key"),c=s.extractFormInfo(l),u=n+"/"+c.path;r.ajax({url:u,type:"GET",dataType:c.format,success:function(r){if("json"==c.format){r.modelType!=c.modelType&&o(e,"Form modelType mismatch, "+r.modelType+"!="+c.modelType);var s=new t.Model;s.setAttribute("formInfo",c),s.setAttribute("submitText",r.submitText),s.setAttribute("cancelText",r.cancelText),s.setAttribute("controlGroups",r.controlGroups),e.success.call(this,s)}else e.success.call(this,r)},error:function(t){o(e,"Error retrieving form: "+u+". "+t)}})}catch(f){o(e,f)}},error:function(t){o(e,t)}});var o=function(e,t){e.error&&e.error.call(this,t),console.log(t)}},i.getStructure=function(e){if(e=e||{},null==e.success)throw new Error("success callback missing");if(null==e.wfDefinitionId||""==e.wfDefinitionId)throw new Error("wfDefinitionId missing");var r="/WorkflowService/oss/rest/services/wfs/default/definitions/"+e.wfDefinitionId+"/structure",s=new t.Model([],{url:r});s.fetch({success:function(t){e.success.call(this,t)},error:function(t){e.error&&e.error.call(this,t),console.log(t)}})},i.getWfDiagramXml=function(e){if(e=e||{},null==e.success)throw new Error("success callback missing");if(null==e.wfDefinitionId||""==e.wfDefinitionId)throw new Error("wfDefinitionId missing");var r="/engine-rest/engine/default/process-definition/"+e.wfDefinitionId+"/xml",s=new t.Model([],{url:r});s.fetch({success:function(t){e.success.call(this,t)},error:function(t){e.error&&e.error.call(this,t),console.log(t)}})},i.getDefinitionNames=function(e){if(e=e||{},null==e.success)throw new Error("success callback missing");var r="/engine-rest/engine/default/process-definition",s=0;if(null!=e.latest&&(r+="?latest="+(e.latest?"true":"false"),s++),null!=e.sortBy){if(-1==["name"].indexOf(e.sortBy))throw new Error("Invalid sortBy value: "+sortBy);if(r+=(s>0?"&":"?")+"sortBy="+e.sortBy,null!=e.sortOrder){if(-1==["asc","desc"].indexOf(e.sortOrder))throw new Error("Invalid sortOrder value: "+sortOrder);r+="&sortOrder="+e.sortOrder}}var i=new t.Collection([],{url:r});i.fetch({success:function(r){var s=new t.Collection;r.each(function(e){var r=new t.Model;r.setAttribute("id",e.getAttribute("id")),r.setAttribute("name",e.getAttribute("name")),r.setAttribute("version",e.getAttribute("version")),s.addModel(r)}),e.success.call(this,s)},error:function(t){e.error&&e.error.call(this,t)}})},i});