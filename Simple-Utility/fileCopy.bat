@echo off
set "sourceFolder=C:\Path\To\Source"
set "targetFolder=C:\Path\To\Target"
set "filePrefix=YourPrefix*"

:: Check if source folder exists
if not exist "%sourceFolder%" (
    echo Error: Source folder does not exist: %sourceFolder%
    exit /b
)

:: Check if target folder exists, create if it doesn't
if not exist "%targetFolder%" (
    echo Target folder does not exist. Creating: %targetFolder%
    mkdir "%targetFolder%"
)

:: Copy files using robocopy
robocopy "%sourceFolder%" "%targetFolder%" "%filePrefix%" /XO /MT:8

:: Check robocopy exit code
if %errorlevel% GEQ 8 (
    echo Error occurred during file copy.
) else (
    echo File copy completed successfully.
)
