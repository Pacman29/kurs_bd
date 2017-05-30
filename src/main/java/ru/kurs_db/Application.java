package ru.kurs_db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by lieroz on 6.05.17.
 */
@SpringBootApplication
@ComponentScan({"ru.kurs_db.Controllers",
        "ru.kurs_db.JdbcDAO", "ru.kurs_db.DropboxService"
})
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
