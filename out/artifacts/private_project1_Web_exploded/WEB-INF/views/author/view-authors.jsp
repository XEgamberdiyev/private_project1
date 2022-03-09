<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
<div style="padding: 20px">

    <a class="m-4 btn btn-success" href="/author/add">+ Add new author</a>


    <h3>Author list</h3>


    <table class="table">
        <tr>
            <th>Full name</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="author" items="${authors}">
            <tr>
               <td>${author.fullName}</td>
                <td>${author.email}</td>
                <td>
                    <a class="btn btn-warning" href='/author/edit/${author.id}'>Edit</a>
                    <a class="btn btn-danger" href="/author/delete/${author.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>


</div>
</body>
</html>
