package tobyspring.hello;

import java.util.Objects;

public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


    public String hello(String name) {

        return helloService.sayHello(Objects.requireNonNull(name)); //name값이 null인것을 방지
    }


}
