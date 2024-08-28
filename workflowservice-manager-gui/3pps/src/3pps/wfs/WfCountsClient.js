define('3pps/wfs/WfCountsClient',[
    'jscore/core',
    "jscore/ext/mvp",
    'jscore/ext/net',
    "3pps/wfs/WfClientCommon",
], function(core, mvp, net, WfClientCommon) {
    
    /**
     * The wfs.WfCountsClient package provides WorkflowService workflow definition functionality for apps.
     *
     * TODO: unit + acceptance tests
     *
     * @class wfs.WfDefinitionClient
     */
    var WfCountsClient = {};

//   Overview-Definitions Count
    WfCountsClient.getDefinitionCount = function(options) {
		options = options || {};

        if (options.success == null) {
            throw new Error("success callback missing");
        }

        // Build URL
		var url = "/engine-rest/engine/default/process-definition/count";

        var numQueryParams = 0;

        // option: 'latest'
        if (options.latest != null) {
            url += "?latest=" + (options.latest ? "true" : "false");
            numQueryParams++;
        }

        // options: 'sortBy', 'sortOrder'
        if (options.sortBy != null) {
            if (["name"].indexOf(options.sortBy) == -1) {
                throw new Error("Invalid sortBy value: " + sortBy);
            }
            url += (numQueryParams > 0 ? "&" : "?") + "sortBy=" + options.sortBy;
            if (options.sortOrder != null) {
                if (["asc", "desc"].indexOf(options.sortOrder) == -1) {
                    throw new Error("Invalid sortOrder value: " + sortOrder);
                }
                url += "&sortOrder=" + options.sortOrder;
            }
        }

        // Retrieve collection from server
        var serverCollection = new mvp.Collection([], {url: url});
        serverCollection.fetch({
            success: function(serverCollection) {
				options.success(serverCollection.toJSON()[0].count);
			},
            error: function(errorThrown) {
            	if (options.error) {
					options.error.call(this, errorThrown);
            	}
            }
        });
    };
    
    //Overview-Instance Count
    WfCountsClient.getWfWorkflowInstancesCount = function(options) {
		options = options || {};

        if (options.success == null) {
            throw new Error("success callback missing");
        }

        // Build URL
		var url = "/engine-rest/engine/default/process-instance/count";

        var numQueryParams = 0;

        // option: 'active'
        if (options.active != null) {
            url += "?active=" + (options.active ? "true" : "false");
            numQueryParams++;
        }

        // options: 'sortBy', 'sortOrder'
        if (options.sortBy != null) {
//            if (["name"].indexOf(options.sortBy) == -1) {
//                throw new Error("Invalid sortBy value: " + options.sortBy);
//            }
            url += (numQueryParams > 0 ? "&" : "?") + "sortBy=" + options.sortBy;
            if (options.sortOrder != null) {
                if (["asc", "desc"].indexOf(options.sortOrder) == -1) {
                    throw new Error("Invalid sortOrder value: " + sortOrder);
                }
                url += "&sortOrder=" + options.sortOrder;
            }
        }

        // Retrieve collection from server
        var serverCollection = new mvp.Collection([], {url: url});
        serverCollection.fetch({
            success: function(serverCollection) {
				options.success(serverCollection.toJSON()[0].count);
			},
            error: function(errorThrown) {
            	if (options.error) {
					options.error.call(this, errorThrown);
            	}
            }
        });
    };
    
//    Overview-Task Count
    WfCountsClient.getWfActiveTasksCount = function(options) {
		options = options || {};

        if (options.success == null) {
            throw new Error("success callback missing");
        }

        // Build URL
		var url = "/engine-rest/engine/default/task/count";

        var numQueryParams = 0;

        // option: 'active'
        if (options.active != null) {
            url += "?active=" + (options.active ? "true" : "false");
            numQueryParams++;
        }

        // options: 'sortBy', 'sortOrder'
        if (options.sortBy != null) {
//            if (["name"].indexOf(options.sortBy) == -1) {
//                throw new Error("Invalid sortBy value: " + sortBy);
//            }
            url += (numQueryParams > 0 ? "&" : "?") + "sortBy=" + options.sortBy;
            if (options.sortOrder != null) {
                if (["asc", "desc"].indexOf(options.sortOrder) == -1) {
                    throw new Error("Invalid sortOrder value: " + sortOrder);
                }
                url += "&sortOrder=" + options.sortOrder;
            }
        }

        // Retrieve collection from server
        var serverCollection = new mvp.Collection([], {url: url});
        serverCollection.fetch({
            success: function(serverCollection) {
				options.success(serverCollection.toJSON()[0].count);
			},
            error: function(errorThrown) {
            	if (options.error) {
					options.error.call(this, errorThrown);
            	}
            }
        });
    };
   
///////////////////////////////////////////////////////////////////////////////////////////
    
//  Workflow Instance-Total
    WfCountsClient.getWorkflowInstanceTotal = function(options) {
		options = options || {};

        if (options.success == null) {
            throw new Error("success callback missing");
        }

        // Build URL
		var url = "/engine-rest/engine/default/process-instance/count";

        var numQueryParams = 0;

        // option: 'active'
        if (options.active != null) {
            url += "?active=" + (options.active ? "true" : "false");
            numQueryParams++;
        }
        
        if (options.suspended != null) {
            url += "&suspended=" + (options.suspended ? "true" : "false");
            numQueryParams++;
        }
        
        if (options.processDefinitionId != null) {
            url += "?processDefinitionId=" + options.processDefinitionId;
            numQueryParams++;
        }

        // options: 'sortBy', 'sortOrder'
        if (options.sortBy != null) {
//            if (["name"].indexOf(options.sortBy) == -1) {
//                throw new Error("Invalid sortBy value: " + sortBy);
//            }
            url += (numQueryParams > 0 ? "&" : "?") + "sortBy=" + options.sortBy;
            if (options.sortOrder != null) {
                if (["asc", "desc"].indexOf(options.sortOrder) == -1) {
                    throw new Error("Invalid sortOrder value: " + sortOrder);
                }
                url += "&sortOrder=" + options.sortOrder;
            }
        }

        // Retrieve collection from server
        var serverCollection = new mvp.Collection([], {url: url});
        serverCollection.fetch({
            success: function(serverCollection) {
				options.success(serverCollection.toJSON()[0].count);
			},
            error: function(errorThrown) {
            	if (options.error) {
					options.error.call(this, errorThrown);
            	}
            }
        });
    };
    
//    Workflow Instance-Active
    WfCountsClient.getWorkflowInstanceActive = function(options) {
		options = options || {};

        if (options.success == null) {
            throw new Error("success callback missing");
        }

        // Build URL
		var url = "/engine-rest/engine/default/process-instance/count";

        var numQueryParams = 0;

        // option: 'active'
        if (options.active != null) {
            url += "?active=" + (options.active ? "true" : "false");
            numQueryParams++;
        }
        
        if (options.suspended != null) {
            url += "&suspended=" + (options.suspended ? "true" : "false");
            numQueryParams++;
        }
        
        if (options.processDefinitionId != null) {
            url += "&processDefinitionId=" + options.processDefinitionId;
            numQueryParams++;
        }

        // options: 'sortBy', 'sortOrder'
        if (options.sortBy != null) {
            if (["name"].indexOf(options.sortBy) == -1) {
                throw new Error("Invalid sortBy value: " + sortBy);
            }
            url += (numQueryParams > 0 ? "&" : "?") + "sortBy=" + options.sortBy;
            if (options.sortOrder != null) {
                if (["asc", "desc"].indexOf(options.sortOrder) == -1) {
                    throw new Error("Invalid sortOrder value: " + sortOrder);
                }
                url += "&sortOrder=" + options.sortOrder;
            }
        }

        // Retrieve collection from server
        var serverCollection = new mvp.Collection([], {url: url});
        serverCollection.fetch({
            success: function(serverCollection) {
				options.success(serverCollection.toJSON()[0].count);
			},
            error: function(errorThrown) {
            	if (options.error) {
					options.error.call(this, errorThrown);
            	}
            }
        });
    };
    
//  Workflow Instance-Inactive
//    WfCountsClient.getWorkflowInstanceInactive = function(options) {
//		options = options || {};
//
//        if (options.success == null) {
//            throw new Error("success callback missing");
//        }
//
//        // Build URL
//		var url = "/engine-rest/engine/default/process-instance/count";
//
//        var numQueryParams = 0;
//
//        // option: 'active'
//        if (options.active != null) {
//            url += "?active=" + (options.active ? "true" : "false");
//            numQueryParams++;
//        }
//        
//        if (options.suspended != null) {
//            url += "&suspended=" + (options.suspended ? "true" : "false");
//            numQueryParams++;
//        }
//
//        // options: 'sortBy', 'sortOrder'
//        if (options.sortBy != null) {
//            if (["name"].indexOf(options.sortBy) == -1) {
//                throw new Error("Invalid sortBy value: " + sortBy);
//            }
//            url += (numQueryParams > 0 ? "&" : "?") + "sortBy=" + options.sortBy;
//            if (options.sortOrder != null) {
//                if (["asc", "desc"].indexOf(options.sortOrder) == -1) {
//                    throw new Error("Invalid sortOrder value: " + sortOrder);
//                }
//                url += "&sortOrder=" + options.sortOrder;
//            }
//        }
//
//        // Retrieve collection from server
//        var serverCollection = new mvp.Collection([], {url: url});
//        serverCollection.fetch({
//            success: function(serverCollection) {
//				options.success(serverCollection.toJSON()[0].count);
//			},
//            error: function(errorThrown) {
//            	if (options.error) {
//					options.error.call(this, errorThrown);
//            	}
//            }
//        });
//    };
    
//  Workflow Instance-Cancelled
    WfCountsClient.getWorkflowInstanceCancelled = function(options) {
		options = options || {};

        if (options.success == null) {
            throw new Error("success callback missing");
        }

        // Build URL
		var url = "/engine-rest/engine/default/process-instance/count";

        var numQueryParams = 0;
        
        if (options.active != null) {
            url += "?active=" + (options.active ? "true" : "false");
            numQueryParams++;
        }

        // option: 'suspended'
        if (options.suspended != null) {
            url += "&suspended=" + (options.suspended ? "true" : "false");
            numQueryParams++;
        }
        
        if (options.processDefinitionId != null) {
            url += "&processDefinitionId=" + options.processDefinitionId;
            numQueryParams++;
        }
        
        
        // options: 'sortBy', 'sortOrder'
        if (options.sortBy != null) {
            if (["name"].indexOf(options.sortBy) == -1) {
                throw new Error("Invalid sortBy value: " + sortBy);
            }
            url += (numQueryParams > 0 ? "&" : "?") + "sortBy=" + options.sortBy;
            if (options.sortOrder != null) {
                if (["asc", "desc"].indexOf(options.sortOrder) == -1) {
                    throw new Error("Invalid sortOrder value: " + sortOrder);
                }
                url += "&sortOrder=" + options.sortOrder;
            }
        }

        // Retrieve collection from server
        var serverCollection = new mvp.Collection([], {url: url});
        serverCollection.fetch({
            success: function(serverCollection) {
				options.success(serverCollection.toJSON()[0].count);
			},
            error: function(errorThrown) {
            	if (options.error) {
					options.error.call(this, errorThrown);
            	}
            }
        });
    };
  
///////////////////////////////////////////////////////////////////////////////////////////////
    
//  Tasks- Total
    WfCountsClient.getTasksTotal = function(options) {
		options = options || {};

        if (options.success == null) {
            throw new Error("success callback missing");
        }

        // Build URL
		var url = "/engine-rest/engine/default/task/count";

        var numQueryParams = 0;

        // option: 'active'
        if (options.active != null) {
            url += "?active=" + (options.active ? "true" : "false");
            numQueryParams++;
        }
        
        if (options.suspended != null) {
            url += "&suspended=" + (options.suspended ? "true" : "false");
            numQueryParams++;
        }

        // options: 'sortBy', 'sortOrder'
        if (options.sortBy != null) {
            if (["name"].indexOf(options.sortBy) == -1) {
                throw new Error("Invalid sortBy value: " + sortBy);
            }
            url += (numQueryParams > 0 ? "&" : "?") + "sortBy=" + options.sortBy;
            if (options.sortOrder != null) {
                if (["asc", "desc"].indexOf(options.sortOrder) == -1) {
                    throw new Error("Invalid sortOrder value: " + sortOrder);
                }
                url += "&sortOrder=" + options.sortOrder;
            }
        }

        // Retrieve collection from server
        var serverCollection = new mvp.Collection([], {url: url});
        serverCollection.fetch({
            success: function(serverCollection) {
				options.success(serverCollection.toJSON()[0].count);
			},
            error: function(errorThrown) {
            	if (options.error) {
					options.error.call(this, errorThrown);
            	}
            }
        });
    };
    
//    Tasks- Active
    
    WfCountsClient.getTasksActive = function(options) {
		options = options || {};

        if (options.success == null) {
            throw new Error("success callback missing");
        }

        // Build URL
		var url = "/engine-rest/engine/default/task/count";

        var numQueryParams = 0;

        // option: 'active'
        if (options.active != null) {
            url += "?active=" + (options.active ? "true" : "false");
            numQueryParams++;
        }
        
        if (options.suspended != null) {
            url += "&suspended=" + (options.suspended ? "true" : "false");
            numQueryParams++;
        }

        // options: 'sortBy', 'sortOrder'
        if (options.sortBy != null) {
            if (["name"].indexOf(options.sortBy) == -1) {
                throw new Error("Invalid sortBy value: " + sortBy);
            }
            url += (numQueryParams > 0 ? "&" : "?") + "sortBy=" + options.sortBy;
            if (options.sortOrder != null) {
                if (["asc", "desc"].indexOf(options.sortOrder) == -1) {
                    throw new Error("Invalid sortOrder value: " + sortOrder);
                }
                url += "&sortOrder=" + options.sortOrder;
            }
        }

        // Retrieve collection from server
        var serverCollection = new mvp.Collection([], {url: url});
        serverCollection.fetch({
            success: function(serverCollection) {
				options.success(serverCollection.toJSON()[0].count);
			},
            error: function(errorThrown) {
            	if (options.error) {
					options.error.call(this, errorThrown);
            	}
            }
        });
    };
    
//    Tasks- Inactive
    
//    WfCountsClient.getTasksInactive = function(options) {
//		options = options || {};
//
//        if (options.success == null) {
//            throw new Error("success callback missing");
//        }
//
//        // Build URL
//		var url = "/engine-rest/engine/default/task/count";
//
//        var numQueryParams = 0;
//
//        // option: 'active'
//        if (options.active != null) {
//            url += "?active=" + (options.active ? "true" : "false");
//            numQueryParams++;
//        }
//        
//        if (options.suspended != null) {
//            url += "&suspended=" + (options.suspended ? "true" : "false");
//            numQueryParams++;
//        }
//
//        // options: 'sortBy', 'sortOrder'
//        if (options.sortBy != null) {
//            if (["name"].indexOf(options.sortBy) == -1) {
//                throw new Error("Invalid sortBy value: " + sortBy);
//            }
//            url += (numQueryParams > 0 ? "&" : "?") + "sortBy=" + options.sortBy;
//            if (options.sortOrder != null) {
//                if (["asc", "desc"].indexOf(options.sortOrder) == -1) {
//                    throw new Error("Invalid sortOrder value: " + sortOrder);
//                }
//                url += "&sortOrder=" + options.sortOrder;
//            }
//        }
//
//        // Retrieve collection from server
//        var serverCollection = new mvp.Collection([], {url: url});
//        serverCollection.fetch({
//            success: function(serverCollection) {
//				options.success(serverCollection.toJSON()[0].count);
//			},
//            error: function(errorThrown) {
//            	if (options.error) {
//					options.error.call(this, errorThrown);
//            	}
//            }
//        });
//    };
    
//    Percentage Service
    
    WfCountsClient.getInstances2 = function(options) {
		options = options || {};

        if (options.success == null) {
            throw new Error("success callback missing");
        }

        // Build URL
		var url = "/workflowservice-ext/rest/progress-service/process-instance/";

        var numQueryParams = 0;
        
        if (options.workflowDefinitionId != null) {
            url += (options.workflowDefinitionId);
            numQueryParams++;
        }

        // Retrieve collection from server
        var serverCollection = new mvp.Collection([], {url: url});
        serverCollection.fetch({
            success: function(serverCollection) {
				options.success(serverCollection);
			},
            error: function(errorThrown) {
            	if (options.error) {
					options.error.call(this, errorThrown);
            	}
            }
        });
    };
    
//    Get Parent/Children/GrandChild   
    WfCountsClient.getAncestry  = function(options) {
		options = options || {};

        if (options.success == null) {
            throw new Error("success callback missing");
        }

        // Build URL
		var url = "/workflowservice-ext/rest/progress-service/process-instance/";

        var numQueryParams = 0;
        
        if (options.processInstanceId != null) {
            url += options.processInstanceId + "/ancestry";
            numQueryParams++;
        }

        // Retrieve collection from server
        var serverCollection = new mvp.Collection([], {url: url});
        serverCollection.fetch({
            success: function(serverCollection) {
				options.success(serverCollection);
			},
            error: function(errorThrown) {
            	if (options.error) {
					options.error.call(this, errorThrown);
            	}
            }
        });
    };
    
    WfCountsClient.getTasksCancelled = function(options) {
		options = options || {};

        if (options.success == null) {
            throw new Error("success callback missing");
        }

        // Build URL
		var url = "/engine-rest/engine/default/task/count";

        var numQueryParams = 0;
        
        if (options.active != null) {
            url += "?active=" + (options.active ? "true" : "false");
            numQueryParams++;
        }

        // option: 'suspended'
        if (options.suspended != null) {
            url += "&suspended=" + (options.suspended ? "true" : "false");
            numQueryParams++;
        }

        // options: 'sortBy', 'sortOrder'
        if (options.sortBy != null) {
            if (["name"].indexOf(options.sortBy) == -1) {
                throw new Error("Invalid sortBy value: " + sortBy);
            }
            url += (numQueryParams > 0 ? "&" : "?") + "sortBy=" + options.sortBy;
            if (options.sortOrder != null) {
                if (["asc", "desc"].indexOf(options.sortOrder) == -1) {
                    throw new Error("Invalid sortOrder value: " + sortOrder);
                }
                url += "&sortOrder=" + options.sortOrder;
            }
        }

        // Retrieve collection from server
        var serverCollection = new mvp.Collection([], {url: url});
        serverCollection.fetch({
            success: function(serverCollection) {
				options.success(serverCollection.toJSON()[0].count);
			},
            error: function(errorThrown) {
            	if (options.error) {
					options.error.call(this, errorThrown);
            	}
            }
        });
    };
    
	return WfCountsClient;
});    
