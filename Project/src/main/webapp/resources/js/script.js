var load = function() {
	var completeTable = document.getElementById("complete-table");
	var initRequest = function() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	};
	var appendActivity = function(id,date, firstName, lastName, comment) {
		var linkElement;
		var row = document.createElement("tr");
		var cell = document.createElement("td");
		
		linkElement = document.createElement("a");
		linkElement.setAttribute("href", "#");
		// linkElement.setAttribute("onClick","sendPost('/project/taskNew.do','"+composerId+"')");
		linkElement.appendChild(document.createTextNode(firstName + " "
				+ lastName+" "));

		cell.appendChild(document.createTextNode(id+" "));
		cell.appendChild(document.createTextNode(date+" "));
		cell.appendChild(linkElement);
		cell.appendChild(document.createTextNode(comment+" "));
		
		row.appendChild(cell);
		completeTable.appendChild(row);
	};

	var clearTable = function() {
		//>1 >=1 (now if > 0  ... loop >= 0)
		if (completeTable.getElementsByTagName("tr").length > 0) {
			for (var loop = completeTable.childNodes.length - 1; loop >= 0; loop--) {
				completeTable.removeChild(completeTable.childNodes[loop]);
			}
		}
	};
	var parseMessages = function(responseXML) {
		if (responseXML == null) {
			return false;
		} else {
			var activities = responseXML.getElementsByTagName("activities")[0];
			console.log(activities.tagName);
			console.log("activities count=" + activities.childNodes.length);
			if (activities.childNodes.length > 0) {
				for (var loop = 0; loop < activities.childNodes.length; loop++) {
					var activity = activities.childNodes[loop];
					console.log("activity"+activity.innerHTML);
					var id = activity.getElementsByTagName("id")[0];
					var date = activity.getElementsByTagName("date")[0];
					var firstName = activity.getElementsByTagName("firstName")[0];
					var lastName = activity.getElementsByTagName("lastName")[0];
					var comment = activity.getElementsByTagName("comment")[0];
					console.log("id=" + id.innerHTML);
					console.log("date=" + date.innerHTML);
					console.log("firstName=" + firstName.innerHTML);
					console.log("lastName=" + lastName.innerHTML);
					console.log("comment=" + comment.innerHTML);
					appendActivity(id.innerHTML,date.innerHTML, firstName.innerHTML,
							lastName.innerHTML, comment.innerHTML);
				}
			}
		}
	};
	var callback = function() {
		clearTable();
		if (req.readyState == 4) {
			if (req.status == 200) {
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
		var url = "/project/activity.do?count=" + countInput.value;
		countInput.value = +countInput.value + 10;
		req.open("GET", url, true);
		req.setRequestHeader('Content-Type', 'text/xml');// commend
		req.onreadystatechange = callback;
		req.send(null);
	}();
};

// ======================================================
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