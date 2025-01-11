#!/bin/bash

# Функция для билда Spring Boot приложения
build_app() {
  local app_dir=$1
  echo "Building $app_dir..."
  (cd "$app_dir" && ./mvnw clean package -DskipTests) || {
    echo "Error building $app_dir"
    exit 1
  }
}

# Список приложений для билда
apps=("animals" "api-gateway" "config-server" "registry-eureka")

# Билдим каждое приложение
for app in "${apps[@]}"; do
  if [ -d "$app" ]; then
    build_app "$app"
  else
    echo "Directory $app not found, skipping..."
  fi
done

# Запуск основного docker-compose файла
echo "Starting Docker Compose..."
docker-compose -f docker-compose.yml up --build -d || {
  echo "Error starting Docker Compose"
  exit 1
}

echo "All services are up and running!"
