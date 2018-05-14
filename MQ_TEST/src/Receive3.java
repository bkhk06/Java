
import javax.jms.Connection;  
import javax.jms.ConnectionFactory;  
import javax.jms.Destination;  
import javax.jms.JMSException;  
import javax.jms.Message;  
import javax.jms.MessageConsumer;  
import javax.jms.Session;  
import javax.jms.TextMessage;  
   
import org.apache.activemq.ActiveMQConnectionFactory;  
   

public class Receive3 {  
	     private static final String url = "failover:(tcp://192.168.11.105:61616,tcp://192.168.11.106:61616,tcp://192.168.11.107:61616)?jms.useAsyncSend=true&randomize=false";//"tcp://192.168.11.105:61616";//
         private static final String QUEUE_NAME = "G6Queue";  
   
         public void receiveMessage() {  
                   Connection connection = null;  
                   try{  
                            try{  
                                     ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);  
                                     connection= connectionFactory.createConnection();  
                            }catch (Exception e) {  
                                     System.out.println(e.toString());  
                            }  
                            connection.start();  
                            Session session = connection.createSession(false,  Session.AUTO_ACKNOWLEDGE);  
                            Destination destination = session.createQueue(QUEUE_NAME);  
                            //��Ϣ�����ߣ�Ҳ����������  
                            MessageConsumer consumer = session.createConsumer(destination);  
                             
                            consumeMessagesAndClose(connection,session, consumer);  
                   }catch (Exception e) {  
                            System.out.println(e.toString());  
                   }  
         }  
         
         protected void consumeMessagesAndClose(Connection connection,  
                            Session session, MessageConsumer consumer) throws JMSException {  
                   do{  
                            Message message = consumer.receive(1);  
                            if("close".equals(message)){  
                                     consumer.close();  
                                     session.close();  
                                     connection.close();  
                            }  
                            if(message != null) {  
                                     onMessage(message);  
                            }  
                   }while (true);  
                    
         }  
   
         public void onMessage(Message message) {  
                   try{  
                            if(message instanceof TextMessage) {  
                                     TextMessage txtMsg = (TextMessage) message;  
                                     String msg = txtMsg.getText();  
                                     System.out.println("Received:" + msg);  
                            }  
                   }catch (Exception e) {  
                            e.printStackTrace();  
                   }  
   
         }  
   
         public static void main(String args[]) {  
                   Receive3 rm = new Receive3();  
                   rm.receiveMessage();  
         }  
}  