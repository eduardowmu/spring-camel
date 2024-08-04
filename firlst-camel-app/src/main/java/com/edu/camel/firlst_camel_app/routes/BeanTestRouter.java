package com.edu.camel.firlst_camel_app.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class BeanTestRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:bean-test-timer?period=5000")
                .bean("calculator","sub")
                .to("log:bean-test-timer");
    }
}

//@Component
class Calculator {
    public String add() {
        return "Welcome to add method!";
    }

    public String sub() {
        return "Welcome to sub method!";
    }
}