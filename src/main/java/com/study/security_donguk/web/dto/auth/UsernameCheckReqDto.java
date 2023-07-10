package com.study.security_donguk.web.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UsernameCheckReqDto {
	@NotBlank
	@Size(max = 16, min = 4)
	private String username;
	
}
