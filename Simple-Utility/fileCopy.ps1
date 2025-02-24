# Define source and target folder paths
$sourceFolder = "C:\Path\To\Source"
$targetFolder = "C:\Path\To\Target"

# Specify the file prefix
$filePrefix = "YourPrefix*"

# Check if source and target folders exist
if (!(Test-Path -Path $sourceFolder)) {
    Write-Host "Error: Source folder does not exist: $sourceFolder" -ForegroundColor Red
    exit
}

if (!(Test-Path -Path $targetFolder)) {
    Write-Host "Target folder does not exist. Creating: $targetFolder" -ForegroundColor Yellow
    New-Item -ItemType Directory -Path $targetFolder | Out-Null
}

# Loop through each file with the specific prefix in the source folder
Get-ChildItem -Path $sourceFolder -Filter $filePrefix | ForEach-Object -Parallel {
    $sourceFile = $_
    $targetFile = Join-Path -Path $targetFolder -ChildPath $sourceFile.Name
    
    # Check if the file exists in the target folder
    if (Test-Path -Path $targetFile) {
        Write-Host "Skipping $($sourceFile.Name), already exists in target." -ForegroundColor Cyan
    } else {
        try {
            # Copy the file to the target folder
            Start-Process -NoNewWindow -FilePath 'robocopy' -ArgumentList '"$using:sourceFolder" "$using:targetFolder" "$using:filePrefix" /XO /MT:8'
            Write-Host "Copied $($sourceFile.Name) to target folder." -ForegroundColor Green
        } catch {
            Write-Host "Error copying $($sourceFile.Name): $_" -ForegroundColor Red
        }
    }
}