package com.exampleSpringBoot.restful_web_services.jpa;
import org.springframework.data.jpa.repository.JpaRepository;

import com.exampleSpringBoot.restful_web_services.users.Users;

public interface UserRepository extends JpaRepository<Users,Integer>{

}
