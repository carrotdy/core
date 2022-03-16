package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성이 모두 자동으로 설정
//@SpringBootApplication = @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan
@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
