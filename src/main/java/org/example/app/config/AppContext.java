package org.example.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

// Клас інтеграції Spring та Hibernate
// з використанням конфігурації Spring через Java.
// Конфігурація Hibernate через Java.

// @Configuration використовується для вказівки того,
// що клас конфігурації оголошує один або кілька
// методів @Bean.
// Ці класи обробляються контейнером Spring для генерації
// визначень bean-компонентів та запитів на обслуговування
// для цих bean-компонентів під час виконання.
@Configuration
// @PropertySource забезпечує механізм додавання
// PropertySource в Spring Environment для використання
// у поєднанні з класами @Configuration.
@PropertySource("classpath:app.properties")
// @EnableTransactionManagement активує можливості Spring
// з управління транзакціями через анотації.
@EnableTransactionManagement
public class AppContext {

    // Interface Environment представляє середовище,
    // в якому працює поточна програма.
    // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/env/Environment.html
    @Autowired
    private Environment environment;

    // LocalSessionFactoryBean створює Hibernate SessionFactory,
    // яка є загальною в контексті Spring-додатку.
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("org.example.app.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    // Через цей бін відбувається з'єднання та взаємодія з базою даних.
    // Interface DataSource - фабрика для підключень до фізичного джерела даних,
    // яке представляє цей об'єкт DataSource.
    // https://docs.oracle.com/en/java/javase/21/docs/api/java.sql/javax/sql/DataSource.html
    // Class DriverManagerDataSource - проста реалізація стандартного інтерфейсу JDBC DataSource,
    // налаштування звичайного JDBC DriverManager через властивості bean-компонента
    // та повернення нового Connection з кожного виклику getConnection.
    // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/datasource/DriverManagerDataSource.html
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbcDriver"));
        dataSource.setUrl(environment.getRequiredProperty("dbUrl"));
        dataSource.setUsername(environment.getRequiredProperty("dbUser"));
        dataSource.setPassword(environment.getRequiredProperty("dbPass"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        // https://docs.jboss.org/hibernate/orm/6.0/userguide/html_single/Hibernate_User_Guide.html#_sql_statement_logging
        // 26.10.1. SQL statement logging
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        // hibernate.hbm2ddl.auto автоматично перевіряє/експортує схему DDL
        // в базу даних при створенні SessionFactory.
        // За допомогою create-drop схема бази даних буде видалена
        // при явному закритті SessionFactory.
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    // HibernateTransactionManager прив'язує Hibernate Session
    // із зазначеної фабрики до потоку, що потенційно дозволяє
    // використовувати одну сесію, прив'язану до потоку, на фабрику.
    // Цей диспетчер транзакцій підходить для додатків, які
    // використовують одну Hibernate SessionFactory для доступу до транзакційних
    // даних, але він також підтримує прямий доступ до джерела даних
    // всередині транзакції, тобто простий JDBC.
    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
