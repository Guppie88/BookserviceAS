package com.marcus.webservice.Service;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Profile("rds") // Använd denna konfiguration om profilen "rds" är aktiv
    public DataSource rdsDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://webservice-db.chcwmoqw0h8e.eu-north-1.rds.amazonaws.com:3306/webservice?useSSL=false")
                .username("root")
                .password("Guppie88!")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean
    @Profile("local") // Använd denna konfiguration om profilen "local" är aktiv
    public DataSource h2DataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")
                .username("sa")
                .password("")
                .driverClassName("org.h2.Driver")
                .build();
    }
}
