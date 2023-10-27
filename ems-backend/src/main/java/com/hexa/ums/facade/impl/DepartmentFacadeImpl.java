package com.hexa.ums.facade.impl;

import com.hexa.ums.converter.Populator;
import com.hexa.ums.dto.DepartmentDto;
import com.hexa.ums.facade.DepartmentFacade;
import com.hexa.ums.model.Department;
import com.hexa.ums.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("departmentFacade")
public class DepartmentFacadeImpl implements DepartmentFacade {
    @Autowired
    private DepartmentService departmentService;

    @Resource(name = "departmentPopulator")
    private Populator<Department, DepartmentDto> departmentPopulator;

    @Override
    public List<DepartmentDto> findAll() {
        List<Department> departments = departmentService.findAll();
        return departmentPopulator.populateAll(departments);
    }
}
