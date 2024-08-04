package com.edu.camel.firlst_camel_app.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMQSenderRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer://test?period=1000")
                .setBody(simple("Welcome friend!"))
                .to("activemq:test-mq");
    }
}
