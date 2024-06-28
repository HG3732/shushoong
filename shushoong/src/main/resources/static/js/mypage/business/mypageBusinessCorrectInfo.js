$(loadedHandler);

function loadedHandler() {
	$(".btn.save").on("click", saveInfoHandler);
	$(".btn.secession").on("click", deleteAccHandler);
	$(".pop-btn.cancle").on("click", cancleHandler);
	$(".close_btn").on("click", cancleHandler);
}

function saveInfoHandler() {
	var userPwd = $("#userPwd").val();
	var passwordChk = $("#passwordChk").val();
	var pwd_text = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;
	
	if(pwd_text.test(userPwd)) {
		alert("형식에 맞지 않은 비밀번호입니다.");
		$("#userPwd").val('');
		$("#passwordChk").val('');
		return false;
	} else if (userPwd != passwordChk) {
		alert("비밀번호와 비밀번호 확인란이 일치하지 않습니다. 다시 확인해 주세요.");
		$("#userPwd").val('');
		$("#passwordChk").val('');
		return false;
	} else {
		$.ajax({
			type: "post",
			url: "/shushoong/business/changePwd.ajax",
			data: {userPwd:userPwd},
			success:function(request,status,error) {
				alert("비밀번호가 변경되었습니다.");
			}, error:function(request,status,error) {
				alert("비밀번호 변경에 실패하였습니다.");
			}
		})
	}
}

function deleteAccHandler() {
	$("#popup").hide().fadeIn();
}

function cancleHandler() {
	$("#popup").fadeOut();
}