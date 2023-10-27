package com.hexa.ums.service;


import com.hexa.ums.model.User;
import com.hexa.ums.model.UserType;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getByUserId(Long userId);

    List<User> findAll();

    List<User> getByUserIdIn(List<Long> subordinatesUserIds);

    Boolean checkIfEmailIdExist(String emailId);

    User save(User user);

    Map<String, Boolean> deleteUser(Long userId);


    List<User> findAllByUserTypeIn(List<UserType> userTypeList);
}
