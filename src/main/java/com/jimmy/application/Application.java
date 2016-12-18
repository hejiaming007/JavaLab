package com.jimmy.application;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.jimmy.domain.Student;
import com.jimmy.service.StudentRepository;

@SpringBootApplication
@PropertySource("classpath:/appProp.properties")
//@ComponentScan(basePackageClasses = GreetingController.class)
@ComponentScan("com.jimmy")
@EnableJpaRepositories("com.jimmy")
@EntityScan("com.jimmy")//This line is mandatory, other wise will throw some Entity class is not a managed type.
public class Application {
	
	@Value("${com.sampleProp1}")
	String beanName;

	private static final Logger logger = LoggerFactory
			.getLogger(Application.class);
	
    @Autowired
    private StudentRepository studentRepository;
	
	@Bean
	protected RestTemplate resetTemplate() { 
		System.out.println("beanName:"+beanName);
		return new RestTemplate() {
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@PostConstruct
	public void logSomething() {
		Student s = new Student();
		s.setName("Jimmy");
		s.setSex("Male");
		s.setNickName("Jimbo");
		studentRepository.save(s);
		
		Student s2 = new Student();
		s2.setName("Angel");
		s2.setSex("Female");
		s2.setNickName("Angela");
		studentRepository.save(s2);
		
		
		logger.debug("Sample Debug Message");
		logger.trace("Sample Trace Message");
	}

}
