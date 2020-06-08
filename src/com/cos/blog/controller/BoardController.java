package com.cos.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.action.board.BoardDeleteAction;
import com.cos.blog.action.board.BoardDetailAction;
import com.cos.blog.action.board.BoardHomeAction;
import com.cos.blog.action.board.BoardUpdateAction;
import com.cos.blog.action.board.BoardUpdateProcAction;
import com.cos.blog.action.board.BoardWriteAction;
import com.cos.blog.action.board.BoardWriteProcAction;
import com.cos.blog.action.user.UsersJoinAction;
import com.cos.blog.action.user.UsersJoinProcAction;
import com.cos.blog.action.user.UsersLoginAction;


//Http://localhost:8000/blog/user
@WebServlet("/board")
public class BoardController extends HttpServlet {
	private final static String TAG = "BoardController";
	private static final long serialVersionUID = 1L;
       
    
    public BoardController() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
		
	}
	
	protected void doprocess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      //http://localhost:8000/blog/user?cmd=join
		  String cmd = request.getParameter("cmd");
		  System.out.println(TAG + "router : " + cmd);
		  Action action = router(cmd);
		  action.execute(request, response);
		
	}
   public Action router(String cmd) {
	   if(cmd.equals("home")) {
		    //회원 가입 페이지로 이동
	    return new BoardHomeAction();   //Board 목록 보여주기
	   } else if(cmd.equals("write")) {
		    //글쓰기  페이지로 이동
	    return new BoardWriteAction();   //글쓰기

	   } else if(cmd.equals("writeProc")) {
		    //글쓰기  페이지로 이동
	    return new BoardWriteProcAction();   //글쓰기
	   }
	   
	   else if(cmd.equals("detail")) {
		    //글쓰기  페이지로 이동
	    return new BoardDetailAction();   //상세보기
	   }
	   else if(cmd.equals("update")) {
		    //글쓰기  페이지로 이동
	    return new BoardUpdateAction();   //수정페이지
	   }
	   else if(cmd.equals("updateProc")) {
		    //글쓰기  페이지로 이동
	    return new BoardUpdateProcAction();   //수정하기
	   }
	   else if(cmd.equals("updateProc")) {
		    //글쓰기  페이지로 이동
	    return new BoardUpdateProcAction();   //수정하기
	   }
	   else if(cmd.equals("delete")) {
		    //글쓰기  페이지로 이동
	    return new BoardDeleteAction();   //수정하기
	   }
	   
			  
			   
	   return null;
    
   } 
}
