# API Autotests

#### Фреймворк для тестирования апи
---
## 🛠 Зависимости

- Java 21
- RestAssured
- Gradle (используется Wrapper)
- Junit
- Lombok
- Spring Boot

---

## 📂 Структура проекта
* [constants](src/main/java/com/example/evo/constants) - значения постоянных (строки,enum)
* [models](src/main/java/com/example/evo/models) - Модели для request и response
* [services](src/main/java/com/example/evo/services) - Сервисы для вызова апи методов
* [lib](lib) - локальные библиотеки

---
## 🚀 Запуск тестов
### Через Idea
1. Открыть тестовый класс (например [AutoPayTests.java](src/test/java/uz/click/tests/evo_api/AutoPayTests.java))
2. Нажать по зеленой кнопке ▶️ напротив теста - запустится только этот тест или нажать по кнопку напротив названия класса, это запустит все тесты в классе
#### Запуск через командную строку
`./gradlew clean test` - запуск всех тестов

`./gradlew clean test --tests "evo_api.tests.com.example.AutoPayTests.getAutopayListTest"` - запуск одного теста

---
## 🧪 Генерация локального отчета Allure
### Выполнить команды изкомандной строки
1. `gradle tasks allureReport` - сгенерирует данные для репорта
2. `gradle tasks allureServe` - запустит веб-сервер и откроет страницу с репортом в браузере

