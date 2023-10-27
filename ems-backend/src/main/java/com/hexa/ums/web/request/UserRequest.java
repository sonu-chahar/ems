package com.hexa.ums.web.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import javax.validation.constraints.Pattern.Flag;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserRequest {

    @NotEmpty(message = "Name is required.")
    @Size(min = 2, max = 100, message = "The length of name must be between 2 and 100 characters.")
    private String name;

    @JsonAlias({"id"})
    private Long employeeId;

    @NotNull(message = "The department id is required.")
    private Integer departmentId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfJoining;

    @NotNull(message = "The date of birth is required.")
    @Past(message = "The date of birth must be in the past.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull(message = "The date of birth is required.")
    @Email(message = "The email address is invalid.", flags = {Flag.CASE_INSENSITIVE})
    private String emailId;

    @NotNull(message = "The user type is required.")
    private String userType;

    @NotEmpty(message = "Phone number is required.")
    @Size(min = 9, max = 12, message = "The length of phone number must be between 9 and 12 characters.")
    private String phoneNumber;

    private Long reportingUserId;

    private List<Long> subordinatesUserIds;

//    private Boolean isEmailVerified;
//    private Boolean isMobileVerified;
}
