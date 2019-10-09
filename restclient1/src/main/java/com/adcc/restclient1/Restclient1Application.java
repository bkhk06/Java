package com.adcc.restclient1;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Restclient1Application {

    //@Value("${rest-api}")
    private static String REST_API="http://192.168.11.101:9097/user";

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Restclient1Application.class, args);
        getRandomResource();
        //addResource();
        getAllResource();
    }

    public static void getRandomResource() throws IOException {
        Client client = ClientBuilder.newClient();
        client.property("Content-Type","xml");
        Response response = client.target(REST_API + "/getuser?id=4").request().get();
        String str = response.readEntity(String.class);
        System.out.print("getRandomResource result is : " + str + "\n");
    }

    public static void addResource() {
        Client client = ClientBuilder.newClient();
        //PersonEntity entity = new PersonEntity("NO2", "Joker", "http://");
        //Response response = client.target(REST_API + "/addResource/person").request().buildPost(Entity.entity(entity, MediaType.APPLICATION_JSON)).invoke();
        //String str  = response.readEntity(String.class);
        //System.out.print("addResource result is : " + str + "\n");
    }

    public static void getAllResource() throws IOException {
        Client client = ClientBuilder.newClient();
        client.property("Content-Type","xml");
        Response response = client.target(REST_API + "/getalluser").request().get();
        String str = response.readEntity(String.class);
        System.out.print("getAllResource result is : " + str + "\n");

    }



}
