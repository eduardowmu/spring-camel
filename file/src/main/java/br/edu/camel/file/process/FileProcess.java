package br.edu.camel.file.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FileProcess implements Processor {
    /*o objeto de parametro deste metodo e do tipo de intercambio que
    * esta acontecendo na execuçao do fluxo ate o final*/
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("Processor: " + exchange.getIn().getBody());
    }
}
