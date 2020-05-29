package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;


public class UsersJoinProcAction implements Action{
         
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		     //0.유효성 검사
		
		   if 
		    ( 
		           request.getParameter("username").equals("") ||
		           request.getParameter("username") == null ||
		           request.getParameter("password").equals("") ||
		           request.getParameter("password") == null ||
		           request.getParameter("email").equals("") ||
		           request.getParameter("email") == null ||
		           request.getParameter("address").equals("") ||
		           request.getParameter("address") == null 
		    		) {
		   return;
	}
		   
		   //1. 파라미터 받기(x-www-form-urlencoded 라는 MIME타입 key = value)
		   String username = request.getParameter("username");
		   String password = request.getParameter("password");
		   String email = request.getParameter("email");
		   String address = request.getParameter("address");
		   String userRole = RoleType.USER.toString();
           
		   // 2. User 오브젝트 변환
		  Users user = Users.builder()
				  .username(username)
		          .password(password)
		          .email(email)
		          .address(address)
		          .userRole(userRole)
		          .build();
          //2. DB연결 - UserRepository	
		  UsersRepository usersRepository = UsersRepository.getInstance();
		  int result = usersRepository.save(user);
		  
		  // 이동할때는 RequestDispatcher 사용 , 주소는 controller를 타는  주소(cmd=???) , 액션만들어져 있다면 자바스크립트로 이동(cmd=???)
		  //4. index.jsp 페이지로 이동 
		  if(result == 1) {
			  //RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
			  Script.href("회원가입에 성공하였습니다.", "/blog/user?cmd=login", response);
			  
		  }else { 
			  Script.back("회원가입에 실패하였습니다.", response);
		  }
		          
    }

}