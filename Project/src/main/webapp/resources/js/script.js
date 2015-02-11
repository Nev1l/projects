var load = function() {
	var completeTable = document.getElementById("complete-table");
	var initRequest = function() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	};
	var appendActivity = function(id, date, firstName, lastName, comment) {
		var row = document.createElement("tr");
		var cell = document.createElement("td");

		/*
		 * cell.appendChild(document.createTextNode(id+" "));
		 * row.appendChild(cell); completeTable.appendChild(row); row =
		 * document.createElement("tr"); cell = document.createElement("td");
		 */
		cell.appendChild(document.createTextNode(date + " "));
		row.appendChild(cell);
		completeTable.appendChild(row);

		var linkElement = document.createElement("a");
		linkElement.setAttribute("href", "#");
		linkElement.setAttribute("onClick",
				"sendPost('/project/userprofile.do','" + id + "')");
		linkElement.appendChild(document.createTextNode(firstName + " "
				+ lastName + " "));

		cell = document.createElement("td");
		cell.appendChild(linkElement);
		row.appendChild(cell);
		completeTable.appendChild(row);

		cell = document.createElement("td");
		cell.appendChild(document.createTextNode(comment + " "));
		row.appendChild(cell);
		completeTable.appendChild(row);
	};

	var clearTable = function() {
		// >1 >=2 (now if > 0 ... loop >= 1 ?0)
		var count = completeTable.getElementsByTagName("tr").length;
		console.log("clear table(total=" + count + ")");
		if (count > 1) {
			for (var loop = completeTable.childNodes.length - 1; loop >= 2; loop--) {
				var elem = completeTable.childNodes[loop];
				console.log(elem.outerHTML);
				completeTable.removeChild(elem);
			}
		}
	};
	var parseMessages = function(responseXML) {
		if (responseXML == null) {
			return false;
		} else {
			var activities = responseXML.getElementsByTagName("activities")[0];
			console.log(activities.tagName + " count="
					+ activities.childNodes.length);
			if (activities.childNodes.length > 0) {
				for (var loop = 0; loop < activities.childNodes.length; loop++) {
					var activity = activities.childNodes[loop];
					// console.log("activity"+activity.innerHTML);
					var id = activity.getElementsByTagName("id")[0];
					var date = activity.getElementsByTagName("date")[0];
					var firstName = activity.getElementsByTagName("firstName")[0];
					var lastName = activity.getElementsByTagName("lastName")[0];
					var comment = activity.getElementsByTagName("comment")[0];
					// console.log("id=" + id.innerHTML);
					// console.log("date=" + date.innerHTML);
					// console.log("firstName=" + firstName.innerHTML);
					// console.log("lastName=" + lastName.innerHTML);
					// console.log("comment=" + comment.innerHTML);
					appendActivity(id.innerHTML, date.innerHTML,
							firstName.innerHTML, lastName.innerHTML,
							comment.innerHTML);
				}
			}
		}
	};
	var callback = function() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				clearTable();
				var parser = new DOMParser();
				var xmlDoc = parser.parseFromString(req.responseText,
						"application/xml");
				parseMessages(xmlDoc);
			}
		}
	};
	var req;
	return function() {
		req = initRequest();
		var countInput = document.getElementById("count");
		countInput.value = +countInput.value + 5;
		var url = "/project/activity.do?count=" + countInput.value;
		req.open("GET", url, true);
		req.setRequestHeader('Content-Type', 'text/xml');// commend
		req.onreadystatechange = callback;
		req.send(null);
	}();
};

// =================[Form angular]=====================
// check urls names project or projects & etc
/*
 * var qweqweqwe = function() { $.ajax({ type : "post", url :
 * "/project/ajax/projects", contentType : "application/json", data :
 * JSON.stringify(jsonobject), success : function(result) {
 * console.log("result:"+result); }, error : function() { alert('Error: Data
 * does not load!'); } }); };
 */
// ======================================================
var sendTasksPageReq = function(page) {
	console.log("================sendTasksPageReq=============");
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", "/project/tasks.do");
	var pageField = document.createElement("input");
	pageField.setAttribute("type", "hidden");
	pageField.setAttribute("name", "page");
	pageField.setAttribute("value", page);
	console.log("page="+page);
	form.appendChild(pageField);
	var e = document.getElementById("status");
	var statusValue = e.options[e.selectedIndex].value;
	if (statusValue !== 'All') {
		console.log("statusValue="+statusValue);
		var filterStatus = document.createElement("input");
		filterStatus.setAttribute("type", "hidden");
		filterStatus.setAttribute("name", "fs");
		filterStatus.setAttribute("value", statusValue);
		form.appendChild(filterStatus);
	}
	e = document.getElementById("project");
	var projectValue = e.options[e.selectedIndex].value;
	if (projectValue !== 'All') {
		console.log("projectValue="+projectValue);
		var filterProject = document.createElement("input");
		filterProject.setAttribute("type", "hidden");
		filterProject.setAttribute("name", "fp");
		filterProject.setAttribute("value", projectValue);
		form.appendChild(filterProject);
	}
	var taskNameValue = document.getElementById("taskName").value;
	if (taskNameValue.length > 0) {
		console.log("taskNameValue="+taskNameValue);
		var filterTaskName = document.createElement("input");
		filterTaskName.setAttribute("type", "hidden");
		filterTaskName.setAttribute("name", "ftn");
		filterTaskName.setAttribute("value", taskNameValue);
		form.appendChild(filterTaskName);
	}
	document.body.appendChild(form);
	form.submit();
};

var sendPost = function(action, id) {
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", action);
	var hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "id");
	hiddenField.setAttribute("value", id);
	form.appendChild(hiddenField);
	document.body.appendChild(form);
	form.submit();
};

var selectOption = function(obj, id) {
	document.getElementById(id).value = obj.checked;
};

var memberDelete = function(action, id, employee_id) {
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", action);
	var hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "id");
	hiddenField.setAttribute("value", id);
	form.appendChild(hiddenField);
	hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "employee_id");
	hiddenField.setAttribute("value", employee_id);
	form.appendChild(hiddenField);
	document.body.appendChild(form);
	form.submit();
};

var selectAssignee = function(value) {
	var selectBox = document.getElementById("selectAssigneeBox");
	var assigneeField = document.getElementById("assignee");
	var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	assigneeField.value = selectedValue;
};