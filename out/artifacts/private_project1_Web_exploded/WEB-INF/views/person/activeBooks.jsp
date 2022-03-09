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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Books</title>
</head>
<body>
<a class="m-4 btn btn-warning" href="/person/home">Home</a>

<div style="padding: 20px" class="col-6">
    <form action="/person/search" method="post">
        <input type="text" class="form-control" placeholder="Search" name="search">
    </form>
</div>

<div class="container">
    <div class="row">

        <c:forEach var="book" items="${bookList}">

            <div class="col-md-3" >
                <div class="card border-success mb-3" style="width: 18rem;">
                    <img src="data:image/png;base64, ${course.imagePath}" class="card-img-top"
                         alt="Here Should be image">
                    <div class="card-body">
                        <h5 class="card-title">${book.name}</h5>
                        <p class="card-text">${book.description}</p>
                    </div>
                    <div class="card-footer">
                        <small class="text-muted"><a class="btn btn-info"
                                                     href="/person/bookInfo/${book.id}">More...</a></small>
                    </div>
                </div>
            </div>

        </c:forEach>

    </div>
</div>

<div style="margin-left: 600px">
    <c:forEach var="j" begin="1" end="${pages}">
        <a href="/person/activeBooks/${j}">${j}</a>
    </c:forEach>
</div>

</body>
</html>
