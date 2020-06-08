package com.cos.blog.action.board;

import java.io.IOException;
import java.security.Principal;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.Script;

public class BoardUpdateProcAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0번 인증 확인
		HttpSession session = request.getSession();
		if (session.getAttribute("principal") == null) {
			Script.getMessage("잘못된 접근입니다.", response);
			return;
		}
		
		// 1번 request에 title값과 content값 null인지 공백인지 확인

		if (request.getParameter("id").equals("") || request.getParameter("id") == null
				|| request.getParameter("title").equals("") || request.getParameter("title") == null
				|| request.getParameter("content").equals("") || request.getParameter("content") == null

		) {
			return;
		}
		// 2번 request에 title 값과 content 값을 받기
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		// 3번 title값과 content,principal.getId()값을 Board 오브젝트에 담기

//   	   Board board = new Board();
//   	   board.setTitle(title);
//   	   board.setContent(content);

		Board board = Board.builder()
				.id(id)
				.title(title)
				.content(content)
				.build();

		// 4번 BoardRepository연결해서 save(board)함수 호출
		BoardRepository boardRepository = BoardRepository.getInstance();

		int result = boardRepository.update(board);
		// 5번 result ==1이면 성공로직(index.jsp로 이동)
		if (result == 1) {

			Script.href("수정 성공", "/blog/board?cmd=detail&id="+id, response);
		} else {
			Script.back("수정을 실패하였습니다.", response);
		}
		// 6번 result != 1이면 실패로직(history.back())
	}
}
