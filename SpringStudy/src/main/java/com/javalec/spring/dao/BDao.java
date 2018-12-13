package com.javalec.spring.dao;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;

import com.javalec.spring.dto.BDto;
import com.javalec.spring.util.Constant;

public class BDao {
	
	JdbcTemplate template;
	
	public BDao() {
		this.template = Constant.template;
	}
	
	public ArrayList<BDto> list() {

		String query = "SELECT bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent FROM BOARD ORDER BY bGroup DESC, bStep ASC";
		ArrayList<BDto> dtos = (ArrayList<BDto>) template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));
		return dtos;
	}
	
	public void write(final String bName, final String bTitle, final String bContent) {
		
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String query = "INSERT INTO BOARD SELECT 0, ?, ?, ?, NOW(), 0, MAX(bId), 0, 0 FROM BOARD";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				
				return pstmt;
			}
		});
	}
	
	private void upHit(final String bId) {

		String query = "UPDATE BOARD SET bHit = bHit + 1 WHERE bId = ?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(bId));
				
			}
		});
	}
	
	public BDto contentView(String strID) {
		
		upHit(strID);
		String query = "SELECT bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent FROM BOARD WHERE bId = " + strID;
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	
	public void modify(final String bId, final String bName, final String bTitle, final String bContent) {
		
		String query = "UPDATE BOARD SET bName = ?, bTitle = ?, bContent = ? WHERE bId = ?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setInt(4, Integer.parseInt(bId));
			}
		});
	}
	
	public void delete( final String strID) {
		
		String query = "DELETE FROM BOARD where bId = ?";
			
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, strID);
			}
		});
	}
	
	public BDto reply_view(String strID) {
		
		String query = "SELECT bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent FROM BOARD where bId = " + strID;
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	
	private void replyShape( final String strGroup, final String strStep) {

		String query = "update BOARD set bStep = bStep + 1 where bGroup = ? and bStep > ?";
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(strGroup));
				ps.setInt(2, Integer.parseInt(strStep));
			}
		});
	}
	
	public void reply(final String bName, final String bTitle, final String bContent, final String bGroup, final String bStep, final String bIndent) {
		
		replyShape(bGroup, bStep);
		
		String query = "INSERT INTO BOARD (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) VALUES (0, ?, ?, ?, ?, ?, ?)";
		
		template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bName);
				ps.setString(2, bTitle);
				ps.setString(3, bContent);
				ps.setInt(4, Integer.parseInt(bGroup));
				ps.setInt(5, Integer.parseInt(bStep) + 1);
				ps.setInt(6, Integer.parseInt(bIndent) + 1);
			}
		});
	}
	
}
