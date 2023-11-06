package tobyspring.hello;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HelloApplication {
	public static void main(String[] args) {
		//Spring Container 생성
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
		applicationContext.registerBean(HelloController.class); //object를 넘기는 것이 아니고 class 정보만 넘긴다
		applicationContext.registerBean(SimpleHelloService.class);
		applicationContext.refresh();

		//Servlet Container를 코드로 생성 + 등록
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("dispatcherServlet",
						new DispatcherServlet(applicationContext)
					).addMapping("/*");
		});
		webServer.start();
	}

}
