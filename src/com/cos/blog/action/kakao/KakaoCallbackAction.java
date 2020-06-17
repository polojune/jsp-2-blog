package com.cos.blog.action.kakao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.util.Script;

public class KakaoCallbackAction implements Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	    String code = request.getParameter("code"); 
            //Script.outText(code, response);
    	    //POST요청, x-www -form-urlencoded
    	    
    	    //Buffered 읽어서 
    	    
    	    //Gson으로 파싱
      	  
    }
}
