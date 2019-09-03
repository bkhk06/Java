package com.redis_mybatis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Liu.DA on 2019/7/25
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/{id}")
    public Product getProductInfo(
            @PathVariable("id")

    ){

    }

}
