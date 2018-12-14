package com.javalec.spring.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter{
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object habdler) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("======================================          START         ======================================");
            log.debug(" Request URI \t:  " + request.getRequestURI());
		}
		return super.preHandle(request, response, habdler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object habdler, ModelAndView modelAndView) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("======================================           END          ======================================\n");
	    }
	}
}
