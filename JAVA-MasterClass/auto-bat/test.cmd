@echo off

set "pscmd=powershell.exe -Command " ^
$inputPass = read-host 'Enter Your Password :' -AsSecureString ; ^
$BSTR=[System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($inputPass); ^
[System.Runtime.InteropServices.Marshal]::PtrToStringAuto($BSTR)""

for /f "tokens=*" %%a in ('%pscmd%') do set PASSWORD=%%a

echo %PASSWORD%
pause