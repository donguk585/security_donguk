const inputs = document.querySelectorAll("input");
const signupButton = document.querySelectorAll("button")[0];

let checkUsernameFlag = false;

inputs[2].onblur = () => {
    //아이디 중복확인
    $.ajax({
        async: false,
        type: "get",
        url: "/api/v1/auth/signup/validation/username",
        data: {username : inputs[2].value},
        dataType: "json",
        success: (response) => {
			checkUsernameFlag = response.data;
			if(checkUsernameFlag) {
				alert("사용가능한 아이디입니다.");
			} else{
				alert("이미 사용중인 아이디입니다.");
			}
        },
        error: (error) => {
           	if(error.status == 400) {
				   alert(JSON.stringify(error.responseJSON.data))
			   }else {
				   console.log("요청실패");
				   console.log(error);
			   }
			
        }
    })
}

signupButton.onclick = () => {
	let signupData = {
		name: inputs[0].value,
		email: inputs[1].value,
		username: inputs[2].value,
		password: inputs[3].value,
		"checkUsernameFlag": checkUsernameFlag
	}
	
	$.ajax({
		async: false,
		type: "post",
		url: "/api/v1/auth/signup",
		contentType: "application/json",
		data: JSON.stringify(signupData),
		dataType: "json",
		success: (response) => {
			if(response.data) {
				alert("회원가입 성공");
				location.replace("/auth/signin");
			}
		},
		error: (error) => {
           	if(error.status == 400) {
				   alert(JSON.stringify(error.responseJSON.data))
			   }else {
				   console.log("요청실패");
				   console.log(error);
			   }
		}
	})
}

