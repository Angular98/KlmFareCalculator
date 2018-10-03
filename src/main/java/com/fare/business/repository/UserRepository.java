package com.fare.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fare.business.authenticationdetails.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String username);

	
}
