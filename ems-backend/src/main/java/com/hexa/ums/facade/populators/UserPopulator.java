package com.hexa.ums.facade.populators;

import com.hexa.ums.converter.Populator;
import com.hexa.ums.dto.UserDto;
import com.hexa.ums.exception.ConversionException;
import com.hexa.ums.model.User;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component("userPopulator")
public class UserPopulator implements Populator<User, UserDto> {
    @Override
    public UserDto populate(User user) throws ConversionException {
        UserDto userDto = populateUserDtoBasicDetails(user);

        if (user.getReportingUser() != null) {
            userDto.setReportingUser(populateReportingUsers(user.getReportingUser()));
            userDto.setReportingUserId(user.getReportingUser().getUserId());
        }

        if (user.getSubordinatesUsers() != null && !user.getSubordinatesUsers().isEmpty()) {

            List<UserDto> subordinateUserDtoList = user.getSubordinatesUsers()
                    .stream()
                    .sorted(Comparator.comparing(User::getName))
                    .map(this::populateUserDtoBasicDetails)
                    .collect(Collectors.toList());
            userDto.setSubordinatesUsers(subordinateUserDtoList);
        }
        return userDto;
    }

    private UserDto populateReportingUsers(User user) throws ConversionException {
        UserDto userDto = populateUserDtoBasicDetails(user);
        if (user.getReportingUser() != null) {
            userDto.setReportingUser(populateReportingUsers(user.getReportingUser()));
        }
        return userDto;
    }

    private UserDto populateUserDtoBasicDetails(User user) throws ConversionException {
        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setEmailId(user.getEmailId());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setUserType(user.getUserType().name());
        userDto.setDepartmentId(user.getDepartment().getDepartmentId());
        userDto.setDateOfJoining(user.getDateOfJoining());
        userDto.setDateOfBirth(user.getDateOfBirth());
        return userDto;
    }

    @Override
    public List<UserDto> populateAll(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getName))
                .map(this::populateUserDtoBasicDetails)
                .collect(Collectors.toList());
    }
}
