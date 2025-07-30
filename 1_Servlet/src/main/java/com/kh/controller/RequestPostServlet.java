package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet 메소드 실행");
		
		// 요청시 전달된 값들은 request parameter 공간에 담겨 있음
		
		// POST 방식 요청 같은 경우는 뽑기 전에 인코딩 설정해야됨
		
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name"); // "차은우" | ""
		String gender = request.getParameter("gender"); // "M" | "F" | NULL
		int age = Integer.parseInt(request.getParameter("age")); // "20" => 20 | "" => NumberFormatException
		String city = request.getParameter("city"); // "서울" , "제주", ... 
		double height = Double.parseDouble(request.getParameter("height")); // "170" => 170.0
		
		String[] foods = request.getParameterValues("food"); // ["한식", "중식", ...] | NULL
		
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city : " + city);
		System.out.println("height : " + height);
		
		if(foods == null) {
			System.out.println("foods : 없음");
		} else {
			System.out.println("foods : " + String.join("-", foods));
		}
		
		// 요청처리 (db에 sql문 실행) : Service > Dao > sql문
		
		// 요청처리 다 됐다는 가정하에 사용자가 보게될 응답 html
		
		// 순수 Servlet 방식 : Java 코드 내에 html을 기술
		// JSP(Java Sever Page)방식 : html 내에 Java 코드를 쓸 수 있음
		
		// 응답페이지를 만드는 과정을 jsp에게 위임 (떠넘기기)
		
		// 단, 응답화면(jsp)에서 필요로 하는 데이터들을 주섬주섬 담아서 전달해줘야됨
		// 주섬주섬 담기 위한 공간 == request attribute 영역 (키-벨류 세트로)
		// request.setAttribute("키", 벨류(object형));
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		request.setAttribute("gender", gender);
		request.setAttribute("foods", foods) ; // 두번째는 오브젝트도 받으니까
		
		
		
		
		// 응답하고자 하는 뷰(jsp)를 선택하면서 RequestDispatcher 객체 생성
		RequestDispatcher view = request.getRequestDispatcher("views/responsePage.jsp");
		view.forward(request, response); // 포워딩
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost 메소드 실행");
		doGet(request, response); 
		// get이든 post든 get을 탄다..
	}

}
