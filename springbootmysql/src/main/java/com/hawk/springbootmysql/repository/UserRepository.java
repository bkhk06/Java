package com.hawk.springbootmysql.repository;

import com.hawk.springbootmysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Liu.DA on 2019/6/13
 */
@Repository
public interface UserRepository extends JpaRepository <User,Long>{
    User findByNameLike(String name);
    User readByName(String name);
    List<User> getByCreatedateLessThan(Date star);
}
