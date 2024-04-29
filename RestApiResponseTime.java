import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class RestApiResponseTime {
    public static void main(String[] args) {
        try {
            // 创建URL对象
            URL url = new URL("http://www.bing.com/"); // 替换为你的REST接口URL

            // 打开连接并记录开始时间
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            long startTime = System.currentTimeMillis();
            //Timestamp timestamp;
            System.out.println("Begin Time: "+startTime);

            //获取当前时间
            //SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            System.out.println("CurrentTime: "+formatter.format(date));

            //Date date = new Date(); 
            //formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
            //formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            System.out.println(formatter.format(date)); 

            

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
            System.out.println("End Time: "+endTime);

            // 计算响应时间（单位：毫秒）
            long responseTime = endTime - startTime;

            // 输出响应时间和响应内容
            System.out.println("Response Time: " + responseTime + " ms，与"+ "responseCode: "+responseCode);
            //System.out.println("Response Content: " + response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        UrlTest();

        
    }

    public static void UrlTest() {
        try {
            // 创建一个URL实例
            URL url = new URL("http://www.imooc.com");
            System.out.println("协议：" + url.getProtocol());
            System.out.println("主机：" + url.getHost());
            System.out.println("授权：" + url.getAuthority());
            System.out.println("内容：" + url.getContent());
            System.out.println("端口：" + url.getPort());
            System.out.println("文件路径：" + url.getPath());
            System.out.println("文件名：" + url.getFile());
            System.out.println("相对路径：" + url.getRef());
            System.out.println("查询字符串：" + url.getQuery());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
