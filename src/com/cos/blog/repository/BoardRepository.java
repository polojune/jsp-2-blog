package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.User;

import com.cos.blog.db.DBConn;
import com.cos.blog.dto.BoardResponseDto;
import com.cos.blog.dto.DetailResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;

//DAO라고 칭하던 클래스
public class BoardRepository {

	private static final String TAG = "BoardRepository : ";
	private static BoardRepository instance = new BoardRepository();

	public BoardRepository() {
	}

	public static BoardRepository getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
    
	
	
	public List<Board> findAll(int page, String keyword) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT /*+ INDEX_DESC(BOARD SYS_C007932)*/id,");
		sb.append("userId, title, content, readCount, createDate ");
		sb.append("FROM board ");
		sb.append("WHERE title like ? OR content like ? ");
		sb.append("OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY");

		final String SQL = sb.toString();
		List<Board> boards = new ArrayList<>();

		try {
			conn = DBConn.getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, "%"+keyword+"%");
			psmt.setString(2, "%"+keyword+"%");
			psmt.setInt(3, page*3);
			// while 돌려서 rs -> java오브젝트에 집어넣기
			rs = psmt.executeQuery();
			while(rs.next()) {
				Board board = new Board(
						rs.getInt("id"),
						rs.getInt("userId"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getInt("readCount"),
						rs.getTimestamp("createDate")
				);
				boards.add(board);
			}

			return boards;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findAll(page,keyword) : "+e.getMessage());
		} finally {
			DBConn.close(conn, psmt, rs);
		}

		return null;
	}
	
	public int count(String keyword) {
		final String SQL = "SELECT count(*) FROM board WHERE title LIKE ? OR content LIKE ?";

		try {
			conn = DBConn.getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setString(1, "%"+keyword+"%");
			psmt.setString(2, "%"+keyword+"%");
			rs = psmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"count(keyword) : "+e.getMessage());
		} finally {
			DBConn.close(conn, psmt, rs);
		}

		return -1;
	}
	
	public int count() {
		final String SQL = "SELECT count(*) FROM board";

		try {
			conn = DBConn.getConnection();
			psmt = conn.prepareStatement(SQL);
			rs = psmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"count : "+e.getMessage());
		} finally {
			DBConn.close(conn, psmt, rs);
		}

		return -1;
	}
	
	public int updateReadCount(int id) {
		final String SQL 
		= "UPDATE board SET readCount = readCount + 1 WHERE id = ?";
		
		try {
			conn = DBConn.getConnection();
			psmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			psmt.setInt(1, id);
			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"updateReadCount : "+e.getMessage());
		} finally {
			DBConn.close(conn, psmt);
		}
		return -1;
	}

	public int save(Board board) {
		final String SQL = "INSERT INTO board(id,userId,title,content,readCount,createDate) VALUES (BOARD_SEQ.NEXTVAL, ?,?,?,?,SYSDATE)";
		try {
			conn = DBConn.getConnection();
			psmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			psmt.setInt(1, board.getUserId());
			psmt.setString(2, board.getTitle());
			psmt.setString(3, board.getContent());
			psmt.setInt(4, board.getReadCount());

			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " + e.getMessage());
		} finally {
			DBConn.close(conn, psmt);

		}
		return -1;
	}

	public int update(Board board) {
		final String SQL = "UPDATE board SET title = ?, content = ? WHERE id = ?";
		try {
			conn = DBConn.getConnection();
			psmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
            psmt.setString(1, board.getTitle());
            psmt.setString(2, board.getContent());
            psmt.setInt(3, board.getId());
			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "update : " + e.getMessage());
		} finally {
			DBConn.close(conn, psmt);

		}
		return -1;
	}

	public int deleteById(int id) {
		System.out.println("BoardRepository : id : "+id);
		final String SQL = "DELETE FROM board WHERE id = ?";
		try {
			conn = DBConn.getConnection();
			psmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
			psmt.setInt(1, id);
			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "deleteById : " + e.getMessage());
		} finally {
			DBConn.close(conn, psmt);

		}
		return -1;
	}
   
	public List<Board> findAll(int page) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT /*+ INDEX_DESC(BOARD SYS_C007932)*/id,");
		sb.append("userId, title, content, readCount, createDate ");
		sb.append("FROM board ");
		sb.append("OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY");

		final String SQL = sb.toString();
		List<Board> boards = new ArrayList<>();

		try {
			conn = DBConn.getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setInt(1, page*3);
			// while 돌려서 rs -> java오브젝트에 집어넣기
			rs = psmt.executeQuery();
			while(rs.next()) {
				Board board = new Board(
						rs.getInt("id"),
						rs.getInt("userId"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getInt("readCount"),
						rs.getTimestamp("createDate")
				);
				boards.add(board);
			}

			return boards;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"findAll(page) : "+e.getMessage());
		} finally {
			DBConn.close(conn, psmt, rs);
		}

		return null;
	}
	
	public List<Board> findAll() {
		final String SQL = "SELECT * FROM board ORDER BY id DESC";
		List<Board> boards = new ArrayList<>();
		try {
			conn = DBConn.getConnection();
			psmt = conn.prepareStatement(SQL);
			// 물음표 완성하기

			// while 돌려서 rs -> java 오브젝트 집어 넣기
            rs = psmt.executeQuery();
            while(rs.next()) {
            	  Board board = new Board(
            			        rs.getInt("id"),
            			        rs.getInt("userId"), 
            			        rs.getString("title"), 
            			        rs.getString("content"), 
            			        rs.getInt("readCount"), 
            			        rs.getTimestamp("createDate")
            			              			             			  
            			  );
            		 boards.add(board);	  
            			  
            }
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, psmt, rs);

		}
		return null;
	}

	public BoardResponseDto findById(int id) {
		StringBuilder sb = new StringBuilder(); 
		sb.append("SELECT b.id, b.userId, b.title, b.content, b.readCount,b.createDate,u.username ");
		sb.append("FROM board b INNER JOIN users u ");
		sb.append("ON b.userid = u.id ");
		sb.append("WHERE b.id = ?");
		
		final String SQL = sb.toString();
		BoardResponseDto boardDto = null;
		
		try {
			conn = DBConn.getConnection();
			psmt = conn.prepareStatement(SQL);
			// 물음표 완성하기
            psmt.setInt(1, id);
			// if 돌려서 rs -> java 오브젝트 집어 넣기
            rs = psmt.executeQuery();
            if(rs.next()) {
//            	Board board = new Board(); 
//            	board.setId(rs.getInt("b.id"));
            	boardDto = new BoardResponseDto();
            	Board  board = Board.builder()
            			 .id(rs.getInt(1))
            			 .userId(rs.getInt(2))
            			 .title(rs.getString(3))
            			 .content(rs.getString(4))
            			 .readCount(rs.getInt(5))
            			 .createDate(rs.getTimestamp(6))
            			 .build();
            	boardDto.setBoard(board);
            	boardDto.setUsername(rs.getString(7));
            } 
			return boardDto;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG + "findById : " + e.getMessage());
		} finally {
			DBConn.close(conn, psmt, rs);

		}
		return null;
	}
}
