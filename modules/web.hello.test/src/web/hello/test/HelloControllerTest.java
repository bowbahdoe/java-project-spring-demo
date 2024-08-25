package web.hello.test;

import org.junit.jupiter.api.Test;
import web.hello.HelloController;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloControllerTest {
    @Test
    public void getHello() {
        assertEquals(
                new HelloController().index(),
                "Greetings from Spring Boot!"
        );
    }
}