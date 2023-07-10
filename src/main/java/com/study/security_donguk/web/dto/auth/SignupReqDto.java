package com.study.security_donguk.web.dto.auth;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.study.security_donguk.domain.User.User;

import lombok.Data;

@Data
public class SignupReqDto {
	@NotBlank
	@Pattern(regexp = "^[가-힣]*$", message = "한글만 입력가능합니다.")
	private String name;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9]{4,12}$", message = "hi")
	private String username;
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&*_+=])[a-zA-Z\\d-~!@#$%^&*_+=]{8,16}$", message = "영문자, 특수문자를 모두 포함하여야합니다.")
	private String password;
	@AssertTrue(message = "아이디 중복확인이 되지 않았습니다.")
	private boolean checkUsernameFlag;
	
	public User toEntity() {
		return User.builder()
				.user_name(name)
				.user_email(email)
				.user_id(username)
				.user_password(new BCryptPasswordEncoder().encode(password))
				.user_roles("ROLE_USER")
				.build();

	}
}
