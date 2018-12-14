package com.javalec.spring.dao;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstarctDAO {
	protected Log log = LogFactory.getLog(AbstarctDAO.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	protected void printQueryId(String queryId) {
		if(log.isDebugEnabled()) {
			log.debug("\t QueryId	\t: " + queryId);
		}
	}
	
}
