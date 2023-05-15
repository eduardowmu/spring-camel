package br.edu.camel.file.bean;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileComponent {
    /*este componente, sendo definido como na linha 16 da classe
    * FileRoute, exige que este componente tenha pelo menos um
    * metodo.*/
    public void log(File file) {
        System.out.println("FileComponente: ".concat(file.getName()));
    }
}
