package com.adcc;

import com.adcc.cdm.fc.bean.FlightCoordination;
import com.adcc.cdm.pulsarClient.PulsarServiceClient;
import com.adcc.cdm.slot.bean.FlightAutoslot;
import com.adcc.cdm.slot.bean.FlowControlInfo;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class pulsarTest {
    private static final Logger logger = Logger.getLogger(pulsarTest.class);

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public static void main(String[] args) {

//        producer.sendOnce("Hello Pulsar");

//        String sendMsg = readToString(fliePath);

        String sendMsg = "Hello Pulsar";
        List<Object> data = new ArrayList<>();
        data.add(sendMsg);

        PulsarServiceClient client = new PulsarServiceClient();
        System.out.printf("%s Pulsar send data start...\n", df.format(new Date()));
        List<Object> objectList = new ArrayList<>();
        FlightAutoslot flightAutoslot1 = new FlightAutoslot((long)900000001);
        FlightAutoslot flightAutoslot2 = new FlightAutoslot((long)900000002);
        flightAutoslot1.setCto("202002101959");
        flightAutoslot1.setSource(1);
        flightAutoslot2.setCto("202002101950");
        flightAutoslot2.setSource(2);
        objectList.add(flightAutoslot1);
        objectList.add(flightAutoslot2);

//        FlowControlInfo flowControlInfo = new FlowControlInfo();
//        flowControlInfo.setId((long)97244);
//        flowControlInfo.setName("test");
//        flowControlInfo.setType("test");
//        objectList.add(flowControlInfo);

//        FlightCoordination flightCoordination = new FlightCoordination();
//        flightCoordination.setId((long)993678042);
//        flightCoordination.setClearanceStatus(1);
//        flightCoordination.setPriority(1);
//        flightCoordination.setDeiceStatus(1);
//        objectList.add(flightCoordination);

        boolean result = client.pulsarSend(1, objectList);
        if(result){
            System.out.printf("%s Pulsar send data successfully.\n", df.format(new Date()));
        }else{
            System.out.printf("%s Pulsar send data failed.\n", df.format(new Date()));
        }
//        for (int i = 0; i < 1; i++) {
////            producer.sendMessage(sendMsg);
//            client.pulsarSend(1, objectList);
////            producer.sendMessage("Hello Pulsar " + i);
//
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        System.out.printf("%s Pulsar send data end.\n", df.format(new Date()));
//        try {
//            Thread.sleep(10000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
