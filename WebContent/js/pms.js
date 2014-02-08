jQuery(document).ready(function($) {

	/*------------------------------------
	 * Login
	 ------------------------------------*/
	/*
	 * $( "#lgn_form" ).submit(function(e){ e.preventDefault();
	 * $('#fancybox-loading').show(); var posting = $.post( 'login', $( this
	 * ).serialize()); posting.done(function( data ) { console.log(data);
	 * alert(data['msgtype']); if(typeof data['msgtype']==='undefined') {
	 * $(document).empty().text(data); return false; }
	 * $('#msg_txt').empty().text(data['msg']);
	 * $('#msg_div').removeClass(data['msgtype']).addClass(data['msgtype']);
	 * $('#msg_div').fadeIn(); $('#fancybox-loading').hide(); }); return false;
	 * });
	 */
	/*----------------------------------------
	 * Menu
	 -----------------------------------------*/
	$(document).on('click', '#upld_menu', function(e) {
		e.preventDefault();
		clearDr();
		$('#upld_div').slideDown();
		return false;
	});

	$(document).on('click', '#home_nav', function(e) {
		e.preventDefault();
		clearDr();
		$('#menu').slideDown();
	});
	/*-------------------------------------
	 * Upload Prescription
	 --------------------------------------*/
	$("#upldrx").submit(function(e) {
		e.preventDefault();
		$('#fancybox-loading').show();
		$.ajax({
			url : $(this).attr('action'),
			type : 'POST',
			data : new FormData(this),
			mimeType : "multipart/form-data",
			contentType : false,
			cache : false,
			processData : false,
			dataType:'json',
			success : function(data, textStatus, jqXHR) {
				console.log(data);
				var msgtype = data['msgtype'];
				var msg = data['msg'];
				var msgDiv = $(createMsg(msgtype, msg));
				console.log(msgDiv);
				msgDiv.insertBefore($('#upldrx')).show();
				$('#upldrx').reset();
				$('#preview').attr('src', '').hide();
				$('#fancybox-loading').hide();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				var msgDiv = createMsg(data['msgtype'], data['msg']);
				msgDiv.insertBefore($(this).html()).show();
				$('#fancybox-loading').hide();
			}

		});

	});

	/*-------------------------------------
	 * Prescription preview
	 --------------------------------------*/
	$("#rx").change(function() {
		readImageURL(this);
	});

});

function readImageURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#preview').attr('src', e.target.result).show().parent().show();

		};

		reader.readAsDataURL(input.files[0]);
	}
}

function clearDr() {
	$('#content').children().hide();
	$('form').each(function() {
		this.reset();
	});
	$('#preview').attr("src", '').hide();
}

function createMsg(msgtype, msg) {
	var icon = msgtype;
	if (msgtype === 'success') {
		icon = 'ok';
	} else if (msgtype === 'error') {
		icon = 'remove';
	}
	return '<div id="msg_div" class="notice ' + msgtype + '">'
			+ '<i class="icon-' + icon + '-sign icon-large"></i>'
			+ '<div id="msg_txt">' + msg + '</div></div>';
}