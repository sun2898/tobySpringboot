package tobyspring.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

//Configuration 어노테이션이 붙은 class 는 맨처음 container 에 등록된다
@Configuration
@ComponentScan // Component 가 붙은 class 들을 찾아서 Bean 으로 등록을 해라!
public class HelloApplication {

	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}
}
