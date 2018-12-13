package com.javalec.spring.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.javalec.spring.dao.BDao;
import com.javalec.spring.dto.BDto;

public class BListService implements BService {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		
		model.addAttribute("list", dtos);
		
	}

}
