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