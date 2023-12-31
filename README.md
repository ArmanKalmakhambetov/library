# Проект "Личная библиотека" (серверная часть)

Этот проект представляет собой серверную часть web-приложения для личной библиотеки. Он разработан на языке Java с использованием различных технологий.
## Описание

Личная библиотека - это веб-приложение, которое позволяет пользователям просматривать, добавлять, удалять и изменять информацию о книгах в их личной библиотеке. Приложение предоставляет удобный интерфейс для управления коллекцией книг.

## Технологии

Проект использует следующие технологии:

- **Java**: Основной язык программирования.
- **MySQL**: СУБД для хранения информации о книгах и пользователях.
- **REST API**: Для взаимодействия с клиентской частью приложения.
- **Spring Boot**: Фреймворк для упрощения создания и настройки веб-приложений.
- **Maven**: Инструмент для управления зависимостями и сборки проекта.
- **Hibernate**: Фреймворк для работы с базой данных, обеспечивающий объектно-реляционное отображение.
- **Swagger**: Инструмент для автоматической генерации документации по API.
- **Lombok**: Библиотека для уменьшения объема кода Java.

## Установка и настройка

1. **Клонирование репозитория**:

git clone https://github.com/ArmanKalmakhambetov/library.git

3. **Настройка базы данных**:
   
- Создайте базу данных MySQL и настройте доступ к ней в файле `src/main/resources/application.properties`.

3. **Сборка проекта**:
   
mvn clean install

5. **Запуск приложения**:

java -jar target/library.jar

## Документация API

Документация API доступна с использованием Swagger. После запуска сервера, вы можете открыть Swagger UI в браузере по адресу [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) для просмотра и тестирования API.

## Использование

После успешной установки и запуска приложения, вы можете начать использовать его. Подключитесь к серверу с клиентской частью приложения для просмотра, добавления, удаления и изменения информации о книгах в вашей личной библиотеке.

## Лицензия

Этот проект распространяется под лицензией [MIT](LICENSE), что позволяет вам свободно использовать, модифицировать и распространять код.

## Авторы

- Arman Kalmakhambetov

## Обратная связь

Если у вас есть вопросы, предложения или замечания, пожалуйста, свяжитесь с нами по электронной почте arman458792@gmail.com.
