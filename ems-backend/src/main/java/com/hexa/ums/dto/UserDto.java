package com.hexa.ums.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserDto {
    private String name;

    private Long id;

    private Integer departmentId;

    private LocalDate dateOfJoining;

    private LocalDate dateOfBirth;

    private String emailId;

    private String phoneNumber;

    private UserDto reportingUser;

    private Long reportingUserId;

    private List<UserDto> subordinatesUsers = new ArrayList<>();

    private String userType;

    @JsonIgnore
    private Boolean isEmailVerified;
    @JsonIgnore
    private Boolean isMobileVerified;
}
