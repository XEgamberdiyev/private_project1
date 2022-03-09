<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2/10/2022
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">
    <title>Books</title>
</head>
<body>
<div style="padding: 20px">

    <a class="m-4 btn btn-success" href="/person/activeBooks/1">Back To Books</a><br>


    <h4>${book.name}</h4>
    <p>${book.description}</p>

    <h4 style="color: green">Price: ${book.price}</h4>

    <c:choose>
        <c:when test="${isBookPurchased == true}">
            <button type="button" class="btn btn-secondary" disabled>Purchased</button>
        </c:when>
        <c:when test="${isBookPurchased == false}">
            <a class="btn btn-success" href='/student/purchaseBook/${book.id}'>Purchase
                Book</a>
        </c:when>
    </c:choose>


</div>
</body>
</html>
