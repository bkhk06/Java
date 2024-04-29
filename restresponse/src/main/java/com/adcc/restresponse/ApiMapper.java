package com.adcc.restresponse;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ApiMapper {

    @Select("select * from response_times")
    List<ResponseTimeEntity> findAll();
}

