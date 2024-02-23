package dao;

import model.Todo;

import java.sql.SQLException;
import java.util.List;

public interface ToDoDao {
    void insertTodo(Todo todo) throws SQLException;
    Todo selectTodo(int todoId);
    List<Todo>selectAllTodos();
    boolean deleteTodo(int id) throws SQLException;
    boolean updateTodo(Todo todo) throws SQLException;
}
