package org.bear.comp.task.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class TaskApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApiApplication.class, args);
    }

}
