@echo off
rem Script to compare files in source and destination folders
rem Define Source and Destination Folders

set "sourceFolder=E:\GitHub\Java-Dev\Simple-Utility\Source"
set "destinationFolder=E:\GitHub\Java-Dev\Simple-Utility\target"
set "logFile=%~dp0output.log"

rem Clear previous log file or create a new one
if exist "%logFile%" del "%logFile%"
echo Missing files: > "%logFile%"

rem Check if source and destination folders exist
if not exist "%sourceFolder%" (
    echo Error: Source folder does not exist. >> "%logFile%"
    echo Error: Source folder does not exist.
    exit /b
)

if not exist "%destinationFolder%" (
    echo Error: Destination folder does not exist. >> "%logFile%"
    echo Error: Destination folder does not exist.
    exit /b
)

rem Loop through files in source folder
for %%f in ("%sourceFolder%\*") do (
    if not exist "%destinationFolder%\%%~nxf" (
        echo %%~nxf >> "%logFile%"
    )
)

echo Comparison complete. Check output.log for missing files.
exit /b