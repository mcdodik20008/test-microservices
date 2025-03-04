apk add --no-cache curl


<h3> 1. Проектирование архитектуры: </h3>

Микросервисы: Каждый из указанных сервисов будет реализован как отдельный микросервис с собственной базой данных, что
обеспечит независимость сервисов и их масштабируемость.

CQRS: Разделение команд на чтение и запись обеспечит оптимизацию взаимодействий с данными, где операция записи и чтения
будут обрабатываться отдельными компонентами.

Event Sourcing: Хранение всех изменений состояния сущностей как событий, что поможет отслеживать историю переходов между
состояниями животных и других объектов в системе.
<h3> 2. Модели данных: </h3>
CatchService: Модели, такие как CatchAct (акт отлова) и CatchCompany (компании по отлову), будут храниться в базе данных
этого сервиса.

ShelterService: Модели ShelterAct (акт передачи в приют) и ShelterOrganization (приюты) будут храниться в отдельной
базе.

TransferService: Модели, такие как TransferAct (акт передачи нового владельцу) и Owner (владельцы), будут находиться
здесь.

TreatmentService и PetClinicService: Модели медицинских актов будут разделены по типу животного (бездомное или
домашнее), а модели MedicalWorker (медицинские работники) будут общими для обоих сервисов.

Дополнительные сервисы: Для работы с историей и пользователями создаются соответствующие сервисы, которые будут
обращаться к данным из других сервисов через API.


<h3> 3. Интеграция между сервисами: </h3>

API Gateway: Использование API Gateway для обработки входящих запросов и маршрутизации их к нужным сервисам.

Event-driven взаимодействие: Использование событий для передачи данных между сервисами. Например, событие о передаче

животного может быть отправлено от одного сервиса к другому.

Согласование данных: Использование подхода CQRS и событий для поддержания консистентности данных между сервисами.

<h3> 4. Мониторинг: </h3>

Prometheus и Grafana: Интеграция с Prometheus для сбора метрик и Grafana для визуализации. Можно настроить метрики для
каждого микросервиса, таких как количество запросов, время отклика, ошибки и т. д.


Kiali: Использование Kiali для отслеживания состояния сети сервисов и микросервисной архитектуры в целом.

<h3> 5. Реализация: </h3>

Spring Boot и Spring Cloud для разработки микросервисов.

Для каждой базы данных можно использовать подходящие решения, такие как PostgreSQL или MySQL, в зависимости от

особенностей данных и требований к их масштабируемости.

<h3> 6. Документация: </h3>

Swagger/OpenAPI для документирования API.

Четкое описание сервисов и их взаимодействий в технической документации.