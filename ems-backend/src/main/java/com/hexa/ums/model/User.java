package com.hexa.ums.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "sequenceUserPkIdGenerator")
    @GenericGenerator(name = "sequenceUserPkIdGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "sequence_user_pk")})
    private Long userId;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(columnDefinition = "DATE")
    private LocalDate dateOfJoining;

    @Column(columnDefinition = "DATE")
    private LocalDate dateOfBirth;

    @Column(name = "email_id", unique = true)
    private String emailId;

    private String phoneNumber;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "reporting_user_id")
//    @Transient
    private User reportingUser;

//    private Long reportingUserId;

//    private List<Long> subordinateUserIds;

    //    @Transient
    @OneToMany(cascade = {CascadeType.REFRESH}, mappedBy = "reportingUser", fetch = FetchType.LAZY)
    private Set<User> subordinatesUsers = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String createdBy;
    private String updatedBy;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate createdOn;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate updatedOn;

    private Boolean isEmailVerified;
    private Boolean isMobileVerified;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", dateOfJoining=" + dateOfJoining +
                ", dateOfBirth=" + dateOfBirth +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userType=" + userType +
                '}';

    }
}