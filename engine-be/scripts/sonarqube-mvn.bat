@echo off

if "%~1"=="" (
    echo Usage: %0 ^<login^> ^<password^>
    exit /b 1
)

set LOGIN=%~1
set PASSWORD=%~2

mvn clean verify sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=%LOGIN% -Dsonar.password=%PASSWORD%
