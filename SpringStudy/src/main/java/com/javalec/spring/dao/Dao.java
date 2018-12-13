package com.javalec.spring.dao;

import java.util.ArrayList;

import com.javalec.spring.dto.BDto;
import com.javalec.spring.service.BPage;

public interface Dao {

	ArrayList<BDto> list(BPage bpage);
	public void write(String bName, String bTitle, String bContent);
	public BDto contentView(String bId);
	public void upHit(String bId);
	public void modify(String bId, String bName, String bTitle, String bContent);
	public BDto reply_view(String bId);
	public void reply(String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent);
	public void replyShape(String strGroup, String strStep);
	public void delete(String bId);
	public int page();
	
}
