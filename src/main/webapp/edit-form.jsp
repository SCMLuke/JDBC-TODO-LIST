<%@ page import="java.util.List" %>
<%@ page import="model.Todo" %>
<%@ page import="dao.ToDoDao" %>
<%@ page import="dao.ToDoDaoImpl" %>
<%@ page import="controller.ToDoController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Todo</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <h3 class="text-center">Edit Todo</h3>
    <hr>
    <form action="<%=request.getContextPath()%>/update" method="post">
        <div class="form-group">
            <label for="task_number">Task Number:</label>
            <input type="text" class="form-control" id="task_number" name="task_number"
                   value="<%= ((model.Todo)request.getAttribute("todo")).getTaskNumber() %>" readonly>
        </div>
        <div class="form-group">
            <label for="task_name">Task Name:</label>
            <input type="text" class="form-control" id="task_name" name="task_name"
                   value="<%= ((model.Todo)request.getAttribute("todo")).getTaskName() %>" required>
        </div>
        <div class="form-group">
            <label for="task_description">Task Description:</label>
            <textarea class="form-control" id="task_description" name="task_description" required>
                <%= ((model.Todo)request.getAttribute("todo")).getTaskDescription() %>
            </textarea>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>
</body>
</html>
