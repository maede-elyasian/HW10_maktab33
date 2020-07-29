package config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"dao","utility","service.userService"})
public class BeanConfig {

    @Bean()
    public SessionFactory sessionFactory() {
       SessionFactory sessionFactory = new org.hibernate.cfg. Configuration()
               .configure("config/hibernate.cfg.xml").buildSessionFactory();
        return sessionFactory;
    }

}
