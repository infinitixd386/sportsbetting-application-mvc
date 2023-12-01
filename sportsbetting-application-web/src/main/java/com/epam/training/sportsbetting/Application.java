package com.epam.training.sportsbetting;


import com.epam.training.sportsbetting.data.TestDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Application implements CommandLineRunner {

    @Autowired
    public SportsBetting sportsBetting;

    @Autowired
    public TestDataLoader dataLoader;

    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void run(String... args) {
        dataLoader.testJpa();
        sportsBetting.play();
    }
}
