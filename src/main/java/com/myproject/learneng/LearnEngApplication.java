package com.myproject.learneng;

import com.myproject.learneng.util.PhrasesPopulator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class LearnEngApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(LearnEngApplication.class, args);

	}

}
