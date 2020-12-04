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
        //限航路点制方向
        String direction = "";
        //受控起飞机场
        String controlDepAp = "";
        //豁免起飞机场
        String exemptDepAp = "";
        //受控降落机场
        String controlArrAp = "";
        //豁免降落机场
        String exemptArrAp = "";
        //获取航路点
        String points = "";
        //
        // 创建复合流控返回结果
        List<FlowControlInfo> compositeFlowcontrols = new ArrayList<FlowControlInfo>();
        while (it.hasNext()) {
            // 新建流控对象
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
            //航路点限制方向
            direction = "";
            //受控起飞机场
            controlDepAp = "";
            //豁免起飞机场 
            exemptDepAp = "";
            //受控降落机场
            controlArrAp = "";
            //豁免降落机场
            exemptArrAp = "";
            //获取航路点
            points = "";

            Map<String, String> singleFlowMap = it.next();
            direction = singleFlowMap.get("DIRECTION");
            //获取组成RULE的各个元素（起降机场和航路点）
            String[] ruleArray = singleFlowMap.get("RULE").split("&");
            for (int index = 0; index < ruleArray.length; index++) {
                // 获取起飞机场
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
                // 获取降落机场
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
                // 获取降落机场
                if (ruleArray[index].indexOf("POINT") > -1) {
                    points = points + ruleArray[index].split("=")[1];
                }
            }
            //子流控设置受控起飞机场
            if (StringUtils.isNotBlank(controlDepAp)) {
                FlowControlInfo.setControlDepDirection(controlDepAp.substring(0, controlDepAp.length() - 1));
            }
            //子流控设置受控降落机场
            if (StringUtils.isNotBlank(controlArrAp)) {
                FlowControlInfo.setControlDirection(controlArrAp.substring(0, controlArrAp.length() - 1));
            }
            //子流控设置豁免起飞机场
            if (StringUtils.isNotBlank(exemptDepAp)) {
                FlowControlInfo.setExemptDepDirection(exemptDepAp.substring(0, exemptDepAp.length() - 1));
            }
            //子流控设置豁免降落机场
            if (StringUtils.isNotBlank(exemptArrAp)) {
                FlowControlInfo.setExemptDirection(exemptArrAp.substring(0, exemptArrAp.length() - 1));
            }
            //子流控设置航路限制方向
            FlowControlInfo.setFlowcontrolDirection(direction);
            //子流控设置受限航路点
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
     * 复合流控限制条件
     */
    private String compositeCondition;
}
