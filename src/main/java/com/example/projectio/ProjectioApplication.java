package com.example.projectio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class ProjectioApplication {

    private static JdbcTemplate jdbcTemplate = null;


    public static void main(String[] args) {
        SpringApplication.run(ProjectioApplication.class, args);
    }

    public static JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("...");
            dataSource.setUsername("...");
            dataSource.setPassword("...");
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }
}
