package com.example.pm;

/**
 * Represents a work task in the sprint.
 */
public class Task {

    public enum Status { PENDING, IN_PROGRESS, AWAITING_APPROVAL, DONE }

    private final String id;
    private final String description;
    private Status status;
    private String assignee;

    public Task(String id, String description) {
        this.id = id;
        this.description = description;
        this.status = Status.PENDING;
    }

    public String getId()           { return id; }
    public String getDescription()  { return description; }
    public Status getStatus()       { return status; }
    public String getAssignee()     { return assignee; }

    public void assign(String memberName) {
        this.assignee = memberName;
        this.status = Status.IN_PROGRESS;
    }

    public void awaitApproval() {
        this.status = Status.AWAITING_APPROVAL;
    }

    public void complete() {
        this.status = Status.DONE;
    }

    @Override
    public String toString() {
        return String.format("Task{id='%s', status=%s, assignee='%s'}", id, status, assignee);
    }
}
