/* Copyright (c) Ericsson 2015 */

define("3pps/wfs/WfProgressClient",["jscore/core","jscore/ext/mvp","jscore/ext/net"],function(e,s,r){var t={};return t.getProgress=function(e){if(e=e||{},null==e.success)throw new Error("success callback missing");var t="/WorkflowService/oss/rest/services/wfs/default/progresses/"+e.wfInstanceId;r.ajax({url:t,type:"GET",dataType:"json",success:function(r){var t=new s.Model;t.setAttribute("instanceId",r.instanceId),t.setAttribute("nodeProgresses",r.nodeProgresses),e.success.call(this,t)},error:function(s){e.error&&e.error.call(this,s)}})},t});