@echo off
javac -cp "C:\Program Files\Java\junit-4.13-rc-2.jar" fraction\*.java
if not errorlevel 1 java -cp ;"C:\Program Files\Java\junit-4.13-rc-2.jar" junit.textui.TestRunner fraction.FractionImplTest