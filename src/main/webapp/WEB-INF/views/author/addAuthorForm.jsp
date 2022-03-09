<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Mentor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>

<div style="padding: 20px" class="col-8 mx-auto">
    <h5>Add Mentor Form</h5><hr>

    <form action="/mentor/add" method="post">

        <input type="text" class="form-control" placeholder="Enter full name" name="fullname"><br>
        <input type="email" class="form-control" placeholder="Enter email" name="email"><br>
        <input type="password" class="form-control" placeholder="Enter password" name="password"><br>

        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</div>


</body>
</html>
