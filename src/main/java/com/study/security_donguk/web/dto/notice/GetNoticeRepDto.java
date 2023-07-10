package com.study.security_donguk.web.dto.notice;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetNoticeRepDto {

	private int noticeCode;
	private String noticeTitle;
	private int userCode;
	private String userId;
	private int noticeCount;
	private String noticeContent;
	private String createDate;
	private List<Map<String, Object>> downloadFiles;
	
}
