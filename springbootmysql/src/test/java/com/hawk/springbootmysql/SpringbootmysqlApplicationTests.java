package com.hawk.springbootmysql;

import com.hawk.springbootmysql.entity.Department;
import com.hawk.springbootmysql.entity.Role;
import com.hawk.springbootmysql.entity.User;
import com.hawk.springbootmysql.repository.DepartmentRepository;
import com.hawk.springbootmysql.repository.RoleRepository;
import com.hawk.springbootmysql.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
//import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class SpringbootmysqlApplicationTests {
    private static Logger logger = LoggerFactory.getLogger(SpringbootmysqlApplicationTests.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    RoleRepository roleRepository;

    @Before
    public  void initData(){
        userRepository.deleteAll();
        roleRepository.deleteAll();
        departmentRepository.deleteAll();

        Department department = new Department();
        department.setName("dev");
        departmentRepository.save(department);
        Assert.assertNotNull(department.getId());

        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.assertNotNull(role.getId());

        User user = new User();
        user.setName("user");
        user.setCreatedate(new Date());
        user.setDepartment(department);

        List<Role> roles = roleRepository.findAll();
        Assert.assertNotNull(roles);
        user.setRoles(roles);

        userRepository.save(user);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void findPage(){
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
        Page<User> page = userRepository.findAll(pageable);
        Assert.assertNotNull(page);
        for(User user : page.getContent()) {
            logger.info("====user==== user name:{}, department name:{}, role name:{}",
                    user.getName(),user.getDepartment().getName(),user.getRoles().get(0).getName());           ;
        }
    }

    //@Test
    public void test(){
        User user1 = userRepository.findByNameLike("u%");
        Assert.assertNotNull(user1);

        User user2 = userRepository.readByName("user");
        Assert.assertNotNull(user2);

        List<User> users = userRepository.getByCreatedateLessThan(new Date());
        Assert.assertNotNull(users);
    }
    public void contextLoads() {
    }

}
