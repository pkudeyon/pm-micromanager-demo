package com.example.pm;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Task — model tests")
class TaskTest {

    @Test
    @DisplayName("New task starts as PENDING")
    void testInitialStatus() {
        Task t = new Task("T-01", "Write unit tests");
        assertEquals(Task.Status.PENDING, t.getStatus());
        assertNull(t.getAssignee());
    }

    @Test
    @DisplayName("Assigning a task moves it to IN_PROGRESS")
    void testAssign() {
        Task t = new Task("T-01", "Write unit tests");
        t.assign("Alice");
        assertEquals(Task.Status.IN_PROGRESS, t.getStatus());
        assertEquals("Alice", t.getAssignee());
    }

    @Test
    @DisplayName("awaitApproval moves status to AWAITING_APPROVAL")
    void testAwaitApproval() {
        Task t = new Task("T-02", "Deploy service");
        t.assign("Bob");
        t.awaitApproval();
        assertEquals(Task.Status.AWAITING_APPROVAL, t.getStatus());
    }

    @Test
    @DisplayName("complete() moves status to DONE")
    void testComplete() {
        Task t = new Task("T-03", "Code review");
        t.assign("Carol");
        t.complete();
        assertEquals(Task.Status.DONE, t.getStatus());
    }
}
