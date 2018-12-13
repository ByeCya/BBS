package reply;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bbs.Bbs;

public class ReplyDAO {

	private Connection conn;
	private ResultSet rs;
	
	public ReplyDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS?serverTimezone=UTC";
			String dbID = "root";
			String dbPassword = "root";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 조회 SQL (글 번호대로 내림차순)
	public int getNext() {
		String SQL = "SELECT replyID FROM REPLY";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;	// 첫 게시물인 경우
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	// 데이터베이스 오류
	}
	
	//댓글 내용 불러오는 함수
	public Reply getReply() {
		String SQL = "SELECT * FROM REPLY";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			//pstmt.setInt(1, replyID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Reply reply = new Reply();
				reply.setReplyID(rs.getInt(1));
				reply.setReplyBbs(rs.getInt(2));
				reply.setReplyUser(rs.getString(3));
				reply.setReplyContent(rs.getString(4));
				reply.setReplyDate(rs.getString(5));
				reply.setReplyAvailable(rs.getInt(6));
				return reply;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 페이징 처리를 위한 함수1
	public ArrayList<Reply> getList(int pageNumber, int bbsID) {
		String SQL = "SELECT * FROM REPLY WHERE replyID < ? AND replyAvailable = 1 AND replyBbs = ? ORDER BY replyID DESC LIMIT 10";
		ArrayList<Reply> list = new ArrayList<Reply>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			pstmt.setInt(2, bbsID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Reply reply = new Reply();
				reply.setReplyID(rs.getInt(1));
				reply.setReplyBbs(rs.getInt(2));
				reply.setReplyUser(rs.getString(3));
				reply.setReplyContent(rs.getString(4));
				reply.setReplyDate(rs.getString(5));
				reply.setReplyAvailable(rs.getInt(6));
				list.add(reply);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 댓글 작성SQL
		public int write(int replyBbs, String replyUser, String replyContent) {
			String SQL = "INSERT INTO REPLY VALUES (0, ?, ?, ?, NOW(), 1)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, replyBbs);
				pstmt.setString(2, replyUser);
				pstmt.setString(3, replyContent);
				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return -1;
		}
}
