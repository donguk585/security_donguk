package com.study.security_donguk.service.notice;

import java.util.List;

import com.study.security_donguk.web.dto.notice.AddNoticeReqDto;
import com.study.security_donguk.web.dto.notice.GetNoticeListRespDto;
import com.study.security_donguk.web.dto.notice.GetNoticeRepDto;

public interface NoticeService {
	public int addNotice(AddNoticeReqDto addNoticeReqDto) throws Exception;
	
	public GetNoticeRepDto getNotice(String flag, int noticeCode) throws Exception;
	
	public List<GetNoticeListRespDto> getNoticeList(int page, String searchFlag, String searchValue) throws Exception;
}
