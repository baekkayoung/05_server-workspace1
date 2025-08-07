package com.kh.controller;

import java.io.Console;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/confirmPizza.do")
public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("잘");
		
		// 1) 전달값 중에 한글이 있을 경우 인코딩 처리 (post 방식일때만)
//		request.setCharacterEncoding("utf-8");
		
		// 2) 요청시 전달값 뽑기 및 데이터가공처리(파싱같은거) => 변수 및 객체 기록
		//    request.getParameter("키") : 벨류값(String)
		//	  request.getParameterValues("키") : 벨류값["값1", "값2" , ..](String[])
		//    => 만일 키값이 존재하지 않을 경우 : null 반환
		
		// 요청시 전달값들 뽑아서 변수에 기록 => 출력
		
		String userName = request.getParameter("userName"); // "차은우" | 
		String phone = request.getParameter("phone"); // "01011112222" | 
		String address = request.getParameter("address"); // "서울시 강남구" |
		String message = request.getParameter("message"); // "메세지" | ""
		String pizza = request.getParameter("pizza"); // "콤비네이션피자" | "선택피자" 
		String[] toppings = request.getParameterValues("topping"); // ["고구마무스","피자바이트"] | NULL
		String[] sides = request.getParameterValues("side"); // ["콜라", "사이다"] | NU
		String payment = request.getParameter("payment"); // "card" | "cash"
		
		// 3) 요청처리 (db에 가서 sql문 실행 > Service > Dao)
		
		int price = 0;
		
		switch(pizza) {
		case "콤비네이션피자" : price +=5000; break;
		case "치즈피자" : price += 6000; break;
		case "포테이토피자" : 
		case "고구마피자" : price += 7000; break;
		case "불고기피자" : price += 8000; break;
		
		}
		
		if(toppings != null) {
			for(int i = 0; i<toppings.length; i++) {
				switch(toppings[i]) {
				case "고구마무스" :
				case "콘크림무스" : price +=1500; break;
				case "파인애플토핑" : 
				case "치즈토핑" : price += 2000; break;
				case "치즈바이트" :
				case "치즈크러스트" : price +=3000; break;
				}
			}
		}
		
	if(sides != null) {
		for(int i=0; i<sides.length; i++) {
			switch(sides[i]) {
			case "콜라" :
			case "사이다" : price+=2000; break;
			case "갈릭소스" :
			case "핫소스" : price += 300; break;
			case "피클" : 
			case "파마산치즈가루" : price += 500; break;
				
			}
		}
	}
	
	// 4) 요청 처리 후 사용자가 보게될 응답페이지(jsp) 만들기
	
	// 응답페이지(jsp)를 선택해서 포워딩
	// 단, 응답페이지에서 필요한 데이터가 있다면 담아서 포워딩 할 것
	// request의 attribute 영역에 담기
		
	request.setAttribute("userName", userName); // 여기서 "userName" 은 JSP에서 꺼낼 때 쓸 **키(이름)**이고,
	request.setAttribute("phone", phone); // userName 은 아까 서블릿에서 받아서 저장한 사용자 입력 값(String 변수)
	request.setAttribute("address", address);
	request.setAttribute("message", message);
	request.setAttribute("pizza", pizza);
	request.setAttribute("toppings", toppings);
	request.setAttribute("sides", sides);
	request.setAttribute("payment", payment);
	
	request.setAttribute("price", price);
	
	// 응답할뷰(jsp) 선택
	RequestDispatcher view = request.getRequestDispatcher("views/pizza/pizzaPayment.jsp");
	// 선택된 뷰 포워딩!
	view.forward(request, response);
	
	}

	/**
	<li></li> * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
