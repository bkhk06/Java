package com.hawk.readinglist;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReadinglistApplication {

    @Value("${tomcat.ajp.port}")
    private static int ajpPort;
    @Value("${tomcat.ajp.remoteauthenticatio}")
    private static boolean remote;
    @Value("${tomcat.ajp.enabled}")
    private static boolean enabled;

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
        return server -> {
            if (server instanceof TomcatServletWebServerFactory) {
                ((TomcatServletWebServerFactory)   server).addAdditionalTomcatConnectors(redirectConnector());
            }
        };
    }

    private  Connector redirectConnector() {
        System.out.println("ajpPort is : "+ajpPort);
        Connector connector = new Connector("AJP/1.3");
        connector.setScheme("http");
        connector.setPort(9098);
        connector.setSecure(false);
        connector.setAllowTrace(false);
        return connector;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReadinglistApplication.class, args);
    }

}
