package com.edu.camel.firlst_camel_app.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMQReceiverRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        //mesmo nome da fila criada: http://localhost:8161/admin/queues.jsp
        from("activemq:test-amq")
                .log("log:receiving msg => ${body}");
    }
}
