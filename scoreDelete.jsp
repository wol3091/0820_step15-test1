<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적삭제</title>
<script type="text/javascript">
	window.onload = function() {
		if(${su > 0}) {
			alert("삭제 성공");
		} else {
			alert("삭제 실패");
		}		
		location.href="scoreList.do?pg=${pg}";
	}
</script>
</head>
<body>
</body>
</html>