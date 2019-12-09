$('input[name=toggle]:checked').val();
$(document).ready(function() {
	$('#checkBoxAll').click(function() {
		if ($(this).is(":checked"))
			$('.chkCheckBoxId').prop('checked', true);
		else
			$('.chkCheckBoxId').prop('checked', false);
	});
});

$('#change_billing').on('show.bs.modal', function(e) {
	var id = $(e.relatedTarget).data('id');
	$(e.currentTarget).find('input[name="id"]').val(id);
});