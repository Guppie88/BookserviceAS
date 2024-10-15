package com.marcus.webservice.Service;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        String rdsUrl = "jdbc:mysql://webservice-db.chcwmoqw0h8e.eu-north-1.rds.amazonaws.com:3306/webservice?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String rdsUsername = "root";
        String rdsPassword = "Guppie88!";

        try {
            // Försök att ansluta till AWS RDS
            Connection connection = DriverManager.getConnection(rdsUrl, rdsUsername, rdsPassword);
            System.out.println("Connected to AWS RDS.");
            connection.close();

            return DataSourceBuilder.create()
                    .url(rdsUrl)
                    .username(rdsUsername)
                    .password(rdsPassword)
                    .driverClassName("com.mysql.cj.jdbc.Driver")
                    .build();
        } catch (SQLException e) {
            // Om RDS inte är tillgänglig, växla till H2
            System.out.println("AWS RDS is not available, falling back to H2 in-memory database.");
            return DataSourceBuilder.create()
                    .url("jdbc:h2:mem:testdb") // In-memory H2-databas
                    .username("sa")
                    .password("password")
                    .driverClassName("org.h2.Driver")
                    .build();
        }
    }
}
