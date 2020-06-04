package com.cos.blog.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;

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
		// 1. DB 연결해서 Board 목록 다 불러와서
		BoardRepository boardRepository = BoardRepository.getInstance();
		List<Board> boards = boardRepository.findAll(); 
		
		for(Board board : boards) { 
			  String preview = board.getContent(); 
			  preview = preview.substring(0,10)+"...";
			  board.setContent(preview);
		}
		
		// 2. request에 담고
		request.setAttribute("boards", boards); 
		// 3. home.jsp로 이동
		RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);

	}

}