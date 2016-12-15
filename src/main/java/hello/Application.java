package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@PropertySource("classpath:/appProp.properties")
public class Application {
	

	@Value("${com.sampleProp1}")
	String beanName;
	
	@Bean
	protected RestTemplate resetTemplate() { 
		System.out.println("beanName:"+beanName);
		return new RestTemplate() {
		};
	}

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}

}
