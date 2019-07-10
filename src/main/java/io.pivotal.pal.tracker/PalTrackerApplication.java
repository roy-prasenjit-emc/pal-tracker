package io.pivotal.pal.tracker;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PalTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }

    @Bean
    public TimeEntryRepository timeEntryRepository(@Value("${spring.datasource.url:NOT SET}") String url) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(url);
        return new JdbcTimeEntryRepository(dataSource);
    }
}
