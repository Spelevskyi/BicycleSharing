$(document).ready(function() {
	$('#checkBoxAll').click(function() {
		if ($(this).is(":checked"))
			$('.chkCheckBoxId').prop('checked', true);
		else
			$('.chkCheckBoxId').prop('checked', false);
	});
});