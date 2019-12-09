$('input[type=hidden]:checked').val();
$(document).ready(function() {
	$('#checkBoxAll').click(function() {
		if ($(this).is(":checked"))
			$('.chkCheckBoxId').prop('checked', true);
		else
			$('.chkCheckBoxId').prop('checked', false);
	});
});

$("#searchImage").click(function(e) {
	$("#uploadImage").click();
});

function fasterPreview(uploader) {
	if (uploader.files && uploader.files[0]) {
		var path = window.URL.createObjectURL(uploader.files[0]);
	}
}

$("#uploadImage").change(function() {
	fasterPreview(this);
	var path, url;
	url = this.value;
	url = url.split("\\");
	path = "./image/" + url[2];
	document.getElementById('imagePath').value = path;
});


$('#change_bicycle').on('show.bs.modal', function(e) {
	var id = $(e.relatedTarget).data('id');
	$(e.currentTarget).find('input[name="id"]').val(id);
});