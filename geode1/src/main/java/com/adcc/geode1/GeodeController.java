package com.adcc.geode1;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;

import java.util.Map;

/**
 * @author LD
 * @version 1.0
 * @date 2021/9/10 15:55
 */
public class GeodeController {
    public void geodeControl() throws Exception{
        ClientCache cache = new ClientCacheFactory()
                .addPoolLocator("192.168.11.85", 10334)
                .create();
        Region<String, String> region = cache
                .<String, String>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
                .create("hello");

        region.put("1", "Hello");
        region.put("2", "World");

        for (Map.Entry<String, String>  entry : region.entrySet()) {
            System.out.format("key = %s, value = %s\n", entry.getKey(), entry.getValue());
        }
        cache.close();
    }
}
