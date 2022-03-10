package com.adcc.mongo;

import jdk.internal.instrumentation.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LD
 * @version 1.0
 * @date 2022/3/9 13:59
 */
@RestController
@RequestMapping("/template")
@Slf4j
public class MongoTemplateController {

    @Autowired
    NotifyMsgService notifyMsgService;

    @PostMapping("/add")
    public NotifyMsg add(NotifyMsg msg) {
       // Logger logger=null;
       // assert logger != null;
       // logger.info("mongoTemplate方式新增：{}",msg);
        return notifyMsgService.saveNotifyMsg(msg);
    }

    @PostMapping("del/{id}")
    public NotifyMsg del(@PathVariable String id) {
      //  log.info("mongoTemplate方式删除：{}", id);
        return notifyMsgService.delNotifyMsgById(id);
    }

    @GetMapping("/find/{no}")
    public NotifyMsg findNotifyMsgByNo(@PathVariable String no){
       // log.info("mongoTemplate方式查找：notifyNo-{}", no);
        return notifyMsgService.findNotifyMsgByNo(no);
    }

    @GetMapping("/find/list/{date}")
    public List<NotifyMsg> findNotifyMsgByDate(@PathVariable String date){
       // log.info("mongoTemplate方式查找：notifyDate-{}", date);
        return notifyMsgService.findNotifyMsgByDate(date);
    }
}
