<%@ page import="java.util.List" %>
<%@ page import="model.Todo" %>
<%@ page import="dao.ToDoDao" %> <!-- Add this import -->
<%@ page import="dao.ToDoDaoImpl" %> <!-- Add this import -->
<%@ page import="controller.ToDoController" %> <!-- Add this import -->
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo List</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <h3 class="text-center">List of Todos</h3>
    <hr>
    <div class="container text-left">

        <a href="<%=request.getContextPath()%>/new"
           class="btn btn-success">Add Todo</a>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Task Number</th>
            <th>Name</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            ToDoDao toDoDao = new ToDoDaoImpl();
            List<Todo> allTodos = toDoDao.selectAllTodos();
            for (Todo todo : allTodos) {
        %>
        <tr>
            <td><%= todo.getTaskNumber() %></td>
            <td><%= todo.getTaskName() %></td>
            <td><%= todo.getTaskDescription() %></td>
            <td>
                <a href="edit?task_number=<%= todo.getTaskNumber() %>" class="btn btn-primary">Edit</a>
                <a href="delete?task_number=<%= todo.getTaskNumber() %>" class="btn btn-danger">Delete</a>
            </td>
        </tr>
        <% } // Remove this extra curly brace
            if (allTodos.isEmpty()) { %>
        <tr>
            <td colspan="4" class="text-center">No todos available</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
