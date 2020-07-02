package org.mycompany.models.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Class for creating hibernate session factory
 */
@Component
public class FactorySession {

    private static SessionFactory sessionFactory;

    public FactorySession(){

    }

    /**
     * Creates hibernate session factory
     * @return sessionFactory object
     */
    @Bean
    @Scope("prototype")
    public static SessionFactory getSessionFactory(){
        return sessionFactory = new Configuration().configure().buildSessionFactory();
    }



}
