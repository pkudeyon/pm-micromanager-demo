package com.example.pm;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TeamMember — model tests")
class TeamMemberTest {

    @Test
    @DisplayName("Initial state is set correctly")
    void testInitialState() {
        TeamMember tm = new TeamMember("Alice", 80, 70);
        assertEquals("Alice", tm.getName());
        assertEquals(80, tm.getMorale());
        assertEquals(70, tm.getSkillLevel());
        assertFalse(tm.hasResigned());
    }

    @Test
    @DisplayName("Morale decrement respects floor of 0")
    void testMoraleFloor() {
        TeamMember tm = new TeamMember("Bob", 10, 50);
        tm.decrementMorale(50);
        assertEquals(0, tm.getMorale());
    }

    @Test
    @DisplayName("Skill increment respects ceiling of 100")
    void testSkillCeiling() {
        TeamMember tm = new TeamMember("Carol", 100, 95);
        tm.incrementSkill(20);
        assertEquals(100, tm.getSkillLevel());
    }

    @Test
    @DisplayName("Resign flag is set correctly")
    void testResign() {
        TeamMember tm = new TeamMember("Dave", 0, 60);
        tm.resign();
        assertTrue(tm.hasResigned());
    }
}
