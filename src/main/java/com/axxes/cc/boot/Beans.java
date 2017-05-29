package com.axxes.cc.boot;

import java.sql.Driver;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class Beans {

    @Bean
    public DataSource dataSource() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        String url = "jdbc:mysql://127.0.0.1:3306/springboot";
        String username = "kevin";
        String password = "kevin_pwd";
        return new SimpleDriverDataSource(driver, url, username, password);
    }

}
