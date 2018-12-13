// mysql 접근 클래스
// DAO: 데이터베이스 접근 객체 (data access object)
// 회원 정보를 불러올 때 사용
// 데이터베이스에 회원 정보를 넣을때 사용

package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private Connection conn;	// 데이터베이스 접근 객체
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
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

	//값을 loginAction.jsp 페이지에 전달
	public int login(String userID, String userPassword) {
		String SQL = "SELECT AES_DECRYPT(UNHEX(userPassword), 'password') FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);	// SQL인젝션을 막기 위한 방법
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1;	// 로그인 성공
				}
				else
					return 0;	// 비밀번호 불일치
			}
			return -1;	// 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;	// 데이터베이스 오류
	}
	
	// 회원가입 기능 (회원가입이 완료될 경우 DB에 추가) // HEX(AES_ENCRYPT(?, 'password')): 암호화 (암호키: password)
	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES (?, HEX(AES_ENCRYPT(?, 'password')), ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;	// 데이터베이스 오류
	}
	
}
