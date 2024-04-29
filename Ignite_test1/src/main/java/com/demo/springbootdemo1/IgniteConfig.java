package com.demo.springbootdemo1;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IgniteConfig {
    @Bean
    public Ignore igniteInstance() {

        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Arrays.asList("127.0.0.1:47500"));
        spi.setIpFinder(ipFinder);

        IgniteConfiguration igniteConfig = new IgniteConfiguration();
        igniteConfig.setIgniteInstanceName("TestInstance");
        igniteConfig.setDiscoverySpi(spi);
        igniteConfig.setClientMode(true);

        CacheConfiguration cacheConfig = new CacheConfiguration("TestCache");
        cacheConfig.setIndexedTypes(Long.class, TestObject.class);
        igniteConfig.setCacheConfiguration(cacheConfig);
        return Ignition.start(igniteConfig);
    }
}
