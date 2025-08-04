package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestGetServlet
 */
@WebServlet("/test1.do") // 요청하는 url을 부르는 구간
public class RequestGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get방식으로 요청을 하면 doGet 메소드가 저절로 호출됨
		
		/*
		 * 첫번째 매개변수 request에는 요청시 전달된 내용들이 담겨있음(사용자가 입력한 값, 요청 전송 방식, 요청자의 ip주소, 등등 요청하는 것)
		 * 두번째 매개변수 response에는 요청 처리 후 응답할 때 사용되는 객체
		 * 
		 * 요청 처리를 위해서 요청시 전달된 값들 뽑기
		 * request의 parameter 영역 안에 존재 (키(네임)=벨류 세트로 담겨 있음!)
		 * 
		 * 따라서 request의 parameter 영역으로부터 전달된 데이터 뽑는 메소드
		 * > request.getParameter("키값") : String (그에 해당하는)value 
		 * > request.getParameterValues("키값") : String[] (그에 해당하는 벨류값들)
		 * 
		 */
		 
		 String name = request.getParameter("name"); // "차은우" | ""  : 입력 안 하면 빈문자열
		 String gender = request.getParameter("gender"); // "M" | "F" | NULL : radio 특성
		 int age = Integer.parseInt(request.getParameter("age")); // "20" => 20 | "" => NumberFormatException 예외발생
		 String city = request.getParameter("city"); //"서울", "경기도"
		 double height = Double.parseDouble(request.getParameter("height")); // "160" => 160.0
		 
		 // 체크박스와 같이 복수개의 벨류값을 뽑고자 할 때
		 String[] foods = request.getParameterValues("food");
		 
		 System.out.println("name :" + name);
		 System.out.println("gender :" + gender);
		 System.out.println("age : " + age);
		 System.out.println("city : " + city );
		 System.out.println("height : " + height);
		 
//		 System.out.println("food : " + foods[0]);
		 if(foods == null) {
			 System.out.println("foods : 없음");
		 } else {
			 System.out.println("foods : " + String.join(" / ", foods)); // 한식 / 중식 / ,... 반복문도 괜찮지만 이 메소드 사용도 괜찮음
		 }
		 
		 // 뽑아낸 값(요청시 전달된 값)들을 가지고 요청처리 해야됨 (db와 상호작용)
		 // 여기서 Service 메소드 호출! > Dao 메소드 호출 > DB에 sql문 실행
		 
		 /*
		 int result = new MemberService().insertMember(name, gender, age, city, height, foods);
		 if(result >0 ) {
			 // 성공 -> 성공 메소드
 		 }else {
			 // 실패 -> 실패 메소드
		 }
		 */
		 
		 // 위의 요청 처리 후 성공했다는 가정하에 사용자가 보게될 응답페이지(html)를 만들어서 전송
		 // 즉, 여기 Java 코드 내에 사용자가 보게될 응답 html 구문 작성할거임!
		 
		 // 장점 : Java 코드 내에 작성하기 때문에 반복문, 조건문, 유용한 메소드 같은 것들 활용 가능!
		 // 단점 : 불편, 복잡, 혹시라도 후에 html 수정한다면 Java 코드를 수정하는거기때문에
		 // 	  수정된 내용을 다시 반영시키고자 한다면 서버 재실행 해야됨
		 
		 // * response 객체를 통해 사용자에게 html(응답화면) 전달
		 // 1) 이제부터 내가 출력할 내용은 문서의 형태는 html이고, 문자셋은 utf-8이다 라는 걸 선언
		 response.setContentType("text/html; charset=UTF-8");
		 
		 
		 // 2) 응답하고자하는 사용자와의 스트림(통로) 생성 서버가 html 왔다갔다 할 수 있게 됨
		 PrintWriter out = response.getWriter();
		 
		 // 3) 저 스트림을 통해 응답 html 구문을 한 줄씩 출력
		 out.println("<html>");
		 
		 out.println("<head>");
		 
		 out.println("<style>");
		 
		 out.println("h2{color : red}");
		 out.println("#name{color : orange}");
		 out.println("#age{color : yellow}");
		 out.println("#height{color:green}");
		 out.println("#gender{color:purple}");	
		 
		 out.println("</style>");
		 
		 out.println("</head>");
		 
		 out.println("<body>");
		 
		 out.println("<h2>개인정보응답화면</h2>");
		 
		 out.printf("<span id='name'>%s</span>님은 ", name);
		 out.printf("<span id='age'>%d</span>살이며 ", age);
		 out.printf("<span id='city'>%s</span>에 사는 ", city);
		 out.printf("키는 <span id='height'>%.1f</span> cm이고 ", height);
		 out.print("성별은 ");
		 if(gender == null) {
			 out.println("선택을 안했습니다. <br>");
		 }else {
			 if(gender.equals("M")) {
				 out.print("<span id='gender'>남자</span> 입니다. <br>");
			 }else {
				 out.print("<span id='gender=>여자</span> 입니다. <br>");
			 }
		 }
		 
		 out.print("좋아하는 음식은 ");
		 if(foods == null) {
			 out.println("없습니다. <br>");
		 }else { // ["한식","분식"]
			 out.println("<ul>");
			 
			 for(int i=0; i<foods.length; i++) {
				 out.println("<li>" + foods[i] + "</li>");
			 } 
			 out.println("</ul>");
			 
		 }
		 
		 out.println("</body>");
		 
		 out.println("</html>");
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
