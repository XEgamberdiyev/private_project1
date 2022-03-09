<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Books</title>
</head>
<body>
<div class="container">
    <div class="row">

        <c:forEach var="book" items="${authorBooks}">


            <div class="col-md-3" >
                <div class="card border-success mb-3" style="width: 18rem;">
                    <img src="data:image/png;base64, ${book.imagePath}" class="card-img-top"
                         alt="Here Should be image">
                    <div class="card-body">
                        <h5 class="card-title">${book.name}</h5>
                        <p class="card-text">${book.active}</p>
                    </div>
                    <div class="card-footer">
                        <small class="text-muted"><a class="btn btn-info" href="/books/info/${book.id}">More...</a></small>
                    </div>
                </div>
            </div>


        </c:forEach>

    </div>
</div>

</body>
</html>
