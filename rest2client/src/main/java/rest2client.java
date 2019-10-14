import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by Liu.DA on 2019/10/9
 */
public class rest2client {
    //@Value("${rest-api}")
        private static String REST_API="http://192.168.11.101:9097/user";

        public static void main(String[] args) throws IOException {
            getUser();
            //addUser();
            getAllUsers();
        }

        public static void getUser() throws IOException {
            Client client = ClientBuilder.newClient();
            client.property("Content-Type","xml");
            Response response = client.target(REST_API + "/getuser?id=8").request().get();
            String str = response.readEntity(String.class);
            System.out.print("getUser result is : " + str + "\n");
        }

        /*public static void addUser(String name,String password,String salt, String role) {
            Client client = ClientBuilder.newClient();
            client.property("Content-Type","xml");

            PersonEntity entity = new PersonEntity("NO2", "Joker", "http://");
            Response response = client.target(REST_API + "/addUser/person").request().buildPost(Entity.entity(entity, MediaType.APPLICATION_JSON)).invoke();
            String str  = response.readEntity(String.class);
            System.out.print("addUser result is : " + str + "\n");
        }*/

        public static void getAllUsers() throws IOException {
            Client client = ClientBuilder.newClient();
            client.property("Content-Type","xml");
            Response response = client.target(REST_API + "/getalluser").request().get();
            String str = response.readEntity(String.class);
            System.out.print("getAllUsers result is : " + str + "\n");

        }
}
