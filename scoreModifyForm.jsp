<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적관리</title>
<script type="text/javascript" src="../script/scoreScript.js?v=1"></script>
<style type="text/css">
h2 {
	text-align: center;
}
table {
	margin: auto;
	width: 200px
}
.td_left {
	width: 50px;
	background: orange;
}
.td_right {
	width: 100px;
	background: skyblue;
}
</style>
</head>
<body>
<h2>성적 수정</h2>
<form action="scoreModify.do" method="post" name="scoreModify">
	<input type="hidden" name="pg" value="${pg}">
<table>
	<tr align="center">
		<td class="td_left"><label for="studNo">학번</label></td>
		<td class="td_right">
			<input type="text" name="studNo" id="studNo" 
				value="${scoreDTO.studNo}" readonly="readonly">
		</td>
	</tr>
	<tr align="center">
		<td class="td_left"><label for="name">이름</label></td>
		<td class="td_right">
			<input type="text" name="name" id="name" 
				value="${scoreDTO.name}" readonly="readonly">
		</td>
	</tr>
	<tr align="center">
		<td class="td_left"><label for="kor">국어</label></td>
		<td class="td_right">
			<input type="text" name="kor" id="kor" value="${scoreDTO.kor}">
		</td>
	</tr>
	<tr align="center">
		<td class="td_left"><label for="eng">영어</label></td>
		<td class="td_right">
			<input type="text" name="eng" id="eng" value="${scoreDTO.eng}">
		</td>
	</tr>
	<tr align="center">
		<td class="td_left"><label for="mat">수학</label></td>
		<td class="td_right">
			<input type="text" name="mat" id="mat" value="${scoreDTO.mat}">
		</td>
	</tr>
	<tr align="center">
		<td colspan="2">
			<input type="button" value="수정" onclick="checkScoreModify()">
			<input type="reset" value="다시쓰기">
			<input type="button" value="목록" 
					onclick="location.href='scoreList.do'">
		</td>
	</tr>
</table>
</form>
</body>
</html>





