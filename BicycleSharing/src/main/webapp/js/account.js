$("#profileImage").click(function(e) {
	$("#imageUpload").click();
});

function fasterPreview(uploader) {
	if (uploader.files && uploader.files[0]) {
		var path = window.URL.createObjectURL(uploader.files[0]);
		$('#profileImage').attr('src', path);
	}
}

$("#imageUpload").change(function() {
	fasterPreview(this);
	var path, url;
	url = this.value;
	alert(url);
	url = url.split("\\");
	path = "../" + url[8] + "/" + url[9];
	alert(path);
	document.getElementById('ImagePathId').value = path;
	document.getElementById("formId").submit();
});