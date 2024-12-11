<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mượn Sách</title>
</head>
<body>
<h1>Mượn Sách</h1>

<form action="books" method="post">
    <%--@declare id="studentid"--%><%--@declare id="issuedate"--%><%--@declare id="returndate"--%>
        <input type="hidden" name="bookCode" value="${param.bookCode}" />

    <label for="studentId">Chọn học sinh:</label>
    <select name="studentId">
        <c:forEach var="student" items="${students}">
            <option value="${student.id}">${student.name}</option>
        </c:forEach>
    </select><br>

    <label for="issueDate">Ngày mượn:</label>
    <input type="text" name="issueDate" value="${currentDate}" /><br>

    <label for="returnDate">Ngày trả:</label>
    <input type="text" name="returnDate" /><br>

    <button type="submit">Mượn Sách</button>
</form>
<a href="${pageContext.request.contextPath}/books">Quay lại danh sách sách</a>
</body>
</html>
