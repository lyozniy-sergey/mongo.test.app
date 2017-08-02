package org.mongo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author lyozniy.sergey on 19 Jul 2017.
 */
@Configuration
@Import({WebConfig.class, MongoConfig.class})
public class ServletConfig {
}
