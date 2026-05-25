package com.example.pm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Models a micro-managing project manager.
 *
 * Each method demonstrates one of the 10 reasons micromanagement fails:
 *
 *  1. trustDeficit         — does not delegate; redoes others' work
 *  2. becomeBottleneck     — all tasks must pass through one gatekeeper
 *  3. killInnovation       — rejects non-standard solutions outright
 *  4. destroyMorale        — constant oversight drains team morale
 *  5. loseBigPicture       — obsesses over details, ignores strategy
 *  6. createDependency     — team cannot act without explicit approval
 *  7. stuntGrowth          — never allows developers to learn on the job
 *  8. oneWayCommunication  — broadcasts orders; ignores feedback
 *  9. driveAwayTalent      — top performers resign under pressure
 * 10. degradeVelocity      — approval gates on every micro-task slow delivery
 */
public class MicroManager {

    private final String name;
    private final List<String> broadcastLog = new ArrayList<>();
    private boolean acceptsFeedback = false;

    public MicroManager(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    // ── Reason 1: Trust Deficit ──────────────────────────────────────────────
    /**
     * Manager does not trust the developer's output and redoes it.
     *
     * @return true — meaning the manager always overrides the work
     */
    public boolean trustDeficit(TeamMember developer) {
        // Never trusts; always intervenes
        return true;
    }

    // ── Reason 2: Bottleneck ─────────────────────────────────────────────────
    /**
     * Every task must wait for manager approval before it can be marked DONE.
     * Simulates a synchronized single-threaded choke point.
     */
    public synchronized void becomeBottleneck(Task task) {
        task.awaitApproval();
        // Simulated delay: in reality this blocks the whole sprint
        task.complete();
    }

    // ── Reason 3: Kill Innovation ─────────────────────────────────────────────
    /**
     * Rejects any solution that deviates from the manager's exact approach.
     *
     * @param proposedSolution the developer's idea
     * @param managerSolution  the manager's prescribed way
     * @return the manager's solution regardless of quality
     */
    public String killInnovation(String proposedSolution, String managerSolution) {
        // Developer's idea is discarded unconditionally
        return managerSolution;
    }

    // ── Reason 4: Destroy Morale ──────────────────────────────────────────────
    /**
     * Watches the team constantly; every watch cycle costs morale points.
     *
     * @param cycles number of oversight cycles applied
     */
    public void destroyMorale(TeamMember member, int cycles) {
        for (int i = 0; i < cycles; i++) {
            member.decrementMorale(10);
        }
    }

    // ── Reason 5: Lose Big Picture ────────────────────────────────────────────
    /**
     * Returns only the irrelevant micro-detail, never the strategic goal.
     */
    public String loseBigPicture(String strategicGoal, String microDetail) {
        // Strategic goal is ignored; only the trivial detail is returned
        return microDetail;
    }

    // ── Reason 6: Create Dependency ───────────────────────────────────────────
    /**
     * Team members may only act when the manager explicitly approves.
     * Without approval the task list remains empty.
     *
     * @param managerApproves whether approval was granted
     * @param tasks           tasks ready to be worked
     * @return actionable tasks (empty unless approved)
     */
    public List<Task> createDependency(boolean managerApproves, List<Task> tasks) {
        if (!managerApproves) {
            return Collections.emptyList(); // team is blocked
        }
        return tasks;
    }

    // ── Reason 7: Stunt Growth ────────────────────────────────────────────────
    /**
     * Manager assigns all challenging work to themselves; developer skill stagnates.
     */
    public void stuntGrowth(TeamMember member) {
        // Growth opportunity is absorbed by manager — member learns nothing
        // member.incrementSkill(10) is never called
    }

    // ── Reason 8: One-Way Communication ──────────────────────────────────────
    /**
     * Manager broadcasts a directive and ignores any response.
     */
    public void oneWayCommunication(String directive, String teamFeedback) {
        broadcastLog.add(directive);
        // teamFeedback is silently discarded
    }

    public List<String> getBroadcastLog() {
        return Collections.unmodifiableList(broadcastLog);
    }

    public boolean isAcceptsFeedback() {
        return acceptsFeedback;
    }

    // ── Reason 9: Drive Away Talent ───────────────────────────────────────────
    /**
     * When morale drops to zero the top performer resigns.
     */
    public void driveAwayTalent(TeamMember member) {
        if (member.getMorale() == 0) {
            member.resign();
        }
    }

    // ── Reason 10: Degrade Velocity ───────────────────────────────────────────
    /**
     * Inserts an approval gate for every single task, creating O(n) unnecessary
     * hand-offs and slowing overall sprint velocity.
     *
     * @return number of approval hand-offs created (equals task count)
     */
    public int degradeVelocity(List<Task> sprintTasks) {
        int approvalGates = 0;
        for (Task task : sprintTasks) {
            becomeBottleneck(task);   // every task goes through the manager
            approvalGates++;
        }
        return approvalGates;
    }
}
