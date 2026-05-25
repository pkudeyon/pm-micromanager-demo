# PM Micromanager Demo 🚨

> A Java project that models **10 reasons project managers fail as micro-managers**,
> each expressed as a concrete Java anti-pattern.

## Requirements
- Java 21+
- Maven 3.8+

## Quick Start

```bash
# Clone (after pushing to your repo)
git clone <your-repo-url>
cd pm-micromanager-demo

# Build
mvn package

# Run the demo
java -jar target/pm-micromanager-demo-1.0.0.jar

# Run all tests
mvn test
```

## Project Structure

```
pm-micromanager-demo/
├── pom.xml
└── src/
    ├── main/java/com/example/pm/
    │   ├── Main.java           ← entry point / live demo
    │   ├── MicroManager.java   ← all 10 failure patterns
    │   ├── TeamMember.java     ← developer model
    │   └── Task.java           ← sprint task model
    └── test/java/com/example/pm/
        ├── MicroManagerTest.java   ← 12 tests covering all 10 patterns
        ├── TeamMemberTest.java     ← 4 model unit tests
        └── TaskTest.java           ← 4 model unit tests
```

## The 10 Failure Patterns

| # | Pattern | Java Anti-Pattern |
|---|---------|-------------------|
| 1 | Trust Deficit | Always overrides delegate output |
| 2 | Bottleneck | `synchronized` approval gate blocks all work |
| 3 | Kill Innovation | Developer ideas unconditionally discarded |
| 4 | Destroy Morale | Oversight loop drains morale to 0 |
| 5 | Lose Big Picture | Returns micro-detail, ignores strategy |
| 6 | Create Dependency | `Collections.emptyList()` without approval |
| 7 | Stunt Growth | `incrementSkill()` never called |
| 8 | One-Way Communication | Feedback silently discarded |
| 9 | Drive Away Talent | `resign()` triggered at zero morale |
| 10 | Degrade Velocity | O(n) approval gates per sprint task |

## Push to GitHub

```bash
git init
git add .
git commit -m "feat: PM micromanager demo — 10 failure patterns in Java"
git remote add origin <your-repo-url>
git push -u origin main
```
