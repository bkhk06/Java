package cn.adcc.mongorepository;

import java.util.List;

/**
 * @author LD
 * @version 1.0
 * @date 2022/3/9 13:55
 */
public interface NotifyMsgService {

    NotifyMsg saveNotifyMsg(NotifyMsg msg);

    NotifyMsg findNotifyMsgByNo(String notifyNo);

    List<NotifyMsg> findNotifyMsgByDate(String notifyDate);

    NotifyMsg delNotifyMsgById(String id);
}
