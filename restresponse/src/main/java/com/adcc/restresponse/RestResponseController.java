package com.adcc.restresponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Format.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@RestController
public class RestResponseController {

    @Autowired
    private ResponseTimeRepository responseTimeRepository;

    @RequestMapping(value = "/apiget", method=RequestMethod.GET)
       public String ApiMapper(Model m){
        List<ResponseTimeEntity> responseTimeEntities = ApiMapper.findAll();
        m.addAttribute("responseTimeEntities",responseTimeEntities);
        return "user";
    }


    

    @PostMapping("/apipost")
    public ResponseEntity<?> yourApiMethod() throws IOException  {
        long startTime = System.currentTimeMillis();
        // Your API logic here
        
            // 创建URL对象
            URL url = new URL("http://www.bing.com/"); // 替换为你的REST接口URL
            

            // 打开连接并记录开始时间
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //long startTime = System.currentTimeMillis();

            // 发送GET请求并获取响应码
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            // 读取响应内容（可选）
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 关闭连接并记录结束时间
            connection.disconnect();
            long endTime = System.currentTimeMillis();

            // 计算响应时间（单位：毫秒）
            long responseTime = endTime - startTime;

            // 输出响应时间和响应内容
            System.out.println("Response Time: " + responseTime + " ms"+ "responseCode: "+responseCode);
            //System.out.println("Response Content: " + response.toString());
        
        //
        //long endTime = System.currentTimeMillis();
        //double responseTime = (endTime - startTime) / 1000.0; // in seconds

        ResponseTimeEntity responseTimeEntity = new ResponseTimeEntity();
        responseTimeEntity.setApiEndpoint(url.getHost());
        responseTimeEntity.setResponseTime(responseTime);
        responseTimeEntity.setResponseCode(responseCode);
        responseTimeRepository.save(responseTimeEntity);

        return ResponseEntity.ok("Your API response");
    }
  
}

