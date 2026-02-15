package org.example.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

// Конфігурація Spring MVC через Java анотації

// @Configuration використовується для вказівки того,
// що клас конфігурації оголошує один або кілька
// методів @Bean.
// Ці класи обробляються контейнером Spring для генерації
// визначень bean-компонентів та запитів на обслуговування
// для цих bean-компонентів під час виконання.
@Configuration
// Додавання @EnableWebMvc до класу @Configuration імпортує
// конфігурацію Spring MVC з WebMvcConfigurationSupport
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/EnableWebMvc.html
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurationSupport.html
@EnableWebMvc
// @ComponentScan використовується для вказівки базових пакетів
// для сканування компонентів.
@ComponentScan(basePackages = {"org.example.app"})
public class WebMvcConfig implements WebMvcConfigurer {

    // Interface ApplicationContext - центральний інтерфейс
    // для налаштування застосунку.
    // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/ApplicationContext.html
    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        // Class SpringResourceTemplateResolver автоматично інтегрується
        // з власною інфраструктурою Spring для обробки ресурсів.
        // https://www.thymeleaf.org/apidocs/thymeleaf-spring6/3.1.1.RELEASE/org/thymeleaf/spring6/templateresolver/SpringResourceTemplateResolver.html
        SpringResourceTemplateResolver templateResolver =
                new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        // HTML – це значення за замовчуванням, додане тут для ясності.
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // Кеш шаблонів за замовчуванням має значення true.
        // Встановіть значення false, якщо хочете, щоб шаблони
        // автоматично оновлювалися після зміни.
        templateResolver.setCacheable(true);
        // Для роботи з кирилицею.
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        // Class SpringTemplateEngine автоматично застосовує
        // SpringStandardDialect та активує власні механізми
        // обробки повідомлень MessageSource у Spring.
        // https://www.thymeleaf.org/apidocs/thymeleaf-spring6/3.1.2.RELEASE/org/thymeleaf/spring6/SpringTemplateEngine.html
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        // Активація компілятора SpringEL у Spring 4.2.4 або новішої версії може
        // пришвидшити виконання у більшості сценаріїв, але може бути несумісним
        // з певними випадками, коли вирази в одному шаблоні повторно використовуються
        // для різних типів даних, тому цей прапорець за замовчуванням має значення "false"
        // для безпечнішої зворотної сумісності.
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    // Class ThymeleafViewResolver - реалізація інтерфейсу ViewResolver з Spring WebMVC .
    // https://www.thymeleaf.org/apidocs/thymeleaf-spring6/3.1.2.RELEASE/org/thymeleaf/spring6/view/ThymeleafViewResolver.html
    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/WEB-INF/resources/");
    }
}
