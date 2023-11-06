package tobyspring.hello;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String... args) {
        //Spring Container 생성
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                //Servlet Container 코드로 생성 + 등록
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                //dispatcherServlet.setApplicationContext(this); //빈 주입

                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet
                    ).addMapping("/*");
                });
                webServer.start();
            }
        };
        applicationContext.register(applicationClass); //object를 넘기는 것이 아니고 class 정보만 넘긴다
        applicationContext.refresh();
    }
}
