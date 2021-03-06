package com.cos.blog.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.HtmlParser;

public class BoardHomeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		Cookie[] cookies = request.getCookies();
//		if(cookies != null) {
//			 for (Cookie cookie : cookies) {
//				 System.out.println("boardhomeaction.java : " + cookie.getName() + " : " + cookie.getValue());
//				
//			}
//		}
//
//		
		int page = Integer.parseInt(request.getParameter("page"));
		// 1. DB 연결해서 Board 목록 다 불러와서
		BoardRepository boardRepository = BoardRepository.getInstance();

		// 2. 3건만 가져오기

		List<Board> boards = boardRepository.findAll(page);
		// 본문 짧게 가공하기
//		for (Board board : boards) {
//			String preview = HtmlParser.getContentPreview(board.getContent());
//			board.setContent(preview);
//
//		}

		// 2. request에 담고
		request.setAttribute("boards", boards);
		// 마지막페이지 확인 로직

		int count = boardRepository.count();
		int lastPage = (count - 1) / 3;
		request.setAttribute("lastPage", lastPage);

		// 이전 페이지 정보
		HttpSession session = request.getSession();
		session.setAttribute("backPage", page);
		session.setAttribute("backKeyword", null);
		// 3. home.jsp로 이동
		RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);

	}

}