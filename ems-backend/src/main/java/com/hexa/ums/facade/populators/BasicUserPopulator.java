package com.hexa.ums.facade.populators;

import com.hexa.ums.converter.Populator;
import com.hexa.ums.dto.UserDto;
import com.hexa.ums.exception.ConversionException;
import com.hexa.ums.model.User;
import org.springframework.stereotype.Component;

@Component("basicUserPopulator")
public class BasicUserPopulator implements Populator<User, UserDto> {
    @Override
    public UserDto populate(User user) throws ConversionException {
        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setSubordinatesUsers(null);
        return userDto;
    }
}
