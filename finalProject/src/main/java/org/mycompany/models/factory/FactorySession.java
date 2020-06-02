package org.mycompany.models.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class FactorySession {

    private static SessionFactory sessionFactory;

    public FactorySession(){

    }

    @Bean
    @Scope("prototype")
    public static SessionFactory getSessionFactory(){
        return sessionFactory = new Configuration().configure().buildSessionFactory();
    }



}
