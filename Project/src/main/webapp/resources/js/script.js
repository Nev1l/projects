var sendPost = function(action,id) {
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

var selectOption = function(obj,id){
	document.getElementById(id).value=obj.checked;
};

var memberDelete = function(action,id,employee_id){
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

var selectAssignee = function (value) {
    var selectBox = document.getElementById("selectAssigneeBox");
    var assigneeField = document.getElementById("assignee");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    assigneeField.value = selectedValue;
};