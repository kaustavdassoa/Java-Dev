# ==========================
# Configuration Section
# ==========================
# Provide your values here before running the script
$credential = [pscredential]::new("username", (ConvertTo-SecureString "password" -AsPlainText -Force))

# Source and Target Directories (provide full paths)
$sourceDir = "C:\SourceFolder"  # Example: C:\SourceFolder
$targetDir = "D:\TargetFolder"  # Example: D:\TargetFolder

# Maximum files per batch
$maxFiles = 10  # Example: 10

# File filter (e.g., files starting with 'FILTER')
$filter = "FILTER*"  # Example: "FILTER*"

# ==========================
# Script Execution Section
# ==========================
# Ensure copied subfolder exists
$copiedFolder = Join-Path -Path $sourceDir -ChildPath "copied"
if (!(Test-Path -Path $copiedFolder)) {
    New-Item -ItemType Directory -Path $copiedFolder | Out-Null
}

# Get files based on filter
$files = Get-ChildItem -Path $sourceDir -Filter $filter | Select-Object -First $maxFiles

if ($files.Count -eq 0) {
    Write-Output "No files found matching the pattern '$filter' in $sourceDir."
    exit
}

foreach ($file in $files) {
    try {
        # Copy file
        Copy-Item -Path $file.FullName -Destination $targetDir -Credential $credential -ErrorAction Stop
        
        # Move file to 'copied' sub-folder
        Move-Item -Path $file.FullName -Destination $copiedFolder -ErrorAction Stop
        
        Write-Output "Copied and moved: $($file.Name)"
    } catch {
        Write-Output "Error processing file: $($file.Name). Error: $_"
    }
}

Write-Output "File transfer complete. Files copied to $targetDir and moved to $copiedFolder."