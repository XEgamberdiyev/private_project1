<%--
  Created by IntelliJ IDEA.
  User: muhammadsodiq
  Date: 20/02/22
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div style="padding: 20px" class="col-6 mx-auto">

    <form action="/user/edit" method="post">
        <div class="form-group">
            <input type="hidden" class="form-control" id="exampleInputEmail1"
                   aria-describedby="emailHelp" value="${user.id}" name="id">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Enter fullname</label>
            <input type="text" class="form-control" id="exampleInputPassword1"
                   value="${user.fullName}" name="fullname">
        </div>
        <br>
        <div class="form-group">
            <label for="exampleInputPassword2">Enter email</label>
            <input type="email" class="form-control" id="exampleInputPassword2"
                   value="${user.email}" name="email">
        </div>
        <br>
        <div class="form-group">
            <label for="exampleInputPassword3">Enter old password</label>
            <input type="password" class="form-control" id="exampleInputPassword3"
                   name="oldPassword">
        </div>
        <br>
        <div class="form-group">
            <label for="exampleInputPassword4">Enter new password</label>
            <input type="password" class="form-control" id="exampleInputPassword4"
                   name="newPassword">
        </div>
        <br>
        <div class="form-group">
            <label for="exampleInputPassword5">Enter again new password</label>
            <input type="password" class="form-control" id="exampleInputPassword5"
                   name="confirmPassword">
        </div>
        <br>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>
</body>
</html>
