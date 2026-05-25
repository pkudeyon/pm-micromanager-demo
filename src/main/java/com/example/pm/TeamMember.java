package com.example.pm;

/**
 * Represents a developer on the team.
 * In a healthy environment, skills grow and morale stays high.
 */
public class TeamMember {

    private final String name;
    private int morale;       // 0–100
    private int skillLevel;   // 0–100
    private boolean resigned;

    public TeamMember(String name, int morale, int skillLevel) {
        this.name = name;
        this.morale = morale;
        this.skillLevel = skillLevel;
        this.resigned = false;
    }

    public String getName()       { return name; }
    public int getMorale()        { return morale; }
    public int getSkillLevel()    { return skillLevel; }
    public boolean hasResigned()  { return resigned; }

    public void decrementMorale(int amount) {
        this.morale = Math.max(0, this.morale - amount);
    }

    public void incrementSkill(int amount) {
        this.skillLevel = Math.min(100, this.skillLevel + amount);
    }

    public void resign() {
        this.resigned = true;
    }

    @Override
    public String toString() {
        return String.format("TeamMember{name='%s', morale=%d, skill=%d, resigned=%b}",
                name, morale, skillLevel, resigned);
    }
}
