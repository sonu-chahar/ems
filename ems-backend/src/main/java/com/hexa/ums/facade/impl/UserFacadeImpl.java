package com.hexa.ums.facade.impl;

import com.hexa.ums.converter.Populator;
import com.hexa.ums.dto.UserDto;
import com.hexa.ums.exception.ValidationException;
import com.hexa.ums.facade.UserFacade;
import com.hexa.ums.model.Department;
import com.hexa.ums.model.User;
import com.hexa.ums.model.UserType;
import com.hexa.ums.service.DepartmentService;
import com.hexa.ums.service.UserService;
import com.hexa.ums.web.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Component("userFacade")
public class UserFacadeImpl implements UserFacade {
    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Resource(name = "userPopulator")
    private Populator<User, UserDto> userPopulator;

    @Resource(name = "basicUserPopulator")
    private Populator<User, UserDto> basicUserPopulator;

    @Override
    public UserDto save(UserRequest userRequest) {
        if (userService.checkIfEmailIdExist(userRequest.getEmailId())) {
            throw new ValidationException("Email Id is already exist");
        }
        User user = handleUserAssociationForAddRequest(userRequest);

        Department department = departmentService.get(userRequest.getDepartmentId());

        if (department != null) {
            user.setDepartment(department);
        } else {
            throw new ValidationException("Invalid department Id");
        }

        user = userService.save(user);

        return userPopulator.populate(user);

    }

    @Override
    public UserDto findByUserId(Long userId) {
        User user = userService.getByUserId(userId);
        return userPopulator.populate(user);
    }

    @Override
    @Transactional
    public UserDto update(UserRequest userRequest) {
        if (userRequest.getEmployeeId() == null) {
            throw new ValidationException("User Id is missing");
        }
        User user = handleUserAssociationForUpdateRequest(userRequest);
        user = userService.save(user);
        return userPopulator.populate(user);
    }

    @Override
    public Map<String, Boolean> deleteUser(Long userId) {
        return userService.deleteUser(userId);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userService.findAll();
        return userPopulator.populateAll(users);
    }

    @Override
    public List<UserDto> findAvailableReportingByUserType(String type) {
        UserType userType;
        try {
            userType = UserType.valueOf(type);
            List<UserType> userTypeList = Arrays.stream(UserType.values())
                    .filter(userType1 -> userType1.ordinal() < userType.ordinal())
                    .collect(Collectors.toList());
            if (!userTypeList.isEmpty()) {
                List<User> userList = userService.findAllByUserTypeIn(userTypeList);
                return basicUserPopulator.populateAll(userList);
            }
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("user type is not known");
        }
        return new ArrayList<>();
    }


    private User handleUserAssociationForAddRequest(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .emailId(userRequest.getEmailId())
                .phoneNumber(userRequest.getPhoneNumber())
                .dateOfJoining(userRequest.getDateOfJoining())
                .dateOfBirth(userRequest.getDateOfBirth())
                .userType(UserType.valueOf(userRequest.getUserType()))
                .build();


        if (userRequest.getReportingUserId() != null) {
            user.setReportingUser(userService.getByUserId(userRequest.getReportingUserId()));
        }

        if (userRequest.getSubordinatesUserIds() != null && !userRequest.getSubordinatesUserIds().isEmpty()) {
            List<User> fetchedSubordinateUserList = userService.getByUserIdIn(userRequest.getSubordinatesUserIds());
            for (User fetchedSubUser : fetchedSubordinateUserList) {
                fetchedSubUser.setReportingUser(user);
            }

            user.setSubordinatesUsers(new HashSet<>(fetchedSubordinateUserList));
        }
        return user;
    }

    private User handleUserAssociationForUpdateRequest(UserRequest userRequest) {
        User fetchedUser = this.userService.getByUserId(userRequest.getEmployeeId());

        if (fetchedUser == null) {
            throw new ValidationException("Invalid userId");
        }

        fetchedUser.setName(userRequest.getName());
        fetchedUser.setEmailId(userRequest.getEmailId());
        fetchedUser.setPhoneNumber(userRequest.getPhoneNumber());
        fetchedUser.setDateOfJoining(userRequest.getDateOfJoining());
        fetchedUser.setDateOfBirth(userRequest.getDateOfBirth());
        fetchedUser.setUserType(UserType.valueOf(userRequest.getUserType()));

        // handling bidirectional reporting user association manually
        if (userRequest.getReportingUserId() != null) {
            User fetchedReportingUser = userService.getByUserId(userRequest.getReportingUserId());

            if (fetchedUser.getReportingUser() != null
                    && !fetchedUser.getReportingUser().equals(fetchedReportingUser)) {
                fetchedUser.getReportingUser().getSubordinatesUsers().remove(fetchedUser);
            } else {
                fetchedReportingUser.getSubordinatesUsers().add(fetchedUser);
            }
            fetchedUser.setReportingUser(fetchedReportingUser);
        } else {
            if (fetchedUser.getReportingUser() != null) {
                fetchedUser.getReportingUser().getSubordinatesUsers().remove(fetchedUser);
                fetchedUser.setReportingUser(null);
            }
        }

//        // handling bidirectional subordinates association manually
//        List<Long> userIds = userRequest.getSubordinatesUserIds();
//        if (userIds != null && !userIds.isEmpty()) {
//
//            List<User> newSubUserList = userService.getByUserIdIn(userIds);
//            Set<User> oldSubUsers = fetchedUser.getSubordinatesUsers();
//
//            for (User oldSubUser : oldSubUsers) {
//                if (!newSubUserList.contains(oldSubUser)) {
//                    oldSubUser.setReportingUser(null);
//                    oldSubUsers.remove(oldSubUser);
//                }
//            }
//            for (User newSubUser : newSubUserList) {
//                if (!oldSubUsers.contains(newSubUser)) {
//                    newSubUser.setReportingUser(fetchedUser);
//                    oldSubUsers.add(newSubUser);
//                }
//            }
//        }
        return fetchedUser;
    }
}
