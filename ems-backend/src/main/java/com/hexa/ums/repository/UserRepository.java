package com.hexa.ums.repository;

import com.hexa.ums.model.User;
import com.hexa.ums.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailId(String emailId);

    List<User> findAllByUserTypeIn(List<UserType> userTypeList);
}