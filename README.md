# CRUD Сервис

Это CRUD сервис, который позволяет выполнять основные операции с базой данных.

## Инструкция по сборке проекта

1. Клонировать репозиторий:
   ```bash
   git clone https://github.com/aleksguz00/OpenSchool

2. Перейти в директорию проекта:
   ```bash
    cd

3. Собрать проект:
   ```bash
   mvn clean install

4. Перед запуском также нужно поднять кафку
   ```bash
   docker-compose -d up

5. Запустить проект
- Либо из IDE
- Либо через команду:

  ```bash
  mvn spring-boot:run
## Тестирование

Тестирование можно проводить через curl запросы

1. Получение задачи по ID
    ```bash
    curl -X GET http://localhost:8080/api/tasks/{id}
    
2. Получение всех задач
    ```bash
    curl -X GET http://localhost:8080/api/tasks

3. Создание новой задачи
    ```bash
    curl -X POST http://localhost:8080/tasks \
    -H "Content-Type: application/json" \
    -d '{
    "title": "My Task",
    "description": "Task description",
    "userId": 1,
    "status": "PENDING"
    }'

4. Обновление задачи
    ```bash
      curl -X PUT http://localhost:8080/tasks/{id} \
      -H "Content-Type: application/json" \
      -d '{
          "title": "Updated Task",
          "description": "Updated description",
          "userId": 1,
          "status": "IN_PROGRESS"
      }'
   
   В поле "status" используются значения из enum TaskStatus

5. Удаление задачи
   Для удаления задачи по её идентификатору, выполните следующий запрос:
    ```bash
    curl -X DELETE http://localhost:8080/tasks/{id}
    
6. Просмотр базы данных
   Чтобы просмотреть базу данных можно перейти по адресу http://localhost:8080/h2-console и ввести настройки подключения:
    - JDBC URL: jdbc:h2:mem:testdb
    - User Name: alex
    - Password: 12345
      Все эти данные есть в конфиге БД в application.properties
