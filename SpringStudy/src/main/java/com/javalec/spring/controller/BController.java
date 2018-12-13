package com.javalec.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.javalec.spring.dao.Dao;
import com.javalec.spring.dto.BDto;
import com.javalec.spring.dto.UDto;
import com.javalec.spring.service.*;
import com.javalec.spring.util.Constant;

@Controller
public class BController {
	
	//private JdbcTemplate template;

	@Autowired
	private SqlSession sqlSession;
	
//	@Autowired
//	public void setTemplate(JdbcTemplate template) {
//		this.template = template;
//		Constant.template = this.template;
//	}

	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest request) {
		System.out.println("list()");
		//BService command =  new BListService();;
		//command.execute(model);
		String pg = request.getParameter("page");
		int page = 0;
		if (pg == null) page =  0;
		else {
			try {
				page = Integer.parseInt(pg);
			} catch (Exception e) {
				page = 1;
			}
		}

		BPage bpage = new BPage();
		bpage.setPage(page);
		bpage.setPageNum(10);
		bpage.getPageStart();
		
		Dao dao = sqlSession.getMapper(Dao.class);
		model.addAttribute("list", dao.list(bpage));
		model.addAttribute("totalCount", (int) Math.ceil((float)dao.page() / 10));
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write()");
//		model.addAttribute("request", request);
//		BService command =  new BWriteService();;
//		command.execute(model);

		Dao dao = sqlSession.getMapper(Dao.class);
		dao.write(request.getParameter("bName"), request.getParameter("bTitle"), request.getParameter("bContent"));
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view()");
		
//		model.addAttribute("request", request);
//		BService command = new BContentService();
//		command.execute(model);
		
		Dao dao = sqlSession.getMapper(Dao.class);
		dao.upHit(request.getParameter("bId"));
		BDto dto = dao.contentView(request.getParameter("bId"));
		model.addAttribute("content_view", dto);
		
		return "content_view";
	}
	
	@RequestMapping(value = "/modify", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modify()");
		
//		model.addAttribute("request", request);
//		BService command = new BModifyService();
//		command.execute(model);
		
		Dao dao = sqlSession.getMapper(Dao.class);
		dao.modify(request.getParameter("bId"), request.getParameter("bName"), request.getParameter("bTitle"), request.getParameter("bContent"));
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view()");
		
//		model.addAttribute("request", request);
//		BService command = new BReplyViewService();
//		command.execute(model);
		
		Dao dao = sqlSession.getMapper(Dao.class);
		BDto dto = dao.reply_view(request.getParameter("bId"));
		model.addAttribute("reply_view", dto);
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply()");
		
//		model.addAttribute("request", request);
//		BService command = new BReplyService();
//		command.execute(model);
		
		Dao dao = sqlSession.getMapper(Dao.class);
		dao.reply(request.getParameter("bName"), request.getParameter("bTitle"), request.getParameter("bContent"), request.getParameter("bGroup"), request.getParameter("bStep"), request.getParameter("Indent"));
		dao.replyShape(request.getParameter("bGroup"), request.getParameter("bStep"));
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		
//		model.addAttribute("request", request);
//		BService command = new BDeleteService();
//		command.execute(model);
		
		Dao dao = sqlSession.getMapper(Dao.class);
		dao.delete(request.getParameter("bId"));
		
		return "redirect:list";
	}
}
