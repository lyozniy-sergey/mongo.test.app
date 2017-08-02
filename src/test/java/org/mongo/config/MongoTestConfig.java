package org.mongo.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.mongo.listener.CascadeSaveMongoEventListener;
import org.mongo.listener.ContactCascadeSaveMongoEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 * @author lyozniy.sergey on 31 Jul 2017.
 */
@ComponentScan({ "org.mongo.dao", "org.mongo.services" })
@PropertySource("classpath:database.test.properties")
@Profile("test")
public class MongoTestConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getRequiredProperty("mongo.db");
    }

    @Override
    public Mongo mongo() throws Exception {
//        return new EmbeddedMongoBuilder()
//                .version(env.getProperty("mongo.version"))
//                .bindIp(env.getProperty("mongo.host"))
//                .port(env.getProperty("mongo.port", Integer.class))
//                .build();
        return new MongoClient(new ServerAddress(env.getProperty("mongo.host"), env.getProperty("mongo.port", Integer.class)));
    }

    @Bean
    public CascadeSaveMongoEventListener contactCascadingMongoEventListener() {
        return new ContactCascadeSaveMongoEventListener();
    }
}
