#!/bin/bash
mkdir -p build/classes
find src -name "*.java" > sources.txt
javac -d build/classes @sources.txt
java -cp build/classes RecruitmentSystem
