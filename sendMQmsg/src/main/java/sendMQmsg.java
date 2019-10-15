/**
 * Created by Liu.DA on 2019/10/14
 */
public class sendMQmsg {

    public static void main(String[] args) {
        //String path="192.168.13.208:9290/sendTopic?msg=testTopic";
        //String data="";
        SendMsg sendMsg = new SendMsg();
        try {
            SendMsg.interfaceUtil("192.168.13.208:9290/sendTopic?msg=testTopic","");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
