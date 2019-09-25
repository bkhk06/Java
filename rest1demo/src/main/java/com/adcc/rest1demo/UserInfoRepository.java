package com.adcc.rest1demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Liu.DA on 2019/9/24
 */
public interface UserInfoRepository extends CrudRepository<UserInfo,Integer> {
    UserInfo findUserInfoById(int id);
    List<UserInfo> findUserInfoByRole(String role);

    @Query(value = "select * from t_user limit ?1", nativeQuery = true)
    List<UserInfo> findAllUsersByCount(int count);
}
