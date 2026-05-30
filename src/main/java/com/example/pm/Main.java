package com.example.pm;

import java.util.List;

/**
 * Entry point — runs a live demo of all 10 micro-management failure patterns.
 *
 * Build  : mvn package
 * Run    : java -jar target/pm-micromanager-demo-1.0.0.jar
 * Test   : mvn test
 */

public class Main {

    public static void main(String[] args) {

        MicroManager manager = new MicroManager("Boss");
        TeamMember alice = new TeamMember("Alice", 100, 80);
        TeamMember bob   = new TeamMember("Bob",   100, 90);

        System.out.println("=== PM Micro-Manager Failure Demo ===\n");

        // 1 — Trust Deficit
        System.out.println("[1] Trust Deficit");
        boolean overridden = manager.trustDeficit(alice);
        System.out.println("    Alice's work overridden: " + overridden + "\n");

        // 2 — Bottleneck
        System.out.println("[2] Bottleneck");
        Task t1 = new Task("T-01", "Implement login");
        t1.assign("Alice");
        manager.becomeBottleneck(t1);
        System.out.println("    Task status after bottleneck: " + t1.getStatus() + "\n");

        // 3 — Kill Innovation
        System.out.println("[3] Kill Innovation");
        String chosen = manager.killInnovation("Use reactive streams", "Use a for-loop");
        System.out.println("    Solution chosen: " + chosen + "\n");

        // 4 — Destroy Morale
        System.out.println("[4] Destroy Morale");
        manager.destroyMorale(alice, 7);
        System.out.println("    Alice's morale after 7 oversight cycles: " + alice.getMorale() + "\n");

        // 5 — Lose Big Picture
        System.out.println("[5] Lose Big Picture");
        String focus = manager.loseBigPicture("Increase user retention by 20%", "Fix button colour");
        System.out.println("    Manager's focus: " + focus + "\n");

        // 6 — Create Dependency
        System.out.println("[6] Create Dependency");
        List<Task> sprint = List.of(new Task("T-02", "Deploy service"), new Task("T-03", "Write tests"));
        List<Task> actionable = manager.createDependency(false, sprint);
        System.out.println("    Actionable tasks without approval: " + actionable.size() + "\n");

        // 7 — Stunt Growth
        System.out.println("[7] Stunt Growth");
        int skillBefore = bob.getSkillLevel();
        manager.stuntGrowth(bob);
        System.out.println("    Bob's skill before/after: " + skillBefore + " / " + bob.getSkillLevel() + "\n");

        // 8 — One-Way Communication
        System.out.println("[8] One-Way Communication");
        manager.oneWayCommunication("Ship by Friday, no exceptions.", "We need 2 more days to test properly.");
        System.out.println("    Broadcast log size: " + manager.getBroadcastLog().size());
        System.out.println("    Feedback accepted: " + manager.isAcceptsFeedback() + "\n");

        // 9 — Drive Away Talent
        System.out.println("[9] Drive Away Talent");
        manager.destroyMorale(alice, 100); // drain remaining morale
        manager.driveAwayTalent(alice);
        System.out.println("    Alice resigned: " + alice.hasResigned() + "\n");

        // 10 — Degrade Velocity
        System.out.println("[10] Degrade Velocity");
        List<Task> bigSprint = List.of(
                new Task("T-04", "Feature A"),
                new Task("T-05", "Feature B"),
                new Task("T-06", "Feature C"),
                new Task("T-07", "Feature D"),
                new Task("T-08", "Feature E")
        );
        int gates = manager.degradeVelocity(bigSprint);
        System.out.println("    Approval gates for 5 tasks: " + gates + " (should be 0 in a healthy team)\n");

        System.out.println("=== Demo complete. Run 'mvn test' to verify all assertions. ===");
    }
}
