

import java.util.Random;  
   
import javax.jms.Connection;  
import javax.jms.ConnectionFactory;  
import javax.jms.DeliveryMode;  
import javax.jms.Destination;  
import javax.jms.JMSException;  
import javax.jms.MessageProducer;  
import javax.jms.Session;  
import javax.jms.TextMessage;  
   
import org.apache.activemq.ActiveMQConnectionFactory;  
   
public class Send3 {  
    
	private static final String url = "failover:(tcp://192.168.11.105:61616,tcp://192.168.11.106:61616,tcp://192.168.11.107:61616)?jms.useAsyncSend=true&randomize=false";
    private static final String QUEUE_NAME = "G6Queue";  
   
    public void sendMessage() throws JMSException {  
      
       Connection connection = null;  
       try {  
           // ���ӹ�����JMS ������������  
           // ����ConnectionFactoryʵ�����󣬴˴�����ActiveMq��ʵ��jar  
           ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);  
           connection = connectionFactory.createConnection();  
           // ��������  
           connection.start();  
           //Session�����ͻ������Ϣ���߳�  
           // ��ȡsession  
           Session session = connection.createSession(false,  
                  Session.AUTO_ACKNOWLEDGE);  
           // ��Ϣ��Ŀ�ĵأ���Ϣ���͵��Ǹ�����  
           Destination destination = session.createQueue(QUEUE_NAME);  
           //MessageProducer����Ϣ�����ߣ������ߣ�  
           // ������Ϣ������  
           MessageProducer producer =session.createProducer(destination);  
           // �����Ƿ�־û�  
           //DeliveryMode.NON_PERSISTENT�����־û�  
           //DeliveryMode.PERSISTENT���־û�  
           producer.setDeliveryMode(DeliveryMode.PERSISTENT);  
            
           String msg = "";  
           int i = 0;  
        do {  
            msg = "��"+i + "�η��͵���Ϣ��"+new Random();  
                TextMessage message = session.createTextMessage(msg);  
                Thread.sleep(1);  
                // ������Ϣ��Ŀ�ĵط�  
               producer.send(message);  
                System.out.println("������Ϣ��" +msg);  
                i++;  
        } while (i<100000000);  
       } catch (Exception e) {  
           e.printStackTrace();  
       }  
    }  
   
    public static void main(String[] args) {  
       Send3 sndMsg = new Send3();  
       try {  
           sndMsg.sendMessage();  
       } catch (Exception ex) {  
           System.out.println(ex.toString());  
       }  
    }  
}  

