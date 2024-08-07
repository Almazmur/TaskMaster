# TaskMaster

TaskMaster - это приложение для управления задачами, построенное с использованием Spring Boot и PostgreSQL.

## Требования

- Docker
- Docker Compose
- Maven (если не используете Maven Wrapper)

## Сборка и запуск проекта

### Шаг 1: Клонирование репозитория

Клонируйте репозиторий на ваш локальный компьютер:
```bash
git clone https://github.com/yourusername/TaskMaster.git
cd TaskMaster
Шаг 2: Сборка проекта
Соберите проект с использованием Maven. Если у вас нет Maven Wrapper (mvnw.cmd), используйте команду mvn:

bash
Копировать код
./mvnw clean package  # Для Unix-систем
mvnw.cmd clean package  # Для Windows
Если у вас нет Maven Wrapper, используйте:

bash
Копировать код
mvn clean package
Шаг 3: Создание Docker образа
Создайте Docker образ для вашего приложения. Убедитесь, что у вас есть Dockerfile в корневом каталоге проекта:

dockerfile
Копировать код
# Dockerfile

# Используем официальный образ OpenJDK 17
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем JAR файл из локальной системы в контейнер
COPY target/TaskMaster-0.0.1-SNAPSHOT.jar app.jar

# Запускаем JAR файл
ENTRYPOINT ["java", "-jar", "app.jar"]
Шаг 4: Настройка Docker Compose
Убедитесь, что у вас есть docker-compose.yml файл в корневом каталоге проекта:

yaml
Копировать код
version: '3.8'

services:
  db:
    image: postgres:13
    container_name: postgresql
    environment:
      POSTGRES_USER: root
      POSTGRES_PASSWORD: rootpassword
      POSTGRES_DB: taskmanagement
    ports:
      - "5432:5432"
    networks:
      - taskmanagement-network

  app:
    build: .
    container_name: task-management-app
    depends_on:
      - db
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/taskmanagement
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: rootpassword
      JWT_SECRET_KEY: mySecretKey
    networks:
      - taskmanagement-network

networks:
  taskmanagement-network:
    driver: bridge
Шаг 5: Запуск Docker Compose
Запустите Docker Compose для создания и запуска контейнеров:

bash
Копировать код
docker-compose up --build
Шаг 6: Доступ к приложению
После того как контейнеры будут запущены, приложение будет доступно по адресу: http://localhost:8080

Конфигурация приложения
Конфигурация приложения находится в файле src/main/resources/application.properties. Вот пример содержимого файла:

properties
Копировать код
# Spring Data JPA
spring.datasource.url=jdbc:postgresql://db:5432/taskmanagement
spring.datasource.username=root
spring.datasource.password=rootpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# JWT Configuration
jwt.secret=mySecretKey
jwt.expirationMs=3600000
Тестирование
Для запуска тестов используйте следующую команду:

bash
Копировать код
./mvnw test  # Для Unix-систем
mvnw.cmd test  # Для Windows
Если у вас нет Maven Wrapper, используйте:

bash
Копировать код
mvn test
Остановка и удаление контейнеров
Для остановки контейнеров используйте:

bash
Копировать код
docker-compose down
