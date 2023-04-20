package com.microservice.User.Repositories;

import com.microservice.User.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

}
