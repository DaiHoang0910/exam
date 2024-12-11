<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sách</title>
</head>
<body>
<h1>Danh sách sách</h1>
<table border="1">
    <thead>
    <tr>
        <th>Mã sách</th>
        <th>Tên sách</th>
        <th>Tác giả</th>
        <th>Số lượng</th>
        <th>Mô tả</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.code}</td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>${book.quantity}</td>
            <td>${book.description}</td>
            <td><a href="borrowBook.jsp?bookCode=${book.code}">Mượn</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
