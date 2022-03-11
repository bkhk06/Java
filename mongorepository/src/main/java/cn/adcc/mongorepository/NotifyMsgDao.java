package cn.adcc.mongorepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * @author LD
 * @version 1.0
 * @date 2022/3/11 10:21
 */
public interface NotifyMsgDao extends MongoRepository<NotifyMsg, String> {

    /*
     * 根据消息号进行查询
     */
    NotifyMsg findByNotifyNo(String notifyNo);

    //需要注意 查询的语法结构 ，同时这里和`jpa`不一样的地方是，第一个索引值从0 开始。。
    @Query("{'notifyDate':?0}")
    Page<NotifyMsg> queryBySql(String notifyDate, Pageable pageable);
}
