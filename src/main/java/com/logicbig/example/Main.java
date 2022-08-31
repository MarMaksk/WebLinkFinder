package com.logicbig.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

@SpringBootApplication
public class Main{

    public static void main (String[] args) {
        SpringApplication.run(Main.class, args);
    }


}