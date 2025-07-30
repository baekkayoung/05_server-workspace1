<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	h2{color:red}
	#name{color:orange}
	#age{color:blue}
	#city{color : yellow}
	#height{color : purple}
	#gender{color : green}
</style>
</head>
<body>
	<%	
		// request는 jsp를 포워딩하면 내장되어있어서 쓸 수 있음
		// 스크립틀릿 == html 문서 내에 바코드를 쓸 수 있는 영역
		// request.getAttribute("키") : 벨류 => servlet에서 반환형 object니까
		//String name = //request.getAttribute("name"); // 얘는 Object라 못 넣음 => String으로 형변환
		String name = (String)request.getAttribute("name");
		int age = (int)request.getAttribute("age");
		String city = (String)request.getAttribute("city");
		double height = (double)request.getAttribute("height");
		String gender = (String)request.getAttribute("gender");
		String[] foods = (String[])request.getAttribute("foods");
	%>
	
	
	<h2>개인정보응답화면 - POST</h2>
	
	<span id = "name"><%= name %></span>님은
	<span id = "age"><%= age %></span>살이며,
	<span id = "city"><%= city %></span>에 사는
	키는 <span id = "height"><%= height %></span>cm 이고
	
	성별은
	<% if(gender == null){ %>
		선택하지 않았습니다 <br>  <!-- case1 -->
	<%	}else {%>
	<%	if(gender.equals("M")){ %>
		<span id = "gender">남자</span>입니다. <br> <!-- case2_1 -->
		<%}else{ %>
		<span id = "gender">여자</span>입니다. <br> <!-- case2_2 -->
		<% } %>
	<%	} %>
		
		
	좋아하는 음식은
	<% if(foods == null) {%>
		없습니다. <br>
	<% }else{%>	
		<ul>
			<% for(int i=0; i<foods.length; i++){ %>
				<li><%= foods[i]%></li>
			<%} %>
		</ul>
	<% } %>	

</body>
</html>