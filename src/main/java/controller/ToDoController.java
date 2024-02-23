package controller;

import dao.ToDoDao;
import dao.ToDoDaoImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Todo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/")
public class ToDoController extends HttpServlet {
    private static final long version = 1L;
    private ToDoDao toDoDao;

    public void init() {
        toDoDao = new ToDoDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new" :
                    showNewForm(request, response);
                    break;
                case "/insert" :
                    insertTodo(request, response);
                    break;
                case "/delete" :
                    deleteTodo(request, response);
                    break;
                case "/edit" :
                    showEditForm(request, response);
                    break;
                case "/update" :
                    updateTodo(request, response);
                    break;
                case "/list" :
                    listTodo(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("todo-list.jsp");
                    dispatcher.forward(request,response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listTodo(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        List<Todo> listTodo = toDoDao.selectAllTodos();
        request.setAttribute("listTodo", listTodo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("task_number"));
        Todo existingTodo = toDoDao.selectTodo(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-form.jsp");
        request.setAttribute("todo", existingTodo);
        dispatcher.forward(request, response);
    }

    private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("task_name");
        String description = request.getParameter("task_description");
        Todo todo = new Todo(name,description);
        toDoDao.insertTodo(todo);
        response.sendRedirect("list");
    }

    private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("task_number"));
        String name = request.getParameter("task_name");
        String description = request.getParameter("task_description");

        Todo updateTodo = new Todo(id, name, description);

        toDoDao.updateTodo(updateTodo);

        response.sendRedirect("list");
    }

    private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("task_number"));
        toDoDao.deleteTodo(id);
        response.sendRedirect("list");
    }
}
