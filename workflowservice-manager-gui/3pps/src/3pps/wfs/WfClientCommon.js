define('3pps/wfs/WfClientCommon', [], function(WfClientCommon) {

	/**
	 * The wfs.WfClientCommon package provides common functions for WorkflowService client library.
	 *
	 * TODO: unit + acceptance tests
	 *
	 * @class wfs.WfClientCommon
	 */
	var WfClientCommon = {};

	WfClientCommon.extractFormInfo = function(formKey) {
		// expected format - example: "embedded:app:json:model1:/some/path/MyForm.json

		var parts = formKey.split(':');
		if (parts.length != 5 && parts.length != 4) {
			throw new Error("Invalid form key: " + formKey + ", only " + parts.length + " parts");
		}

		if (parts.length == 5){
			if (parts[0] != "embedded" && parts[1] != "app") {
				throw new Error("Invalid form key: " + formKey + ", missing expected prefix: embedded:app");
			}

			var formInfo = { format: parts[2], modelType: parts[3], path: parts[4]};

			return formInfo;
		}

		if (parts.length == 4){
			if (parts[0] != "embedded" && parts[1] != "app") {
				throw new Error("Invalid form key: " + formKey + ", missing expected prefix: embedded:app");
			}

			var formInfo = { format: parts[2], path: parts[3]};

			return formInfo;
		}
	}

	return WfClientCommon;

});