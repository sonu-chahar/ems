package com.hexa.ums.web.controller;

import com.hexa.ums.dto.DepartmentDto;
import com.hexa.ums.facade.DepartmentFacade;
import com.hexa.ums.model.Department;
import com.hexa.ums.service.DepartmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
@Log4j2
public class DepartmentController {
    @Resource(name = "departmentFacade")
    private DepartmentFacade departmentFacade;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDto>> getAllUsers() {
        return ResponseEntity.ok(departmentFacade.findAll());
    }

    @PostConstruct
    public void saveDefaultDepartment() {
        try {
            log.debug("[DepartmentController] [saveDefaultDepartment]");
            List<Department> savedDepartmentList = departmentService.findAll();
            if(savedDepartmentList.isEmpty()) {

                List<String> departmentNameList = Arrays.asList("HR","Network","Delivery");
                for(String departmentName: departmentNameList) {
                    Department department = new Department();
                    department.setName(departmentName);
                    savedDepartmentList.add(department);
                }

                departmentService.saveAll(savedDepartmentList);
                log.debug("[DepartmentController] post [saveDefaultSeverities]");
            }
            else {
                log.debug("default departments are already present in database");
            }
        }
        catch (Exception exception) {
            log.error("ERROR DepartmentController post saveDefaultSeverities- exception:  {}",exception.getMessage());
        }
    }
}
