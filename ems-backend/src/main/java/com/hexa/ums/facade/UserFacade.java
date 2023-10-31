package com.hexa.ums.facade;

import com.hexa.ums.dto.UserDto;
import com.hexa.ums.model.UserType;
import com.hexa.ums.web.request.UserRequest;

import java.util.List;
import java.util.Map;

public interface UserFacade {
    UserDto save(UserRequest userRequest);

    UserDto findByUserId(Long userId);

    UserDto update(UserRequest userRequest);

    Map<String, Boolean> deleteUser(Long userId);

    List<UserDto> findAll();

    List<UserDto> findAvailableReportingByUserType(String type);

    List<String> findAllUserTypes();
}
