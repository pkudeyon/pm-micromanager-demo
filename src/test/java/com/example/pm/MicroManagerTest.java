package com.example.pm;

import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests every micro-management failure method in MicroManager.
 * Run with:  mvn test
 */
@DisplayName("MicroManager — 10 Failure Patterns")
class MicroManagerTest {

    private MicroManager manager;
    private TeamMember alice;
    private TeamMember bob;

    @BeforeEach
    void setUp() {
        manager = new MicroManager("Boss");
        alice   = new TeamMember("Alice", 100, 80);
        bob     = new TeamMember("Bob",   100, 90);
    }

    // ── 1 ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("1. Trust deficit — always overrides developer work")
    void testTrustDeficit() {
        assertTrue(manager.trustDeficit(alice),
                "Manager should always override developer output (trust = 0)");
    }

    // ── 2 ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("2. Bottleneck — task transitions through AWAITING_APPROVAL to DONE")
    void testBecomeBottleneck() {
        Task task = new Task("T-01", "Implement login");
        task.assign("Alice");
        assertEquals(Task.Status.IN_PROGRESS, task.getStatus());

        manager.becomeBottleneck(task);

        assertEquals(Task.Status.DONE, task.getStatus(),
                "Task should be DONE only after manager approval");
    }

    // ── 3 ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("3. Kill innovation — developer's idea is always discarded")
    void testKillInnovation() {
        String developerIdea = "Use reactive streams";
        String managerWay    = "Use a for-loop";

        String result = manager.killInnovation(developerIdea, managerWay);

        assertEquals(managerWay, result,
                "Manager should always enforce their own solution");
        assertNotEquals(developerIdea, result,
                "Developer's innovative idea should be rejected");
    }

    // ── 4 ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("4. Destroy morale — each oversight cycle costs 10 morale points")
    void testDestroyMorale() {
        manager.destroyMorale(alice, 5);
        assertEquals(50, alice.getMorale(),
                "5 oversight cycles at -10 each should leave morale at 50");
    }

    @Test
    @DisplayName("4b. Morale cannot drop below 0")
    void testMoraleFloor() {
        manager.destroyMorale(alice, 20); // 200 points worth
        assertEquals(0, alice.getMorale(), "Morale must not go negative");
    }

    // ── 5 ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("5. Lose big picture — returns micro-detail, ignores strategy")
    void testLoseBigPicture() {
        String strategy = "Increase user retention by 20%";
        String detail   = "Fix button colour";

        String focus = manager.loseBigPicture(strategy, detail);

        assertEquals(detail, focus,
                "Manager focuses on trivial detail instead of strategic goal");
    }

    // ── 6 ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("6. Create dependency — no tasks actionable without approval")
    void testCreateDependencyBlocked() {
        List<Task> sprint = List.of(
                new Task("T-02", "Deploy service"),
                new Task("T-03", "Write tests"));

        List<Task> actionable = manager.createDependency(false, sprint);

        assertTrue(actionable.isEmpty(),
                "Team should have no actionable tasks without manager approval");
    }

    @Test
    @DisplayName("6b. Tasks are released when manager approves")
    void testCreateDependencyApproved() {
        List<Task> sprint = List.of(
                new Task("T-02", "Deploy service"),
                new Task("T-03", "Write tests"));

        List<Task> actionable = manager.createDependency(true, sprint);

        assertEquals(2, actionable.size(),
                "All tasks should be accessible once manager approves");
    }

    // ── 7 ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("7. Stunt growth — developer skill does not improve")
    void testStuntGrowth() {
        int skillBefore = bob.getSkillLevel();
        manager.stuntGrowth(bob);

        assertEquals(skillBefore, bob.getSkillLevel(),
                "Bob's skill should be unchanged — growth was absorbed by manager");
    }

    // ── 8 ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("8. One-way communication — directive logged, feedback ignored")
    void testOneWayCommunication() {
        manager.oneWayCommunication(
                "Ship by Friday, no exceptions.",
                "We need 2 more days to test properly.");

        assertEquals(1, manager.getBroadcastLog().size(),
                "Directive should be recorded in broadcast log");
        assertFalse(manager.isAcceptsFeedback(),
                "Manager should not accept feedback");
    }

    // ── 9 ────────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("9. Drive away talent — top performer resigns at zero morale")
    void testDriveAwayTalent() {
        manager.destroyMorale(alice, 100); // drain to 0
        assertEquals(0, alice.getMorale());

        manager.driveAwayTalent(alice);

        assertTrue(alice.hasResigned(),
                "Alice should resign when morale hits zero");
    }

    @Test
    @DisplayName("9b. High-morale member does not resign")
    void testTalentStaysWithMorale() {
        manager.driveAwayTalent(bob); // Bob still at 100 morale
        assertFalse(bob.hasResigned(),
                "Bob should not resign while morale is high");
    }

    // ── 10 ───────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("10. Degrade velocity — every task requires a manager approval gate")
    void testDegradeVelocity() {
        List<Task> sprint = List.of(
                new Task("T-04", "Feature A"),
                new Task("T-05", "Feature B"),
                new Task("T-06", "Feature C"));

        int gates = manager.degradeVelocity(sprint);

        assertEquals(3, gates,
                "Every task should require its own approval gate (3 tasks = 3 gates)");

        sprint.forEach(t -> assertEquals(Task.Status.DONE, t.getStatus(),
                "All tasks must eventually reach DONE — but only after unnecessary delays"));
    }
}
