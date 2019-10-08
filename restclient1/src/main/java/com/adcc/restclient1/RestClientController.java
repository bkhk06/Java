package com.adcc.restclient1;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Liu.DA on 2019/9/29
 */
public class RestClientController {
    @Value("$rest-api")
    private static String REST_API;


}
