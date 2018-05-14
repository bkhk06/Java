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
   
public class Send {  
    
	private static final String url = "failover:(tcp://192.168.13.253:61616,tcp://192.168.11.68:61616)?jms.useAsyncSend=true&randomize=false";
    private static final String QUEUE_NAME = "G2Queue";  
   
    public void sendMessage() throws JMSException {  
      
       Connection connection = null;
       Session session = null;
       int liveSeconds = 3;
       try {  
          
           ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);  
           connection = connectionFactory.createConnection();  
          
           connection.start();  
          
           session = connection.createSession(false,  
                  Session.AUTO_ACKNOWLEDGE);  
           
           Destination destination = session.createQueue(QUEUE_NAME);  
           
           MessageProducer producer =session.createProducer(destination);  
         
           producer.setDeliveryMode(DeliveryMode.PERSISTENT);  
            
           String msg = "";  
           int i = 0;  
        do {  
            msg = "The "+i +"th " + "msg_"+new Random();  
                TextMessage message = session.createTextMessage(msg);  
                Thread.sleep(1);  
                producer.send(message);  
                if(liveSeconds>0) {
                	//jmsMessagingTamplate.getJmsTemplate().SetExplicitQosEnabled(true);
                	//jmsMessagingTamplate.getJmsTemplate().setTimeToLive(liveSeconds*1000);
                	//producer.SetExplicitQosEnabled(true);
                	producer.setTimeToLive(liveSeconds*1000);
                	
                }
                
                System.out.println("The send msg is:" +msg);  
                i++;  
        } while (true);  
       } catch (Exception e) {  
           e.printStackTrace();  
       }  finally 
       {
    	   connection.close();
    	   session.close();
    	   
       }
    }  
   
    public static void main(String[] args) {  
       Send sndMsg = new Send();  
       try {  
           sndMsg.sendMessage();  
       } catch (Exception ex) {  
           System.out.println(ex.toString());  
       }  
    }  
}  

