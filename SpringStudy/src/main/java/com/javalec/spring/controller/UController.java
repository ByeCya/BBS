package com.javalec.spring.controller;

import java.io.WriteAbortedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javalec.spring.dao.UDao;
import com.javalec.spring.dto.UDto;

@Controller
public class UController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/login_view")
	public String login_view() {
		System.out.println("login_view()");
		
		return "login_view";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		System.out.println("login()");
		
		UDao dao = sqlSession.getMapper(UDao.class);
		UDto dto = dao.login(request.getParameter("userID"));
		if (dto == null) {
			System.out.println("dto null");
			System.out.println("데이터베이스가 존재하지 않습니다.");
			return "login_view";
		} else if(!request.getParameter("userID").equals(dto.getUserID())) {
			System.out.println("아이디 존재하지 않습니다.");
			return "login_view";
		} else if (!request.getParameter("userPassword").equals(dto.getUserPassword())) {
			System.out.println("비밀번호가 존재하지 않습니다.");
			return "login_view";
		} else {
			HttpSession session = request.getSession();
			System.out.println("로그인 성공");
			session.setAttribute("user",dto);
			System.out.println(session.getAttribute("user"));
			return "redirect:list";
		}
	}
	
	@RequestMapping("/join_view")
	public String join_view() {
		System.out.println("join_view()");
		
		return "join_view";
	}
	
	@RequestMapping("/join")
	public String join(HttpServletRequest request) {
		System.out.println("join()");
		
		UDao dao = sqlSession.getMapper(UDao.class);
		dao.join(request.getParameter("userID"), request.getParameter("userPassword"), request.getParameter("userName"), request.getParameter("userGender"), request.getParameter("userEamil"));
		return "redirect:list";
	}
}
