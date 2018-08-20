package score.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import score.bean.ScoreDTO;

public class ScoreDAO {
	// JDBC 관련 변수
		private Connection conn = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		// SQL 명령어들
		private final String SCORE_INSERT = 
				"insert into score values (?, ?, ?, ?, ?, ?, ?, sysdate)";
		private final String SCORE_UPDATE = 
				"update score set kor=?, eng=?, mat=?, tot=?, avg=? where studNo=?";
		private final String SCORE_DELETE = "delete score where studNo=?";
		private final String SCORE_GET = "select * from score where studNo=?";
		private final String SCORE_LIST = "select * from "
							+ " (select rownum rn, tt.* from "
							+ " (select * from score order by studNo asc) tt) "
							+ " where rn>=? and rn<=?";
		private final String SCORE_COUNT = "select count(*) from score";
		
		private String driver = "oracle.jdbc.driver.OracleDriver";
		private String url = "jdbc:oracle:thin:@localhost:1521:xe";
		private String username = "jspexam";
		private String password = "m1234";
		
		public ScoreDAO() {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		public Connection getConnection() {
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}
		
		public void close() {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// CRUD 기능의 메소드 구현
		// 성적 등록
		public int insertScore(ScoreDTO scoreDTO) {
			int result = 0;
			conn = getConnection();
			try {
				pstmt = conn.prepareStatement(SCORE_INSERT);
				pstmt.setString(1, scoreDTO.getStudNo());
				pstmt.setString(2, scoreDTO.getName());
				pstmt.setInt(3, scoreDTO.getKor());
				pstmt.setInt(4, scoreDTO.getEng());
				pstmt.setInt(5, scoreDTO.getMat());
				pstmt.setInt(6, scoreDTO.getTot());
				pstmt.setDouble(7, scoreDTO.getAvg());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			return result;
		}
		// 성적 수정
		public int updateScore(ScoreDTO scoreDTO) {
			int result = 0;
			conn = getConnection();
			try {
				pstmt = conn.prepareStatement(SCORE_UPDATE);			
				pstmt.setInt(1, scoreDTO.getKor());
				pstmt.setInt(2, scoreDTO.getEng());
				pstmt.setInt(3, scoreDTO.getMat());
				pstmt.setInt(4, scoreDTO.getTot());
				pstmt.setDouble(5, scoreDTO.getAvg());
				pstmt.setString(6, scoreDTO.getStudNo());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			return result;
		}
		// 성적 삭제
		public int deleteScore(ScoreDTO scoreDTO) {
			int result = 0;
			conn = getConnection();
			try {
				pstmt = conn.prepareStatement(SCORE_DELETE);			
				pstmt.setString(1, scoreDTO.getStudNo());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			return result;
		}
		// 성적 상세 조회
		public ScoreDTO getScore(ScoreDTO scoreDTO1) {
			ScoreDTO scoreDTO = null;
			conn = getConnection();
			try {
				pstmt = conn.prepareStatement(SCORE_GET);			
				pstmt.setString(1, scoreDTO1.getStudNo());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					scoreDTO = new ScoreDTO();
					scoreDTO.setStudNo(rs.getString("studNo"));
					scoreDTO.setName(rs.getString("name"));
					scoreDTO.setKor(rs.getInt("kor"));
					scoreDTO.setEng(rs.getInt("eng"));
					scoreDTO.setMat(rs.getInt("mat"));
					scoreDTO.setTot(rs.getInt("tot"));
					scoreDTO.setAvg(rs.getDouble("avg"));
					scoreDTO.setLogtime(rs.getString("logtime"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			return scoreDTO;
		}
		// 성적 목록 조회
		public List<ScoreDTO> getScoreList(int startNum, int endNum) {
			List<ScoreDTO> list = new ArrayList<>();
			conn = getConnection();
			try {
				pstmt = conn.prepareStatement(SCORE_LIST);
				pstmt.setInt(1, startNum);
				pstmt.setInt(2, endNum);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					ScoreDTO scoreDTO = new ScoreDTO();
					scoreDTO.setStudNo(rs.getString("studNo"));
					scoreDTO.setName(rs.getString("name"));
					scoreDTO.setKor(rs.getInt("kor"));
					scoreDTO.setEng(rs.getInt("eng"));
					scoreDTO.setMat(rs.getInt("mat"));
					scoreDTO.setTot(rs.getInt("tot"));
					scoreDTO.setAvg(rs.getDouble("avg"));
					scoreDTO.setLogtime(rs.getString("logtime"));
					list.add(scoreDTO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			return list;
		}
		
		public int selectListCount() {
			int listCount = 0;
			conn = getConnection();
			try {
				pstmt = conn.prepareStatement(SCORE_COUNT);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return listCount;
		}
}














