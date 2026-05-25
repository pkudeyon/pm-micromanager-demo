#!/usr/bin/env bash
# ─────────────────────────────────────────────────────────────────────────────
# build.sh — compile, test, and package pm-micromanager-demo without Maven
# Requires: JDK 21+, JUnit 5 (junit-platform-console-standalone.jar on classpath)
# ─────────────────────────────────────────────────────────────────────────────
set -e

JUNIT_STANDALONE="junit-platform-console-standalone.jar"
JUNIT_PATHS=(
  "/usr/share/java/$JUNIT_STANDALONE"
  "$HOME/.local/share/java/$JUNIT_STANDALONE"
  "./$JUNIT_STANDALONE"
)

JUNIT_CP=""
for p in "${JUNIT_PATHS[@]}"; do
  if [ -f "$p" ]; then JUNIT_CP="$p"; break; fi
done

if [ -z "$JUNIT_CP" ]; then
  echo "ERROR: $JUNIT_STANDALONE not found. Install junit5 or place the jar in this directory."
  exit 1
fi

OUT="out"
TARGET="target"
JAR="$TARGET/pm-micromanager-demo-1.0.0.jar"

echo "── Cleaning ─────────────────────────────"
rm -rf $OUT $TARGET

echo "── Compiling ────────────────────────────"
mkdir -p $OUT $TARGET
javac -cp "$JUNIT_CP" -d $OUT \
  $(find src/main/java -name "*.java") \
  $(find src/test/java  -name "*.java")
echo "   OK"

echo "── Testing ──────────────────────────────"
java -jar "$JUNIT_CP" --class-path $OUT --scan-class-path --details=tree
echo "   OK"

echo "── Packaging ────────────────────────────"
mkdir -p $OUT/META-INF
echo "Main-Class: com.example.pm.Main" > $OUT/META-INF/MANIFEST.MF
cd $OUT
jar cfm ../$JAR META-INF/MANIFEST.MF \
  com/example/pm/Main.class \
  com/example/pm/MicroManager.class \
  com/example/pm/TeamMember.class \
  com/example/pm/Task.class
cd ..
echo "   JAR → $JAR"

echo ""
echo "── Done! Run with:  java -jar $JAR"
