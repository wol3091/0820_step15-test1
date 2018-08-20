<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적 목록</title>
<style type="text/css">
#listForm {
	width: 500px;
	height: 160px;
	margin: auto;
}
h2 {
	text-align: center;
}
table {
	margin: auto;
	width: 450px;
}
#tr_top {
	background: orange;
	text-align: center;
}
#currentPaging {
	color: red;
	text-decoration: underline;
}
#paging {
	color: blue;
	text-decoration: none;
}
#name:link { color:black; text-decoration: none; }
#name:visited { color:black; text-decoration: none; }
#name:hover { color:green; text-decoration: underline; }
#name:achive { color:black; text-decoration: none; }
</style>
</head>
<body>
<h2>성적 목록</h2>
<div id="listForm">
<table>
	<tr id="tr_top">
		<td>학번</td>
		<td>이름</td>
		<td>국어</td>
		<td>영어</td>
		<td>수학</td>
		<td>총점</td>
		<td>평균</td>
	</tr>
<c:forEach var="scoreDTO" items="${list }">
	<tr bgcolor="ffffcc" align="center">
		<td>${scoreDTO.studNo}</td>
		<td>
			<a id="name" 
				href="scoreView.do?studNo=${scoreDTO.studNo}&pg=${pg}">
				${scoreDTO.name}
			</a>
		</td>
		<td>${scoreDTO.kor}</td>
		<td>${scoreDTO.eng}</td>
		<td>${scoreDTO.mat}</td>
		<td>${scoreDTO.tot}</td>
		<td>${scoreDTO.avg}</td>
	</tr>
</c:forEach>	

	<!-- 페이징 -->
	<tr>
		<td colspan="7" align="center">
		<c:if test="${startPage > 3 }">
			[<a id="paging" href="scoreList.do?pg=${startPage - 1}">이전</a>]
		</c:if>

	<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
		<c:if test="${i == pg }">
			[<a id="currentPaging" href="scoreList.do?pg=${i}">${i}</a>]
		</c:if>
		<c:if test="${i != pg }">
			[<a id="paging" href="scoreList.do?pg=${i}">${i}</a>]
		</c:if>	
	</c:forEach>
		
		<c:if test="${endPage < maxPage}">
			[<a id="paging" href="scoreList.do?pg=${endPage + 1}">다음</a>]
		</c:if>		
		</td>
	</tr>
</table>
<a href="scoreWriteForm.do">성적 입력</a>
</div>
</body>
</html>