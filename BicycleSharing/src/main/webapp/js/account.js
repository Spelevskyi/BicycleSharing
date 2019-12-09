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
	url = url.split("\\");
	path = "./image/" + url[2];
	document.getElementById('ImagePathId').value = path;
	document.getElementById("formId").submit();
});