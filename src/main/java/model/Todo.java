package model;

public class Todo {
    private int taskNumber;
    private String taskName;
    private String taskDescription;

    protected Todo() {}

    public Todo(int taskNumber, String taskName, String taskDescription) {
        super();
        this.taskNumber = taskNumber;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }
    public Todo(String taskName, String taskDescription) {
        super();
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
