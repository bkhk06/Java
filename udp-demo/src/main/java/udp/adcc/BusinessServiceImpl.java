package udp.adcc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author LD
 * @version 1.0
 * @date 2021/12/3 11:07
 */
@Service
public class BusinessServiceImpl<BusinessService> extends BusinessService {

    private static final Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);

    @Override
    @Async("threadPoolTaskExecutor")
    public void udpHandleMethod(String message) throws Exception {
        logger.info("业务开始处理");
        Thread.sleep(3000);
        logger.info("业务处理完成");
    }
}