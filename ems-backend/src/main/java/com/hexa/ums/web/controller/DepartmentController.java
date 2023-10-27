package com.hexa.ums.web.controller;

import com.hexa.ums.dto.DepartmentDto;
import com.hexa.ums.facade.DepartmentFacade;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
@Log4j2
public class DepartmentController {
    @Resource(name = "departmentFacade")
    private DepartmentFacade departmentFacade;

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDto>> getAllUsers() {
        return ResponseEntity.ok(departmentFacade.findAll());
    }
}
