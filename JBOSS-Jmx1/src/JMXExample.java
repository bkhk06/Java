
import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
 
public class JMXExample {
 
    public static void main(String[] args) throws Exception {
        //Get a connection to the JBoss AS MBean server on localhost
        String host = "192.168.11.101";
        int port = 1090;
        String urlString = System.getProperty("jmx.service.url", 
            //"service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi");
        	"service:jmx:rmi://"+host + "/jndi/rmi://"+host+":"+port+"/jmxconnector");
        JMXServiceURL serviceURL = new JMXServiceURL(urlString);
        JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, null);
        MBeanServerConnection connection = jmxConnector.getMBeanServerConnection();
 
        //Invoke on the JBoss AS MBean server
        int count = connection.getMBeanCount();
        System.out.println(count);
       }
}


/*import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
 
public class JMXExample {
 
    public static void main(String[] args) throws Exception {
        //Get a connection to the WildFly 8 MBean server on localhost
        String host = "192.168.11.68";
        int port = 9990;  // management-web port
        String urlString =
            System.getProperty("jmx.service.url","service:jmx:http-remoting-jmx://" + host + ":" + port);
        JMXServiceURL serviceURL = new JMXServiceURL(urlString);
        JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, null);
        MBeanServerConnection connection = jmxConnector.getMBeanServerConnection();
 
        //Invoke on the WildFly 8 MBean server
        int count = connection.getMBeanCount();
        System.out.println(count);
        jmxConnector.close();
    }
}*/