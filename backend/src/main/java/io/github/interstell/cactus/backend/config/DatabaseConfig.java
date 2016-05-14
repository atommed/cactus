package io.github.interstell.cactus.backend.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Grigoriy on 5/14/2016.
 */
@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource dataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("");
        ds.setUrl("jdbc:postgresql://10.55.33.64:8888/eventDB");
        ds.setUsername("postgres");
        ds.setPassword("1234");
        ds.setInitialSize(5);

        return ds;
    }

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
