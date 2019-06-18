package ld.springboot_jpa_and_page_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootJpaAndPageDemoApplication {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaAndPageDemoApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception{
        //save a cuple of users;
        userRepository.save(new User("Jack","Bauer"));
        userRepository.save(new User("Chloe", "O'Brian"));
        userRepository.save(new User("Kim", "Bauer"));
        userRepository.save(new User("David", "Palmer"));
        userRepository.save(new User("Michelle", "Dessler"));

        // fetch all users
        System.out.println("User found with findAll():");
        System.out.println("-------------------------------");
        for (User user : userRepository.findAll()) {
            System.out.println(user);
        }
        System.out.println();

        // fetch an individual user by ID
        /*User user = userRepository.findById();
        System.out.println("User found with findOne(1L):");
        System.out.println("--------------------------------");
        System.out.println(user);
        System.out.println();*/

        // fetch customers by last name
        System.out.println("User found with findByLastName('Bauer'):");
        System.out.println("--------------------------------------------");
        for (User user : userRepository.findByLastName("Bauer")) {
            System.out.println(user);
        }

    }
}
