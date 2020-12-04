package com.adcc.cdm.slot.bean;

//import com.adcc.flowcontrol.service.FlowcontrolMaintanceServiceBean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CompositeFlow {


    /**
     *
     */
    private static Logger logger = Logger.getLogger(CompositeFlow.class);

    public static List<FlowControlInfo> parseCompositeFlowcontrol(FlowControlInfo fcInfo) {
        Gson gson = new Gson();
        Type typeToken = new TypeToken<List<Map<String, String>>>() {
        }.getType();
        List<Map<String, String>> compositeCondition = gson.fromJson(fcInfo.getCompositeCondition(), typeToken);
        Iterator<Map<String, String>> it = compositeCondition.iterator();
        //�޺�·���Ʒ���
        String direction = "";
        //�ܿ���ɻ���
        String controlDepAp = "";
        //������ɻ���
        String exemptDepAp = "";
        //�ܿؽ������
        String controlArrAp = "";
        //���⽵�����
        String exemptArrAp = "";
        //��ȡ��·��
        String points = "";
        //
        // �����������ط��ؽ��
        List<FlowControlInfo> compositeFlowcontrols = new ArrayList<FlowControlInfo>();
        while (it.hasNext()) {
            // �½����ض���
            FlowControlInfo FlowControlInfo = new FlowControlInfo();
            try {
                BeanUtils.copyProperties(FlowControlInfo, fcInfo);
            } catch (IllegalAccessException e) {
                if (logger.isInfoEnabled()) {
                    logger.info("create flowcontrol with BeanUtils failed: illegal access");
                }
            } catch (InvocationTargetException e) {
                if (logger.isInfoEnabled()) {
                    logger.info("create flowcontrol with BeanUtils failed: invocation target");
                }
            }
            //��·�����Ʒ���
            direction = "";
            //�ܿ���ɻ���
            controlDepAp = "";
            //������ɻ��� 
            exemptDepAp = "";
            //�ܿؽ������
            controlArrAp = "";
            //���⽵�����
            exemptArrAp = "";
            //��ȡ��·��
            points = "";

            Map<String, String> singleFlowMap = it.next();
            direction = singleFlowMap.get("DIRECTION");
            //��ȡ���RULE�ĸ���Ԫ�أ��𽵻����ͺ�·�㣩
            String[] ruleArray = singleFlowMap.get("RULE").split("&");
            for (int index = 0; index < ruleArray.length; index++) {
                // ��ȡ��ɻ���
                if (ruleArray[index].indexOf("DEPAP") > -1) {
                    String[] depAps = ruleArray[index].split("=")[1].split(",");
                    for (int ins = 0; ins < depAps.length; ins++) {
                        if (depAps[ins].indexOf("!") > -1) {
                            exemptDepAp = exemptDepAp + depAps[ins].substring(1) + ",";
                        } else {
                            controlDepAp = controlDepAp + depAps[ins] + ",";
                        }
                    }
                }
                // ��ȡ�������
                if (ruleArray[index].indexOf("ARRAP") > -1) {
                    String[] arrAps = ruleArray[index].split("=")[1].split(",");
                    for (int ins = 0; ins < arrAps.length; ins++) {
                        if (arrAps[ins].indexOf("!") > -1) {
                            exemptArrAp = exemptArrAp + arrAps[ins].substring(1) + ",";
                        } else {
                            controlArrAp = controlArrAp + arrAps[ins] + ",";
                        }
                    }
                }
                // ��ȡ�������
                if (ruleArray[index].indexOf("POINT") > -1) {
                    points = points + ruleArray[index].split("=")[1];
                }
            }
            //�����������ܿ���ɻ���
            if (StringUtils.isNotBlank(controlDepAp)) {
                FlowControlInfo.setControlDepDirection(controlDepAp.substring(0, controlDepAp.length() - 1));
            }
            //�����������ܿؽ������
            if (StringUtils.isNotBlank(controlArrAp)) {
                FlowControlInfo.setControlDirection(controlArrAp.substring(0, controlArrAp.length() - 1));
            }
            //���������û�����ɻ���
            if (StringUtils.isNotBlank(exemptDepAp)) {
                FlowControlInfo.setExemptDepDirection(exemptDepAp.substring(0, exemptDepAp.length() - 1));
            }
            //���������û��⽵�����
            if (StringUtils.isNotBlank(exemptArrAp)) {
                FlowControlInfo.setExemptDirection(exemptArrAp.substring(0, exemptArrAp.length() - 1));
            }
            //���������ú�·���Ʒ���
            FlowControlInfo.setFlowcontrolDirection(direction);
            //�������������޺�·��
            FlowControlInfo.setControlPoints(points);


            compositeFlowcontrols.add(FlowControlInfo);
        }
        //
        return compositeFlowcontrols;
    }

    public String getCompositeCondition() {
        return compositeCondition;
    }

    /**
     * ����������������
     */
    private String compositeCondition;
}
