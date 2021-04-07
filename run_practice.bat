@echo off
javac -cp "C:\Program Files\Java\junit-4.13-rc-2.jar" *.java fraction\*.java
if not errorlevel 1 java -cp ;"C:\Program Files\Java\junit-4.13-rc-2.jar" FractionPractice