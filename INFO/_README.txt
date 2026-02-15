
ТЕХСТЕК
---------

JDK
https://www.oracle.com/java/technologies/downloads/
(якщо встановлена, то Apache Tomcat має підтягнути встановлену)

Apache Tomcat
https://tomcat.apache.org/
(якщо Apache Tomcat вже встановлено, то можна скористатися встановленим)

Spring Framework
https://spring.io/

Spring Web MVC
https://docs.spring.io/spring-framework/reference/web/webmvc.html

PostgreSQL
https://www.postgresql.org/

Jackson
https://github.com/FasterXML/jackson

Hibernate
https://hibernate.org/

Bootstrap
https://getbootstrap.com/
https://getbootstrap.com/docs/5.3/getting-started/introduction/
https://getbootstrap.com/docs/5.3/extend/icons/

Thymeleaf
https://www.thymeleaf.org/

Apache Maven WAR Plugin
https://maven.apache.org/plugins/maven-war-plugin/index.html


JAVA ПРОЕКТ
-------------

(1) Складаємо SQL-запити INFO/SQLs.sql
(2) Створюємо Maven-проект.
(3) Додаємо залежності в pom.xml
(4) Формуємо контент в src/main/resources
(5) Створюємо src/main/webapp та формуємо контент.
(6) Формуємо контент в org/example/app


РОЗГОРТАННЯ (ДЕПЛОЙ) ПРОЕКТУ
-------------------------------

Запустимо Apache Tomcat. Створемо секцію Git Bash

В IDE внизу
Terminal > <іконка випадаючого списку> > Git Bash

В секції буде шлях до директорії поточного проекту

<your-base-path>/<your-project-name>

Переходимо до директорії, де розташовані файли запуску
та припинення роботи Apache Tomcat

$ cd <your-base-path>/apache-tomcat-<version>/bin

Виконуємо команду

$ ./startup.bat

УВАГА.
Запуск Apache Tomcat: startup.bat - для Windows,
startup.sh - для MacOS/Linux.
Припинення Apache Tomcat: shutdown.bat - для Windows,
shutdown.sh - для MacOS/Linux.

Окремо з'явиться інформаційне вікно,
де відображається інформація про роботу
Apache Tomcat та програми.

Можемо згорнути його.

В IDE відкриваємо бокову праворуч вкладку Maven.
Через меню вкладки відкриваємо вікно команд,
де послідовно знаходимо та подвійним кліком
запускаємо відповідні Maven-команди

mvn clean

mvn install

mvn war:war

В директорії проекту target , знаходимо файл
Your-Project-Name-1.0-SNAPSHOT.war та копіюємо його
у відповідну директорію Apache Tomcat, в файловій системі
комп'ютера

<your-base-path>/apache-tomcat-<version>/webapps

Через декілька секунд, в цій директорії, має з'явитися
папка Your-Project-Name-1.0-SNAPSHOT.
Apache Tomcat розархівував архівний файл проекту.


ТЕСТ ПРОЕКТУ
--------------

В Web-браузері запускаємо

http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/contacts


та тестуємо функціонал програми.

УВАГА на дані в інформаційному вікні Apache Tomcat
про роботу Hibernate.


РЕСУРСИ
---------

https://www.baeldung.com/spring-mvc-annotations
https://www.baeldung.com/thymeleaf-in-spring-mvc
https://www.baeldung.com/spring-mvc-annotations
https://www.baeldung.com/spring-controller-vs-restcontroller
https://www.baeldung.com/spring-template-engines
https://www.baeldung.com/spring-new-requestmapping-shortcuts
https://www.baeldung.com/spring-requestmapping
https://www.baeldung.com/spring-mvc-view-resolver-tutorial
https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
https://www.thymeleaf.org/doc/tutorials/3.1/thymeleafspring.html
https://www.thymeleaf.org/doc/tutorials/3.1/thymeleafspring.html#integrating-thymeleaf-with-spring
https://www.thymeleaf.org/doc/articles/standarddialect5minutes.html
https://dzone.com/articles/how-does-spring-transactional
https://www.baeldung.com/hibernate-entitymanager
https://docs.spring.io/spring-framework/reference/data-access/transaction.html
https://docs.spring.io/spring-framework/reference/data-access/transaction/declarative/annotations.html
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html
https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth
