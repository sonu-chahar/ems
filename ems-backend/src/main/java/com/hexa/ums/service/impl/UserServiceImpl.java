package com.hexa.ums.service.impl;

import com.hexa.ums.exception.ResourceNotFoundException;
import com.hexa.ums.model.User;
import com.hexa.ums.model.UserType;
import com.hexa.ums.repository.UserRepository;
import com.hexa.ums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User getByUserId(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + userId));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> getByUserIdIn(List<Long> subordinatesUserIds) {
        return this.userRepository.findAllById(subordinatesUserIds);
    }

    @Override
    public Boolean checkIfEmailIdExist(String emailId) {
        return this.userRepository.findByEmailId(emailId) != null;
    }

    @Override
    @Transactional
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Transactional
    @Override
    public Map<String, Boolean> deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + userId));

        Set<User> subordinateUsers = user.getSubordinatesUsers();
        if (subordinateUsers != null && !subordinateUsers.isEmpty()) {
            for (User subordianteUser : subordinateUsers) {
                subordianteUser.setReportingUser(user.getReportingUser());
            }
        }

        if (user.getReportingUser() != null) {
            user.setReportingUser(null);
        }

        userRepository.delete(user);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @Override
    public List<User> findAllByUserTypeIn(List<UserType> userTypeList) {
        return this.userRepository.findAllByUserTypeIn(userTypeList);
    }
}
