@echo off
:: Use WMIC to get date and time in the format: yyyyMMddHHmmss
for /f "tokens=1 delims=." %%a in ('wmic os get localdatetime ^| find "."') do (
    set datetime=%%a
)

:: Extract year, month, and day
set year=%datetime:~0,4%
set month=%datetime:~4,2%
set day=%datetime:~6,2%

:: Set the log date as mm_dd_yyyy
set logdate=%month%_%day%_%year%

:: Ensure the logs directory exists
cd /d "C:\Users\eX_Di\git\StS-Metrics-Server\windows-service"
if not exist logs mkdir logs
if not exist logs\standard mkdir logs\standard
if not exist logs\error mkdir logs\error

:: Run the Java application and log output to the appropriate files
java -jar Duelist-Metrics-Server.jar >> "logs\standard\output_%logdate%.log" 2>> "logs\error\error_%logdate%.log"
