package org.mongo.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.mongo.listener.BankCascadeSaveMongoEventListener;
import org.mongo.listener.CascadeSaveMongoEventListener;
import org.mongo.listener.ContactCascadeSaveMongoEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;

/**
 * @author lyozniy.sergey on 18 Jul 2017.
 */
@PropertySource("classpath:database.dev.properties")
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration {
    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getRequiredProperty("mongo.db");
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(new ServerAddress(env.getProperty("mongo.host"), env.getProperty("mongo.port", Integer.class)), Collections.singletonList(MongoCredential.createCredential(env.getProperty("mongo.username"), env.getProperty("mongo.db"), env.getProperty("mongo.password").toCharArray())));
    }

    public @Bean
    static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        MappingMongoConverter c = super.mappingMongoConverter();
        c.afterPropertiesSet();
        return c;
    }

    @Bean
    public CascadeSaveMongoEventListener contactCascadingMongoEventListener() {
        return new ContactCascadeSaveMongoEventListener();
    }

    @Bean
    public CascadeSaveMongoEventListener bankCascadingMongoEventListener() {
        return new BankCascadeSaveMongoEventListener();
    }

//    @Bean
//    public ValidatingMongoEventListener validatingMongoEventListener() {
//        return new ValidatingMongoEventListener(validator());
//    }
//
//    @Bean
//    public LocalValidatorFactoryBean validator() {
//        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
//        factoryBean.setValidationMessageSource(messageSource);
//        return factoryBean;
//    }
//
//    @Bean
//    public Validator contactValidator() {
//        return new ContactValidator();
//    }
}
