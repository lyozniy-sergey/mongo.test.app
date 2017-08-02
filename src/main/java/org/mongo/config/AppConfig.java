package org.mongo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author lyozniy.sergey on 18 Jul 2017.
 */
@Configuration
@ComponentScan({ "org.mongo.config", "org.mongo.dao", "org.mongo.services" })
@Import({ MongoConfig.class })
public class AppConfig {
}
