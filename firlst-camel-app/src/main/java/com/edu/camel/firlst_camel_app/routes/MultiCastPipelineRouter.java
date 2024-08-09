package com.edu.camel.firlst_camel_app.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class MultiCastPipelineRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet");

        rest("/payment")
                .get()
                .to("direct:payment-completed");

        from("direct:payment-completed")
                .setBody()
                .constant("Payment completed for your Online Purchase. Thanks!")
                //todos recebem a mesma mensagem
                .multicast()
                //todos recebem e escrevem paralelamente ao mesmo tempo
                .parallelProcessing()
                .to("direct:sender-bank-system")
                .to("direct:receiver-bank-system")
                .to("direct:online-shopping-system");

        from("direct:sender-bank-system")
//                .setBody()
//                .constant("Sender is received the payment!!!")
                .log("${body}...${threadName}");

        from("direct:receiver-bank-system")
                .setBody()
                .constant("Sender is received the payment!!!")
                .log("${body}...${threadName}");

        from("direct:online-shopping-system")
                .log("${body}...${threadName}");
    }
}
