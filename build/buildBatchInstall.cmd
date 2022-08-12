@echo off
rem inizializzazione ambiente per build batch
mvn clean -P batch -f ../pom.xml generate-sources