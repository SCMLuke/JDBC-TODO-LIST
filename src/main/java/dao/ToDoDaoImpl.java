package dao;

import jdbc.JDBC;
import model.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToDoDaoImpl implements ToDoDao {
    private static final String INSERT_TODOS_SQL = "INSERT INTO todo" +
            "(task_name, task_description) VALUES " + "(?,?);";
    private static final String SELECT_TODO_BY_ID = "select task_number,task_name,task_description from todo where task_number = ?";
    private static final String SELECT_ALL_TODOS = "select * from todo";
    private static final String DELETE_TODO_BY_ID = "delete from todo where task_number = ?;";
    private static final String UPDATE_TODO = "update todo set task_name = ?, task_description = ? where task_number = ?;";

    public ToDoDaoImpl(){}

    @Override
    public void insertTodo(Todo todo) throws SQLException {
        System.out.println(INSERT_TODOS_SQL);
        try (Connection connection = JDBC.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODOS_SQL)) {
            preparedStatement.setString(1, todo.getTaskName());
            preparedStatement.setString(2, todo.getTaskDescription());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBC.printSQLException(exception);
        }
    }

    @Override
    public Todo selectTodo(int todoNumber) {
        Todo todo = null;
        try (Connection connection = JDBC.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODO_BY_ID);) {

            preparedStatement.setInt(1, todoNumber);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int number = rs.getInt("task_number");
                String name = rs.getString("task_name");
                String description = rs.getString("task_description");
                todo = new Todo(number,name,description);
            }

        } catch (SQLException exception) {
            JDBC.printSQLException(exception);
        }
        return todo;
    }

    @Override
    public List<Todo> selectAllTodos() {
        List<Todo> todo = new ArrayList<>();
        try (Connection connection = JDBC.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODOS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int number = rs.getInt("task_number");
                String name = rs.getString("task_name");
                String description = rs.getString("task_description");
                todo.add(new Todo(number,name,description));
            }
        } catch (SQLException exception) {
            JDBC.printSQLException(exception);
        }
        return todo;
    }

    @Override
    public boolean deleteTodo(int number) throws SQLException {
        boolean delete;
        try (Connection connection = JDBC.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TODO_BY_ID);) {
            preparedStatement.setInt(1, number);
            delete = preparedStatement.executeUpdate() > 0;
        }
        return delete;
    }

    @Override public boolean updateTodo(Todo todo) throws SQLException {
        boolean update;
        try (Connection connection = JDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TODO);) {
            preparedStatement.setString(1, todo.getTaskName());
            preparedStatement.setString(2, todo.getTaskDescription());
            preparedStatement.setInt(3, todo.getTaskNumber());
            update = preparedStatement.executeUpdate() > 0;
        }
        return update;
    }
}
