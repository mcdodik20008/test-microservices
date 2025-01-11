function Build-App($appDir) {
    Write-Host "Building $appDir..."
    Set-Location $appDir
    & ./mvnw clean package -DskipTests
    if ($LASTEXITCODE -ne 0) {
        Write-Error "Error building $appDir"
        exit 1
    }
    Set-Location ..
}

$apps = @("animals", "api-gateway", "config-server", "registry-eureka")

foreach ($app in $apps) {
    if (Test-Path $app) {
        Build-App $app
    } else {
        Write-Warning "Directory $app not found, skipping..."
    }
}

Write-Host "Starting Docker Compose..."
docker-compose -f docker-compose.yml up --build -d

Write-Host "All services are up and running!"
