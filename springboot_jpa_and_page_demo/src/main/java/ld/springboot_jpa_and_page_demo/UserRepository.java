package ld.springboot_jpa_and_page_demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findByLastName(String lastName);
}
