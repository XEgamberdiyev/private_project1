<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>


<div style="padding: 20px">
    <h4>${message}</h4><hr>

    <h3>Please, fill the registration form</h3><br>

    <form action="/register" method="post">

        <input type="text" class="form-control" placeholder="Enter Full name" name="fullName" required><br>
        <input type="email" class="form-control" placeholder="Enter Email" name="email" required><br>
        <input type="password" class="form-control" placeholder="Enter password" name="password" required><br>

        <button type="submit" class="btn btn-primary">Register</button>
    </form>
</div>


</body>
</html>
