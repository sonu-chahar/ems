package com.hexa.ums.web.controller;

import com.hexa.ums.dto.UserDto;
import com.hexa.ums.facade.UserFacade;
import com.hexa.ums.web.request.UserRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
@Log4j2
public class UserController {

    @Resource(name = "userFacade")
    private UserFacade userFacade;

    // get all employees
    @GetMapping("/employees")
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(required = false, name = "userType") String type) {
        if (type != null && !"".equals(type.trim())) {
            return ResponseEntity.ok(userFacade.findAvailableReportingByUserType(type));
        }
        return ResponseEntity.ok(userFacade.findAll());
    }

    // create userRequest rest api
    @PostMapping("/employees")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userFacade.save(userRequest), HttpStatus.CREATED);
    }

    // get employee by id rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity<UserDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(userFacade.findByUserId(id));
    }

    // update employee rest api
    @PutMapping("/employees/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id, @Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userFacade.update(userRequest));
    }

    // delete employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userFacade.deleteUser(id));
    }


}