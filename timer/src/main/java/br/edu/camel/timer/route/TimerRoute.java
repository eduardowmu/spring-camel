package br.edu.camel.timer.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerRoute extends RouteBuilder {
    private static final String PATH_FILE = "file/input";

    @Override
    public void configure() throws Exception {
        from("timer:my-timer?period=5000&repeatCount=6&time=2023-05-16 00:24:00")
                .log("Hora: ${date:now:HH:mm:ss}");
    }
}
